package matthewadev.bridgeduels.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()){
            sender.sendMessage(ChatColor.RED + "You do not have the privileges to execute this command.");
            return false;
        }
        sender.sendMessage(ChatColor.GREEN + "Stopping game");
        return false;
    }
}
