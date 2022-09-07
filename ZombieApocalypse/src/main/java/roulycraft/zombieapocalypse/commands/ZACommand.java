package roulycraft.zombieapocalypse.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class ZACommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("za")) {

            if (args.length == 0) {
                sender.sendMessage("Wincyj argumentów dawaj §x§0§0§f§f§0§0test §6test");
                return true;
            }

            if (!sender.isOp() || !sender.hasPermission("podstawka")) {
                sender.sendMessage("Permisje kurwa");
                return true;
            }

            if (Objects.equals(args[0], "help")) {
                if (Objects.equals(args[1], "ct" )) {
                    sender.sendMessage("Creation Tools ZA:");
                    sender.sendMessage("/za ct <arena> create - Stwarza");
                    sender.sendMessage("/za ct <arena> lobby - Ustawia lokacje lobby areny");
                    sender.sendMessage("/za ct <arena> spawn <cyfra> - ustawia spawn graczy");
                    return true;
                }
                sender.sendMessage("Komendy ZA:");
                sender.sendMessage("/za help - Pomoc ogólna");
                sender.sendMessage("/za leave - Wyjście z areny");
                if (sender.isOp() || sender.hasPermission("technik")) {
                    sender.sendMessage("/za join <arena> - Dołączenia na konkretną arenę");
                    sender.sendMessage("/za help ct - Pomoc tworzeniu aren");
                    sender.sendMessage("/za help debug - Pomoc w testowaniu lub debugowaniu aren");
                }

                return true;
            }
            sender.sendMessage("Jest zajebiście");
            return true;

            }

        return false;
    }
}
