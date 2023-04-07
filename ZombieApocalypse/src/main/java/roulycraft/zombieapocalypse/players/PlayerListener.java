package roulycraft.zombieapocalypse.players;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import roulycraft.zombieapocalypse.game.GameManager;
import roulycraft.zombieapocalypse.zombie.ZombieSpecial;

public class PlayerListener implements Listener {

    @EventHandler public void onQuit(PlayerQuitEvent event) {

        GameManager.getManager().removePlayer(event.getPlayer());
    }

    @EventHandler public void onFoodChange(FoodLevelChangeEvent event) {

        if (GameManager.getManager().playerStats.containsKey(event.getEntity().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    public void dealPlayerDamage(Player player, Integer damage) {

        if (!GameManager.getManager().playerStats.containsKey(player.getUniqueId())) {
            return;
        }

        int hp = GameManager.getManager().playerStats.get(player.getUniqueId()).getHp();
        int maxHP = GameManager.getManager().playerStats.get(player.getUniqueId()).getMaxHP();

        hp -= damage;

        GameManager.getManager().playerStats.get(player.getUniqueId()).setHp(hp);
        GameManager.getManager().displayPlayerActionBar(player);

        double hpPercentage = (double) hp / (double) maxHP * 20;

        if (hp <= 1.0) {
            hpPercentage = 1.0;
        }

        player.setHealth(hpPercentage);
        if (hp <= 0) {
            GameManager.getManager().playerDeath(player);
        }
    }

    @EventHandler public void onPlayerDamage(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof Player && GameManager.getManager().playerStats.containsKey(event.getEntity().getUniqueId())) {
            if (event.getDamager() instanceof Projectile) {
                Projectile projectile = (Projectile) event.getDamager();
                if (projectile.getShooter() instanceof Player) {
                    Player damager = (Player) projectile.getShooter();
                    if (GameManager.getManager().playerStats.containsKey(damager.getUniqueId())) {
                        event.setCancelled(true);
                        return;
                    }
                }
            }
            else if (event.getDamager() instanceof Player && GameManager.getManager().playerStats.containsKey(event.getDamager().getUniqueId())) {
                event.setCancelled(true);
                return;
            }
        }

        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Zombie) || !event.getDamager().getMetadata("ZA").get(0).asBoolean() || !GameManager.getManager().playerStats.containsKey(event.getEntity().getUniqueId())) {
            return;
        }

        Player player = (Player) event.getEntity();
        Zombie zombie = (Zombie) event.getDamager();

        int damage = zombie.getMetadata("damage").get(0).asInt();
        int hp = GameManager.getManager().playerStats.get(player.getUniqueId()).getHp();
        int maxHP = GameManager.getManager().playerStats.get(player.getUniqueId()).getMaxHP();

        event.setDamage(0.0);

        hp -= damage;

        GameManager.getManager().playerStats.get(player.getUniqueId()).setHp(hp);
        GameManager.getManager().displayPlayerActionBar(player);

        double hpPercentage = (double) hp / (double) maxHP * 20;

        if (hp <= 1.0) {
            hpPercentage = 1.0;
        }

        player.setHealth(hpPercentage);
        if (hp <= 0) {
            GameManager.getManager().playerDeath(player);
        }
        else {
            ZombieSpecial.getManager().onPlayerDamageEffect(zombie.getMetadata("special").get(0).asString(), zombie.getLocation(), player);
        }
    }
}
