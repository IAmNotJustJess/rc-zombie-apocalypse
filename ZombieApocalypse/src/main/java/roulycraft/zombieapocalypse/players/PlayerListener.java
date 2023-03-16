package roulycraft.zombieapocalypse.players;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import roulycraft.zombieapocalypse.game.GameManager;

public class PlayerListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        GameManager.getManager().removePlayer(event.getPlayer());
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {

        if(GameManager.getManager().playerStats.containsKey(event.getEntity().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {

        if(event.getEntity().getType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.PLAYER &&
            GameManager.getManager().playerStats.containsKey(event.getEntity().getUniqueId()) && GameManager.getManager().playerStats.containsKey(event.getDamager().getUniqueId())) {
            event.setCancelled(true);
            return;
        }

        if(event.getEntity().getType() != EntityType.PLAYER
            || event.getDamager().getType() != EntityType.ZOMBIE
            || !event.getDamager().getMetadata("ZA").get(0).asBoolean()
            || !GameManager.getManager().playerStats.containsKey(event.getEntity().getUniqueId())) {
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

        double hpPercentage = (double) hp/(double) maxHP * 20;

        if(hp <= 1.0){
            hpPercentage = 1.0;
        }

        player.setHealth(hpPercentage);
        if(hp <= 0) {
            GameManager.getManager().playerDeath(player);
        }
    }
}
