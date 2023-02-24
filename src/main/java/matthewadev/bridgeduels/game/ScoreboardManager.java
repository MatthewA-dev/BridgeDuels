package matthewadev.bridgeduels.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;

public class ScoreboardManager {
    public static Scoreboard generateScoreboard(){
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = sb.registerNewObjective("sidebar","");
        objective.setDisplayName(ChatColor.RED + "BRIDGE" + ChatColor.BLUE + "DUELS");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        ArrayList<Score> scores = new ArrayList<>();

        scores.add(objective.getScore("      "));
        scores.add(objective.getScore(ChatColor.RED + "Red: " + ChatColor.WHITE + GameManager.redScore + "/" + GameManager.maxScore));
        scores.add(objective.getScore(ChatColor.BLUE + "Blue: " + ChatColor.WHITE + GameManager.blueScore + "/" + GameManager.maxScore));
        scores.add(objective.getScore("         "));

        for (int i = 0; i < scores.size(); i++) {
            Score s = scores.get(i);
            s.setScore(scores.size() - i);
        }
        return sb;
    }
    public static void setScoreboard(){
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setScoreboard(generateScoreboard());
        }
    }
    public static void removeScoreboard(){
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
    }
}
