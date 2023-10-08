package org.elako.idleprison.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.player.Rangos;

import java.util.List;

public class IpFragmento extends IpMaterial {
    private final Enchantment enchant;
    private final int nivel;

    public IpFragmento(Material material, String nombre, List<String> lore, double precio, Rangos permiso, Enchantment ench, int nivel, IpMateriales ipMateriales) {
        super(material, nombre, lore, precio, permiso, ipMateriales);
        this.enchant = ench;
        this.nivel = nivel;
    }

    public Enchantment getEnchant() { return enchant; }

    public int getNivel() { return nivel; }

    @Override
    public ItemStack getItem(int cantidad){
        ItemStack item = super.getItem(cantidad);
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) return item;
        if (enchant.equals(Enchantment.DIG_SPEED)) itemMeta.setDisplayName(ChatColor.DARK_BLUE + String.valueOf(ChatColor.BOLD) + getNombre());
        else if (enchant.equals(Enchantment.LOOT_BONUS_BLOCKS)) itemMeta.setDisplayName(ChatColor.DARK_RED + String.valueOf(ChatColor.BOLD) + getNombre());
        else if (enchant.equals(Enchantment.LOOT_BONUS_MOBS)) itemMeta.setDisplayName(ChatColor.DARK_GREEN + String.valueOf(ChatColor.BOLD) + getNombre());
        else if (enchant.equals(Enchantment.CHANNELING)) itemMeta.setDisplayName(ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + getNombre());
        else if (enchant.equals(Enchantment.MULTISHOT)) itemMeta.setDisplayName(ChatColor.DARK_GRAY + String.valueOf(ChatColor.BOLD) + getNombre());

        itemMeta.addEnchant(enchant,nivel,true);
        item.setItemMeta(itemMeta);
        return item;
    }
}
