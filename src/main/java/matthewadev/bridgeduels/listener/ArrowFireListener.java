package matthewadev.bridgeduels.listener;

import matthewadev.bridgeduels.BridgeDuels;
import matthewadev.bridgeduels.runnables.GiveArrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ArrowFireListener implements Listener {
    @EventHandler
    public void onArrow(ProjectileLaunchEvent e){
        if(e.getEntity().getType().equals(EntityType.ARROW)){
            if(e.getEntity().getShooter() instanceof Player){
                GiveArrow arrow = new GiveArrow((Player) e.getEntity().getShooter());
                arrow.runTaskLater(BridgeDuels.self, 70); // 3.5 seconds
            }
        }
    }
}
