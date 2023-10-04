package org.elako.idleprison.eventos;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.elako.idleprison.items.NotaManager;
import org.elako.idleprison.items.PicosManager;
import org.elako.idleprison.player.RangosManager;

public class ItemDropEvent implements Listener {
    RangosManager rangosManager;

    public ItemDropEvent(RangosManager rangosManager) {
        this.rangosManager = rangosManager;
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(!e.getItemDrop().getItemStack().getType().equals(Material.PAPER)) return;

        int nota = Integer.parseInt(String.valueOf(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().charAt(9)));
        e.getItemDrop().remove();

        NotaManager.sendMensajeRecompensa(p, nota);

        if(nota == 1) {
            p.getInventory().setItem(8, PicosManager.getMenu());
            rangosManager.setPlayer(p.getName(), "condenado4");
            NotaManager.getNota(p,2);
        }

    }

}
