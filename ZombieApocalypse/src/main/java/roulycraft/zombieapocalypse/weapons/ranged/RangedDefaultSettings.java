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

        {
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
                0.25,
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
                    0.25,
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
                    0.25,
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
                    0.25,
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
        } // ID: 1

        {

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
                    "BLOCK_GLASS_PLACE|0.2|1.5|0;ENTITY_IRON_GOLEM_STEP|1|1.5|0;ENTITY_VEX_HURT|1|2|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|1|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|38;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|40",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    2,
                    (rangedLvl1 + "Pistolet Mało Maszynowy"),
                    1,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    2,
                    4,
                    0,
                    1.1,
                    1,
                    2,
                    0.15,
                    0.43,
                    1.0,
                    15.0,
                    0.2,
                    25,
                    2.2,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_PLACE|0.2|1.5|0;ENTITY_IRON_GOLEM_STEP|1|1.5|0;ENTITY_VEX_HURT|1|2|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|1|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|36;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|38",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    2,
                    (rangedLvl2 + "Pistolet Mało Maszynowy"),
                    2,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    2,
                    4,
                    0,
                    1.2,
                    1,
                    2,
                    0.15,
                    0.36,
                    1.0,
                    12.5,
                    0.2,
                    30,
                    2.2,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_PLACE|0.2|1.5|0;ENTITY_IRON_GOLEM_STEP|1|1.5|0;ENTITY_VEX_HURT|1|2|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|1|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|36;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|38",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    2,
                    (rangedLvl3 + "Pistolet Mało Maszynowy"),
                    3,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    2,
                    5,
                    0,
                    1.3,
                    1,
                    2,
                    0.15,
                    0.3,
                    1.0,
                    12.5,
                    0.2,
                    30,
                    2.0,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_PLACE|0.2|1.5|0;ENTITY_IRON_GOLEM_STEP|1|1.5|0;ENTITY_VEX_HURT|1|2|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|1|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|32;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|34",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );
        } // ID: 2

        {
            RangedManager.getManager().createRangedInstance(

                    3,
                    (rangedLvl0 + "Seryjny Pistolet"),
                    0,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    2,
                    5,
                    0,
                    1.25,
                    1,
                    3,
                    0.1,
                    0.1,
                    1.7,
                    20.0,
                    0.3,
                    12,
                    1.8,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_HIT|0.8|2|0;ENTITY_CHICKEN_STEP|0.4|0.75|0",
                    "BLOCK_TRIPWIRE_ATTACH|0.4|1.5|4;BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|6;BLOCK_IRON_TRAPDOOR_CLOSE|1|1.5|32",
                    "BLOCK_PISTON_EXTEND|0.25|1.5|4;BLOCK_PISTON_EXTEND|0.25|2|6"

            );

            RangedManager.getManager().createRangedInstance(

                    3,
                    (rangedLvl1 + "Seryjny Pistolet"),
                    1,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    3,
                    5,
                    0,
                    1.25,
                    1,
                    3,
                    0.1,
                    0.1,
                    1.5,
                    20.0,
                    0.3,
                    12,
                    1.8,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_HIT|0.8|2|0;ENTITY_CHICKEN_STEP|0.4|0.75|0",
                    "BLOCK_TRIPWIRE_ATTACH|0.4|1.5|4;BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|6;BLOCK_IRON_TRAPDOOR_CLOSE|1|1.5|32",
                    "BLOCK_PISTON_EXTEND|0.25|1.5|4;BLOCK_PISTON_EXTEND|0.25|2|6"

            );

            RangedManager.getManager().createRangedInstance(

                    3,
                    (rangedLvl2 + "Seryjny Pistolet"),
                    2,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    3,
                    5,
                    0,
                    1.25,
                    1,
                    3,
                    0.1,
                    0.1,
                    1.3,
                    20.0,
                    0.3,
                    15,
                    1.8,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_HIT|0.8|2|0;ENTITY_CHICKEN_STEP|0.4|0.75|0",
                    "BLOCK_TRIPWIRE_ATTACH|0.4|1.5|4;BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|6;BLOCK_IRON_TRAPDOOR_CLOSE|1|1.5|32",
                    "BLOCK_PISTON_EXTEND|0.25|1.5|4;BLOCK_PISTON_EXTEND|0.25|2|6"

            );

            RangedManager.getManager().createRangedInstance(

                    3,
                    (rangedLvl3 + "Seryjny Pistolet"),
                    3,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    3,
                    6,
                    0,
                    1.25,
                    1,
                    3,
                    0.1,
                    0.1,
                    1.1,
                    20.0,
                    0.3,
                    18,
                    1.8,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_HIT|0.8|2|0;ENTITY_CHICKEN_STEP|0.4|0.75|0",
                    "BLOCK_TRIPWIRE_ATTACH|0.4|1.5|4;BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|6;BLOCK_IRON_TRAPDOOR_CLOSE|1|1.5|32",
                    "BLOCK_PISTON_EXTEND|0.25|1.5|4;BLOCK_PISTON_EXTEND|0.25|2|6"

            );

        } // ID: 3

        {
            RangedManager.getManager().createRangedInstance(

                    4,
                    (rangedLvl0 + "Trój-strzałowy Pistolet"),
                    0,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    2,
                    5,
                    0,
                    1.25,
                    3,
                    1,
                    0.1,
                    0.1,
                    1.7,
                    20.0,
                    0.3,
                    5,
                    0.4,
                    "multipleBullets-1",
                    "breakAction",
                    0.5,
                    0,
                    1,
                    0.2,
                    "BLOCK_GLASS_HIT|0.8|2|0;ENTITY_FIREWORK_ROCKET_BLAST_FAR|1|1.5|0",
                    "ENTITY_IRON_GOLEM_ATTACK|1|1.75|4",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|4][BLOCK_IRON_TRAPDOOR_CLOSE|1|1.5|4"

            );

            RangedManager.getManager().createRangedInstance(

                    4,
                    (rangedLvl0 + "Trój-strzałowy Pistolet"),
                    1,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    2,
                    5,
                    0,
                    1.25,
                    1,
                    1,
                    0.1,
                    0.1,
                    1.7,
                    20.0,
                    0.3,
                    5,
                    0.4,
                    "multipleBullets-1",
                    "breakAction",
                    0.5,
                    0,
                    1,
                    0.2,
                    "BLOCK_GLASS_HIT|0.8|2|0;ENTITY_FIREWORK_ROCKET_BLAST_FAR|1|1.5|0",
                    "ENTITY_IRON_GOLEM_ATTACK|1|1.75|4",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|4][BLOCK_IRON_TRAPDOOR_CLOSE|1|1.5|4"

            );

            RangedManager.getManager().createRangedInstance(

                    4,
                    (rangedLvl0 + "Trój-strzałowy Pistolet"),
                    2,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    2,
                    5,
                    0,
                    1.25,
                    1,
                    1,
                    0.1,
                    0.1,
                    1.7,
                    20.0,
                    0.3,
                    5,
                    0.4,
                    "multipleBullets-1",
                    "breakAction",
                    0.5,
                    0,
                    1,
                    0.2,
                    "BLOCK_GLASS_HIT|0.8|2|0;ENTITY_FIREWORK_ROCKET_BLAST_FAR|1|1.5|0",
                    "ENTITY_IRON_GOLEM_ATTACK|1|1.75|4",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|4][BLOCK_IRON_TRAPDOOR_CLOSE|1|1.5|4"

            );

            RangedManager.getManager().createRangedInstance(

                    4,
                    (rangedLvl0 + "Trój-strzałowy Pistolet"),
                    3,
                    new ItemStack(Material.IRON_HORSE_ARMOR, 1),
                    2,
                    5,
                    0,
                    1.25,
                    1,
                    1,
                    0.1,
                    0.1,
                    1.7,
                    20.0,
                    0.3,
                    5,
                    0.4,
                    "multipleBullets-1",
                    "breakAction",
                    0.5,
                    0,
                    1,
                    0.2,
                    "BLOCK_GLASS_HIT|0.8|2|0;ENTITY_FIREWORK_ROCKET_BLAST_FAR|1|1.5|0",
                    "ENTITY_IRON_GOLEM_ATTACK|1|1.75|4",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|4][BLOCK_IRON_TRAPDOOR_CLOSE|1|1.5|4"

            );


        } // ID: 4

    }

}
