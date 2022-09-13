package roulycraft.zombieapocalypse.managers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.Bukkit;
import roulycraft.zombieapocalypse.ZombieApocalypse;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
import java.util.HashMap;

public class GameInstance {

    // INSTANCE VALUES
    private final ZombieApocalypse plugin;
    private final String name;
    private final String displayName;
    private final List<UUID> playerSpawnLocs = new ArrayList<UUID>();
    private final List<UUID> zombieSpawnLocs = new ArrayList<UUID>();
    public GameState gameState = GameState.LOBBY;
    private final Integer wave;
    private final Integer timeLeft;
    private final Integer zombieKills;
    private final Integer gameDifficulty;
    private final Double zombieHPMultiplier;
    private final Double zombieDMGMultiplier;
    private final Double playerHPMultiplier;
    private final Double playerDMGMultiplier;
    private final Double playerAmmoMultiplier;
    private final HashMap<String, Boolean> selectedModifiers = new HashMap<String, Boolean>();

    public GameInstance(ZombieApocalypse plugin, String name, String displayName, Integer wave, Integer timeLeft, Integer zombieKills, Integer gameDifficulty, Double zombieHPMultiplier, Double zombieDMGMultiplier, Double playerHPMultiplier, Double playerDMGMultiplier, Double playerAmmoMultiplier) {
        this.plugin = plugin;
        this.name = name;
        this.displayName = displayName;
        this.wave = wave;
        this.timeLeft = timeLeft;
        this.zombieKills = zombieKills;
        this.gameDifficulty = gameDifficulty;
        this.zombieHPMultiplier = zombieHPMultiplier;
        this.zombieDMGMultiplier = zombieDMGMultiplier;
        this.playerHPMultiplier = playerHPMultiplier;
        this.playerDMGMultiplier = playerDMGMultiplier;
        this.playerAmmoMultiplier = playerAmmoMultiplier;
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public List<UUID> getPlayerSpawnLocs() {
        return this.playerSpawnLocs;
    }

    public List<UUID> getZombieSpawnLocs() {
        return this.zombieSpawnLocs;
    }

    public String getConcretePlayerSpawnLocs(Integer i) {
        return String.valueOf(this.playerSpawnLocs.get(i));
    }

    public String getConcreteZombieSpawnLocs(Integer i) {
        return String.valueOf(this.zombieSpawnLocs.get(i));
    }

    public Integer getWave() {
        return this.wave;
    }

    public Integer getTimeLeft() {
        return this.timeLeft;
    }

    public Integer getZombieKills() {
        return this.zombieKills;
    }

    public Integer getGameDifficulty() {
        return this.gameDifficulty;
    }

    public Double getZombieHPMultiplier() {
        return zombieHPMultiplier;
    }

    public Double getZombieDMGMultiplier() {
        return zombieDMGMultiplier;
    }

    public Double getPlayerHPMultiplier() {
        return playerHPMultiplier;
    }

    public Double getPlayerDMGMultiplier() {
        return playerDMGMultiplier;
    }

    public Double getPlayerAmmoMultiplier() {
        return playerAmmoMultiplier;
    }

    public HashMap<String, Boolean> getSelectedModifiers() {
        return selectedModifiers;
    }

    public String getConcreteSelectedModifiers(String v) {
        return String.valueOf(this.selectedModifiers.get(v));
    }
    // SETTERS
    public void setGameState(GameState gameState) {

        if (this.gameState == GameState.LOBBY && gameState != GameState.COUNTDOWN) return;
        if (this.gameState == GameState.COUNTDOWN && gameState != GameState.STARTING) return;
        if (this.gameState == GameState.STARTING && gameState != GameState.ACTIVE) return;
        if (this.gameState == GameState.ACTIVE && gameState != GameState.FINISHED) return;
        if (this.gameState == GameState.FINISHED && gameState != GameState.RESTARTING) return;
        if (this.gameState == GameState.RESTARTING && gameState != GameState.LOBBY) return;
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
