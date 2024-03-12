package org.elako.idleprison.eventos;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.mina.RomperBloquesManager;
import org.elako.idleprison.player.PlayerManager;

public class BreakPutBlockEvent implements Listener {
    private final RomperBloquesManager romperBloquesManager;
    private final PlayerManager playerManager;

    public BreakPutBlockEvent(RomperBloquesManager romperbloques, PlayerManager player) {
        playerManager = player;
        romperBloquesManager = romperbloques;
    }

    @EventHandler
    public void onPutEvent(BlockPlaceEvent e){
        Player p = e.getPlayer();

        if (!playerManager.isPermisoConstructor(p.getName())){
            e.setCancelled(true);
            p.sendMessage("Permiso denegado: no pongas cosas");
        }

    }

    @EventHandler
    public void onDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBreakEvent(BlockBreakEvent e){
        Player p = e.getPlayer();
        ItemStack pico = p.getInventory().getItemInMainHand(); //obtener item mano principal
        Block bloque = e.getBlock();

        if (!romperBloquesManager.romperBloque(pico,p,bloque)){
            e.setCancelled(true);
            p.sendMessage("Permiso denegado: porfavor vaya a la zona de mina");
        }
    }

}
