package roulycraft.zombieapocalypse.commands;

import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.managers.GameManager;
import roulycraft.zombieapocalypse.weapons.ranged.RangedManager;
import roulycraft.zombieapocalypse.zombie.ZombieInstance;
import roulycraft.zombieapocalypse.zombie.ZombieManager;

public class MainCommand implements CommandExecutor {

    private static ZombieApocalypse plugin;

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String missingPermissionMessage = "§4BŁĄD! §cBrakuje uprawnień!";
        String missingArgumentsMessage = "§4BŁĄD! §cPodaj więcej argumentów!";
        String noArgumentsMessage = "§4BŁĄD! §cWpisz /za help by wyświetlić listę argumentów!";
        String missingInstanceNameMessage = "§4BŁĄD! §cWprowadź nazwę areny!";
        String missingZombieMessage = "§4BŁĄD! §cWprowadź nazwę zombie!";

        if (!(sender instanceof Player)) {

            sender.sendMessage("§4BŁĄD §cDla bezpieczeństwa, tylko gracze mogą wykorzystywać tą komendę!");
            return true;

        }

        Player p = (Player) sender;

        if (command.getName().equalsIgnoreCase("za")) {

            if (args.length == 0) {

                sender.sendMessage(noArgumentsMessage);
                return true;

            }

            switch(args[0].toLowerCase()) {
                case "help": {
                    if (args.length == 1) {
                        sender.sendMessage(" ");
                        sender.sendMessage(plugin.getConfig().getString("commands.colours.symbol")+"== "+plugin.getConfig().getString("commands.colours.headline")+"§lKomendy Podstawowe "+plugin.getConfig().getString("plugin.colour")+"§lZombie Apocalypse "+plugin.getConfig().getString("commands.colours.symbol")+"§r==");
                        sender.sendMessage(" ");
                        sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za help "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"Pomoc ogólna.");
                        sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za leave "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"Wyjście z areny.");
                        if (sender.isOp() || sender.hasPermission("za.ct")) {
                            sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za join <instancja> "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"Dołączenia na konkretną arenę.");
                            sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za help ct "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"Pomoc tworzeniu aren.");
                            sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za help debug "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"Pomoc w testowaniu lub debugowaniu aren.");
                            return true;
                        }
                    } else {
                        if ("ct".equalsIgnoreCase(args[1]) && sender.hasPermission("za.ct")) {
                            sender.sendMessage(" ");
                            sender.sendMessage(""+plugin.getConfig().getString("commands.colours.symbol")+"== "+plugin.getConfig().getString("commands.colours.headline")+"§lKomendy Creation Tools "+plugin.getConfig().getString("plugin.colour")+"§lZombie Apocalypse "+plugin.getConfig().getString("commands.colours.symbol")+"==");
                            sender.sendMessage(" ");
                            sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za ct <instancja> create "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"Stwarza instancję.");
                            sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za ct <instancja> lobby "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"Ustawia lokacje lobby instancji.");
                            sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za ct <instancja> spawn add "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"dodaje spawn graczy instancji");
                            sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za ct <instancja> spawn remove <number> "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"usuwa spawn graczy instancji (przesuwa spawny z wyższym numerem w dół).");
                            sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za ct <instancja> zombie add "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"dodaje spawn zombie instancji.");
                            sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za ct <instancja> zombie remove <number> "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"usuwa spawn zombie instancji (przesuwa spawny z wyższym numerem w dół).");
                            return true;
                        }

                        if ("debug".equalsIgnoreCase(args[1]) && sender.hasPermission("za.ct")) {
                            sender.sendMessage(" ");
                            sender.sendMessage(""+plugin.getConfig().getString("commands.colours.symbol")+"== "+plugin.getConfig().getString("commands.colours.headline")+"§lKomendy Creation Tools "+plugin.getConfig().getString("plugin.colour")+"§lZombie Apocalypse "+plugin.getConfig().getString("commands.colours.symbol")+"==");
                            sender.sendMessage(" ");
                            sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za debug spawnZombie <nazwa> "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"Respi zombiaka na twojej lokacji.");
                            sender.sendMessage(plugin.getConfig().getString("commands.colours.command")+"/za debug giveRanged <gracz> <id> "+plugin.getConfig().getString("commands.colours.symbol")+"- "+plugin.getConfig().getString("commands.colours.headline")+"Daje graczu broń palną.");
                            return true;
                        }
                    }
                }

                case "ct":

                    if (!sender.isOp() || !sender.hasPermission("za.ct")) {
                        sender.sendMessage(missingPermissionMessage);
                        return true;
                    }

                    if (args.length == 1) {
                        sender.sendMessage(missingInstanceNameMessage);
                        return true;
                    }

                    if("create".equalsIgnoreCase(args[2])) {

                        GameManager.getManager().createGameInstance((Player) sender, args[1]);
                        return true;
                    }
                    else {

                        if (!GameManager.getManager().checkIfGameInstanceExists(args[1])) {

                            sender.sendMessage("§4BŁĄD! §cArena §f" + args[1] + " §cjuż istnieje!");

                        }

                        switch (args[2].toLowerCase()) {

                            case "lobby":

                                GameManager.getManager().checkIfGameInstanceExists(args[1]);

                                GameManager.getManager().getGameInstance(args[1]).setLobby(p.getLocation());
                                GameManager.getManager().getGameInstanceConfig(args[1]).set("lobby", p.getLocation());

                                GameManager.getManager().saveGameInstanceConfig(args[1]);

                                sender.sendMessage("§2SUKCES! §aLokacja Lobby areny §f" + args[1] + " §azostała ustawiona!");
                                return true;

                            case "spawn":

                                switch (args[3].toLowerCase()) {
                                    case "add":

                                        GameManager.getManager().getGameInstance(args[1]).getPlayerSpawnLocs().add(p.getLocation());
                                        GameManager.getManager().getGameInstanceConfig(args[1]).set(("playerSpawnLocs." + (GameManager.getManager().getGameInstance(args[1]).getPlayerSpawnLocs().size() - 1)), p.getLocation());

                                        GameManager.getManager().saveGameInstanceConfig(args[1]);

                                        sender.sendMessage("§2SUKCES! §aDodano spawn graczy nr. §f" + (GameManager.getManager().getGameInstance(args[1]).getPlayerSpawnLocs().size() - 1) + "§a na instancji§f " + args[1] + "§a!");
                                        return true;

                                    case "remove":

                                        if (!Objects.isNull(args[4])) {

                                            try {

                                                Location loc = GameManager.getManager().getGameInstance(args[1]).getPlayerSpawnLocs().get(Integer.parseInt(args[4]));

                                                for (Integer i = 0; i < GameManager.getManager().getGameInstance(args[1]).getPlayerSpawnLocs().size(); i++) {
                                                    GameManager.getManager().getGameInstanceConfig(args[1]).set("playerSpawnLocs." + i, null);
                                                }

                                                List<Location> tempArray = new ArrayList<>();
                                                for (Location loc1 : GameManager.getManager().getGameInstance(args[1]).getPlayerSpawnLocs()) {
                                                    if (loc1 != loc) {
                                                        tempArray.add(loc1);
                                                    }
                                                }

                                                for (Integer i = 0; i < tempArray.size(); i++) {
                                                    GameManager.getManager().getGameInstanceConfig(args[1]).set("playerSpawnLocs." + i, GameManager.getManager().getGameInstance(args[1]).getPlayerSpawnLocs().get(i));
                                                }

                                                GameManager.getManager().getGameInstance(args[1]).setPlayerSpawnLocs(tempArray);
                                                GameManager.getManager().saveGameInstanceConfig(args[1]);

                                                sender.sendMessage("§2SUKCES! §aUsunięto spawn graczy nr. §f" + (args[4]) + "§a na instancji§f " + args[1] + "§a!");

                                                return true;

                                            } catch (IndexOutOfBoundsException e) {

                                                sender.sendMessage("§4BŁĄD! §cSpawn graczy nr. §f" + (args[4]) + "§c na instancji§f " + args[1] + " §cnie istnieje!");
                                                return true;

                                            }

                                        }

                                    default:

                                        sender.sendMessage(missingArgumentsMessage);
                                        return true;
                                }

                            case "zombie":

                                switch (args[3].toLowerCase()) {
                                    case "add":

                                        GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().add(p.getLocation());
                                        GameManager.getManager().getGameInstanceConfig(args[1]).set(("playerSpawnLocs." + (GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().size() - 1)), p.getLocation());

                                        GameManager.getManager().saveGameInstanceConfig(args[1]);

                                        sender.sendMessage("§2SUKCES! §aDodano spawn zombie nr. §f" + (GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().size() - 1) + "§a na instancji§f " + args[1] + "§a!");
                                        return true;

                                    case "remove":

                                        if (!Objects.isNull(args[4])) {

                                            try {

                                                Location loc = GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().get(Integer.parseInt(args[4]));

                                                for (Integer i = 0; i < GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().size(); i++) {
                                                    GameManager.getManager().getGameInstanceConfig(args[1]).set("playerSpawnLocs." + i, null);
                                                }

                                                List<Location> tempArray = new ArrayList<>();
                                                for (Location loc1 : GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs()) {
                                                    if (loc1 != loc) {
                                                        tempArray.add(loc1);
                                                    }
                                                }

                                                for (Integer i = 0; i < tempArray.size(); i++) {
                                                    GameManager.getManager().getGameInstanceConfig(args[1]).set("playerSpawnLocs." + i, GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().get(i));
                                                }

                                                GameManager.getManager().getGameInstance(args[1]).setZombieSpawnLocs(tempArray);
                                                GameManager.getManager().saveGameInstanceConfig(args[1]);

                                                sender.sendMessage("§2SUKCES! §aUsunięto spawn zombie nr. §f" + (args[4]) + "§a na instancji§f " + args[1] + "§a!");

                                                return true;

                                            } catch (IndexOutOfBoundsException e) {

                                                sender.sendMessage("§4BŁĄD! §cSpawn zombie nr. §f" + (args[4]) + "§c na instancji§f " + args[1] + " §cnie istnieje!");
                                                return true;

                                            }

                                        }

                                    default:

                                        sender.sendMessage(missingArgumentsMessage);
                                        return true;
                                }

                            case "loadfile":

                                if (GameManager.getManager().loadGameInstanceConfig(args[1])) {

                                    sender.sendMessage("§2SUKCES! §aWczytano dane instancji §f" + args[1] + " §az pliku!");
                                    return true;
                                }

                                sender.sendMessage("§4BŁĄD! §cWczytanie pliku instancji §f" + args[1] + " §cnie powiodło się - plik nie istnieje!");
                                sender.sendMessage(String.valueOf(args.length));
                                return true;

                            default:

                                sender.sendMessage(missingArgumentsMessage);
                                return true;
                    }
                }
                case "debug":

                    if (!sender.isOp() || !sender.hasPermission("za.ct")) {
                        sender.sendMessage(missingPermissionMessage);
                        return true;
                    }

                    if (args.length == 1) {
                        sender.sendMessage(missingInstanceNameMessage);
                        return true;
                    }

                    switch(args[1].toLowerCase()) {

                        case "getgun":

                            Integer.valueOf(args[2]);
                            Integer.valueOf(args[3]);

                            if (RangedManager.getManager().getGun(Integer.valueOf(args[2]), Integer.valueOf(args[3])) == null) {

                                sender.sendMessage("§4BŁĄD! §cBroń o ID: §f" + args[2] + " §ci poziomie  §f" + args[3] + " §c nie istnieje!");
                                return true;

                            }

                            ((Player) sender).getInventory().addItem(RangedManager.getManager().getGun(Integer.valueOf(args[2]), Integer.valueOf(args[3])));
                            sender.sendMessage("§2SUKCES! §aOtrzymano broń o ID: §f" + args[2] + " §ai poziomie §f" + args[3] + "§a!");

                            return true;

                        case "spawnzombie":

                            boolean count = false;

                            if (Objects.isNull(args[2])) {
                                sender.sendMessage(missingZombieMessage);
                                return true;
                            }

                            try {
                                if (Objects.equals(args[3], "true")) {
                                    count = true;
                                }
                            }
                            catch (ArrayIndexOutOfBoundsException e) {
                                count = false;
                            }

                            boolean exists = false;

                            for (ZombieInstance checkIfExists : ZombieManager.getManager().zombieInstanceList) {
                                if (checkIfExists.getName().equals(args[2])) {
                                    exists = true;
                                }
                            }

                            if(!exists) {
                                sender.sendMessage(missingZombieMessage);
                                return true;
                            }

                            Location loc = ((Player) sender).getLocation();
                            ZombieManager.getManager().spawnZombie(loc, args[2], count);

                            return true;

                    }
            }

        }
        return true;
    }
}
