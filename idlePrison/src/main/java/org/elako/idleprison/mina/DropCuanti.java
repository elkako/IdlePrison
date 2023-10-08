package org.elako.idleprison.mina;

import org.bukkit.inventory.ItemStack;

public class DropCuanti {
    private final ItemStack item;
    private final int min;
    private final int max;

    public DropCuanti(ItemStack item, int min, int max) {
        this.item = item;
        this.min = min;
        this.max = max;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
