package org.elako.idleprison.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.player.Rangos;

import java.util.List;

public class IpComida extends IpMaterial {
    private final Comer comer;

    public interface Comer{
        void comer(Player p);
    }

    public IpComida(Material material, String nombre, List<String> lore, double precio, Rangos permiso, IpMateriales ipMateriales, Comer comer) {
        super(material, nombre, lore, precio, permiso, ipMateriales);
        this.comer = comer;
    }

    public void comido(Player p){
        comer.comer(p);
    }

    @Override
    public ItemStack getItem(int cantidad){
        ItemStack item = super.getItem(cantidad);
        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + getNombre());


        List<String> lore = itemMeta.getLore() ;

        lore.add(lore.size()-2, ChatColor.WHITE + "Efectos:");
        //if (regeneracion == 0) lore.add(lore.size()-2, ChatColor.WHITE + "-Vida máx: +" + vida);
        //else lore.add(lore.size()-2, ChatColor.WHITE + "-Regeneración: " + regeneracion);

        lore.add(lore.size()-2, ChatColor.WHITE + "-Vida máx: +");

        item.setItemMeta(itemMeta);
        return item;
    }
}
