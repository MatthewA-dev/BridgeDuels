package matthewadev.bridgeduels.game;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class InventoryManager {
    public static void giveInventory(PlayerGame p){
        Player player = p.getPlayer();
        Inventory i = player.getInventory();
        clearInventory(p);

        //sword
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.spigot().setUnbreakable(true);
        sword.setItemMeta(swordMeta);
        i.setItem(0,sword);

        //pick
        ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta pickMeta = pick.getItemMeta();
        pickMeta.spigot().setUnbreakable(true);
        pickMeta.addEnchant(Enchantment.DIG_SPEED, 2, true);
        pick.setItemMeta(pickMeta);
        i.setItem(1,pick);

        //bow
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.spigot().setUnbreakable(true);
        bow.setItemMeta(bowMeta);
        i.setItem(2,bow);

        //blocks + armor
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta cmeta = (LeatherArmorMeta) chest.getItemMeta();
        LeatherArmorMeta lmeta = (LeatherArmorMeta) legs.getItemMeta();
        LeatherArmorMeta bmeta = (LeatherArmorMeta) boots.getItemMeta();
        ItemStack blocks = null;
        if(p.getTeam().equals(PlayerState.REDTEAM)) {
            cmeta.setColor(Color.RED);
            lmeta.setColor(Color.RED);
            bmeta.setColor(Color.RED);
            blocks = new ItemStack(Material.STAINED_CLAY, 64, DyeColor.RED.getData());
        }else if(p.getTeam().equals(PlayerState.BLUETEAM)){
            cmeta.setColor(Color.BLUE);
            lmeta.setColor(Color.BLUE);
            bmeta.setColor(Color.BLUE);
            blocks = new ItemStack(Material.STAINED_CLAY, 64, DyeColor.BLUE.getData());
        }
        chest.setItemMeta(cmeta);
        legs.setItemMeta(lmeta);
        boots.setItemMeta(bmeta);
        i.setItem(38,chest);
        i.setItem(37,legs);
        i.setItem(36,boots);
        i.setItem(3,blocks);
        i.setItem(4,blocks);

        ItemStack arrow = new ItemStack(Material.ARROW);
        i.setItem(8,arrow);

        ItemStack gapples = new ItemStack(Material.GOLDEN_APPLE, 8);
        i.setItem(5,gapples);

        player.updateInventory();
    }
    public static void clearInventory(PlayerGame p){
        p.getPlayer().getInventory().clear();
        p.getPlayer().getInventory().setHelmet(null);
        p.getPlayer().getInventory().setChestplate(null);
        p.getPlayer().getInventory().setLeggings(null);
        p.getPlayer().getInventory().setBoots(null);
        p.getPlayer().updateInventory();
    }
}
