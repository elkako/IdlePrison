package org.elako.idleprison.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.items.notas.NotaManager;
import org.elako.idleprison.items.notas.TipoNota;
import org.elako.idleprison.player.rango.Rangos;

import java.util.LinkedList;
import java.util.Objects;

public class PlayerManager {
    LinkedList<Jugador> jugadores = new LinkedList<>();

    public static boolean inventarioLleno(Player jugador) {
        for (ItemStack item : jugador.getInventory().getContents()) {
            if (item == null || item.getType() == Material.AIR) {
                // Espacio vacío en el inventario
                return false;
            }
        }
        return true; // Si no se encontraron espacios vacíos, el inventario está lleno
    }

    public Jugador getPlayer(String player){
        //IdlePrison.getPlugin().getServer().getConsoleSender().sendMessage("[PPPP]"); // pruebas
        for (Jugador p: jugadores) {
            //IdlePrison.getPlugin().getServer().getConsoleSender().sendMessage(p + "[JJJJJ]"); // pruebas
            if(p.getJugador().equals(player)) return p;
        }
        return null;
    }

    public double getDinero(String player){ return getPlayer(player).getDinero(); }
    public void setDinero(String player, double dinero){
        IdlePrison.getPlugin().escribirDinero(player, dinero);
        getPlayer(player).setDinero(dinero);
    }

    public Rangos getRango(String player){ return getPlayer(player).getRango(); }
    public void setRango(String player, String rango){
        Rangos rangos = Rangos.valueOf(rango.toUpperCase());
        int nota1 = NotaManager.getNum(TipoNota.RANGO, 1);
        int nota2 = NotaManager.getNum(TipoNota.RANGO, 2);
        if(isNotNota(player, nota1))
            if(rangos.equals(Rangos.CONDENADO3)) NotaManager.getNota(getPlayer(player).getPlayer(),nota1);
        if(isNotNota(player, nota2))
            if(rangos.equals(Rangos.CAMPESINO1)) NotaManager.getNota(getPlayer(player).getPlayer(),nota2);
        // añadir nota salir infierno
        IdlePrison.getPlugin().escribirRango(player, rango);
        getPlayer(player).setRango(rangos);
    }

    public boolean isNotNota(String player, int i){ return getPlayer(player).isNotNota(i); }
    public int getNotas(String player){ return getPlayer(player).getNotas(); }
    public void setNotas(String player, int nota){ getPlayer(player).setNotas(nota); }
    public boolean isNotaRecibidas(String player, int i){ return getPlayer(player).isNotaRecibida(i); }
    public void setNotasRecibidas(String player, int nota){ getPlayer(player).setNotasRecibidas(nota); }

    public int getItemsVendidos(String player){ return getPlayer(player).getItemsVendidos(); }
    public void setItemsVendidos(String player, int cant){
        IdlePrison.getPlugin().escribirItemsVendidos(player,cant);
        getPlayer(player).setItemsVendidos(cant);
    }

    public int getBloquesRotos(String player){ return getPlayer(player).getBloquesRotos(); }
    public void setBloquesRotos(String player, int n){
        IdlePrison.getPlugin().escribirBloquesRotos(player,n);
        getPlayer(player).setBloquesRotos(n);
    }

    public int getTimeScore(String player){ return getPlayer(player).getTimeScore(); }
    public void reloadTimeScoreDetector(String player, int segs){ getPlayer(player).reloadTimeScoreboard(segs); }
    public void reduceTimeScore(String player){  getPlayer(player).reduceTimeScore(); }

    // funciones para idle START
    public int getTimeOffline(String player){ return getPlayer(player).getTimeOffline(); }
    public void reloadTimeOffline(String player){ getPlayer(player).reloadTimeOffline(); }
    public void reduceTimeOffline(String player){  getPlayer(player).reduceTimeOffline(); }

    public int getIdle(int i, String player){
        return getPlayer(player).getIdle(i);
    }

    public void setIdle(int i, String player, int cantidad){
        getPlayer(player).setIdle(i,cantidad);
        IdlePrison.getPlugin().escribirIdle(player, cantidad, String.valueOf(i));
    }

