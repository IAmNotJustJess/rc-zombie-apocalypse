package roulycraft.zombieapocalypse.waves;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class WaveInstance {
    private final int waveNumber;
    private double totalWeight;
    private final NavigableMap<String, Double> preZombieList = new TreeMap<>();
    private final NavigableMap<Double, String> zombieList = new TreeMap<>();

    public WaveInstance(int waveNumber) {

        this.waveNumber = waveNumber;
        this.totalWeight = 0.0;

    }
    public int getWaveNumber() {
        return waveNumber;
    }
    public NavigableMap<Double, String> getZombieList() {
        return zombieList;
    }
    public NavigableMap<String, Double> getPreZombieList() {
        return preZombieList;
    }

    public boolean add(Double weight, String zombieName) {
        if (weight <= 0.0) {
            return false;
        }

        totalWeight += weight;
        preZombieList.put(zombieName, weight);
        zombieList.put(totalWeight, zombieName);

        return true;
    }

    public boolean remove(String zombieName) {
        if(!zombieList.containsValue(zombieName)) {
            return false;
        }

        for(double i : zombieList.keySet()) {
            if (zombieList.get(i).equals(zombieName)) {
                totalWeight -= i;
                zombieList.remove(i, zombieName);
                return true;
            }
        }
        for(String i : preZombieList.keySet()) {
            if (preZombieList.containsKey(i)) {
                preZombieList.remove(i);
                return true;
            }
        }

        return false;
    }

    public String getRandomZombie() {

        double key = new Random().nextDouble() * totalWeight;

        return zombieList.higherEntry(key).getValue();

    }
}
