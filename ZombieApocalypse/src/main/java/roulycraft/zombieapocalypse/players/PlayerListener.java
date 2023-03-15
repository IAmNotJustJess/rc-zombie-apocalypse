package roulycraft.zombieapocalypse.players;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
}
