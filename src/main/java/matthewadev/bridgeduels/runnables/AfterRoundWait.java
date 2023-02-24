package matthewadev.bridgeduels.runnables;

import matthewadev.bridgeduels.game.CageManager;
import matthewadev.bridgeduels.game.GameManager;
import matthewadev.bridgeduels.game.PlayerGame;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static matthewadev.bridgeduels.game.GameManager.allPlayers;

public class AfterRoundWait extends BukkitRunnable {
    private final JavaPlugin plugin;
    private int counter;

    public AfterRoundWait(JavaPlugin plugin, int counter) {
        this.plugin = plugin;
        this.counter = counter;
    }

    @Override
    public void run() {
        if (counter > 0) {
            if(counter == 1) {
                plugin.getServer().broadcastMessage(ChatColor.YELLOW + "The round starts in " + ChatColor.RED + counter + ChatColor.YELLOW + " second!");
            }else{
                plugin.getServer().broadcastMessage(ChatColor.YELLOW + "The round starts in " + ChatColor.RED + counter + ChatColor.YELLOW + " seconds!");
            }
        } else {
            CageManager.removeFloor();
            this.cancel();
        }
        counter --;
    }
}