    public double recogerDineroAcum(String player){ return getPlayer(player).recogerDineroAcum(); }
    public double getDineroAcum(String player){ return getPlayer(player).getDineroAcum(); }

    public void addDineroAcum(String player, double cantidad, boolean online){
        if(cantidad != 0) {
            if(online) Objects.requireNonNull(IdlePrison.getPlugin().getServer().getPlayer(player)).sendMessage("dineroAcum == " + getPlayer(player).addDineroAcum(cantidad) + " [" + cantidad);
            else getPlayer(player).addDineroAcum(cantidad);
        }
    }
    // funciones para idle END
    // funciones para treeskill START
    public int getTreeSkill(int i, String player){
        return getPlayer(player).getTreeSkill(i);
    }

    public void setTreeSkill(int i, String player, int cantidad){
        IdlePrison.getPlugin().escribirTreeSkill(player, cantidad, String.valueOf(i));
        getPlayer(player).setTreeSkill(i,cantidad);
    }

    public double getDineroRenacer(String player){ return getPlayer(player).getDineroRenacer(); }
    public double getDineroRun(String player){
        return getPlayer(player).getDineroRun();
    }

    public void setDineroRenacer(String player, double dinero){
        IdlePrison.getPlugin().escribirDineroRenacer(player, dinero);
        getPlayer(player).setDineroRenacer(dinero);
    }

    public void setDineroRun(String player, double dinero){
        IdlePrison.getPlugin().escribirDineroRun(player, dinero);
        getPlayer(player).setDineroRun(dinero);
    }

    // funciones para treeskill END


    public LinkedList<String> getPlayers(){
        LinkedList<String> devolver = new LinkedList<>();
        for (Jugador p: jugadores) {
            devolver.add(p.getJugador());
        }
        return devolver;
    }

    public boolean addNewJugador(String p){
        if(getPlayers().contains(p)){
            IdlePrison.getPlugin().getServer().getConsoleSender().sendMessage(p + "[Personaje NO creadoew ]");
            return false;
        }
        Jugador jugador = new Jugador(p,0.0,0.0,0.0, Rangos.NUEVO, 0 ,0 ,0 ,
                0,0 ,0 ,0, 0 , 0, "00",
                "000000000000000000000000000000000000000000000000000000000000",
                "000000000000000000000000000000000000000000000000000000000000",
                0,0);
        IdlePrison.getPlugin().getServer().getConsoleSender().sendMessage(p + "[Personaje creado ew]");

        jugadores.add(jugador);
        return true;
    }

    public void addJugador(String p, double dinero, double dineroRenacer, double dineroRun , Rangos rango,
                           int idle1, int idle2, int idle3, int idle4, int idle5, int idle6, int treeskill1, int treeskill2,
                           int treeskill3, String permisos, String notas, String notasRecibidas, int itemsVendidos, int bloquesRotos){

        if (getPlayers().contains(p)){
            return;
        }

        Jugador jugador = new Jugador(p, dinero, dineroRenacer, dineroRun, rango, idle1, idle2, idle3, idle4, idle5,
                idle6, treeskill1, treeskill2, treeskill3, permisos, notas, notasRecibidas, itemsVendidos, bloquesRotos);
        IdlePrison.getPlugin().getServer().getConsoleSender().sendMessage(p + "[Personaje cargado]");
        jugadores.add(jugador);
    }

    public void diferenciaDinero(String jugador, double money) {
        getPlayer(jugador).diferenciaDinero(money);
    }
    public double getDiferenciaDinero(String jugador) {return getPlayer(jugador).getDiferenciaDinero(); }
    public void reiniciarDiferenciaDinero(String jugador) { getPlayer(jugador).reiniciarDiferenciaDinero(); }

    public boolean isPermisoConstructor(String player){ return getPlayer(player).isPermisoConstructor(); }
    public boolean isPermisoComandos(String player){ return getPlayer(player).isPermisoComandos(); }
    public void setPermiso(String player, String permiso){
        IdlePrison.getPlugin().escribirPermisos(player, permiso);
        getPlayer(player).setPermisos(permiso);
    }
}
