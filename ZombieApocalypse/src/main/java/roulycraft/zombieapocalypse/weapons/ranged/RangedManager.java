package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.zombie.ZombieInstance;

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

    public void reloadRangedInstanceConfig() {
        rangedInstanceConfig = YamlConfiguration.loadConfiguration(rangedInstanceFile);
    }

    public static RangedManager getManager() {
        if (rangedManager == null) {
            rangedManager = new RangedManager();
        }
        return rangedManager;
    }

    public RangedInstance getInstance(Integer id, Integer level) {
        for (RangedInstance rangedInstance : this.rangedInstanceList) {
            if (rangedInstance.getId().equals(id) && rangedInstance.getLevel().equals(level)) {
                return rangedInstance;
            }
        }

        return null;
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
        getRangedInstanceConfig(id).set(level+".name", name);
        getRangedInstanceConfig(id).set(level+".item", item);
        getRangedInstanceConfig(id).set(level+".minDmg", minDmg);
        getRangedInstanceConfig(id).set(level+".maxDmg", maxDmg);
        getRangedInstanceConfig(id).set(level+".projectileType", projectileType);
        getRangedInstanceConfig(id).set(level+".projectileSpeed", projectileSpeed);
        getRangedInstanceConfig(id).set(level+".pellets", pellets);
        getRangedInstanceConfig(id).set(level+".bulletSpread", bulletSpread);
        getRangedInstanceConfig(id).set(level+".spreadPercentage", spreadPercentage);
        getRangedInstanceConfig(id).set(level+".delayBetweenShots", delayBetweenShots);
        getRangedInstanceConfig(id).set(level+".clipSize", clipSize);
        getRangedInstanceConfig(id).set(level+".reloadSpeed", reloadSpeed);
        getRangedInstanceConfig(id).set(level+".reloadType", reloadType);
        getRangedInstanceConfig(id).set(level+".actionType", actionType);
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

    public FileConfiguration getRangedInstanceConfig(Integer id) {
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

            getRangedInstanceConfig(id).save(rangedInstanceFile);

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

                RangedInstance rangedInstance = new RangedInstance(

                        id,
                        rangedInstanceConfig.getString(i+".name"),
                        i,
                        rangedInstanceConfig.getItemStack(i+".item"),
                        rangedInstanceConfig.getInt(i+".minDmg"),
                        rangedInstanceConfig.getInt(i+".maxDmg"),
                        rangedInstanceConfig.getInt(i+".projectileType"),
                        rangedInstanceConfig.getDouble(i+".projectileSpeed"),
                        rangedInstanceConfig.getInt(i+".pellets"),
                        rangedInstanceConfig.getDouble(i+".bulletSpread"),
                        rangedInstanceConfig.getInt(i+".spreadPercentage"),
                        rangedInstanceConfig.getDouble(i+".delayBetweenShots"),
                        rangedInstanceConfig.getInt(i+".clipSize"),
                        rangedInstanceConfig.getDouble(i+"reloadSpeed"),
                        rangedInstanceConfig.getString(i+".reloadType"),
                        rangedInstanceConfig.getString(i+".actionType"),
                        rangedInstanceConfig.getDouble(i+".actionDelay")

                );
                rangedInstance.setLevel(i);
                rangedInstanceList.add(rangedInstance);

            }

        }

        return true;
    }

}
