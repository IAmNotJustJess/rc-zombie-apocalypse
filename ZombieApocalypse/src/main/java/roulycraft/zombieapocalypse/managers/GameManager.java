package roulycraft.zombieapocalypse.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.ConsoleCommandSender;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.UUID;


import roulycraft.zombieapocalypse.ZombieApocalypse;
public class GameManager {
    private static GameManager gameManager;
    private static ZombieApocalypse plugin;
    private FileConfiguration gameInstanceConfig = null;
    private File gameInstanceFile = null;
    private final Map<UUID, Location> lastPlayerLocs = new HashMap<>();
    private final Map<UUID, ItemStack[]> lastPlayerInventories = new HashMap<>();
    private final Map<UUID, ItemStack[]> lastPlayerArmour = new HashMap<>();

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
        for (GameInstance gameInstance : this.gameInstanceList) {
            if (gameInstance.getName().equals(name)) {
                return gameInstance;
            }
        }

        return null;
    }

    public void addPlayer(Player p, String name) {
        GameInstance gameInstance = this.getGameInstance(name);

        if (gameInstance == null) {
            p.sendMessage("§4BŁĄD! §cArena nie istnieje!");
            return;
        }

        if (this.isInGame(p)) {
            p.sendMessage("§4BŁĄD! §cJesteś już na arenie!");
            return;
        }

        gameInstance.getPlayers().add(p.getUniqueId());

        lastPlayerInventories.put(p.getUniqueId(), p.getInventory().getContents());
        lastPlayerArmour.put(p.getUniqueId(), p.getInventory().getArmorContents());

        p.getInventory().setArmorContents(null);
        p.getInventory().clear();

        lastPlayerLocs.put(p.getUniqueId(), p.getLocation());
        p.teleport(gameInstance.getRandomPlayerSpawnLoc());
    }

    public void removePlayer(Player p, String name) {
        GameInstance gameInstance = null;

        for (GameInstance gameInstance1 : this.gameInstanceList) {
            if (gameInstance1.getPlayers().contains(p.getUniqueId())) {
                gameInstance = gameInstance1;
            }
        }

        if (gameInstance == null) {
            p.sendMessage("§4BŁĄD! §cOperacja nieznana!");
            return;
        }

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

    public GameInstance createArena(Player p, String name) {

        GameInstance gameInstance = null;

        for (GameInstance gameInstance1 : this.gameInstanceList) {
            if (gameInstance1.getName().equals(name)) {
                p.sendMessage("§4BŁĄD! §cArena §f" + name + "§cjuż istnieje!");
                return null;
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
        return gameInstance;
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
            gameInstanceFile = new File(plugin.getDataFolder() + File.separator + "instances", (name + ".yml"));
        }

        gameInstanceConfig = YamlConfiguration.loadConfiguration(gameInstanceFile);
    }

    public FileConfiguration getGameInstanceConfig(String name) {
        if (gameInstanceFile == null) {
            reloadGameInstanceConfig(name);
        }
        return gameInstanceConfig;
    }

    public void saveGameInstanceConfig(String name) {
        if (gameInstanceConfig == null || gameInstanceFile == null) {
            return;
        }
        try {;
            getGameInstanceConfig(name).save(gameInstanceFile);
        }
        catch (IOException ex) {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            console.sendMessage("§4BŁĄD KRYTYCZNY §cNie można było zapisać konfiguracji instancji areny do §f" + gameInstanceFile);
            console.sendMessage(String.valueOf(ex));
        }
    }
}
