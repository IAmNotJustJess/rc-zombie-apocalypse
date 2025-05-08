package IAmNotJustJess.destroyTheMonument.team;

import java.util.HashMap;

public class TeamList {

    private static HashMap<TeamColour, Team> list = new HashMap<>();

    public static HashMap<TeamColour, Team> getList() {
        return list;
    }

}
