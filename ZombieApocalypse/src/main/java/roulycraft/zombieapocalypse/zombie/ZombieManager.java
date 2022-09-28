package roulycraft.zombieapocalypse.zombie;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import roulycraft.zombieapocalypse.ZombieApocalypse;

import javax.xml.stream.events.Namespace;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZombieManager {
    private static ZombieManager zombieManager;
    private static ZombieApocalypse plugin;
    private FileConfiguration zombieInstanceConfig = null;
    private File zombieInstanceFile = zombieInstanceFile = new File(plugin.getDataFolder() + File.separator + "zombie.yml");
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

        reloadZombieInstanceConfig();
        getZombieInstanceConfig().set(("zombies." + name + ".displayName"), displayName);
        getZombieInstanceConfig().set(("zombies." + name + ".health"), health);
        getZombieInstanceConfig().set(("zombies." + name + ".damage"), damage);
        getZombieInstanceConfig().set(("zombies." + name + ".speed"), speed);
        getZombieInstanceConfig().set(("zombies." + name + ".special"), special);
        getZombieInstanceConfig().set(("zombies." + name + ".helmet"), helmet);
        getZombieInstanceConfig().set(("zombies." + name + ".chestplate"), chestplate);
        getZombieInstanceConfig().set(("zombies." + name + ".leggings"), leggings);
        getZombieInstanceConfig().set(("zombies." + name + ".boots"), boots);
        getZombieInstanceConfig().set(("zombies." + name + ".xpReward"), xpReward);
        saveZombieInstanceConfig();
    }

    public ZombieInstance getZombieInstance(String name){
        for (ZombieInstance zombieInstance : this.zombieInstanceList) {
            if (zombieInstance.getName().equals(name)) {
                return zombieInstance;
            }
        }

        return null;
    }

    public void reloadZombieInstanceConfig() {
        zombieInstanceConfig = YamlConfiguration.loadConfiguration(zombieInstanceFile);
    }

    public FileConfiguration getZombieInstanceConfig() {
        if (zombieInstanceConfig == null) {
            reloadZombieInstanceConfig();
        }
        return zombieInstanceConfig;
    }

    public void saveZombieInstanceConfig() {
        try {
            getZombieInstanceConfig().save(zombieInstanceFile);
        }
        catch (IOException ex) {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            console.sendMessage("§4BŁĄD KRYTYCZNY §cNie można było zapisać konfiguracji §fzombie.yml§c!");
            console.sendMessage(String.valueOf(ex));
        }
    }

    public void loadZombieInstanceConfig() {
        reloadZombieInstanceConfig();

        if (!zombieInstanceFile.exists()) {
            return;
        }
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        ZP:
        for (String path : zombieInstanceConfig.getConfigurationSection("zombies").getKeys(false)) {

            for (ZombieInstance zombieInstance : this.zombieInstanceList) {
                if (zombieInstance.getName().equals(path)) {

                    getZombieInstance(path).setDisplayName(zombieInstanceConfig.getString("zombies."+path+".displayName"));
                    getZombieInstance(path).setHealth(zombieInstanceConfig.getInt("zombies."+path+".health"));
                    getZombieInstance(path).setDamage(zombieInstanceConfig.getInt("zombies."+path+".damage"));
                    getZombieInstance(path).setSpeed((float) zombieInstanceConfig.getDouble("zombies."+path+".speed"));
                    getZombieInstance(path).setSpecial(zombieInstanceConfig.getString("zombies."+path+".special"));
                    getZombieInstance(path).setHelmet(zombieInstanceConfig.getItemStack("zombies."+path+".helmet"));
                    getZombieInstance(path).setChestplate(zombieInstanceConfig.getItemStack("zombies."+path+".chestplate"));
                    getZombieInstance(path).setLeggings(zombieInstanceConfig.getItemStack("zombies."+path+".leggings"));
                    getZombieInstance(path).setBoots(zombieInstanceConfig.getItemStack("zombies."+path+".boots"));
                    getZombieInstance(path).setXPReward(zombieInstanceConfig.getInt("zombies."+path+".xpReward"));

                    console.sendMessage("§2SUKCES! §aPoprawnie zinicjonowano zombie §f" + path + "§a!");

                    continue ZP;
                }
            }
            createZombieInstance(path,
                    zombieInstanceConfig.getString("zombies."+path+".displayName"),
                    zombieInstanceConfig.getInt("zombies."+path+".health"),
                    zombieInstanceConfig.getInt("zombies."+path+".damage"),
                    (float) zombieInstanceConfig.getDouble("zombies."+path+".speed"),
                    zombieInstanceConfig.getString("zombies."+path+".special"),
                    zombieInstanceConfig.getItemStack("zombies."+path+".helmet"),
                    zombieInstanceConfig.getItemStack("zombies."+path+".chestplate"),
                    zombieInstanceConfig.getItemStack("zombies."+path+".leggings"),
                    zombieInstanceConfig.getItemStack("zombies."+path+".boots"),
                    zombieInstanceConfig.getInt("zombies."+path+".xpReward")
                    );

            console.sendMessage("§2SUKCES! §aPoprawnie zinicjonowano zombie §f" + path + "§a!");
        }

    }

    public void spawnZombie(Location loc, String name, Boolean countTowardsKills) {
        Entity zombie = loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);

        zombie.setCustomName(ZombieManager.getManager().getZombieInstance(name).getDisplayName());
        zombie.setCustomNameVisible(true);

        zombie.setMetadata("ZA", new FixedMetadataValue(plugin, true));
        zombie.setMetadata("health", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getHealth()));
        zombie.setMetadata("maxHealth", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getHealth()));
        zombie.setMetadata("damage", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getDamage()));
        zombie.setMetadata("xpReward", new FixedMetadataValue(plugin, ZombieManager.getManager().getZombieInstance(name).getXPReward()));

        NamespacedKey key = new NamespacedKey(plugin, "zabossbar" + zombie.getEntityId());
        zombie.setMetadata("bossbarKey", new FixedMetadataValue(plugin, ("zabossbar" + zombie.getEntityId())));

        Bukkit.getServer().createBossBar(key, "", BarColor.GREEN, BarStyle.SOLID);
        Bukkit.getServer().getBossBar(key).setProgress(1.0);


        if(countTowardsKills) {
            zombie.setMetadata("countTowardKills", new FixedMetadataValue(plugin, 1));
        }

        else {
            zombie.setMetadata("countTowardKills", new FixedMetadataValue(plugin, 0));
        }

        ((Zombie) zombie).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(ZombieManager.getManager().getZombieInstance(name).getSpeed()/10);

        ((Zombie) zombie).setAdult();

        ((Zombie) zombie).getEquipment().setHelmet(ZombieManager.getManager().getZombieInstance(name).getHelmet());
        ((Zombie) zombie).getEquipment().setChestplate(ZombieManager.getManager().getZombieInstance(name).getChestplate());
        ((Zombie) zombie).getEquipment().setLeggings(ZombieManager.getManager().getZombieInstance(name).getLeggings());
        ((Zombie) zombie).getEquipment().setBoots(ZombieManager.getManager().getZombieInstance(name).getBoots());

        ((Zombie) zombie).getEquipment().setHelmetDropChance(0f);
        ((Zombie) zombie).getEquipment().setChestplateDropChance(0f);
        ((Zombie) zombie).getEquipment().setLeggingsDropChance(0f);
        ((Zombie) zombie).getEquipment().setBootsDropChance(0f);
    }
}

