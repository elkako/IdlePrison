package org.elako.idleprison.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageAbortEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.elako.idleprison.mina.RompiendoBloque;

import java.util.HashMap;
import java.util.LinkedList;

public class BlockDigEvent implements Listener {
    HashMap<Player,RompiendoBloque> rompiendos = new HashMap<>();

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        //event.getPlayer().sendMessage( String.valueOf( event.getBlock().getType() ) );
        Player player = event.getPlayer();

        if(rompiendos.containsKey(player)) {
            rompiendos.get(player).terminarRompiendoBloque();
            rompiendos.remove(player);
        }

        RompiendoBloque rompiendo = new RompiendoBloque(player);

        rompiendos.put(player,rompiendo);
    }

    @EventHandler
    public void onBlockDamage(BlockDamageAbortEvent event) {
        Player player = event.getPlayer();

        if(rompiendos.containsKey(player)) {
            rompiendos.get(player).terminarRompiendoBloque();
            rompiendos.remove(player);
        }
    }

}


