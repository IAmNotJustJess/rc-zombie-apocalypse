package IAmNotJustJess.destroyTheMonument.utility;

import IAmNotJustJess.destroyTheMonument.player.classes.upgrades.UpgradeTreeLocation;

import java.util.HashMap;

public class UpgradeTreeLocationConverter {

    private static final HashMap<Integer, UpgradeTreeLocation> mapOfUpgrades = new HashMap<>();
    private static final HashMap<UpgradeTreeLocation, Integer> mapOfIntegers = new HashMap<>();

    private static void addToMap() {
        if(!mapOfUpgrades.isEmpty()) return;
        mapOfUpgrades.put(0, UpgradeTreeLocation.BASIC_ONE);
        mapOfUpgrades.put(1, UpgradeTreeLocation.BASIC_TWO);
        mapOfUpgrades.put(2, UpgradeTreeLocation.SPECIAL_ONE);
        mapOfUpgrades.put(3, UpgradeTreeLocation.SPECIAL_TWO);
        mapOfUpgrades.put(4, UpgradeTreeLocation.SKILL_ONE);
        mapOfUpgrades.put(5, UpgradeTreeLocation.SKILL_TWO);
        mapOfUpgrades.put(6, UpgradeTreeLocation.ULTIMATE_ONE);
        mapOfUpgrades.put(7, UpgradeTreeLocation.ULTIMATE_TWO);
        mapOfIntegers.put(UpgradeTreeLocation.BASIC_ONE, 0);
        mapOfIntegers.put(UpgradeTreeLocation.BASIC_TWO, 1);
        mapOfIntegers.put(UpgradeTreeLocation.SPECIAL_ONE, 2);
        mapOfIntegers.put(UpgradeTreeLocation.SPECIAL_TWO, 3);
        mapOfIntegers.put(UpgradeTreeLocation.SKILL_ONE, 4);
        mapOfIntegers.put(UpgradeTreeLocation.SKILL_TWO, 5);
        mapOfIntegers.put(UpgradeTreeLocation.ULTIMATE_ONE, 6);
        mapOfIntegers.put(UpgradeTreeLocation.ULTIMATE_TWO, 7);
    }

    public static int convertLocationToInteger(UpgradeTreeLocation location) {
        addToMap();
        return mapOfIntegers.get(location);
    }

    public static UpgradeTreeLocation convertIntegerToLocation(Integer integer) {
        addToMap();
        return mapOfUpgrades.get(integer);
    }
}
