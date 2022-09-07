package roulycraft.zombieapocalypse.managers;

import org.bukkit.Bukkit;
import roulycraft.zombieapocalypse.ZombieApocalypse;

public class GameManager {

    private final ZombieApocalypse plugin;
    public GameState gameState = GameState.LOBBY;

    public GameManager(ZombieApocalypse plugin) {
        this.plugin = plugin;

    }

    public void setGameState(GameState gameState) {

        if (this.gameState == GameState.ACTIVE && gameState == GameState.STARTING) return;
        if (this.gameState == GameState.RESTARTING && gameState == GameState.STARTING) return;
        if (this.gameState == gameState) return;

        this.gameState = gameState;

        switch(gameState) {
            case LOBBY:
                Bukkit.broadcastMessage("Lobby");
                break;
            case STARTING:
                Bukkit.broadcastMessage("Odliczańsko");
                break;
            case ACTIVE:
                Bukkit.broadcastMessage("Aktywna");
                break;
        }
    }

    public void cleanUp() {

    }
}
