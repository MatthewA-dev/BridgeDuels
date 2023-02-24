package matthewadev.bridgeduels.runnables;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GiveArrow extends BukkitRunnable {
    private Player p;
    public GiveArrow(Player p){
        this.p = p;
    }
    @Override
    public void run() {
        p.getInventory().setItem(8, new ItemStack(Material.ARROW));
    }
}
