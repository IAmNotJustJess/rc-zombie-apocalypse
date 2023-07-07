package roulycraft.zombieapocalypse.bosses;

import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import roulycraft.zombieapocalypse.ZombieApocalypse;

import java.util.HashMap;
import java.util.Map;

public class BossSpecial {
    private static ZombieApocalypse plugin;
    private static BossSpecial bossSpecial;

    public static BossSpecial getManager() {
        if (bossSpecial == null) {
            bossSpecial = new BossSpecial();
        }
        return bossSpecial;
    }
    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }
    public void bossAbility(Zombie zombie, Integer abilityType, Integer abilityNo, String instanceName) {
        switch(abilityType) {
            case 1: {
                switch(abilityType) {
                    case 1: {

                    }
                    case 2: {

                    }
                    case 3: {

                    }
                }
            }
        }
    }

}
