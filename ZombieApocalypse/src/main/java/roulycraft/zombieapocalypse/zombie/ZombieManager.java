package roulycraft.zombieapocalypse.zombie;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.game.GameManager;
import roulycraft.zombieapocalypse.utility.StringSerialisation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Material.*;

public class ZombieManager {
    private static ZombieManager zombieManager;
    private static ZombieApocalypse plugin;
    private FileConfiguration zombieInstanceConfig = null;
    private File zombieInstanceFile;
    public final List<ZombieInstance> zombieInstanceList = new ArrayList<>();

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }
    private ZombieManager() {
    }

    public static ZombieManager getManager() {
        if (zombieManager == null) {
            zombieManager = new ZombieManager();
        }
        return zombieManager;
    }

    public void createZombieInstance(String name, String displayName, Integer health, Integer damage, Float speed, String special, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, Integer xpReward) {

        ZombieInstance zombieInstance = null;

        for (ZombieInstance checkIfExists : this.zombieInstanceList) {
            if (checkIfExists.getName().equals(name)) {
                return;
            }
        }

        zombieInstance = new ZombieInstance(name, displayName, health, damage, speed, special, helmet, chestplate, leggings, boots, xpReward);
        this.zombieInstanceList.add(zombieInstance);
        this.zombieInstanceList.sort(Comparator.comparing(ZombieInstance::getName));

        reloadZombieInstanceConfig(name);
        getZombieInstanceConfig(name).set("displayName", displayName);
        getZombieInstanceConfig(name).set("health", health);
        getZombieInstanceConfig(name).set("damage", damage);
        getZombieInstanceConfig(name).set("speed", speed);
        getZombieInstanceConfig(name).set("special", special);
        getZombieInstanceConfig(name).set("helmet", helmet);
        getZombieInstanceConfig(name).set("chestplate", chestplate);
        getZombieInstanceConfig(name).set("leggings", leggings);
        getZombieInstanceConfig(name).set("boots", boots);
        getZombieInstanceConfig(name).set("xpReward", xpReward);
        saveZombieInstanceConfig(name);
    }

    public ZombieInstance getZombieInstance(String name){
        int left = 0;
        int right = zombieInstanceList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int result = name.compareTo(zombieInstanceList.get(mid).getName());

            if(result == 0) {
                return zombieInstanceList.get(mid);
            }

            if(result > 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return null;
    }

    public void reloadZombieInstanceConfig(String name) {

        zombieInstanceFile = new File(plugin.getDataFolder() + File.separator + "instances" + File.separator + "zombies", (name + ".yml"));
        zombieInstanceConfig = YamlConfiguration.loadConfiguration(zombieInstanceFile);
    }

    public FileConfiguration getZombieInstanceConfig(String name) {
        if (zombieInstanceConfig == null) {
            reloadZombieInstanceConfig(name);
        }
        return zombieInstanceConfig;
    }

    public void saveZombieInstanceConfig(String name) {

        try {
            getZombieInstanceConfig(name).save(zombieInstanceFile);
        }

        catch (IOException ex) {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            console.sendMessage("§4BŁĄD KRYTYCZNY §cNie można było zapisać konfiguracji §fzombie.yml§c!");
            console.sendMessage(String.valueOf(ex));
        }
    }

    public boolean loadZombieInstanceConfig(String name) {
        reloadZombieInstanceConfig(name);

        if (!zombieInstanceFile.exists()) {
            return false;
        }

        if (getZombieInstance(name) != null) {

            getZombieInstance(name).setDisplayName(zombieInstanceConfig.getString("displayName"));
            getZombieInstance(name).setHealth(zombieInstanceConfig.getInt("health"));
            getZombieInstance(name).setDamage(zombieInstanceConfig.getInt("damage"));
            getZombieInstance(name).setSpeed((float) zombieInstanceConfig.getDouble("speed"));
            getZombieInstance(name).setSpecial(zombieInstanceConfig.getString("special"));
            getZombieInstance(name).setHelmet(zombieInstanceConfig.getItemStack("helmet"));
            getZombieInstance(name).setChestplate(zombieInstanceConfig.getItemStack("chestplate"));
            getZombieInstance(name).setLeggings(zombieInstanceConfig.getItemStack("leggings"));
            getZombieInstance(name).setBoots(zombieInstanceConfig.getItemStack("boots"));
            getZombieInstance(name).setXPReward(zombieInstanceConfig.getInt("xpReward"));

            return true;
        }
        else {
            createZombieInstance(
                name,
                zombieInstanceConfig.getString("displayName"),
                zombieInstanceConfig.getInt("health"),
                zombieInstanceConfig.getInt("damage"),
                (float) zombieInstanceConfig.getDouble("speed"),
                zombieInstanceConfig.getString("special"),
                zombieInstanceConfig.getItemStack("helmet"),
                zombieInstanceConfig.getItemStack("chestplate"),
                zombieInstanceConfig.getItemStack("leggings"),
                zombieInstanceConfig.getItemStack("boots"),
                zombieInstanceConfig.getInt("xpReward")
            );

            return true;

        }
    }

    public UUID spawnZombie(Location loc, String name, Boolean countTowardsKills, String instanceName) {

        Zombie zombie = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);

        zombie.setCustomName(StringSerialisation.deserialise(getZombieInstance(name).getDisplayName()));
        zombie.setCustomNameVisible(true);

        zombie.setMetadata("ZA", new FixedMetadataValue(plugin, true));
        zombie.setMetadata("health", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getHealth()));
        zombie.setMetadata("maxHealth", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getHealth()));
        zombie.setMetadata("damage", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getDamage()));
        zombie.setMetadata("xpReward", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getXPReward()));

        zombie.setMetadata("instanceName", new FixedMetadataValue(plugin, instanceName));

        NamespacedKey key = new NamespacedKey(plugin, ("zabossbar." + zombie.getEntityId()));
        zombie.setMetadata("bossbarKey", new FixedMetadataValue(plugin, ("zabossbar." + zombie.getEntityId())));

        Bukkit.getServer().createBossBar(key, "", BarColor.GREEN, BarStyle.SOLID);
        Bukkit.getServer().getBossBar(key).setProgress(1.0);


        if(countTowardsKills) {
            zombie.setMetadata("countTowardKills", new FixedMetadataValue(plugin, 1));
        }

        else {
            zombie.setMetadata("countTowardKills", new FixedMetadataValue(plugin, 0));
        }

        zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(ZombieManager.getManager().getZombieInstance(name).getSpeed()/10);

        zombie.setAdult();
        zombie.setRemoveWhenFarAway(false);

        zombie.getEquipment().setItemInMainHand(new ItemStack(Material.AIR, 0));
        zombie.getEquipment().setItemInOffHand(new ItemStack(Material.AIR, 0));

        zombie.getEquipment().setHelmet(ZombieManager.getManager().getZombieInstance(name).getHelmet());
        zombie.getEquipment().setChestplate(ZombieManager.getManager().getZombieInstance(name).getChestplate());
        zombie.getEquipment().setLeggings(ZombieManager.getManager().getZombieInstance(name).getLeggings());
        zombie.getEquipment().setBoots(ZombieManager.getManager().getZombieInstance(name).getBoots());

        zombie.getEquipment().setHelmetDropChance(0f);
        zombie.getEquipment().setChestplateDropChance(0f);
        zombie.getEquipment().setLeggingsDropChance(0f);
        zombie.getEquipment().setBootsDropChance(0f);

        if (!instanceName.equals("")) {
            Player player = GameManager.getManager().getRandomPlayer(instanceName);
            if(player != null){
                zombie.setTarget(player);
            }
        }

        ZombieListener.insertMap(NamespacedKey.fromString(zombie.getMetadata("bossbarKey").get(0).asString(), plugin));
        return zombie.getUniqueId();
    }
}

