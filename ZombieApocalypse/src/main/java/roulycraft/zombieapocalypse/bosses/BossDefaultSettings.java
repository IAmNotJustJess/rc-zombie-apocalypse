package roulycraft.zombieapocalypse.bosses;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import static org.bukkit.Material.*;

public class BossDefaultSettings {
    public static BossDefaultSettings bossDefaultSettings;
    private BossDefaultSettings() {
    }

    public static BossDefaultSettings getManager() {

        if (bossDefaultSettings == null) {

            bossDefaultSettings = new BossDefaultSettings();

        }

        return bossDefaultSettings;

    }
    public void loadDefaultSettings() {

        ItemStack helmet = new ItemStack(LEATHER_HELMET);
        ItemStack chestplate = new ItemStack(LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(LEATHER_BOOTS);

        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();

        BossManager.getManager().createBossInstance("1", ("<#999999><bold>Zombie Rycerz"), 500, 25, 3.2f, new ItemStack(IRON_HELMET, 1), new ItemStack(IRON_CHESTPLATE, 1), new ItemStack(IRON_LEGGINGS, 1), new ItemStack(IRON_BOOTS, 1), new ItemStack(IRON_SWORD, 1), new ItemStack(SHIELD, 1), 500);

        chestplateMeta.setColor(Color.fromRGB(89, 68, 64));
        leggingsMeta.setColor(Color.fromRGB(84, 65, 42));
        bootsMeta.setColor(Color.fromRGB(73, 52, 23));
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        BossManager.getManager().createBossInstance("2", ("<#cccccc><bold>Zombie Górnik"), 750, 25, 3.2f, new ItemStack(GOLDEN_HELMET, 1), chestplate, leggings, boots, new ItemStack(IRON_PICKAXE, 1), new ItemStack(AIR, 1), 750);

        helmetMeta.setColor(Color.fromRGB(41, 99, 52));
        chestplateMeta.setColor(Color.fromRGB(35, 91, 46));
        leggingsMeta.setColor(Color.fromRGB(29, 81, 38));
        bootsMeta.setColor(Color.fromRGB(48, 28, 10));
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        BossManager.getManager().createBossInstance("3", ("<#126020><bold>Zombie Żołnierz"), 1000, 30, 3.2f, helmet, chestplate, leggings, boots, new ItemStack(IRON_HORSE_ARMOR, 1), new ItemStack(AIR, 1), 1000);

        chestplateMeta.setColor(Color.fromRGB(82, 35, 130));
        leggingsMeta.setColor(Color.fromRGB(57, 20, 94));
        bootsMeta.setColor(Color.fromRGB(27, 9, 45));
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        BossManager.getManager().createBossInstance("4", ("<#602d93><bold>Zombie Nekromanta"), 1500, 15, 3.2f, new ItemStack(SKELETON_SKULL, 1), chestplate, leggings, boots, new ItemStack(IRON_HORSE_ARMOR, 1), new ItemStack(AIR, 1), 1000);
    }
}
