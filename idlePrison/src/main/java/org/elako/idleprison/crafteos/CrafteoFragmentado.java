package org.elako.idleprison.crafteos;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.player.rango.Rangos;

import java.util.LinkedList;

public class CrafteoFragmentado extends Crafteo {
    public CrafteoFragmentado(LinkedList<ItemStack> receta, ItemStack resultado, Rangos permiso) {
        super(receta, resultado, permiso);
    }

    @Override
    public ItemStack getIcono() { return getResultado(); }

    @Override
    public int isCrafteo(LinkedList<ItemStack> items) {
        LinkedList<ItemStack> receta = getReceta();
        for (ItemStack item : items) {
            if(!receta.pop().getType().equals(item.getType()) || notComparteEnchantis(item) )
                return 0;
        }
        return 1;
    }

    private boolean notComparteEnchantis(ItemStack item) {
        return !getReceta().getFirst().getEnchantments().equals(item.getEnchantments());
    }

    @Override
    public Inventory getGuide(Player p) {
        return guiaCrafteoSimple(p, new LinkedList<>(getReceta()), new ItemStack(getResultado()));
    }
}
