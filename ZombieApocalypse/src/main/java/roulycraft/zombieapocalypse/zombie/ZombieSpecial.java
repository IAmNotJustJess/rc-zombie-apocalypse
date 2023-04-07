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
            @Override
            public void run() {

                if (!disableZoomList.containsKey(player)) {

                    return;

                }

                int currentDelay = disableZoomList.get(player) - 1;

                disableZoomList.put(player, currentDelay);

                if (currentDelay > 0) {

                    disableZoomCountdown(player);

                } else {

                    disableZoomList.remove(player);

                }
            }
        }.runTaskLater(plugin, 1L);
    }

    public void onPlayerDamageEffect(String effectName, Location location, Player player) {
        switch (effectName) {
            case "slowing": {

                int duration = plugin.getConfig().getInt("settings.effects.slowingDuration");
                int amplifier = plugin.getConfig().getInt("settings.effects.slowingAmplifier");

                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, amplifier, false, false));

                location.getWorld().playSound(location, Sound.BLOCK_GLASS_BREAK, SoundCategory.HOSTILE, 0.2f, 0.5f);

                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3,
                        new ItemStack(Material.PACKED_ICE, 1).getType().createBlockData()
                );
                player.getWorld().spawnParticle(Particle.BLOCK_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3,
                        new ItemStack(Material.SNOW_BLOCK, 1).getType().createBlockData()
                );

                if (!disableZoomList.containsKey(player)) {
                    disableZoomList.put(player, duration);
                    disableZoomCountdown(player);
                } else if (disableZoomList.get(player) < duration) {
                    disableZoomList.put(player, duration);
                }

                break;
            }
            case "void": {

                double power = plugin.getConfig().getDouble("settings.effects.voidPower");

                location.getWorld().playSound(location, Sound.ENTITY_WITHER_SHOOT, SoundCategory.HOSTILE, 0.2f, 1.5f);

                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3,
                        new ItemStack(Material.PURPLE_STAINED_GLASS, 1).getType().createBlockData());
                player.getWorld().spawnParticle(Particle.BLOCK_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3,
                        new ItemStack(Material.BLACK_STAINED_GLASS, 1).getType().createBlockData());

                player.setVelocity(location.toVector().subtract(player.getLocation().toVector()).normalize().multiply(power));

                break;
            }
            case "magma":
            case "electric":
            case "slime":
            default: {
                break;
            }
        }

    }

    public void onZombieDeathEffect(String effectName, UUID entityUUID, Location location) {
        switch (effectName) {
            case "explosive": {

                List<Entity> affected = new ArrayList<>();

                int damage = plugin.getConfig().getInt("settings.effects.explosiveDamage");
                double aoe = plugin.getConfig().getDouble("settings.effects.explosiveAOE");

                location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 1f, 1f);

                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 50, aoe * 2, aoe * 2, aoe * 2, 0.3,
                        new ItemStack(Material.TNT, 1).getType().createBlockData()
                );
                location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location, 50, aoe * 2, aoe * 2, aoe * 2, 0.3
                );

                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2,
                        (entity) -> entity.getType() == EntityType.PLAYER
                ));
                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2,
                        (entity) -> entity.getType() == EntityType.ZOMBIE
                ));


                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Entity entity : affected) {
                            if (entity instanceof Player) {
                                Player player = (Player) entity;

                                new PlayerListener().dealPlayerDamage(player, damage);
                            }
                            if (entity instanceof Zombie) {
                                if (entity.getUniqueId() != entityUUID) {
                                    new ZombieListener().dealZombieDamage(entity, damage);
                                }
                            }
                        }
                    }
                }.runTaskLater(plugin, 1L);


                break;
            }
            case "slowing": {

                int duration = plugin.getConfig().getInt("settings.effects.slowingDuration");
                int amplifier = plugin.getConfig().getInt("settings.effects.slowingAmplifier");
                double aoe = plugin.getConfig().getDouble("settings.effects.slowingAOE");

                location.getWorld().playSound(location, Sound.BLOCK_GLASS_BREAK, SoundCategory.HOSTILE, 1f, 0.5f);

                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 25, aoe, aoe, aoe, 0.3,
                        new ItemStack(Material.PACKED_ICE, 1).getType().createBlockData()
                );
                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 70, aoe * 1.5, aoe * 1.5, aoe * 1.5,
                        0.3, new ItemStack(Material.SNOW_BLOCK, 1).getType().createBlockData()
                );
                location.getWorld().spawnParticle(Particle.CLOUD, location, 50, 0, 0, 0, 0.1);

                List<Entity> affected = new ArrayList<>();
                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2,
                        (entity) -> entity.getType() == EntityType.PLAYER
                ));

                for (Entity entity : affected) {

                    Player player = (Player) entity;

                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, amplifier, false, false));

                    player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3,
                            new ItemStack(Material.PACKED_ICE, 1).getType().createBlockData()
                    );
                    player.getWorld().spawnParticle(Particle.FALLING_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3,
                            new ItemStack(Material.SNOW_BLOCK, 1).getType().createBlockData()
                    );

                    if (!disableZoomList.containsKey(player)) {
                        disableZoomList.put(player, duration);
                        disableZoomCountdown(player);
                    } else if (disableZoomList.get(player) < duration) {
                        disableZoomList.put(player, duration);
                    }
                }

                break;
            }
            case "void": {

                double power = plugin.getConfig().getDouble("settings.effects.voidPower");
                double aoe = plugin.getConfig().getDouble("settings.effects.voidAOE");

                location.getWorld().playSound(location, Sound.ENTITY_WITHER_SHOOT, SoundCategory.HOSTILE, 1f, 1.5f);

                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 100, aoe, aoe, aoe, 0.3,
                        new ItemStack(Material.PURPLE_STAINED_GLASS, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 40, aoe * 1.5, aoe * 1.5, aoe * 1.5,
                        0.3, new ItemStack(Material.BLACK_STAINED_GLASS, 1).getType().createBlockData());
                location.getWorld().spawnParticle(Particle.CLOUD, location, 50, 0, 0, 0, 0.1);

                List<Entity> affected = new ArrayList<>();
                affected.addAll(location.getWorld().getNearbyEntities(location, aoe * 2, aoe * 2, aoe * 2,
                        (entity) -> entity.getType() == EntityType.PLAYER));

                for (Entity entity : affected) {

                    Player player = (Player) entity;

                    player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(), 50, 0.5, 1, 0.5, 0.3,
                            new ItemStack(Material.PURPLE_STAINED_GLASS, 1).getType().createBlockData());
                    player.getWorld().spawnParticle(Particle.BLOCK_DUST, player.getLocation(), 20, 0.5, 1, 0.5, 0.3,
                            new ItemStack(Material.BLACK_STAINED_GLASS, 1).getType().createBlockData());

                    player.setVelocity(location.toVector().subtract(player.getLocation().toVector()).normalize().multiply(power));

                }

                break;
            }
            case "magma":
            case "slime":
            case "electric":
            case "beehive0":
            case "beehive1":
            case "beehive2":
            case "beehive3":
            default: {
                break;
            }
        }
    }
}
