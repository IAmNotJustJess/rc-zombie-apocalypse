package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RangedInstance {
    private final Integer id;
    private String name = "Broń";
    private ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR, 1);
    private Integer level = 1;
    private Integer minDmg = 1;
    private Integer maxDmg = 1;
    private Integer projectileType = 0;
    private Float projectileSpeed = 4f;
    private Integer pellets = 1;
    private Float bulletSpread = 2.0f;
    private Integer spreadPercentage = 25;
    private Float delayBetweenShots = 0.2f;
    private Integer clipSize = 10;
    private Float reloadSpeed = 1.6f;
    private String reloadType = "clip";
    private String actionType = "fullAuto";

    public RangedInstance(Integer id) {
        this.id = id;
    }

    public RangedInstance(Integer id, String name, Integer level, Integer minDmg, Integer maxDmg, Integer projectileType, Float projectileSpeed, Integer pellets, Float bulletSpread, Integer spreadPercentage, Float delayBetweenShots, Integer clipSize, Float reloadSpeed, String reloadType, String actionType) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.projectileType = projectileType;
        this.projectileSpeed = projectileSpeed;
        this.pellets = pellets;
        this.bulletSpread = bulletSpread;
        this.spreadPercentage = spreadPercentage;
        this.delayBetweenShots = delayBetweenShots;
        this.clipSize = clipSize;
        this.reloadSpeed = reloadSpeed;
        this.reloadType = reloadType;
        this.actionType = actionType;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public Integer getLevel() {
        return this.level;
    }

    public Integer getMinDmg() {
        return this.minDmg;
    }

    public Integer getMaxDmg() {
        return this.maxDmg;
    }

    public Integer getProjectileType() {
        return this.projectileType;
    }

    public Integer getPellets() {
        return this.pellets;
    }

    public Float getDelayBetweenShots() {
        return delayBetweenShots;
    }

    public Integer getClipSize() {
        return clipSize;
    }

    public Float getReloadSpeed() {
        return reloadSpeed;
    }

    public String getReloadType() {
        return reloadType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setItem (ItemStack item) {
        this.item = item;
    }

    public void setLevel (Integer level) {
        this.level = level;
    }

    public void setMinDmg (Integer minDmg) {
        this.minDmg = minDmg;
    }

    public void setMaxDmg (Integer maxDmg) {
        this.maxDmg = maxDmg;
    }

    public void setProjectileType (Integer projectileType) {
        this.projectileType = projectileType;
    }

    public void setPellets (Integer pellets) {
        this.pellets = pellets;
    }

    public void setDelayBetweenShots (Float delayBetweenShots) {
        this.delayBetweenShots = delayBetweenShots;
    }

    public void setClipSize (Integer clipSize) {
        this.clipSize = clipSize;
    }

    public void setReloadSpeed(Float reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
    }

    public void setReloadType(String reloadType) {
        this.reloadType = reloadType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
