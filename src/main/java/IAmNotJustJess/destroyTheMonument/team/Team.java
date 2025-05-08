package IAmNotJustJess.destroyTheMonument.team;

import org.bukkit.Color;
import org.bukkit.Material;

public class Team {

    public String name;
    public TeamColour teamColour;
    public Material blockType;
    public Color armourColour;

    public Team(String name, TeamColour teamColour, Material blockType, Color armourColour) {
        this.name = name;
        this.teamColour = teamColour;
        this.blockType = blockType;
        this.armourColour = armourColour;
    }
}
