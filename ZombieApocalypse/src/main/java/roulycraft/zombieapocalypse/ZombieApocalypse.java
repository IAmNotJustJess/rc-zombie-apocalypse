package roulycraft.zombieapocalypse;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
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
        this.saveDefaultConfig();
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage("§6==== §eTrwa proces inicjacji §aZombieApocalypse§e! §6====");

        File zombieFile = new File(this.getDataFolder() + File.separator + "zombie.yml");

        console.sendMessage("");
        console.sendMessage("§9== §bInicjowanie listy zombie! §9==");
        console.sendMessage("");

        if(!zombieFile.exists()) {
            console.sendMessage("§6INFO! §eNie znaleziono pliku! Tworzę domyślną konfigurację pliku §fzombie.yml§e...");
            ZombieManager.getManager().saveZombieInstanceConfig();
            ZombieManager.getManager().createZombieInstance("Default", "Test", 20, 4, 3f, "", new ItemStack(AIR, 1), new ItemStack(AIR, 1), new ItemStack(AIR, 1), new ItemStack(AIR, 1));
            ZombieManager.getManager().createZombieInstance("Iron", "Testowiron", 55, 6, 3f, "", new ItemStack(AIR, 1), new ItemStack(AIR, 1), new ItemStack(AIR, 1), new ItemStack(AIR, 1));
            ZombieManager.getManager().createZombieInstance("Gold", "Testowiron", 55, 6, 3f, "", new ItemStack(AIR, 1), new ItemStack(AIR, 1), new ItemStack(AIR, 1), new ItemStack(AIR, 1));
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
