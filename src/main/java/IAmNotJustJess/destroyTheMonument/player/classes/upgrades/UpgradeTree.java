package IAmNotJustJess.destroyTheMonument.player.classes.upgrades;

import java.util.HashMap;

public class UpgradeTree {
    private HashMap<UpgradeTreeLocation, Upgrade> upgradeTree;

    public UpgradeTree() {
        this.upgradeTree = new HashMap<>();
    }

    public Upgrade getUpgrade(UpgradeTreeLocation location) {
        return this.upgradeTree.get(location);
    }

    public void setUpgrade(UpgradeTreeLocation location, Upgrade upgrade) {
        upgradeTree.put(location, upgrade);
    }
}
