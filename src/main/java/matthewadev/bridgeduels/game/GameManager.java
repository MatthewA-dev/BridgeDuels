package matthewadev.bridgeduels.game;

import matthewadev.bridgeduels.BridgeDuels;
import matthewadev.bridgeduels.runnables.AfterRoundWait;
import matthewadev.bridgeduels.runnables.FinishGame;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Score;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class GameManager {
    public static final Location lobbyLocation = new Location(Bukkit.getWorld("world"),0.5,250,0.5);
    public static final Location blueCageLocation = new Location(Bukkit.getWorld("world"), -32.5, 106, 0.5);
    public static final Location redCageLocation = new Location(Bukkit.getWorld("world"), 32.5, 106, 0.5);
    public static final Location center = new Location(Bukkit.getWorld("world"), 0.5, 106, 0.5);
    public static State gameState = State.LOBBY;
    public static ArrayList<PlayerGame> allPlayers = new ArrayList<>();
    public static int redScore = 0;
    public static int blueScore = 0;
    public static int maxScore = 5;
    public static void addPlayer(PlayerGame p){
        allPlayers.add(p);
    }
    public static void setTeamForPlayer(Player p, PlayerState ps){
        for (int i = 0; i < allPlayers.size(); i++) {
            if(allPlayers.get(i).getPlayer().equals(p)){
                PlayerGame playerGame = allPlayers.get(i);
                playerGame.setTeam(ps);
                allPlayers.set(i, playerGame);
            }
        }
    }
    public static void start(){
        ScoreboardManager.setScoreboard();
        gameState = State.STARTING;
        for (PlayerGame p: allPlayers) {
            triggerKill(p);
        }
        CageManager.placeFloor();
        AfterRoundWait wait = new AfterRoundWait(BridgeDuels.self, 5);
        wait.runTaskTimer(BridgeDuels.self, 0, 20);
    }

    public static void stop(){
        gameState = State.END;
    }
    public static void resetToLobbyState(){
        for(PlayerGame p: allPlayers){
            p.getPlayer().teleport(lobbyLocation);
        }
        gameState = State.LOBBY;
    }
    public static void triggerWinRound(PlayerState ps){
        ScoreboardManager.setScoreboard();
        if(ps.equals(PlayerState.SPECTATOR)){
            return;
        }else if(ps.equals(PlayerState.REDTEAM)){
            redScore++;
            if(redScore >= maxScore){
                triggerWin(PlayerState.REDTEAM);
                return;
            }
        }else if(ps.equals(PlayerState.BLUETEAM)){
            blueScore++;
            if(blueScore >= maxScore){
                triggerWin(PlayerState.BLUETEAM);
                return;
            }
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.GREEN + ps.name() + " has won the round!");
        }
        start();
    }
    public static void triggerWin(PlayerState ps){
        if(ps.equals(PlayerState.SPECTATOR)){
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.GREEN + ps.name() + " has won the game!");
        }
        BridgeDuels.b.removeAllBlocks();
        gameState = State.END;
        FinishGame end = new FinishGame();
        for(PlayerGame p: allPlayers){
            InventoryManager.clearInventory(p);
        }
        end.runTaskLater(BridgeDuels.self,200);
    }
    public static void triggerKill(PlayerGame p){
        if(p.getTeam() == PlayerState.SPECTATOR){
            p.getPlayer().teleport(center);
            p.getPlayer().setHealth(20);
            p.getPlayer().setGameMode(GameMode.SPECTATOR);
            return;
        }
        InventoryManager.giveInventory(p);
        Player player = p.getPlayer();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setVelocity(new Vector(0,0,0));

        if(p.getTeam() == PlayerState.BLUETEAM){
            player.teleport(blueCageLocation);
        }else if(p.getTeam() == PlayerState.REDTEAM){
            player.teleport(redCageLocation);
        }
    }
}

