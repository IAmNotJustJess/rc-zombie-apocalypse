package roulycraft.zombieapocalypse;

import org.bukkit.plugin.java.JavaPlugin;
import roulycraft.zombieapocalypse.commands.ZACommand;
import roulycraft.zombieapocalypse.managers.GameInstance;

import java.util.Objects;

public final class ZombieApocalypse extends JavaPlugin {

    private GameInstance gameManager;
    @Override
    public void onEnable() {

        getLogger().info("Zinicjalizowano plugin!");
        Objects.requireNonNull(getServer().getPluginCommand("za")).setExecutor(new ZACommand());
//        getPlugin(ZombieApocalypse.class).saveDefaultConfig();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
