package roulycraft.zombieapocalypse;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import roulycraft.zombieapocalypse.commands.ZACommand;
import roulycraft.zombieapocalypse.managers.*;
import roulycraft.zombieapocalypse.weapons.ranged.RangedWeaponInterpreter;
import roulycraft.zombieapocalypse.zombie.*;

import java.io.File;
import java.util.Objects;

import static org.bukkit.Material.*;

public final class ZombieApocalypse extends JavaPlugin {

    private GameInstance gameInstance;
    private GameManager gameManager;

    @Override
    public void onEnable() {

        GameManager.injectPlugin(this);
        ZombieManager.injectPlugin(this);
        ZACommand.injectPlugin(this);
        ZombieListener.injectPlugin(this);
        RangedWeaponInterpreter.injectPlugin(this);

        this.saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new ZombieListener(), this);
        getServer().getPluginManager().registerEvents(new RangedWeaponInterpreter(), this);

        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage("§6==== §eTrwa proces inicjacji §aZombieApocalypse§e! §6====");

        File zombieFile = new File(this.getDataFolder() + File.separator + "zombie.yml");

        console.sendMessage("");
        console.sendMessage("§9== §bInicjowanie listy zombie! §9==");
        console.sendMessage("");

        if(!zombieFile.exists()) {
            console.sendMessage("§6INFO! §eNie znaleziono pliku! Tworzę domyślną konfigurację pliku §fzombie.yml§e...");

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

        ZombieManager.getManager().loadZombieInstanceConfig();

        console.sendMessage("");
        console.sendMessage("§9== §bZakończono inicjowanie listy zombie! §9==");
        console.sendMessage("");

        File instanceFolder = new File(this.getDataFolder() + File.separator + "instances");

        File[] instanceFileList = instanceFolder.listFiles();
        assert instanceFileList != null;

        if(instanceFolder.exists() && instanceFileList.length != 0) {

            console.sendMessage("");
            console.sendMessage("§9== §bInicjowanie instancji gier! §9==");
            console.sendMessage("");

            for (File gameInstanceFile : instanceFileList) {
                String gameInstanceName = gameInstanceFile.getName().substring(0, gameInstanceFile.getName().length() - 4);
                if (GameManager.getManager().loadGameInstanceConfig(gameInstanceName)) {

                    console.sendMessage("§2SUKCES! §aPoprawnie zinicjonowano instancję gry §f" + gameInstanceName + "§a!");
                }
                else {

                    console.sendMessage("§4BŁĄD! §cInicjacja instancji gry §f" + gameInstanceName + "§c nie powiodła się!");
                }
            }

            console.sendMessage("");
            console.sendMessage("§9== §bZakończono inicjowanie instancji gier! §9==");
            console.sendMessage("");

        }
        // Tutaj dodać inicjalizowanie map zapisanych do pliku
        Objects.requireNonNull(getServer().getPluginCommand("za")).setExecutor(new ZACommand());

        console.sendMessage("§6==== §ePoprawnie zinicjowano §aZombieApocalypse§e! §6====");
//        getPlugin(ZombieApocalypse.class).saveDefaultConfig();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        this.saveDefaultConfig();
        // Funkcja przechodząca przez wszystkie pliki i zapisująca rzeczy do plików
    }

}
