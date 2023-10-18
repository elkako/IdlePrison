package org.elako.idleprison.items.armaduras;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.items.IpMaterial;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.player.rango.Rangos;

import java.util.List;

public class IpArmadura extends IpMaterial {
    private final int vida;
    private final SetsArmadura setArmadura;

    public IpArmadura(Material material, String nombre, List<String> lore, Rangos permiso, IpMateriales ipMateriales,
                      int vida, SetsArmadura setArmadura) {
        super(material, nombre, lore, -1, permiso, ipMateriales);
        this.vida = vida;
        this.setArmadura = setArmadura;
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
        lore.add(lore.size()-1, ChatColor.WHITE + "-Vida máx: +" + vida/2.0 );
        itemMeta.setUnbreakable(true);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public int getVida() { return vida; }

    public SetsArmadura getSetArmadura() { return setArmadura; }
}
