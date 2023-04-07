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

    public PlayerInstance(Player player, String inArena, Boolean isAlive, String selectedRanged, Integer maxHP, Integer hp, Double speed, Double speedModifier, Double ammoModifier, Double damageModifier, Double spreadModifier) {

        this.player = player;
        this.inArena = inArena;
        this.isAlive = isAlive;
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

    public void setInArena(String inArena) {
        this.inArena = inArena;
    }

    public Boolean getAlive() {
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public String getSelectedRanged() {
        return selectedRanged;
    }

    public void setSelectedRanged(String selectedRanged) {
        this.selectedRanged = selectedRanged;
    }

    public Integer getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(Integer maxHP) {
        this.maxHP = maxHP;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getMaxHPModifier() {
        return maxHPModifier;
    }

    public void setMaxHPModifier(Double maxHPModifier) {
        this.maxHPModifier = maxHPModifier;
    }

    public Double getSpeedModifier() {
        return speedModifier;
    }

    public void setSpeedModifier(Double speedModifier) {
        this.speedModifier = speedModifier;
    }

    public Double getAmmoModifier() {
        return ammoModifier;
    }

    public void setAmmoModifier(Double ammoModifier) {
        this.ammoModifier = ammoModifier;
    }

    public Double getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(Double damageModifier) {
        this.damageModifier = damageModifier;
    }

    public Double getSpreadModifier() {
        return spreadModifier;
    }

    public void setSpreadModifier(Double spreadModifier) {
        this.spreadModifier = spreadModifier;
    }
}

