package matthewadev.bridgeduels.listener;

import matthewadev.bridgeduels.game.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VoidFallListener implements Listener {
    @EventHandler
    public void onFall(PlayerMoveEvent e){
        if(GameManager.gameState == State.STARTING || GameManager.gameState == State.GAME){
            if(e.getPlayer().getLocation().getY() < 0){
                for(PlayerGame pg : GameManager.allPlayers){
                    if(pg.getPlayer().equals(e.getPlayer())) {
                        GameManager.triggerKill(pg);
                    }
                }
            }
        }
    }
}
