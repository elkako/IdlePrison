package org.elako.idleprison.items;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.elako.idleprison.player.Rangos;

import java.util.List;

public class IpArmaduraColor extends IpArmadura {
    private final Color color;
    // colores RGB https://htmlcolorcodes.com/es/

    public IpArmaduraColor(Material material, String nombre, List<String> lore, Rangos permiso, IpMateriales ipMateriales,
                           int vida, int regeneracion, Color color) {
        super(material, nombre, lore, permiso, ipMateriales, vida, regeneracion);
        this.color = color;
    }


    @Override
    public ItemStack getItem(int cantidad){
        ItemStack item = super.getItem(cantidad);
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) return item;

        LeatherArmorMeta lam = (LeatherArmorMeta) itemMeta;
        lam.setColor(color);

        item.setItemMeta(itemMeta);
        return item;
    }

}
