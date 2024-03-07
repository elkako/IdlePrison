package org.elako.idleprison.eventos;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageAbortEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.mina.RompiendoBloque;

import java.util.HashMap;

public class BlockDigEvent implements Listener {
    HashMap<Player,RompiendoBloque> rompiendos = new HashMap<>();

    @EventHandler
    public void onBlockDamage(BlockDamageEvent e) {
        //event.getPlayer().sendMessage( String.valueOf( event.getBlock().getType() ) );
        Player player = e.getPlayer();
        Block block = e.getBlock();

        IdlePrison plugin = IdlePrison.getPlugin();

        RompiendoBloque rompiendo = new RompiendoBloque(player, block);
        rompiendos.put(player,rompiendo);

        int segundos = 1;

        if (block.getType() != Material.AIR) {
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
               rompiendo.romperBloque();
               if(rompiendos.containsKey(player)) rompiendos.remove(player);
            }, 20L*segundos); // 20 ticks = 1 segundo
        }
    }

    @EventHandler
    public void onBlockDamage(BlockDamageAbortEvent event) {
        Player player = event.getPlayer();

        player.sendMessage("hola");
        if(rompiendos.containsKey(player)) {
            rompiendos.get(player).terminarRompiendoBloque();
            rompiendos.remove(player);
        }
    }

}


