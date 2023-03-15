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
        } // 1
        {
            waveInstance = WaveManager.getManager().getWaveInstance(2);
            waveInstance.add(50.0, "default0");
            waveInstance.add(1.2, "iron0");
            waveInstance.add(0.7, "gold0");
            waveInstance.add(0.0, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(2.0, "speedy0");
            waveInstance.add(1.0, "explosive0");
            waveInstance.add(0.0, "slowing0");
            waveInstance.add(0.0, "void0");
            waveInstance.add(0.0, "magma0");
        } // 2
        {
            waveInstance = WaveManager.getManager().getWaveInstance(3);
            waveInstance.add(50.0, "default0");
            waveInstance.add(1.4, "iron0");
            waveInstance.add(0.8, "gold0");
            waveInstance.add(0.0, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(2.25, "speedy0");
            waveInstance.add(1.25, "explosive0");
            waveInstance.add(0.0, "slowing0");
            waveInstance.add(0.0, "void0");
            waveInstance.add(0.0, "magma0");
        } // 3
        {
            waveInstance = WaveManager.getManager().getWaveInstance(4);
            waveInstance.add(50.0, "default0");
            waveInstance.add(1.5, "iron0");
            waveInstance.add(1.0, "gold0");
            waveInstance.add(0.1, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(2.25, "speedy0");
            waveInstance.add(1.25, "explosive0");
            waveInstance.add(0.0, "slowing0");
            waveInstance.add(0.0, "void0");
            waveInstance.add(0.0, "magma0");
        } // 4
        {
            waveInstance = WaveManager.getManager().getWaveInstance(5);
            waveInstance.add(50.0, "default0");
            waveInstance.add(1.6, "iron0");
            waveInstance.add(1.1, "gold0");
            waveInstance.add(0.2, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(2.5, "speedy0");
            waveInstance.add(1.5, "explosive0");
            waveInstance.add(0.2, "slowing0");
            waveInstance.add(0.0, "void0");
            waveInstance.add(0.0, "magma0");
        } // 5
        {
            waveInstance = WaveManager.getManager().getWaveInstance(6);
            waveInstance.add(45.0, "default0");
            waveInstance.add(1.6, "iron0");
            waveInstance.add(1.1, "gold0");
            waveInstance.add(0.3, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(2.5, "speedy0");
            waveInstance.add(1.5, "explosive0");
            waveInstance.add(0.2, "slowing0");
            waveInstance.add(0.0, "void0");
            waveInstance.add(0.0, "magma0");
        } // 6
        {
            waveInstance = WaveManager.getManager().getWaveInstance(7);
            waveInstance.add(45.0, "default0");
            waveInstance.add(1.8, "iron0");
            waveInstance.add(1.5, "gold0");
            waveInstance.add(0.4, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(2.5, "speedy0");
            waveInstance.add(1.5, "explosive0");
            waveInstance.add(0.2, "slowing0");
            waveInstance.add(0.0, "void0");
            waveInstance.add(0.0, "magma0");
        } // 7
        {
            waveInstance = WaveManager.getManager().getWaveInstance(8);
            waveInstance.add(45.0, "default0");
            waveInstance.add(2.0, "iron0");
            waveInstance.add(1.5, "gold0");
            waveInstance.add(0.5, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(3.0, "speedy0");
            waveInstance.add(2.0, "explosive0");
            waveInstance.add(0.5, "slowing0");
            waveInstance.add(0.0, "void0");
            waveInstance.add(0.0, "magma0");
        } // 8
        {
            waveInstance = WaveManager.getManager().getWaveInstance(9);
            waveInstance.add(45.0, "default0");
            waveInstance.add(2.25, "iron0");
            waveInstance.add(1.75, "gold0");
            waveInstance.add(0.75, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(3.0, "speedy0");
            waveInstance.add(2.0, "explosive0");
            waveInstance.add(0.5, "slowing0");
            waveInstance.add(0.1, "void0");
            waveInstance.add(0.0, "magma0");
        } // 9
        {
            waveInstance = WaveManager.getManager().getWaveInstance(10);
            waveInstance.add(45.0, "default0");
            waveInstance.add(2.5, "iron0");
            waveInstance.add(2.0, "gold0");
            waveInstance.add(1.0, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(3.0, "speedy0");
            waveInstance.add(2.0, "explosive0");
            waveInstance.add(0.5, "slowing0");
            waveInstance.add(0.3, "void0");
            waveInstance.add(0.0, "magma0");
        } // 10
        {
            waveInstance = WaveManager.getManager().getWaveInstance(11);
            waveInstance.add(40.0, "default0");
            waveInstance.add(2.5, "iron0");
            waveInstance.add(2.0, "gold0");
            waveInstance.add(1.0, "diamond0");
            waveInstance.add(0.0, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(3.0, "speedy0");
            waveInstance.add(2.0, "explosive0");
            waveInstance.add(0.5, "slowing0");
            waveInstance.add(0.3, "void0");
            waveInstance.add(0.0, "magma0");
        } // 11
        {
            waveInstance = WaveManager.getManager().getWaveInstance(12);
            waveInstance.add(40.0, "default0");
            waveInstance.add(3.0, "iron0");
            waveInstance.add(2.5, "gold0");
            waveInstance.add(1.5, "diamond0");
            waveInstance.add(0.1, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(3.0, "speedy0");
            waveInstance.add(2.0, "explosive0");
            waveInstance.add(0.5, "slowing0");
            waveInstance.add(0.3, "void0");
            waveInstance.add(0.0, "magma0");
        } // 12
        {
            waveInstance = WaveManager.getManager().getWaveInstance(13);
            waveInstance.add(40.0, "default0");
            waveInstance.add(3.5, "iron0");
            waveInstance.add(2.5, "gold0");
            waveInstance.add(1.75, "diamond0");
            waveInstance.add(0.25, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(3.5, "speedy0");
            waveInstance.add(2.5, "explosive0");
            waveInstance.add(1.0, "slowing0");
            waveInstance.add(0.3, "void0");
            waveInstance.add(0.0, "magma0");
        } // 13
        {
            waveInstance = WaveManager.getManager().getWaveInstance(14);
            waveInstance.add(40.0, "default0");
            waveInstance.add(3.75, "iron0");
            waveInstance.add(2.75, "gold0");
            waveInstance.add(1.75, "diamond0");
            waveInstance.add(0.5, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(3.5, "speedy0");
            waveInstance.add(2.5, "explosive0");
            waveInstance.add(1.0, "slowing0");
            waveInstance.add(0.5, "void0");
            waveInstance.add(0.0, "magma0");
        } // 14
        {
            waveInstance = WaveManager.getManager().getWaveInstance(15);
            waveInstance.add(40.0, "default0");
            waveInstance.add(3.75, "iron0");
            waveInstance.add(2.75, "gold0");
            waveInstance.add(1.75, "diamond0");
            waveInstance.add(0.75, "emerald0");
            waveInstance.add(0.0, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(3.5, "speedy0");
            waveInstance.add(2.5, "explosive0");
            waveInstance.add(1.0, "slowing0");
            waveInstance.add(0.5, "void0");
            waveInstance.add(0.0, "magma0");
        } // 15
        {
            waveInstance = WaveManager.getManager().getWaveInstance(16);
            waveInstance.add(37.5, "default0");
            waveInstance.add(3.75, "iron0");
            waveInstance.add(2.75, "gold0");
            waveInstance.add(1.75, "diamond0");
            waveInstance.add(0.75, "emerald0");
            waveInstance.add(0.1, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(3.5, "speedy0");
            waveInstance.add(2.5, "explosive0");
            waveInstance.add(1.0, "slowing0");
            waveInstance.add(0.5, "void0");
            waveInstance.add(0.1, "magma0");
        } // 16
        {
            waveInstance = WaveManager.getManager().getWaveInstance(17);
            waveInstance.add(37.5, "default0");
            waveInstance.add(4.0, "iron0");
            waveInstance.add(3.0, "gold0");
            waveInstance.add(2.0, "diamond0");
            waveInstance.add(1.0, "emerald0");
            waveInstance.add(0.3, "ruby0");
            waveInstance.add(0.0, "obsidian0");
            waveInstance.add(3.5, "speedy0");
            waveInstance.add(2.5, "explosive0");
            waveInstance.add(1.0, "slowing0");
            waveInstance.add(0.5, "void0");
            waveInstance.add(0.3, "magma0");
        } // 17
        {
            waveInstance = WaveManager.getManager().getWaveInstance(18);
            waveInstance.add(37.5, "default0");
            waveInstance.add(4.5, "iron0");
            waveInstance.add(3.25, "gold0");
            waveInstance.add(2.25, "diamond0");
            waveInstance.add(1.25, "emerald0");
            waveInstance.add(0.5, "ruby0");
            waveInstance.add(0.25, "obsidian0");
            waveInstance.add(3.75, "speedy0");
            waveInstance.add(2.75, "explosive0");
            waveInstance.add(1.25, "slowing0");
            waveInstance.add(0.75, "void0");
            waveInstance.add(0.5, "magma0");
        } // 18
        {
            waveInstance = WaveManager.getManager().getWaveInstance(19);
            waveInstance.add(37.5, "default0");
            waveInstance.add(4.5, "iron0");
            waveInstance.add(3.5, "gold0");
            waveInstance.add(2.5, "diamond0");
            waveInstance.add(1.75, "emerald0");
            waveInstance.add(1.5, "ruby0");
            waveInstance.add(0.5, "obsidian0");
            waveInstance.add(4.0, "speedy0");
            waveInstance.add(3.0, "explosive0");
            waveInstance.add(2.0, "slowing0");
            waveInstance.add(1.5, "void0");
            waveInstance.add(1.0, "magma0");
        } // 19
        {
            waveInstance = WaveManager.getManager().getWaveInstance(20);
            waveInstance.add(37.5, "default0");
            waveInstance.add(4.5, "iron0");
            waveInstance.add(3.5, "gold0");
            waveInstance.add(2.5, "diamond0");
            waveInstance.add(2.0, "emerald0");
            waveInstance.add(1.75, "ruby0");
            waveInstance.add(0.75, "obsidian0");
            waveInstance.add(4.0, "speedy0");
            waveInstance.add(3.0, "explosive0");
            waveInstance.add(2.5, "slowing0");
            waveInstance.add(1.5, "void0");
            waveInstance.add(1.25, "magma0");
        } // 20
        {
            waveInstance = WaveManager.getManager().getWaveInstance(21);
            waveInstance.add(35.0, "default1");
            waveInstance.add(4.5, "iron0");
            waveInstance.add(3.5, "gold0");
            waveInstance.add(2.5, "diamond0");
            waveInstance.add(2.0, "emerald0");
            waveInstance.add(1.75, "ruby0");
            waveInstance.add(0.75, "obsidian0");
            waveInstance.add(4.0, "speedy0");
            waveInstance.add(3.0, "explosive0");
            waveInstance.add(2.5, "slowing0");
            waveInstance.add(1.5, "void0");
            waveInstance.add(1.25, "magma0");
        } // 21
        {
            waveInstance = WaveManager.getManager().getWaveInstance(22);
            waveInstance.add(35.0, "default1");
            waveInstance.add(5.5, "iron0");
            waveInstance.add(4.0, "gold0");
            waveInstance.add(2.75, "diamond0");
            waveInstance.add(2.25, "emerald0");
            waveInstance.add(1.75, "ruby0");
            waveInstance.add(1.0, "obsidian0");
            waveInstance.add(4.5, "speedy0");
            waveInstance.add(3.0, "explosive0");
            waveInstance.add(2.5, "slowing0");
            waveInstance.add(1.5, "void0");
            waveInstance.add(1.5, "magma0");
        } // 22
        {
            waveInstance = WaveManager.getManager().getWaveInstance(23);
            waveInstance.add(35.0, "default1");
            waveInstance.add(5.5, "iron1");
            waveInstance.add(4.0, "gold0");
            waveInstance.add(2.75, "diamond0");
            waveInstance.add(2.25, "emerald0");
            waveInstance.add(1.75, "ruby0");
            waveInstance.add(1.0, "obsidian0");
            waveInstance.add(4.5, "speedy0");
            waveInstance.add(3.0, "explosive0");
            waveInstance.add(2.5, "slowing0");
            waveInstance.add(1.5, "void0");
            waveInstance.add(1.5, "magma0");
        } // 23
        {
            waveInstance = WaveManager.getManager().getWaveInstance(24);
            waveInstance.add(35.0, "default1");
            waveInstance.add(6.0, "iron1");
            waveInstance.add(4.0, "gold0");
            waveInstance.add(2.75, "diamond0");
            waveInstance.add(2.25, "emerald0");
            waveInstance.add(1.75, "ruby0");
            waveInstance.add(1.0, "obsidian0");
            waveInstance.add(4.5, "speedy0");
            waveInstance.add(3.0, "explosive0");
            waveInstance.add(2.5, "slowing0");
            waveInstance.add(1.5, "void0");
            waveInstance.add(1.5, "magma0");
        } // 24
        {
            waveInstance = WaveManager.getManager().getWaveInstance(25);
            waveInstance.add(35.0, "default1");
            waveInstance.add(6.0, "iron1");
            waveInstance.add(4.5, "gold0");
            waveInstance.add(3.0, "diamond0");
            waveInstance.add(2.5, "emerald0");
            waveInstance.add(2.0, "ruby0");
            waveInstance.add(1.25, "obsidian0");
            waveInstance.add(4.25, "speedy0");
            waveInstance.add(3.0, "explosive0");
            waveInstance.add(2.5, "slowing0");
            waveInstance.add(1.75, "void0");
            waveInstance.add(1.5, "magma0");
        } // 25
        {
            waveInstance = WaveManager.getManager().getWaveInstance(26);
            waveInstance.add(35.0, "default1");
            waveInstance.add(6.0, "iron1");
            waveInstance.add(4.5, "gold1");
            waveInstance.add(3.0, "diamond0");
            waveInstance.add(2.5, "emerald0");
            waveInstance.add(2.0, "ruby0");
            waveInstance.add(1.25, "obsidian0");
            waveInstance.add(4.25, "speedy0");
            waveInstance.add(3.0, "explosive0");
            waveInstance.add(2.5, "slowing0");
            waveInstance.add(1.75, "void0");
            waveInstance.add(1.5, "magma0");
        } // 26
        {
            waveInstance = WaveManager.getManager().getWaveInstance(27);
            waveInstance.add(35.0, "default1");
            waveInstance.add(6.0, "iron1");
            waveInstance.add(4.5, "gold1");
            waveInstance.add(3.5, "diamond0");
            waveInstance.add(2.5, "emerald0");
            waveInstance.add(2.0, "ruby0");
            waveInstance.add(1.25, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.0, "explosive0");
            waveInstance.add(2.5, "slowing0");
            waveInstance.add(1.75, "void0");
            waveInstance.add(1.5, "magma0");
        } // 27
        {
            waveInstance = WaveManager.getManager().getWaveInstance(28);
            waveInstance.add(32.5, "default1");
            waveInstance.add(6.25, "iron1");
            waveInstance.add(4.75, "gold1");
            waveInstance.add(3.5, "diamond0");
            waveInstance.add(2.5, "emerald0");
            waveInstance.add(2.0, "ruby0");
            waveInstance.add(1.25, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.0, "explosive1");
            waveInstance.add(2.5, "slowing0");
            waveInstance.add(2.0, "void0");
            waveInstance.add(1.5, "magma0");
        } // 28
        {
            waveInstance = WaveManager.getManager().getWaveInstance(29);
            waveInstance.add(32.5, "default1");
            waveInstance.add(6.5, "iron1");
            waveInstance.add(5.0, "gold1");
            waveInstance.add(3.75, "diamond0");
            waveInstance.add(2.75, "emerald0");
            waveInstance.add(2.25, "ruby0");
            waveInstance.add(1.5, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.0, "explosive1");
            waveInstance.add(2.75, "slowing0");
            waveInstance.add(2.0, "void0");
            waveInstance.add(1.5, "magma0");
        } // 29
        {
            waveInstance = WaveManager.getManager().getWaveInstance(30);
            waveInstance.add(32.5, "default1");
            waveInstance.add(6.5, "iron1");
            waveInstance.add(5.0, "gold1");
            waveInstance.add(3.75, "diamond0");
            waveInstance.add(2.75, "emerald0");
            waveInstance.add(2.25, "ruby0");
            waveInstance.add(1.5, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.0, "explosive1");
            waveInstance.add(2.75, "slowing1");
            waveInstance.add(2.0, "void0");
            waveInstance.add(1.5, "magma0");
        } // 30
        {
            waveInstance = WaveManager.getManager().getWaveInstance(31);
            waveInstance.add(32.5, "default1");
            waveInstance.add(6.5, "iron1");
            waveInstance.add(5.0, "gold1");
            waveInstance.add(3.75, "diamond1");
            waveInstance.add(2.75, "emerald0");
            waveInstance.add(2.25, "ruby0");
            waveInstance.add(1.5, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.0, "explosive1");
            waveInstance.add(2.75, "slowing1");
            waveInstance.add(2.0, "void0");
            waveInstance.add(1.5, "magma0");
        } // 31
        {
            waveInstance = WaveManager.getManager().getWaveInstance(32);
            waveInstance.add(30.0, "default1");
            waveInstance.add(7.5, "iron1");
            waveInstance.add(5.5, "gold1");
            waveInstance.add(4.0, "diamond1");
            waveInstance.add(3.0, "emerald0");
            waveInstance.add(2.5, "ruby0");
            waveInstance.add(1.75, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.0, "explosive1");
            waveInstance.add(2.75, "slowing1");
            waveInstance.add(2.0, "void0");
            waveInstance.add(1.5, "magma0");
        } // 32
        {
            waveInstance = WaveManager.getManager().getWaveInstance(33);
            waveInstance.add(30.0, "default1");
            waveInstance.add(7.5, "iron1");
            waveInstance.add(5.5, "gold1");
            waveInstance.add(4.0, "diamond1");
            waveInstance.add(3.0, "emerald0");
            waveInstance.add(2.5, "ruby0");
            waveInstance.add(2.0, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.25, "explosive1");
            waveInstance.add(2.75, "slowing1");
            waveInstance.add(2.25, "void0");
            waveInstance.add(1.5, "magma0");
        } // 33
        {
            waveInstance = WaveManager.getManager().getWaveInstance(34);
            waveInstance.add(30.0, "default1");
            waveInstance.add(7.5, "iron1");
            waveInstance.add(5.5, "gold1");
            waveInstance.add(4.0, "diamond1");
            waveInstance.add(3.0, "emerald1");
            waveInstance.add(2.5, "ruby0");
            waveInstance.add(2.0, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.25, "explosive1");
            waveInstance.add(2.75, "slowing1");
            waveInstance.add(2.5, "void1");
            waveInstance.add(1.5, "magma0");
        } // 34
        {
            waveInstance = WaveManager.getManager().getWaveInstance(35);
            waveInstance.add(30.0, "default1");
            waveInstance.add(7.5, "iron1");
            waveInstance.add(6.0, "gold1");
            waveInstance.add(4.0, "diamond1");
            waveInstance.add(3.0, "emerald1");
            waveInstance.add(2.5, "ruby0");
            waveInstance.add(2.0, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.25, "explosive1");
            waveInstance.add(2.75, "slowing1");
            waveInstance.add(2.5, "void1");
            waveInstance.add(1.5, "magma0");
        } // 35
        {
            waveInstance = WaveManager.getManager().getWaveInstance(36);
            waveInstance.add(27.5, "default1");
            waveInstance.add(7.5, "iron1");
            waveInstance.add(6.0, "gold1");
            waveInstance.add(4.0, "diamond1");
            waveInstance.add(3.0, "emerald1");
            waveInstance.add(2.5, "ruby1");
            waveInstance.add(2.0, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.25, "explosive1");
            waveInstance.add(2.75, "slowing1");
            waveInstance.add(2.5, "void1");
            waveInstance.add(1.5, "magma0");
        } // 36
        {
            waveInstance = WaveManager.getManager().getWaveInstance(37);
            waveInstance.add(27.5, "default1");
            waveInstance.add(7.5, "iron1");
            waveInstance.add(6.0, "gold1");
            waveInstance.add(4.0, "diamond1");
            waveInstance.add(3.0, "emerald1");
            waveInstance.add(2.5, "ruby1");
            waveInstance.add(2.0, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.25, "explosive1");
            waveInstance.add(2.75, "slowing1");
            waveInstance.add(2.5, "void1");
            waveInstance.add(1.5, "magma1");
        } // 37
        {
            waveInstance = WaveManager.getManager().getWaveInstance(38);
            waveInstance.add(27.5, "default1");
            waveInstance.add(8.0, "iron1");
            waveInstance.add(6.0, "gold1");
            waveInstance.add(4.0, "diamond1");
            waveInstance.add(3.5, "emerald1");
            waveInstance.add(3.0, "ruby1");
            waveInstance.add(2.5, "obsidian0");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.5, "explosive1");
            waveInstance.add(3.0, "slowing1");
            waveInstance.add(3.0, "void1");
            waveInstance.add(2.5, "magma1");
        } // 38
        {
            waveInstance = WaveManager.getManager().getWaveInstance(39);
            waveInstance.add(27.5, "default1");
            waveInstance.add(8.0, "iron1");
            waveInstance.add(6.0, "gold1");
            waveInstance.add(4.0, "diamond1");
            waveInstance.add(3.5, "emerald1");
            waveInstance.add(3.0, "ruby1");
            waveInstance.add(2.5, "obsidian1");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.5, "explosive1");
            waveInstance.add(3.0, "slowing1");
            waveInstance.add(3.0, "void1");
            waveInstance.add(2.5, "magma1");
        } // 39
        {
            waveInstance = WaveManager.getManager().getWaveInstance(40);
            waveInstance.add(27.5, "default1");
            waveInstance.add(9.0, "iron1");
            waveInstance.add(7.0, "gold1");
            waveInstance.add(4.25, "diamond1");
            waveInstance.add(3.75, "emerald1");
            waveInstance.add(3.5, "ruby1");
            waveInstance.add(3.0, "obsidian1");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.5, "explosive1");
            waveInstance.add(4.0, "slowing1");
            waveInstance.add(4.0, "void1");
            waveInstance.add(4.5, "magma1");
        } // 40
        {
            waveInstance = WaveManager.getManager().getWaveInstance(41);
            waveInstance.add(27.5, "default2");
            waveInstance.add(9.0, "iron1");
            waveInstance.add(7.0, "gold1");
            waveInstance.add(4.25, "diamond1");
            waveInstance.add(3.75, "emerald1");
            waveInstance.add(3.5, "ruby1");
            waveInstance.add(3.0, "obsidian1");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.5, "explosive1");
            waveInstance.add(4.0, "slowing1");
            waveInstance.add(4.0, "void1");
            waveInstance.add(4.5, "magma1");
        } // 41
        {
            waveInstance = WaveManager.getManager().getWaveInstance(42);
            waveInstance.add(27.5, "default2");
            waveInstance.add(11.0, "iron1");
            waveInstance.add(8.0, "gold1");
            waveInstance.add(4.5, "diamond1");
            waveInstance.add(4.0, "emerald1");
            waveInstance.add(3.5, "ruby1");
            waveInstance.add(3.0, "obsidian1");
            waveInstance.add(4.0, "speedy1");
            waveInstance.add(3.0, "explosive1");
            waveInstance.add(4.0, "slowing1");
            waveInstance.add(4.0, "void1");
            waveInstance.add(5.0, "magma1");
        } // 42
        {
            waveInstance = WaveManager.getManager().getWaveInstance(43);
            waveInstance.add(27.5, "default2");
            waveInstance.add(11.0, "iron1");
            waveInstance.add(8.0, "gold1");
            waveInstance.add(4.5, "diamond1");
            waveInstance.add(4.0, "emerald1");
            waveInstance.add(3.5, "ruby1");
            waveInstance.add(3.0, "obsidian1");
            waveInstance.add(4.0, "speedy2");
            waveInstance.add(3.0, "explosive1");
            waveInstance.add(4.0, "slowing1");
            waveInstance.add(4.0, "void1");
            waveInstance.add(5.0, "magma1");
        } // 43
        {
            waveInstance = WaveManager.getManager().getWaveInstance(44);
            waveInstance.add(27.5, "default2");
            waveInstance.add(12.0, "iron1");
            waveInstance.add(9.0, "gold1");
            waveInstance.add(4.5, "diamond1");
            waveInstance.add(4.0, "emerald1");
            waveInstance.add(3.5, "ruby1");
            waveInstance.add(3.0, "obsidian1");
            waveInstance.add(4.0, "speedy2");
            waveInstance.add(3.0, "explosive2");
            waveInstance.add(4.0, "slowing1");
            waveInstance.add(4.0, "void1");
            waveInstance.add(5.0, "magma1");
        } // 44
        {
            waveInstance = WaveManager.getManager().getWaveInstance(45);
            waveInstance.add(27.5, "default2");
            waveInstance.add(12.0, "iron1");
            waveInstance.add(10.0, "gold1");
            waveInstance.add(5.0, "diamond1");
            waveInstance.add(4.5, "emerald1");
            waveInstance.add(4.0, "ruby1");
            waveInstance.add(3.5, "obsidian1");
            waveInstance.add(5.0, "speedy2");
            waveInstance.add(4.0, "explosive2");
            waveInstance.add(5.0, "slowing1");
            waveInstance.add(5.0, "void1");
            waveInstance.add(5.0, "magma1");
        } // 45
        {
            waveInstance = WaveManager.getManager().getWaveInstance(46);
            waveInstance.add(25.0, "default2");
            waveInstance.add(12.0, "iron2");
            waveInstance.add(10.0, "gold1");
            waveInstance.add(5.0, "diamond1");
            waveInstance.add(4.5, "emerald1");
            waveInstance.add(4.0, "ruby1");
            waveInstance.add(3.5, "obsidian1");
            waveInstance.add(5.0, "speedy2");
            waveInstance.add(4.0, "explosive2");
            waveInstance.add(5.0, "slowing1");
            waveInstance.add(5.0, "void1");
            waveInstance.add(5.0, "magma1");
        } // 46
        {
            waveInstance = WaveManager.getManager().getWaveInstance(47);
            waveInstance.add(25.0, "default2");
            waveInstance.add(14.0, "iron2");
            waveInstance.add(11.0, "gold1");
            waveInstance.add(6.0, "diamond1");
            waveInstance.add(5.0, "emerald1");
            waveInstance.add(4.0, "ruby1");
            waveInstance.add(3.75, "obsidian1");
            waveInstance.add(5.0, "speedy2");
            waveInstance.add(5.0, "explosive2");
            waveInstance.add(6.0, "slowing1");
            waveInstance.add(6.0, "void1");
            waveInstance.add(6.0, "magma1");
        } // 47
        {
            waveInstance = WaveManager.getManager().getWaveInstance(48);
            waveInstance.add(25.0, "default2");
            waveInstance.add(14.0, "iron2");
            waveInstance.add(11.0, "gold2");
            waveInstance.add(6.0, "diamond1");
            waveInstance.add(5.0, "emerald1");
            waveInstance.add(4.0, "ruby1");
            waveInstance.add(3.75, "obsidian1");
            waveInstance.add(5.0, "speedy2");
            waveInstance.add(5.0, "explosive2");
            waveInstance.add(6.0, "slowing1");
            waveInstance.add(6.0, "void1");
            waveInstance.add(6.0, "magma1");
        } // 48
        {
            waveInstance = WaveManager.getManager().getWaveInstance(49);
            waveInstance.add(25.0, "default2");
            waveInstance.add(14.0, "iron2");
            waveInstance.add(11.0, "gold2");
            waveInstance.add(6.0, "diamond2");
            waveInstance.add(5.0, "emerald1");
            waveInstance.add(4.0, "ruby1");
            waveInstance.add(3.75, "obsidian1");
            waveInstance.add(5.0, "speedy2");
            waveInstance.add(5.0, "explosive2");
            waveInstance.add(6.0, "slowing2");
            waveInstance.add(6.0, "void1");
            waveInstance.add(6.0, "magma1");
        } // 49
        {
            waveInstance = WaveManager.getManager().getWaveInstance(50);
            waveInstance.add(25.0, "default2");
            waveInstance.add(15.0, "iron2");
            waveInstance.add(12.0, "gold2");
            waveInstance.add(7.0, "diamond2");
            waveInstance.add(5.0, "emerald1");
            waveInstance.add(4.5, "ruby1");
            waveInstance.add(4.0, "obsidian1");
            waveInstance.add(5.0, "speedy2");
            waveInstance.add(5.0, "explosive2");
            waveInstance.add(6.0, "slowing2");
            waveInstance.add(6.0, "void1");
            waveInstance.add(6.0, "magma1");
        } // 50
        {
            waveInstance = WaveManager.getManager().getWaveInstance(51);
            waveInstance.add(25.0, "default2");
            waveInstance.add(15.0, "iron2");
            waveInstance.add(12.0, "gold2");
            waveInstance.add(7.0, "diamond2");
            waveInstance.add(5.0, "emerald1");
            waveInstance.add(4.5, "ruby1");
            waveInstance.add(4.0, "obsidian1");
            waveInstance.add(5.0, "speedy2");
            waveInstance.add(5.0, "explosive2");
            waveInstance.add(6.0, "slowing2");
            waveInstance.add(6.0, "void2");
            waveInstance.add(6.0, "magma1");
        } // 51
        {
            waveInstance = WaveManager.getManager().getWaveInstance(52);
            waveInstance.add(25.0, "default2");
            waveInstance.add(15.0, "iron2");
            waveInstance.add(12.0, "gold2");
            waveInstance.add(7.0, "diamond2");
            waveInstance.add(5.5, "emerald2");
            waveInstance.add(4.5, "ruby1");
            waveInstance.add(4.0, "obsidian1");
            waveInstance.add(5.0, "speedy2");
            waveInstance.add(5.0, "explosive2");
            waveInstance.add(6.0, "slowing2");
            waveInstance.add(6.0, "void2");
            waveInstance.add(6.0, "magma1");
        } // 52
        {
            waveInstance = WaveManager.getManager().getWaveInstance(53);
            waveInstance.add(25.0, "default2");
            waveInstance.add(15.0, "iron2");
            waveInstance.add(12.0, "gold2");
            waveInstance.add(7.0, "diamond2");
            waveInstance.add(6.0, "emerald2");
            waveInstance.add(5.0, "ruby1");
            waveInstance.add(4.0, "obsidian1");
            waveInstance.add(4.5, "speedy2");
            waveInstance.add(5.0, "explosive2");
            waveInstance.add(6.0, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.0, "magma1");
        } // 53
        {
            waveInstance = WaveManager.getManager().getWaveInstance(54);
            waveInstance.add(25.0, "default2");
            waveInstance.add(15.0, "iron2");
            waveInstance.add(12.0, "gold2");
            waveInstance.add(7.0, "diamond2");
            waveInstance.add(6.0, "emerald2");
            waveInstance.add(5.0, "ruby2");
            waveInstance.add(4.0, "obsidian1");
            waveInstance.add(4.5, "speedy2");
            waveInstance.add(5.0, "explosive2");
            waveInstance.add(6.0, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.0, "magma1");
        } // 54
        {
            waveInstance = WaveManager.getManager().getWaveInstance(55);
            waveInstance.add(25.0, "default2");
            waveInstance.add(16.0, "iron2");
            waveInstance.add(13.0, "gold2");
            waveInstance.add(9.0, "diamond2");
            waveInstance.add(7.0, "emerald2");
            waveInstance.add(6.0, "ruby2");
            waveInstance.add(5.0, "obsidian1");
            waveInstance.add(3.5, "speedy2");
            waveInstance.add(3.5, "explosive2");
            waveInstance.add(6.0, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.0, "magma1");
        } // 55
        {
            waveInstance = WaveManager.getManager().getWaveInstance(56);
            waveInstance.add(23.0, "default2");
            waveInstance.add(16.0, "iron2");
            waveInstance.add(13.0, "gold2");
            waveInstance.add(9.0, "diamond2");
            waveInstance.add(7.0, "emerald2");
            waveInstance.add(6.0, "ruby2");
            waveInstance.add(5.0, "obsidian1");
            waveInstance.add(3.5, "speedy2");
            waveInstance.add(3.5, "explosive2");
            waveInstance.add(6.0, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.0, "magma1");
        } // 56
        {
            waveInstance = WaveManager.getManager().getWaveInstance(57);
            waveInstance.add(23.0, "default2");
            waveInstance.add(16.0, "iron2");
            waveInstance.add(13.0, "gold2");
            waveInstance.add(9.0, "diamond2");
            waveInstance.add(7.0, "emerald2");
            waveInstance.add(6.0, "ruby2");
            waveInstance.add(5.0, "obsidian1");
            waveInstance.add(3.5, "speedy2");
            waveInstance.add(3.5, "explosive2");
            waveInstance.add(6.0, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.0, "magma2");
        } // 57
        {
            waveInstance = WaveManager.getManager().getWaveInstance(58);
            waveInstance.add(23.0, "default2");
            waveInstance.add(16.0, "iron2");
            waveInstance.add(13.0, "gold2");
            waveInstance.add(10.0, "diamond2");
            waveInstance.add(9.0, "emerald2");
            waveInstance.add(7.0, "ruby2");
            waveInstance.add(5.0, "obsidian1");
            waveInstance.add(3.5, "speedy2");
            waveInstance.add(3.5, "explosive2");
            waveInstance.add(6.5, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.5, "magma2");
        } // 58
        {
            waveInstance = WaveManager.getManager().getWaveInstance(59);
            waveInstance.add(23.0, "default2");
            waveInstance.add(16.0, "iron2");
            waveInstance.add(13.0, "gold2");
            waveInstance.add(10.0, "diamond2");
            waveInstance.add(9.0, "emerald2");
            waveInstance.add(7.0, "ruby2");
            waveInstance.add(5.0, "obsidian2");
            waveInstance.add(3.5, "speedy2");
            waveInstance.add(3.5, "explosive2");
            waveInstance.add(6.5, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.5, "magma2");
        } // 59
        {
            waveInstance = WaveManager.getManager().getWaveInstance(60);
            waveInstance.add(23.0, "default2");
            waveInstance.add(18.0, "iron2");
            waveInstance.add(15.0, "gold2");
            waveInstance.add(12.0, "diamond2");
            waveInstance.add(10.0, "emerald2");
            waveInstance.add(8.0, "ruby2");
            waveInstance.add(5.0, "obsidian2");
            waveInstance.add(3.0, "speedy2");
            waveInstance.add(3.0, "explosive2");
            waveInstance.add(6.5, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.5, "magma2");
        } // 60
        {
            waveInstance = WaveManager.getManager().getWaveInstance(61);
            waveInstance.add(20.0, "default3");
            waveInstance.add(18.0, "iron2");
            waveInstance.add(15.0, "gold2");
            waveInstance.add(12.0, "diamond2");
            waveInstance.add(10.0, "emerald2");
            waveInstance.add(8.0, "ruby2");
            waveInstance.add(5.0, "obsidian2");
            waveInstance.add(3.0, "speedy2");
            waveInstance.add(3.0, "explosive2");
            waveInstance.add(6.5, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.5, "magma2");
        } // 61
        {
            waveInstance = WaveManager.getManager().getWaveInstance(62);
            waveInstance.add(20.0, "default3");
            waveInstance.add(20.0, "iron2");
            waveInstance.add(17.5, "gold2");
            waveInstance.add(15.0, "diamond2");
            waveInstance.add(12.5, "emerald2");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(3.0, "speedy2");
            waveInstance.add(3.0, "explosive2");
            waveInstance.add(6.5, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.5, "magma2");
        } // 62
        {
            waveInstance = WaveManager.getManager().getWaveInstance(63);
            waveInstance.add(15.0, "default3");
            waveInstance.add(20.0, "iron2");
            waveInstance.add(17.5, "gold2");
            waveInstance.add(15.0, "diamond2");
            waveInstance.add(12.5, "emerald2");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(3.0, "speedy3");
            waveInstance.add(3.0, "explosive3");
            waveInstance.add(6.5, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.5, "magma2");
        } // 63
        {
            waveInstance = WaveManager.getManager().getWaveInstance(64);
            waveInstance.add(10.0, "default3");
            waveInstance.add(18.0, "iron3");
            waveInstance.add(17.5, "gold2");
            waveInstance.add(15.0, "diamond2");
            waveInstance.add(12.5, "emerald2");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(3.0, "speedy3");
            waveInstance.add(3.0, "explosive3");
            waveInstance.add(6.5, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.5, "magma2");
        } // 64
        {
            waveInstance = WaveManager.getManager().getWaveInstance(65);
            waveInstance.add(5.0, "default3");
            waveInstance.add(18.0, "iron3");
            waveInstance.add(17.5, "gold2");
            waveInstance.add(15.0, "diamond2");
            waveInstance.add(12.5, "emerald2");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(3.0, "speedy3");
            waveInstance.add(3.0, "explosive3");
            waveInstance.add(6.5, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.5, "magma2");
        } // 65
        {
            waveInstance = WaveManager.getManager().getWaveInstance(66);
            waveInstance.add(15.0, "iron3");
            waveInstance.add(17.5, "gold3");
            waveInstance.add(15.0, "diamond2");
            waveInstance.add(12.5, "emerald2");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(2.5, "speedy3");
            waveInstance.add(2.5, "explosive3");
            waveInstance.add(6.5, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.5, "magma2");
        } // 66
        {
            waveInstance = WaveManager.getManager().getWaveInstance(67);
            waveInstance.add(12.5, "iron3");
            waveInstance.add(17.5, "gold3");
            waveInstance.add(15.0, "diamond2");
            waveInstance.add(12.5, "emerald2");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(2.0, "speedy3");
            waveInstance.add(2.0, "explosive3");
            waveInstance.add(6.5, "slowing2");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.5, "magma2");
        } // 67
        {
            waveInstance = WaveManager.getManager().getWaveInstance(68);
            waveInstance.add(7.5, "iron3");
            waveInstance.add(17.5, "gold3");
            waveInstance.add(15.0, "diamond2");
            waveInstance.add(12.5, "emerald2");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(1.0, "speedy3");
            waveInstance.add(1.0, "explosive3");
            waveInstance.add(6.5, "slowing3");
            waveInstance.add(6.5, "void2");
            waveInstance.add(6.5, "magma2");
        } // 68
        {
            waveInstance = WaveManager.getManager().getWaveInstance(69);
            waveInstance.add(5.5, "iron3");
            waveInstance.add(17.5, "gold3");
            waveInstance.add(15.0, "diamond2");
            waveInstance.add(12.5, "emerald2");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(1.0, "speedy3");
            waveInstance.add(1.0, "explosive3");
            waveInstance.add(6.5, "slowing3");
            waveInstance.add(6.5, "void3");
            waveInstance.add(6.5, "magma2");
        } // 69
        {
            waveInstance = WaveManager.getManager().getWaveInstance(70);
            waveInstance.add(17.5, "gold3");
            waveInstance.add(15.0, "diamond3");
            waveInstance.add(12.5, "emerald2");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(1.0, "speedy3");
            waveInstance.add(1.0, "explosive3");
            waveInstance.add(6.5, "slowing3");
            waveInstance.add(6.5, "void3");
            waveInstance.add(6.5, "magma2");
        } // 70
        {
            waveInstance = WaveManager.getManager().getWaveInstance(71);
            waveInstance.add(17.5, "gold3");
            waveInstance.add(15.0, "diamond3");
            waveInstance.add(12.5, "emerald2");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(5.0, "slowing3");
            waveInstance.add(5.0, "void3");
            waveInstance.add(6.0, "magma2");
        } // 71
        {
            waveInstance = WaveManager.getManager().getWaveInstance(72);
            waveInstance.add(10.0, "gold3");
            waveInstance.add(15.0, "diamond3");
            waveInstance.add(12.5, "emerald3");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(5.0, "slowing3");
            waveInstance.add(5.0, "void3");
            waveInstance.add(6.0, "magma2");
        } // 72
        {
            waveInstance = WaveManager.getManager().getWaveInstance(73);
            waveInstance.add(5.0, "gold3");
            waveInstance.add(15.0, "diamond3");
            waveInstance.add(12.5, "emerald3");
            waveInstance.add(10.0, "ruby2");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(5.0, "slowing3");
            waveInstance.add(5.0, "void3");
            waveInstance.add(6.0, "magma2");
        } // 73
        {
            waveInstance = WaveManager.getManager().getWaveInstance(74);
            waveInstance.add(15.0, "diamond3");
            waveInstance.add(12.5, "emerald3");
            waveInstance.add(10.0, "ruby3");
            waveInstance.add(7.0, "obsidian2");
            waveInstance.add(5.0, "slowing3");
            waveInstance.add(5.0, "void3");
            waveInstance.add(6.0, "magma3");
        } // 74
        {
            waveInstance = WaveManager.getManager().getWaveInstance(75);
            waveInstance.add(10.0, "diamond3");
            waveInstance.add(12.5, "emerald3");
            waveInstance.add(10.0, "ruby3");
            waveInstance.add(7.5, "obsidian3");
            waveInstance.add(3.0, "slowing3");
            waveInstance.add(3.0, "void3");
            waveInstance.add(5.0, "magma3");
        } // 75
        {
            waveInstance = WaveManager.getManager().getWaveInstance(76);
            waveInstance.add(8.0, "diamond3");
            waveInstance.add(11.5, "emerald3");
            waveInstance.add(10.0, "ruby3");
            waveInstance.add(7.5, "obsidian3");
            waveInstance.add(3.0, "slowing3");
            waveInstance.add(3.0, "void3");
            waveInstance.add(3.0, "magma3");
        } // 76
        {
            waveInstance = WaveManager.getManager().getWaveInstance(77);
            waveInstance.add(6.0, "diamond3");
            waveInstance.add(10.0, "emerald3");
            waveInstance.add(12.0, "ruby3");
            waveInstance.add(10.0, "obsidian3");
            waveInstance.add(2.0, "slowing3");
            waveInstance.add(2.0, "void3");
            waveInstance.add(2.0, "magma3");
        } // 77
        {
            waveInstance = WaveManager.getManager().getWaveInstance(78);
            waveInstance.add(3.0, "diamond3");
            waveInstance.add(10.0, "emerald3");
            waveInstance.add(12.0, "ruby3");
            waveInstance.add(10.0, "obsidian3");
            waveInstance.add(2.0, "slowing3");
            waveInstance.add(2.0, "void3");
            waveInstance.add(2.0, "magma3");
        } // 78
        {
            waveInstance = WaveManager.getManager().getWaveInstance(79);
            waveInstance.add(10.0, "emerald3");
            waveInstance.add(10.0, "ruby3");
            waveInstance.add(10.0, "obsidian3");
            waveInstance.add(3.0, "slowing3");
            waveInstance.add(3.0, "void3");
            waveInstance.add(3.0, "magma3");
        } // 79
        {
            waveInstance = WaveManager.getManager().getWaveInstance(80);
            waveInstance.add(6.0, "emerald3");
            waveInstance.add(8.0, "ruby3");
            waveInstance.add(10.0, "obsidian3");
            waveInstance.add(4.0, "slowing3");
            waveInstance.add(3.0, "void3");
            waveInstance.add(2.0, "magma3");
        } // 80

        for(int i = 1; i <= 80; i++) {
            WaveManager.getManager().reloadWaveInstanceConfig(i);
            WaveManager.getManager().saveWaveInstanceConfig(i);
        }

        WaveManager.getManager().waveInstanceList.clear();

    }
}
