package roulycraft.zombieapocalypse.zombie;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.players.PlayerListener;

import java.util.*;

public class ZombieSpecial {

    private static ZombieApocalypse plugin;
    private static ZombieSpecial zombieSpecial;
    public Map<Player, Integer> disableZoomList = new HashMap<>();
    public Map<Player, Integer> onFireList = new HashMap<>();
    public Map<Player, Integer> electrifiedList = new HashMap<>();

    public static ZombieSpecial getManager() {
        if (zombieSpecial == null) {
            zombieSpecial = new ZombieSpecial();
        }
        return zombieSpecial;
    }

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }
    private void disableZoomCountdown(Player player) {

        new BukkitRunnable() {
            @Override public void run() {

                if (!disableZoomList.containsKey(player)) {

                    return;

                }

                int currentDelay = disableZoomList.get(player) - 1;

                disableZoomList.put(player, currentDelay);

                if (currentDelay > 0) {

                    disableZoomCountdown(player);

                }
                else {

                    disableZoomList.remove(player);

                }
            }
        }.runTaskLater(plugin, 1L);
    }
    private void onFireCountdown(Player player) {

        new BukkitRunnable() {
            @Override public void run() {

                if (!onFireList.containsKey(player)) {

                    return;

                }

                int currentDelay = onFireList.get(player) - 1;

                onFireList.put(player, currentDelay);

                if(currentDelay % plugin.getConfig().getInt("settings.effects.magmaDamagePerTicks") == 0) {
                    new PlayerListener().dealPlayerDamage(player, plugin.getConfig().getInt("settings.effects.magmaDamage"));
                }

                if (currentDelay > 0) {

                    player.setFireTicks(3);
                    onFireCountdown(player);

                }
                else {

                    onFireList.remove(player);

                }
            }
        }.runTaskLater(plugin, 1L);
    }
    private void electrifiedCountdown(Player player) {

        new BukkitRunnable() {
            @Override public void run() {

                if (!electrifiedList.containsKey(player)) {

                    return;

                }

                int currentDelay = electrifiedList.get(player) - 1;

                electrifiedList.put(player, currentDelay);

                if (currentDelay > 0) {

                    Random random = new Random();

                    Location loc = player.getLocation().clone();
                    loc.setPitch((random.nextFloat() * 180 - 90));
                    loc.setYaw((random.nextFloat() * 360));

                    player.teleport(loc);
                    electrifiedCountdown(player);

                }
                else {

                    electrifiedList.remove(player);

                }
            }
        }.runTaskLater(plugin, 1L);
    }

    public void onPlayerDamageEffect(String effectName, Location location, Player player) {
        switch (effectName) {
            case "slowing": {

                int duration = (int) (plugin.getConfig().getDouble("settings.effects.slowingDuration") * 20);
                int amplifier = plugin.getConfig().getInt("settings.effects.slowingAmplifier");

                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, amplifier, false, false));

                location.getWorld().playSound(location, Sound.BLOCK_GLASS_BREAK, SoundCategory.HOSTILE, 0.2f, 0.5f);

                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3, new ItemStack(Material.PACKED_ICE, 1).getType().createBlockData());
                player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3, new ItemStack(Material.SNOW_BLOCK, 1).getType().createBlockData());

                if (!disableZoomList.containsKey(player)) {
                    disableZoomList.put(player, duration);
                    disableZoomCountdown(player);
                }
                else if (disableZoomList.get(player) < duration) {
                    disableZoomList.put(player, duration);
                }

                break;
            }
            case "void": {

                double power = plugin.getConfig().getDouble("settings.effects.voidPower");

                location.getWorld().playSound(location, Sound.ENTITY_WITHER_SHOOT, SoundCategory.HOSTILE, 0.2f, 1.5f);

                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3, new ItemStack(Material.PURPLE_STAINED_GLASS, 1).getType().createBlockData());
                player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3, new ItemStack(Material.BLACK_STAINED_GLASS, 1).getType().createBlockData());

                player.setVelocity(location.toVector().subtract(player.getLocation().toVector()).normalize().multiply(power));

                break;
            }
            case "magma": {

                int duration = (int) (plugin.getConfig().getDouble("settings.effects.magmaDuration") * 20);

                location.getWorld().playSound(location, Sound.ENTITY_PLAYER_HURT_ON_FIRE, SoundCategory.HOSTILE, 0.2f, 0.5f);

                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3, new ItemStack(Material.MAGMA_BLOCK, 1).getType().createBlockData());
                player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3, new ItemStack(Material.LAVA, 1).getType().createBlockData());
                player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 40, 0.5, 1, 0.5, 0.3);

                if (!onFireList.containsKey(player)) {
                    onFireList.put(player, duration);
                    onFireCountdown(player);
                }
                else if (onFireList.get(player) < duration) {
                    onFireList.put(player, duration);
                }

                break;
            }
            case "slime": {
                {

                    double power = plugin.getConfig().getDouble("settings.effects.slimePower");

                    location.getWorld().playSound(location, Sound.ENTITY_SLIME_JUMP, SoundCategory.HOSTILE, 1f, 0.75f);

                    player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3, new ItemStack(Material.SLIME_BLOCK, 1).getType().createBlockData());
                    player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3, new ItemStack(Material.GREEN_STAINED_GLASS, 1).getType().createBlockData());

                    player.setVelocity(player.getLocation().toVector().add(location.toVector()).normalize().multiply(power));

                    break;
                }
            }
            case "electric": {

                int duration = (int) (plugin.getConfig().getDouble("settings.effects.electricDuration") * 20);

                location.getWorld().playSound(location, Sound.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.HOSTILE, 0.2f, 1f);

                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3, new ItemStack(Material.YELLOW_STAINED_GLASS, 1).getType().createBlockData());
                player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3, new ItemStack(Material.YELLOW_STAINED_GLASS, 1).getType().createBlockData());
                player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), 20, 0.5, 1, 0.5, 0.3);

                if (!electrifiedList.containsKey(player)) {
                    electrifiedList.put(player, duration);
                    electrifiedCountdown(player);
                }
                else if (electrifiedList.get(player) < duration) {
                    electrifiedList.put(player, duration);
                }

                break;

            }

            default: {
                break;
            }
        }

    }

    public void onZombieDeathEffect(String effectName, Entity deadEntity, Location location) {
        switch (effectName) {
            case "explosive": {

                List<Entity> affected = new ArrayList<>();

                int damage = plugin.getConfig().getInt("settings.effects.explosiveDamage");
                double aoe = plugin.getConfig().getDouble("settings.effects.explosiveAOE");

                location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 1f, 1f);

                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 50, aoe * 2, aoe * 2, aoe * 2, 0.3, new ItemStack(Material.TNT, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location, 50, aoe * 2, aoe * 2, aoe * 2, 0.3);

                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2, (entity) -> entity.getType() == EntityType.PLAYER));
                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2, (entity) -> entity.getType() == EntityType.ZOMBIE));


                new BukkitRunnable() {
                    @Override public void run() {
                        for (Entity entity : affected) {
                            if (entity instanceof Player) {
                                Player player = (Player) entity;

                                new PlayerListener().dealPlayerDamage(player, damage);
                            }
                            if (entity instanceof Zombie) {
                                if (entity.getUniqueId() != deadEntity.getUniqueId()) {
                                    new ZombieListener().dealZombieDamage(entity, damage);
                                }
                            }
                        }
                    }
                }.runTaskLater(plugin, 1L);


                break;
            }
            case "slowing": {

                int duration = (int) (plugin.getConfig().getDouble("settings.effects.slowingDuration") * 20);
                int amplifier = plugin.getConfig().getInt("settings.effects.slowingAmplifier");
                double aoe = plugin.getConfig().getDouble("settings.effects.slowingAOE");

                location.getWorld().playSound(location, Sound.BLOCK_GLASS_BREAK, SoundCategory.HOSTILE, 1f, 0.5f);

                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 25, aoe, aoe, aoe, 0.3, new ItemStack(Material.PACKED_ICE, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 70, aoe * 1.5, aoe * 1.5, aoe * 1.5, 0.3, new ItemStack(Material.SNOW_BLOCK, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.CLOUD, location, 50, 0, 0, 0, 0.1);

                List<Entity> affected = new ArrayList<>();
                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2, (entity) -> entity.getType() == EntityType.PLAYER));

                for (Entity entity : affected) {

                    Player player = (Player) entity;

                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, amplifier, false, false));

                    player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3, new ItemStack(Material.PACKED_ICE, 1).getType().createBlockData());
                    player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3, new ItemStack(Material.SNOW_BLOCK, 1).getType().createBlockData());

                    if (!disableZoomList.containsKey(player)) {
                        disableZoomList.put(player, duration);
                        disableZoomCountdown(player);
                    }
                    else if (disableZoomList.get(player) < duration) {
                        disableZoomList.put(player, duration);
                    }
                }

                break;
            }
            case "void": {

                double power = plugin.getConfig().getDouble("settings.effects.voidPower");
                double aoe = plugin.getConfig().getDouble("settings.effects.voidAOE");

                location.getWorld().playSound(location, Sound.ENTITY_WITHER_SHOOT, SoundCategory.HOSTILE, 1f, 1.5f);

                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 100, aoe, aoe, aoe, 0.3, new ItemStack(Material.PURPLE_STAINED_GLASS, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 40, aoe * 1.5, aoe * 1.5, aoe * 1.5, 0.3, new ItemStack(Material.BLACK_STAINED_GLASS, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.CLOUD, location, 50, 0, 0, 0, 0.1);

                List<Entity> affected = new ArrayList<>();
                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2, (entity) -> entity.getType() == EntityType.PLAYER));

                for (Entity entity : affected) {

                    Player player = (Player) entity;

                    player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3, new ItemStack(Material.PURPLE_STAINED_GLASS, 1).getType().createBlockData());
                    player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3, new ItemStack(Material.BLACK_STAINED_GLASS, 1).getType().createBlockData());

                    player.setVelocity(location.toVector().subtract(player.getLocation().toVector()).normalize().multiply(power));

                }

                break;
            }
            case "magma": {

                int duration = (int) (plugin.getConfig().getDouble("settings.effects.magmaDuration") * 20);
                double aoe = plugin.getConfig().getDouble("settings.effects.magmaAOE");

                location.getWorld().playSound(location, Sound.ENTITY_PLAYER_HURT_ON_FIRE, SoundCategory.HOSTILE, 1f, 0.5f);

                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 25, aoe, aoe, aoe, 0.3, new ItemStack(Material.LAVA, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 70, aoe * 1.5, aoe * 1.5, aoe * 1.5, 0.3, new ItemStack(Material.MAGMA_BLOCK, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.FLAME, location, 50, 0.5, 1, 0.5, 0.3);

                List<Entity> affected = new ArrayList<>();
                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2, (entity) -> entity.getType() == EntityType.PLAYER));

                for (Entity entity : affected) {

                    Player player = (Player) entity;

                    player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3, new ItemStack(Material.MAGMA_BLOCK, 1).getType().createBlockData());
                    player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3, new ItemStack(Material.LAVA, 1).getType().createBlockData());

                    if (!onFireList.containsKey(player)) {
                        onFireList.put(player, duration);
                        onFireCountdown(player);
                    }
                    else if (onFireList.get(player) < duration) {
                        onFireList.put(player, duration);
                    }

                    break;
                }

                break;
            }
            case "slime": {

                double power = plugin.getConfig().getDouble("settings.effects.slimePower");
                double aoe = plugin.getConfig().getDouble("settings.effects.slimeAOE");

                location.getWorld().playSound(location, Sound.ENTITY_SLIME_JUMP, SoundCategory.HOSTILE, 1f, 0.75f);

                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 100, aoe, aoe, aoe, 0.3, new ItemStack(Material.SLIME_BLOCK, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 40, aoe * 1.5, aoe * 1.5, aoe * 1.5, 0.3, new ItemStack(Material.GREEN_STAINED_GLASS, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.CLOUD, location, 50, 0, 0, 0, 0.1);

                List<Entity> affected = new ArrayList<>();
                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2, (entity) -> entity.getType() == EntityType.PLAYER));
                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2, (entity) -> entity.getType() == EntityType.ZOMBIE));

                for (Entity entity : affected) {

                    if(deadEntity.getUniqueId() == entity.getUniqueId()) {
                        continue;
                    }

                    entity.getWorld().spawnParticle(Particle.BLOCK_CRACK, entity.getLocation(), 50, 0.5, 1, 0.5, 0.3, new ItemStack(Material.SLIME_BLOCK, 1).getType().createBlockData());
                    entity.getWorld().spawnParticle(Particle.FALLING_DUST, entity.getLocation(), 20, 0.5, 1, 0.5, 0.3, new ItemStack(Material.GREEN_STAINED_GLASS, 1).getType().createBlockData());

                    entity.setVelocity(entity.getLocation().toVector().subtract(location.toVector()).normalize().multiply(power));

                }

                break;
            }
            case "electric": {

                int duration = (int) (plugin.getConfig().getDouble("settings.effects.electricDuration") * 20);
                double aoe = plugin.getConfig().getDouble("settings.effects.electricAOE");

                location.getWorld().playSound(location, Sound.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.HOSTILE, 1f, 1f);

                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 25, aoe, aoe, aoe, 0.3, new ItemStack(Material.YELLOW_STAINED_GLASS, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 70, aoe * 1.5, aoe * 1.5, aoe * 1.5, 0.3, new ItemStack(Material.WHITE_STAINED_GLASS, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, location, 50, 0, 0, 0, 0.1);

                List<Entity> affected = new ArrayList<>();
                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2, (entity) -> entity.getType() == EntityType.PLAYER));

                for (Entity entity : affected) {

                    Player player = (Player) entity;

                    player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3, new ItemStack(Material.YELLOW_STAINED_GLASS, 1).getType().createBlockData());
                    player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3, new ItemStack(Material.YELLOW_STAINED_GLASS, 1).getType().createBlockData());
                    player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), 20, 0.5, 1, 0.5, 0.3);

                    if (!electrifiedList.containsKey(player)) {
                        electrifiedList.put(player, duration);
                        electrifiedCountdown(player);
                    }
                    else if (electrifiedList.get(player) < duration) {
                        electrifiedList.put(player, duration);
                    }

                    break;
                }

                break;
            }
            case "beehive0": {

                for(int i = 0; i < plugin.getConfig().getInt("settings.effects.beehiveSpawnedAmount"); i++) {
                    new BukkitRunnable() {
                        @Override public void run() {
                            location.getWorld().playSound(location, Sound.ENTITY_CHICKEN_EGG, SoundCategory.HOSTILE, 1f, 0.75f);

                            location.getWorld().spawnParticle(Particle.CLOUD, location, 20, 0.5, 1, 0.5, 0.3);

                            ZombieManager.getManager().spawnZombie(location, "hiveling0", false, deadEntity.getMetadata("instanceName").get(0).asString());
                        }
                    }.runTaskLater(plugin, i * 15L);
                }

                break;
            }
            case "beehive1":{

                for(int i = 0; i < plugin.getConfig().getInt("settings.effects.beehiveSpawnedAmount"); i++) {
                    new BukkitRunnable() {
                        @Override public void run() {
                            location.getWorld().playSound(location, Sound.ENTITY_CHICKEN_EGG, SoundCategory.HOSTILE, 1f, 0.75f);

                            location.getWorld().spawnParticle(Particle.CLOUD, location, 20, 0.5, 1, 0.5, 0.3);

                            ZombieManager.getManager().spawnZombie(location, "hiveling1", false, deadEntity.getMetadata("instanceName").get(0).asString());
                        }
                    }.runTaskLater(plugin, i * 15L);
                }

                break;
            }
            case "beehive2":{

                for(int i = 0; i < plugin.getConfig().getInt("settings.effects.beehiveSpawnedAmount"); i++) {
                    new BukkitRunnable() {
                        @Override public void run() {
                            location.getWorld().playSound(location, Sound.ENTITY_CHICKEN_EGG, SoundCategory.HOSTILE, 1f, 0.75f);

                            location.getWorld().spawnParticle(Particle.CLOUD, location, 20, 0.5, 1, 0.5, 0.3);

                            ZombieManager.getManager().spawnZombie(location, "hiveling2", false, deadEntity.getMetadata("instanceName").get(0).asString());
                        }
                    }.runTaskLater(plugin, i * 15L);
                }

                break;
            }
            case "beehive3":{

                for(int i = 0; i < plugin.getConfig().getInt("settings.effects.beehiveSpawnedAmount"); i++) {
                    new BukkitRunnable() {
                        @Override public void run() {
                            location.getWorld().playSound(location, Sound.ENTITY_CHICKEN_EGG, SoundCategory.HOSTILE, 1f, 0.75f);

                            location.getWorld().spawnParticle(Particle.CLOUD, location, 20, 0.5, 1, 0.5, 0.3);

                            ZombieManager.getManager().spawnZombie(location, "hiveling3", false, deadEntity.getMetadata("instanceName").get(0).asString());
                        }
                    }.runTaskLater(plugin, i * 15L);
                }

                break;
            }
            default: {
                break;
            }
        }
    }
}
