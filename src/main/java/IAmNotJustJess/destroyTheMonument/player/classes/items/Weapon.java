package IAmNotJustJess.destroyTheMonument.player.classes.items;

import IAmNotJustJess.destroyTheMonument.utility.MiniMessageParser;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import IAmNotJustJess.destroyTheMonument.player.classes.effects.Effect;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Weapon {

    public String name;
    public String description;
    public WeaponType weaponType;
    public ItemStack item;
    public Integer damage;
    public Integer baseDamage;
    public ArrayList<Effect> effects;

    Weapon(String name, String description, WeaponType weaponType, ItemStack item, Integer damage) {
        this.name = name;
        this.description = description;
        this.weaponType = weaponType;
        this.item = item;
        this.damage = damage;
        this.baseDamage = damage;
        this.effects = new ArrayList<>();
    }

    public ItemStack generateItem() {

        ItemStack itemStack = item.clone();
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.setItemName(MiniMessageParser.Deserialize(name));
        itemMeta.setLore(MiniMessageParser.DeserializeMultiline(description));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
