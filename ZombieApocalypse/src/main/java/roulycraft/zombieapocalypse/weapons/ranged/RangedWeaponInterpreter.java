package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.utility.ConfigColourParser;
import roulycraft.zombieapocalypse.utility.SoundSplitter;

import javax.naming.Name;
import java.util.*;

public class RangedWeaponInterpreter implements Listener {
    private static ZombieApocalypse plugin;

    private Map<Player, Integer> delayBetweenShotsList = new WeakHashMap<>();
    private Map<Player, Double> delayBeforeSpreadNullification = new WeakHashMap<>();
    private Map<Player, Integer> delayBeforeSpreadDecay = new WeakHashMap<>();
    private Map<Player, Boolean> reloadCancelling = new WeakHashMap<>();

    private void gunAction(Player player, Integer actionDelay, String actionSound, Integer delayBeforeSound) {

        if(delayBetweenShotsList.containsKey(player)) {

            delayBetweenShotsList.put(player, delayBetweenShotsList.get(player) + actionDelay);

        }

        else {


            delayBetweenShotsList.put(player, actionDelay);
            delayDecay(player);

        }

        new BukkitRunnable() {

            @Override
            public void run() {

                SoundSplitter.playSplitSound(player, actionSound);
            }
        }.runTaskLaterAsynchronously(plugin, (long) delayBeforeSound);

    }
    private void reloadGun(Player player, ItemStack item, Integer itemSlot, String reloadType, Integer reloadTypeSpecial, Integer reloadSpeed, String reloadingSound, Integer actionDelay, String actionSound) {

        if(delayBetweenShotsList.containsKey(player)) {

            return;

        }

        ItemMeta itemMeta = item.getItemMeta();

        Integer clipSize = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "clipSize"), PersistentDataType.INTEGER);
        Integer currentAmmo = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "currentAmmo"), PersistentDataType.INTEGER);

        Integer level = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER);

        String primaryColour = ConfigColourParser.getColour(plugin.getConfig().getString("guns.colours.level"+level+".primary"));
        String secondaryColour = ConfigColourParser.getColour(plugin.getConfig().getString("guns.colours.level"+level+".secondary"));

        String[] displayName = itemMeta.getDisplayName().split("\\|");

        final Integer[] cancelRestReloads = {0};

        switch(reloadType) {

            case "multipleBullets": {

                Integer loopCount = 1;

                reloadCancelling.put(player, false);

                Integer loopTimes = currentAmmo;

                if (currentAmmo == 0) {

                    new BukkitRunnable() {

                        @Override
                        public void run() {

                            if (cancelRestReloads[0] == 0) {

                                gunAction(player, actionDelay, actionSound, 0);

                                itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "actionSpecialTracker"), PersistentDataType.INTEGER, 0);

                                item.setItemMeta(itemMeta);

                                if (itemSlot >= 0) {

                                    player.getInventory().setItem(itemSlot, item);

                                }

                                reloadCancelling.remove(player);

                            }

                        }

                    }.runTaskLaterAsynchronously(plugin, (long) ((reloadSpeed) * Math.floor(clipSize / reloadTypeSpecial)));

                }

                SoundSplitter.playSplitSound(player, reloadingSound);

                if (delayBetweenShotsList.containsKey(player)) {

                    delayBetweenShotsList.put(player, reloadSpeed);

                } else {

                    delayBetweenShotsList.put(player, reloadSpeed);

                    delayDecay(player);

                }

                while (loopTimes < clipSize) {

                    new BukkitRunnable() {

                        @Override
                        public void run() {

                            if (cancelRestReloads[0] == 2) {

                                return;

                            }

                            if (reloadCancelling.get(player)) {

                                cancelRestReloads[0] = 1;

                                if (currentAmmo == 0) {

                                    SoundSplitter.playSplitSound(player, "BLOCK_WOOL_BREAK|1|2|0");

                                    reloadCancelling.remove(player);

                                    gunAction(player, actionDelay, actionSound, 0);

                                    itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "actionSpecialTracker"), PersistentDataType.INTEGER, 0);

                                    item.setItemMeta(itemMeta);

                                    if (itemSlot >= 0) {

                                        player.getInventory().setItem(itemSlot, item);

                                    }

                                }

                            }

                            if (cancelRestReloads[0] < 2) {

                                if (cancelRestReloads[0] == 1) {

                                    cancelRestReloads[0] = 2;

                                }

                                Integer currentAmmo = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "currentAmmo"), PersistentDataType.INTEGER) + reloadTypeSpecial;

                                if (currentAmmo >= clipSize) {

                                    currentAmmo = clipSize;

                                    itemMeta.getPersistentDataContainer().set(
                                            new NamespacedKey(plugin, "currentAmmo"),
                                            PersistentDataType.INTEGER,
                                            clipSize
                                    );

                                } else {

                                    if (cancelRestReloads[0] == 0) {

                                        SoundSplitter.playSplitSound(player, reloadingSound);

                                        if (delayBetweenShotsList.containsKey(player)) {

                                            delayBetweenShotsList.put(player, reloadSpeed);

                                        } else {

                                            delayBetweenShotsList.put(player, reloadSpeed);

                                            delayDecay(player);

                                        }

                                    }

                                    itemMeta.getPersistentDataContainer().set(
                                            new NamespacedKey(plugin, "currentAmmo"),
                                            PersistentDataType.INTEGER,
                                            currentAmmo
                                    );


                                }

                                itemMeta.setDisplayName(displayName[0] + secondaryColour + "| " + primaryColour + currentAmmo);

                                item.setItemMeta(itemMeta);

                                if (itemSlot >= 0) {

                                    player.getInventory().setItem(itemSlot, item);

                                }
                            }
                        }

                    }.runTaskLaterAsynchronously(plugin, (loopCount * reloadSpeed));

                    loopTimes += reloadTypeSpecial;
                    loopCount++;


                }

                break;

            }

            default: {

                SoundSplitter.playSplitSound(player, reloadingSound);

                delayBetweenShotsList.put(player, reloadSpeed);

                delayDecay(player);

                new BukkitRunnable() {
                    @Override
                    public void run() {

                        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "currentAmmo"),
                                PersistentDataType.INTEGER,
                                clipSize);


                        itemMeta.setDisplayName(displayName[0]+secondaryColour+"| "+primaryColour+clipSize);

                        item.setItemMeta(itemMeta);

                        if(itemSlot >= 0) {

                            player.getInventory().setItem(itemSlot, item);

                        }

                    }
                }.runTaskLaterAsynchronously(plugin, (long) reloadSpeed);


                if(currentAmmo == 0) {

                    gunAction(player, actionDelay, actionSound, reloadSpeed);

                }

            }

            break;

        }

    }
    private void delayDecay(Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {

                if(!delayBetweenShotsList.containsKey(player)) {

                    return;

                }

                int currentDelay = delayBetweenShotsList.get(player) - 1;

                delayBetweenShotsList.put(player, currentDelay);

                if(currentDelay > 1) {

                    delayDecay(player);

                }

                else {

                    delayBetweenShotsList.remove(player);

                }
            }
        }.runTaskLaterAsynchronously(plugin, 1L);

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
        }.runTaskLaterAsynchronously(plugin, 1L);

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
        }.runTaskLaterAsynchronously(plugin, 1L);

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
                    double angle = random.nextDouble() * 2 * Math.PI;

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
                double angle = random.nextDouble() * 2 * Math.PI;

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
                double angle = random.nextDouble() * 2 * Math.PI;

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

    @EventHandler
    public void onHotbarSwitch(PlayerItemHeldEvent event){

        if(delayBetweenShotsList != null && delayBetweenShotsList.containsKey(event.getPlayer())) {

            event.setCancelled(true);

        }

    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {

        ItemMeta itemMeta = event.getItemDrop().getItemStack().getItemMeta();

        if (itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "zaGun"), PersistentDataType.INTEGER) == null) {

            return;

        }

        event.setCancelled(true);

        if(delayBetweenShotsList != null && delayBetweenShotsList.containsKey(event.getPlayer())) {

            return;

        }

        Integer clipSize = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "clipSize"), PersistentDataType.INTEGER);
        Integer currentAmmo = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "currentAmmo"), PersistentDataType.INTEGER);

        if(!Objects.equals(currentAmmo, clipSize)) {

            Integer reloadSpeed = (int) Math.round(itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "reloadSpeed"), PersistentDataType.DOUBLE)*20);

            Integer actionDelay = (int) Math.round(itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "actionDelay"), PersistentDataType.DOUBLE)*20);

            String reloadingSound = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "reloadingSound"), PersistentDataType.STRING);

            String actionSound = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "actionSound"), PersistentDataType.STRING);

            String[] reloadTypeSplit = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "reloadType"), PersistentDataType.STRING).split("-");

            String reloadType = reloadTypeSplit[0];

            Integer reloadTypeSpecial = 0;

            if(reloadTypeSplit.length > 1) {

                reloadTypeSpecial = Integer.valueOf(reloadTypeSplit[1]);

            }

            reloadGun(event.getPlayer(), event.getItemDrop().getItemStack(), event.getPlayer().getInventory().getHeldItemSlot(), reloadType, reloadTypeSpecial, reloadSpeed, reloadingSound, actionDelay, actionSound);

        }

    }

    @EventHandler
    public void onEggHatch(CreatureSpawnEvent event) {

        if(event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.EGG) {

            event.setCancelled(true);

        }

    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {

        if (!event.getEntity().getMetadata("ZAProjectile").get(0).asBoolean() && event.getEntity().getType() == EntityType.ARROW) {

            return;

        }

        System.out.println("xdd");


        event.getEntity().remove();

    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.hasItem()) {

            if (reloadCancelling.containsKey(event.getPlayer())) {

                reloadCancelling.put(event.getPlayer(), true);

            }

            if (delayBetweenShotsList != null && delayBetweenShotsList.containsKey(event.getPlayer())) {

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

            final Integer[] currentAmmo = {itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "currentAmmo"), PersistentDataType.INTEGER)};

            Integer burstAmount = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "burstAmount"), PersistentDataType.INTEGER);
            Integer burstDelay = (int) (itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "burstDelay"), PersistentDataType.DOUBLE) * 20);

            if (currentAmmo[0] <= 0) { // reloading

                Integer reloadSpeed = (int) Math.round(itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "reloadSpeed"), PersistentDataType.DOUBLE) * 20);

                Integer actionDelay = (int) Math.round(itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "actionDelay"), PersistentDataType.DOUBLE) * 20);

                String reloadingSound = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "reloadingSound"), PersistentDataType.STRING);

                String actionSound = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "actionSound"), PersistentDataType.STRING);

                String[] reloadTypeSplit = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "reloadType"), PersistentDataType.STRING).split("-");

                String reloadType = reloadTypeSplit[0];

                Integer reloadTypeSpecial = 0;

                if (reloadTypeSplit.length > 1) {

                    reloadTypeSpecial = Integer.valueOf(reloadTypeSplit[1]);

                }

                reloadGun(event.getPlayer(), event.getItem(), -1, reloadType, reloadTypeSpecial, reloadSpeed, reloadingSound, actionDelay, actionSound);

                return;

            }

            final Integer[] delayBetweenShots = {(int) Math.round(itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "delayBetweenShots"), PersistentDataType.DOUBLE) * 20)};

            Integer burstLoopStopper = currentAmmo[0];

            if(currentAmmo[0] >= burstAmount) {

                burstLoopStopper = burstAmount;

            }

            delayBetweenShotsList.put(event.getPlayer(), delayBetweenShots[0]);

            delayDecay(event.getPlayer());

            for (int i = 0; i < burstAmount && burstLoopStopper > 0 && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "currentAmmo"), PersistentDataType.INTEGER) > 0; i++) {

                burstLoopStopper -= 1;

                new BukkitRunnable() {

                    @Override
                    public void run() {

                        if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "currentAmmo"), PersistentDataType.INTEGER) <= 0) {

                            return;

                        }

                        currentAmmo[0] -= 1;

                        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "currentAmmo"), PersistentDataType.INTEGER, currentAmmo[0]);

                        Integer level = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER);

                        String primaryColour = ConfigColourParser.getColour(plugin.getConfig().getString("guns.colours.level"+level+".primary"));
                        String secondaryColour = ConfigColourParser.getColour(plugin.getConfig().getString("guns.colours.level"+level+".secondary"));

                        String[] displayName = itemMeta.getDisplayName().split("\\|");

                        itemMeta.setDisplayName(displayName[0]+secondaryColour+"| "+primaryColour+ currentAmmo[0]);

                        String actionType = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "actionType"), PersistentDataType.STRING);
                        Integer actionSpecial = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "actionSpecial"), PersistentDataType.INTEGER);
                        Integer actionSpecialTracker = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "actionSpecialTracker"), PersistentDataType.INTEGER);

                        actionSpecialTracker += 1;

                        switch (actionType) {

                            default: {

                                break;

                            }

                            case "pumpAction": case "leverAction": case "boltAction": {

                                if(actionSpecialTracker >= actionSpecial && currentAmmo[0] != 0) {

                                    Integer actionDelay = (int) Math.round(itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "actionDelay"), PersistentDataType.DOUBLE)*20);

                                    String actionSound = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "actionSound"), PersistentDataType.STRING);

                                    gunAction(event.getPlayer(), actionDelay, actionSound, delayBetweenShots[0]);

                                    actionSpecialTracker = 0;

                                    delayBetweenShots[0] += actionDelay;

                                }

                                break;

                            }

                        }

                        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "actionSpecialTracker"), PersistentDataType.INTEGER, actionSpecialTracker);

                        event.getItem().setItemMeta(itemMeta);

                        Integer projectileType = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "projectileType"), PersistentDataType.INTEGER);

                        Double projectileSpeed = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "projectileSpeed"), PersistentDataType.DOUBLE);

                        Double bulletSpread = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "bulletSpread"), PersistentDataType.DOUBLE)/10;
                        Double additiveBulletSpread = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "additiveBulletSpread"), PersistentDataType.DOUBLE)/10;
                        Double spreadPercentage = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "spreadPercentage"), PersistentDataType.DOUBLE);

                        Integer pellets = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "pellets"), PersistentDataType.INTEGER);
                        Integer minDMG = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "minDMG"), PersistentDataType.INTEGER);
                        Integer maxDMG = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "maxDMG"), PersistentDataType.INTEGER);

                        Integer shootingPatternType = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "shootingPatternType"), PersistentDataType.INTEGER);
                        Double shootingPatternOffset = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "shootingPatternOffset"), PersistentDataType.DOUBLE);

                        String shootingSound = itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, "shootingSound"), PersistentDataType.STRING);

                        if(!delayBeforeSpreadDecay.containsKey(event.getPlayer())) {

                            delayBeforeSpreadDecay.put(event.getPlayer(), 10 + delayBetweenShots[0]);

                            delaySpreadDecay(event.getPlayer());

                        }

                        else {

                            delayBeforeSpreadDecay.put(event.getPlayer(), 10 + delayBetweenShots[0]);

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

                }.runTaskLater(plugin, ((long) i * burstDelay));

            }

        }
    }


    public static void injectPlugin(ZombieApocalypse plugin) {
        RangedWeaponInterpreter.plugin = plugin;
    }
}
