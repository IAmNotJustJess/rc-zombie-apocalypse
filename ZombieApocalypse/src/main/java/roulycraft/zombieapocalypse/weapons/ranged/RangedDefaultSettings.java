package roulycraft.zombieapocalypse.weapons.ranged;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import roulycraft.zombieapocalypse.ZombieApocalypse;

public class RangedDefaultSettings {

    public static ZombieApocalypse plugin;
    public static String[] rangedLvl = {"", "", "", ""};

    public static void injectPlugin(ZombieApocalypse p) {

        plugin = p;

        rangedLvl[0] = plugin.getConfig().getString("messages.plugin.guns.level0.primary");
        rangedLvl[1] = plugin.getConfig().getString("messages.plugin.guns.level1.primary");
        rangedLvl[2] = plugin.getConfig().getString("messages.plugin.guns.level2.primary");
        rangedLvl[3] = plugin.getConfig().getString("messages.plugin.guns.level3.primary");

    }

    public static void loadDefaultSettings() {

        {
            RangedManager.getManager().createRangedInstance(

                1,
                rangedLvl[0]+"Pistolet",
                0,
                Material.IRON_HORSE_ARMOR,
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
                1,
                0.5,
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
                rangedLvl[1]+"Pistolet",
                1,
                Material.IRON_HORSE_ARMOR,
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
                1,
                0.5,
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
                rangedLvl[2]+"Pistolet",
                2,
                Material.IRON_HORSE_ARMOR,
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
                1,
                0.5,
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
                rangedLvl[3]+"Pistolet",
                3,
                Material.IRON_HORSE_ARMOR,
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
                1,
                0.5,
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
                    rangedLvl[0]+"Pistolet Mało Maszynowy",
                    0,
                    Material.IRON_HORSE_ARMOR,
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
                    1,
                    0.75,
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
                    rangedLvl[1]+"Pistolet Mało Maszynowy",
                    1,
                    Material.IRON_HORSE_ARMOR,
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
                    1,
                    0.75,
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
                    rangedLvl[2]+"Pistolet Mało Maszynowy",
                    2,
                    Material.IRON_HORSE_ARMOR,
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
                    1,
                    0.75,
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
                    rangedLvl[3]+"Pistolet Mało Maszynowy",
                    3,
                    Material.IRON_HORSE_ARMOR,
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
                    1,
                    0.75,
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
                    rangedLvl[0]+"Seryjny Pistolet",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    3,
                    6,
                    0,
                    1.25,
                    1,
                    3,
                    0.1,
                    0.1,
                    1.7,
                    20.0,
                    1,
                    0.5,
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
                    rangedLvl[1]+"Seryjny Pistolet",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    4,
                    6,
                    0,
                    1.25,
                    1,
                    3,
                    0.1,
                    0.1,
                    1.5,
                    20.0,
                    1,
                    0.5,
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
                    rangedLvl[2]+"Seryjny Pistolet",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    4,
                    7,
                    0,
                    1.25,
                    1,
                    3,
                    0.1,
                    0.1,
                    1.3,
                    20.0,
                    1,
                    0.5,
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
                    rangedLvl[3]+"Seryjny Pistolet",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    5,
                    7,
                    0,
                    1.25,
                    1,
                    3,
                    0.1,
                    0.1,
                    1.1,
                    20.0,
                    1,
                    0.5,
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
                    rangedLvl[0]+"Trój-strzałowy Pistolet",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    3,
                    6,
                    0,
                    1.25,
                    3,
                    1,
                    0.1,
                    0.0,
                    1.0,
                    50.0,
                    1,
                    0.5,
                    0.3,
                    4,
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
                    rangedLvl[1]+"Trój-strzałowy Pistolet",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    4,
                    6,
                    0,
                    1.25,
                    3,
                    1,
                    0.1,
                    0.0,
                    0.9,
                    50.0,
                    1,
                    0.5,
                    0.3,
                    4,
                    0.4,
                    "multipleBullets-1",
                    "breakAction",
                    0.5,
                    0,
                    1,
                    0.16,
                    "BLOCK_GLASS_HIT|0.8|2|0;ENTITY_FIREWORK_ROCKET_BLAST_FAR|1|1.5|0",
                    "ENTITY_IRON_GOLEM_ATTACK|1|1.75|4",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|4][BLOCK_IRON_TRAPDOOR_CLOSE|1|1.5|4"

            );

            RangedManager.getManager().createRangedInstance(

                    4,
                    rangedLvl[2]+"Trój-strzałowy Pistolet",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    4,
                    7,
                    0,
                    1.25,
                    3,
                    1,
                    0.1,
                    0.0,
                    0.8,
                    33.0,
                    1,
                    0.5,
                    0.3,
                    5,
                    0.4,
                    "multipleBullets-1",
                    "breakAction",
                    0.5,
                    0,
                    1,
                    0.13,
                    "BLOCK_GLASS_HIT|0.8|2|0;ENTITY_FIREWORK_ROCKET_BLAST_FAR|1|1.5|0",
                    "ENTITY_IRON_GOLEM_ATTACK|1|1.75|4",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|4][BLOCK_IRON_TRAPDOOR_CLOSE|1|1.5|4"

            );


            RangedManager.getManager().createRangedInstance(

                    4,
                    rangedLvl[3]+"Trój-strzałowy Pistolet",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    4,
                    8,
                    0,
                    1.25,
                    3,
                    1,
                    0.0,
                    0.1,
                    0.7,
                    33.0,
                    1,
                    0.5,
                    0.3,
                    5,
                    0.4,
                    "multipleBullets-1",
                    "breakAction",
                    0.5,
                    0,
                    1,
                    0.1,
                    "BLOCK_GLASS_HIT|0.8|2|0;ENTITY_FIREWORK_ROCKET_BLAST_FAR|1|1.5|0",
                    "ENTITY_IRON_GOLEM_ATTACK|1|1.75|4",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|4][BLOCK_IRON_TRAPDOOR_CLOSE|1|1.5|4"

            );

        } // ID: 4

        {
            RangedManager.getManager().createRangedInstance(

                    5,
                    rangedLvl[0]+"Dubeltówka",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    1,
                    3,
                    0,
                    0.6,
                    12,
                    2,
                    0.2,
                    1.3,
                    1.2,
                    100.0,
                    1,
                    0.85,
                    0.3,
                    2,
                    0.7,
                    "multipleBullets-1",
                    "breakAction",
                    0.3,
                    0,
                    0,
                    0.0,
                    "ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0;ENTITY_GENERIC_EXPLODE|1|2|0;ENTITY_GHAST_SHOOT|1|2|0",
                    "BLOCK_NOTE_BLOCK_HAT|1|2|8;BLOCK_NOTE_BLOCK_SNARE|1|2|8",
                    "BLOCK_IRON_DOOR_OPEN|1|1.5|4][BLOCK_IRON_DOOR_CLOSE|1|1.5|4"

            );

            RangedManager.getManager().createRangedInstance(

                    5,
                    rangedLvl[1]+"Dubeltówka",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    1,
                    3,
                    0,
                    0.6,
                    12,
                    2,
                    0.2,
                    1.25,
                    1.2,
                    100.0,
                    1,
                    0.85,
                    0.5,
                    2,
                    0.7,
                    "multipleBullets-1",
                    "breakAction",
                    0.3,
                    0,
                    0,
                    0.0,
                    "ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0;ENTITY_GENERIC_EXPLODE|1|2|0;ENTITY_GHAST_SHOOT|1|2|0",
                    "BLOCK_NOTE_BLOCK_HAT|1|2|8;BLOCK_NOTE_BLOCK_SNARE|1|2|8",
                    "BLOCK_IRON_DOOR_OPEN|1|1.5|4][BLOCK_IRON_DOOR_CLOSE|1|1.5|4"

            );

            RangedManager.getManager().createRangedInstance(

                    5,
                    rangedLvl[2]+"Dubeltówka",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    2,
                    3,
                    0,
                    0.6,
                    12,
                    2,
                    0.15,
                    1.2,
                    1.2,
                    100.0,
                    1,
                    0.85,
                    0.5,
                    2,
                    0.7,
                    "multipleBullets-1",
                    "breakAction",
                    0.3,
                    0,
                    0,
                    0.0,
                    "ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0;ENTITY_GENERIC_EXPLODE|1|2|0;ENTITY_GHAST_SHOOT|1|2|0",
                    "BLOCK_NOTE_BLOCK_HAT|1|2|8;BLOCK_NOTE_BLOCK_SNARE|1|2|8",
                    "BLOCK_IRON_DOOR_OPEN|1|1.5|4][BLOCK_IRON_DOOR_CLOSE|1|1.5|4"

            );

            RangedManager.getManager().createRangedInstance(

                    5,
                    rangedLvl[3]+"Dubeltówka",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    2,
                    3,
                    0,
                    0.6,
                    14,
                    2,
                    0.15,
                    1.15,
                    1.2,
                    100.0,
                    1,
                    0.85,
                    0.5,
                    2,
                    0.7,
                    "multipleBullets-1",
                    "breakAction",
                    0.3,
                    0,
                    0,
                    0.0,
                    "ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0;ENTITY_GENERIC_EXPLODE|1|2|0;ENTITY_GHAST_SHOOT|1|2|0",
                    "BLOCK_NOTE_BLOCK_HAT|1|2|8;BLOCK_NOTE_BLOCK_SNARE|1|2|8",
                    "BLOCK_IRON_DOOR_OPEN|1|1.5|4][BLOCK_IRON_DOOR_CLOSE|1|1.5|4"

            );

        } // ID: 5

        {
            RangedManager.getManager().createRangedInstance(

                    6,
                    rangedLvl[0]+"Rewolwer",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    12,
                    20,
                    0,
                    1.0,
                    1,
                    1,
                    0.0,
                    0.03,
                    1.3,
                    50.0,
                    2,
                    0.35,
                    0.4,
                    6,
                    2.2,
                    "clip",
                    "slide",
                    0.3,
                    0,
                    0,
                    0.0,
                    "ENTITY_ZOMBIE_ATTACK_IRON_DOOR|0.5|2|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|0.8|4;BLOCK_STONE_STEP|1|2|10;BLOCK_STONE_STEP|1|2|11;BLOCK_STONE_STEP|1|2|12;BLOCK_STONE_STEP|1|2|14;BLOCK_STONE_STEP|1|2|18;BLOCK_IRON_TRAPDOOR_CLOSE|1|0.8|34",
                    "ITEM_FLINTANDSTEEL_USE|1|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    6,
                    rangedLvl[1]+"Rewolwer",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    12,
                    22,
                    0,
                    1.15,
                    1,
                    1,
                    0.0,
                    0.025,
                    1.3,
                    50.0,
                    2,
                    0.35,
                    0.4,
                    6,
                    2.2,
                    "clip",
                    "slide",
                    0.3,
                    0,
                    0,
                    0.0,
                    "ENTITY_ZOMBIE_ATTACK_IRON_DOOR|0.5|2|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|0.8|4;BLOCK_STONE_STEP|1|2|10;BLOCK_STONE_STEP|1|2|11;BLOCK_STONE_STEP|1|2|12;BLOCK_STONE_STEP|1|2|14;BLOCK_STONE_STEP|1|2|18;BLOCK_IRON_TRAPDOOR_CLOSE|1|0.8|34",
                    "ITEM_FLINTANDSTEEL_USE|1|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    6,
                    rangedLvl[2]+"Rewolwer",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    12,
                    24,
                    0,
                    1.15,
                    1,
                    1,
                    0.0,
                    0.02,
                    1.3,
                    50.0,
                    2,
                    0.35,
                    0.4,
                    6,
                    2.2,
                    "clip",
                    "slide",
                    0.3,
                    0,
                    0,
                    0.0,
                    "ENTITY_ZOMBIE_ATTACK_IRON_DOOR|0.5|2|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|0.8|4;BLOCK_STONE_STEP|1|2|10;BLOCK_STONE_STEP|1|2|11;BLOCK_STONE_STEP|1|2|12;BLOCK_STONE_STEP|1|2|14;BLOCK_STONE_STEP|1|2|18;BLOCK_IRON_TRAPDOOR_CLOSE|1|0.8|34",
                    "ITEM_FLINTANDSTEEL_USE|1|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    6,
                    rangedLvl[3]+"Rewolwer",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    16,
                    24,
                    0,
                    1.15,
                    1,
                    1,
                    0.0,
                    0.015,
                    1.3,
                    50.0,
                    2,
                    0.35,
                    0.4,
                    6,
                    2.05,
                    "clip",
                    "slide",
                    0.3,
                    0,
                    0,
                    0.0,
                    "ENTITY_ZOMBIE_ATTACK_IRON_DOOR|0.5|2|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|0.8|4;BLOCK_STONE_STEP|1|2|10;BLOCK_STONE_STEP|1|2|11;BLOCK_STONE_STEP|1|2|12;BLOCK_STONE_STEP|1|2|14;BLOCK_STONE_STEP|1|2|18;BLOCK_IRON_TRAPDOOR_CLOSE|1|0.8|28",
                    "ITEM_FLINTANDSTEEL_USE|1|2|4"

            );

        } // ID: 6

        {
            RangedManager.getManager().createRangedInstance(

                    7,
                    rangedLvl[0]+"Karabin Wyborowy",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    10,
                    12,
                    0,
                    1.5,
                    1,
                    1,
                    0.0,
                    0.0,
                    1.5,
                    25.0,
                    3,
                    0.25,
                    0.25,
                    8,
                    2.5,
                    "clip",
                    "slide",
                    0.65,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                    "BLOCK_WOODEN_TRAPDOOR_OPEN|1|2|4;BLOCK_WOOD_BREAK|1|1.75|28;BLOCK_WOOD_BREAK|1|2|34;BLOCK_WOODEN_TRAPDOOR_CLOSE|1|2|40",
                    "BLOCK_PISTON_EXTEND|0.25|1.5|5;BLOCK_PISTON_EXTEND|0.25|2|10"

            );

            RangedManager.getManager().createRangedInstance(

                    7,
                    rangedLvl[1]+"Karabin Wyborowy",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    10,
                    12,
                    0,
                    1.5,
                    1,
                    1,
                    0.0,
                    0.0,
                    1.5,
                    25.0,
                    3,
                    0.25,
                    0.25,
                    10,
                    2.5,
                    "clip",
                    "slide",
                    0.65,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                    "BLOCK_WOODEN_TRAPDOOR_OPEN|1|2|4;BLOCK_WOOD_BREAK|1|1.75|28;BLOCK_WOOD_BREAK|1|2|34;BLOCK_WOODEN_TRAPDOOR_CLOSE|1|2|40",
                    "BLOCK_PISTON_EXTEND|0.25|1.5|5;BLOCK_PISTON_EXTEND|0.25|2|10"

            );

            RangedManager.getManager().createRangedInstance(

                    7,
                    rangedLvl[2]+"Karabin Wyborowy",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    11,
                    13,
                    0,
                    1.5,
                    1,
                    1,
                    0.0,
                    0.0,
                    1.5,
                    25.0,
                    3,
                    0.25,
                    0.25,
                    10,
                    2.5,
                    "clip",
                    "slide",
                    0.65,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                    "BLOCK_WOODEN_TRAPDOOR_OPEN|1|2|4;BLOCK_WOOD_BREAK|1|1.75|28;BLOCK_WOOD_BREAK|1|2|34;BLOCK_WOODEN_TRAPDOOR_CLOSE|1|2|40",
                    "BLOCK_PISTON_EXTEND|0.25|1.5|5;BLOCK_PISTON_EXTEND|0.25|2|10"

            );

            RangedManager.getManager().createRangedInstance(

                    7,
                    rangedLvl[3]+"Karabin Wyborowy",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    12,
                    14,
                    0,
                    1.5,
                    1,
                    1,
                    0.0,
                    0.0,
                    1.5,
                    25.0,
                    3,
                    0.25,
                    0.25,
                    12,
                    2.5,
                    "clip",
                    "slide",
                    0.65,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|1|2|0;ENTITY_ARMOR_STAND_HIT|1|2|0",
                    "BLOCK_WOODEN_TRAPDOOR_OPEN|1|2|4;BLOCK_WOOD_BREAK|1|1.75|28;BLOCK_WOOD_BREAK|1|2|34;BLOCK_WOODEN_TRAPDOOR_CLOSE|1|2|40",
                    "BLOCK_PISTON_EXTEND|0.25|1.5|5;BLOCK_PISTON_EXTEND|0.25|2|10"

            );

        } // ID: 7

        {
            RangedManager.getManager().createRangedInstance(

                    8,
                    rangedLvl[0]+"Karabin Szturmowy",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    6,
                    8,
                    0,
                    1.5,
                    1,
                    1,
                    0.0,
                    0.05,
                    1.45,
                    10.0,
                    1,
                    0.5,
                    0.15,
                    20,
                    2.0,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "ENTITY_IRON_GOLEM_HURT|1|2|0;ENTITY_SKELETON_HURT|0.5|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|0.3|2|0",
                    "BLOCK_WOODEN_DOOR_OPEN|1|2|4;BLOCK_WOODEN_DOOR_OPEN|1|1.5|8;BLOCK_WOODEN_DOOR_CLOSE|1|1.5|30;BLOCK_WOODEN_DOOR_CLOSE|1|2|34",
                    "BLOCK_PISTON_EXTEND|0.25|2|4;BLOCK_PISTON_EXTEND|0.25|2|8"

            );

            RangedManager.getManager().createRangedInstance(

                    8,
                    rangedLvl[1]+"Karabin Szturmowy",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    6,
                    9,
                    0,
                    1.5,
                    1,
                    1,
                    0.0,
                    0.05,
                    1.4,
                    10.0,
                    1,
                    0.5,
                    0.15,
                    20,
                    2.0,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "ENTITY_IRON_GOLEM_HURT|1|2|0;ENTITY_SKELETON_HURT|0.5|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|0.3|2|0",
                    "BLOCK_WOODEN_DOOR_OPEN|1|2|4;BLOCK_WOODEN_DOOR_OPEN|1|1.5|8;BLOCK_WOODEN_DOOR_CLOSE|1|1.5|30;BLOCK_WOODEN_DOOR_CLOSE|1|2|34",
                    "BLOCK_PISTON_EXTEND|0.25|2|4;BLOCK_PISTON_EXTEND|0.25|2|8"

            );

            RangedManager.getManager().createRangedInstance(

                    8,
                    rangedLvl[2]+"Karabin Szturmowy",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    7,
                    9,
                    0,
                    1.5,
                    1,
                    1,
                    0.0,
                    0.05,
                    1.35,
                    10.0,
                    1,
                    0.5,
                    0.15,
                    25,
                    2.0,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "ENTITY_IRON_GOLEM_HURT|1|2|0;ENTITY_SKELETON_HURT|0.5|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|0.3|2|0",
                    "BLOCK_WOODEN_DOOR_OPEN|1|2|4;BLOCK_WOODEN_DOOR_OPEN|1|1.5|8;BLOCK_WOODEN_DOOR_CLOSE|1|1.5|30;BLOCK_WOODEN_DOOR_CLOSE|1|2|34",
                    "BLOCK_PISTON_EXTEND|0.25|2|4;BLOCK_PISTON_EXTEND|0.25|2|8"

            );

            RangedManager.getManager().createRangedInstance(

                    8,
                    rangedLvl[3]+"Karabin Szturmowy",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    7,
                    10,
                    0,
                    1.5,
                    1,
                    1,
                    0.0,
                    0.05,
                    1.3,
                    8.0,
                    1,
                    0.5,
                    0.15,
                    25,
                    1.9,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "ENTITY_IRON_GOLEM_HURT|1|2|0;ENTITY_SKELETON_HURT|0.5|2|0;ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR|0.3|2|0",
                    "BLOCK_WOODEN_DOOR_OPEN|1|2|4;BLOCK_WOODEN_DOOR_OPEN|1|1.5|8;BLOCK_WOODEN_DOOR_CLOSE|1|1.5|28;BLOCK_WOODEN_DOOR_CLOSE|1|2|32",
                    "BLOCK_PISTON_EXTEND|0.25|2|4;BLOCK_PISTON_EXTEND|0.25|2|8"

            );
        } // ID: 8

        {
            RangedManager.getManager().createRangedInstance(

                    9,
                    rangedLvl[0]+"Strzelba",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    2,
                    4,
                    0,
                    1.0,
                    8,
                    1,
                    0.0,
                    0.7,
                    1.3,
                    50.0,
                    1,
                    0.9,
                    0.35,
                    5,
                    0.5,
                    "multipleBullets-1",
                    "pumpAction",
                    0.5,
                    1,
                    0,
                    0.0,
                    "ENTITY_WITHER_SHOOT|1|2|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "ENTITY_IRON_GOLEM_ATTACK|1|2|0",
                    "BLOCK_PISTON_EXTEND|0.25|2|2;BLOCK_PISTON_EXTEND|0.25|2|5"

            );

            RangedManager.getManager().createRangedInstance(

                    9,
                    rangedLvl[1]+"Strzelba",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    2,
                    4,
                    0,
                    1.0,
                    9,
                    1,
                    0.0,
                    0.7,
                    1.3,
                    50.0,
                    1,
                    0.9,
                    0.35,
                    5,
                    0.5,
                    "multipleBullets-1",
                    "pumpAction",
                    0.5,
                    1,
                    0,
                    0.0,
                    "ENTITY_WITHER_SHOOT|1|2|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "ENTITY_IRON_GOLEM_ATTACK|1|2|0",
                    "BLOCK_PISTON_EXTEND|0.25|2|2;BLOCK_PISTON_EXTEND|0.25|2|5"

            );

            RangedManager.getManager().createRangedInstance(

                    9,
                    rangedLvl[2]+"Strzelba",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    2,
                    5,
                    0,
                    1.0,
                    9,
                    1,
                    0.0,
                    0.7,
                    1.3,
                    50.0,
                    1,
                    0.9,
                    0.35,
                    6,
                    0.5,
                    "multipleBullets-1",
                    "pumpAction",
                    0.5,
                    1,
                    0,
                    0.0,
                    "ENTITY_WITHER_SHOOT|1|2|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "ENTITY_IRON_GOLEM_ATTACK|1|2|0",
                    "BLOCK_PISTON_EXTEND|0.25|2|2;BLOCK_PISTON_EXTEND|0.25|2|5"

            );

            RangedManager.getManager().createRangedInstance(

                    9,
                    rangedLvl[3]+"Strzelba",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    2,
                    5,
                    0,
                    1.0,
                    10,
                    1,
                    0.0,
                    0.7,
                    1.3,
                    50.0,
                    1,
                    0.9,
                    0.35,
                    6,
                    0.5,
                    "multipleBullets-1",
                    "pumpAction",
                    0.5,
                    1,
                    0,
                    0.0,
                    "ENTITY_WITHER_SHOOT|1|2|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "ENTITY_IRON_GOLEM_ATTACK|1|2|0",
                    "BLOCK_PISTON_EXTEND|0.25|2|2;BLOCK_PISTON_EXTEND|0.25|2|5"

            );
        } // ID: 9

        {
            RangedManager.getManager().createRangedInstance(

                    10,
                    rangedLvl[0]+"Karabin Myśliwski",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    25,
                    45,
                    0,
                    1.625,
                    1,
                    1,
                    0.0,
                    0.04,
                    2.0,
                    100.0,
                    3,
                    0.1,
                    0.25,
                    3,
                    2.0,
                    "clip",
                    "boltAction",
                    0.75,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|1|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.2|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|1.2|36",
                    "BLOCK_NOTE_BLOCK_HAT|1|0|0;BLOCK_PISTON_CONTRACT|0.25|2|2;BLOCK_PISTON_EXTEND|1|2|8"

            );

            RangedManager.getManager().createRangedInstance(

                    10,
                    rangedLvl[1]+"Karabin Myśliwski",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    25,
                    45,
                    0,
                    1.75,
                    1,
                    1,
                    0.0,
                    0.04,
                    2.0,
                    100.0,
                    3,
                    0.1,
                    0.25,
                    4,
                    2.0,
                    "clip",
                    "boltAction",
                    0.75,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|1|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.2|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|1.2|36",
                    "BLOCK_NOTE_BLOCK_HAT|1|0|0;BLOCK_PISTON_CONTRACT|0.25|2|2;BLOCK_PISTON_EXTEND|1|2|8"

            );

            RangedManager.getManager().createRangedInstance(

                    10,
                    rangedLvl[2]+"Karabin Myśliwski",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    30,
                    50,
                    0,
                    1.875,
                    1,
                    1,
                    0.0,
                    0.04,
                    2.0,
                    100.0,
                    3,
                    0.1,
                    0.25,
                    4,
                    2.0,
                    "clip",
                    "boltAction",
                    0.75,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|1|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.2|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|1.2|36",
                    "BLOCK_NOTE_BLOCK_HAT|1|0|0;BLOCK_PISTON_CONTRACT|0.25|2|2;BLOCK_PISTON_EXTEND|1|2|8"

            );

            RangedManager.getManager().createRangedInstance(

                    10,
                    rangedLvl[3]+"Karabin Myśliwski",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    35,
                    50,
                    0,
                    2.0,
                    1,
                    1,
                    0.0,
                    0.04,
                    2.0,
                    100.0,
                    3,
                    0.1,
                    0.25,
                    5,
                    2.0,
                    "clip",
                    "boltAction",
                    0.75,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|1|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.2|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|1.2|36",
                    "BLOCK_NOTE_BLOCK_HAT|1|0|0;BLOCK_PISTON_CONTRACT|0.25|2|2;BLOCK_PISTON_EXTEND|1|2|8"

            );
        } // ID: 10

        {
            RangedManager.getManager().createRangedInstance(

                    11,
                    rangedLvl[0]+"Kuszabin",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    12,
                    18,
                    2,
                    1.0,
                    1,
                    1,
                    0.0,
                    0.05,
                    0.65,
                    25.0,
                    2,
                    0.8,
                    0.3,
                    12,
                    2.0,
                    "clip",
                    "boltAction",
                    0.5,
                    2,
                    0,
                    0.0,
                    "ENTITY_GHAST_SHOOT|0.2|1.5|0;ENTITY_ARROW_SHOOT|1|1.5|0",
                    "BLOCK_WOODEN_TRAPDOOR_OPEN|1|2|4;BLOCK_WOOD_BREAK|1|1.5|10;BLOCK_WOOD_BREAK|1|1.6|11;BLOCK_WOOD_BREAK|1|1.7|12;BLOCK_WOOD_BREAK|1|1.8|13;BLOCK_WOOD_BREAK|1|1.9|14;BLOCK_WOOD_BREAK|1|1.7|20;BLOCK_WOODEN_TRAPDOOR_CLOSE|1|2|28",
                    "ITEM_CROSSBOW_LOADING_START|1|1.5|0;ITEM_CROSSBOW_LOADING_END|1|1.5|4"

            );

            RangedManager.getManager().createRangedInstance(

                    11,
                    rangedLvl[1]+"Kuszabin",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    12,
                    18,
                    2,
                    1.0,
                    1,
                    1,
                    0.0,
                    0.05,
                    0.65,
                    25.0,
                    2,
                    0.8,
                    0.3,
                    12,
                    2.0,
                    "clip",
                    "boltAction",
                    0.5,
                    2,
                    0,
                    0.0,
                    "ENTITY_GHAST_SHOOT|0.2|1.5|0;ENTITY_ARROW_SHOOT|1|1.5|0",
                    "BLOCK_WOODEN_TRAPDOOR_OPEN|1|2|4;BLOCK_WOOD_BREAK|1|1.5|10;BLOCK_WOOD_BREAK|1|1.6|11;BLOCK_WOOD_BREAK|1|1.7|12;BLOCK_WOOD_BREAK|1|1.8|13;BLOCK_WOOD_BREAK|1|1.9|14;BLOCK_WOOD_BREAK|1|1.7|20;BLOCK_WOODEN_TRAPDOOR_CLOSE|1|2|28",
                    "ITEM_CROSSBOW_LOADING_START|1|1.5|0;ITEM_CROSSBOW_LOADING_END|1|1.5|4"

            );

            RangedManager.getManager().createRangedInstance(

                    11,
                    rangedLvl[2]+"Kuszabin",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    14,
                    20,
                    2,
                    1.0,
                    1,
                    1,
                    0.0,
                    0.05,
                    0.65,
                    25.0,
                    2,
                    0.8,
                    0.3,
                    15,
                    2.0,
                    "clip",
                    "boltAction",
                    0.5,
                    2,
                    0,
                    0.0,
                    "ENTITY_GHAST_SHOOT|0.2|1.5|0;ENTITY_ARROW_SHOOT|1|1.5|0",
                    "BLOCK_WOODEN_TRAPDOOR_OPEN|1|2|4;BLOCK_WOOD_BREAK|1|1.5|10;BLOCK_WOOD_BREAK|1|1.6|11;BLOCK_WOOD_BREAK|1|1.7|12;BLOCK_WOOD_BREAK|1|1.8|13;BLOCK_WOOD_BREAK|1|1.9|14;BLOCK_WOOD_BREAK|1|1.7|20;BLOCK_WOODEN_TRAPDOOR_CLOSE|1|2|28",
                    "ITEM_CROSSBOW_LOADING_START|1|1.5|0;ITEM_CROSSBOW_LOADING_END|1|1.5|4"

            );

            RangedManager.getManager().createRangedInstance(

                    11,
                    rangedLvl[3]+"Kuszabin",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    16,
                    22,
                    2,
                    1.0,
                    1,
                    1,
                    0.0,
                    0.05,
                    0.65,
                    25.0,
                    2,
                    0.8,
                    0.3,
                    15,
                    2.0,
                    "clip",
                    "boltAction",
                    0.5,
                    3,
                    0,
                    0.0,
                    "ENTITY_GHAST_SHOOT|0.2|1.5|0;ENTITY_ARROW_SHOOT|1|1.5|0",
                    "BLOCK_WOODEN_TRAPDOOR_OPEN|1|2|4;BLOCK_WOOD_BREAK|1|1.5|10;BLOCK_WOOD_BREAK|1|1.6|11;BLOCK_WOOD_BREAK|1|1.7|12;BLOCK_WOOD_BREAK|1|1.8|13;BLOCK_WOOD_BREAK|1|1.9|14;BLOCK_WOOD_BREAK|1|1.7|20;BLOCK_WOODEN_TRAPDOOR_CLOSE|1|2|28",
                    "ITEM_CROSSBOW_LOADING_START|1|1.5|0;ITEM_CROSSBOW_LOADING_END|1|1.5|4"

            );

        } // ID: 11

        {
            RangedManager.getManager().createRangedInstance(

                    12,
                    rangedLvl[0]+"Garłacz",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    4,
                    7,
                    0,
                    0.5,
                    16,
                    1,
                    0.0,
                    3.2,
                    0.0,
                    0.0,
                    1,
                    0.95,
                    0.6,
                    1,
                    3.75,
                    "clip",
                    "slide",
                    0.0,
                    1,
                    0,
                    0.0,
                    "ENTITY_WITHER_SHOOT|1|1.5|0;ENTITY_GENERIC_EXPLODE|1|1|0;ENTITY_LIGHTNING_BOLT_THUNDER|0.4|2|0",
                    "ENTITY_SNOWBALL_THROW|0.2|0.6|4;BLOCK_GRASS_STEP|0.6|2|10;BLOCK_GRASS_STEP|0.6|2|16;BLOCK_GRASS_STEP|0.6|2|17;BLOCK_GRASS_STEP|0.6|2|18;BLOCK_GRASS_STEP|0.6|2|19;BLOCK_WOOD_PLACE|1|0.75|38;BLOCK_WOOD_PLACE|1|0.75|46;BLOCK_WOOD_PLACE|1|0.75|54;ENTITY_IRON_GOLEM_ATTACK|1|1.5|64;BLOCK_ANVIL_FALL|1|0.5|64",
                    ""

            );
            RangedManager.getManager().createRangedInstance(

                    12,
                    rangedLvl[1]+"Garłacz",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    4,
                    7,
                    0,
                    0.5,
                    18,
                    1,
                    0.0,
                    3.15,
                    0.0,
                    0.0,
                    1,
                    0.95,
                    0.6,
                    1,
                    3.75,
                    "clip",
                    "slide",
                    0.0,
                    1,
                    0,
                    0.0,
                    "ENTITY_WITHER_SHOOT|1|1.5|0;ENTITY_GENERIC_EXPLODE|1|1|0;ENTITY_LIGHTNING_BOLT_THUNDER|0.4|2|0",
                    "ENTITY_SNOWBALL_THROW|0.2|0.6|4;BLOCK_GRASS_STEP|0.6|2|10;BLOCK_GRASS_STEP|0.6|2|16;BLOCK_GRASS_STEP|0.6|2|17;BLOCK_GRASS_STEP|0.6|2|18;BLOCK_GRASS_STEP|0.6|2|19;BLOCK_WOOD_PLACE|1|0.75|38;BLOCK_WOOD_PLACE|1|0.75|46;BLOCK_WOOD_PLACE|1|0.75|54;ENTITY_IRON_GOLEM_ATTACK|1|1.5|64;BLOCK_ANVIL_FALL|1|0.5|64",
                    ""

            );
            RangedManager.getManager().createRangedInstance(

                    12,
                    rangedLvl[2]+"Garłacz",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    4,
                    7,
                    0,
                    0.5,
                    20,
                    1,
                    0.0,
                    3.1,
                    0.0,
                    0.0,
                    1,
                    0.95,
                    0.6,
                    1,
                    3.75,
                    "clip",
                    "slide",
                    0.0,
                    1,
                    0,
                    0.0,
                    "ENTITY_WITHER_SHOOT|1|1.5|0;ENTITY_GENERIC_EXPLODE|1|1|0;ENTITY_LIGHTNING_BOLT_THUNDER|0.4|2|0",
                    "ENTITY_SNOWBALL_THROW|0.2|0.6|4;BLOCK_GRASS_STEP|0.6|2|10;BLOCK_GRASS_STEP|0.6|2|16;BLOCK_GRASS_STEP|0.6|2|17;BLOCK_GRASS_STEP|0.6|2|18;BLOCK_GRASS_STEP|0.6|2|19;BLOCK_WOOD_PLACE|1|0.75|38;BLOCK_WOOD_PLACE|1|0.75|46;BLOCK_WOOD_PLACE|1|0.75|54;ENTITY_IRON_GOLEM_ATTACK|1|1.5|64;BLOCK_ANVIL_FALL|1|0.5|64",
                    ""

            );
            RangedManager.getManager().createRangedInstance(

                    12,
                    rangedLvl[3]+"Garłacz",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    4,
                    7,
                    0,
                    0.5,
                    22,
                    1,
                    0.0,
                    3.1,
                    0.0,
                    0.0,
                    1,
                    0.95,
                    0.6,
                    1,
                    3.65,
                    "clip",
                    "slide",
                    0.0,
                    1,
                    0,
                    0.0,
                    "ENTITY_WITHER_SHOOT|1|1.5|0;ENTITY_GENERIC_EXPLODE|1|1|0;ENTITY_LIGHTNING_BOLT_THUNDER|0.4|2|0",
                    "ENTITY_SNOWBALL_THROW|0.2|0.6|4;BLOCK_GRASS_STEP|0.6|2|10;BLOCK_GRASS_STEP|0.6|2|16;BLOCK_GRASS_STEP|0.6|2|17;BLOCK_GRASS_STEP|0.6|2|18;BLOCK_GRASS_STEP|0.6|2|19;BLOCK_WOOD_PLACE|1|0.75|38;BLOCK_WOOD_PLACE|1|0.75|46;BLOCK_WOOD_PLACE|1|0.75|54;ENTITY_IRON_GOLEM_ATTACK|1|1.5|64;BLOCK_ANVIL_FALL|1|0.5|64",
                    ""

            );

        } // ID: 12

        {
            RangedManager.getManager().createRangedInstance(

                    13,
                    rangedLvl[0]+"Karabin Snajperski",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    35,
                    60,
                    0,
                    2.0,
                    1,
                    1,
                    0.0,
                    0.02,
                    4.0,
                    100.0,
                    4,
                    0.05,
                    0.25,
                    2,
                    0.8,
                    "multipleBullets-1",
                    "leverAction",
                    0.45,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|0.75|0;ENTITY_GENERIC_EXPLODE|1|1.75|0;ENTITY_BLAZE_DEATH|0.2|2|0",
                    "BLOCK_NOTE_BLOCK_HAT|1|0.9|2",
                    "BLOCK_NOTE_BLOCK_HAT|1|0|0;BLOCK_PISTON_CONTRACT|0.25|1.5|2][BLOCK_PISTON_EXTEND|1|1.5|0"

            );

            RangedManager.getManager().createRangedInstance(

                    13,
                    rangedLvl[1]+"Karabin Snajperski",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    40,
                    65,
                    0,
                    2.0,
                    1,
                    1,
                    0.0,
                    0.02,
                    4.0,
                    100.0,
                    4,
                    0.05,
                    0.25,
                    2,
                    0.8,
                    "multipleBullets-1",
                    "leverAction",
                    0.45,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|0.75|0;ENTITY_GENERIC_EXPLODE|1|1.75|0;ENTITY_BLAZE_DEATH|0.2|2|0",
                    "BLOCK_NOTE_BLOCK_HAT|1|0.9|2",
                    "BLOCK_NOTE_BLOCK_HAT|1|0|0;BLOCK_PISTON_CONTRACT|0.25|1.5|2][BLOCK_PISTON_EXTEND|1|1.5|0"

            );

            RangedManager.getManager().createRangedInstance(

                    13,
                    rangedLvl[2]+"Karabin Snajperski",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    40,
                    65,
                    0,
                    2.0,
                    1,
                    1,
                    0.0,
                    0.02,
                    4.0,
                    100.0,
                    4,
                    0.05,
                    0.25,
                    3,
                    0.75,
                    "multipleBullets-1",
                    "leverAction",
                    0.45,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|0.75|0;ENTITY_GENERIC_EXPLODE|1|1.75|0;ENTITY_BLAZE_DEATH|0.2|2|0",
                    "BLOCK_NOTE_BLOCK_HAT|1|0.9|2",
                    "BLOCK_NOTE_BLOCK_HAT|1|0|0;BLOCK_PISTON_CONTRACT|0.25|1.5|2][BLOCK_PISTON_EXTEND|1|1.5|0"

            );

            RangedManager.getManager().createRangedInstance(

                    13,
                    rangedLvl[3]+"Karabin Snajperski",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    45,
                    70,
                    0,
                    2.0,
                    1,
                    1,
                    0.0,
                    0.02,
                    4.0,
                    100.0,
                    4,
                    0.05,
                    0.25,
                    3,
                    0.75,
                    "multipleBullets-1",
                    "leverAction",
                    0.45,
                    0,
                    0,
                    0.0,
                    "ENTITY_BLAZE_HURT|1|0.75|0;ENTITY_GENERIC_EXPLODE|1|1.75|0;ENTITY_BLAZE_DEATH|0.2|2|0",
                    "BLOCK_NOTE_BLOCK_HAT|1|0.9|2",
                    "BLOCK_NOTE_BLOCK_HAT|1|0|0;BLOCK_PISTON_CONTRACT|0.25|1.5|2][BLOCK_PISTON_EXTEND|1|1.5|0"

            );
        } // ID: 13

        {
            RangedManager.getManager().createRangedInstance(

                    14,
                    rangedLvl[0]+"Dwutaktowa Strzelba",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    5,
                    7,
                    0,
                    0.7,
                    5,
                    1,
                    0.0,
                    2.0,
                    0.5,
                    25.0,
                    1,
                    0.95,
                    0.15,
                    10,
                    2.2,
                    "clip",
                    "pumpAction",
                    0.5,
                    2,
                    0,
                    0.0,
                    "ENTITY_SNOWBALL_THROW|1|0.7|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|1|34",
                    "BLOCK_PISTON_EXTEND|0.25|1.75|5"

            );
            RangedManager.getManager().createRangedInstance(

                    14,
                    rangedLvl[1]+"Dwutaktowa Strzelba",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    5,
                    7,
                    0,
                    0.7,
                    5,
                    1,
                    0.0,
                    1.9,
                    0.5,
                    25.0,
                    1,
                    0.95,
                    0.15,
                    10,
                    2.2,
                    "clip",
                    "pumpAction",
                    0.5,
                    2,
                    0,
                    0.0,
                    "ENTITY_SNOWBALL_THROW|1|0.7|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|1|34",
                    "BLOCK_PISTON_EXTEND|0.25|1.75|5"

            );

            RangedManager.getManager().createRangedInstance(

                    14,
                    rangedLvl[2]+"Dwutaktowa Strzelba",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    6,
                    8,
                    0,
                    0.7,
                    6,
                    1,
                    0.0,
                    1.8,
                    0.5,
                    25.0,
                    1,
                    0.95,
                    0.15,
                    10,
                    2.2,
                    "clip",
                    "pumpAction",
                    0.5,
                    2,
                    0,
                    0.0,
                    "ENTITY_SNOWBALL_THROW|1|0.7|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|1|34",
                    "BLOCK_PISTON_EXTEND|0.25|1.75|5"

            );

            RangedManager.getManager().createRangedInstance(

                    14,
                    rangedLvl[3]+"Dwutaktowa Strzelba",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    6,
                    8,
                    0,
                    0.7,
                    7,
                    1,
                    0.0,
                    1.7,
                    0.5,
                    25.0,
                    1,
                    0.95,
                    0.15,
                    12,
                    2.2,
                    "clip",
                    "pumpAction",
                    0.5,
                    2,
                    0,
                    0.0,
                    "ENTITY_SNOWBALL_THROW|1|0.7|0;ENTITY_GENERIC_EXPLODE|1|2|0",
                    "BLOCK_IRON_TRAPDOOR_OPEN|1|1.5|4;BLOCK_IRON_TRAPDOOR_CLOSE|1|1|34",
                    "BLOCK_PISTON_EXTEND|0.25|1.75|5"

            );

        } // ID: 14

        {

            RangedManager.getManager().createRangedInstance(

                    15,
                    rangedLvl[0]+"Szybkostrzelny Pistolet Maszynowy",
                    0,
                    Material.IRON_HORSE_ARMOR,
                    1,
                    4,
                    0,
                    1.0,
                    1,
                    4,
                    0.05,
                    0.5,
                    1.5,
                    5.0,
                    1,
                    0.75,
                    0.15,
                    24,
                    2.7,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_PLACE|0.2|2|0;ENTITY_IRON_GOLEM_STEP|1|2|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|2|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|42;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|47",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    15,
                    rangedLvl[1]+"Szybkostrzelny Pistolet Maszynowy",
                    1,
                    Material.IRON_HORSE_ARMOR,
                    1,
                    4,
                    0,
                    1.0,
                    1,
                    4,
                    0.05,
                    0.5,
                    1.5,
                    5.0,
                    1,
                    0.75,
                    0.15,
                    28,
                    2.7,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_PLACE|0.2|2|0;ENTITY_IRON_GOLEM_STEP|1|2|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|2|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|42;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|47",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    15,
                    rangedLvl[2]+"Szybkostrzelny Pistolet Maszynowy",
                    2,
                    Material.IRON_HORSE_ARMOR,
                    2,
                    4,
                    0,
                    1.0,
                    1,
                    4,
                    0.05,
                    0.4,
                    1.5,
                    5.0,
                    1,
                    0.75,
                    0.15,
                    32,
                    2.7,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_PLACE|0.2|2|0;ENTITY_IRON_GOLEM_STEP|1|2|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|2|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|42;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|47",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );

            RangedManager.getManager().createRangedInstance(

                    15,
                    rangedLvl[3]+"Szybkostrzelny Pistolet Maszynowy",
                    3,
                    Material.IRON_HORSE_ARMOR,
                    2,
                    5,
                    0,
                    1.0,
                    1,
                    4,
                    0.05,
                    0.35,
                    1.5,
                    5.0,
                    1,
                    0.75,
                    0.15,
                    32,
                    2.7,
                    "clip",
                    "slide",
                    0.5,
                    0,
                    0,
                    0.0,
                    "BLOCK_GLASS_PLACE|0.2|2|0;ENTITY_IRON_GOLEM_STEP|1|2|0",
                    "BLOCK_WOODEN_BUTTON_CLICK_ON|1|2|4;BLOCK_IRON_TRAPDOOR_OPEN|1|2|5;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|42;BLOCK_IRON_TRAPDOOR_CLOSE|1|2|47",
                    "BLOCK_PISTON_EXTEND|0.25|2|4"

            );

        } // ID: 15
    }

}
