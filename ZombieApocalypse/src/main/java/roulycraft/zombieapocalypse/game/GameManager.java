package roulycraft.zombieapocalypse.game;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.ConsoleCommandSender;

import java.io.File;
import java.io.IOException;
import java.util.*;


import org.bukkit.scheduler.BukkitRunnable;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.players.PlayerInstance;
import roulycraft.zombieapocalypse.utility.MinuteSecondsFormat;
import roulycraft.zombieapocalypse.utility.PolishNumberConverter;
import roulycraft.zombieapocalypse.waves.WaveManager;
import roulycraft.zombieapocalypse.weapons.ranged.RangedManager;
import roulycraft.zombieapocalypse.zombie.ZombieManager;

public class GameManager {
    private static GameManager gameManager;
    private static ZombieApocalypse plugin;
    private FileConfiguration gameInstanceConfig = null;
    private File gameInstanceFile = null;
    private final Map<UUID, Location> lastPlayerLocs = new WeakHashMap<>();
    private final Map<UUID, ItemStack[]> lastPlayerInventories = new WeakHashMap<>();
    private final Map<UUID, ItemStack[]> lastPlayerArmour = new WeakHashMap<>();
    public Map<UUID, PlayerInstance> playerStats = new WeakHashMap<>();
    private final Map<String, Integer> arenaCountdownList = new WeakHashMap<>();
    public final Map<String, List<UUID>> arenaZombieList = new WeakHashMap<>();
    private final Map<String, KeyedBossBar> bossbarList = new WeakHashMap<>();
    private final List<GameInstance> gameInstanceList = new ArrayList<>();

