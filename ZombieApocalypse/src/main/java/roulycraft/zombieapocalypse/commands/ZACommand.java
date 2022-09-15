package roulycraft.zombieapocalypse.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.Objects;
import roulycraft.zombieapocalypse.managers.GameInstance;
import roulycraft.zombieapocalypse.managers.GameManager;

public class ZACommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String missingPermissionMessage = "§4BŁĄD! §cBrakuje uprawnień!";
        String missingArgumentsMessage = "§4BŁĄD! §cPodaj więcej argumentów!";
        String noArgumentsMessage = "§4BŁĄD! §cWpisz /za help by wyświetlić listę argumentów!";
        String missingInstanceNameMessage = "§4BŁĄD! §cWprowadź nazwę areny!";

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

            switch(args[0]) {
                case "help": {
                    if (args.length == 1) {
                        sender.sendMessage(" ");
                        sender.sendMessage("§6== §eKomendy Podstawowe §aZombie Apocalypse §6==");
                        sender.sendMessage("§6/za help §8- §ePomoc ogólna.");
                        sender.sendMessage("§6/za leave §8- §eWyjście z areny.");
                        if (sender.isOp() || sender.hasPermission("technik")) {
                            sender.sendMessage("§6/za join <arena> §8- §eDołączenia na konkretną arenę.");
                            sender.sendMessage("§6/za help ct §8- §ePomoc tworzeniu aren.");
                            sender.sendMessage("§6/za help debug §8- §ePomoc w testowaniu lub debugowaniu aren.");
                            return true;
                        }
                    } else {
                        if (Objects.equals(args[1], "ct")) {
                            sender.sendMessage(" ");
                            sender.sendMessage("§6== §eKomendy Creation Tools §aZombie Apocalypse §6==");
                            sender.sendMessage("§6/za ct <arena> create §8- §eStwarza instancję areny.");
                            sender.sendMessage("§6/za ct <arena> lobby §8- §eUstawia lokacje lobby instancji areny.");
                            sender.sendMessage("§6/za ct <arena> spawn add §8- §edodaje spawn graczy instancji areny.");
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

                    switch (args[2]) {
                        case "create":

                            GameManager.getManager().createArena((Player) sender, args[1]);
                            return true;

                        case "lobby":

                            GameManager.getManager().getGameInstance(args[1]).setLobby(p.getLocation());
                            GameManager.getManager().getGameInstanceConfig(args[1]).set("lobby", p.getLocation());

                            GameManager.getManager().saveGameInstanceConfig(args[1]);

                            sender.sendMessage("§2SUKCES! §aLokacja Lobby areny §f" + args[1] + " §azostała ustawiona!");
                            return true;

                        case "spawn":

                            if (args.length == 3) {
                                sender.sendMessage(missingArgumentsMessage);
                                return false;
                            }

                            switch (args[3]) {
                                case "add":

                                    GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().add(p.getLocation());
                                    GameManager.getManager().getGameInstanceConfig(args[1]).set(("playerSpawnLocs." + (GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().size() - 1)), p.getLocation());

                                    GameManager.getManager().saveGameInstanceConfig(args[1]);

                                    sender.sendMessage("§2SUKCES! §aDodano spawn graczy nr. §f" + (GameManager.getManager().getGameInstance(args[1]).getZombieSpawnLocs().size() - 1) + "§a dla areny§f " + args[1] + "§a!");
                                    return true;

                                default:

                                    sender.sendMessage(missingArgumentsMessage);
                                    return true;
                            }

                        case "reloadFile":

                            if (GameManager.getManager().loadGameInstanceConfig(args[1])) {

                                sender.sendMessage("§2SUKCES! §aWczytano dane instancji §f" + args[1] + " §az pliku!");
                                return true;
                            }

                            sender.sendMessage("§4BŁĄD! §cWczytanie pliku instancji §f" + args[1] + " §cnie powiodło się - plik nie istnieje!");
                            sender.sendMessage(String.valueOf(args.length));
                            return false;

                        default:

                            sender.sendMessage(missingArgumentsMessage);
                            return true;
                    }

            }

        }
        return false;
    }
}
