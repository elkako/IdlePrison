package org.elako.idleprison.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.items.MaterialesManager;
import org.elako.idleprison.items.notas.NotaManager;
import org.elako.idleprison.player.rango.Rangos;
import org.elako.idleprison.player.PlayerManager;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.rango.RangosManager;
import org.elako.idleprison.player.TreeSkillManager;

import java.util.Arrays;

public class JoinPlayerEvent implements Listener {
    RangosManager rangoManager;
    DineroManager dineroManager;
    PlayerManager playerManager;
    public JoinPlayerEvent(RangosManager rango, DineroManager dinero, PlayerManager player) {
        playerManager = player;
        dineroManager = dinero;
        rangoManager = rango;
    }

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent e){
        Player p = e.getPlayer();
        String sp = p.getName();
        if(playerManager.addNewJugador(sp)){
            IdlePrison.getPlugin().escribirRango(sp, Rangos.NUEVO.toString());
            IdlePrison.getPlugin().escribirDinero(sp, 0.0);
            IdlePrison.getPlugin().escribirDineroRenacer(sp, 0.0);
            IdlePrison.getPlugin().escribirDineroRun(sp, 0.0);

            IdlePrison.getPlugin().escribirNotas(sp, "000000000000000000000000000000000000000000000000000000000000");
            IdlePrison.getPlugin().escribirNotasRecibidas(sp, "000000000000000000000000000000000000000000000000000000000000");
            IdlePrison.getPlugin().escribirRecompensas(sp, "00000000000000000000");

            IdlePrison.getPlugin().escribirIdle(sp, 0, "1");
            IdlePrison.getPlugin().escribirIdle(sp, 0, "2");
            IdlePrison.getPlugin().escribirIdle(sp, 0, "3");
            IdlePrison.getPlugin().escribirIdle(sp, 0, "4");
            IdlePrison.getPlugin().escribirIdle(sp, 0, "5");
            IdlePrison.getPlugin().escribirIdle(sp, 0, "6");


            IdlePrison.getPlugin().escribirTreeSkill(sp, 0, "1");
            IdlePrison.getPlugin().escribirTreeSkill(sp, 0, "2");
            IdlePrison.getPlugin().escribirTreeSkill(sp, 0, "3");

            IdlePrison.getPlugin().escribirPermisos(sp, "00");
            IdlePrison.getPlugin().escribirItemsVendidos(sp, 0);
            IdlePrison.getPlugin().escribirBloquesRotos(sp, 0);

            p.sendMessage("Ahora eres " + Arrays.stream(Rangos.values()).findFirst());
            p.getInventory().addItem( MaterialesManager.getItem(IpMateriales.PICO_MADERA) );
            NotaManager.getNota(p,1);
        }

        p.setHealthScale(20+ TreeSkillManager.getHealthBoost(sp));
    }
}
