package org.elako.idleprison.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
        itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + getNombre());

        item.setItemMeta(itemMeta);
        return item;
    }
}
