package roulycraft.zombieapocalypse.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
import java.util.HashMap;

public class GameInstance {

    // INSTANCE VALUES
    private final String name;
    private final List<UUID> players = new ArrayList<>();
    private String displayName;
    private final List<Location> playerSpawnLocs = new ArrayList<>();
    private final List<Location> zombieSpawnLocs = new ArrayList<>();
    public GameState gameState = GameState.LOBBY;
    private Integer wave;
    private Integer timeLeft;
    private Integer zombieKills;
    private Integer gameDifficulty;
    private Double zombieHPMultiplier;
    private Double zombieDMGMultiplier;
    private Double playerHPMultiplier;
    private Double playerDMGMultiplier;
    private Double playerAmmoMultiplier;
    private final HashMap<String, Boolean> selectedModifiers = new HashMap<String, Boolean>();

    public GameInstance(String name) {
        this.name = name;
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public List<Location> getPlayerSpawnLocs() {
        return this.playerSpawnLocs;
    }

    public List<Location> getZombieSpawnLocs() {
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
    public void setDisplayName(String s) {
        this.displayName = s;
    }

    public void setWave(Integer i) {
        this.wave = i;
    }

    public void setTimeLeft(Integer i) {
        this.timeLeft = i;
    }

    public void setZombieKills(Integer i) {
        this.zombieKills = i;
    }

    public void setGameDifficulty(Integer i) {
        this.gameDifficulty = i;
    }

    public void setZombieHPMultiplier(Double d) {
        this.zombieHPMultiplier = d;
    }

    public void setZombieDMGMultiplier(Double d) {
        this.zombieDMGMultiplier = d;
    }

    public void setPlayerHPMultiplier(Double d) {
        this.playerHPMultiplier = d;
    }

    public void setPlayerDMGMultiplier(Double d) {
        this.playerDMGMultiplier = d;
    }

    public void setPlayerAmmoMultiplier(Double d) {
        this.playerAmmoMultiplier = d;
    }

    public void setGameState(GameState gameState) {

        if (this.gameState == GameState.LOBBY && gameState != GameState.COUNTDOWN) return;
        if (this.gameState == GameState.COUNTDOWN && gameState != GameState.STARTING) return;
        if (this.gameState == GameState.STARTING && gameState != GameState.ACTIVE) return;
        if (this.gameState == GameState.ACTIVE && gameState != GameState.FINISHED) return;
        if (this.gameState == GameState.FINISHED && gameState != GameState.RESTARTING) return;
        if (this.gameState == GameState.RESTARTING && gameState != GameState.LOBBY) return;
        if (this.gameState == gameState) return;

        this.gameState = gameState;

        switch (gameState) {
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
}
