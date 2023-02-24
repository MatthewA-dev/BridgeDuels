package matthewadev.bridgeduels.game;

import org.bukkit.entity.Player;

public class PlayerGame {
    private Player player;
    private PlayerState team;

    public void setTeam(PlayerState team) {
        this.team = team;
    }
    public void setPlayer(Player p){
        this.player = p;
    }

    public PlayerState getTeam() {
        return team;
    }

    public Player getPlayer() {
        return player;
    }
}
