package roulycraft.zombieapocalypse.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
import roulycraft.zombieapocalypse.weapons.ranged.RangedInstance;
import roulycraft.zombieapocalypse.weapons.ranged.RangedManager;
import roulycraft.zombieapocalypse.zombie.ZombieInstance;
import roulycraft.zombieapocalypse.zombie.ZombieManager;

public class GameManager {
    private static GameManager gameManager;
    private static ZombieApocalypse plugin;
    private FileConfiguration gameInstanceConfig = null;
    private File gameInstanceFile = null;
    private final Map<UUID, Location> lastPlayerLocs = new WeakHashMap<>();
    private final Map<UUID, ItemStack[]> lastPlayerInventories = new WeakHashMap<>();
    private final Map<UUID, ItemStack[]> lastPlayerArmour = new WeakHashMap<>();
    private final Map<UUID, PlayerInstance> playerStats = new WeakHashMap<>();
    private final Map<String, Integer> arenaCountdownList = new WeakHashMap<>();
    private final Map<String, List<Zombie>> arenaZombieList = new WeakHashMap<>();
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
    private void startArenaCountdown(GameInstance gameInstance) {

        new BukkitRunnable() {
            @Override
            public void run() {

                if(!arenaCountdownList.containsKey(gameInstance.getName())) {

                    return;

                }

                int currentDelay = arenaCountdownList.get(gameInstance.getName()) - 1;

                arenaCountdownList.put(gameInstance.getName(), currentDelay);

                if(currentDelay > 0) {

                    startArenaCountdown(gameInstance);
                    sendGameMessage(MessageType.COUNTDOWN, null, gameInstance, new String[]{""}
                    );

                }

                else {

                    arenaCountdownList.remove(gameInstance.getName());
                    startArena(gameInstance);

                }
            }
        }.runTaskLaterAsynchronously(plugin, 1L);
    }
    private void startWaveCountdown(GameInstance gameInstance) {

        new BukkitRunnable() {
            @Override
            public void run() {

                if(!arenaCountdownList.containsKey(gameInstance.getName())) {

                    return;

                }

                int currentDelay = arenaCountdownList.get(gameInstance.getName()) - 1;

                arenaCountdownList.put(gameInstance.getName(), currentDelay);

                if(currentDelay > 0) {

                    startWaveCountdown(gameInstance);

                }

                else {

                    arenaCountdownList.remove(gameInstance.getName());
                    startArena(gameInstance);

                }
            }
        }.runTaskLaterAsynchronously(plugin, 1L);
    }
    private void startArena(GameInstance gameInstance) {

        if(!gameInstance.setGameState(GameState.STARTING)) {
            return;
        }

        int i = 0;

        String bossbarFormat = plugin.getConfig().getString("messages.formats.bossbarFormat");

        bossbarFormat.replace("%wave%", "1");
        bossbarFormat.replace("%zombie1%", "0").replace("%zombie2%", Integer.toString(plugin.getConfig().getInt("settings.newWavezombieKills")));
        bossbarFormat.replace("%time%", MinuteSecondsFormat.convert(plugin.getConfig().getInt("settings.gameOverAfter")));

        Bukkit.createBossBar(new NamespacedKey(plugin, ("zabossbar:"+gameInstance.getName())), bossbarFormat, BarColor.WHITE, BarStyle.SOLID);

        Bukkit.getBossBar(new NamespacedKey(plugin, ("zabossbar:"+gameInstance.getName()))).setProgress(100.0);

        while(i <= gameInstance.getPlayers().size()) {

            i++;
            Player player = Bukkit.getPlayer(gameInstance.getPlayers().get(i));
            player.teleport(gameInstance.getPlayerSpawnLocs().get(i % gameInstance.getPlayerSpawnLocs().size()));

            givePlayerLoadout(player);

        }
    }
    private void updateBossbar(GameInstance gameInstance) {

        int currentSeconds = arenaCountdownList.get(gameInstance);
        int maxSeconds = plugin.getConfig().getInt("settings.gameOverAfter");

        KeyedBossBar bossBar = Bukkit.getBossBar(new NamespacedKey(plugin, ("zabossbar:"+gameInstance.getName())));
        String bossbarFormat = plugin.getConfig().getString("messages.formats.bossbarFormat");

        bossbarFormat.replace("%wave%", "1");
        bossbarFormat.replace("%zombie1%", "0").replace("%zombie2%", Integer.toString(plugin.getConfig().getInt("settings.newWavezombieKills")));
        bossbarFormat.replace("%time%", MinuteSecondsFormat.convert(plugin.getConfig().getInt("settings.gameOverAfter")));

        bossBar.setProgress(currentSeconds/maxSeconds);
        bossBar.setTitle(bossbarFormat);

    }
    private void givePlayerLoadout(Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {

                String[] weapon = playerStats.get(player.getUniqueId()).toString().split(":");
                ItemStack item = RangedManager.getManager().getGun(Integer.parseInt(weapon[0]), Integer.parseInt(weapon[1]));

                player.getInventory().clear();
                player.getInventory().setItem(0, item);

            }
        }.runTaskAsynchronously(plugin);
    }
    private void arenaStartGracePeriod(GameInstance gameInstance) {

        String[] splitTitles = plugin.getConfig().getString("messages.arena.arenaBeginExplenationTitles").split("%nl%");
        String[] splitSubtitles = plugin.getConfig().getString("messages.arena.arenaBeginExplenationTitles").split("%nl%");
        String[] splitMessages = plugin.getConfig().getString("messages.arena.arenaBeginExplenationmessages").split("%nl%");

        for(int i = 0; i < splitTitles.length; i++) {

            int finalI = i;
            new BukkitRunnable() {

                @Override
                public void run() {

                    String[] message = {splitTitles[finalI], splitSubtitles[finalI], splitMessages[finalI]};

                    sendGameMessage(MessageType.START, null, gameInstance, message);

                }
            }.runTaskLaterAsynchronously(plugin, (100L * i));

        }

        new BukkitRunnable() {


            @Override
            public void run() {

                for (UUID uuid : gameInstance.getPlayers()) {

                    Bukkit.getBossBar(new NamespacedKey(plugin, ("zabossbar:"+gameInstance.getName()))).addPlayer(Bukkit.getPlayer(uuid));

                }

                startWave(gameInstance, 1);

            }
        }.runTaskLaterAsynchronously(plugin, (100L * splitTitles.length));

    }
    private void endWave(GameInstance gameInstance, Integer wave) {

        new BukkitRunnable() {
            @Override
            public void run() {

                startWave(gameInstance, wave);

            }
        }.runTaskLaterAsynchronously(plugin, 100L);

    }
    private void startWave(GameInstance gameInstance, Integer wave) {

        String[] msg = {""};
        gameInstance.setWave(wave);
        sendGameMessage(MessageType.WAVE_NEXT, null, gameInstance, msg);
        arenaCountdownList.put(gameInstance.getName(), plugin.getConfig().getInt("settings.gameOverAfter"));
        startWaveCountdown(gameInstance);

    }
    private void registerPlayerStats(Player player, String loadout, String selectedWeapon) {

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
    public void addPlayer(Player p, GameInstance gameInstance) {

        if (gameInstance == null) {
            p.sendMessage("§4BŁĄD! §cArena nie istnieje!");
            return;
        }

        if (this.isInGame(p)) {
            p.sendMessage("§4BŁĄD! §cJesteś już na arenie!");
            return;
        }

        lastPlayerInventories.put(p.getUniqueId(), p.getInventory().getContents());
        lastPlayerArmour.put(p.getUniqueId(), p.getInventory().getArmorContents());

        p.getInventory().setArmorContents(null);
        p.getInventory().clear();

        lastPlayerLocs.put(p.getUniqueId(), p.getLocation());
        p.teleport(gameInstance.getRandomPlayerSpawnLoc());

        gameInstance.getPlayers().add(p.getUniqueId());
        sendGameMessage(MessageType.PLAYER_JOIN, p, gameInstance, new String[]{""});

        if(gameInstance.getPlayers().size() >= plugin.getConfig().getInt("settings.startCountdownAtPlayers") &&
            !arenaCountdownList.containsKey(gameInstance)) {

            arenaCountdownList.put(gameInstance.getName(), plugin.getConfig().getInt("settings.startCountdownAtPlayers"));

        }

        if(gameInstance.getPlayers().size() >= plugin.getConfig().getInt("settings.countdownReductionPlayers") &&
            arenaCountdownList.containsKey(gameInstance) &&
            arenaCountdownList.get(gameInstance) > plugin.getConfig().getInt("settings.countdownReduceTo")) {

            arenaCountdownList.put(gameInstance.getName(), plugin.getConfig().getInt("settings.startCountdownAtPlayers"));

        }
    }
    public void removePlayer(Player p) {

        GameInstance gameInstance = getGameInstance(playerStats.get(p.getUniqueId()).getInArena());

        if (gameInstance == null) {
            p.sendMessage("§4BŁĄD! §cOperacja nieznana!");
            return;
        }

        playerStats.remove(p.getUniqueId());
        gameInstance.getPlayers().remove(p.getUniqueId());

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.getInventory().setContents(lastPlayerInventories.get(p.getUniqueId()));
        p.getInventory().setArmorContents(lastPlayerArmour.get(p.getUniqueId()));

        lastPlayerInventories.remove(p.getUniqueId());
        lastPlayerArmour.remove(p.getUniqueId());

        p.teleport(lastPlayerLocs.get(p.getUniqueId()));

        lastPlayerLocs.remove(p.getUniqueId());

        p.setFireTicks(0);
    }
    private void sendGameMessage(MessageType type, Player player, GameInstance gameInstance, String[] message) {

        switch (type) {
            case PLAYER_JOIN: {

                message[0] = "";

                message[0] += plugin.getConfig().getString("messages.arena.playerJoinLobby");
                message[0] += " ";
                message[0] += plugin.getConfig().getString("messages.format.playersFormat").
                    replace("%min%", Integer.toString(gameInstance.getPlayers().size())).
                    replace("%max%", plugin.getConfig().getString("settings.maxPlayersPerArena"));

                for(UUID uuid : gameInstance.getPlayers()) {
                    Bukkit.getPlayer(uuid).sendMessage(message[0]);
                }
                break;

            }
            case PLAYER_LEAVE: {

                message[0] = "";

                message[0] += plugin.getConfig().getString("messages.arena.playerLeaveLobby");
                message[0] += " ";
                message[0] += plugin.getConfig().getString("messages.format.playersFormat").
                    replace("%min%", Integer.toString(gameInstance.getPlayers().size())).
                    replace("%max%", plugin.getConfig().getString("settings.maxPlayersPerArena"));

                for(UUID uuid : gameInstance.getPlayers()) {
                    Bukkit.getPlayer(uuid).sendMessage(message[0]);
                }
                break;

            }
            case COUNTDOWN: {

                int countdown = arenaCountdownList.get(gameInstance);
                String time = PolishNumberConverter.convert(countdown, "sekunda", "sekundy", "sekund");

                message[0] = plugin.getConfig().getString("messages.arena.arenaCountdown");
                message[0].replace("%time%", time);

                for(UUID uuid : gameInstance.getPlayers()) {
                    if(countdown % 10 == 0 || countdown < 10) {
                        Bukkit.getPlayer(uuid).sendMessage(message[0]);
                    }
                    Bukkit.getPlayer(uuid).sendTitle("", Integer.toString(countdown), 0, 21, 0);
                }
                break;

            }

            case START: {

                for(UUID uuid : gameInstance.getPlayers()) {
                    for(String split : message[2].split("%br%")) {
                        Bukkit.getPlayer(uuid).sendMessage(split);
                    }
                    Bukkit.getPlayer(uuid).sendTitle(message[0], message[1], 0, 100, 0);
                }
                break;

            }

            case WAVE_BEATEN: {

                message[0] = plugin.getConfig().getString("messages.arena.waveBeaten");
                for(UUID uuid : gameInstance.getPlayers()) {
                    Bukkit.getPlayer(uuid).sendMessage(message[0]);
                }
                break;

            }

            case WAVE_NEXT: {

                message[0] = plugin.getConfig().getString("messages.arena.nextWave");
                message[0].replace("%wave%", Integer.toString(gameInstance.getWave()));

                String title = plugin.getConfig().getString("messages.arena.nextWave");
                String subtitle = "";

                if((gameInstance.getWave() - 1) % 10 == 0 && gameInstance.getWave() != 1) {
                    subtitle = plugin.getConfig().getString("messages.arena.nextWaveSubtitle10s");
                }

                for(UUID uuid : gameInstance.getPlayers()) {
                    Bukkit.getPlayer(uuid).sendMessage(message[0]);
                    Bukkit.getPlayer(uuid).sendTitle(title, subtitle, 10, 40, 10);
                }
                break;

            }

            case ONE_PLAYER:

                for(String msg : message) {
                    player.sendMessage(msg);
                }
                break;

            case EVERYONE:

                for(String msg : message) {
                    for (UUID uuid : gameInstance.getPlayers()) {
                        Bukkit.getPlayer(uuid).sendMessage(msg);
                    }
                }
                break;

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
    public void createGameInstance(Player p, String name) {

        GameInstance gameInstance = null;

        for (GameInstance gameInstance1 : this.gameInstanceList) {
            if (gameInstance1.getName().equals(name)) {
                p.sendMessage("§4BŁĄD! §cArena §f" + name + " §cjuż istnieje!");
                return;
            }
        }

        gameInstance = new GameInstance(name);
        this.gameInstanceList.add(gameInstance);

        p.sendMessage("§2SUKCES! §aArena §f" + name + " §azostała utworzona!");
        reloadGameInstanceConfig(name);
        getGameInstanceConfig(name).set("displayName", "-");
        getGameInstanceConfig(name).set("lobby", "-");
        getGameInstanceConfig(name).set("playerSpawnLocs.0", "-");
        getGameInstanceConfig(name).set("zombieSpawnLocs.0", "-");
        saveGameInstanceConfig(name);
    }
    public Boolean isInGame(Player p) {
        for (GameInstance gameInstance : this.gameInstanceList) {
            if (gameInstance.getPlayers().contains(p.getUniqueId()));
                return true;
        }
        return false;
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
}
