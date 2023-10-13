package org.elako.idleprison.player;

import org.bukkit.entity.Player;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.items.notas.NotaManager;
import org.elako.idleprison.items.notas.TipoNota;

import java.util.LinkedList;

public class Jugador {
    private final String jugador;
    private String permisos; //00 = nada, 01 = construir, 10 = comandos, 11 ambos
    private double dinero;
    private double diferenciaDinero=0;
    private Rangos rango;
    private final int rateOFFLINE = 120; //10 mins
    private int timeOffline = rateOFFLINE;
    private int duracionScoreboard = 0;
    private final LinkedList<Integer> idle = new LinkedList<>();
    private double dineroRenacer;
    private double dineroRun;
    private int rama1;
    private int rama2;
    private int rama3;
    private double dineroAcumulado = 0;
    private String notas;
    private String notasRecibidas;
    private int itemsVendidos;
    private int bloquesRotos;

    public Jugador(String jugador, double dinero, double dineroRenacer, double dineroRun, Rangos rango, int idle1,
               int idle2, int idle3, int idle4, int idle5, int idle6, int treeskill1, int treeskill2, int treeskill3,
               String permisos , String notas, String notasRecibidas, int itemsVendidos, int bloquesRotos) {
        this.jugador = jugador;
        this.dinero = dinero;
        this.dineroRun = dineroRun;
        this.dineroRenacer = dineroRenacer;
        this.rango = rango;
        idle.add(idle1);
        idle.add(idle2);
        idle.add(idle3);
        idle.add(idle4);
        idle.add(idle5);
        idle.add(idle6);
        rama1 = treeskill1;
        rama2 = treeskill2;
        rama3 = treeskill3;
        this.permisos = permisos;
        this.notas = notas;
        this.notasRecibidas = notasRecibidas;
        this.itemsVendidos = itemsVendidos;
        this.bloquesRotos = bloquesRotos;

    }

    public String getJugador() { return jugador; }
    public double getDinero() { return dinero; }
    public Rangos getRango() { return rango; }

    public int getTimeTotal(){ return (rateOFFLINE-timeOffline)*5; }
    public int getTimeOffline() { return timeOffline; }
    public void reduceTimeOffline() { timeOffline = timeOffline-1; }
    public void reloadTimeOffline() { timeOffline = rateOFFLINE; }

    public int getTimeScore() { return duracionScoreboard; }
    public void reduceTimeScore() { duracionScoreboard = duracionScoreboard-1; }
    public void reloadTimeScoreboard(int segs) { duracionScoreboard = segs; }

    public int getIdle(int i){
        return idle.get(i-1);
    }

    public double getDineroRun() { return dineroRun; }
    public double getDineroRenacer() { return dineroRenacer; }

    public int getTreeSkill(int i){
        switch (i){
            case 1:
                return rama1;
            case 2:
                return rama2;
            case 3:
                return rama3;
            default:
                return 0;
        }
    }

    public int getNotas() {
        int contador = 0;
        for (char c:notas.toCharArray()) {
            if(c == '1') contador++;
        }
        return contador;
    }

    public int getItemsVendidos() { return itemsVendidos; }
    public int getBloquesRotos() { return bloquesRotos; }

    public boolean isPermisoConstructor(){ return permisos.charAt(0) == '1' ;}
    public boolean isPermisoComandos(){ return permisos.charAt(1) == '1' ;}

    public boolean isNotNota(int i) { return notas.charAt(i - 1) != '1'; }
    public boolean isNotaRecibida(int i) { return notasRecibidas.charAt(i-1) == '1'; }

    public double addDineroAcum(double cantidad) {
        dineroAcumulado += cantidad;
        return dineroAcumulado;
    }
    public double recogerDineroAcum() {
        double money = dineroAcumulado;
        dineroAcumulado = 0;
        return money;
    }
    public double getDineroAcum() { return dineroAcumulado; }

