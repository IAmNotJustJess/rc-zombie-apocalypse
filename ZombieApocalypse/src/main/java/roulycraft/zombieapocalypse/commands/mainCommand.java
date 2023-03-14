package roulycraft.zombieapocalypse.commands;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.inventory.ItemStack;
import roulycraft.zombieapocalypse.ZombieApocalypse;
import roulycraft.zombieapocalypse.managers.GameManager;
import roulycraft.zombieapocalypse.weapons.ranged.RangedManager;
import roulycraft.zombieapocalypse.zombie.ZombieInstance;
import roulycraft.zombieapocalypse.zombie.ZombieManager;

import static net.kyori.adventure.text.format.Style.style;

public class MainCommand implements CommandExecutor {

    private static ZombieApocalypse plugin;

    public static void injectPlugin(ZombieApocalypse p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {

        Audience sender = (Audience) player;
        MiniMessage miniMessage = MiniMessage.miniMessage();

        String pluginName = plugin.getConfig().getString("messages.plugin.name");
        String commandColour = plugin.getConfig().getString("messages.plugin.command");
        String symbolColour = plugin.getConfig().getString("messages.plugin.symbol");
        String headlineColour = plugin.getConfig().getString("messages.plugin.headline");

        String missingPermissionMessage = "<dark_red>BŁĄD! <red>Brakuje uprawnień!";
        String missingArgumentsMessage = "<dark_red>BŁĄD! <red>Podaj więcej argumentów!";
        String noArgumentsMessage = "<dark_red>BŁĄD! <red>Wpisz /za help by wyświetlić listę argumentów!";
        String missingInstanceNameMessage = "<dark_red>BŁĄD! <red>Wprowadź nazwę areny!";
        String missingZombieMessage = "<dark_red>BŁĄD! <red>Wprowadź nazwę zombie!";

        if (!(sender instanceof Player)) {

            sender.sendMessage(miniMessage.deserialize("<dark_red>BŁĄD <red>Dla bezpieczeństwa, tylko gracze mogą wykorzystywać tą komendę!"));
            return true;

        }

        Player p = (Player) sender;

        if (command.getName().equalsIgnoreCase("za")) {

            if (args.length == 0) {

                sender.sendMessage(miniMessage.deserialize(noArgumentsMessage));
                return true;

            }

            switch(args[0].toLowerCase()) {
                case "help": {
                    if (args.length == 1) {
                        sender.sendMessage(miniMessage.deserialize("<newline>"+symbolColour+"== "+headlineColour+"<bold>Komendy Podstawowe "+pluginName+" "+symbolColour+"==<newline>"));
                        sender.sendMessage(miniMessage.deserialize(commandColour+"/za help "+symbolColour+"- "+headlineColour+"Pomoc ogólna."));
                        sender.sendMessage(miniMessage.deserialize(commandColour+"/za leave "+symbolColour+"- "+headlineColour+"Wyjście z areny."));
                        if (player.isOp() || player.hasPermission("za.ct")) {
                            sender.sendMessage(miniMessage.deserialize(commandColour+"/za join <instancja> "+symbolColour+"- "+headlineColour+"Dołączenia na konkretną arenę."));
                            sender.sendMessage(miniMessage.deserialize(commandColour+"/za help ct "+symbolColour+"- "+headlineColour+"Pomoc tworzeniu aren."));
                            sender.sendMessage(miniMessage.deserialize(commandColour+"/za help debug "+symbolColour+"- "+headlineColour+"Pomoc w testowaniu lub debugowaniu aren."));
                            return true;
                        }
                    } else {
                        if ("ct".equalsIgnoreCase(args[1]) && player.hasPermission("za.ct")) {
                            sender.sendMessage(miniMessage.deserialize("<newline>"+symbolColour+"== "+headlineColour+"<bold>Komendy Creation Tools "+pluginName+" "+symbolColour+"==<newline>"));
                            sender.sendMessage(miniMessage.deserialize(commandColour+"/za ct <instancja> create "+symbolColour+"- "+headlineColour+"Stwarza instancję."));
                            sender.sendMessage(miniMessage.deserialize(commandColour+"/za ct <instancja> lobby "+symbolColour+"- "+headlineColour+"Ustawia lokacje lobby instancji."));
                            sender.sendMessage(miniMessage.deserialize(commandColour+"/za ct <instancja> spawn add "+symbolColour+"- "+headlineColour+"dodaje spawn graczy instancji"));
                            sender.sendMessage(miniMessage.deserialize(commandColour+"/za ct <instancja> spawn remove <number> "+symbolColour+"- "+headlineColour+"usuwa spawn graczy instancji (przesuwa spawny z wyższym numerem w dół)."));
                            sender.sendMessage(miniMessage.deserialize(commandColour+"/za ct <instancja> zombie add "+symbolColour+"- "+headlineColour+"dodaje spawn zombie instancji."));
                            sender.sendMessage(miniMessage.deserialize(commandColour+"/za ct <instancja> zombie remove <number> "+symbolColour+"- "+headlineColour+"usuwa spawn zombie instancji (przesuwa spawny z wyższym numerem w dół)."));
                            return true;
                        }

                        if ("debug".equalsIgnoreCase(args[1]) && player.hasPermission("za.ct")) {
                            sender.sendMessage(miniMessage.deserialize("<newline>"+symbolColour+"== "+headlineColour+"<bold>Komendy Creation Tools "+pluginName+" "+symbolColour+"==<newline>"));
                            sender.sendMessage(miniMessage.deserialize(commandColour+"/za debug spawnZombie <nazwa> "+symbolColour+"- "+headlineColour+"Respi zombiaka na twojej lokacji."));
                            sender.sendMessage(miniMessage.deserialize(commandColour+"/za debug giveRanged <gracz> <id> "+symbolColour+"- "+headlineColour+"Daje graczu broń palną."));
                            return true;
                        }
                    }
                }

                case "ct":

                    if (!player.isOp() || !player.hasPermission("za.ct")) {
                        sender.sendMessage(miniMessage.deserialize(missingPermissionMessage));
                        return true;
                    }

                    if (args.length == 1) {
                        sender.sendMessage(miniMessage.deserialize(missingInstanceNameMessage));
                        return true;
                    }

                    if("create".equalsIgnoreCase(args[2])) {

                        GameManager.getManager().createGameInstance((Player) sender, args[1]);
                        return true;
                    }
                    else {

                        if (!GameManager.getManager().checkIfGameInstanceExists(args[1])) {

                            sender.sendMessage(miniMessage.deserialize("<dark_red>BŁĄD! <red>Arena <white>" + args[1] + " <red>już istnieje!"));

                        }

                        switch (args[2].toLowerCase()) {

                            case "lobby":

                                GameManager.getManager().checkIfGameInstanceExists(args[1]);

                                GameManager.getManager().getGameInstance(args[1]).setLobby(p.getLocation());
                                GameManager.getManager().getGameInstanceConfig(args[1]).set("lobby", p.getLocation());

                                GameManager.getManager().saveGameInstanceConfig(args[1]);

                                sender.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Lokacja Lobby areny <white>" + args[1] + " <green>została ustawiona!"));
                                return true;

                            case "spawn":

                                switch (args[3].toLowerCase()) {
                                    case "add":

                                        GameManager.getManager().getGameInstance(args[1]).getPlayerSpawnLocs().add(p.getLocation());
                                        GameManager.getManager().getGameInstanceConfig(args[1]).set(("playerSpawnLocs." + (GameManager.getManager().getGameInstance(args[1]).getPlayerSpawnLocs().size() - 1)), p.getLocation());

                                        GameManager.getManager().saveGameInstanceConfig(args[1]);

                                        sender.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Dodano spawn graczy nr. <white>" + (GameManager.getManager().getGameInstance(args[1]).getPlayerSpawnLocs().size() - 1) + "<green> na instancji<white> " + args[1] + "<green>!"));
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

                                                sender.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Usunięto spawn graczy nr. <white>" + (args[4]) + "<green> na instancji<white> " + args[1] + "<green>!"));

                                                return true;

                                            } catch (IndexOutOfBoundsException e) {

                                                sender.sendMessage(miniMessage.deserialize("<dark_red>BŁĄD! <red>Spawn graczy nr. <white>" + (args[4]) + "<red> na instancji<white> " + args[1] + " <red>nie istnieje!"));
                                                return true;

                                            }

                                        }

                                    default:

                                        sender.sendMessage(miniMessage.deserialize(missingArgumentsMessage));
                                        return true;
                                }

                            case "zombie":

                                switch (args[3].toLowerCase()) {
                                    case "add":

                                        GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().add(p.getLocation());
                                        GameManager.getManager().getGameInstanceConfig(args[1]).set(("playerSpawnLocs." + (GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().size() - 1)), p.getLocation());

                                        GameManager.getManager().saveGameInstanceConfig(args[1]);

                                        sender.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Dodano spawn zombie nr. <white>" + (GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().size() - 1) + "<green> na instancji<white> " + args[1] + "<green>!"));
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

                                                sender.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Usunięto spawn zombie nr. <white>" + (args[4]) + "<green> na instancji<white> " + args[1] + "<green>!"));

                                                return true;

                                            } catch (IndexOutOfBoundsException e) {

                                                sender.sendMessage(miniMessage.deserialize("<dark_red>BŁĄD! <red>Spawn zombie nr. <white>" + (args[4]) + "<red> na instancji<white> " + args[1] + " <red>nie istnieje!"));
                                                return true;

                                            }

                                        }

                                    default:

                                        sender.sendMessage(miniMessage.deserialize(missingArgumentsMessage));
                                        return true;
                                }

                            case "loadfile":

                                if (GameManager.getManager().loadGameInstanceConfig(args[1])) {

                                    sender.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Wczytano dane instancji <white>" + args[1] + " <green>z pliku!"));
                                    return true;
                                }

                                sender.sendMessage(miniMessage.deserialize("<dark_red>BŁĄD! <red>Wczytanie pliku instancji <white>" + args[1] + " <red>nie powiodło się - plik nie istnieje!"));
                                sender.sendMessage(miniMessage.deserialize(String.valueOf(args.length)));
                                return true;

                            default:

                                sender.sendMessage(miniMessage.deserialize(missingArgumentsMessage));
                                return true;
                    }
                }
                case "debug":

