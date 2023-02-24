package matthewadev.bridgeduels.listener;

import matthewadev.bridgeduels.game.GameManager;
import matthewadev.bridgeduels.game.PlayerGame;
import matthewadev.bridgeduels.game.PlayerState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        PlayerGame p = new PlayerGame();
        p.setPlayer(e.getPlayer());
        p.setTeam(PlayerState.SPECTATOR);
        p.getPlayer().teleport(GameManager.lobbyLocation);
        GameManager.addPlayer(p);
    }
}
