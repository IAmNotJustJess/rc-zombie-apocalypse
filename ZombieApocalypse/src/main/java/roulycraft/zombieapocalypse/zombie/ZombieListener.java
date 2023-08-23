package roulycraft.zombieapocalypse.zombie;

import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.game.GameManager;

import java.util.HashMap;
import java.util.Random;

import static org.bukkit.entity.EntityType.PLAYER;

public class ZombieListener implements Listener {

    private static final HashMap<NamespacedKey, HashMap<Player, Integer>> bossbarList = new HashMap<>();
    private static ZombieApocalypse plugin;

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

    private void deathParticles(BlockData data, Location loc) {
        loc.getWorld().spawnParticle(Particle.FALLING_DUST, loc, 50, 0.5, 1, 0.5, 0.3, data);
        loc.getWorld().spawnParticle(Particle.BLOCK_DUST, loc, 50, 0.5, 1, 0.5, 0.3, new ItemStack(Material.REDSTONE_BLOCK, 1).getType().createBlockData());
        loc.getWorld().spawnParticle(Particle.CLOUD, loc, 20, 0.5, 1, 0.5, 0.1);
        loc.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 10, 0.5, 1, 0.5, 0.2);

        loc.getWorld().playSound(loc, Sound.ENTITY_ZOMBIE_DEATH, SoundCategory.HOSTILE, 1, 1);
        loc.getWorld().playSound(loc, Sound.ENTITY_CHICKEN_EGG, SoundCategory.HOSTILE, 1, 2);
    }

    private void zombieBossBarDecay(NamespacedKey key, Player player) {

        new BukkitRunnable() {
            @Override public void run() {

                if (bossbarList.isEmpty() || bossbarList.get(key) == null || !bossbarList.get(key).containsKey(player) || !bossbarList.containsKey(key)) {

                    return;

                }

                int currentDelay = bossbarList.get(key).get(player) - 1;

                bossbarList.get(key).put(player, currentDelay);

                if (currentDelay > 0) {

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

        if (HP > 0) {
            bar.setTitle(name + " §2[§a" + HP + "§2]");
            bar.setProgress((double) HP / (double) maxHP);
            return;
        }

        bar.setColor(BarColor.RED);
        bar.setProgress(0);
        bar.setTitle(name + " §4[§c0§4]");
        deleteZombieBossBar(key);

    }

    private void changeBossBar(KeyedBossBar bar, String name, Integer HP, Integer maxHP) {

        if (HP > 0) {
            bar.setTitle(name + " §2[§a" + HP + "§2]");
            bar.setProgress((double) HP / (double) maxHP);
            return;
        }

        bar.setColor(BarColor.RED);
        bar.setProgress(0);
        bar.setTitle(name + " §4[§c0§4]");

    }

    private void deleteZombieBossBar(NamespacedKey key) {
        new BukkitRunnable() {
            @Override public void run() {

                if (Bukkit.getBossBar(key) == null) {
                    return;
                }

                Bukkit.getBossBar(key).removeAll();
                Bukkit.removeBossBar(key);
                bossbarList.remove(key);

            }
        }.runTaskLaterAsynchronously(plugin, 15L);
    }

    @EventHandler public void onZombieIgnite(EntityCombustEvent event) {

        if (event.getEntity().getType() != EntityType.ZOMBIE || event.getEntity().getMetadata("ZA").size() != 1) {
            return;
        }

        if (event.getEntity().getMetadata("ZA").get(0).asBoolean()) {
            event.setCancelled(true);
        }

    }

    public void dealZombieDamage(Entity zombie, Integer damage) {

        Zombie entity = (Zombie) zombie;

        if (entity.getMetadata("maxHealth").get(0) == null) {
            return;
        }

        int maxHP = entity.getMetadata("maxHealth").get(0).asInt();
        int HP = entity.getMetadata("health").get(0).asInt();
        String key = entity.getMetadata("bossbarKey").get(0).asString();
        String instanceName = entity.getMetadata("instanceName").get(0).asString();
        boolean boss = entity.getMetadata("boss").get(0).asBoolean();

        double damageReduction = 0.0;
        if(zombie.getMetadata("damageReduction").get(0) != null){
            damageReduction = zombie.getMetadata("damageReduction").get(0).asDouble();
        }
        damage -= (int)(damage * damageReduction);

        HP -= damage;

        entity.setMetadata("health", new FixedMetadataValue(plugin, HP));
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);

        final org.bukkit.util.Vector v = new Vector();
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> entity.setVelocity(v), 1L);

        if(boss) {
            KeyedBossBar bar = GameManager.getManager().bossbarList.get(instanceName+".boss");
            changeBossBar(bar, entity.getCustomName(), HP, maxHP);
        }
        else {
            changeBossBar(namespacedKey, entity.getCustomName(), HP, maxHP);
        }


        if (HP <= 0) {

            BlockData data;

            if (!entity.getEquipment().getHelmet().getType().isAir() && entity.getEquipment().getHelmet().getType().isBlock()) {
                data = entity.getEquipment().getHelmet().getType().createBlockData();
            }
            else {
                data = new ItemStack(Material.GREEN_WOOL, 1).getType().createBlockData();
            }
            deathParticles(data, entity.getLocation().add(0, 1, 0));

            if(boss) {
                KeyedBossBar bar = GameManager.getManager().bossbarList.get(instanceName+".boss");
                deleteZombieBossBar(bar.getKey());
            }
            else {
                deleteZombieBossBar(namespacedKey);
            }
            GameManager.getManager().addScore(instanceName, entity.getUniqueId(), entity.getMetadata("countTowardKills").get(0).asInt());
            ZombieSpecial.getManager().onZombieDeathEffect(entity.getMetadata("special").get(0).asString(), entity, entity.getLocation());
            entity.remove();

        }
    }

    @EventHandler public void onZombieDamage(EntityDamageByEntityEvent event) {

        if (event.getEntity().getType() != EntityType.ZOMBIE) {
            return;
        }

        Zombie entity = (Zombie) event.getEntity();
        LivingEntity lentity = (LivingEntity) event.getEntity();
        Player player = null;
        String instanceName = entity.getMetadata("instanceName").get(0).asString();

        if (entity.getType() != EntityType.ZOMBIE || event.getEntity().getMetadata("ZA").size() != 1 || !entity.getMetadata("ZA").get(0).asBoolean()) {
            return;
        }

        int damageType = 0;

        if (event.getDamager().getType() == EntityType.SNOWBALL || event.getDamager().getType() == EntityType.EGG || event.getDamager().getType() == EntityType.ARROW) {
            damageType = 1;
        }

        if (event.getDamager().getType() == PLAYER) {
            damageType = 0;
        }

        if (damageType == 1 && !event.getDamager().getMetadata("ZAProjectile").get(0).asBoolean()) {
            return;
        }

        int damage;

        damage = (int) Math.round(event.getDamage());
        boolean boss = event.getEntity().getMetadata("boss").get(0).asBoolean();

        if (damageType == 1) {

            int minDMG = event.getDamager().getMetadata("minDMG").get(0).asInt();
            int maxDMG = event.getDamager().getMetadata("maxDMG").get(0).asInt();

            player = Bukkit.getPlayer(event.getDamager().getMetadata("shooter").get(0).asString());

            Random rng = new Random();

            damage = rng.nextInt(maxDMG - minDMG) + minDMG;

            lentity.setNoDamageTicks(0);
            lentity.setMaximumNoDamageTicks(0);

            event.setDamage(damage);
        }
        else {

            player = (Player) event.getDamager();

            lentity.setNoDamageTicks(20);
            lentity.setMaximumNoDamageTicks(20);

        }

        int maxHP = entity.getMetadata("maxHealth").get(0).asInt();
        int HP = entity.getMetadata("health").get(0).asInt();

        double damageReduction = 0.0;
        if(entity.getMetadata("damageReduction").get(0) != null){
            damageReduction = entity.getMetadata("damageReduction").get(0).asDouble();
        }
        damage -= (int)(damage * damageReduction);

        HP -= damage;

        entity.setMetadata("health", new FixedMetadataValue(plugin, HP));

        final org.bukkit.util.Vector v = new Vector();
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> entity.setVelocity(v), 1L);

        if(boss) {
            KeyedBossBar bar = GameManager.getManager().bossbarList.get(instanceName+".boss");
            changeBossBar(bar, entity.getCustomName(), HP, maxHP);
        }
        else {
            String key = entity.getMetadata("bossbarKey").get(0).asString();
            NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
            changeBossBar(namespacedKey, entity.getCustomName(), HP, maxHP);

            if (!bossbarList.get(namespacedKey).containsKey(player)) {
                zombieBossBarDecay(namespacedKey, player);
                Bukkit.getBossBar(namespacedKey).addPlayer(player);
            }
            bossbarList.get(namespacedKey).put(player, 50);
        }

        if (HP > 0) {

            event.setDamage(0);

        }
        
        else {

            if(boss) {
                KeyedBossBar bar = GameManager.getManager().bossbarList.get(instanceName+".boss");
                deleteZombieBossBar(bar.getKey());
            }
            else {
                String key = entity.getMetadata("bossbarKey").get(0).asString();
                NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
                deleteZombieBossBar(namespacedKey);
            }

            BlockData data;

            if (!entity.getEquipment().getHelmet().getType().isAir() && entity.getEquipment().getHelmet().getType().isBlock()) {
                data = entity.getEquipment().getHelmet().getType().createBlockData();
            }
            else {
                data = new ItemStack(Material.GREEN_WOOL, 1).getType().createBlockData();
            }
            deathParticles(data, entity.getLocation().add(0, 1, 0));
            GameManager.getManager().addScore(instanceName, entity.getUniqueId(), entity.getMetadata("countTowardKills").get(0).asInt());
            ZombieSpecial.getManager().onZombieDeathEffect(entity.getMetadata("special").get(0).asString(), entity, entity.getLocation());
            entity.remove();

        }
    }
}
