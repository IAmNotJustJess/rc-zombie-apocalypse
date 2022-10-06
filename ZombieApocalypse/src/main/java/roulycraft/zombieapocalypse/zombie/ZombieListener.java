package roulycraft.zombieapocalypse.zombie;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import roulycraft.zombieapocalypse.ZombieApocalypse;

import java.util.*;
import java.util.Timer;

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

        boolean isIn = false;
        assert bar != null;
        for (Player p2 : bar.getPlayers()) {
            if (p2 == p) {
                isIn = true;
                break;
            }
        }

        if (HP > 0) {

            bar.setProgress((double) HP / maxHP);
            bar.setTitle(name + " §2[§a" + HP + "§2]");

            bossbarList.get(key).put(p, 50);

            Timer timer = new Timer();

            final Integer[] detectChange = {50};

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (!Objects.equals(detectChange[0], bossbarList.get(key).get(p)) || bossbarList.get(key).get(p) == null) {
                        bar.removePlayer(p);
                        timer.cancel();
                        timer.purge();
                    }

                    if (bossbarList.get(key).get(p) > 0) {

                        bar.addPlayer(p);
                        bossbarList.get(key).put(p, (bossbarList.get(key).get(p) - 1));
                        detectChange[0] -= 1;
                    }

                    else {

                        bar.removePlayer(p);
                        timer.cancel();
                        timer.purge();
                    }

                }
            };

            timer.scheduleAtFixedRate(task, new Date(), 50);
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

            KeyedBossBar bar = Bukkit.getServer().getBossBar(NamespacedKey.fromString(key, plugin));

            bar.setColor(BarColor.RED);
            bar.setProgress(0);
            bar.setTitle(entity.getCustomName() + " §4[§c0§4]");

            Timer timer = new Timer();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    bar.removeAll();
                    bossbarList.get(NamespacedKey.fromString(key, plugin)).clear();
                    Bukkit.removeBossBar(NamespacedKey.fromString(key, plugin));

                }
            };

            timer.schedule(task, 1000);
        }
        else {
            event.setDamage(0);
            zombieBossBar((Player) event.getDamager(), NamespacedKey.fromString(key, plugin), entity.getCustomName(), maxHP, HP);
        }
    }
}
