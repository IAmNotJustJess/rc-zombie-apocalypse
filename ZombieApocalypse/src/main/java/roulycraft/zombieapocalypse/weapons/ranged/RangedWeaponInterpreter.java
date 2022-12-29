package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import roulycraft.zombieapocalypse.ZombieApocalypse;

public class RangedWeaponInterpreter implements Listener {
    private static ZombieApocalypse plugin;

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            NamespacedKey key = new NamespacedKey(plugin, "zaGun");

            if (event.getItem().getItemMeta() == null || event.getItem().getItemMeta().getPersistentDataContainer() == null) {
                return;
            }

            PersistentDataContainer container = event.getItem().getItemMeta().getPersistentDataContainer();

            if (container.get(key, PersistentDataType.INTEGER) == null || container.get(key, PersistentDataType.INTEGER) != 1) {
                return;
            }

            Integer projectileType = container.get(new NamespacedKey(plugin, "projectileType"), PersistentDataType.INTEGER);
            Float projectileSpeed = container.get(new NamespacedKey(plugin, "projectileSpeed"), PersistentDataType.FLOAT);
            Integer pellets = container.get(new NamespacedKey(plugin, "pellets"), PersistentDataType.INTEGER);
            Integer minDMG = container.get(new NamespacedKey(plugin, "minDMG"), PersistentDataType.INTEGER);
            Integer maxDMG = container.get(new NamespacedKey(plugin, "maxDMG"), PersistentDataType.INTEGER);

            Entity entity;

            for (int i = 0; i < pellets; i++) {

                switch (projectileType) {

                    case 0:

                        entity = event.getPlayer().launchProjectile(Snowball.class, event.getPlayer().getVelocity().multiply(projectileSpeed));
                        break;

                    case 1:

                        entity = event.getPlayer().launchProjectile(Egg.class, event.getPlayer().getVelocity().multiply(projectileSpeed));
                        break;

                    case 2:

                        entity = event.getPlayer().launchProjectile(Arrow.class, event.getPlayer().getVelocity().multiply(projectileSpeed));
                        break;

                    default:

                        entity = event.getPlayer().launchProjectile(Snowball.class, event.getPlayer().getVelocity().multiply(projectileSpeed));
                        break;

                }

                entity.setMetadata("ZAProjectile", new FixedMetadataValue(plugin, 1));
                entity.setMetadata("minDMG", new FixedMetadataValue(plugin, minDMG));
                entity.setMetadata("maxDMG", new FixedMetadataValue(plugin, maxDMG));
            }
        }
    }


    public static void injectPlugin(ZombieApocalypse plugin) {
        RangedWeaponInterpreter.plugin = plugin;
    }
}
