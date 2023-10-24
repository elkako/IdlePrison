package org.elako.idleprison.player.rango;

import org.bukkit.entity.Player;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.PlayerManager;
import org.elako.idleprison.player.TreeSkillManager;

import java.util.*;

public class RangosManager {
    PlayerManager playerManager;
    DineroManager dineroManager;

    public RangosManager(DineroManager dinero, PlayerManager player) {
        playerManager = player;
        dineroManager = dinero;

    }

    public double getDineroAscender(Rangos rango, String player) {
        double rebaja = TreeSkillManager.getPrecioAscender(player);
        return rango.getDineroSiguiente()-rango.getDineroSiguiente()*(rebaja/100) ;
    }

    public Rangos getPlayer(Player jugador){ return playerManager.getRango(jugador.getName()); }

    public String siguienteRango(String jugador){
        List<Rangos> rangos = new ArrayList<>(Arrays.asList(Rangos.values()));
        if(playerManager.getRango(jugador).equals(Rangos.MINERO1)) return "";
        return rangos.get(rangos.indexOf(playerManager.getRango(jugador))+1).toString();
    }

    public boolean isPermitido(String jugador, Rangos rango){
        for (Rangos r: Rangos.values()) {
            if(r.equals(rango)) return true;
            if(r.equals(playerManager.getRango(jugador))) return false;
        }
        return true;
    }

    public void setPlayer(String jugador, String rango){
        if (rango == null) return;
        if (Rangos.contieneNotRango(rango)) return;

        playerManager.setRango(jugador,rango);
    }

    public void ascender(Player jugador){
        String sjugador = jugador.getName();
        double dinero = dineroManager.getDinero(jugador);
        double rebaja = TreeSkillManager.getPrecioAscender(jugador.getName());

        double coste = getPlayer(jugador).getDineroSiguiente();
        coste = coste - coste*(rebaja/100);

        if(siguienteRango(sjugador).equals(Rangos.CONDENADO4.toString())){
            setPlayer(sjugador, siguienteRango(sjugador));
            IdlePrison.scoreboardNotification(jugador,mensajeUnlock(jugador,getPlayer(jugador).toString().toUpperCase(), coste),10);
        }else if(dinero >= coste){
            dineroManager.removeMoney(sjugador,coste);
            setPlayer(sjugador, siguienteRango(sjugador));
            IdlePrison.scoreboardNotification(jugador,mensajeUnlock(jugador,getPlayer(jugador).toString().toUpperCase(), coste),10);
        }else{
            mensajeInsuficiente(jugador,coste-dinero,coste ,siguienteRango(sjugador));
        }
    }

    private LinkedList<String> mensajeUnlock(Player jugador, String rango, double coste) {
        LinkedList<String> s = Rangos.valueOf(rango).getDesbloquearExtra();

        jugador.sendMessage("Ahora eres rango " + rango.toLowerCase() +
                ", has perdido " +  DineroManager.dineroToString(coste, true) +
                " y tu dinero se a quedado en " + DineroManager.dineroToString(dineroManager.getDinero(jugador), true)
                );

        for (String str: s) {
            jugador.sendMessage(str);
        }
        s.addFirst("-------------------------");
        s.addFirst( "Tu dinero se a quedado en " + DineroManager.dineroToString(dineroManager.getDinero(jugador), true));
        s.addFirst("Has perdido " +  DineroManager.dineroToString(coste, true) );
        s.addFirst("Ahora eres rango " + rango.toLowerCase());

        return s;
    }

    private void mensajeInsuficiente(Player player,double dineroRestante, double necesario, String nextRank) {
        player.sendMessage("Para alcanzar el rango " + nextRank.toLowerCase() +
                " necesitas " + DineroManager.dineroToString(necesario, true) +
                ", te queda " + DineroManager.dineroToString(dineroRestante, true));
    }

    public void mostrar(Player p) {
        for (String player: playerManager.getPlayers()) {
            p.sendMessage(player + " " +  playerManager.getRango(player));
        }
    }

    public void listar(Player p) {
        StringBuilder s = new StringBuilder();
        for (Rangos r:Rangos.values()) {
            s.append(r.toString());
            if (r == getPlayer(p)) break;
            s.append(", ");
        }
        p.sendMessage(s.toString());
    }
}
