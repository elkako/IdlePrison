package org.elako.idleprison.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.items.materiales.IpMaterial;
import org.elako.idleprison.items.materiales.IpMateriales;
import org.elako.idleprison.player.rango.Rangos;

import java.util.List;

public class IpHerramienta extends IpMaterial {
    private final Crear crear;
    public interface Crear{
        void crear(ItemMeta itemMeta);
    }

    public IpHerramienta(Material material, String nombre, List<String> lore, Rangos permiso, IpMateriales ipMateriales, Crear crear) {
        super(material, nombre, lore, -1, permiso, ipMateriales);
        this.crear = crear;
    }

    @Override
    public ItemStack getItem(int cantidad){
        ItemStack item = super.getItem(cantidad);
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) return item;
        itemMeta.setDisplayName(ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + getNombre());

        crear.crear(itemMeta);
        item.setItemMeta(itemMeta);
        return item;
    }
}
