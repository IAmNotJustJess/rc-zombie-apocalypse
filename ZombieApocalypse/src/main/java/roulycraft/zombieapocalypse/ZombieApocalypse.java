package roulycraft.zombieapocalypse;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import roulycraft.zombieapocalypse.commands.MainCommand;
import roulycraft.zombieapocalypse.managers.*;
import roulycraft.zombieapocalypse.utility.SoundSplitter;
import roulycraft.zombieapocalypse.weapons.ranged.RangedDefaultSettings;
import roulycraft.zombieapocalypse.weapons.ranged.RangedManager;
import roulycraft.zombieapocalypse.weapons.ranged.RangedWeaponInterpreter;
import roulycraft.zombieapocalypse.zombie.*;

import java.io.File;
import java.util.Objects;

public final class ZombieApocalypse extends JavaPlugin {

    @Override
    public void onEnable() {

        GameManager.injectPlugin(this);
        ZombieManager.injectPlugin(this);
        MainCommand.injectPlugin(this);
        ZombieListener.injectPlugin(this);
        RangedWeaponInterpreter.injectPlugin(this);
        RangedManager.injectPlugin(this);
        SoundSplitter.injectPlugin(this);
        RangedDefaultSettings.injectPlugin(this);

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
            console.sendMessage("");

            ZombieManager.getManager().createDefaultZombieInstances();

        }

        ZombieManager.getManager().loadZombieInstanceConfig();

        console.sendMessage("");
        console.sendMessage("§9== §bZakończono inicjowanie listy zombie! §9==");
        console.sendMessage("");

        File rangedFolder = new File(this.getDataFolder() + File.separator + "instances" + File.separator + "weapons" + File.separator + "ranged");

        console.sendMessage("");
        console.sendMessage("§9== §bInicjowanie listy broni dalekosiężnej! §9==");
        console.sendMessage("");

        if(!rangedFolder.exists()) {

            console.sendMessage("§6INFO! §eNie znaleziono folderu §franged§e! Tworzę domyślną konfigurację folderu §franged§e...");
            console.sendMessage("");

            RangedDefaultSettings.loadDefaultSettings();

        }

        File[] rangedFileList = rangedFolder.listFiles();
        assert rangedFileList != null;

        if(rangedFolder.exists() && rangedFileList.length != 0) {

            for (File rangedInstanceFile : rangedFileList) {

                String rangedInstanceName = rangedInstanceFile.getName().substring(0, rangedInstanceFile.getName().length() - 4);

                if (RangedManager.getManager().loadRangedInstanceConfig(Integer.valueOf(rangedInstanceName))) {

                    console.sendMessage("§2SUKCES! §aPoprawnie zinicjonowano instancję broni dalekosiężnej o ID: §f" + rangedInstanceName + "§a!");

                }

                else {

                    console.sendMessage("§4BŁĄD! §cInicjacja instancji broni dalekosiężnej o ID: §f" + rangedInstanceName + "§c nie powiodła się!");

                }
            }

            RangedManager.getManager().parseRangedInstancesAsItems();

            console.sendMessage("");
            console.sendMessage("§2SUKCES! §aPoprawnie zparsowano pliki na przedmioty!");

            console.sendMessage("");
            console.sendMessage("§9== §bZakończono inicjowanie listy broni dalekosiężnej! §9==");
            console.sendMessage("");

        }

        File instanceFolder = new File(this.getDataFolder() + File.separator + "instances" + File.separator + "arenas");

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
        Objects.requireNonNull(getServer().getPluginCommand("za")).setExecutor(new MainCommand());

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
