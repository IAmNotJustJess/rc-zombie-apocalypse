package roulycraft.zombieapocalypse;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.java.JavaPlugin;
import roulycraft.zombieapocalypse.commands.ZACommand;
import roulycraft.zombieapocalypse.managers.*;

import java.io.File;
import java.util.Objects;

public final class ZombieApocalypse extends JavaPlugin {

    private GameInstance gameInstance;
    private GameManager gameManager;

    @Override
    public void onEnable() {

        GameManager.injectPlugin(this);
        this.saveDefaultConfig();
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage("§6==== §eTrwa proces inicjacji §aZombieApocalypse§e! §6====");

        File instanceFolder = new File(this.getDataFolder() + File.separator + "instances");

        if(instanceFolder.exists()) {

            console.sendMessage("");
            console.sendMessage("§6== §cInicjowanie instancji gier! §6==");
            console.sendMessage("");

            File[] instanceFileList = instanceFolder.listFiles();

            assert instanceFileList != null;

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
            console.sendMessage("§6== §eZakończono inicjowanie instancji gier! §6==");

        }
        // Tutaj dodać inicjalizowanie map zapisanych do pliku
        Objects.requireNonNull(getServer().getPluginCommand("za")).setExecutor(new ZACommand());

        console.sendMessage("");
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
