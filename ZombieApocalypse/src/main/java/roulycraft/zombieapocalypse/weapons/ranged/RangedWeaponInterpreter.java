package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import roulycraft.zombieapocalypse.ZombieApocalypse;

public class RangedWeaponInterpreter implements Listener {
    private static ZombieApocalypse plugin;

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            NamespacedKey key = new NamespacedKey(plugin, "zaNBT");

            ItemStack item = event.getItem();
            ItemMeta itemMeta = item.getItemMeta();

            PersistentDataContainer container = itemMeta.getPersistentDataContainer();

            System.out.println(key);
            System.out.println(item);
            System.out.println("YESSS");
            if (container.get(key, PersistentDataType.INTEGER) != null && container.get(key, PersistentDataType.INTEGER) != 0) {
                System.out.println(container.get(key, PersistentDataType.INTEGER));
                System.out.println("yes");
            }
        }
    }

    public static void injectPlugin(ZombieApocalypse plugin) {
        RangedWeaponInterpreter.plugin = plugin;
    }
}
