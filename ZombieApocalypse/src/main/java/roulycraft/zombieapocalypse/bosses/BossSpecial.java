package roulycraft.zombieapocalypse.bosses;

import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.game.GameManager;
import roulycraft.zombieapocalypse.game.MessageType;

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
        String name = zombie.getCustomName();
        String abilityName = "";
        switch(abilityType) {
            case 1: {
                switch(abilityType) {
                    case 1: {
                        abilityName = "Tarcza";
                        break;
                    }
                    case 2: {
                        abilityName = "Szarża";
                        break;
                    }
                    case 3: {
                        abilityName = "Wirujący atak";
                        break;
                    }
                }
            }
        }
        GameManager.getManager().sendGameMessage(MessageType.BOSS_ATTACK, null, instanceName, new String[]{"", name, abilityName});
    }

}
