package org.elako.idleprison.eventos;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.elako.idleprison.crafteos.CraftManager;
import org.elako.idleprison.player.rango.RangosManager;


public class MesasCrafteoEvent implements Listener {
    RangosManager rangosManager;
    CraftManager craftManager;

    public MesasCrafteoEvent(RangosManager rangos, CraftManager crafteo) {
        rangosManager = rangos;
        craftManager = crafteo;
    }

    @EventHandler
    public void uno(PrepareItemCraftEvent e){ //impide craftear
        CraftingInventory ci = e.getInventory();
        if(ci.getResult() == null) return;

        ci.getResult().setAmount(0);
    }

}
