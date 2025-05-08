package IAmNotJustJess.destroyTheMonument.player.classes;

import IAmNotJustJess.destroyTheMonument.player.classes.skills.Skill;
import IAmNotJustJess.destroyTheMonument.player.classes.upgrades.UpgradeTree;

public class PlayerClass {

    public int HP;
    public float movementSpeed;
    public Skill passiveSkill;
    public Skill activeSkill;
    public Skill ultimateSkill;
    public Loadout loadout;
    public UpgradeTree upgradeTree;

    PlayerClass(int HP, float movementSpeed, Skill passiveSkill, Skill activeSkill, Skill ultimateSkill, Loadout loadout, UpgradeTree upgradeTree) {
        this.HP = HP;
        this.movementSpeed = movementSpeed;
        this.passiveSkill = passiveSkill;
        this.activeSkill = activeSkill;
        this.ultimateSkill = ultimateSkill;
        this.loadout = loadout;
        this.upgradeTree = upgradeTree;
    }

}
