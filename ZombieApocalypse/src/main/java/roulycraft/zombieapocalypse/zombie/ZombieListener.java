package roulycraft.zombieapocalypse.zombie;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

    public static void zombieBossBar(Player p, NamespacedKey key, String name, Integer maxHP, Integer HP) {

        BossBar bar = Bukkit.getServer().getBossBar(key);

        assert bar != null;
        for (Player p2 : bar.getPlayers()) {
            if (p2 == p) {
                break;
            }
        }

        if (HP > 0) {

            bar.setProgress((double) HP / maxHP);
            bar.setTitle(name + " §2[§a" + HP + "§2]");

            bossbarList.get(key).put(p, 50);

            final Integer[] detectChange = {50};

            new BukkitRunnable() {

                @Override
                public void run() {

                    if (!Objects.equals(detectChange[0], bossbarList.get(key).get(p)) || Objects.isNull(bossbarList.get(key).get(p))) {
                        cancel();
                        return;
                    }

                    if (bossbarList.get(key).get(p) > 0) {

                        if(Objects.isNull(bossbarList.get(key).get(p))) {
                            bar.removePlayer(p);
                            cancel();
                        }

                        else {
                            bar.addPlayer(p);
                            bossbarList.get(key).put(p, (bossbarList.get(key).get(p) - 1));
                            detectChange[0] -= 1;
                        }
                    }

                    else {

                        bar.removePlayer(p);
                        cancel();
                    }
                }
            }.runTaskTimerAsynchronously(plugin, 0L, 1L);
        }


    }

    @EventHandler
    public void onZombieDamage(EntityDamageByEntityEvent event) {

        Entity entity = event.getEntity();
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
        Bukkit.getScheduler().runTaskLater(plugin, () -> entity.setVelocity(v), 1l);

        if(HP <= 0) {

            event.setDamage(50);

            KeyedBossBar bar = Bukkit.getServer().getBossBar(NamespacedKey.fromString(key, plugin));

            bar.setColor(BarColor.RED);
            bar.setProgress(0);
            bar.setTitle(entity.getCustomName() + " §4[§c0§4]");

            new BukkitRunnable() {

                @Override
                public void run() {
                    bar.removeAll();
                    bossbarList.get(NamespacedKey.fromString(key, plugin)).clear();
                    Bukkit.removeBossBar(NamespacedKey.fromString(key, plugin));
                }
            }.runTaskLaterAsynchronously(plugin, 5L);


        }

        else {

            event.setDamage(0);
            zombieBossBar(player, NamespacedKey.fromString(key, plugin), entity.getCustomName(), maxHP, HP);

        }
    }
}
