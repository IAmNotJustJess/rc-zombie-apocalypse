package roulycraft.zombieapocalypse.players;

import org.bukkit.entity.Player;

public class PlayerInstance {
    private final Player player;
    private String inArena;
    private Boolean isAlive;
    private String selectedRanged;
    private Integer maxHP;
    private Integer hp;
    private Double speed;
    private Double maxHPModifier;
    private Double speedModifier;
    private Double ammoModifier;
    private Double damageModifier;
    private Double spreadModifier;
    public PlayerInstance(Player player, String selectedRanged, Integer maxHP, Integer hp, Double speed, Double speedModifier, Double ammoModifier, Double damageModifier, Double spreadModifier) {

        this.player = player;
        this.selectedRanged = selectedRanged;
        this.maxHP = maxHP;
        this.hp = hp;
        this.speed = speed;
        this.speedModifier = speedModifier;
        this.ammoModifier = ammoModifier;
        this.damageModifier = damageModifier;
        this.spreadModifier = spreadModifier;

    }

    public Player getPlayer() {
        return player;
    }
    public String getInArena() {
        return inArena;
    }
    public Boolean getAlive() {
        return isAlive;
    }
    public String getSelectedRanged() {
        return selectedRanged;
    }
    public Integer getMaxHP() {
        return maxHP;
    }
    public Integer getHp() {
        return hp;
    }
    public Double getSpeed() {
        return speed;
    }
    public Double getMaxHPModifier() {
        return maxHPModifier;
    }
    public Double getSpeedModifier() {
        return speedModifier;
    }
    public Double getAmmoModifier() {
        return ammoModifier;
    }
    public Double getDamageModifier() {
        return damageModifier;
    }
    public Double getSpreadModifier() {
        return spreadModifier;
    }
    public void setInArena(String inArena) {
        this.inArena = inArena;
    }
    public void setAlive(Boolean alive) {
        isAlive = alive;
    }
    public void setSelectedRanged(String selectedRanged) {
        this.selectedRanged = selectedRanged;
    }
    public void setMaxHP(Integer maxHP) {
        this.maxHP = maxHP;
    }
    public void setHp(Integer hp) {
        this.hp = hp;
    }
    public void setSpeed(Double speed) {
        this.speed = speed;
    }
    public void setMaxHPModifier(Double maxHPModifier) {
        this.maxHPModifier = maxHPModifier;
    }
    public void setSpeedModifier(Double speedModifier) {
        this.speedModifier = speedModifier;
    }
    public void setAmmoModifier(Double ammoModifier) {
        this.ammoModifier = ammoModifier;
    }
    public void setDamageModifier(Double damageModifier) {
        this.damageModifier = damageModifier;
    }
    public void setSpreadModifier(Double spreadModifier) {
        this.spreadModifier = spreadModifier;
    }
}

