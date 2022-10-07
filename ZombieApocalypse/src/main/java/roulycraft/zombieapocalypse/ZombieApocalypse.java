package roulycraft.zombieapocalypse;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import roulycraft.zombieapocalypse.commands.ZACommand;
import roulycraft.zombieapocalypse.managers.*;
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

        this.saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new ZombieListener(), this);

        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage("§6==== §eTrwa proces inicjacji §aZombieApocalypse§e! §6====");

        File zombieFile = new File(this.getDataFolder() + File.separator + "zombie.yml");

        console.sendMessage("");
        console.sendMessage("§9== §bInicjowanie listy zombie! §9==");
        console.sendMessage("");

        if(!zombieFile.exists()) {
            console.sendMessage("§6INFO! §eNie znaleziono pliku! Tworzę domyślną konfigurację pliku §fzombie.yml§e...");

            ItemStack helmet = new ItemStack(LEATHER_HELMET);
            ItemStack chestplate = new ItemStack(LEATHER_CHESTPLATE);
            ItemStack leggings = new ItemStack(LEATHER_LEGGINGS);
            ItemStack boots = new ItemStack(LEATHER_BOOTS);

            LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
            LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();

            ZombieManager.getManager().saveZombieInstanceConfig();
            ZombieManager.getManager().createZombieInstance("default", "§2Zwykły", 20, 10, 3.4f, "", new ItemStack(AIR, 1), new ItemStack(AIR, 1), new ItemStack(AIR, 1), new ItemStack(AIR, 1), 5);

            Color color = Color.fromRGB(255, 255, 255);

            helmetMeta.setColor(color);
            chestplateMeta.setColor(color);
            leggingsMeta.setColor(color);
            bootsMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);

            ZombieManager.getManager().createZombieInstance("iron", "§7Żelazny", 40, 12, 3.4f, "", new ItemStack(IRON_BLOCK, 1), chestplate, leggings, boots, 10);

            color = Color.fromRGB(251, 213, 61);

            helmetMeta.setColor(color);
            chestplateMeta.setColor(color);
            leggingsMeta.setColor(color);
            bootsMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);

            ZombieManager.getManager().createZombieInstance("gold", "§eZłoty", 90, 15, 3.4f, "", new ItemStack(GOLD_BLOCK, 1), chestplate, leggings, boots, 15);

            color = Color.fromRGB(100, 242, 224);

            helmetMeta.setColor(color);
            chestplateMeta.setColor(color);
            leggingsMeta.setColor(color);
            bootsMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);

            ZombieManager.getManager().createZombieInstance("diamond", "§bDiamentowy", 135, 18, 3.4f, "", new ItemStack(DIAMOND_BLOCK, 1), chestplate, leggings, boots, 25);

            color = Color.fromRGB(22, 208, 95);

            helmetMeta.setColor(color);
            chestplateMeta.setColor(color);
            leggingsMeta.setColor(color);
            bootsMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);

            ZombieManager.getManager().createZombieInstance("emerald", "§aSzmaragdowy", 180, 20, 3.4f, "", new ItemStack(EMERALD_BLOCK, 1), chestplate, leggings, boots, 40);

            color = Color.fromRGB(227, 32, 8);

            helmetMeta.setColor(color);
            chestplateMeta.setColor(color);
            leggingsMeta.setColor(color);
            bootsMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);

            ZombieManager.getManager().createZombieInstance("ruby", "§4Rubinowy", 240, 22, 3.4f, "", new ItemStack(REDSTONE_BLOCK, 1), chestplate, leggings, boots, 60);

            color = Color.fromRGB(15, 11, 27);

            helmetMeta.setColor(color);
            chestplateMeta.setColor(color);
            leggingsMeta.setColor(color);
            bootsMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);

            ZombieManager.getManager().createZombieInstance("obsidian", "§0Obsydianowy", 320, 25, 3.4f, "", new ItemStack(OBSIDIAN, 1), chestplate, leggings, boots, 85);

            color = Color.fromRGB(193, 231, 208);

            helmetMeta.setColor(color);
            chestplateMeta.setColor(color);
            leggingsMeta.setColor(color);
            bootsMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);

            ZombieManager.getManager().createZombieInstance("speedy", "§bSzybki", 30, 8, 4.2f, "", new ItemStack(AIR, 1), chestplate, leggings, boots, 10);

            color = Color.fromRGB(231, 66, 24);

            helmetMeta.setColor(color);
            chestplateMeta.setColor(color);
            leggingsMeta.setColor(color);
            bootsMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);

            ZombieManager.getManager().createZombieInstance("explosive", "§cWybuchowy", 55, 12, 2.8f, "explosive", new ItemStack(TNT, 1), chestplate, leggings, boots, 10);

            color = Color.fromRGB(159, 192, 251);

            helmetMeta.setColor(color);
            chestplateMeta.setColor(color);
            leggingsMeta.setColor(color);
            bootsMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);

            ZombieManager.getManager().createZombieInstance("slowing", "§9Spowalniający", 75, 15, 3.4f, "slowing", new ItemStack(PACKED_ICE, 1), chestplate, leggings, boots, 15);

            color = Color.fromRGB(111, 48, 147);

            helmetMeta.setColor(color);
            chestplateMeta.setColor(color);
            leggingsMeta.setColor(color);
            bootsMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);

            ZombieManager.getManager().createZombieInstance("void", "§5Próżni", 105, 15, 3.4f, "void", new ItemStack(PURPLE_STAINED_GLASS, 1), chestplate, leggings, boots, 20);

            color = Color.fromRGB(176, 74, 10);

            helmetMeta.setColor(color);
            chestplateMeta.setColor(color);
            leggingsMeta.setColor(color);
            bootsMeta.setColor(color);
            helmet.setItemMeta(helmetMeta);
            chestplate.setItemMeta(chestplateMeta);
            leggings.setItemMeta(leggingsMeta);
            boots.setItemMeta(bootsMeta);

            ZombieManager.getManager().createZombieInstance("magma", "§6Magmowy", 120, 15, 3.4f, "magma", new ItemStack(MAGMA_BLOCK, 1), chestplate, leggings, boots, 20);

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
