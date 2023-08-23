package roulycraft.zombieapocalypse.bosses;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.game.GameManager;
import roulycraft.zombieapocalypse.game.MessageType;

import java.util.Vector;

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
        if(zombie.isDead()) {
            return;
        }
        String name = zombie.getCustomName();
        String abilityName = "";
        switch(abilityType) {
            case 1: {
                switch(abilityNo) {
                    case 1: {
                        abilityName = "Tarcza";
                        ItemStack shield = zombie.getEquipment().getItemInOffHand();
                        ItemStack glowingShield = shield.clone();
                        glowingShield.addEnchantment(Enchantment.ARROW_FIRE, 1);
                        zombie.getEquipment().setItemInOffHand(glowingShield, false);
                        zombie.getWorld().playSound(zombie.getLocation(), Sound.BLOCK_ANVIL_PLACE, SoundCategory.HOSTILE, 1.0f, 0.5f);
                        zombie.setMetadata("damageReduction", new FixedMetadataValue(plugin, 0.75));
                        new BukkitRunnable() {
                            @Override public void run() {
                                if(zombie.isDead()) {
                                    return;
                                }
                                zombie.getEquipment().setItemInOffHand(shield);
                                zombie.removeMetadata("damageReduction", plugin);
                                zombie.getWorld().playSound(zombie.getLocation(), Sound.BLOCK_ANVIL_DESTROY, SoundCategory.HOSTILE, 1.0f, 0.5f);
                            }
                        }.runTaskLater(plugin, 100L);
                        break;
                    }
                    case 2: {
                        abilityName = "Szarża";
                        zombie.setVelocity(zombie.getTarget().getLocation().toVector().subtract(zombie.getLocation().toVector()).normalize().multiply(3));
                        zombie.getWorld().playSound(zombie.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1.0f, 0.75f);
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
