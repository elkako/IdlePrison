package org.elako.idleprison.player;

import org.bukkit.entity.Player;
import org.elako.idleprison.IdlePrison;

import java.util.*;

public class RangosManager {
    PlayerManager playerManager;
    HashMap<Rangos, Double> dineroAscender = new HashMap<>();
    DineroManager dineroManager;

    public RangosManager(DineroManager dinero, PlayerManager player) {
        playerManager = player;
        dineroManager = dinero;
        dineroAscender.put(Rangos.NUEVO, 9999999999.0);
        dineroAscender.put(Rangos.CONDENADO4, 100.0);
        dineroAscender.put(Rangos.CONDENADO3, 400.0);
        dineroAscender.put(Rangos.CONDENADO2, 1000.0);
        dineroAscender.put(Rangos.CONDENADO1, 5000.0);

        dineroAscender.put(Rangos.SINTECHO2, 10000.0);
        dineroAscender.put(Rangos.SINTECHO1, 15000.0);
        dineroAscender.put(Rangos.CAMPESINO3, 17000.0);
        dineroAscender.put(Rangos.CAMPESINO2, 30000.0);
        dineroAscender.put(Rangos.CAMPESINO1, 50000.0);
        dineroAscender.put(Rangos.MINERO3, 70000.0);
        dineroAscender.put(Rangos.MINERO2, 100000.0);
        dineroAscender.put(Rangos.MINERO1, 9999999999.0);

    }

    public double getDineroAscender(Rangos rango, String player) {
        double rebaja = TreeSkillManager.getPrecioAscender(player);
        return dineroAscender.get(rango)-dineroAscender.get(rango)*(rebaja/100) ;
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

        double coste = dineroAscender.get(getPlayer(jugador));
        coste = coste - coste*(rebaja/100);
        if(dinero >= coste){
            dineroManager.removeMoney(sjugador,coste);
            setPlayer(sjugador, siguienteRango(sjugador));
            IdlePrison.scoreboardNotification(jugador,mensajeUnlock(jugador,getPlayer(jugador).toString().toUpperCase(), coste),10);
        }else{
            mensajeInsuficiente(jugador,coste-dinero,coste ,siguienteRango(sjugador));
        }
    }

    private LinkedList<String> mensajeUnlock(Player jugador, String rango, double coste) {
        LinkedList<String> s = new LinkedList<>();
        jugador.sendMessage("Ahora eres rango " + rango.toLowerCase() + ", has perdido " +  DineroManager.dineroToString(coste) + " y tu dinero se a quedado en " + DineroManager.dineroToString(dineroManager.getDinero(jugador)));
        switch (Rangos.valueOf(rango)){
            case CONDENADO3:
                s.add("[Minas: has desbloqueado infierno2]");
                s.add("[Idle: has desbloqueado Minero experimentado]");
                s.add("[CraftGuide: has desbloqueado Pico humilde]");
                break;
            case CONDENADO2:
                s.add("[Minas: has desbloqueado infierno3]");
                s.add("[CraftGuide: has desbloqueado Esencia destructiva 1]");
                s.add("[CraftGuide: has desbloqueado Pico destructivo 1]");
                s.add("[Idle: has desbloqueado Pescado a la brasa]");
                break;
            case CONDENADO1:
                s.add("[Minas: has desbloqueado infierno4]");
                s.add("[Idle: has desbloqueado Yacimiento de calcita]");
                break;
            case SINTECHO2:
                s.add("[Minas: has desbloqueado afueras1]");
                s.add("[CraftGuide: has desbloqueado Tablones de roble]");
                s.add("[CraftGuide: has desbloqueado Pico resistente]");
                s.add("[CraftGuide: has desbloqueado Lente convergente]");
                break;
            case SINTECHO1:
                s.add("[Minas: has desbloqueado afueras2]");
                s.add("[Idle: has desbloqueado Mina de carbon]");
                s.add("[CraftGuide: has desbloqueado Gran granito]");
                s.add("[CraftGuide: has desbloqueado Esencia de vida 1]");
                s.add("[CraftGuide: has desbloqueado Pico vivo 1]");
                break;
            case CAMPESINO3:
                s.add("[Minas: has desbloqueado Extra1]");
                s.add("[CraftGuide: has desbloqueado Esencia capitalista 1]");
                s.add("[CraftGuide: has desbloqueado Pico capitalista 1]");
                s.add("[CraftGuide: has desbloqueado Esencia destructiva 2]");
                s.add("[CraftGuide: has desbloqueado Pico destructivo 2]");
                break;
            case CAMPESINO2:
                s.add("[Minas: has desbloqueado afueras3]");
                s.add("[Idle: has desbloqueado Templo de hierro]");
                s.add("[CraftGuide: has desbloqueado Pan]");
                s.add("[CraftGuide: has desbloqueado Catalejo]");
                break;
            case CAMPESINO1:
                s.add("[Minas: has desbloqueado afueras4]");
                s.add("[CraftGuide: has desbloqueado Esencia de sabiduría 1]");
                s.add("[CraftGuide: has desbloqueado Detector de bloques 1]");
                break;
            case MINERO3:
                s.add("[Minas: has desbloqueado poblado1]");
                break;
            case MINERO2:
                s.add("[Minas: has desbloqueado poblado2]");
                break;
            case MINERO1:
                s.add("[Minas: has desbloqueado extra2]");
                break;

        }

        for (String str: s) {
            jugador.sendMessage(str);
        }
        s.addFirst("-------------------------");
        s.addFirst( "Tu dinero se a quedado en " + DineroManager.dineroToString(dineroManager.getDinero(jugador)));
        s.addFirst("Has perdido " +  DineroManager.dineroToString(coste) );
        s.addFirst("Ahora eres rango " + rango.toLowerCase());

        return s;
    }


    private void mensajeInsuficiente(Player player,double dineroRestante, double necesario, String nextRank) {
        player.sendMessage("Para alcanzar el rango " + nextRank.toLowerCase() + " necesitas " + DineroManager.dineroToString(necesario) + ", te queda " + DineroManager.dineroToString(dineroRestante));
        player.sendMessage();
    }

    public void mostrar(Player p) {
        for (String player: playerManager.getPlayers()) {
            p.sendMessage(player + " " +  playerManager.getRango(player));
            //p.sendMessage(player + playerManager.getPlayer(player).getDinero());
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