                    if (!player.isOp() || !player.hasPermission("za.ct")) {
                        sender.sendMessage(miniMessage.deserialize(missingPermissionMessage));
                        return true;
                    }

                    if (args.length == 1) {
                        sender.sendMessage(miniMessage.deserialize(missingInstanceNameMessage));
                        return true;
                    }

                    switch(args[1].toLowerCase()) {

                        case "getgun":

                            Integer.valueOf(args[2]);
                            Integer.valueOf(args[3]);

                            if (RangedManager.getManager().getGun(Integer.valueOf(args[2]), Integer.valueOf(args[3])) == null) {

                                sender.sendMessage(miniMessage.deserialize("<dark_red>BŁĄD! <red>Broń o ID: <white>" + args[2] + " <red>i poziomie <white>" + args[3] + "<red> nie istnieje!"));
                                return true;

                            }

                            ((Player) sender).getInventory().addItem(RangedManager.getManager().getGun(Integer.valueOf(args[2]), Integer.valueOf(args[3])));
                            sender.sendMessage(miniMessage.deserialize("<dark_green>SUKCES! <green>Otrzymano broń o ID: <white>" + args[2] + " <green>i poziomie <white>" + args[3] + "<green>!"));

                            return true;

                        case "spawnzombie":

                            boolean count = false;

                            if (Objects.isNull(args[2])) {
                                sender.sendMessage(miniMessage.deserialize(missingZombieMessage));
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
                                sender.sendMessage(miniMessage.deserialize(missingZombieMessage));
                                return true;
                            }

                            Location loc = ((Player) sender).getLocation();
                            ZombieManager.getManager().spawnZombie(loc, args[2], count, "");

                            return true;

                    }
            }

        }
        return true;
    }
}
