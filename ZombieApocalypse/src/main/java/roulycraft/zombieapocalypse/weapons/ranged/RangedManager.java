package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.utility.StringSerialisation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RangedManager {
    private static RangedManager rangedManager;
    private static ZombieApocalypse plugin;
    private static String[] primaryColours = {"", "", "", ""};
    private static String[] secondaryColours = {"", "", "", ""};
    private FileConfiguration rangedInstanceConfig = null;
    private File rangedInstanceFile = null;
    public final List<RangedInstance> rangedInstanceList = new ArrayList<>();
    public final List<ItemStack> rangedInstanceItemList = new ArrayList<>();

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;

        primaryColours[0] = plugin.getConfig().getString("messages.plugin.guns.level0.primary");
        primaryColours[1] = plugin.getConfig().getString("messages.plugin.guns.level1.primary");
        primaryColours[2] = plugin.getConfig().getString("messages.plugin.guns.level2.primary");
        primaryColours[3] = plugin.getConfig().getString("messages.plugin.guns.level3.primary");
        secondaryColours[0] = plugin.getConfig().getString("messages.plugin.guns.level0.secondary");
        secondaryColours[1] = plugin.getConfig().getString("messages.plugin.guns.level1.secondary");
        secondaryColours[2] = plugin.getConfig().getString("messages.plugin.guns.level2.secondary");
        secondaryColours[3] = plugin.getConfig().getString("messages.plugin.guns.level3.secondary");

    }

    private RangedManager() {
    }

    public static RangedManager getManager() {

        if (rangedManager == null) {

            rangedManager = new RangedManager();

        }

        return rangedManager;

    }

    public ItemStack getGun(Integer id, Integer level) {

        int left = 0;
        int right = rangedInstanceItemList.size() - 1;
        List<ItemStack> foundList = new ArrayList<>();

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int foundId = rangedInstanceItemList.get(mid).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "id"), PersistentDataType.INTEGER);

            if (foundId == id) {

                int foundLevel = rangedInstanceItemList.get(mid).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER);
                int offset = (mid - foundLevel);

                for(int i = 0; i < 4; i++) {
                    foundList.add(rangedInstanceItemList.get(offset));
                    offset++;
                }
                break;
            }

            if(foundId < id) {
                left = mid + 1;
            }

            else {
                right = mid - 1;
            }
        }

        left = 0;
        right = foundList.size() - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            int foundLevel = foundList.get(mid).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER);

            if(foundLevel == level) {
                return foundList.get(mid);
            }

            if(foundLevel < level) {
                left = mid + 1;
            }

            else {
                right = mid - 1;
            }
        }
        return null;
    }

    public RangedInstance getInstance(Integer id, Integer level) {

        int left = 0;
        int right = rangedInstanceList.size() - 1;
        List<RangedInstance> foundList = new ArrayList<>();

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int foundId = rangedInstanceList.get(mid).getId();

            if (foundId == id) {

                int foundLevel = rangedInstanceList.get(mid).getLevel();
                int offset = (mid - foundLevel);

                for(int i = 0; i < 4; i++) {
                    foundList.add(rangedInstanceList.get(offset));
                    offset++;
                }
                break;
            }

            if(foundId < id) {
                left = mid + 1;
            }

            else {
                right = mid - 1;
            }
        }

        left = 0;
        right = foundList.size() - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            int foundLevel = foundList.get(mid).getLevel();

            if(foundLevel == level) {
                return foundList.get(mid);
            }

            if(foundLevel < level) {
                left = mid + 1;
            }

            else {
                right = mid - 1;
            }
        }
        return null;
    }
    public void createRangedInstance(
        Integer id,
        String name,
        Integer level,
        ItemStack item,
        Integer minDmg,
        Integer maxDmg,
        Integer projectileType,
        Double projectileSpeed,
        Integer pellets,
        Integer burstAmount,
        Double burstDelay,
        Double bulletSpread,
        Double bulletAdditiveSpread,
        Double spreadPercentage,
        Integer zoomAmount,
        Double zoomSpreadMultiplier,
        Double delayBetweenShots,
        Integer clipSize,
        Double reloadSpeed,
        String reloadType,
        String actionType,
        Double actionDelay,
        Integer actionSpecial,
        Integer shootingPatternType,
        Double shootingPatternOffset,
        String shootingSound,
        String reloadingSound,
        String actionSound
    ) {

        if (level < 0 || level >= 4) {
            return;
        }

        for (RangedInstance checkIfExists : this.rangedInstanceList) {
            if (checkIfExists.getId().equals(id)) {
                if (checkIfExists.getLevel().equals(level))
                    return;
            }
        }

        RangedInstance rangedInstance = new RangedInstance(
                id,
                name,
                level,
                item,
                minDmg,
                maxDmg,
                projectileType,
                projectileSpeed,
                pellets,
                burstAmount,
                burstDelay,
                bulletSpread,
                bulletAdditiveSpread,
                spreadPercentage,
                zoomAmount,
                zoomSpreadMultiplier,
                delayBetweenShots,
                clipSize,
                reloadSpeed,
                reloadType,
                actionType,
                actionDelay,
                actionSpecial,
                shootingPatternType,
                shootingPatternOffset,
                shootingSound,
                reloadingSound,
                actionSound
                );

        reloadRangedInstanceConfig(id);
        getRangedInstanceConfig(id).set((level+".name"), name);
        getRangedInstanceConfig(id).set((level+".item"), item);
        getRangedInstanceConfig(id).set((level+".minDmg"), minDmg);
        getRangedInstanceConfig(id).set((level+".maxDmg"), maxDmg);
        getRangedInstanceConfig(id).set((level+".projectileType"), projectileType);
        getRangedInstanceConfig(id).set((level+".projectileSpeed"), projectileSpeed);
        getRangedInstanceConfig(id).set((level+".pellets"), pellets);
        getRangedInstanceConfig(id).set((level+".burstAmount"), burstAmount);
        getRangedInstanceConfig(id).set((level+".burstDelay"), burstDelay);
        getRangedInstanceConfig(id).set((level+".bulletSpread"), bulletSpread);
        getRangedInstanceConfig(id).set((level+".bulletAdditiveSpread"), bulletAdditiveSpread);
        getRangedInstanceConfig(id).set((level+".spreadPercentage"), spreadPercentage);
        getRangedInstanceConfig(id).set((level+".zoomAmount"), zoomAmount);
        getRangedInstanceConfig(id).set((level+".zoomSpreadMultiplier"), zoomSpreadMultiplier);
        getRangedInstanceConfig(id).set((level+".delayBetweenShots"), delayBetweenShots);
        getRangedInstanceConfig(id).set((level+".clipSize"), clipSize);
        getRangedInstanceConfig(id).set((level+".reloadSpeed"), reloadSpeed);
        getRangedInstanceConfig(id).set((level+".reloadType"), reloadType);
        getRangedInstanceConfig(id).set((level+".actionType"), actionType);
        getRangedInstanceConfig(id).set((level+".actionDelay"), actionDelay);
        getRangedInstanceConfig(id).set((level+".actionSpecial"), actionSpecial);
        getRangedInstanceConfig(id).set((level+".shootingPatternType"), shootingPatternType);
        getRangedInstanceConfig(id).set((level+".shootingPatternOffset"), shootingPatternOffset);
        getRangedInstanceConfig(id).set((level+".shootingSound"), shootingSound);
        getRangedInstanceConfig(id).set((level+".reloadingSound"), reloadingSound);
        getRangedInstanceConfig(id).set((level+".actionSound"), actionSound);
        saveRangedInstanceConfig(id);

        this.rangedInstanceList.add(rangedInstance);
        rangedInstanceList.sort(Comparator.comparing(RangedInstance::getId).thenComparing(RangedInstance::getLevel));
    }

    public RangedInstance getRangedInstance(Integer id, Integer level) {

        for (RangedInstance instance : this.rangedInstanceList) {
            if (instance.getId().equals(id) && instance.getLevel().equals(level)) {
                return instance;
            }
        }

        return null;
    }

    public void reloadRangedInstanceConfig(Integer id) {

        rangedInstanceFile = new File(plugin.getDataFolder() + File.separator + "instances" + File.separator + "weapons" + File.separator + "ranged", (id + ".yml"));
        rangedInstanceConfig = YamlConfiguration.loadConfiguration(rangedInstanceFile);

    }

    public FileConfiguration getRangedInstanceConfig(Integer id) {

        if (rangedInstanceConfig == null) {

            reloadRangedInstanceConfig(id);

        }

        return rangedInstanceConfig;

    }

    public void saveRangedInstanceConfig(Integer id) {

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

            if (!rangedInstanceList.contains(getRangedInstance(id, i)) &&
                !rangedInstanceConfig.getString(i+".name").isEmpty()) {


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
                        rangedInstanceConfig.getInt(i+".burstAmount"),
                        rangedInstanceConfig.getDouble(i+".burstDelay"),
                        rangedInstanceConfig.getDouble(i+".bulletSpread"),
                        rangedInstanceConfig.getDouble(i+".bulletAdditiveSpread"),
                        rangedInstanceConfig.getDouble(i+".spreadPercentage"),
                        rangedInstanceConfig.getInt(i+".zoomAmount"),
                        rangedInstanceConfig.getDouble(i+".zoomSpreadMultiplier"),
                        rangedInstanceConfig.getDouble(i+".delayBetweenShots"),
                        rangedInstanceConfig.getInt(i+".clipSize"),
                        rangedInstanceConfig.getDouble(i+".reloadSpeed"),
                        rangedInstanceConfig.getString(i+".reloadType"),
                        rangedInstanceConfig.getString(i+".actionType"),
                        rangedInstanceConfig.getDouble(i+".actionDelay"),
                        rangedInstanceConfig.getInt(i+".actionSpecial"),
                        rangedInstanceConfig.getInt(i+".shootingPatternType"),
                        rangedInstanceConfig.getDouble(i+".shootingPatternOffset"),
                        rangedInstanceConfig.getString(i+".shootingSound"),
                        rangedInstanceConfig.getString(i+".reloadingSound"),
                        rangedInstanceConfig.getString(i+".actionSound")

                );

                rangedInstanceList.add(rangedInstance);
                rangedInstanceList.sort(Comparator.comparing(RangedInstance::getId).thenComparing(RangedInstance::getLevel));

                continue;

            }

            getInstance(id, i).setName(rangedInstanceConfig.getString(i+".name"));
            getInstance(id, i).setLevel(i); // Prolly not needed
            getInstance(id, i).setItem(rangedInstanceConfig.getItemStack(i+".item"));
            getInstance(id, i).setMinDmg(rangedInstanceConfig.getInt(i+".minDmg"));
            getInstance(id, i).setMaxDmg(rangedInstanceConfig.getInt(i+".maxDmg"));
            getInstance(id, i).setProjectileType(rangedInstanceConfig.getInt(i+".projectileType"));
            getInstance(id, i).setProjectileSpeed(rangedInstanceConfig.getDouble(i+".projectileSpeed"));
            getInstance(id, i).setPellets(rangedInstanceConfig.getInt(i+".pellets"));
            getInstance(id, i).setBurstAmount(rangedInstanceConfig.getInt(i+".burstAmount"));
            getInstance(id, i).setBurstDelay(rangedInstanceConfig.getDouble(i+".burstDelay"));
            getInstance(id, i).setBulletSpread(rangedInstanceConfig.getDouble(i+".bulletSpread"));
            getInstance(id, i).setAdditiveBulletSpread(rangedInstanceConfig.getDouble(i+".bulletAdditiveSpread"));
            getInstance(id, i).setSpreadPercentage(rangedInstanceConfig.getDouble(i+".spreadPercentage"));
            getInstance(id, i).setZoomAmount(rangedInstanceConfig.getInt(i+".zoomAmount"));
            getInstance(id, i).setZoomSpreadMultiplier(rangedInstanceConfig.getDouble(i+".zoomSpreadMultiplier"));
            getInstance(id, i).setDelayBetweenShots(rangedInstanceConfig.getDouble(i+".delayBetweenShots"));
            getInstance(id, i).setClipSize(rangedInstanceConfig.getInt(i+".clipSize"));
            getInstance(id, i).setReloadSpeed(rangedInstanceConfig.getDouble(i+".reloadSpeed"));
            getInstance(id, i).setReloadType(rangedInstanceConfig.getString(i+".reloadType"));
            getInstance(id, i).setActionType(rangedInstanceConfig.getString(i+".actionType"));
            getInstance(id, i).setActionDelay(rangedInstanceConfig.getDouble(i+".actionDelay"));
            getInstance(id, i).setActionSpecial(rangedInstanceConfig.getInt(i+".actionSpecial"));
            getInstance(id, i).setShootingPatternType(rangedInstanceConfig.getInt(i+".shootingPatternType"));
            getInstance(id, i).setShootingPatternOffset(rangedInstanceConfig.getDouble(i+".shootingPatternOffset"));
            getInstance(id, i).setShootingSound(rangedInstanceConfig.getString(i+".shootingSound"));
            getInstance(id, i).setReloadingSound(rangedInstanceConfig.getString(i+".reloadingSound"));
            getInstance(id, i).setActionSound(rangedInstanceConfig.getString(i+".actionSound"));

        }

        return true;
    }

    public void parseRangedInstancesAsItems() {

        rangedInstanceItemList.clear();

        ItemStack item;

        for (RangedInstance rangedInstance : this.rangedInstanceList) {

            item = new ItemStack(rangedInstance.getItem());
            ItemMeta im = item.getItemMeta();

            String itemName = rangedInstance.getName()+" "+secondaryColours[rangedInstance.getLevel()]+"| "+primaryColours[rangedInstance.getLevel()]+rangedInstance.getClipSize();
            itemName = StringSerialisation.deserialise(itemName);

            im.setDisplayName(itemName);

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "zaGun"), PersistentDataType.INTEGER, 1);

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "id"), PersistentDataType.INTEGER, rangedInstance.getId());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER, rangedInstance.getLevel());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "minDmg"), PersistentDataType.INTEGER, rangedInstance.getMinDmg());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "maxDmg"), PersistentDataType.INTEGER, rangedInstance.getMaxDmg());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "projectileType"), PersistentDataType.INTEGER, rangedInstance.getProjectileType());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "projectileSpeed"), PersistentDataType.DOUBLE, rangedInstance.getProjectileSpeed());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "pellets"), PersistentDataType.INTEGER, rangedInstance.getPellets());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "burstAmount"), PersistentDataType.INTEGER, rangedInstance.getBurstAmount());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "burstDelay"), PersistentDataType.DOUBLE, rangedInstance.getBurstDelay());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "bulletSpread"), PersistentDataType.DOUBLE, rangedInstance.getBulletSpread());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "additiveBulletSpread"), PersistentDataType.DOUBLE, rangedInstance.getAdditiveBulletSpread());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "spreadPercentage"), PersistentDataType.DOUBLE, rangedInstance.getSpreadPercentage());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "zoomAmount"), PersistentDataType.INTEGER, rangedInstance.getZoomAmount());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "zoomSpreadMultiplier"), PersistentDataType.DOUBLE, rangedInstance.getZoomSpreadMultiplier());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "delayBetweenShots"), PersistentDataType.DOUBLE, rangedInstance.getDelayBetweenShots());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "currentAmmo"), PersistentDataType.INTEGER, rangedInstance.getClipSize());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "clipSize"), PersistentDataType.INTEGER, rangedInstance.getClipSize());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "reloadSpeed"), PersistentDataType.DOUBLE, rangedInstance.getReloadSpeed());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "reloadType"), PersistentDataType.STRING, rangedInstance.getReloadType());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "actionType"), PersistentDataType.STRING, rangedInstance.getActionType());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "actionDelay"), PersistentDataType.DOUBLE, rangedInstance.getActionDelay());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "actionSpecial"), PersistentDataType.INTEGER, rangedInstance.getActionSpecial());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "actionSpecialTracker"), PersistentDataType.INTEGER, 0);

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "shootingPatternType"), PersistentDataType.INTEGER, rangedInstance.getShootingPatternType());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "shootingPatternOffset"), PersistentDataType.DOUBLE, rangedInstance.getShootingPatternOffset());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "shootingSound"), PersistentDataType.STRING, rangedInstance.getShootingSound());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "reloadingSound"), PersistentDataType.STRING, rangedInstance.getReloadingSound());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "actionSound"), PersistentDataType.STRING, rangedInstance.getActionSound());

            im.setUnbreakable(true);

            im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);

            item.setItemMeta(im);

            /*

            Lore setter here.

            */

            rangedInstanceItemList.add(item);

        }

    }

}
