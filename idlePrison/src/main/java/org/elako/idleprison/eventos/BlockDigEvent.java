package org.elako.idleprison.eventos;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageAbortEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.mina.BloqueManager;
import org.elako.idleprison.mina.IpBloque;
import org.elako.idleprison.mina.RomperBloquesManager;
import org.elako.idleprison.mina.RompiendoBloque;

import java.util.HashMap;

public class BlockDigEvent implements Listener {
    private final HashMap<Player,RompiendoBloque> rompiendos;
    private final RomperBloquesManager romperBloque;
    private final BloqueManager bloqueManager;

    public BlockDigEvent(RomperBloquesManager romperBloque, BloqueManager bloqueManager) {
        this.romperBloque = romperBloque;
        rompiendos = new HashMap<>();
        this.bloqueManager = bloqueManager;
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        IdlePrison plugin = IdlePrison.getPlugin();

        RompiendoBloque rompiendo = new RompiendoBloque(player, block);
        rompiendos.put(player,rompiendo);

        IpBloque ipBloque = bloqueManager.getBloque(block.getType());
        int segundos = ipBloque.getVida();

        if (block.getType() != Material.AIR && segundos != 0) {
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                Player p = e.getPlayer();
                ItemStack pico = p.getInventory().getItemInMainHand(); //obtener item mano principal
                Block bloque = e.getBlock();

                if (rompiendo.isActivo() && block.getType() != Material.AIR){
                    if (!romperBloque.romperBloque(pico,p,bloque)) {
                        e.setCancelled(true);
                        p.sendMessage("Permiso denegado: porfavor vaya a la zona de mina");
                    }
                    block.setType(Material.AIR);
                }


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


