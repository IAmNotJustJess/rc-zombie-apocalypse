package roulycraft.zombieapocalypse.zombie;

import org.bukkit.inventory.ItemStack;

public class ZombieInstance {
    private final String name;
    private String displayName;
    private Integer health;
    private Integer damage;
    private Float speed;
    private String special;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    public ZombieInstance(String name, String displayName, Integer health, Integer damage, Float speed, String special, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        this.name = name;
        this.displayName = displayName;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.special = special;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Integer getHealth() {
        return this.health;
    }

    public Integer getDamage() {
        return this.damage;
    }

    public Float getSpeed() {
        return this.speed;
    }

    public String getSpecial() {
        return this.special;
    }

    public ItemStack getHelmet() {
        return this.helmet;
    }

    public ItemStack getChestplate() {
        return this.chestplate;
    }

    public ItemStack getLeggings() {
        return this.leggings;
    }

    public ItemStack getBoots() {
        return this.boots;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public void setHelmet(ItemStack helmet) {
        this.helmet = helmet;
    }

    public void setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    public void setLeggings(ItemStack leggings) {
        this.leggings = leggings;
    }

    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }
}
