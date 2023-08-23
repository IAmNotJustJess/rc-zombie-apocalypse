package roulycraft.zombieapocalypse;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.plugin.java.JavaPlugin;
import roulycraft.zombieapocalypse.bosses.BossDefaultSettings;
import roulycraft.zombieapocalypse.bosses.BossManager;
import roulycraft.zombieapocalypse.bosses.BossSpecial;
import roulycraft.zombieapocalypse.commands.MainCommand;
import roulycraft.zombieapocalypse.game.GameManager;
import roulycraft.zombieapocalypse.players.PlayerListener;
import roulycraft.zombieapocalypse.utility.SoundSplitter;
import roulycraft.zombieapocalypse.waves.WaveDefaultSettings;
import roulycraft.zombieapocalypse.waves.WaveManager;
import roulycraft.zombieapocalypse.weapons.ranged.RangedDefaultSettings;
import roulycraft.zombieapocalypse.weapons.ranged.RangedManager;
import roulycraft.zombieapocalypse.weapons.ranged.RangedWeaponInterpreter;
import roulycraft.zombieapocalypse.zombie.ZombieDefaultSettings;
import roulycraft.zombieapocalypse.zombie.ZombieListener;
import roulycraft.zombieapocalypse.zombie.ZombieManager;
import roulycraft.zombieapocalypse.zombie.ZombieSpecial;

import java.io.File;
import java.util.Iterator;
import java.util.Objects;

public final class ZombieApocalypse extends JavaPlugin {

