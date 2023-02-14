package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import roulycraft.zombieapocalypse.ZombieApocalypse;

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
    public final List<ItemStack> rangedInstanceItemList = new ArrayList<>();

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }

    private RangedManager() {
    }

    public void createDefaultRangedeInstances() {

        String rangedLvl0 = "§f";
        String rangedLvl1 = "§7Wzmocniony ";
        String rangedLvl2 = "§bNiepowstrzymany ";
        String rangedLvl3 = "§dOstateczny ";

        createRangedInstance(

                1,
                (rangedLvl0 + "PTL-3"),
                0,
                new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                3,
                5,
                0,
                2.0,
                1,
                0.2,
                2.3,
                25.0,
                0.2,
                8,
                2.2,
                "clip",
                "slide",
                0.1,
                0,
                0.0,
                "ENTITY_BLAZE_HURT|1|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0",
                ""

        );

        createRangedInstance(

                1,
                (rangedLvl1 + "PTL-3"),
                1,
                new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                4,
                6,
                0,
                2.0,
                1,
                0.2,
                2.3,
                25.0,
                0.2,
                8,
                2.2,
                "clip",
                "slide",
                0.0,
                0,
                0.0,
                "ENTITY_BLAZE_HURT|1|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0",
                ""

        );

        createRangedInstance(

                1,
                (rangedLvl2 + "PTL-3"),
                2,
                new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                4,
                6,
                0,
                2.0,
                1,
                0.2,
                2.3,
                20.0,
                0.2,
                12,
                2.2,
                "clip",
                "slide",
                0.0,
                0,
                0.0,
                "ENTITY_BLAZE_HURT|1|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0",
                ""

        );

        createRangedInstance(

                1,
                (rangedLvl3 + "PTL-3"),
                3,
                new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                5,
                7,
                0,
                2.5,
                1,
                0.2,
                1.7,
                15.0,
                0.2,
                12,
                2.2,
                "clip",
                "slide",
                0.0,
                0,
                0.0,
                "ENTITY_BLAZE_HURT|1|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0",
                ""

        );

    }

    public static RangedManager getManager() {

        if (rangedManager == null) {

            rangedManager = new RangedManager();

        }

        return rangedManager;

    }

    public ItemStack getGun(Integer id, Integer level) {

        for (ItemStack item : this.rangedInstanceItemList) {

            System.out.println(item);
            System.out.println(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "id"), PersistentDataType.INTEGER));
            System.out.println(item.getItemMeta().getPersistentDataContainer().isEmpty());
            System.out.println(id);
            System.out.println(level);

            if (Objects.equals(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "id"), PersistentDataType.INTEGER), id) && Objects.equals(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER), level)) {

                return item;

            }

        }

        return null;

    }

    public RangedInstance getInstance(Integer id, Integer level) {

        for (RangedInstance rangedInstance : this.rangedInstanceList) {

            if (rangedInstance.getId().equals(id) && rangedInstance.getLevel().equals(level)) {

                return rangedInstance;

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
            Double bulletSpread,
            Double bulletAdditiveSpread,
            Double spreadPercentage,
            Double delayBetweenShots,
            Integer clipSize,
            Double reloadSpeed,
            String reloadType,
            String actionType,
            Double actionDelay,
            Integer shootingPatternType,
            Double shootingPatternOffset,
            String shootingSound,
            String reloadingSound) {

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
                bulletSpread,
                bulletAdditiveSpread,
                spreadPercentage,
                delayBetweenShots,
                clipSize,
                reloadSpeed,
                reloadType,
                actionType,
                actionDelay,
                shootingPatternType,
                shootingPatternOffset,
                shootingSound,
                reloadingSound
                );

        reloadRangedInstanceConfig(id);
        getRangedInstanceConfig(id).set((level+".name"), name);
        getRangedInstanceConfig(id).set((level+".item"), item);
        getRangedInstanceConfig(id).set((level+".minDmg"), minDmg);
        getRangedInstanceConfig(id).set((level+".maxDmg"), maxDmg);
        getRangedInstanceConfig(id).set((level+".projectileType"), projectileType);
        getRangedInstanceConfig(id).set((level+".projectileSpeed"), projectileSpeed);
        getRangedInstanceConfig(id).set((level+".pellets"), pellets);
        getRangedInstanceConfig(id).set((level+".bulletSpread"), bulletSpread);
        getRangedInstanceConfig(id).set((level+".bulletAdditiveSpread"), bulletAdditiveSpread);
        getRangedInstanceConfig(id).set((level+".spreadPercentage"), spreadPercentage);
        getRangedInstanceConfig(id).set((level+".delayBetweenShots"), delayBetweenShots);
        getRangedInstanceConfig(id).set((level+".clipSize"), clipSize);
        getRangedInstanceConfig(id).set((level+".reloadSpeed"), reloadSpeed);
        getRangedInstanceConfig(id).set((level+".reloadType"), reloadType);
        getRangedInstanceConfig(id).set((level+".actionType"), actionType);
        getRangedInstanceConfig(id).set((level+".actionDelay"), actionDelay);
        getRangedInstanceConfig(id).set((level+".shootingPatternType"), shootingPatternType);
        getRangedInstanceConfig(id).set((level+".shootingPatternOffset"), shootingPatternOffset);
        getRangedInstanceConfig(id).set((level+".shootingSound"), shootingSound);
        getRangedInstanceConfig(id).set((level+".reloadingSound"), reloadingSound);
        saveRangedInstanceConfig(id);

        this.rangedInstanceList.add(rangedInstance);
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

        if (rangedInstanceFile == null) {
            rangedInstanceFile = new File(plugin.getDataFolder() + File.separator + "instances" + File.separator + "weapons" + File.separator + "ranged", (id + ".yml"));
        }

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

            if (!rangedInstanceList.contains(this.getRangedInstance(id, i)) &&
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
                        rangedInstanceConfig.getDouble(i+".bulletSpread"),
                        rangedInstanceConfig.getDouble(i+".bulletAdditiveSpread"),
                        rangedInstanceConfig.getDouble(i+".spreadPercentage"),
                        rangedInstanceConfig.getDouble(i+".delayBetweenShots"),
                        rangedInstanceConfig.getInt(i+".clipSize"),
                        rangedInstanceConfig.getDouble(i+"reloadSpeed"),
                        rangedInstanceConfig.getString(i+".reloadType"),
                        rangedInstanceConfig.getString(i+".actionType"),
                        rangedInstanceConfig.getDouble(i+".actionDelay"),
                        rangedInstanceConfig.getInt(i+".shootingPatternType"),
                        rangedInstanceConfig.getDouble(i+".shootingPatternOffset"),
                        rangedInstanceConfig.getString(i+".shootingSound"),
                        rangedInstanceConfig.getString(i+".reloadingSound")

                );

                rangedInstanceList.add(rangedInstance);

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
            getInstance(id, i).setBulletSpread(rangedInstanceConfig.getDouble(i+".bulletSpread"));
            getInstance(id, i).setAdditiveBulletSpread(rangedInstanceConfig.getDouble(i+".bulletAdditiveSpread"));
            getInstance(id, i).setSpreadPercentage(rangedInstanceConfig.getDouble(i+".spreadPercentage"));
            getInstance(id, i).setDelayBetweenShots(rangedInstanceConfig.getDouble(i+".delayBetweenShots"));
            getInstance(id, i).setClipSize(rangedInstanceConfig.getInt(i+".clipSize"));
            getInstance(id, i).setReloadSpeed(rangedInstanceConfig.getDouble(i+".reloadSpeed"));
            getInstance(id, i).setReloadType(rangedInstanceConfig.getString(i+".reloadType"));
            getInstance(id, i).setActionType(rangedInstanceConfig.getString(i+".actionType"));
            getInstance(id, i).setActionDelay(rangedInstanceConfig.getDouble(i+".actionDelay"));
            getInstance(id, i).setShootingPatternType(rangedInstanceConfig.getInt(i+".shootingPatternType"));
            getInstance(id, i).setShootingPatternOffset(rangedInstanceConfig.getDouble(i+".shootingPatternOffset"));
            getInstance(id, i).setShootingSound(rangedInstanceConfig.getString(i+".shootingSound"));
            getInstance(id, i).setReloadingSound(rangedInstanceConfig.getString(i+".reloadingSound"));

        }

        return true;
    }

    public void parseRangedInstancesAsItems() {

        rangedInstanceItemList.clear();

        ItemStack item;

        for (RangedInstance rangedInstance : this.rangedInstanceList) {

            item = new ItemStack(rangedInstance.getItem());
            ItemMeta im = item.getItemMeta();

            im.setDisplayName(rangedInstance.getName());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "zaGun"), PersistentDataType.INTEGER, 1);

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "id"), PersistentDataType.INTEGER, rangedInstance.getId());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER, rangedInstance.getLevel());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "minDmg"), PersistentDataType.INTEGER, rangedInstance.getMinDmg());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "maxDmg"), PersistentDataType.INTEGER, rangedInstance.getMaxDmg());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "projectileType"), PersistentDataType.INTEGER, rangedInstance.getProjectileType());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "projectileSpeed"), PersistentDataType.DOUBLE, rangedInstance.getProjectileSpeed());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "pellets"), PersistentDataType.INTEGER, rangedInstance.getPellets());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "bulletSpread"), PersistentDataType.DOUBLE, rangedInstance.getBulletSpread());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "additiveBulletSpread"), PersistentDataType.DOUBLE, rangedInstance.getAdditiveBulletSpread());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "spreadPercentage"), PersistentDataType.DOUBLE, rangedInstance.getSpreadPercentage());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "delayBetweenShots"), PersistentDataType.DOUBLE, rangedInstance.getDelayBetweenShots());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "clipSize"), PersistentDataType.INTEGER, rangedInstance.getClipSize());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "reloadSpeed"), PersistentDataType.DOUBLE, rangedInstance.getReloadSpeed());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "reloadType"), PersistentDataType.STRING, rangedInstance.getReloadType());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "actionType"), PersistentDataType.STRING, rangedInstance.getActionType());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "actionDelay"), PersistentDataType.DOUBLE, rangedInstance.getActionDelay());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "shootingPatternType"), PersistentDataType.INTEGER, rangedInstance.getShootingPatternType());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "shootingPatternOffset"), PersistentDataType.DOUBLE, rangedInstance.getShootingPatternOffset());

            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "shootingSound"), PersistentDataType.STRING, rangedInstance.getShootingSound());
            im.getPersistentDataContainer().set(new NamespacedKey(plugin, "reloadingSound"), PersistentDataType.STRING, rangedInstance.getReloadingSound());

            im.setUnbreakable(true);

            item.setItemMeta(im);

            /*

            Lore setter here.

            */

            rangedInstanceItemList.add(item);

        }

    }

}
