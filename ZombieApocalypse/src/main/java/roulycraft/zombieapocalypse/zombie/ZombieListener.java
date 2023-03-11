package roulycraft.zombieapocalypse.zombie;

import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import roulycraft.zombieapocalypse.ZombieApocalypse;

import java.util.*;

import static org.bukkit.entity.EntityType.PLAYER;

public class ZombieListener implements Listener {

    private static ZombieApocalypse plugin;
    private static final HashMap<NamespacedKey, HashMap<Player, Integer>> bossbarList = new HashMap<>();

    public static void insertMap(NamespacedKey key) {
        bossbarList.put(key, new HashMap<>());
    }

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }

    public static void clearAllBossbars() {

        for (NamespacedKey key : bossbarList.keySet()) {
            Bukkit.getServer().getBossBar(key).removeAll();
            Bukkit.getServer().removeBossBar(key);
        }

    }

    private void deathParticles(BlockData blockData, Location loc) {
        loc.getWorld().spawnParticle(Particle.FALLING_DUST, loc, 50, 0.5, 1, 0.5, 0.3, blockData);
        loc.getWorld().spawnParticle(Particle.BLOCK_DUST, loc, 20, 0.5, 1, 0.5, 0.3, new ItemStack(Material.REDSTONE_BLOCK, 1).getType().createBlockData());
        loc.getWorld().spawnParticle(Particle.CLOUD, loc, 20, 0.5, 1, 0.5, 0.1);
        loc.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 10, 0.5, 1, 0.5, 0.2);

        loc.getWorld().playSound(loc, Sound.ENTITY_ZOMBIE_DEATH, SoundCategory.HOSTILE, 1, 1);
        loc.getWorld().playSound(loc, Sound.ENTITY_CHICKEN_EGG, SoundCategory.HOSTILE, 1, 2);
    }

    private void zombieBossBarDecay(NamespacedKey key, Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {

                if(bossbarList.isEmpty() || bossbarList.get(key) == null || !bossbarList.get(key).containsKey(player) || !bossbarList.containsKey(key)) {

                    return;

                }

                int currentDelay = bossbarList.get(key).get(player) - 1;

                bossbarList.get(key).put(player, currentDelay);

                if(currentDelay > 0) {

                    zombieBossBarDecay(key, player);

                }

                else {

                    bossbarList.get(key).remove(player);
                    Bukkit.getBossBar(key).removePlayer(player);

                }
            }
        }.runTaskLaterAsynchronously(plugin, 1L);
    }
    private void changeBossBar(NamespacedKey key, String name, Integer HP, Integer maxHP) {

        KeyedBossBar bar = Bukkit.getBossBar(key);

        if(HP > 0) {
            bar.setTitle(name + " §2[§a"+HP+"§2]");
            bar.setProgress((double) HP / (double) maxHP);
            return;
        }

        bar.setColor(BarColor.RED);
        bar.setProgress(0);
        bar.setTitle(name + " §4[§c0§4]");
        deleteZombieBossBar(key);

    }
    private void deleteZombieBossBar(NamespacedKey key) {
        new BukkitRunnable() {
            @Override
            public void run() {

                Bukkit.getBossBar(key).removeAll();
                Bukkit.removeBossBar(key);
                bossbarList.remove(key);

            }
        }.runTaskLaterAsynchronously(plugin, 15L);
    }

    @EventHandler
    public void onZombieDamage(EntityDamageByEntityEvent event) {

        Zombie entity = (Zombie) event.getEntity();
        LivingEntity lentity = (LivingEntity) event.getEntity();
        Player player = null;

        int doesItReturn = 0;

        if(event.getDamager().getType() != EntityType.SNOWBALL && event.getDamager().getType() != EntityType.EGG && event.getDamager().getType() != EntityType.ARROW) {
            doesItReturn = 1;
        }

        if(event.getDamager().getType() == PLAYER) {
            doesItReturn = 0;
        }

        if(doesItReturn == 1 || entity.getType() != EntityType.ZOMBIE || !entity.getMetadata("ZA").get(0).asBoolean()) {
            return;
        }

        if(!event.getDamager().getMetadata("ZAProjectile").get(0).asBoolean()) {
            return;
        }

        int damage;

        damage = (int) Math.round(event.getDamage());

        if(event.getDamager().getType() == EntityType.SNOWBALL || event.getDamager().getType() == EntityType.EGG || event.getDamager().getType() == EntityType.ARROW) {

            if(event.getDamager().getMetadata("ZAProjectile").get(0).asBoolean()) {

                int minDMG = event.getDamager().getMetadata("minDMG").get(0).asInt();
                int maxDMG = event.getDamager().getMetadata("maxDMG").get(0).asInt();

                player = Bukkit.getPlayer(event.getDamager().getMetadata("shooter").get(0).asString());

                Random rng = new Random();

                damage = rng.nextInt(maxDMG - minDMG) + minDMG;

                lentity.setNoDamageTicks(0);
                lentity.setMaximumNoDamageTicks(0);

                event.setDamage(damage);
            }
        }
        
        else {
            
            player = (Player) event.getDamager();

            lentity.setNoDamageTicks(20);
            lentity.setMaximumNoDamageTicks(20);

        }

        int maxHP = entity.getMetadata("maxHealth").get(0).asInt();
        int HP = entity.getMetadata("health").get(0).asInt();
        String key = entity.getMetadata("bossbarKey").get(0).asString();

        HP -= damage;

        entity.setMetadata("health", new FixedMetadataValue(plugin, HP));

        final org.bukkit.util.Vector v = new Vector();
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> entity.setVelocity(v), 1l);

        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);

        changeBossBar(namespacedKey, entity.getCustomName(), HP, maxHP);

        if(!bossbarList.get(namespacedKey).containsKey(player)) {
            zombieBossBarDecay(namespacedKey, player);
            Bukkit.getBossBar(namespacedKey).addPlayer(player);
        }
        bossbarList.get(namespacedKey).put(player, 50);

        if(HP > 0) {

            event.setDamage(0);

        }

        else {

            BlockData data = entity.getEquipment().getHelmet().getType().createBlockData();
            deathParticles(data, entity.getLocation().add(0, 1, 0));

            deleteZombieBossBar(namespacedKey);
            entity.remove();

        }
    }
}
