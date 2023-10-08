package org.elako.idleprison.mina;

import org.bukkit.inventory.ItemStack;

public class DropProb {
    private final ItemStack item;
    private final double prob;

    public DropProb(ItemStack item, double prob) {
        this.item = item;
        this.prob = prob;
    }

    public ItemStack getItem() { return item; }

    public double getProb() { return prob; }
}
