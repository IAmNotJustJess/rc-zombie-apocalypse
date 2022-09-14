package roulycraft.zombieapocalypse;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import roulycraft.zombieapocalypse.commands.ZACommand;
import roulycraft.zombieapocalypse.managers.*;

import java.util.Objects;

public final class ZombieApocalypse extends JavaPlugin {

    private GameInstance gameInstance;
    @Override
    public void onEnable() {

        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage("§aZinicjalizowano plugin ZombieApocalypse!");
        // Tutaj dodać inicjalizowanie map zapisanych do pliku
        Objects.requireNonNull(getServer().getPluginCommand("za")).setExecutor(new ZACommand());
//        getPlugin(ZombieApocalypse.class).saveDefaultConfig();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Funkcja przechodząca przez wszystkie pliki i zapisująca rzeczy do plików
    }

}
