package roulycraft.zombieapocalypse.zombie;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import roulycraft.zombieapocalypse.ZombieApocalypse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Material.*;

public class ZombieManager {
    private static ZombieManager zombieManager;
    private static ZombieApocalypse plugin;
    private FileConfiguration zombieInstanceConfig = null;
    private final File zombieInstanceFile = new File(plugin.getDataFolder() + File.separator + "zombie.yml");
    public final List<ZombieInstance> zombieInstanceList = new ArrayList<>();

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }
    private ZombieManager() {
    }

    public static ZombieManager getManager() {
        if (zombieManager == null) {
            zombieManager = new ZombieManager();
        }
        return zombieManager;
    }

    public void createDefaultZombieInstances() {

        String zombieLvl0 = "";
        String zombieLvl1 = "§7Wzmocniony ";
        String zombieLvl2 = "§bNiepowstrzymany ";
        String zombieLvl3 = "§dOstateczny ";

        ItemStack helmet = new ItemStack(LEATHER_HELMET);
        ItemStack chestplate = new ItemStack(LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(LEATHER_BOOTS);

        ItemStack leggings1 = new ItemStack(LEATHER_LEGGINGS);
        ItemStack leggings2 = new ItemStack(LEATHER_LEGGINGS);
        ItemStack leggings3 = new ItemStack(LEATHER_LEGGINGS);

        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();

        LeatherArmorMeta leggingsMeta1 = (LeatherArmorMeta) leggings1.getItemMeta();
        LeatherArmorMeta leggingsMeta2 = (LeatherArmorMeta) leggings2.getItemMeta();
        LeatherArmorMeta leggingsMeta3 = (LeatherArmorMeta) leggings3.getItemMeta();

        Color color = Color.fromRGB(170, 170, 170);

        leggingsMeta1.setColor(color);

        color = Color.fromRGB(85, 255, 255);

        leggingsMeta2.setColor(color);

        color = Color.fromRGB(255, 85, 255);

        leggingsMeta3.setColor(color);

        leggings1.setItemMeta(leggingsMeta1);
        leggings2.setItemMeta(leggingsMeta2);
        leggings3.setItemMeta(leggingsMeta3);

        ZombieManager.getManager().saveZombieInstanceConfig();

        ZombieManager.getManager().createZombieInstance("default0", (zombieLvl0 + "§2Zwykły"), 20, 10, 3.4f, "", new ItemStack(AIR, 1), new ItemStack(AIR, 1), new ItemStack(AIR, 1), new ItemStack(AIR, 1), 5);

        color = Color.fromRGB(255, 255, 255);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("iron0", (zombieLvl0 + "§7Żelazny"), 40, 12, 3.4f, "", new ItemStack(IRON_BLOCK, 1), chestplate, leggings, boots, 10);

        color = Color.fromRGB(251, 213, 61);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("gold0", (zombieLvl0 + "§eZłoty"), 90, 15, 3.4f, "", new ItemStack(GOLD_BLOCK, 1), chestplate, leggings, boots, 15);

        color = Color.fromRGB(100, 242, 224);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("diamond0", (zombieLvl0 + "§bDiamentowy"), 135, 18, 3.4f, "", new ItemStack(DIAMOND_BLOCK, 1), chestplate, leggings, boots, 25);

        color = Color.fromRGB(22, 208, 95);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("emerald0", (zombieLvl0 + "§aSzmaragdowy"), 180, 20, 3.4f, "", new ItemStack(EMERALD_BLOCK, 1), chestplate, leggings, boots, 40);

        color = Color.fromRGB(227, 32, 8);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("ruby0", (zombieLvl0 + "§4Rubinowy"), 240, 22, 3.4f, "", new ItemStack(REDSTONE_BLOCK, 1), chestplate, leggings, boots, 60);

        color = Color.fromRGB(15, 11, 27);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("obsidian0", (zombieLvl0 + "§0Obsydianowy"), 320, 25, 3.4f, "", new ItemStack(OBSIDIAN, 1), chestplate, leggings, boots, 85);

        color = Color.fromRGB(193, 231, 208);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("speedy0", (zombieLvl0 + "§bSzybki"), 30, 8, 4.2f, "", new ItemStack(AIR, 1), chestplate, leggings, boots, 10);

        color = Color.fromRGB(231, 66, 24);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("explosive0", (zombieLvl0 + "§cWybuchowy"), 55, 12, 2.8f, "explosive", new ItemStack(TNT, 1), chestplate, leggings, boots, 10);

        color = Color.fromRGB(159, 192, 251);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("slowing0", (zombieLvl0 + "§9Spowalniający"), 75, 15, 3.4f, "slowing", new ItemStack(PACKED_ICE, 1), chestplate, leggings, boots, 15);

        color = Color.fromRGB(111, 48, 147);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("void0", (zombieLvl0 + "§5Próżni"), 105, 15, 3.4f, "void", new ItemStack(PURPLE_STAINED_GLASS, 1), chestplate, leggings, boots, 20);

        color = Color.fromRGB(176, 74, 10);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("magma0", (zombieLvl0 + "§6Magmowy"), 120, 15, 3.4f, "magma", new ItemStack(MAGMA_BLOCK, 1), chestplate, leggings, boots, 20);

        ZombieManager.getManager().createZombieInstance("default1", (zombieLvl1 + "§2Zwykły"), 40, 15, 3.2f, "", new ItemStack(AIR, 1), new ItemStack(AIR, 1), leggings1, new ItemStack(AIR, 1), 10);

        color = Color.fromRGB(255, 255, 255);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("iron1", (zombieLvl1 + "§7Żelazny"), 80, 18, 3.2f, "", new ItemStack(IRON_BLOCK, 1), chestplate, leggings1, boots, 20);

        color = Color.fromRGB(251, 213, 61);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("gold1", (zombieLvl1 + "§eZłoty"), 180, 21, 3.2f, "", new ItemStack(GOLD_BLOCK, 1), chestplate, leggings1, boots, 20);

        color = Color.fromRGB(100, 242, 224);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("diamond1", (zombieLvl1 + "§bDiamentowy"), 270, 27, 3.2f, "", new ItemStack(DIAMOND_BLOCK, 1), chestplate, leggings1, boots, 30);

        color = Color.fromRGB(22, 208, 95);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("emerald1", (zombieLvl1 + "§aSzmaragdowy"), 360, 30, 3.2f, "", new ItemStack(EMERALD_BLOCK, 1), chestplate, leggings1, boots, 45);

        color = Color.fromRGB(227, 32, 8);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("ruby1", (zombieLvl1 + "§4Rubinowy"), 480, 33, 3.2f, "", new ItemStack(REDSTONE_BLOCK, 1), chestplate, leggings1, boots, 70);

        color = Color.fromRGB(15, 11, 27);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("obsidian1", (zombieLvl1 + "§0Obsydianowy"), 640, 38, 3.2f, "", new ItemStack(OBSIDIAN, 1), chestplate, leggings1, boots, 100);

        color = Color.fromRGB(193, 231, 208);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("speedy1", (zombieLvl1 + "§bSzybki"), 60, 12, 4.0f, "", new ItemStack(AIR, 1), chestplate, leggings1, boots, 15);

        color = Color.fromRGB(231, 66, 24);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("explosive1", (zombieLvl1 + "§cWybuchowy"), 110, 18, 2.6f, "explosive", new ItemStack(TNT, 1), chestplate, leggings1, boots, 15);

        color = Color.fromRGB(159, 192, 251);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("slowing1", (zombieLvl1 + "§9Spowalniający"), 150, 22, 3.2f, "slowing", new ItemStack(PACKED_ICE, 1), chestplate, leggings1, boots, 20);

        color = Color.fromRGB(111, 48, 147);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("void1", (zombieLvl1 + "§5Próżni"), 210, 22, 3.2f, "void", new ItemStack(PURPLE_STAINED_GLASS, 1), chestplate, leggings1, boots, 25);

        color = Color.fromRGB(176, 74, 10);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("magma1", (zombieLvl1 + "§6Magmowy"), 240, 22, 3.2f, "magma", new ItemStack(MAGMA_BLOCK, 1), chestplate, leggings1, boots, 25);

        ZombieManager.getManager().createZombieInstance("default2", (zombieLvl2 + "§2Zwykły"), 60, 20, 3.0f, "", new ItemStack(AIR, 1), new ItemStack(AIR, 1), leggings2, new ItemStack(AIR, 1), 15);

        color = Color.fromRGB(255, 255, 255);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("iron2", (zombieLvl2 + "§7Żelazny"), 120, 24, 3.0f, "", new ItemStack(IRON_BLOCK, 1), chestplate, leggings2, boots, 25);

        color = Color.fromRGB(251, 213, 61);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("gold2", (zombieLvl2 + "§eZłoty"), 270, 30, 3.0f, "", new ItemStack(GOLD_BLOCK, 1), chestplate, leggings2, boots, 25);

        color = Color.fromRGB(100, 242, 224);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("diamond2", (zombieLvl2 + "§bDiamentowy"), 405, 36, 3.0f, "", new ItemStack(DIAMOND_BLOCK, 1), chestplate, leggings2, boots, 35);

        color = Color.fromRGB(22, 208, 95);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("emerald2", (zombieLvl2 + "§aSzmaragdowy"), 540, 40, 3.0f, "", new ItemStack(EMERALD_BLOCK, 1), chestplate, leggings2, boots, 55);

        color = Color.fromRGB(227, 32, 8);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("ruby2", (zombieLvl2 + "§4Rubinowy"), 720, 44, 3.0f, "", new ItemStack(REDSTONE_BLOCK, 1), chestplate, leggings2, boots, 80);

        color = Color.fromRGB(15, 11, 27);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("obsidian2", (zombieLvl2 + "§0Obsydianowy"), 960, 50, 3.0f, "", new ItemStack(OBSIDIAN, 1), chestplate, leggings2, boots, 115);

        color = Color.fromRGB(193, 231, 208);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("speedy2", (zombieLvl2 + "§bSzybki"), 90, 16, 3.8f, "", new ItemStack(AIR, 1), chestplate, leggings2, boots, 20);

        color = Color.fromRGB(231, 66, 24);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("explosive2", (zombieLvl2 + "§cWybuchowy"), 165, 24, 2.4f, "explosive", new ItemStack(TNT, 1), chestplate, leggings2, boots, 20);

        color = Color.fromRGB(159, 192, 251);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("slowing2", (zombieLvl2 + "§9Spowalniający"), 225, 30, 3.0f, "slowing", new ItemStack(PACKED_ICE, 1), chestplate, leggings2, boots, 25);

        color = Color.fromRGB(111, 48, 147);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("void2", (zombieLvl2 + "§5Próżni"), 315, 30, 3.0f, "void", new ItemStack(PURPLE_STAINED_GLASS, 1), chestplate, leggings2, boots, 30);

        color = Color.fromRGB(176, 74, 10);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("magma2", (zombieLvl2 + "§6Magmowy"), 360, 30, 3.0f, "magma", new ItemStack(MAGMA_BLOCK, 1), chestplate, leggings2, boots, 30);

        ZombieManager.getManager().createZombieInstance("default3", (zombieLvl3 + "§2Zwykły"), 80, 25, 2.8f, "", new ItemStack(AIR, 1), new ItemStack(AIR, 1), leggings3, new ItemStack(AIR, 1), 20);

        color = Color.fromRGB(255, 255, 255);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("iron3", (zombieLvl3 + "§7Żelazny"), 160, 30, 2.8f, "", new ItemStack(IRON_BLOCK, 1), chestplate, leggings3, boots, 30);

        color = Color.fromRGB(251, 213, 61);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("gold3", (zombieLvl3 + "§eZłoty"), 360, 37, 2.8f, "", new ItemStack(GOLD_BLOCK, 1), chestplate, leggings3, boots, 35);

        color = Color.fromRGB(100, 242, 224);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("diamond3", (zombieLvl3 + "§bDiamentowy"), 540, 45, 2.8f, "", new ItemStack(DIAMOND_BLOCK, 1), chestplate, leggings3, boots, 45);

        color = Color.fromRGB(22, 208, 95);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("emerald3", (zombieLvl3 + "§aSzmaragdowy"), 720, 50, 2.8f, "", new ItemStack(EMERALD_BLOCK, 1), chestplate, leggings3, boots, 65);

        color = Color.fromRGB(227, 32, 8);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("ruby3", (zombieLvl3 + "§4Rubinowy"), 960, 55, 2.8f, "", new ItemStack(REDSTONE_BLOCK, 1), chestplate, leggings3, boots, 90);

        color = Color.fromRGB(15, 11, 27);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("obsidian3", (zombieLvl3 + "§0Obsydianowy"), 1280, 63, 2.8f, "", new ItemStack(OBSIDIAN, 1), chestplate, leggings3, boots, 130);

        color = Color.fromRGB(193, 231, 208);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("speedy3", (zombieLvl3 + "§bSzybki"), 120, 16, 3.6f, "", new ItemStack(AIR, 1), chestplate, leggings3, boots, 30);

        color = Color.fromRGB(231, 66, 24);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("explosive3", (zombieLvl3 + "§cWybuchowy"), 220, 24, 2.2f, "explosive", new ItemStack(TNT, 1), chestplate, leggings3, boots, 30);

        color = Color.fromRGB(159, 192, 251);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("slowing3", (zombieLvl3 + "§9Spowalniający"), 300, 37, 2.8f, "slowing", new ItemStack(PACKED_ICE, 1), chestplate, leggings3, boots, 35);

        color = Color.fromRGB(111, 48, 147);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("void3", (zombieLvl3 + "§5Próżni"), 420, 37, 2.8f, "void", new ItemStack(PURPLE_STAINED_GLASS, 1), chestplate, leggings3, boots, 40);

        color = Color.fromRGB(176, 74, 10);

        helmetMeta.setColor(color);
        chestplateMeta.setColor(color);
        leggingsMeta.setColor(color);
        bootsMeta.setColor(color);
        helmet.setItemMeta(helmetMeta);
        chestplate.setItemMeta(chestplateMeta);
        leggings.setItemMeta(leggingsMeta);
        boots.setItemMeta(bootsMeta);

        ZombieManager.getManager().createZombieInstance("magma3", (zombieLvl3 + "§6Magmowy"), 480, 37, 2.8f, "magma", new ItemStack(MAGMA_BLOCK, 1), chestplate, leggings3, boots, 40);

        ZombieManager.getManager().saveZombieInstanceConfig();

    }

    public void createZombieInstance(String name, String displayName, Integer health, Integer damage, Float speed, String special, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, Integer xpReward) {

        ZombieInstance zombieInstance = null;

        for (ZombieInstance checkIfExists : this.zombieInstanceList) {
            if (checkIfExists.getName().equals(name)) {
                return;
            }
        }

        zombieInstance = new ZombieInstance(name, displayName, health, damage, speed, special, helmet, chestplate, leggings, boots, xpReward);
        this.zombieInstanceList.add(zombieInstance);

        reloadZombieInstanceConfig();
        getZombieInstanceConfig().set(("zombies." + name + ".displayName"), displayName);
        getZombieInstanceConfig().set(("zombies." + name + ".health"), health);
        getZombieInstanceConfig().set(("zombies." + name + ".damage"), damage);
        getZombieInstanceConfig().set(("zombies." + name + ".speed"), speed);
        getZombieInstanceConfig().set(("zombies." + name + ".special"), special);
        getZombieInstanceConfig().set(("zombies." + name + ".helmet"), helmet);
        getZombieInstanceConfig().set(("zombies." + name + ".chestplate"), chestplate);
        getZombieInstanceConfig().set(("zombies." + name + ".leggings"), leggings);
        getZombieInstanceConfig().set(("zombies." + name + ".boots"), boots);
        getZombieInstanceConfig().set(("zombies." + name + ".xpReward"), xpReward);
        saveZombieInstanceConfig();
    }

    public ZombieInstance getZombieInstance(String name){
        for (ZombieInstance zombieInstance : this.zombieInstanceList) {
            if (zombieInstance.getName().equals(name)) {
                return zombieInstance;
            }
        }

        return null;
    }

    public void reloadZombieInstanceConfig() {
        zombieInstanceConfig = YamlConfiguration.loadConfiguration(zombieInstanceFile);
    }

    public FileConfiguration getZombieInstanceConfig() {
        if (zombieInstanceConfig == null) {
            reloadZombieInstanceConfig();
        }
        return zombieInstanceConfig;
    }

    public void saveZombieInstanceConfig() {

        try {
            getZombieInstanceConfig().save(zombieInstanceFile);
        }

        catch (IOException ex) {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            console.sendMessage("§4BŁĄD KRYTYCZNY §cNie można było zapisać konfiguracji §fzombie.yml§c!");
            console.sendMessage(String.valueOf(ex));
        }
    }

    public void loadZombieInstanceConfig() {
        reloadZombieInstanceConfig();

        if (!zombieInstanceFile.exists()) {
            return;
        }
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        ZP:
        for (String path : zombieInstanceConfig.getConfigurationSection("zombies").getKeys(false)) {

            for (ZombieInstance zombieInstance : this.zombieInstanceList) {
                if (zombieInstance.getName().equals(path)) {

                    getZombieInstance(path).setDisplayName(zombieInstanceConfig.getString("zombies."+path+".displayName"));
                    getZombieInstance(path).setHealth(zombieInstanceConfig.getInt("zombies."+path+".health"));
                    getZombieInstance(path).setDamage(zombieInstanceConfig.getInt("zombies."+path+".damage"));
                    getZombieInstance(path).setSpeed((float) zombieInstanceConfig.getDouble("zombies."+path+".speed"));
                    getZombieInstance(path).setSpecial(zombieInstanceConfig.getString("zombies."+path+".special"));
                    getZombieInstance(path).setHelmet(zombieInstanceConfig.getItemStack("zombies."+path+".helmet"));
                    getZombieInstance(path).setChestplate(zombieInstanceConfig.getItemStack("zombies."+path+".chestplate"));
                    getZombieInstance(path).setLeggings(zombieInstanceConfig.getItemStack("zombies."+path+".leggings"));
                    getZombieInstance(path).setBoots(zombieInstanceConfig.getItemStack("zombies."+path+".boots"));
                    getZombieInstance(path).setXPReward(zombieInstanceConfig.getInt("zombies."+path+".xpReward"));

                    console.sendMessage("§2SUKCES! §aPoprawnie zinicjonowano zombie §f" + path + "§a!");

                    continue ZP;
                }
            }
            createZombieInstance(
                path,
                zombieInstanceConfig.getString("zombies."+path+".displayName"),
                zombieInstanceConfig.getInt("zombies."+path+".health"),
                zombieInstanceConfig.getInt("zombies."+path+".damage"),
                (float) zombieInstanceConfig.getDouble("zombies."+path+".speed"),
                zombieInstanceConfig.getString("zombies."+path+".special"),
                zombieInstanceConfig.getItemStack("zombies."+path+".helmet"),
                zombieInstanceConfig.getItemStack("zombies."+path+".chestplate"),
                zombieInstanceConfig.getItemStack("zombies."+path+".leggings"),
                zombieInstanceConfig.getItemStack("zombies."+path+".boots"),
                zombieInstanceConfig.getInt("zombies."+path+".xpReward")
            );

            console.sendMessage("§2SUKCES! §aPoprawnie zinicjonowano zombie §f" + path + "§a!");
        }

    }

    public void spawnZombie(Location loc, String name, Boolean countTowardsKills) {

        Entity zombie = loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);

        zombie.setCustomName(ZombieManager.getManager().getZombieInstance(name).getDisplayName());
        zombie.setCustomNameVisible(true);

        zombie.setMetadata("ZA", new FixedMetadataValue(plugin, true));
        zombie.setMetadata("health", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getHealth()));
        zombie.setMetadata("maxHealth", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getHealth()));
        zombie.setMetadata("damage", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getDamage()));
        zombie.setMetadata("xpReward", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getXPReward()));

        NamespacedKey key = new NamespacedKey(plugin, "zabossbar" + zombie.getEntityId());
        zombie.setMetadata("bossbarKey", new FixedMetadataValue(plugin, ("zabossbar" + zombie.getEntityId())));

        Bukkit.getServer().createBossBar(key, "", BarColor.GREEN, BarStyle.SOLID);
        Bukkit.getServer().getBossBar(key).setProgress(1.0);


        if(countTowardsKills) {
            zombie.setMetadata("countTowardKills", new FixedMetadataValue(plugin, 1));
        }

        else {
            zombie.setMetadata("countTowardKills", new FixedMetadataValue(plugin, 0));
        }

        ((Zombie) zombie).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(ZombieManager.getManager().getZombieInstance(name).getSpeed()/10);

        ((Zombie) zombie).setAdult();

        ((Zombie) zombie).getEquipment().setHelmet(ZombieManager.getManager().getZombieInstance(name).getHelmet());
        ((Zombie) zombie).getEquipment().setChestplate(ZombieManager.getManager().getZombieInstance(name).getChestplate());
        ((Zombie) zombie).getEquipment().setLeggings(ZombieManager.getManager().getZombieInstance(name).getLeggings());
        ((Zombie) zombie).getEquipment().setBoots(ZombieManager.getManager().getZombieInstance(name).getBoots());

        ((Zombie) zombie).getEquipment().setHelmetDropChance(0f);
        ((Zombie) zombie).getEquipment().setChestplateDropChance(0f);
        ((Zombie) zombie).getEquipment().setLeggingsDropChance(0f);
        ((Zombie) zombie).getEquipment().setBootsDropChance(0f);

        ZombieListener.insertMap(NamespacedKey.fromString(zombie.getMetadata("bossbarKey").get(0).asString(), plugin));
    }
}

