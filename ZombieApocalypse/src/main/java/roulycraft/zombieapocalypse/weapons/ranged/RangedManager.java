package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import roulycraft.zombieapocalypse.ZombieApocalypse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RangedManager {
    private static RangedManager rangedManager;
    private static ZombieApocalypse plugin;
    private FileConfiguration rangedInstanceConfig = null;
    private File rangedInstanceFile = null;
    public final List<RangedInstance> rangedInstanceList = new ArrayList<>();
    public final List<ItemStack> rangedInstanceItemList = new ArrayList<>();

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
    public void createRangedInstance(Integer id, String name, Integer level, ItemStack item, Integer minDmg, Integer maxDmg, Integer projectileType, Double projectileSpeed, Integer pellets, Double bulletSpread, Double bulletAdditiveSpread, Integer spreadPercentage, Double delayBetweenShots, Integer clipSize, Double reloadSpeed, String reloadType, String actionType, Double actionDelay) {

        if (level < 0 || level > 3) {
            return;
        }

        for (RangedInstance checkIfExists : this.rangedInstanceList) {
            if (checkIfExists.getId().equals(id)) {
                if (checkIfExists.getLevel().equals(level))
                    return;
            }
        }

        RangedInstance rangedInstance = new RangedInstance(id, name, level, item, minDmg, maxDmg, projectileType, projectileSpeed, pellets, bulletSpread, bulletAdditiveSpread, spreadPercentage, delayBetweenShots, clipSize, reloadSpeed, reloadType, actionType, actionDelay);

        reloadRangedInstanceConfig(id);
        getRangedInstanceConfig(id).set(level+".name", name);
        getRangedInstanceConfig(id).set(level+".item", item);
        getRangedInstanceConfig(id).set(level+".minDmg", minDmg);
        getRangedInstanceConfig(id).set(level+".maxDmg", maxDmg);
        getRangedInstanceConfig(id).set(level+".projectileType", projectileType);
        getRangedInstanceConfig(id).set(level+".projectileSpeed", projectileSpeed);
        getRangedInstanceConfig(id).set(level+".pellets", pellets);
        getRangedInstanceConfig(id).set(level+".bulletSpread", bulletSpread);
        getRangedInstanceConfig(id).set(level+".bulletAdditiveSpread", bulletAdditiveSpread);
        getRangedInstanceConfig(id).set(level+".spreadPercentage", spreadPercentage);
        getRangedInstanceConfig(id).set(level+".delayBetweenShots", delayBetweenShots);
        getRangedInstanceConfig(id).set(level+".clipSize", clipSize);
        getRangedInstanceConfig(id).set(level+".reloadSpeed", reloadSpeed);
        getRangedInstanceConfig(id).set(level+".reloadType", reloadType);
        getRangedInstanceConfig(id).set(level+".actionType", actionType);
        getRangedInstanceConfig(id).set(level+".actionDelay", actionType);
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
                        rangedInstanceConfig.getDouble(i+".bulletAdditiveSpread"),
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

    public void parseRangedInstancesAsItems() {

        rangedInstanceItemList.clear();

        ItemStack item;

        for (RangedInstance rangedInstance : this.rangedInstanceList) {

            item = new ItemStack(rangedInstance.getItem());

            item.getItemMeta().setDisplayName(rangedInstance.getName());

            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "zaGun"), PersistentDataType.INTEGER, 1);

            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "minDmg"), PersistentDataType.INTEGER, rangedInstance.getMinDmg());
            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "maxDmg"), PersistentDataType.INTEGER, rangedInstance.getMaxDmg());

            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "projectileType"), PersistentDataType.INTEGER, rangedInstance.getProjectileType());
            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "projectileSpeed"), PersistentDataType.DOUBLE, rangedInstance.getProjectileSpeed());

            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "pellets"), PersistentDataType.INTEGER, rangedInstance.getPellets());

            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "bulletSpread"), PersistentDataType.DOUBLE, rangedInstance.getBulletSpread());
            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "bulletAdditiveSpread"), PersistentDataType.DOUBLE, rangedInstance.getBulletAdditiveSpread());
            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "spreadPercentage"), PersistentDataType.INTEGER, rangedInstance.getSpreadPercentage());

            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "delayBetweenShots"), PersistentDataType.DOUBLE, rangedInstance.getDelayBetweenShots());

            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "clipSize"), PersistentDataType.INTEGER, rangedInstance.getClipSize());
            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "reloadSpeed"), PersistentDataType.DOUBLE, rangedInstance.getReloadSpeed());
            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "reloadType"), PersistentDataType.STRING, rangedInstance.getReloadType());

            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "actionType"), PersistentDataType.STRING, rangedInstance.getActionType());
            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "actionDelay"), PersistentDataType.DOUBLE, rangedInstance.getActionDelay());

            rangedInstanceItemList.add(item);

        }

    }

}
