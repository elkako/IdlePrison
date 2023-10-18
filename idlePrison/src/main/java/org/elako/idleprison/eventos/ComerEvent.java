package org.elako.idleprison.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.elako.idleprison.items.IpComida;
import org.elako.idleprison.items.IpMaterial;
import org.elako.idleprison.items.MaterialesManager;
import org.elako.idleprison.player.rango.RangosManager;

public class ComerEvent implements Listener {
    RangosManager rangosManager;

    public ComerEvent(RangosManager rangosManager) {
        this.rangosManager = rangosManager;

    }

    @EventHandler
    public void onEatEvent(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();

        IpMaterial mate = MaterialesManager.itemToMaterial(e.getItem());
        if (mate == null) return;

        if ( rangosManager.isPermitido(p.getName(), mate.getPermiso()) ) {
            IpComida comida = (IpComida) mate;
            comida.comido(p);
        } else p.sendMessage("Rango insuficiente");

    }
}
