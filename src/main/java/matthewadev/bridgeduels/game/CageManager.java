package matthewadev.bridgeduels.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.io.IOException;

public class CageManager {
    private static final Location blueCageFloorLocation = new Location(Bukkit.getWorld("world"),-34, 105, -3); // go 5 in x and 7 in 8
    private static final Location redCageFloorLocation = new Location(Bukkit.getWorld("world"),30, 105, -3);
    public static void placeFloor() {// need light gray stained glass
        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 7; z++) {
                Location redCage = redCageFloorLocation.clone();
                Location blueCage = blueCageFloorLocation.clone();
                // corner checking
                if((x == 0 && z == 0) ||
                    (x == 4 && z == 0) ||
                    (x == 0 && z == 6) ||
                    (x == 4 && z == 6)){
                    continue;
                }
                redCage.add(x,0,z);
                blueCage.add(x,0,z);
                redCage.getBlock().setType(Material.STAINED_GLASS);
                blueCage.getBlock().setType(Material.STAINED_GLASS);
                redCage.getBlock().setData((byte) 8);
                blueCage.getBlock().setData((byte) 8);
            }
        }
    }
    public static void removeFloor(){
        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 7; z++) {
                Location redCage = redCageFloorLocation.clone();
                Location blueCage = blueCageFloorLocation.clone();
                // corner checking
                if((x == 0 && z == 0) ||
                        (x == 4 && z == 0) ||
                        (x == 0 && z == 6) ||
                        (x == 4 && z == 6)){
                    continue;
                }
                redCage.add(x,0,z);
                blueCage.add(x,0,z);
                redCage.getBlock().setType(Material.AIR);
                blueCage.getBlock().setType(Material.AIR);
            }
        }
    }
}
