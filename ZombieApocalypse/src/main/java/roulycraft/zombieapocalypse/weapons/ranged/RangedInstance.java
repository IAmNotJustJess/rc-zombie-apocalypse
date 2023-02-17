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
    private Double additiveBulletSpread = 2.0;
    private Double spreadPercentage = 25.0;
    private Double delayBetweenShots = 0.2;
    private Integer clipSize = 10;
    private Double reloadSpeed = 1.6;
    private String reloadType = "clip";
    private String actionType = "fullAuto";
    private Double actionDelay = 0.0;
    private Integer actionSpecial = 0;
    private Integer shootingPatternType = 0;
    private Double shootingPatternOffset = 0.0;
    private String shootingSound = "";
    private String reloadingSound = "";
    private String actionSound = "";

    public RangedInstance(Integer id) {
        this.id = id;
    }

    public RangedInstance(
            Integer id,
            String name,
            Integer level,
            ItemStack item,
            Integer minDmg,
            Integer maxDmg,
            Integer projectileType,
            Double projectileSpeed,
            Integer pellets,
            Double bulletSpread,
            Double additiveBulletSpread,
            Double spreadPercentage,
            Double delayBetweenShots,
            Integer clipSize,
            Double reloadSpeed,
            String reloadType,
            String actionType,
            Double actionDelay,
            Integer actionSpecial,
            Integer shootingPatternType,
            Double shootingPatternOffset,
            String shootingSound,
            String reloadingSound,
            String actionSound
    ) {

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
        this.additiveBulletSpread = additiveBulletSpread;
        this.spreadPercentage = spreadPercentage;
        this.delayBetweenShots = delayBetweenShots;
        this.clipSize = clipSize;
        this.reloadSpeed = reloadSpeed;
        this.reloadType = reloadType;
        this.actionType = actionType;
        this.actionDelay = actionDelay;
        this.actionSpecial = actionSpecial;
        this.shootingPatternType = shootingPatternType;
        this.shootingPatternOffset = shootingPatternOffset;
        this.shootingSound = shootingSound;
        this.reloadingSound = reloadingSound;
        this.actionSound = actionSound;

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

    public Double getAdditiveBulletSpread() {
        return this.additiveBulletSpread;
    }

    public Double getSpreadPercentage() {
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

    public Integer getActionSpecial() {
        return actionSpecial;
    }

    public Integer getShootingPatternType() {
        return shootingPatternType;
    }

    public Double getShootingPatternOffset() {
        return shootingPatternOffset;
    }

    public String getShootingSound() {
        return shootingSound;
    }

    public String getReloadingSound() {
        return reloadingSound;
    }

    public String getActionSound() {
        return actionSound;
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

    public void setAdditiveBulletSpread(Double additiveBulletSpread) {
        this.additiveBulletSpread = additiveBulletSpread;
    }

    public void setSpreadPercentage(Double spreadPercentage) {
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

    public void setActionSpecial(Integer actionSpecial) {
        this.actionSpecial = actionSpecial;
    }

    public void setShootingPatternType(Integer shootingPatternType) {
        this.shootingPatternType = shootingPatternType;
    }

    public void setShootingPatternOffset(Double shootingPatternOffset) {
        this.shootingPatternOffset = shootingPatternOffset;
    }

    public void setShootingSound(String shootingSound) {
        this.shootingSound = shootingSound;
    }

    public void setReloadingSound(String reloadingSound) {
        this.reloadingSound = reloadingSound;
    }

    public void setActionSound(String actionSound) {
        this.actionSound = actionSound;
    }
}
