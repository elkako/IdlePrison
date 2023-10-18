package org.elako.idleprison.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.rango.Rangos;

import java.util.LinkedList;
import java.util.List;

public class IpMaterial {

    private final Material material;
    private final String nombre;
    private final LinkedList<String> lore;
    private final double precio;
    private final Rangos permiso;
    private final IpMateriales ipMateriales;

    public IpMaterial(Material material, String nombre, List<String> lore, double precio, Rangos permiso, IpMateriales ipMateriales) {
        this.material = material;
        this.nombre = nombre;
        this.lore = new LinkedList<>(lore);
        if (precio != -1) this.lore.add(ChatColor.GREEN + DineroManager.dineroToString(precio));
        this.precio = precio;
        this.permiso = permiso;
        this.ipMateriales=ipMateriales;
    }

    public ItemStack getItem(int cantidad){
        ItemStack item = new ItemStack(material, cantidad);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.GRAY + String.valueOf(ChatColor.BOLD) + nombre);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public Material getMaterial(){ return material; }
    public Rangos getRango(){ return permiso; }
    public double getDinero(){ return precio; }
    public Rangos getPermiso() { return permiso; }
    public String getNombre() { return nombre; }

    public boolean isNombre(IpMateriales ipMateriales){ return this.ipMateriales.equals(ipMateriales); }

}
