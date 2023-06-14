package roulycraft.zombieapocalypse.bosses;

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
    }
}
