package org.elako.idleprison.eventos;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.elako.idleprison.comandos.*;
import org.elako.idleprison.crafteos.CrafteoManager;
import org.elako.idleprison.items.*;
import org.elako.idleprison.items.materiales.IpMateriales;
import org.elako.idleprison.items.materiales.MaterialesManager;
import org.elako.idleprison.items.notas.NotaManager;
import org.elako.idleprison.items.notas.TipoNota;
import org.elako.idleprison.player.PlayerManager;
import org.elako.idleprison.player.rango.RangosManager;

public class InteractPlayerEvent implements Listener {
    private final PlayerManager playerManager;
    private final RangosManager rangosManager;
    private final VenderManager venderManager;

    public InteractPlayerEvent(PlayerManager playerManager, RangosManager rangosManager, VenderManager venderManager) {
        this.playerManager = playerManager;
        this.rangosManager = rangosManager;
        this.venderManager = venderManager;
    }

    public void getRecompensa(Player p, int n){
        switch (n){
           case 1:
               p.getInventory().addItem(MaterialesManager.getItem(IpMateriales.ALGA,5));
               break;
           case 2:
           case 3:
                break;
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player p = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (action == Action.RIGHT_CLICK_BLOCK && clickedBlock != null) {
            // Verifica si el jugador ha interactuado con un cartel o un botón
            Material blockType = clickedBlock.getType();

            if (blockType == Material.OAK_SIGN ) { // carteles comandos
                Sign sign = (Sign) clickedBlock.getState();
                String firstLine = sign.getLine(0);

                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 2);
                if(firstLine.contains("general")){
                    p.openInventory(IdleprisonCom.crearInventario(p));
                } else if(firstLine.contains("idle")){
                    p.openInventory(IdleCom.crearInventario(p,1));
                } else if(firstLine.contains("minas")){
                    p.openInventory(MinaCom.crearInventario(p));
                } else if(firstLine.contains("Rankup")){
                    rangosManager.ascender(p);
                } else if(firstLine.contains("hand")){
                    venderManager.venderObjeto(p.getInventory().getItemInMainHand(),p.getInventory().getContents(), p);
                } else if(firstLine.contains("vender")){
                    p.openInventory(VenderCom.crearInventario(p));
                } else if(firstLine.contains("Craftguide")){
                    p.openInventory(CrafteoManager.CraftGuide(p));
                } else if(firstLine.contains("craftear")){
                    p.openWorkbench(null,true);
                }
                
            } else if (blockType == Material.WARPED_SIGN ) { // recompensa
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 100, 2);
                Sign sign = (Sign) clickedBlock.getState();
                String firstLine = sign.getLine(0);
                int recompensa = Integer.parseInt(firstLine.substring(firstLine.length()-1));
                int nota = NotaManager.getNum(TipoNota.SECRETO, recompensa);

                if(!playerManager.isNotaRecibidas(p.getName(), nota)) {
                    NotaManager.getNota(p,nota);
                    getRecompensa(p, recompensa);
                }
            }
        }
    }




}
