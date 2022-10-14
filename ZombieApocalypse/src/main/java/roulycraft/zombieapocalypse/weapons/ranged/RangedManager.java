package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.configuration.file.FileConfiguration;
import roulycraft.zombieapocalypse.ZombieApocalypse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RangedManager {
    private static RangedManager rangedManager;
    private static ZombieApocalypse plugin;
    private FileConfiguration rangedInstanceConfig = null;
    private final File rangedInstanceFile = new File(plugin.getDataFolder() + File.separator + "weapons" + File.separator + "ranged.yml");
    public final List<RangedInstance> rangedInstanceList = new ArrayList<>();

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }

    private RangedManager() {
    }

    public static RangedManager getManager() {
        if (rangedManager == null) {
            rangedManager = new RangedManager();
        }
        return rangedManager;
    }

    public void createDefaultRangedZombieInstance() {

        int nextFreeID = rangedInstanceList.size();

        for (RangedInstance checkIfExists : this.rangedInstanceList) {
            if (checkIfExists.getId().equals(nextFreeID)) {
                if (checkIfExists.getLevel().equals(1))
                    return;
            }
        }
    }

    public void createRangedZombieInstance(Integer id, String name, Integer level, Integer minDmg, Integer maxDmg, Integer projectileType, Float projectileSpeed, Integer pellets, Float bulletSpread, Integer spreadPercentage, Float delayBetweenShots, Integer clipSize, Float reloadSpeed, String reloadType, String actionType) {

        if (level < 0 || level > 3) {
            return;
        }

        for (RangedInstance checkIfExists : this.rangedInstanceList) {
            if (checkIfExists.getId().equals(id)) {
                if (checkIfExists.getLevel().equals(level))
                    return;
            }
        }

        RangedInstance rangedInstance = new RangedInstance(id, name, level, minDmg, maxDmg, projectileType, projectileSpeed, pellets, bulletSpread, spreadPercentage, delayBetweenShots, clipSize, reloadSpeed, reloadType, actionType);

        this.rangedInstanceList.add(rangedInstance);
    }

    public RangedInstance getRangedInstance(Integer id){
        for (RangedInstance instance : this.rangedInstanceList) {
            if (instance.getId().equals(id)) {
                return instance;
            }
        }

        return null;
    }
}
