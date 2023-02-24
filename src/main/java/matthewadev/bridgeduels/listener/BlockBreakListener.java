package matthewadev.bridgeduels.listener;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.ArrayList;

public class BlockBreakListener implements Listener {
    private ArrayList<Block> playerBlocks = new ArrayList<>();
    private ArrayList<BlockTuple> bridgeBlocksFinal = new ArrayList<>();
    @EventHandler
    public void onExplode(EntityExplodeEvent e){
        for (int i = e.blockList().size() - 1; i >= 0; i--) {
            Block b = e.blockList().get(i);
            boolean cancel = true;
            for (Block playerBlock : playerBlocks) {
                if (b.equals(playerBlock)) {
                    cancel = false;
                }
            }
            if(cancel){
                e.blockList().remove(b);
            }
        }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        e.setCancelled(true);
        for (int i = 0; i < playerBlocks.size(); i++){
            if(e.getBlock().equals(playerBlocks.get(i))){
                playerBlocks.remove(e.getBlock());
                e.setCancelled(false);
                break;
            }
        }
        if(e.isCancelled()){
            e.getPlayer().sendMessage(ChatColor.RED + "You can only break blocks placed by a player!");
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        playerBlocks.add(e.getBlock());
    }
    public void removeAllBlocks(){
        for(Block b: playerBlocks){
            b.setType(Material.AIR);
        }
        for(BlockTuple bt: bridgeBlocksFinal){
            Bukkit.getWorld("world").getBlockAt(bt.getL()).setType(bt.getMat());
            Bukkit.getWorld("world").getBlockAt(bt.getL()).setData(bt.getColor());
        }
    }
    public void registerBlocks(){
        for (int x = -20; x <= 20; x++) {
            for (int y = 84; y <= 92; y++) {
                bridgeBlocksFinal.add(new BlockTuple(Bukkit.getWorld("world").getBlockAt(x,y,0).getType(),
                                                    Bukkit.getWorld("world").getBlockAt(x,y,0).getLocation(),
                                                    Bukkit.getWorld("world").getBlockAt(x,y,0).getData()));
                playerBlocks.add(Bukkit.getWorld("world").getBlockAt(x,y,0));
            }
        }
    }

//    public void registerNoBreakBlocks(){
//        World w = Bukkit.getWorld("world");
//        for (int cy = -4; cy <= 4 ; cy++) {
//            for (int cx = -4; cx <= 4; cx++) {
//                // Get all blocks that are in the 16 chunks around spawn and add them to illegal blocks
//                int ccx = cx << 4;
//                int ccz = cy << 4;
//                for (int x = ccx; x < ccx + 16; x++) {
//                    for (int z = ccz; z < ccz + 16; z++) {
//                        for (int y = 0; y < 256; y++) {
//                            if(w.getBlockAt(x,y,z).getType() != Material.AIR) {
//                                badBlocks.add(w.getBlockAt(x,y,z));
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
}
class BlockTuple{
    private Material mat;
    private Location l;
    private byte color;
    public BlockTuple(Material mat, Location l, byte color){
        this.mat = mat;
        this.l = l;
        this.color = color;
    }

    public Material getMat() {
        return mat;
    }

    public void setMat(Material mat) {
        this.mat = mat;
    }

    public Location getL() {
        return l;
    }

    public void setL(Location l) {
        this.l = l;
    }

    public byte getColor() {
        return color;
    }

    public void setColor(byte color) {
        this.color = color;
    }
}