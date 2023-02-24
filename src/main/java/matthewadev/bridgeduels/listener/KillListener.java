package matthewadev.bridgeduels.listener;

import matthewadev.bridgeduels.game.GameManager;
import matthewadev.bridgeduels.game.InventoryManager;
import matthewadev.bridgeduels.game.PlayerGame;
import matthewadev.bridgeduels.game.PlayerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class KillListener implements Listener {
    @EventHandler
    public void onAttack(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
            return;
        }
        for (PlayerGame p: GameManager.allPlayers) {
            if(p.getPlayer().equals(e.getEntity())){
                Player player = (Player) e.getEntity();
                if(player.getHealth() - e.getDamage() < 1){
                    e.setCancelled(true);
                    GameManager.triggerKill(p);
                }
            }
        }
    }
}