    public void setDinero(double dinero) { this.dinero = dinero; }
    public void setRango(Rangos rango) {
        this.rango = rango;
    }
    public void setPermisos(String permiso) { this.permisos = permiso;}

    public Player getPlayer(){
        return IdlePrison.getPlugin().getServer().getPlayer(jugador);
    }

    public void actualizarNotasIdle(){
        int total = 0;
        for (int i:idle) {
            total += i;
        }

        int nota1 = NotaManager.getNum(TipoNota.IDLE, 1);
        int nota2 = NotaManager.getNum(TipoNota.IDLE, 2);
        if(isNotNota(nota1))
            if(total >= 1) NotaManager.getNota(getPlayer(),nota1);
        if(isNotNota(nota2))
            if(total >= 10) NotaManager.getNota(getPlayer(),nota2);

    }

    public void setIdle(int i, int cantidad){
        actualizarNotasIdle();
        idle.set(i-1, cantidad);
    }

    public void setDineroRenacer(double dineroRenacer) {
        notasRecibidas = notas; // al renacer puedo poner que las notas recibidas son las reclamadas ya que si no ha sido reclamada se pierde
        this.dineroRenacer = dineroRenacer;
    }
    public void setDineroRun(double dineroRun) {
        int nota1 = NotaManager.getNum(TipoNota.DINERO, 1);
        int nota2 = NotaManager.getNum(TipoNota.DINERO, 2);
        int nota3 = NotaManager.getNum(TipoNota.DINERO, 3);
        if(isNotNota(nota1))
            if(dinero >= 100) NotaManager.getNota(getPlayer(),nota1);
        if(isNotNota(nota2))
            if(dinero >= 1000) NotaManager.getNota(getPlayer(),nota2);
        if(isNotNota(nota3))
            if(dinero >= 10000) NotaManager.getNota(getPlayer(),nota3);

        this.dineroRun = dineroRun;
    }

    public void setTreeSkill(int i, int n){
        switch (i){
            case 1:
                rama1 = n;
                return;
            case 2:
                rama2 = n;
                return;
            case 3:
                rama3 = n;
        }
    }

    public void setNotas(int i) {
        char[] lista = notas.toCharArray();
        lista[i-1] = '1';
        StringBuilder provisional = new StringBuilder();
        for (char c:lista) provisional.append(c);
        notas = provisional.toString();
        IdlePrison.getPlugin().escribirNotas(jugador, notas);
    }

    public void setNotasRecibidas(int i) {
        char[] lista = notasRecibidas.toCharArray();
        lista[i-1] = '1';
        StringBuilder provisional = new StringBuilder();
        for (char c:lista) provisional.append(c);
        notasRecibidas = provisional.toString();
        IdlePrison.getPlugin().escribirNotasRecibidas(jugador, notasRecibidas);
    }

    public void setItemsVendidos(int cant) {
        int nota1 = NotaManager.getNum(TipoNota.VENDER, 1);
        int nota2 = NotaManager.getNum(TipoNota.VENDER, 2);
        if(isNotNota(nota1))
            if(cant >= 70) NotaManager.getNota(getPlayer(),nota1);
        if(isNotNota(nota2))
            if(cant >= 700) NotaManager.getNota(getPlayer(),nota1);

        itemsVendidos = cant;
    }
    public void setBloquesRotos(int n) {
        int nota1 = NotaManager.getNum(TipoNota.MINERO, 1);
        int nota2 = NotaManager.getNum(TipoNota.MINERO, 2);
        if(isNotNota(nota1))
            if(n >= 30) NotaManager.getNota(getPlayer(),nota1);
        if(isNotNota(nota2))
            if(n >= 300) NotaManager.getNota(getPlayer(),nota2);

        bloquesRotos = n;
    }

    public void diferenciaDinero(double money) {
        diferenciaDinero += money;
    }
    public double getDiferenciaDinero() {return diferenciaDinero;}
    public void reiniciarDiferenciaDinero() { diferenciaDinero = 0;}
}
