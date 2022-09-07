package roulycraft.zombieapocalypse;

import org.bukkit.plugin.java.JavaPlugin;
import roulycraft.zombieapocalypse.commands.ZACommand;

public final class ZombieApocalypse extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("Zinicjalizowano plugin!");
        getServer().getPluginCommand("za").setExecutor(new ZACommand());
//        getPlugin(ZombieApocalypse.class).saveDefaultConfig();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
