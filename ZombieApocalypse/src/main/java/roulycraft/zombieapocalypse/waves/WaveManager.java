package roulycraft.zombieapocalypse.waves;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.zombie.ZombieManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WaveManager {
    private static WaveManager waveManager;
    private static ZombieApocalypse plugin;
    private FileConfiguration waveInstanceConfig = null;
    private File waveInstanceFile = null;
    public final List<WaveInstance> waveInstanceList = new ArrayList<>();

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }

    private WaveManager() {
    }
    public static WaveManager getManager() {

        if (waveManager == null) {

            waveManager = new WaveManager();

        }

        return waveManager;

    }
    public WaveInstance getWaveInstance(Integer waveNumber) {

        int left = 0;
        int right = waveInstanceList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int foundWaveNo = waveInstanceList.get(mid).getWaveNumber();

            if (foundWaveNo == waveNumber) {

                return waveInstanceList.get(mid);
            }

            if(foundWaveNo < waveNumber) {
                left = mid + 1;
            }

            else {
                right = mid - 1;
            }
        }

        return null;

    }
    public void createInstance(Integer waveNumber) {
        if(getWaveInstance(waveNumber) != null && waveInstanceList.contains(getWaveInstance(waveNumber))) {
            return;
        }

        waveInstanceList.add(new WaveInstance(waveNumber));
        waveInstanceList.sort(Comparator.comparing(WaveInstance::getWaveNumber));

        saveWaveInstanceConfig(waveNumber);
    }
    public void reloadWaveInstanceConfig(Integer id) {

        if (waveInstanceFile == null) {
            waveInstanceFile = new File(plugin.getDataFolder() + File.separator + "instances" + File.separator + "waves", (id + ".yml"));
        }

        waveInstanceFile = new File(plugin.getDataFolder() + File.separator + "instances" + File.separator + "waves", (id + ".yml"));
        waveInstanceConfig = YamlConfiguration.loadConfiguration(waveInstanceFile);

    }
    public FileConfiguration getWaveInstanceConfig(Integer id) {

        if (waveInstanceConfig == null) {

            reloadWaveInstanceConfig(id);

        }

        return waveInstanceConfig;

    }

    public void saveWaveInstanceConfig(Integer id) {

        if (waveInstanceConfig == null || waveInstanceFile == null) {

            return;

        }

        for(String zombieName : WaveManager.getManager().getWaveInstance(id).getPreZombieList().keySet()) {
            waveInstanceConfig.set(zombieName, WaveManager.getManager().getWaveInstance(id).getPreZombieList().get(zombieName));
        }

        try {

            getWaveInstanceConfig(id).save(waveInstanceFile);

        }

        catch (IOException ex) {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            console.sendMessage("§4BŁĄD KRYTYCZNY §cNie można było zapisać konfiguracji instancji fali do §f" + waveInstanceFile);
            console.sendMessage(String.valueOf(ex));

        }

    }

    public boolean loadWaveInstanceConfig(Integer id) {

        reloadWaveInstanceConfig(id);

        if (!waveInstanceFile.exists()) {
            return false;
        }

        if (!waveInstanceList.contains(getWaveInstance(id))) {

            createInstance(id);

        }

        getWaveInstance(id).getZombieList().clear();
        for(String zombieName : waveInstanceConfig.getKeys(false)) {

            Double weight = waveInstanceConfig.getDouble(zombieName);

            if(!ZombieManager.getManager().getZombieInstance(zombieName).getName().equals(zombieName)) {
                Bukkit.getConsoleSender().sendMessage("§4BŁĄD §cNie można było wczytać wartości zombie z§f" + zombieName + " §cpliku fali §f" + waveInstanceFile + "§c! Takie zombie nie istnieje!");
                continue;
            }

            getWaveInstance(id).add(weight, zombieName);

        }

        return true;
    }

}
