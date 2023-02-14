package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.utility.SoundSplitter;

import java.util.*;

public class RangedWeaponInterpreter implements Listener {
    private static ZombieApocalypse plugin;

    private Map<Player, Integer> delayBetweenShotsList = new WeakHashMap<>();
    private Map<Player, Double> delayBeforeSpreadNullification = new WeakHashMap<>();
    private Map<Player, Integer> delayBeforeSpreadDecay = new WeakHashMap<>();

    private void delayDecay(Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {

                int currentDelay = delayBetweenShotsList.get(player) - 1;

                delayBetweenShotsList.put(player, currentDelay);

                if(currentDelay > 0) {

                    delayDecay(player);

                }

                else {

                    delayBetweenShotsList.remove(player);

                }
            }
        }.runTaskLater(plugin, 1L);

    }

    private void delaySpreadDecay(Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {

                int currentDelay = delayBeforeSpreadDecay.get(player) - 1;

                delayBeforeSpreadDecay.put(player, currentDelay);

                if(currentDelay > 0) {

                    delaySpreadDecay(player);

                }

                else {

                    delayBeforeSpreadDecay.remove(player);

                    spreadDecay(player);

                }
            }
        }.runTaskLater(plugin, 1L);

    }

    private void spreadDecay(Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {

                if(delayBeforeSpreadDecay.containsKey(player)) {

                    return;

                }

                double currentDelay = delayBeforeSpreadNullification.get(player) - 7.5;

                delayBeforeSpreadNullification.put(player, currentDelay);

                if(currentDelay > 0) {

                    spreadDecay(player);

                }

                else {

                    delayBeforeSpreadNullification.remove(player);

                }

            }
        }.runTaskLater(plugin, 1L);

    }


    private void shootProjectile(
        Player player,
        Integer projectileType,
        Integer pellets,
        Double bulletSpread,
        Double additiveBulletSpread,
        Double projectileSpeed,
        Integer minDMG,
        Integer maxDMG,
        Integer shootingPatternType,
        Double shootingPatternOffset)

    {

        projectileSpeed *= 2;

        Location playerEyeLocation = player.getEyeLocation().clone();
        Location playerEyeLocationRotationUp = playerEyeLocation.clone();
        playerEyeLocationRotationUp.setPitch(playerEyeLocation.getPitch() + 90F);

        Vector playerVector = playerEyeLocation.getDirection();

        Entity entity;

        double spread = (bulletSpread + (additiveBulletSpread * delayBeforeSpreadNullification.get(player) / 100));

        switch(shootingPatternType) {

            default: {

                for (int i = 0; i < pellets; i++) {

                    Random random = new Random();

                    Vector spreadOffset = playerEyeLocationRotationUp.getDirection().multiply((random.nextDouble() * spread * 2 - spread));
                    double angle = random.nextDouble() * 360 * Math.PI / 180;

                    Vector vector = playerVector.clone().
                        add(spreadOffset.clone().rotateAroundAxis(playerVector, angle)).
                        multiply(projectileSpeed);

                    switch (projectileType) {

                        case 1:

                            entity = player.launchProjectile(Egg.class, vector);
                            break;

                        case 2:

                            entity = player.launchProjectile(Arrow.class, vector); // Make sure that eggs don't chickens
                            break;

                        default:

                            entity = player.launchProjectile(Snowball.class, vector);
                            break;

                    }

                    entity.setMetadata("ZAProjectile", new FixedMetadataValue(plugin, 1));
                    entity.setMetadata("minDMG", new FixedMetadataValue(plugin, minDMG));
                    entity.setMetadata("maxDMG", new FixedMetadataValue(plugin, maxDMG));
                    entity.setMetadata("shooter", new FixedMetadataValue(plugin, player.getName()));
                }

                break;

            }

            case 1: {

                Random random = new Random();

                Vector spreadOffset = playerEyeLocationRotationUp.getDirection().multiply((random.nextDouble() * spread * 2 - spread));
                double angle = random.nextDouble() * 360 * Math.PI / 180;

                double offsetPerPellet = shootingPatternOffset / pellets;
                double currentOffset = (shootingPatternOffset - offsetPerPellet) * 0.5;

                for (int i = 0; i < pellets; i++) {

                    Vector offsetPatternYaw = playerEyeLocationRotationUp.getDirection().multiply(currentOffset);

                    Vector vector = playerVector.clone().
                        add(offsetPatternYaw.clone().rotateAroundAxis(playerVector, Math.PI / 2)).
                        add(spreadOffset.clone().rotateAroundAxis(playerVector, angle)).
                        multiply(projectileSpeed);

                    switch (projectileType) {

                        case 1:

                            entity = player.launchProjectile(Egg.class, vector);
                            break;

                        case 2:

                            entity = player.launchProjectile(Arrow.class, vector); // Make sure that eggs don't chickens
                            break;

                        default:

                            entity = player.launchProjectile(Snowball.class, vector);
                            break;

                    }

                    entity.setMetadata("ZAProjectile", new FixedMetadataValue(plugin, 1));
                    entity.setMetadata("minDMG", new FixedMetadataValue(plugin, minDMG));
                    entity.setMetadata("maxDMG", new FixedMetadataValue(plugin, maxDMG));
                    entity.setMetadata("shooter", new FixedMetadataValue(plugin, player.getName()));



                    currentOffset -= offsetPerPellet;

                }

                break;

            }

            case 2: {

                Random random = new Random();

                Vector spreadOffset = playerEyeLocationRotationUp.getDirection().multiply((random.nextDouble() * spread * 2 - spread));
                double angle = random.nextDouble() * 360 * Math.PI / 180;

                for (int i = 0; i < pellets; i++) {

                    Vector offsetPatternYaw = playerEyeLocationRotationUp.getDirection().multiply(shootingPatternOffset);

                    Vector vector = playerVector.clone().
                            add(offsetPatternYaw.clone().rotateAroundAxis(playerVector, Math.PI / pellets * 2 * i - Math.PI)).
                            add(spreadOffset.clone().rotateAroundAxis(playerVector, angle)).
                            multiply(projectileSpeed);

                    switch (projectileType) {

                        case 1:

                            entity = player.launchProjectile(Egg.class, vector);
                            break;

                        case 2:

                            entity = player.launchProjectile(Arrow.class, vector); // Make sure that eggs don't chickens
                            break;

                        default:

                            entity = player.launchProjectile(Snowball.class, vector);
                            break;

                    }

                    entity.setMetadata("ZAProjectile", new FixedMetadataValue(plugin, 1));
                    entity.setMetadata("minDMG", new FixedMetadataValue(plugin, minDMG));
                    entity.setMetadata("maxDMG", new FixedMetadataValue(plugin, maxDMG));
                    entity.setMetadata("shooter", new FixedMetadataValue(plugin, player.getName()));

                }

                break;

            }
        }
    }

    private void shootProjectile(
            Player player,
            Integer projectileType,
            Integer pellets,
            Double bulletSpread,
            Double additiveBulletSpread,
            Double projectileSpeed,
            Integer minDMG,
            Integer maxDMG,
            Integer shootingPattern,
            Double patternOffset,
            Integer burstAmount,
            Integer delayBetweenBurst) {

        for (int i = 0; i < burstAmount; i++) {

            new BukkitRunnable() {

                @Override
                public void run() {

                    shootProjectile(player, projectileType, pellets, bulletSpread, additiveBulletSpread, projectileSpeed, minDMG, maxDMG, shootingPattern, patternOffset);

                }

            }.runTaskLater(plugin, ((long) (i - 1) * delayBetweenBurst));

        }

    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.hasItem()) {

            if(delayBetweenShotsList != null && delayBetweenShotsList.containsKey(event.getPlayer())) {

                return;

            }

            NamespacedKey key = new NamespacedKey(plugin, "zaGun");

            if (event.getItem().getItemMeta() == null) {
                return;
            }

            ItemMeta itemMeta = event.getItem().getItemMeta();

            if (itemMeta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) == null || itemMeta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER) != 1) {

                return;

            }

            Integer currentAmmo = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "currentAmmo"), PersistentDataType.INTEGER);

            if(currentAmmo <= 0) {

                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf("BLOCK_NOTE_BLOCK_HAT"), 1, 2);
                return;

            }

            currentAmmo -= 1;

            itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "currentAmmo"), PersistentDataType.INTEGER, currentAmmo);

            event.getItem().setItemMeta(itemMeta);

            Integer projectileType = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "projectileType"), PersistentDataType.INTEGER);

            Double projectileSpeed = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "projectileSpeed"), PersistentDataType.DOUBLE);

            Double bulletSpread = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "bulletSpread"), PersistentDataType.DOUBLE)/10;
            Double additiveBulletSpread = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "additiveBulletSpread"), PersistentDataType.DOUBLE)/10;
            Double spreadPercentage = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "spreadPercentage"), PersistentDataType.DOUBLE);

            Integer delayBetweenShots = (int) Math.round(itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "delayBetweenShots"), PersistentDataType.DOUBLE)*20);

            Integer pellets = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "pellets"), PersistentDataType.INTEGER);
            Integer minDMG = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "minDMG"), PersistentDataType.INTEGER);
            Integer maxDMG = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "maxDMG"), PersistentDataType.INTEGER);

            Integer shootingPatternType = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "shootingPatternType"), PersistentDataType.INTEGER);
            Double shootingPatternOffset = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "shootingPatternOffset"), PersistentDataType.DOUBLE);

            String shootingSound = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "shootingSound"), PersistentDataType.STRING);

            delayBetweenShotsList.put(event.getPlayer(), delayBetweenShots);

            delayDecay(event.getPlayer());

            if(!delayBeforeSpreadDecay.containsKey(event.getPlayer())) {

                delayBeforeSpreadDecay.put(event.getPlayer(), 10);

                delaySpreadDecay(event.getPlayer());

            }

            else {

                delayBeforeSpreadDecay.put(event.getPlayer(), 10);

            }

            if(!delayBeforeSpreadNullification.containsKey(event.getPlayer())) {

                delayBeforeSpreadNullification.put(event.getPlayer(), spreadPercentage);

            }

            else {

                delayBeforeSpreadNullification.put(event.getPlayer(), delayBeforeSpreadNullification.get(event.getPlayer()) + spreadPercentage);

                if (delayBeforeSpreadNullification.get(event.getPlayer()) > 100.0) {

                    delayBeforeSpreadNullification.put(event.getPlayer(), 100.0);

                }

            }

            SoundSplitter.playSplitSound(event.getPlayer(), shootingSound);

            shootProjectile(event.getPlayer(), projectileType, pellets, bulletSpread, additiveBulletSpread, projectileSpeed, minDMG, maxDMG, shootingPatternType, shootingPatternOffset);

        }
    }


    public static void injectPlugin(ZombieApocalypse plugin) {
        RangedWeaponInterpreter.plugin = plugin;
    }
}
