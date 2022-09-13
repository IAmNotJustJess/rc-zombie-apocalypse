package roulycraft.zombieapocalypse.managers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.UUID;

public class GameManager {

    private static GameManager gameManager;

    private final Map<UUID, Location> lastPlayerLocs = new HashMap<>();
    private final Map<UUID, ItemStack[]> lastPlayerInventories = new HashMap<>();
    private final Map<UUID, ItemStack[]> lastPlayerArmour = new HashMap<>();

    private final List<GameInstance> gameInstanceList = new ArrayList<GameInstance>();

    private GameManager() {}

    public GameManager getManager() {
        if (this.gameManager == null) {
            this.gameManager = new GameManager();
        }
        return this.gameManager;
    }

    public GameInstance getGameInstance(String name){
        for (GameInstance gameInstance : this.gameInstanceList) {
            if (gameInstance.getName().equals(name)) {
                return gameInstance;
            }
        }

        return null;
    }

    public void addPlayer(Player p, String name) {
        GameInstance gameInstance = this.getGameInstance(name);

        if (gameInstance == null) {
            p.sendMessage("§4BŁĄD! §cArena nie istnieje!");
            return;
        }

        if (this.isInGame(p)) {
            p.sendMessage("§4BŁĄD! §cJesteś już na arenie!");
            return;
        }

        gameInstance.getPlayers().add(p.getUniqueId());

        lastPlayerInventories.put(p.getUniqueId(), p.getInventory().getContents());
        lastPlayerArmour.put(p.getUniqueId(), p.getInventory().getArmorContents());

        p.getInventory().setArmorContents(null);
        p.getInventory().clear();

        lastPlayerLocs.put(p.getUniqueId(), p.getLocation());
        p.teleport(gameInstance.getRandomPlayerSpawnLoc());
    }

    public void removePlayer(Player p, String name) {
        GameInstance gameInstance = null;

        for (GameInstance gameInstance1 : this.gameInstanceList) {
            if (gameInstance1.getPlayers().contains(p.getUniqueId())) {
                gameInstance = gameInstance1;
            }
        }

        if (gameInstance == null) {
            p.sendMessage("§4BŁĄD! §cOperacja nieznana!");
            return;
        }

        gameInstance.getPlayers().remove(p.getUniqueId());

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.getInventory().setContents(lastPlayerInventories.get(p.getUniqueId()));
        p.getInventory().setArmorContents(lastPlayerArmour.get(p.getUniqueId()));

        lastPlayerInventories.remove(p.getUniqueId());
        lastPlayerArmour.remove(p.getUniqueId());

        p.teleport(lastPlayerLocs.get(p.getUniqueId()));

        lastPlayerLocs.remove(p.getUniqueId());

        p.setFireTicks(0);
    }

    public GameInstance createArena(Player p, String name) {

        GameInstance gameInstance = null;

        for (GameInstance gameInstance1 : this.gameInstanceList) {
            if (gameInstance1.getName().equals(name)) {
                p.sendMessage("§4BŁĄD! §cArena już istnieje!");
                break;
            }
        }

        gameInstance = new GameInstance(name);
        this.gameInstanceList.add(gameInstance);

        return gameInstance;
    }

    public Boolean isInGame(Player p) {
        for (GameInstance gameInstance : this.gameInstanceList) {
            if (gameInstance.getPlayers().contains(p.getUniqueId()));
                return true;
        }
        return false;
    }
}
