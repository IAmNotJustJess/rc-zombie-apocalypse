package roulycraft.zombieapocalypse.bosses;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
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

public class BossManager {
    private static BossManager bossManager;
    private static ZombieApocalypse plugin;
    public final List<BossInstance> bossInstanceList = new ArrayList<>();
    private FileConfiguration bossInstanceConfig = null;
    private File bossInstanceFile;

    private BossManager() {
    }

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }

    public static BossManager getManager() {
        if (bossManager == null) {
            bossManager = new BossManager();
        }
        return bossManager;
    }

    public void createBossInstance(String name, String displayName, Integer health, Integer damage, Float speed, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack mainhand, ItemStack offhand, Integer xpReward) {

        BossInstance bossInstance = null;

        for (BossInstance checkIfExists : this.bossInstanceList) {
            if (checkIfExists.getName().equals(name)) {
                return;
            }
        }

        bossInstance = new BossInstance(name, displayName, health, damage, speed, helmet, chestplate, leggings, boots, mainhand, offhand, xpReward);
        this.bossInstanceList.add(bossInstance);
        this.bossInstanceList.sort(Comparator.comparing(BossInstance::getName));

        reloadBossInstanceConfig(name);
        getBossInstanceConfig(name).set("displayName", displayName);
        getBossInstanceConfig(name).set("health", health);
        getBossInstanceConfig(name).set("damage", damage);
        getBossInstanceConfig(name).set("speed", speed);
        getBossInstanceConfig(name).set("helmet", helmet);
        getBossInstanceConfig(name).set("chestplate", chestplate);
        getBossInstanceConfig(name).set("leggings", leggings);
        getBossInstanceConfig(name).set("boots", boots);
        getBossInstanceConfig(name).set("mainhand", mainhand);
        getBossInstanceConfig(name).set("offhand", offhand);
        getBossInstanceConfig(name).set("xpReward", xpReward);
        saveBossInstanceConfig(name);
    }

    public BossInstance getBossInstance(String name) {
        int left = 0;
        int right = bossInstanceList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int result = name.compareTo(bossInstanceList.get(mid).getName());

            if (result == 0) {
                return bossInstanceList.get(mid);
            }

            if (result > 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return null;
    }

    public void reloadBossInstanceConfig(String name) {

        bossInstanceFile = new File(plugin.getDataFolder() + File.separator + "instances" + File.separator + "bosses", (name + ".yml"));
        bossInstanceConfig = YamlConfiguration.loadConfiguration(bossInstanceFile);
    }

    public FileConfiguration getBossInstanceConfig(String name) {
        if (bossInstanceConfig == null) {
            reloadBossInstanceConfig(name);
        }
        return bossInstanceConfig;
    }

    public void saveBossInstanceConfig(String name) {

        try {
            getBossInstanceConfig(name).save(bossInstanceFile);
        } catch (IOException ex) {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            console.sendMessage("§4BŁĄD KRYTYCZNY §cNie można było zapisać konfiguracji §fbosses.yml§c!");
            console.sendMessage(String.valueOf(ex));
        }
    }

    public boolean loadBossInstanceConfig(String name) {
        reloadBossInstanceConfig(name);

        if (!bossInstanceFile.exists()) {
            return false;
        }

        if (getBossInstance(name) != null) {

            getBossInstance(name).setDisplayName(bossInstanceConfig.getString("displayName"));
            getBossInstance(name).setHealth(bossInstanceConfig.getInt("health"));
            getBossInstance(name).setDamage(bossInstanceConfig.getInt("damage"));
            getBossInstance(name).setSpeed((float) bossInstanceConfig.getDouble("speed"));
            getBossInstance(name).setHelmet(bossInstanceConfig.getItemStack("helmet"));
            getBossInstance(name).setChestplate(bossInstanceConfig.getItemStack("chestplate"));
            getBossInstance(name).setLeggings(bossInstanceConfig.getItemStack("leggings"));
            getBossInstance(name).setBoots(bossInstanceConfig.getItemStack("boots"));
            getBossInstance(name).setMainhand(bossInstanceConfig.getItemStack("mainhand"));
            getBossInstance(name).setOffhand(bossInstanceConfig.getItemStack("offhand"));
            getBossInstance(name).setXPReward(bossInstanceConfig.getInt("xpReward"));

            return true;
        }
        else {
            createBossInstance(name, bossInstanceConfig.getString("displayName"), bossInstanceConfig.getInt("health"), bossInstanceConfig.getInt("damage"), (float) bossInstanceConfig.getDouble("speed"), bossInstanceConfig.getItemStack("helmet"), bossInstanceConfig.getItemStack("chestplate"), bossInstanceConfig.getItemStack("leggings"), bossInstanceConfig.getItemStack("boots"), bossInstanceConfig.getItemStack("mainhand"), bossInstanceConfig.getItemStack("offhand"), bossInstanceConfig.getInt("xpReward"));

            return true;

        }
    }

    public UUID spawnBoss(Location loc, String name, String instanceName) {

        Zombie boss = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);

        boss.setCustomName(StringSerialisation.deserialise(getBossInstance(name).getDisplayName()));
        boss.setCustomNameVisible(true);

        boss.setMetadata("ZA", new FixedMetadataValue(plugin, true));
        boss.setMetadata("boss", new FixedMetadataValue(plugin, true));
        boss.setMetadata("health", new FixedMetadataValue(plugin, BossManager.getManager().getBossInstance(name).getHealth()));
        boss.setMetadata("maxHealth", new FixedMetadataValue(plugin, BossManager.getManager().getBossInstance(name).getHealth()));
        boss.setMetadata("damage", new FixedMetadataValue(plugin, BossManager.getManager().getBossInstance(name).getDamage()));
        boss.setMetadata("xpReward", new FixedMetadataValue(plugin, BossManager.getManager().getBossInstance(name).getXPReward()));
        boss.setMetadata("special", new FixedMetadataValue(plugin, BossManager.getManager().getBossInstance(name).getName()));

        boss.setMetadata("instanceName", new FixedMetadataValue(plugin, instanceName));

        boss.setMetadata("countTowardKills", new FixedMetadataValue(plugin, 1));

        boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(BossManager.getManager().getBossInstance(name).getSpeed() / 10);

        boss.setAdult();
        boss.setRemoveWhenFarAway(false);

        boss.getEquipment().setItemInMainHand(BossManager.getManager().getBossInstance(name).getMainhand());
        boss.getEquipment().setItemInOffHand(BossManager.getManager().getBossInstance(name).getOffhand());

        boss.getEquipment().setHelmet(BossManager.getManager().getBossInstance(name).getHelmet());
        boss.getEquipment().setChestplate(BossManager.getManager().getBossInstance(name).getChestplate());
        boss.getEquipment().setLeggings(BossManager.getManager().getBossInstance(name).getLeggings());
        boss.getEquipment().setBoots(BossManager.getManager().getBossInstance(name).getBoots());

        boss.getEquipment().setHelmetDropChance(0f);
        boss.getEquipment().setChestplateDropChance(0f);
        boss.getEquipment().setLeggingsDropChance(0f);
        boss.getEquipment().setBootsDropChance(0f);

        if (!instanceName.equals("")) {
            Player player = GameManager.getManager().getRandomPlayer(instanceName);
            if (player != null) {
                boss.setTarget(player);
            }
        }

        return boss.getUniqueId();
    }
}
