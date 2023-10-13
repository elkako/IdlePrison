package org.elako.idleprison.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.player.Rangos;

import java.util.List;

public class IpArmadura extends IpMaterial {
    private final int vida;
    private final int regeneracion;

    public IpArmadura(Material material, String nombre, List<String> lore, Rangos permiso, IpMateriales ipMateriales,
                      int vida, int regeneracion) {
        super(material, nombre, lore, -1, permiso, ipMateriales);
        this.vida = vida;
        this.regeneracion = regeneracion;
    }

    @Override
    public ItemStack getItem(int cantidad){
        ItemStack item = super.getItem(cantidad);
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) return item;
        itemMeta.setDisplayName(ChatColor.RED + String.valueOf(ChatColor.BOLD) + getNombre());

        List<String> lore = itemMeta.getLore() ;

        assert lore != null;
        lore.add(lore.size()-1, ChatColor.WHITE + "Efectos:");
        lore.add(lore.size()-1, ChatColor.WHITE + "-Vida máx: +" + vida);
        if (regeneracion != 0) lore.add(lore.size()-1, ChatColor.WHITE + "-Regeneración: " + regeneracion);

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public int getVida() { return vida; }

    public int getRegeneracion() { return regeneracion; }
}
