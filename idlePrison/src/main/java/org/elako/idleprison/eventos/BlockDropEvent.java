package org.elako.idleprison.eventos;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.elako.idleprison.mina.MinaManager;

public class BlockDropEvent implements Listener {
    public MinaManager bloquesPrison;

    public BlockDropEvent(MinaManager mina) {
        bloquesPrison = mina;
    }

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent e){
        if(bloquesPrison.isPrison(e.getBlock())) e.setCancelled(true);
    }
}
