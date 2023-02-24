package matthewadev.bridgeduels.commands;

import matthewadev.bridgeduels.game.GameManager;
import matthewadev.bridgeduels.game.PlayerState;
import matthewadev.bridgeduels.game.State;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()){
            sender.sendMessage(ChatColor.RED + "You do not have the privileges to execute this command.");
            return false;
        }
        if(!GameManager.gameState.equals(State.GAME) && !GameManager.gameState.equals(State.STARTING)){
            sender.sendMessage(ChatColor.RED + "No game is currently in progress.");
            return false;
        }
        GameManager.triggerWin(PlayerState.valueOf(args[0]));
        return false;
    }
}
