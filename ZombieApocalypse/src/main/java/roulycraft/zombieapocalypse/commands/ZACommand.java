package roulycraft.zombieapocalypse.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Player;

import java.util.Objects;
import roulycraft.zombieapocalypse.managers.GameInstance;
import roulycraft.zombieapocalypse.managers.GameManager;
import roulycraft.zombieapocalypse.utility.CatchArgumentIndexException;

public class ZACommand implements CommandExecutor {

    private final CatchArgumentIndexException cAIE = new CatchArgumentIndexException();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String missingPermissionMessage = "§4BŁĄD! §cBrakuje uprawnień!";
        String missingArgumentsMessage = "§4BŁĄD! §cPodaj więcej argumentów!";
        String missingInstanceNameMessage = "§4BŁĄD! §cWprowadź nazwę areny!";

        if (command.getName().equalsIgnoreCase("za")) {

            if (args.length == 0) {
                sender.sendMessage(missingArgumentsMessage);
                return true;
            }

            switch(args[0]) {
                case "help": {
                    if (cAIE.obtain(args, 1).equals("false")) {
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

                    if (Objects.isNull(args[1])) {
                        sender.sendMessage(missingInstanceNameMessage);
                        return true;
                    }

                    if (Objects.isNull(args[2])) {
                        sender.sendMessage(missingArgumentsMessage);
                        return true;
                    }

                    switch (args[2]) {
                        case "create":
                            GameManager.getManager().createArena((Player) sender, args[1]);
                    }

                return true;
            }

        }
        return false;
    }
}