    @Override
    public void onEnable() {

        for (Iterator<KeyedBossBar> it = Bukkit.getBossBars(); it.hasNext(); ) {

            KeyedBossBar bar = it.next();

            if (bar.getKey().getKey().contains("zabossbar.")) {
                bar.removeAll();
            }

        }

        {
            SoundSplitter.injectPlugin(this);
            MainCommand.injectPlugin(this);
            GameManager.injectPlugin(this);
            ZombieManager.injectPlugin(this);
            ZombieListener.injectPlugin(this);
            ZombieDefaultSettings.injectPlugin(this);
            ZombieSpecial.injectPlugin(this);
            BossManager.injectPlugin(this);
            BossSpecial.injectPlugin(this);
            RangedWeaponInterpreter.injectPlugin(this);
            RangedManager.injectPlugin(this);
            RangedDefaultSettings.injectPlugin(this);
            WaveManager.injectPlugin(this);
            WaveDefaultSettings.injectPlugin(this);
        } // Plugin Injections

        ZombieListener.clearAllBossbars();

        this.saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new ZombieListener(), this);
        getServer().getPluginManager().registerEvents(new RangedWeaponInterpreter(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        Audience console = (Audience) Bukkit.getConsoleSender();
        MiniMessage miniMessage = MiniMessage.miniMessage();

        console.sendMessage(miniMessage.deserialize("<gold>==== <yellow>Trwa proces inicjacji <green>ZombieApocalypse<yellow>! <gold>===="));

        {
            File zombieFile = new File(this.getDataFolder() + File.separator + "instances" + File.separator + "zombies");

            console.sendMessage(miniMessage.deserialize(""));
            console.sendMessage(miniMessage.deserialize("<blue>== <aqua>Inicjowanie listy zombie! <blue>=="));
            console.sendMessage(miniMessage.deserialize(""));

            if (!zombieFile.exists()) {

                if (this.getConfig().getBoolean("settings.logZombieLoad")) {
                    console.sendMessage(miniMessage.deserialize("<gold>INFO! <yellow>Nie znaleziono folderu <white> zombies<yellow>! Tworzę domyślną konfigurację folder <white>zombies<yellow>..."));
                    console.sendMessage(miniMessage.deserialize(""));
                }

                ZombieDefaultSettings.getManager().loadDefaultSettings();

            }

            File[] zombieFileList = zombieFile.listFiles();
            assert zombieFileList != null;

            if (zombieFile.exists()) {

                for (File zombieInstanceFile : zombieFileList) {

                    String zombieInstanceName = zombieInstanceFile.getName().substring(0, zombieInstanceFile.getName().length() - 4);

                    if (ZombieManager.getManager().loadZombieInstanceConfig(zombieInstanceName)) {

                        if (this.getConfig().getBoolean("settings.logZombieLoad")) {
                            console.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Poprawnie zinicjonowano zombie <white>" + zombieInstanceName + "<green>!"));
                        }

                    }
                    else {

                        if (this.getConfig().getBoolean("settings.logZombieLoad")) {
                            console.sendMessage(miniMessage.deserialize("<dark_red>BŁĄD! <red>Inicjacja zombie <white>" + zombieInstanceName + "<red> nie powiodła się!"));
                        }

                    }
                }
            }

            console.sendMessage(miniMessage.deserialize(""));
            console.sendMessage(miniMessage.deserialize("<blue>== <aqua>Zakończono inicjowanie listy zombie! <blue>=="));
            console.sendMessage(miniMessage.deserialize(""));
        } // Zombie loading
        {

            console.sendMessage(miniMessage.deserialize(""));
            console.sendMessage(miniMessage.deserialize("<blue>== <aqua>Inicjowanie listy bossów! <blue>=="));
            console.sendMessage(miniMessage.deserialize(""));

            File bossFile = new File(this.getDataFolder() + File.separator + "instances" + File.separator + "bosses");

            if (!bossFile.exists()) {

                if (this.getConfig().getBoolean("settings.logBossLoad")) {
                    console.sendMessage(miniMessage.deserialize("<gold>INFO! <yellow>Nie znaleziono folderu <white> bosses<yellow>! Tworzę domyślną konfigurację folder <white>bosses<yellow>..."));
                    console.sendMessage(miniMessage.deserialize(""));
                }

                BossDefaultSettings.getManager().loadDefaultSettings();

            }


            File[] bossFileList = bossFile.listFiles();
            assert bossFileList != null;

            if (bossFile.exists()) {

                for (File bossInstanceFile : bossFileList) {

                    String bossInstanceName = bossInstanceFile.getName().substring(0, bossInstanceFile.getName().length() - 4);

                    if (BossManager.getManager().loadBossInstanceConfig(bossInstanceName)) {

                        if (this.getConfig().getBoolean("settings.logZombieLoad")) {
                            console.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Poprawnie zinicjonowano zombie <white>" + bossInstanceName + "<green>!"));
                        }

                    }
                    else {

                        if (this.getConfig().getBoolean("settings.logZombieLoad")) {
                            console.sendMessage(miniMessage.deserialize("<dark_red>BŁĄD! <red>Inicjacja zombie <white>" + bossInstanceName + "<red> nie powiodła się!"));
                        }

                    }
                }
            }

            console.sendMessage(miniMessage.deserialize(""));
            console.sendMessage(miniMessage.deserialize("<blue>== <aqua>Zakończono inicjowanie listy bossów! <blue>=="));
            console.sendMessage(miniMessage.deserialize(""));
        } // Boss loading
        {
            File waveFolder = new File(this.getDataFolder() + File.separator + "instances" + File.separator + "waves");

            console.sendMessage(miniMessage.deserialize(""));
            console.sendMessage(miniMessage.deserialize("<blue>== <aqua>Inicjowanie listy fal! <blue>=="));
            console.sendMessage(miniMessage.deserialize(""));

            if (!waveFolder.exists()) {

                if (this.getConfig().getBoolean("settings.logWaveLoad")) {
                    console.sendMessage(miniMessage.deserialize("<gold>INFO! <yellow>Nie znaleziono folderu <white>waves<yellow>! Tworzę domyślną konfigurację folderu <white>waves<yellow>..."));
                    console.sendMessage(miniMessage.deserialize(""));
                }

                WaveDefaultSettings.loadDefaultSettings();

            }

            File[] waveFileList = waveFolder.listFiles();
            assert waveFileList != null;

            if (waveFolder.exists()) {

                for (File waveInstanceFile : waveFileList) {

                    String waveInstanceName = waveInstanceFile.getName().substring(0, waveInstanceFile.getName().length() - 4);

                    if (WaveManager.getManager().loadWaveInstanceConfig(Integer.valueOf(waveInstanceName))) {

                        if (this.getConfig().getBoolean("settings.logWaveLoad")) {
                            console.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Poprawnie zinicjonowano instancję fali o ID: <white>" + waveInstanceName + "<green>!"));
                        }

                    }
                    else {

                        if (this.getConfig().getBoolean("settings.logWaveLoad")) {
                            console.sendMessage(miniMessage.deserialize("<dark_red>BŁĄD! <red>Inicjacja instancji fali o ID: <white>" + waveInstanceName + "<red> nie powiodła się!"));
                        }

                    }
                }
            }

            console.sendMessage(miniMessage.deserialize(""));
            console.sendMessage(miniMessage.deserialize("<blue>== <aqua>Zakończono inicjowanie listy fal! <blue>=="));
            console.sendMessage(miniMessage.deserialize(""));
        } // Wave loading
        {
            File rangedFolder = new File(this.getDataFolder() + File.separator + "instances" + File.separator + "weapons" + File.separator + "ranged");

            console.sendMessage(miniMessage.deserialize(""));
            console.sendMessage(miniMessage.deserialize("<blue>== <aqua>Inicjowanie listy broni dalekosiężnej! <blue>=="));
            console.sendMessage(miniMessage.deserialize(""));

            if (!rangedFolder.exists()) {

                if (this.getConfig().getBoolean("settings.logRangedLoad")) {
                    console.sendMessage(miniMessage.deserialize("<gold>INFO! <yellow>Nie znaleziono folderu <white>ranged<yellow>! Tworzę domyślną konfigurację folderu <white>ranged<yellow>..."));
                    console.sendMessage(miniMessage.deserialize(""));
                }

                RangedDefaultSettings.loadDefaultSettings();

            }

            File[] rangedFileList = rangedFolder.listFiles();
            assert rangedFileList != null;

            if (rangedFolder.exists() && rangedFileList.length != 0) {

                for (File rangedInstanceFile : rangedFileList) {

                    String rangedInstanceName = rangedInstanceFile.getName().substring(0, rangedInstanceFile.getName().length() - 4);

                    if (RangedManager.getManager().loadRangedInstanceConfig(Integer.valueOf(rangedInstanceName))) {

                        if (this.getConfig().getBoolean("settings.logRangedLoad")) {
                            console.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Poprawnie zinicjonowano instancję broni dalekosiężnej o ID: <white>" + rangedInstanceName + "<green>!"));
                        }

                    }
                    else {

                        if (this.getConfig().getBoolean("settings.logRangedLoad")) {
                            console.sendMessage(miniMessage.deserialize("<dark_red>BŁĄD! <red>Inicjacja instancji broni dalekosiężnej o ID: <white>" + rangedInstanceName + "<red> nie powiodła się!"));
                        }

                    }
                }

                RangedManager.getManager().parseRangedInstancesAsItems();

                if (this.getConfig().getBoolean("settings.logRangedLoad")) {
                    console.sendMessage(miniMessage.deserialize(""));
                    console.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Poprawnie zparsowano pliki na przedmioty!"));
                }


                console.sendMessage(miniMessage.deserialize(""));
                console.sendMessage(miniMessage.deserialize("<blue>== <aqua>Zakończono inicjowanie listy broni dalekosiężnej! <blue>=="));
                console.sendMessage(miniMessage.deserialize(""));

            }
        } // Ranged weapon loading
        {
            File instanceFolder = new File(this.getDataFolder() + File.separator + "instances" + File.separator + "arenas");

            File[] instanceFileList = instanceFolder.listFiles();
            assert instanceFileList != null;

            if (instanceFolder.exists() && instanceFileList.length != 0) {

                console.sendMessage(miniMessage.deserialize(""));
                console.sendMessage(miniMessage.deserialize("<blue>== <aqua>Inicjowanie instancji gier! <blue>=="));
                console.sendMessage(miniMessage.deserialize(""));

                for (File gameInstanceFile : instanceFileList) {

                    String gameInstanceName = gameInstanceFile.getName().substring(0, gameInstanceFile.getName().length() - 4);

                    if (GameManager.getManager().loadGameInstanceConfig(gameInstanceName)) {

                        if (this.getConfig().getBoolean("settings.logArenaLoad")) {
                            console.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Poprawnie zinicjonowano instancję gry <white>" + gameInstanceName + "<green>!"));
                        }
                    }
                    else {
                        if (this.getConfig().getBoolean("settings.logArenaLoad")) {
                            console.sendMessage(miniMessage.deserialize("<dark_red>BŁĄD! <red>Inicjacja instancji gry <white>" + gameInstanceName + "<red> nie powiodła się!"));
                        }
                    }
                }

                console.sendMessage(miniMessage.deserialize(""));
                console.sendMessage(miniMessage.deserialize("<blue>== <aqua>Zakończono inicjowanie instancji gier! <blue>=="));
                console.sendMessage(miniMessage.deserialize(""));

            }
        } // Arena loading

        Objects.requireNonNull(getServer().getPluginCommand("za")).setExecutor(new MainCommand());

        console.sendMessage(miniMessage.deserialize("<gold>==== <yellow>Poprawnie zinicjowano <green>ZombieApocalypse<yellow>! <gold>===="));

    }

    @Override public void onDisable() {
        this.saveDefaultConfig();
    }

}
