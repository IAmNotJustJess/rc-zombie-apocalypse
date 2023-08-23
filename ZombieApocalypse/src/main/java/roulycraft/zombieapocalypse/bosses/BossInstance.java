package roulycraft.zombieapocalypse.bosses;

import org.bukkit.inventory.ItemStack;
import roulycraft.zombieapocalypse.zombie.ZombieInstance;

public class BossInstance extends ZombieInstance {
    protected ItemStack mainhand;
    protected ItemStack offhand;


    public BossInstance(String name, String displayName, Integer health, Integer damage, Float speed, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack mainhand, ItemStack offhand, Integer xpReward) {
        super(name, displayName, health, damage, speed, name, helmet, chestplate, leggings, boots, xpReward);
        this.boots = boots;
        this.mainhand = mainhand;
        this.offhand = offhand;
    }

    public ItemStack getMainhand() {
        return mainhand;
    }

    public void setMainhand(ItemStack mainhand) {
        this.mainhand = mainhand;
    }

    public ItemStack getOffhand() {
        return offhand;
    }

    public void setOffhand(ItemStack offhand) {
        this.offhand = offhand;
    }
}
