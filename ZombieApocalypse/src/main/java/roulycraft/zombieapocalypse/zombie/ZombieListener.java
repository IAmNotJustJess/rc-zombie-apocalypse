package roulycraft.zombieapocalypse.zombie;

import org.bukkit.Bukkit;

import org.bukkit.NamespacedKey;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import org.bukkit.metadata.FixedMetadataValue;

import roulycraft.zombieapocalypse.ZombieApocalypse;

import javax.xml.stream.events.Namespace;
import java.util.Timer;
import java.util.TimerTask;

public class ZombieListener implements Listener {

    private static ZombieApocalypse plugin;
    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }

    public static void zombieBossBar(Player p, NamespacedKey key, String name, Integer maxHP, Integer HP) {

        BossBar bar = Bukkit.getServer().getBossBar(key);

        boolean isIn = false;
        for (Player p2 : bar.getPlayers()) {
            if (p2 == p) {
                isIn = true;
            }
        }

        if (HP > 0) {
            if(!isIn) {
                bar.addPlayer(p);
            }

            bar.setProgress((double) HP / maxHP);
            bar.setTitle(name);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    bar.removePlayer(p);
                }
            }, 2500);
        }

        else {
            bar.removeAll();
            Bukkit.removeBossBar(key);
        }

    }

    @EventHandler
    public void onZombieDamage(EntityDamageByEntityEvent event) {

        Entity entity = event.getEntity();
        if(entity.getType() != EntityType.ZOMBIE) {
            return;
        }

        if(event.getDamager().getType() != EntityType.PLAYER) {
            return;
        }

        if(!entity.getMetadata("ZA").get(0).asBoolean()) {
            return;
        }

        int maxHP = entity.getMetadata("maxHealth").get(0).asInt();
        int HP = entity.getMetadata("health").get(0).asInt();
        String key = entity.getMetadata("bossbarKey").get(0).asString();

        HP -= event.getDamage();

        entity.setMetadata("health", new FixedMetadataValue(plugin, HP));

        if(HP <= 0) {
            event.setDamage(50);
            Bukkit.getServer().getBossBar(NamespacedKey.fromString(key, plugin)).removeAll();
            Bukkit.getServer().removeBossBar(NamespacedKey.fromString(key, plugin));
        }
        else {
            event.setDamage(0);
            zombieBossBar((Player) event.getDamager(), NamespacedKey.fromString(key, plugin), entity.getCustomName(), maxHP, HP);
        }
    }
}
