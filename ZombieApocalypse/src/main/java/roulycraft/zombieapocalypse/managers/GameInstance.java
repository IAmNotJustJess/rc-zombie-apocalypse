package roulycraft.zombieapocalypse.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class GameInstance {

    // INSTANCE VALUES
    private final String name;
    private final List<UUID> players = new ArrayList<>();
    private String displayName;
    private Location lobby;
    private List<Location> playerSpawnLocs = new ArrayList<>();
    private List<Location> zombieSpawnLocs = new ArrayList<>();
    public GameState gameState = GameState.LOBBY;
    private Integer wave;
    private Integer zombieKills;
    private Integer gameDifficulty;
    private Double zombieHPMultiplier;
    private Double zombieDMGMultiplier;
    private Double playerHPMultiplier;
    private Double playerDMGMultiplier;
    private Double playerAmmoMultiplier;
    private HashMap<String, Boolean> selectedModifiers = new HashMap<String, Boolean>();

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

    public Location getLobby() {
        return this.lobby;
    }
    public List<UUID> getPlayers() {
        return this.players;
    }

    public List<Location> getPlayerSpawnLocs() {
        return this.playerSpawnLocs;
    }

    public List<Location> getZombieSpawnLocs() {
        return this.zombieSpawnLocs;
    }

    public Location getConcretePlayerSpawnLoc(Integer i) {
        return this.playerSpawnLocs.get(i);
    }

    public Location getConcreteZombieSpawnLoc(Integer i) {
        return this.zombieSpawnLocs.get(i);
    }

    public Location getRandomPlayerSpawnLoc() {
        int rng = new Random().nextInt(this.playerSpawnLocs.size());
        return this.playerSpawnLocs.get(rng);
    }

    public Location getRandomZombieSpawnLoc() {
        int rng = new Random().nextInt(this.zombieSpawnLocs.size());
        return this.zombieSpawnLocs.get(rng);
    }


    public Integer getWave() {
        return this.wave;
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
        if (this.gameState != GameState.ACTIVE && this.gameState != GameState.FINISHED) {
            this.displayName = s;
        }
    }

    public void setLobby(Location l) {
        this.lobby = l;
    }

    public void setPlayerSpawnLocs(List<Location> list) {
        this.playerSpawnLocs = list;
    }

    public void setZombieSpawnLocs(List<Location> list) {
        this.zombieSpawnLocs = list;
    }

    public void setWave(Integer i) {
        if (this.gameState == GameState.ACTIVE) {
            this.wave = i;
        }
    }

    public void setZombieKills(Integer i) {
        if (this.gameState == GameState.ACTIVE) {
            this.zombieKills = i;
        }
    }

    public void setGameDifficulty(Integer i) {
        if (this.gameState == GameState.STARTING) {
            this.gameDifficulty = i;
        }
    }

    public void setZombieHPMultiplier(Double d) {
        if (this.gameState == GameState.ACTIVE) {
            this.zombieHPMultiplier = d;
        }
    }

    public void setZombieDMGMultiplier(Double d) {
        if (this.gameState == GameState.ACTIVE) {
            this.zombieDMGMultiplier = d;
        }
    }

    public void setPlayerHPMultiplier(Double d) {
        if (this.gameState == GameState.ACTIVE) {
            this.playerHPMultiplier = d;
        }
    }

    public void setPlayerDMGMultiplier(Double d) {
        if (this.gameState == GameState.ACTIVE) {
            this.playerDMGMultiplier = d;
        }
    }

    public void setPlayerAmmoMultiplier(Double d) {
        if (this.gameState == GameState.ACTIVE) {
            this.playerAmmoMultiplier = d;
        }
    }

    // A. SETTERS
    public boolean setGameState(GameState gameState) {

        if (this.gameState == GameState.LOBBY && gameState != GameState.COUNTDOWN) return false;
        if (this.gameState == GameState.COUNTDOWN && gameState != GameState.STARTING) return false;
        if (this.gameState == GameState.STARTING && gameState != GameState.ACTIVE) return false;
        if (this.gameState == GameState.ACTIVE && gameState != GameState.FINISHED) return false;
        if (this.gameState == GameState.FINISHED && gameState != GameState.RESTARTING) return false;
        if (this.gameState == GameState.RESTARTING && gameState != GameState.LOBBY) return false;
        if (this.gameState == gameState) return false;

        if(JavaPlugin.getProvidingPlugin(this.getClass()).getConfig().getBoolean("settings.logGameStateChange")) {
            Bukkit.getConsoleSender().sendMessage("§6INFO! §eStatus instancja areny §f"+this.getName()+" §ezostał zmieniony na: §f"+gameState+"§e!");
        }

        this.gameState = gameState;
        return true;
    }
}
