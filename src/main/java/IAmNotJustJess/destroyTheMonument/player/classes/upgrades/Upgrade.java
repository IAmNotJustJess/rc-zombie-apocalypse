package IAmNotJustJess.destroyTheMonument.player.classes.upgrades;

import java.util.ArrayList;
import java.util.List;

import IAmNotJustJess.destroyTheMonument.player.classes.effects.Effect;
import IAmNotJustJess.destroyTheMonument.utility.MiniMessageParser;
import org.checkerframework.checker.units.qual.A;

public class Upgrade {
    private String name;
    private String description;
    private int currentLevel;
    private int maxLevels;
    private UpgradeAffection upgradeAffection;
    private UpgradeType upgradeType;
    private int maxStacks;
    private int stackCount;
    public ArrayList<ArrayList<String>> descriptionTextReplacementList;
    public ArrayList<ArrayList<Double>> strengthLevels;
    public ArrayList<Integer> shardPricesLevels;
    public ArrayList<ArrayList<Effect>> effectsLevels;

    public String getName() {
        return MiniMessageParser.Deserialize(this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxLevels() {
        return maxLevels;
    }

    public void setMaxLevels(int maxLevels) {
        int helper = this.maxLevels;
        this.maxLevels = maxLevels;
        for(int i = helper; i > maxLevels; i--) {
            this.descriptionTextReplacementList.removeLast();
            this.strengthLevels.removeLast();
            this.shardPricesLevels.removeLast();
            this.effectsLevels.removeLast();
        }
        for(int i = helper; i < maxLevels; i++) {
            this.descriptionTextReplacementList.add(new ArrayList<>());
            this.strengthLevels.add(new ArrayList<>());
            this.shardPricesLevels.add(0);
            this.effectsLevels.add(new ArrayList<>());
        }
    }

    public List<String> getDescription() {
        String string = description;
        for(int i = 0; i < descriptionTextReplacementList.get(currentLevel).size(); i++) {
            string = string.replaceAll("<"+i+">", descriptionTextReplacementList.get(currentLevel).get(i));
        }
        return MiniMessageParser.DeserializeMultiline(string);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Upgrade(String name, String description, int maxLevels, UpgradeAffection upgradeAffection, UpgradeType upgradeType, int maxStacks) {
        this.name = name;
        this.description = description;
        this.maxLevels = maxLevels;
        this.currentLevel = 0;
        this.upgradeAffection = upgradeAffection;
        this.upgradeType = upgradeType;
        this.maxStacks = maxStacks;
        this.stackCount = 0;
        this.descriptionTextReplacementList = new ArrayList<>();
        this.strengthLevels = new ArrayList<>();
        this.shardPricesLevels = new ArrayList<>();
        this.effectsLevels = new ArrayList<>();
        for(int i = 0; i < maxLevels; i++) {
            this.descriptionTextReplacementList.add(new ArrayList<>());
            this.shardPricesLevels.add(0);
            this.strengthLevels.add(new ArrayList<>());
            this.effectsLevels.add(new ArrayList<>());
        }
    }

    public int getStackCount() {
        return stackCount;
    }

    public void setStackCount(int stackCount) {
        this.stackCount = stackCount;
    }

    public int getMaxStacks() {
        return maxStacks;
    }

    public void setMaxStacks(int maxStacks) {
        this.maxStacks = maxStacks;
    }

    public UpgradeAffection getUpgradeAffection() {
        return upgradeAffection;
    }

    public void setUpgradeAffection(UpgradeAffection upgradeAffection) {
        this.upgradeAffection = upgradeAffection;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public UpgradeType getUpgradeType() {
        return upgradeType;
    }

    public void setUpgradeType(UpgradeType upgradeType) {
        this.upgradeType = upgradeType;
    }
}
