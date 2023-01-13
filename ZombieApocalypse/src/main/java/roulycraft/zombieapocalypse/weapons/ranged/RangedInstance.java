package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RangedInstance {
    private final Integer id;
    private String name = "Broń";
    private ItemStack item = new ItemStack(Material.IRON_HORSE_ARMOR, 1);
    private Integer level = 0;
    private Integer minDmg = 1;
    private Integer maxDmg = 1;
    private Integer projectileType = 0; // 0 - Snowball, 1 - Egg, 2 - Arrow
    private Double projectileSpeed = 4.0;
    private Integer pellets = 1;
    private Double bulletSpread = 1.0;
    private Double bulletAdditiveSpread = 2.0;
    private Integer spreadPercentage = 25;
    private Double delayBetweenShots = 0.2;
    private Integer clipSize = 10;
    private Double reloadSpeed = 1.6;
    private String reloadType = "clip";
    private String actionType = "fullAuto";
    private Double actionDelay = 0.0;

    public RangedInstance(Integer id) {
        this.id = id;
    }

    public RangedInstance(Integer id, String name, Integer level, ItemStack item, Integer minDmg, Integer maxDmg, Integer projectileType, Double projectileSpeed, Integer pellets, Double bulletSpread, Double bulletAdditiveSpread, Integer spreadPercentage, Double delayBetweenShots, Integer clipSize, Double reloadSpeed, String reloadType, String actionType, Double actionDelay) {

        this.id = id;
        this.name = name;
        this.level = level;
        this.item = item;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.projectileType = projectileType;
        this.projectileSpeed = projectileSpeed;
        this.pellets = pellets;
        this.bulletSpread = bulletSpread;
        this.bulletAdditiveSpread = bulletAdditiveSpread;
        this.spreadPercentage = spreadPercentage;
        this.delayBetweenShots = delayBetweenShots;
        this.clipSize = clipSize;
        this.reloadSpeed = reloadSpeed;
        this.reloadType = reloadType;
        this.actionType = actionType;
        this.actionDelay = actionDelay;

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

    public Double getProjectileSpeed() {
        return projectileSpeed;
    }

    public Integer getPellets() {
        return this.pellets;
    }

    public Double getBulletSpread() {
        return this.bulletSpread;
    }

    public Double getBulletAdditiveSpread() {
        return this.bulletAdditiveSpread;
    }

    public Integer getSpreadPercentage() {
        return spreadPercentage;
    }

    public Double getDelayBetweenShots() {
        return delayBetweenShots;
    }

    public Integer getClipSize() {
        return clipSize;
    }

    public Double getReloadSpeed() {
        return reloadSpeed;
    }

    public String getReloadType() {
        return reloadType;
    }

    public String getActionType() {
        return actionType;
    }

    public Double getActionDelay() {
        return actionDelay;
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

    public void setProjectileSpeed (Double projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    public void setPellets (Integer pellets) {
        this.pellets = pellets;
    }

    public void setBulletSpread(Double bulletSpread) {
        this.bulletSpread = bulletSpread;
    }

    public void setBulletAdditiveSpread(Double bulletAdditiveSpread) {
        this.bulletAdditiveSpread = bulletAdditiveSpread;
    }

    public void setSpreadPercentage(Integer spreadPercentage) {
        this.spreadPercentage = spreadPercentage;
    }

    public void setDelayBetweenShots (Double delayBetweenShots) {
        this.delayBetweenShots = delayBetweenShots;
    }

    public void setClipSize (Integer clipSize) {
        this.clipSize = clipSize;
    }

    public void setReloadSpeed(Double reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
    }

    public void setReloadType(String reloadType) {
        this.reloadType = reloadType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setActionDelay(Double actionDelay) {
        this.actionDelay = actionDelay;
    }

}
