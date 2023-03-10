package roulycraft.zombieapocalypse.waves;

import roulycraft.zombieapocalypse.ZombieApocalypse;

public class WaveDefaultSettings {

    public static ZombieApocalypse plugin;

    public static void injectPlugin(ZombieApocalypse p) {

        plugin = p;

    }

    public static void loadDefaultSettings() {

        for(int i = 1; i <= 80; i++) {
            WaveManager.getManager().createInstance(i);
        }
        WaveInstance waveInstance;
        {
            waveInstance = WaveManager.getManager().getWaveInstance(1);
            waveInstance.add(50.0, "default0");
            waveInstance.add(1.0, "iron0");
            waveInstance.add(0.5, "gold0");
            waveInstance.add(0.0, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(2.0, "speedy0");
            waveInstance.add(1.0, "explosive0");
            waveInstance.add(0.0, "slowing0");
            waveInstance.add(0.0, "void0");
            waveInstance.add(0.0, "magma0");
            waveInstance.add(0.0, "default1");
            waveInstance.add(0.0, "iron1");
            waveInstance.add(0.0, "gold1");
            waveInstance.add(0.0, "diamond1");
            waveInstance.add(0.0, "emerald1");
            waveInstance.add(0.0, "ruby1");
            waveInstance.add(0.0, "obsidian1");
            waveInstance.add(0.0, "speedy1");
            waveInstance.add(0.0, "explosive1");
            waveInstance.add(0.0, "slowing1");
            waveInstance.add(0.0, "void1");
            waveInstance.add(0.0, "magma1");
            waveInstance.add(0.0, "default2");
            waveInstance.add(0.0, "iron2");
            waveInstance.add(0.0, "gold2");
            waveInstance.add(0.0, "diamond2");
            waveInstance.add(0.0, "emerald2");
            waveInstance.add(0.0, "ruby2");
            waveInstance.add(0.0, "obsidian2");
            waveInstance.add(0.0, "speedy2");
            waveInstance.add(0.0, "explosive2");
            waveInstance.add(0.0, "slowing2");
            waveInstance.add(0.0, "void2");
            waveInstance.add(0.0, "magma2");
            waveInstance.add(0.0, "default3");
            waveInstance.add(0.0, "iron3");
            waveInstance.add(0.0, "gold3");
            waveInstance.add(0.0, "diamond3");
            waveInstance.add(0.0, "emerald3");
            waveInstance.add(0.0, "ruby3");
            waveInstance.add(0.0, "obsidian3");
            waveInstance.add(0.0, "speedy3");
            waveInstance.add(0.0, "explosive3");
            waveInstance.add(0.0, "slowing3");
            waveInstance.add(0.0, "void3");
            waveInstance.add(0.0, "magma3");
        } // 1
        {
            waveInstance = WaveManager.getManager().getWaveInstance(1);
            waveInstance.add(50.0, "default0");
            waveInstance.add(1.5, "iron0");
            waveInstance.add(0.75, "gold0");
            waveInstance.add(0.0, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(2.5, "speedy0");
            waveInstance.add(1.5, "explosive0");
            waveInstance.add(0.0, "slowing0");
            waveInstance.add(0.0, "void0");
            waveInstance.add(0.0, "magma0");
            waveInstance.add(0.0, "default1");
            waveInstance.add(0.0, "iron1");
            waveInstance.add(0.0, "gold1");
            waveInstance.add(0.0, "diamond1");
            waveInstance.add(0.0, "emerald1");
            waveInstance.add(0.0, "ruby1");
            waveInstance.add(0.0, "obsidian1");
            waveInstance.add(0.0, "speedy1");
            waveInstance.add(0.0, "explosive1");
            waveInstance.add(0.0, "slowing1");
            waveInstance.add(0.0, "void1");
            waveInstance.add(0.0, "magma1");
            waveInstance.add(0.0, "default2");
            waveInstance.add(0.0, "iron2");
            waveInstance.add(0.0, "gold2");
            waveInstance.add(0.0, "diamond2");
            waveInstance.add(0.0, "emerald2");
            waveInstance.add(0.0, "ruby2");
            waveInstance.add(0.0, "obsidian2");
            waveInstance.add(0.0, "speedy2");
            waveInstance.add(0.0, "explosive2");
            waveInstance.add(0.0, "slowing2");
            waveInstance.add(0.0, "void2");
            waveInstance.add(0.0, "magma2");
            waveInstance.add(0.0, "default3");
            waveInstance.add(0.0, "iron3");
            waveInstance.add(0.0, "gold3");
            waveInstance.add(0.0, "diamond3");
            waveInstance.add(0.0, "emerald3");
            waveInstance.add(0.0, "ruby3");
            waveInstance.add(0.0, "obsidian3");
            waveInstance.add(0.0, "speedy3");
            waveInstance.add(0.0, "explosive3");
            waveInstance.add(0.0, "slowing3");
            waveInstance.add(0.0, "void3");
            waveInstance.add(0.0, "magma3");
        } // 2


        for(int i = 1; i <= 80; i++) {
            WaveManager.getManager().saveWaveInstanceConfig(i);
        }

    }
}
