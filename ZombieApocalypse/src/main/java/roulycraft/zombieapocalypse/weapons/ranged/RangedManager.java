package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.managers.GameInstance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RangedManager {
    private static RangedManager rangedManager;
    private static ZombieApocalypse plugin;
    private FileConfiguration rangedInstanceConfig = null;
    private File rangedInstanceFile = null;
    public final List<RangedInstance> rangedInstanceList = new ArrayList<>();

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }

    private RangedManager() {
    }

    public void reloadZombieInstanceConfig() {
        rangedInstanceConfig = YamlConfiguration.loadConfiguration(rangedInstanceFile);
    }

    public static RangedManager getManager() {
        if (rangedManager == null) {
            rangedManager = new RangedManager();
        }
        return rangedManager;
    }

    public void createDefaultRangedInstance() {

        int nextFreeID = rangedInstanceList.size();

        for (RangedInstance checkIfExists : this.rangedInstanceList) {
            if (checkIfExists.getId().equals(nextFreeID)) {
                if (checkIfExists.getLevel().equals(1))
                    return;
            }
        }
    }

    public void createRangedInstance(Integer id, String name, Integer level, ItemStack item, Integer minDmg, Integer maxDmg, Integer projectileType, Float projectileSpeed, Integer pellets, Float bulletSpread, Integer spreadPercentage, Float delayBetweenShots, Integer clipSize, Float reloadSpeed, String reloadType, String actionType) {

        if (level < 0 || level > 3) {
            return;
        }

        for (RangedInstance checkIfExists : this.rangedInstanceList) {
            if (checkIfExists.getId().equals(id)) {
                if (checkIfExists.getLevel().equals(level))
                    return;
            }
        }

        RangedInstance rangedInstance = new RangedInstance(id, name, level, item, minDmg, maxDmg, projectileType, projectileSpeed, pellets, bulletSpread, spreadPercentage, delayBetweenShots, clipSize, reloadSpeed, reloadType, actionType);

        reloadRangedInstanceConfig(id);
        getGameInstanceConfig(id).set(level+".name", name);
        getGameInstanceConfig(id).set(level+".item", item);
        getGameInstanceConfig(id).set(level+".minDmg", minDmg);
        getGameInstanceConfig(id).set(level+".maxDmg", maxDmg);
        getGameInstanceConfig(id).set(level+".projectileType", projectileType);
        getGameInstanceConfig(id).set(level+".projectileSpeed", projectileSpeed);
        getGameInstanceConfig(id).set(level+".pellets", pellets);
        getGameInstanceConfig(id).set(level+".bulletSpread", bulletSpread);
        getGameInstanceConfig(id).set(level+".spreadPercentage", spreadPercentage);
        getGameInstanceConfig(id).set(level+".delayBetweenShots", delayBetweenShots);
        getGameInstanceConfig(id).set(level+".clipSize", clipSize);
        getGameInstanceConfig(id).set(level+".reloadSpeed", reloadSpeed);
        getGameInstanceConfig(id).set(level+".reloadType", reloadType);
        getGameInstanceConfig(id).set(level+".actionType", actionType);
        saveGameInstanceConfig(id);

        this.rangedInstanceList.add(rangedInstance);
    }

    public RangedInstance getRangedInstance(Integer id, Integer level){
        for (RangedInstance instance : this.rangedInstanceList) {
            if (instance.getId().equals(id) && instance.getLevel().equals(level)) {
                return instance;
            }
        }

        return null;
    }

    public void reloadRangedInstanceConfig(Integer id) {
        if (rangedInstanceFile == null) {
            rangedInstanceFile = new File(plugin.getDataFolder() + File.separator + "instances" + File.separator + "weapons" + File.separator + "ranged", (id + ".yml"));
        }

        rangedInstanceConfig = YamlConfiguration.loadConfiguration(rangedInstanceFile);
        rangedInstanceFile = new File(plugin.getDataFolder() + File.separator + "instances" + File.separator + "weapons" + File.separator + "ranged", (id + ".yml"));
    }

    public FileConfiguration getGameInstanceConfig(Integer id) {
        if (rangedInstanceConfig == null) {
            reloadRangedInstanceConfig(id);
        }
        return rangedInstanceConfig;
    }

    public void saveGameInstanceConfig(Integer id) {
        if (rangedInstanceConfig == null || rangedInstanceFile == null) {
            return;
        }
        try {
            getGameInstanceConfig(id).save(rangedInstanceFile);
        }
        catch (IOException ex) {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            console.sendMessage("§4BŁĄD KRYTYCZNY §cNie można było zapisać konfiguracji instancji broni do §f" + rangedInstanceFile);
            console.sendMessage(String.valueOf(ex));
        }
    }

    public boolean loadRangedInstanceConfig(Integer id) {
        reloadRangedInstanceConfig(id);

        if (!rangedInstanceFile.exists()) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (!rangedInstanceList.contains(this.getRangedInstance(id, i))) {

                RangedInstance rangedInstance = new RangedInstance(id);
                rangedInstance.setLevel(i);
                rangedInstanceList.add(rangedInstance); // może nie działać :shrug:
            }

            if (rangedInstanceConfig.getString(i+".name") != null) {
                getRangedInstance(id, i).setName(rangedInstanceConfig.getString("name"));
            }

            if (rangedInstanceConfig.getItemStack(i+".item") != null) {
                getRangedInstance(id, i).setItem(rangedInstanceConfig.getItemStack("item"));
            }

            if (rangedInstanceConfig.getItemStack(i+".minDmg") != null) {
                getRangedInstance(id, i).setMinDmg(rangedInstanceConfig.getInt("minDmg"));
            }

            if (rangedInstanceConfig.getItemStack(i+".maxDmg") != null) {
                getRangedInstance(id, i).setMaxDmg(rangedInstanceConfig.getInt("maxDmg"));
            }

            if (rangedInstanceConfig.getItemStack(i+".projectileType") != null) {
                getRangedInstance(id, i).setProjectileType(rangedInstanceConfig.getInt("projectileType"));
            }

            if (rangedInstanceConfig.getItemStack(i+".projectileSpeed") != null) {
                getRangedInstance(id, i).setProjectileSpeed(Float.valueOf(Objects.requireNonNull(rangedInstanceConfig.getString("projectileSpeed"))));
            }

            if (rangedInstanceConfig.getItemStack(i+".pellets") != null) {
                getRangedInstance(id, i).setPellets(rangedInstanceConfig.getInt("pellets"));
            }

            if (rangedInstanceConfig.getItemStack(i+".bulletSpread") != null) {
                getRangedInstance(id, i).setBulletSpread(Float.valueOf(Objects.requireNonNull(rangedInstanceConfig.getString("bulletSpread"))));
            }

            if (rangedInstanceConfig.getItemStack(i+".spreadPercentage") != null) {
                getRangedInstance(id, i).setSpreadPercentage(rangedInstanceConfig.getInt("spreadPercentage"));
            }

            if (rangedInstanceConfig.getItemStack(i+".delayBetweenShots") != null) {
                getRangedInstance(id, i).setDelayBetweenShots(Float.valueOf(Objects.requireNonNull(rangedInstanceConfig.getString("delayBetweenShots"))));
            }

            if (rangedInstanceConfig.getItemStack(i+".clipSize") != null) {
                getRangedInstance(id, i).setClipSize(rangedInstanceConfig.getInt("clipSize"));
            }

            if (rangedInstanceConfig.getItemStack(i+".reloadSpeed") != null) {
                getRangedInstance(id, i).setReloadSpeed(Float.valueOf(Objects.requireNonNull(rangedInstanceConfig.getString("reloadSpeed"))));
            }

            if (rangedInstanceConfig.getItemStack(i+".reloadType") != null) {
                getRangedInstance(id, i).setReloadType(rangedInstanceConfig.getString("reloadType"));
            }

            if (rangedInstanceConfig.getItemStack(i+".actionType") != null) {
                getRangedInstance(id, i).setActionType(rangedInstanceConfig.getString("actionType"));
            }
        }

        return true;
    }
}
