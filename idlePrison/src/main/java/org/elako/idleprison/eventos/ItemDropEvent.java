package org.elako.idleprison.eventos;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.items.MaterialesManager;
import org.elako.idleprison.items.notas.NotaManager;
import org.elako.idleprison.player.rango.RangosManager;

public class ItemDropEvent implements Listener {
    RangosManager rangosManager;

    public ItemDropEvent(RangosManager rangosManager) {
        this.rangosManager = rangosManager;
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        ItemStack item = e.getItemDrop().getItemStack();
        if(!item.getType().equals(Material.PAPER)) return;
        int nota;

        if(item.getItemMeta() == null) return;

        try {
            nota = Integer.parseInt(item.getItemMeta().getDisplayName().substring(9,11));
        } catch (NumberFormatException excepcion) {
            nota = Integer.parseInt(item.getItemMeta().getDisplayName().substring(9,10));
        }

        e.getItemDrop().remove();

        NotaManager.sendMensajeRecompensa(p, nota);

        if(nota == 1) {
            p.getInventory().setItem(8, MaterialesManager.getItem(IpMateriales.MENU));
            rangosManager.setPlayer(p.getName(), "condenado4");
            NotaManager.getNota(p,2);
        }

    }

}