    private GameManager() {
    }
    public static GameManager getManager() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }
        return gameManager;
    }

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }
    public GameInstance getGameInstance(String name){

        int left = 0;
        int right = gameInstanceList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int res = name.compareTo(gameInstanceList.get(mid).getName());

            if(res == 0) {
                return gameInstanceList.get(mid);
            }

            if(res > 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return null;
    }
    public void addScore(String name, UUID uuid) {

        if(name.equals("")) {
            return;
        }

        GameInstance gameInstance = getGameInstance(name);

        arenaZombieList.get(name).remove(uuid);
        gameInstance.setZombieKills(gameInstance.getZombieKills() + 1);
        updateBossbar(name);

        if(gameInstance.getZombieKills() >= plugin.getConfig().getInt("settings.newWaveZombieKills")) {

            endWave(name, gameInstance.getWave() + 1);

        }

    }
    private void spawnZombieInArena(String name) {

        GameInstance gameInstance = getGameInstance(name);

        new BukkitRunnable() {
            @Override
            public void run() {

                if(!arenaCountdownList.containsKey(name)) {
                    return;
                }

                Location loc = gameInstance.getRandomZombieSpawnLoc();
                String zombieName = WaveManager.getManager().getWaveInstance(gameInstance.getWave()).getRandomZombie();
                UUID id = ZombieManager.spawnZombie(loc, zombieName, true, gameInstance.getName());
                arenaZombieList.get(gameInstance.getName()).add(id);

                spawnZombieInArena(name);

            }
        }.runTaskLater(plugin, plugin.getConfig().getLong("settings.spawnZombiesDelay") * 20);
    }
    private void startArenaCountdown(String name) {

        new BukkitRunnable() {
            @Override
            public void run() {

                if(!arenaCountdownList.containsKey(name)) {

                    return;

                }

                int currentDelay = arenaCountdownList.get(name) - 1;

                arenaCountdownList.put(name, currentDelay);

                if(currentDelay > 0) {

                    startArenaCountdown(name);
                    sendGameMessage(MessageType.COUNTDOWN, null, name, new String[]{""}
                    );

                }

                else {

                    arenaCountdownList.remove(name);
                    startArena(name);

                }
            }
        }.runTaskLater(plugin, 20L);
    }
    private void startWaveCountdown(String name) {

        new BukkitRunnable() {
            @Override
            public void run() {

                if(!arenaCountdownList.containsKey(name)) {
                    return;
                }

                int currentDelay = arenaCountdownList.get(name) - 1;
                arenaCountdownList.put(name, currentDelay);

                updateBossbar(name);

                for(UUID uuid : getGameInstance(name).getPlayers()) {
                    displayPlayerActionBar(Bukkit.getPlayer(uuid));
                }

                if(currentDelay % 5 == 0) {
                    for(UUID uuid : getGameInstance(name).getPlayers()) {
                        int hp = playerStats.get(uuid).getHp();
                        int maxhp = playerStats.get(uuid).getMaxHP();

                        hp += 5;
                        if(hp > maxhp) {
                            hp = maxhp;
                        }

                        playerStats.get(uuid).setHp(hp);
                    }
                }

                if(currentDelay > 0) {
                    startWaveCountdown(name);
                }

                else {
                    arenaCountdownList.remove(name);
                    endArena(name, 1);
                }
            }
        }.runTaskLaterAsynchronously(plugin, 20L);
    }
    private void startArena(String name) {

        GameInstance gameInstance = getGameInstance(name);

        if(!gameInstance.setGameState(GameState.STARTING)) {
            return;
        }

        arenaZombieList.put(gameInstance.getName(), new ArrayList<>());

        int i = 0;

        gameInstance.setGameState(GameState.STARTING);
        gameInstance.setZombieKills(0);

        String bossbarFormat = plugin.getConfig().getString("messages.formats.bossbarFormat");

        bossbarFormat = bossbarFormat.replace("%wave%", "1");
        bossbarFormat = bossbarFormat.replace("%zombie1%", "0").replace("%zombie2%", Integer.toString(plugin.getConfig().getInt("settings.newWaveZombieKills")));
        bossbarFormat = bossbarFormat.replace("%time%", MinuteSecondsFormat.convert(plugin.getConfig().getInt("settings.gameOverAfter")));

        KeyedBossBar bar = Bukkit.createBossBar(new NamespacedKey(plugin, ("zabossbar."+gameInstance.getName())), bossbarFormat, BarColor.WHITE, BarStyle.SOLID);
        bossbarList.put(name, bar);

        bar.setProgress(1.0);
        getRandomPlayer(name).getWorld().setGameRule(GameRule.NATURAL_REGENERATION, false);

        for(UUID uuid : gameInstance.getPlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            player.teleport(gameInstance.getPlayerSpawnLocs().get(i % gameInstance.getPlayerSpawnLocs().size()));

            givePlayerLoadout(player);
        }

        arenaStartGracePeriod(name);
    }
    private void endArena(String name, Integer endType) {

        GameInstance gameInstance = getGameInstance(name);
        gameInstance.setGameState(GameState.FINISHED);

        new BukkitRunnable(){
            @Override
            public void run() {
                resetArena(name);
            }
        }.runTaskLater(plugin, 100L);

        arenaCountdownList.remove(name);
        switch(endType) {
            case 1: {
                sendGameMessage(MessageType.DEFEAT, null, name, new String[]{"",""});
                break;
            }
            case 2: {
                sendGameMessage(MessageType.VICTORY, null, name, new String[]{"",""});
                break;
            }
            default: {
                break;
            }
        }

    }
    private void resetArena(String name) {

        GameInstance gameInstance = getGameInstance(name);
        gameInstance.setGameState(GameState.RESTARTING);

        KeyedBossBar bar = bossbarList.get(name);
        bar.removeAll();
        bossbarList.remove(name);

        for(UUID uuid : gameInstance.getPlayers()) {
            Player player = Bukkit.getPlayer(uuid);

            player.getInventory().setContents(lastPlayerInventories.get(uuid));
            player.getInventory().setArmorContents(lastPlayerArmour.get(uuid));
            player.teleport(lastPlayerLocs.get(uuid));
            player.setGameMode(GameMode.SURVIVAL);

            lastPlayerInventories.remove(uuid);
            lastPlayerArmour.remove(uuid);
            lastPlayerLocs.remove(uuid);

            playerStats.remove(uuid);

            player.setGameMode(GameMode.ADVENTURE);

            player.setHealth(20);
            player.setFoodLevel(20);
            player.setExp(0f);
            player.setLevel(0);
        }

        gameInstance.getPlayers().clear();

        for(UUID id : arenaZombieList.get(name)) {
            Zombie zombie = (Zombie) Bukkit.getEntity(id);
            zombie.remove();
        }

        arenaZombieList.remove(name);

        gameInstance.setGameState(GameState.LOBBY);
    }
    private void updateBossbar(String name) {

        GameInstance gameInstance = getGameInstance(name);

        double currentSeconds = (double) arenaCountdownList.get(name);
        double maxSeconds = plugin.getConfig().getInt("settings.gameOverAfter");

        KeyedBossBar bossBar = bossbarList.get(gameInstance.getName());
        String bossbarFormat = plugin.getConfig().getString("messages.formats.bossbarFormat");

        String wave = Integer.toString(gameInstance.getWave());
        String zombie = Integer.toString(gameInstance.getZombieKills());
        String time = MinuteSecondsFormat.convert(arenaCountdownList.get(name));

        bossbarFormat = bossbarFormat.replace("%wave%", wave);
        bossbarFormat = bossbarFormat.replace("%zombie1%", zombie).replace("%zombie2%", Integer.toString(plugin.getConfig().getInt("settings.newWaveZombieKills")));
        bossbarFormat = bossbarFormat.replace("%time%", time);

        bossBar.setProgress(currentSeconds/maxSeconds);
        bossBar.setTitle(bossbarFormat);

    }
    public void displayPlayerActionBar(Player player) {

        if(player.getGameMode() != GameMode.ADVENTURE || !playerStats.get(player.getUniqueId()).getAlive()) {
            return;
        }

        int hp = playerStats.get(player.getUniqueId()).getHp();
        int maxhp = playerStats.get(player.getUniqueId()).getMaxHP();
        double hpPercentage = (double) hp/(double) maxhp * 20;

        String bar = plugin.getConfig().getString("messages.formats.playerActionBar")
            .replace("%hp1%", String.valueOf(hp))
            .replace("%hp2%", String.valueOf(maxhp));
        MiniMessage miniMessage = MiniMessage.miniMessage();
        if(hp <= 1.0){
            hpPercentage = 1.0;
        }
        if(hp <= 0.0){
            bar = "";
        }
        player.setHealth(hpPercentage);


        ((Audience) player).sendActionBar(miniMessage.deserialize(bar));

    }
    private void givePlayerLoadout(Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {

                String[] weapon = playerStats.get(player.getUniqueId()).getSelectedRanged().split(":");
                ItemStack item = RangedManager.getManager().getGun(Integer.parseInt(weapon[0]), Integer.parseInt(weapon[1]));

                player.getInventory().clear();
                player.getInventory().setItem(0, item);

            }
        }.runTaskAsynchronously(plugin);
    }
    private void arenaStartGracePeriod(String name) {

        GameInstance gameInstance = getGameInstance(name);

        String[] splitTitles = plugin.getConfig().getString("messages.arena.arenaBeginExplenationTitles").split("%nl%");
        String[] splitSubtitles = plugin.getConfig().getString("messages.arena.arenaBeginExplenationSubtitles").split("%nl%");
        String[] splitMessages = plugin.getConfig().getString("messages.arena.arenaBeginExplenationMessages").split("%nl%");

        gameInstance.setGameState(GameState.ACTIVE);

        for(int i = 0; i < splitTitles.length - 1; i++) {

            int finalI = i;
            new BukkitRunnable() {

                @Override
                public void run() {

                    String[] message = {splitTitles[finalI], splitSubtitles[finalI], splitMessages[finalI]};

                    sendGameMessage(MessageType.START_EXPLENATION, null, name, message);

                }
            }.runTaskLaterAsynchronously(plugin, (100L * i));

        }

        new BukkitRunnable() {

            @Override
            public void run() {
                sendGameMessage(MessageType.START, null, name, new String[]{""});
            }
        }.runTaskLaterAsynchronously(plugin, 100L * (splitTitles.length - 1));

        new BukkitRunnable() {


            @Override
            public void run() {

                for (UUID uuid : gameInstance.getPlayers()) {
                    KeyedBossBar bar = bossbarList.get(name);
                    bar.addPlayer(Bukkit.getPlayer(uuid));
                }

                startWave(name, 1);

            }
        }.runTaskLaterAsynchronously(plugin, (100L * splitTitles.length));

    }
    private void endWave(String name, Integer wave) {

        arenaCountdownList.remove(name);

        for(UUID id : arenaZombieList.get(name)) {
            Entity entity = Bukkit.getEntity(id);
            entity.getWorld().spawnParticle(Particle.CLOUD, entity.getLocation(), 50, 0.5, 1, 0.5, 0.1);
            entity.remove();
        }

        arenaZombieList.remove(name);
        arenaZombieList.put(name, new ArrayList<>());

        if(wave > plugin.getConfig().getInt("settings.waveCount")) {
            endArena(name, 2);
            return;
        }

        sendGameMessage(MessageType.WAVE_BEATEN, null, name, new String[]{""});

        new BukkitRunnable() {
            @Override
            public void run() {

                startWave(name, wave);

            }
        }.runTaskLater(plugin, plugin.getConfig().getLong("settings.delayBeforeNextWave") * 20);

    }
    private void startWave(String name, Integer wave) {

        GameInstance gameInstance = getGameInstance(name);

        for(UUID uuid : gameInstance.getPlayers()) {
            if(!playerStats.get(uuid).getAlive()) {
                playerRespawn(Bukkit.getPlayer(uuid));
            }
            givePlayerLoadout(Bukkit.getPlayer(uuid));
        }

        gameInstance.setZombieKills(0);
        gameInstance.setWave(wave);
        sendGameMessage(MessageType.WAVE_NEXT, null, name, new String[]{""});
        arenaCountdownList.put(name, plugin.getConfig().getInt("settings.gameOverAfter"));
        startWaveCountdown(name);
        spawnZombieInArena(name);
    }
    private void registerPlayerStats(Player player, String name, String loadout, String selectedWeapon) {

        new BukkitRunnable() {
            @Override
            public void run() {

                int hp;
                double speed;
                double speedModifier;
                double ammoModifier;
                double damageModifier;
                double spreadModifier;

                switch (loadout) {
                    default: {

                        hp = 100;
                        speed = 1.0;
                        speedModifier = 1.0;
                        ammoModifier = 1.0;
                        damageModifier = 1.0;
                        spreadModifier = 1.0;

                        break;
                    }
                }

                playerStats.put(
                    player.getUniqueId(),
                    new PlayerInstance(
                        player,
                        name,
                        true,
                        selectedWeapon,
                        hp,
                        hp,
                        speed,
                        speedModifier,
                        ammoModifier,
                        damageModifier,
                        spreadModifier
                    )
                );
            }
        }.runTaskAsynchronously(plugin);
    }
    public void playerDeath(Player player) {

        UUID playerUUID = player.getUniqueId();
        PlayerInstance playerInstance = playerStats.get(playerUUID);

        player.getInventory().clear();
        playerInstance.setAlive(false);
        player.setGameMode(GameMode.SPECTATOR);

        int alivePlayers = 0;
        for(UUID uuid : getGameInstance(playerInstance.getInArena()).getPlayers()) {
            if(playerStats.get(uuid).getAlive()) {
                alivePlayers += 1;
            }
        }

        sendGameMessage(MessageType.PLAYER_DEATH, player, playerInstance.getInArena(), new String[]{"", "", "", Integer.toString(alivePlayers)});
        if(alivePlayers == 0) {
            endArena(playerInstance.getInArena(), 1);
        }
    }
    private void playerRespawn(Player player) {

        playerStats.get(player.getUniqueId()).setAlive(true);
        String name = playerStats.get(player.getUniqueId()).getInArena();
        player.teleport(getGameInstance(name).getRandomPlayerSpawnLoc());
        player.setGameMode(GameMode.ADVENTURE);
        givePlayerLoadout(player);

    }
    public void addPlayer(Player player, String name) {

        GameInstance gameInstance = getGameInstance(name);

        if (playerStats.containsKey(player.getUniqueId())) {
            player.sendMessage("§4BŁĄD! §cJesteś już na arenie!");
            return;
        }

        if (gameInstance == null) {
            player.sendMessage("§4BŁĄD! §cArena nie istnieje!");
            return;
        }

        if (gameInstance.gameState != GameState.LOBBY && gameInstance.gameState != GameState.COUNTDOWN) {
            player.sendMessage("§4BŁĄD! §cNie możesz dołączyć na tą arenę!");
            return;
        }

        lastPlayerInventories.put(player.getUniqueId(), player.getInventory().getContents());
        lastPlayerArmour.put(player.getUniqueId(), player.getInventory().getArmorContents());

        player.getInventory().setArmorContents(null);
        player.getInventory().clear();
        player.setGameMode(GameMode.ADVENTURE);

        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0f);
        player.setLevel(0);

        lastPlayerLocs.put(player.getUniqueId(), player.getLocation());
        player.teleport(gameInstance.getLobby());

        gameInstance.getPlayers().add(player.getUniqueId());
        sendGameMessage(MessageType.PLAYER_JOIN, player, name, new String[]{""});

        if(gameInstance.getPlayers().size() >= plugin.getConfig().getInt("settings.startCountdownAtPlayers") &&
            !arenaCountdownList.containsKey(name)) {

            gameInstance.setGameState(GameState.COUNTDOWN);
            arenaCountdownList.put(gameInstance.getName(), plugin.getConfig().getInt("settings.countdownTime") + 1);
            sendGameMessage(MessageType.START_COUNTDOWN, null, name, new String[]{""});
            startArenaCountdown(name);

        }

        if(gameInstance.getPlayers().size() >= plugin.getConfig().getInt("settings.countdownReductionPlayers") &&
            arenaCountdownList.containsKey(name) &&
            arenaCountdownList.get(name) > plugin.getConfig().getInt("settings.countdownReduceTo")) {

            sendGameMessage(MessageType.REDUCE_COUNTDOWN, null, name, new String[]{""});
            arenaCountdownList.put(gameInstance.getName(), plugin.getConfig().getInt("settings.countdownReduceTo") + 1);

        }

        registerPlayerStats(player, name, "", "1:0");
    }
    public void changePlayerWeapon(Player player, String weapon) {

        if (!playerStats.containsKey(player.getUniqueId())) {
            player.sendMessage("§4BŁĄD! §cNie jesteś na arenie!");
            return;
        }

        String[] split = weapon.split(":");

        if(Integer.parseInt(split[0]) < 1 || Integer.parseInt(split[0]) > 15 || Integer.parseInt(split[1]) < 0 || Integer.parseInt(split[1]) > 3) {
            player.sendMessage("§4BŁĄD! §cNie ma takiej broni");
            return;
        }

        String name = RangedManager.getManager().getRangedInstance(Integer.parseInt(split[0]), Integer.parseInt(split[1])).getName();

        sendGameMessage(MessageType.CHANGE_WEAPON, player, "", new String[]{"", name, split[1]});
        playerStats.get(player.getUniqueId()).setSelectedRanged(weapon);
    }
    public void removePlayer(Player player) {

        if(!playerStats.containsKey(player.getUniqueId())) {
            player.sendMessage("§4BŁĄD! §cNie jesteś na arenie!");
            return;
        }

        GameInstance gameInstance = getGameInstance(playerStats.get(player.getUniqueId()).getInArena());

        if (gameInstance == null) {
            player.sendMessage("§4BŁĄD! §cOperacja nieznana!");
            return;
        }

        playerStats.remove(player.getUniqueId());

        player.getInventory().clear();

        player.getInventory().setContents(lastPlayerInventories.get(player.getUniqueId()));
        player.getInventory().setArmorContents(lastPlayerArmour.get(player.getUniqueId()));

        lastPlayerInventories.remove(player.getUniqueId());
        lastPlayerArmour.remove(player.getUniqueId());

        player.teleport(lastPlayerLocs.get(player.getUniqueId()));

        lastPlayerLocs.remove(player.getUniqueId());

        playerStats.remove(player.getUniqueId());

        if(gameInstance.gameState == GameState.ACTIVE || gameInstance.gameState == GameState.STARTING) {
            KeyedBossBar bar = bossbarList.get(gameInstance.getName());
            bar.removePlayer(player);
        }

        player.setFireTicks(0);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setExp(0f);
        player.setLevel(0);
        player.setGameMode(GameMode.SURVIVAL);

        gameInstance.getPlayers().remove(player.getUniqueId());

        if((gameInstance.gameState == GameState.ACTIVE || gameInstance.gameState == GameState.STARTING) && gameInstance.getPlayers().size() == 0) {
            endArena(
                gameInstance.getName(),
                0
            );
        }
    }
    private void sendGameMessage(MessageType type, Player player, String name, String[] message) {

        GameInstance gameInstance = getGameInstance(name);
        MiniMessage miniMessage = MiniMessage.miniMessage();

        switch (type) {
            case PLAYER_JOIN: {

                message[0] = "";

                message[0] += plugin.getConfig().getString("messages.arena.playerJoinLobby");
                message[0] += " ";
                message[0] += plugin.getConfig().getString("messages.formats.arenaPlayersFormat");
                message[0] = message[0].replace("%min%", Integer.toString(gameInstance.getPlayers().size()))
                    .replace("%max%", plugin.getConfig().getString("settings.maxPlayersPerArena"))
                    .replace("%player%", player.getName());

                for(UUID uuid : gameInstance.getPlayers()) {
                    ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(message[0]));
                }
                break;
            }
            case PLAYER_LEAVE: {

                message[0] = "";

                message[0] += plugin.getConfig().getString("messages.arena.playerLeaveLobby");
                message[0] += " ";
                message[0] += plugin.getConfig().getString("messages.formats.arenaPlayersFormat");
                message[0] = message[0].replace("%min%", Integer.toString(gameInstance.getPlayers().size()))
                    .replace("%max%", plugin.getConfig().getString("settings.maxPlayersPerArena"))
                    .replace("%player%", player.getName());;

                for(UUID uuid : gameInstance.getPlayers()) {
                    ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(message[0]));
                }
                break;
            }
            case START_COUNTDOWN: {

                message[0] = plugin.getConfig().getString("messages.arena.minimumPlayersReached");

                for(UUID uuid : gameInstance.getPlayers()) {
                    ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(message[0]));
                }
                break;
            }
            case REDUCE_COUNTDOWN: {

                message[0] = plugin.getConfig().getString("messages.arena.reductionPlayersReached");

                for(UUID uuid : gameInstance.getPlayers()) {
                    ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(message[0]));
                }
                break;
            }
            case COUNTDOWN: {

                Integer countdown = arenaCountdownList.get(name);
                String time = PolishNumberConverter.convert(countdown, "sekundę", "sekundy", "sekund");

                message[0] = plugin.getConfig().getString("messages.arena.arenaCountdown");
                message[0] = message[0].replace("%time%", time);

                for(UUID uuid : gameInstance.getPlayers()) {
                    if(countdown % 10 == 0 || countdown < 10) {
                        ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(message[0]));
                    }
                    Bukkit.getPlayer(uuid).sendTitle("", Integer.toString(countdown), 0, 21, 0);
                }
                break;
            }
            case CHANGE_WEAPON: {

                message[0] = plugin.getConfig().getString("messages.arena.weaponChange");
                message[0] = message[0].replace("%weapon%", message[1])
                    .replace("%level%", message[2]);

                player.sendMessage(message[0]);
                break;
            }
            case START_EXPLENATION: {

                for(UUID uuid : gameInstance.getPlayers()) {
                    for(String split : message[2].split("%br%")) {
                        ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(split));
                    }
                    Bukkit.getPlayer(uuid).sendTitle(message[0], message[1], 0, 105, 0);
                }
                break;
            }
            case START: {
                String title = plugin.getConfig().getString("messages.arena.arenaBeginTitle");
                String subtitle = plugin.getConfig().getString("messages.arena.arenaBeginSubtitle");
                message[0] = plugin.getConfig().getString("messages.arena.arenaBeginMessages");

                for(UUID uuid : gameInstance.getPlayers()) {
                    for(String split : message[0].split("%br%")) {
                        ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(split));
                    }
                    Bukkit.getPlayer(uuid).sendTitle(title, subtitle, 0, 60, 30);
                }
                break;
            }
            case WAVE_BEATEN: {

                message[0] = plugin.getConfig().getString("messages.arena.waveBeaten");
                message[0] = message[0].replace("%wave%", Integer.toString(gameInstance.getWave()));
                for(UUID uuid : gameInstance.getPlayers()) {
                    ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(message[0]));
                }
                break;
            }
            case WAVE_NEXT: {

                message[0] = plugin.getConfig().getString("messages.arena.nextWave");
                message[0] = message[0].replace("%wave%", Integer.toString(gameInstance.getWave()));

                String title = plugin.getConfig().getString("messages.arena.nextWaveTitle");
                title = title.replace("%wave%", Integer.toString(gameInstance.getWave()));
                String subtitle = "";

                if((gameInstance.getWave() - 1) % 10 == 0 && gameInstance.getWave() != 1) {
                    subtitle = plugin.getConfig().getString("messages.arena.nextWaveSubtitle10s");
                }

                for(UUID uuid : gameInstance.getPlayers()) {
                    ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(message[0]));
                    Bukkit.getPlayer(uuid).sendTitle(title, subtitle, 10, 40, 10);
                }
                break;
            }
            case PLAYER_DEATH: {

                message[0] = plugin.getConfig().getString("messages.arena.playerDeathMessage");
                message[1] = plugin.getConfig().getString("messages.arena.playerDeathTitle");
                message[2] = plugin.getConfig().getString("messages.arena.playerDeathSubtitle");
                message[0] = message[0].replace("%player%", player.getName())
                    .replace("%alive%", message[3]);

                for(UUID uuid : gameInstance.getPlayers()) {
                    ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(message[0]));
                }
                player.sendTitle(message[1], message[2], 0, 40, 20);
                break;
            }
            case VICTORY: {

                message[0] = plugin.getConfig().getString("messages.arena.victory");
                message[1] = plugin.getConfig().getString("messages.arena.victoryTitle");
                for(UUID uuid : gameInstance.getPlayers()) {
                    ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(message[0]));
                    Bukkit.getPlayer(uuid).sendTitle(message[1], "", 0, 40, 60);
                }
                break;
            }
            case DEFEAT: {

                message[0] = plugin.getConfig().getString("messages.arena.defeat");
                message[1] = plugin.getConfig().getString("messages.arena.defeatTitle");
                for(UUID uuid : gameInstance.getPlayers()) {
                    ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(message[0]));
                    Bukkit.getPlayer(uuid).sendTitle(message[1], "", 0, 40, 60);
                }
                break;
            }
            case ONE_PLAYER: {

                for (String msg : message) {
                    ((Audience) player).sendMessage(miniMessage.deserialize(msg));
                }
                break;
            }
            case EVERYONE: {

                for (String msg : message) {
                    for (UUID uuid : gameInstance.getPlayers()) {
                        ((Audience) Bukkit.getPlayer(uuid)).sendMessage(miniMessage.deserialize(msg));
                    }
                }
                break;
            }
        }

    }
    public boolean checkIfGameInstanceExists(String name) {

        GameInstance gameInstance = null;

        for (GameInstance gameInstance1 : this.gameInstanceList) {
            if (gameInstance1.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }
    public void createGameInstance(Player player, String name) {

        GameInstance gameInstance = null;

        for (GameInstance gameInstance1 : this.gameInstanceList) {
            if (gameInstance1.getName().equals(name)) {
                player.sendMessage("§4BŁĄD! §cArena §f" + name + " §cjuż istnieje!");
                return;
            }
        }

        gameInstance = new GameInstance(name);
        this.gameInstanceList.add(gameInstance);

        player.sendMessage("§2SUKCES! §aArena §f" + name + " §azostała utworzona!");
        reloadGameInstanceConfig(name);
        getGameInstanceConfig(name).set("displayName", "-");
        getGameInstanceConfig(name).set("lobby", "-");
        getGameInstanceConfig(name).set("playerSpawnLocs.0", "-");
        getGameInstanceConfig(name).set("zombieSpawnLocs.0", "-");
        saveGameInstanceConfig(name);
    }
    public void reloadGameInstanceConfig(String name) {
        if (gameInstanceFile == null) {
            gameInstanceFile = new File(plugin.getDataFolder() + File.separator + "instances" + File.separator + "arenas", (name + ".yml"));
        }

        gameInstanceConfig = YamlConfiguration.loadConfiguration(gameInstanceFile);
        gameInstanceFile = new File(plugin.getDataFolder() + File.separator + "instances" + File.separator + "arenas", (name + ".yml"));
    }
    public FileConfiguration getGameInstanceConfig(String name) {
        if (gameInstanceConfig == null) {
            reloadGameInstanceConfig(name);
        }
        return gameInstanceConfig;
    }
    public void saveGameInstanceConfig(String name) {
        if (gameInstanceConfig == null || gameInstanceFile == null) {
            return;
        }
        try {
            getGameInstanceConfig(name).save(gameInstanceFile);
        }
        catch (IOException ex) {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            console.sendMessage("§4BŁĄD KRYTYCZNY §cNie można było zapisać konfiguracji instancji areny do §f" + gameInstanceFile);
            console.sendMessage(String.valueOf(ex));
        }
    }
    public boolean loadGameInstanceConfig(String name) {

        reloadGameInstanceConfig(name);

        if (!gameInstanceFile.exists()) {
            return false;
        }

        if (!gameInstanceList.contains(this.getGameInstance(name))) {

            GameInstance gameInstance = new GameInstance(name);
            gameInstanceList.add(gameInstance);
            gameInstanceList.sort(Comparator.comparing(GameInstance::getName));
        }

        if(gameInstanceConfig.getLocation("lobby") != null) {
            getGameInstance(name).setLobby(gameInstanceConfig.getLocation("lobby"));
        }

        if(gameInstanceConfig.getString("displayName") != null || Objects.equals(gameInstanceConfig.getString("displayName"), "-")) {
            getGameInstance(name).setDisplayName(gameInstanceConfig.getString("displayName"));
        }

        for (String path : gameInstanceConfig.getConfigurationSection("playerSpawnLocs").getKeys(false)) {

            Location loc = gameInstanceConfig.getLocation("playerSpawnLocs."+path);
            getGameInstance(name).getPlayerSpawnLocs().add(loc);

        }

        for (String path : gameInstanceConfig.getConfigurationSection("zombieSpawnLocs").getKeys(false)) {

            Location loc = gameInstanceConfig.getLocation("zombieSpawnLocs."+path);
            getGameInstance(name).getZombieSpawnLocs().add(loc);

        }
        return true;
    }
    public Player getRandomPlayer(String name) {
        GameInstance gameInstance = getGameInstance(name);

        List<UUID> list = gameInstance.getPlayers();

        for(UUID uuid : list) {
            if(Bukkit.getPlayer(uuid).getGameMode() != GameMode.ADVENTURE)
                list.remove(uuid);
        }

        int playerIndex = new Random().nextInt(list.size());
        if(list.size() == 1){
            playerIndex = 0;
        }

        return Bukkit.getPlayer(list.get(playerIndex));
    }
}
