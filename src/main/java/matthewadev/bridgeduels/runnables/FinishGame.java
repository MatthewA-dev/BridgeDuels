package matthewadev.bridgeduels.runnables;

import matthewadev.bridgeduels.game.GameManager;
import matthewadev.bridgeduels.game.PlayerGame;
import matthewadev.bridgeduels.game.ScoreboardManager;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitRunnable;

public class FinishGame extends BukkitRunnable {
    @Override
    public void run() {
        GameManager.resetToLobbyState();
        ScoreboardManager.removeScoreboard();
        for (PlayerGame pg: GameManager.allPlayers) {
            pg.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
        this.cancel();
    }
}
