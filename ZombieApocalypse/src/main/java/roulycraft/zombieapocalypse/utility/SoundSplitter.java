package roulycraft.zombieapocalypse.utility;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import roulycraft.zombieapocalypse.ZombieApocalypse;

public class SoundSplitter {

    private static ZombieApocalypse plugin;

    public static void injectPlugin(ZombieApocalypse p) {

        plugin = p;

    }
    public static void playSplitSound(Player player, String normalizedSoundString) {

        Location playerLocation = player.getLocation();
        World playerWorld = player.getWorld();

        // 1|2|3|4;1|2|3|4
        // 1: Spigot Sound - https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Sound.html
        // 2: Volume - Głośność dzwięku w float
        // 3: Pitch - Wysokość dźwięku
        // 4: Delay - Opóźnienie wypuszczenia dźwięku

        String example = "ENTITY_SHULKER_HURT|1|1|0;ENTITY_VINDICATOR_HURT|1|2|5";

        String[] split = example.split(";");

        for (String sound : split) {

            String[] soundInfo = sound.split("\\|");

            new BukkitRunnable() {
                @Override
                public void run() {

                    playerWorld.playSound(playerLocation,
                            Sound.valueOf(soundInfo[0]),
                            SoundCategory.PLAYERS,
                            Float.parseFloat(soundInfo[1]),
                            Float.parseFloat(soundInfo[2]));
                }
            }.runTaskLater(plugin, Long.parseLong(soundInfo[3]));



        }

    }

}
