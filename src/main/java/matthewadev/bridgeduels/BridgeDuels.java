package matthewadev.bridgeduels;

import matthewadev.bridgeduels.commands.*;
import matthewadev.bridgeduels.game.GameManager;
import matthewadev.bridgeduels.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class BridgeDuels extends JavaPlugin {
    public static BridgeDuels self;
    public static BlockBreakListener b = new BlockBreakListener(); // used to remove blocks on disable
    @Override
    public void onEnable() {
        self = this;
        Location l = GameManager.lobbyLocation;
        Bukkit.getWorld("world").setSpawnLocation(l.getBlockX(),l.getBlockY(),l.getBlockZ());
        b.registerBlocks();
        getServer().getPluginManager().registerEvents(b, self);
        getServer().getPluginManager().registerEvents(new JoinListener(), self);
        getServer().getPluginManager().registerEvents(new KillListener(), self);
        getServer().getPluginManager().registerEvents(new VoidFallListener(), self);
        getServer().getPluginManager().registerEvents(new FallDamageListener(), self);
        getServer().getPluginManager().registerEvents(new MobSpawnListener(), self);
        getServer().getPluginManager().registerEvents(new GoalEnterListener(), self);
        getServer().getPluginManager().registerEvents(new ArrowFireListener(), self);
        getCommand("startgame").setExecutor(new StartCommand());
        getCommand("stopgame").setExecutor(new StopCommand());
        getCommand("setTeam").setExecutor(new SetTeamCommand());
        getCommand("win").setExecutor(new WinCommand());
        getCommand("winround").setExecutor(new WinRoundCommand());
    }

    @Override
    public void onDisable() {
        b.removeAllBlocks();
    }
}
