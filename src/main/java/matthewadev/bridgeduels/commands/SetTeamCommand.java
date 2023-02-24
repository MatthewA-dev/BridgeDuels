package matthewadev.bridgeduels.commands;

import matthewadev.bridgeduels.game.GameManager;
import matthewadev.bridgeduels.game.PlayerState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetTeamCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()){
            sender.sendMessage(ChatColor.RED + "You do not have the privileges to execute this command.");
            return false;
        }
        try {
            Player p = Bukkit.getPlayer(args[0]);
            PlayerState ps = PlayerState.valueOf(args[1]);
            GameManager.setTeamForPlayer(p, ps);
            sender.sendMessage(ChatColor.GREEN + "Set team for " + args[0] + " to " + args[1]);
        }catch(IndexOutOfBoundsException e) {
            sender.sendMessage(ChatColor.RED + "Not enough arguments");
        }catch(IllegalArgumentException e){
            sender.sendMessage(ChatColor.RED + "Invalid arguments");
        }
        return false;
    }
}
