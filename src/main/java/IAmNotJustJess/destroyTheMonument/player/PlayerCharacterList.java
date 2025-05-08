package IAmNotJustJess.destroyTheMonument.player;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerCharacterList {

    private static final HashMap<Player, PlayerCharacter> list = new HashMap<>();

    public static HashMap<Player, PlayerCharacter> getList() {
        return list;
    }

}
