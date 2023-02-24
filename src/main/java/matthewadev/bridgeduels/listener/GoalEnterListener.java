package matthewadev.bridgeduels.listener;

import matthewadev.bridgeduels.game.GameManager;
import matthewadev.bridgeduels.game.PlayerGame;
import matthewadev.bridgeduels.game.PlayerState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.HashMap;

public class GoalEnterListener implements Listener {
    private HashMap<Player, Long> playerTimes = new HashMap<>();
    @EventHandler
    public void GoalEnter(PlayerPortalEvent e){
        if(e.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL){
            e.setCancelled(true);
            // if i dont add this it runs four times and i dont know why and i dont care iregjeiogjerg
            Long time = playerTimes.get(e.getPlayer());
            if(time == null){
                playerTimes.put(e.getPlayer(), System.currentTimeMillis());
            }else if(System.currentTimeMillis() - time < 20){
                return;
            }
            playerTimes.put(e.getPlayer(), System.currentTimeMillis());

            for (PlayerGame pg : GameManager.allPlayers){
                if(pg.getPlayer().equals(e.getPlayer())){
                    if(pg.getTeam() == PlayerState.SPECTATOR){
                        pg.getPlayer().teleport(GameManager.center);
                    }else{
                        Location endPortal = e.getPlayer().getLocation();
                        if(endPortal.distance(GameManager.blueCageLocation) > endPortal.distance(GameManager.redCageLocation)){// in red goal
                            if(pg.getTeam() == PlayerState.BLUETEAM){
                                GameManager.triggerKill(pg);
                                GameManager.triggerWinRound(pg.getTeam());
                            }else{
                                GameManager.triggerKill(pg);
                            }
                        }else{ // in blue goal
                            if(pg.getTeam() == PlayerState.REDTEAM){
                                GameManager.triggerKill(pg);
                                GameManager.triggerWinRound(pg.getTeam());
                            }else{
                                GameManager.triggerKill(pg);
                            }
                        }
                    }
                }
            }
        }
    }
}
