package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.utility.ConfigColourParser;

public class RangedDefaultSettings {

    public static ZombieApocalypse plugin;

    public static void injectPlugin(ZombieApocalypse p) {

        plugin = p;

    }

    public static void loadDefaultSettings() {

        String rangedLvl0 = ConfigColourParser.getColour(plugin.getConfig().getString("guns.colours.level0.primary"));
        String rangedLvl1 = ConfigColourParser.getColour(plugin.getConfig().getString("guns.colours.level1.primary"));
        String rangedLvl2 = ConfigColourParser.getColour(plugin.getConfig().getString("guns.colours.level2.primary"));
        String rangedLvl3 = ConfigColourParser.getColour(plugin.getConfig().getString("guns.colours.level3.primary"));

        { // ID: 1
            RangedManager.getManager().createRangedInstance(

                1,
                (rangedLvl0 + "Pistolet"),
                0,
                new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                4,
                8,
                0,
                1.0,
                1,
                1,
                0.0,
                0.2,
                1.3,
                25.0,
                0.2,
                8,
                1.6,
                "clip",
                "slide",
                0.5,
                0,
                0,
                0.0,
                "ENTITY_GHAST_SHOOT|0.2|1.5|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                "BLOCK_IRON_TRAPDOOR_OPEN|1|2|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|28",
                "BLOCK_PISTON_EXTEND|0.25|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    1,
                    (rangedLvl1 + "Pistolet"),
                    1,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    5,
                    9,
                    0,
                    1.0,
                    1,
                    1,
                    0.0,
                    0.2,
                    1.2,
                    25.0,
                    0.2,
                    8,
                    1.4,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "ENTITY_GHAST_SHOOT|0.2|1.5|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|2|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|20",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    1,
                    (rangedLvl2 + "Pistolet"),
                    2,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    5,
                    9,
                    0,
                    1.0,
                    1,
                    1,
                    0.0,
                    0.2,
                    1.1,
                    20.0,
                    0.2,
                    12,
                    1.4,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "ENTITY_GHAST_SHOOT|0.2|1.5|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|2|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|20",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    1,
                    (rangedLvl3 + "Pistolet"),
                    3,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    6,
                    10,
                    0,
                    1.0,
                    1,
                    1,
                    0.0,
                    0.2,
                    1.0,
                    15.0,
                    0.2,
                    12,
                    1.4,
                    "clip",
                    "slide",
                    0.4,
                    0,
                    0,
                    0.0,
                    "ENTITY_GHAST_SHOOT|0.2|1.5|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|2|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|20",
                    "BLOCK_PISTON_EXTEND|0.25|2|3"

            );
        }

        { // ID: 2

            RangedManager.getManager().createRangedInstance(

                    2,
                    (rangedLvl0 + "Pistolet Mało Maszynowy"),
                    0,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    1,
                    4,
                    0,
                    1.0,
                    1,
                    2,
                    0.15,
                    0.5,
                    1.0,
                    15.0,
                    0.2,
                    25,
                    2.3,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_PLACE|0.2|1.5|0;ENTITY_PLAYER_ATTACK_WEAK|1|2|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|1|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|38;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|40",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );
            RangedManager.getManager().createRangedInstance(

                    2,
                    (rangedLvl0 + "Pistolet Mało Maszynowy"),
                    1,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    1,
                    4,
                    0,
                    1.0,
                    1,
                    2,
                    0.15,
                    0.5,
                    1.0,
                    15.0,
                    0.2,
                    25,
                    2.3,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_PLACE|0.2|1.5|0;ENTITY_PLAYER_ATTACK_WEAK|1|1.5|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|1|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|38;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|40",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );
            RangedManager.getManager().createRangedInstance(

                    2,
                    (rangedLvl0 + "Pistolet Mało Maszynowy"),
                    2,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    1,
                    4,
                    0,
                    1.0,
                    1,
                    2,
                    0.15,
                    0.5,
                    1.0,
                    15.0,
                    0.2,
                    25,
                    2.3,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_PLACE|0.2|1.5|0;ENTITY_PLAYER_ATTACK_WEAK|1|2|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|1|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|38;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|40",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );
            RangedManager.getManager().createRangedInstance(

                    2,
                    (rangedLvl0 + "Pistolet Mało Maszynowy"),
                    3,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    1,
                    4,
                    0,
                    1.0,
                    1,
                    2,
                    0.15,
                    0.5,
                    1.0,
                    15.0,
                    0.2,
                    25,
                    2.3,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_PLACE|0.2|1.5|0;ENTITY_PLAYER_ATTACK_WEAK|1|2|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|1|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|38;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|40",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );

        }

    }

}
