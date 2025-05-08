package IAmNotJustJess.destroyTheMonument.player;

import IAmNotJustJess.destroyTheMonument.player.classes.PlayerClass;
import IAmNotJustJess.destroyTheMonument.player.classes.effects.Effect;
import IAmNotJustJess.destroyTheMonument.player.classes.upgrades.Upgrade;
import IAmNotJustJess.destroyTheMonument.player.classes.upgrades.UpgradeTreeLocation;
import IAmNotJustJess.destroyTheMonument.player.classes.upgrades.UpgradeType;
import IAmNotJustJess.destroyTheMonument.team.TeamColour;
import IAmNotJustJess.destroyTheMonument.utility.UpgradeTreeLocationConverter;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerCharacter {

    private Player player;
    private int maxHealth;
    private int health;
    private float movementSpeed;
    private PlayerClass chosenPlayerClass;
    private TeamColour team;
    private ArrayList<Effect> effectList;
    private double dealDamageMultiplier = 1;
    private double takeDamageMultiplier = 1;
    private int flatDealDamageIncrease = 0;
    private int flatTakeDamageIncrease = 0;
    private int shards = 0;
    private ArrayList<Player> assistList;
    private Player lastAttacked;
    private int baseMaxHealth;
    private float baseMovementSpeed;

    public PlayerCharacter(Player player, PlayerClass chosenPlayerClass, TeamColour team, float movementSpeed) {
        this.player = player;
        this.chosenPlayerClass = chosenPlayerClass;
        this.team = team;
        this.maxHealth = chosenPlayerClass.HP;
        this.health = maxHealth;
        this.baseMaxHealth = maxHealth;
        this.movementSpeed = movementSpeed;
        this.baseMovementSpeed = movementSpeed;
        this.effectList = new ArrayList<>();
        this.assistList = new ArrayList<>();
        this.shards = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public PlayerClass getChosenClass() {
        return chosenPlayerClass;
    }

    public void setChosenClass(PlayerClass chosenPlayerClass) {
        this.chosenPlayerClass = chosenPlayerClass;
    }

    public TeamColour getTeam() {
        return team;
    }

    public void setTeam(TeamColour team) {
        this.team = team;
    }

    public ArrayList<Effect> getEffectList() {
        return effectList;
    }

    public void setEffectList(ArrayList<Effect> effectList) {
        this.effectList = effectList;
    }

    public int buyUpgrade(UpgradeTreeLocation location) {
        Upgrade upgrade = chosenPlayerClass.upgradeTree.getUpgrade(location);
        if(upgrade.getCurrentLevel() == upgrade.getMaxLevels()) return 1;
        if(shards < upgrade.shardPricesLevels.get(upgrade.getCurrentLevel() + 1)) return 2;

        shards -= upgrade.shardPricesLevels.get(upgrade.getCurrentLevel() + 1);
        upgrade.setCurrentLevel(upgrade.getCurrentLevel() + 1);

        switch(upgrade.getUpgradeAffection()) {
            case MAX_HP -> {
                switch(upgrade.getUpgradeType()) {
                    case FLAT -> {
                        maxHealth += upgrade.strengthLevels.get(upgrade.getCurrentLevel()).getFirst();
                    }
                    case PERCENTAGE -> {
                        maxHealth += (int) (upgrade.strengthLevels.get(upgrade.getCurrentLevel()).getFirst() * baseMaxHealth);
                    }
                }
            }
        }

        return 0;
    }

    public void onEnemyKill() { // dodac wiecej efektow, dodanie flat/percentage ma tez dodac do bazowego!

        for(int i = 0; i < 8; i++) {
            UpgradeTreeLocation location = UpgradeTreeLocationConverter.convertIntegerToLocation(i);
            Upgrade upgrade = chosenPlayerClass.upgradeTree.getUpgrade(location);
            if (upgrade.getCurrentLevel() == 0) continue;
            if (upgrade.getUpgradeType() != UpgradeType.STACKING_FLAT_PER_KILL) continue;

            upgrade.setStackCount(upgrade.getStackCount() + 1);

            double value = upgrade.strengthLevels.get(upgrade.getCurrentLevel()).get(upgrade.getStackCount());
            switch(upgrade.getUpgradeAffection()) {
                case MAX_HP -> {
                    maxHealth += (int) value;
                }
                case MOVEMENT_SPEED -> {
                    movementSpeed += (int) value;
                }
                case ULTIMATE_STRENGTH -> {
                    for(Integer integer : chosenPlayerClass.ultimateSkill.upgradeAffectingEffect.get(location)) {
                        chosenPlayerClass.ultimateSkill.effectList.get(integer).strength += value;
                    }
                }
                case ULTIMATE_LONGEVITY -> {
                    for(Integer integer : chosenPlayerClass.ultimateSkill.upgradeAffectingEffect.get(location)) {
                        chosenPlayerClass.ultimateSkill.effectList.get(integer).longevity += (int) value;
                    }
                }
                case ACTIVE_STRENGTH -> {
                    for(Integer integer : chosenPlayerClass.activeSkill.upgradeAffectingEffect.get(location)) {
                        chosenPlayerClass.activeSkill.effectList.get(integer).strength += value;
                    }
                }
                case ACTIVE_LONGEVITY -> {
                    for(Integer integer : chosenPlayerClass.activeSkill.upgradeAffectingEffect.get(location)) {
                        chosenPlayerClass.activeSkill.effectList.get(integer).longevity += (int) value;
                    }
                }
                case PASSIVE_STRENGTH -> {
                    for(Integer integer : chosenPlayerClass.passiveSkill.upgradeAffectingEffect.get(location)) {
                        chosenPlayerClass.passiveSkill.effectList.get(integer).strength += value;
                    }
                }
                case PASSIVE_LONGEVITY -> {
                    for(Integer integer : chosenPlayerClass.passiveSkill.upgradeAffectingEffect.get(location)) {
                        chosenPlayerClass.passiveSkill.effectList.get(integer).longevity += (int) value;
                    }
                }
                case MAIN_WEAPON_DAMAGE -> {
                    chosenPlayerClass.loadout.mainWeapon.damage += (int) value;
                }
                default -> {}
            }
        }
    }

    public void onDeath() { // edit dla percentage potrzebny :)

        for(int i = 0; i < 8; i++) {
            UpgradeTreeLocation location = UpgradeTreeLocationConverter.convertIntegerToLocation(i);
            Upgrade upgrade = chosenPlayerClass.upgradeTree.getUpgrade(location);
            if(upgrade.getCurrentLevel() == 0) continue;
            if(upgrade.getUpgradeType() != UpgradeType.STACKING_FLAT_PER_KILL) continue;

            for(int j = upgrade.getStackCount() - 1; j > 0; j--) {
                double value = upgrade.strengthLevels.get(upgrade.getCurrentLevel()).get(j);
                switch(upgrade.getUpgradeAffection()) {
                    case MAX_HP -> {
                        maxHealth -= (int) value;
                    }
                    case MOVEMENT_SPEED -> {
                        movementSpeed -= (int) value;
                    }
                    case ULTIMATE_STRENGTH -> {
                        for(Integer integer : chosenPlayerClass.ultimateSkill.upgradeAffectingEffect.get(location)) {
                            chosenPlayerClass.ultimateSkill.effectList.get(integer).strength -= value;
                        }
                    }
                    case ULTIMATE_LONGEVITY -> {
                        for(Integer integer : chosenPlayerClass.ultimateSkill.upgradeAffectingEffect.get(location)) {
                            chosenPlayerClass.ultimateSkill.effectList.get(integer).longevity -= (int) value;
                        }
                    }
                    case ACTIVE_STRENGTH -> {
                        for(Integer integer : chosenPlayerClass.activeSkill.upgradeAffectingEffect.get(location)) {
                            chosenPlayerClass.activeSkill.effectList.get(integer).strength -= value;
                        }
                    }
                    case ACTIVE_LONGEVITY -> {
                        for(Integer integer : chosenPlayerClass.activeSkill.upgradeAffectingEffect.get(location)) {
                            chosenPlayerClass.activeSkill.effectList.get(integer).longevity -= (int) value;
                        }
                    }
                    case PASSIVE_STRENGTH -> {
                        for(Integer integer : chosenPlayerClass.passiveSkill.upgradeAffectingEffect.get(location)) {
                            chosenPlayerClass.passiveSkill.effectList.get(integer).strength -= value;
                        }
                    }
                    case PASSIVE_LONGEVITY -> {
                        for(Integer integer : chosenPlayerClass.passiveSkill.upgradeAffectingEffect.get(location)) {
                            chosenPlayerClass.passiveSkill.effectList.get(integer).longevity -= (int) value;
                        }
                    }
                    default -> {}
                }
            }

            upgrade.setStackCount(0);
        }

    }

    public void readThroughEffectList() {

        for(Effect effect : this.getEffectList()) {

            if(effect.longevity == -1) continue;

            effect.longevity -= 1;

            if(effect.longevity % effect.tickEveryServerTicks == 0) {
                switch (effect.effectType) {
                    case HEAL_OVER_TIME_FLAT -> {
                        this.heal((int) effect.strength);
                    }
                    case HEAL_OVER_TIME_PERCENTAGE -> {
                        this.heal((int) effect.strength * this.maxHealth);
                    }
                    case DAMAGE_OVER_TIME_FLAT -> {
                        this.dealDamage((int) effect.strength);
                    }
                    case DAMAGE_OVER_TIME_PERCENTAGE -> {
                        this.dealDamage((int) effect.strength * this.maxHealth);
                    }
                    default -> {
                    }
                }
            }

            if(effect.longevity < 1) {
                this.getEffectList().remove(effect);
                this.checkForMultiplierChange();
            }

        }
    }

    public void checkForMultiplierChange() {

        this.dealDamageMultiplier = 1;
        this.takeDamageMultiplier = 1;
        this.flatDealDamageIncrease = 0;
        this.flatTakeDamageIncrease = 0;

        for(Effect effect : this.getEffectList()) {
            switch(effect.effectType) {
                case DAMAGE_INCREASE_FLAT -> {
                    this.flatDealDamageIncrease += (int) effect.strength;
                }
                case DAMAGE_VULNERABILITY_FLAT -> {
                    this.flatTakeDamageIncrease += (int) effect.strength;
                }
                case DAMAGE_INCREASE_MULTIPLIER -> {
                    this.dealDamageMultiplier *= effect.strength;
                }
                case DAMAGE_VULNERABILITY_MULTIPLIER -> {
                    this.takeDamageMultiplier *= effect.strength;
                }
                default -> {
                    break;
                }
            }
        }
    }

    public ArrayList<Player> getAssistList() {
        return assistList;
    }

    public void addAssist(Player player) {
        if(!this.assistList.contains(player)) this.assistList.add(player);
    }

    public void setAssistList(ArrayList<Player> assistList) {
        this.assistList = assistList;
    }

    public Player getLastAttacked() {
        return lastAttacked;
    }

    public void setLastAttacked(Player lastAttacked) {
        this.lastAttacked = lastAttacked;
    }

    public void dealDamage(int damageAmount) {
        damageAmount = (int) Math.round((damageAmount + this.flatTakeDamageIncrease) * this.takeDamageMultiplier);
        this.setHealth(this.getHealth() - damageAmount);
        if(this.getHealth() <= 0) this.kill();
    }

    public void heal(int healAmount) {
        this.setHealth(this.getHealth() + healAmount);
        if(this.getHealth() >= this.getMaxHealth()) this.setHealth(this.getMaxHealth());
    }

    public void kill() {

    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public double getDealDamageMultiplier() {
        return dealDamageMultiplier;
    }

    public void setDealDamageMultiplier(double dealDamageMultiplier) {
        this.dealDamageMultiplier = dealDamageMultiplier;
    }

    public int getFlatDealDamageIncrease() {
        return flatDealDamageIncrease;
    }

    public void setFlatDealDamageIncrease(int flatDealDamageIncrease) {
        this.flatDealDamageIncrease = flatDealDamageIncrease;
    }

    public int getShards() {
        return shards;
    }
}
