package matthewadev.bridgeduels.commands;

import matthewadev.bridgeduels.game.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()){
            sender.sendMessage(ChatColor.RED + "You do not have the privileges to execute this command.");
            return false;
        }
        GameManager.redScore = 0;
        GameManager.blueScore = 0;
        try{
            GameManager.maxScore = Integer.parseInt(args[0]);
        }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
            sender.sendMessage(ChatColor.RED + "No max rounds provided. Defaulting to 5.");
            GameManager.maxScore = 5;
        }
        GameManager.start();
        sender.sendMessage(ChatColor.GREEN + "Starting game");
        return false;
    }
}
