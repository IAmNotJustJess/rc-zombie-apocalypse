package IAmNotJustJess.destroyTheMonument.player.classes;

import java.util.HashMap;
import java.util.UUID;

public class PlayerClassList {
    private static final HashMap<UUID, PlayerClass> list = new HashMap<>();

    public static HashMap<UUID, PlayerClass> getList() {
        return list;
    }
}
