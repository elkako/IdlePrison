package org.elako.idleprison.player;

import org.elako.idleprison.IdlePrison;

import java.util.Arrays;

public class Jugador {
    private String jugador;
    private String permisos; //00 = nada, 01 = construir, 10 = comandos, 11 ambos
    private double dinero;
    private double diferenciaDinero=0;
    private Rangos rango;
    private final int rateOFFLINE = 120; //10 mins
    private int timeOffline = rateOFFLINE;
    private int duracionScoreboard = 0;
    private int idle1;// niño minerow
    private int idle2; // minero experto
    private int idle3; // yacimiento de calcita
    private int idle4; // mina de carbon
    private int idle5; // mina de carbon
    private int idle6; // mina de carbon

    private double dineroRenacer;
    private double dineroRun;
    private int rama1;
    private int rama2;
    private int rama3;
    private double dineroAcumulado = 0;
    private String notas;
    private String notasRecibidas;
    private String recompensas;
    private int itemsVendidos;
    private int bloquesRotos;

    public Jugador(String jugador, double dinero, double dineroRenacer, double dineroRun, Rangos rango, int idle1,
               int idle2, int idle3, int idle4, int idle5, int idle6, int treeskill1, int treeskill2, int treeskill3,
               String permisos , String notas, String notasRecibidas, String recompensas, int itemsVendidos, int bloquesRotos) {
        this.jugador = jugador;
        this.dinero = dinero;
        this.dineroRun = dineroRun;
        this.dineroRenacer = dineroRenacer;
        this.rango = rango;
        this.idle1 = idle1;
        this.idle2 = idle2;
        this.idle3 = idle3;
        this.idle4 = idle4;
        this.idle5 = idle5;
        this.idle6 = idle6;
        rama1 = treeskill1;
        rama2 = treeskill2;
        rama3 = treeskill3;
        this.permisos = permisos;
        this.notas = notas;
        this.notasRecibidas = notasRecibidas;
        this.recompensas = recompensas;
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
        switch (i){
            case 1:
                return idle1; // niño minero
            case 2:
                return idle2; // minero experto
            case 3:
                return idle3; // yacimiento de calcita
            case 4:
                return idle4; // cueva de granito
            case 5:
                return idle5; // piramide de arenisca
            case 6:
                return idle6; // mina de carbón
            default:
                return 0;
        }
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

    public boolean isNota(int i) { return notas.charAt(i-1) == '1'; }
    public boolean isNotaRecibida(int i) { return notasRecibidas.charAt(i-1) == '1'; }
    public boolean isRecompensa(int i) { return recompensas.charAt(i-1) == '1'; }
    
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
    public void setRango(Rangos rango) { this.rango = rango; }
    public void setPermisos(String permiso) { this.permisos = permiso;}

    public void setIdle(int i, int cantidad){
        switch (i){
            case 1:
                idle1 = cantidad; // niño minero
                return;
            case 2:
                idle2 = cantidad; // minero experto
                return;
            case 3:
                idle3 = cantidad; // yacimiento de calcita
                return;
            case 4:
                idle4 = cantidad; // cueva de granito
                return;
            case 5:
                idle5 = cantidad; // piramide de arenisca
                return;
            case 6:
                idle6 = cantidad; // mina de carbón
                return;
        }
    }

    public void setDineroRenacer(double dineroRenacer) { this.dineroRenacer = dineroRenacer; }
    public void setDineroRun(double dineroRun) { this.dineroRun = dineroRun; }

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
        String provisional = "";
        for (char c:lista) provisional += c;
        notas = provisional;
        IdlePrison.getPlugin().escribirNotas(jugador, notas);
    }

    public void setNotasRecibidas(int i) {
        char[] lista = notasRecibidas.toCharArray();
        lista[i-1] = '1';
        String provisional = "";
        for (char c:lista) provisional += c;
        notasRecibidas = provisional;
        IdlePrison.getPlugin().escribirNotasRecibidas(jugador, notasRecibidas);
    }

    public void setRecompensas(int i) {
        char[] lista = recompensas.toCharArray();
        lista[i-1] = '1';
        String provisional = "";
        for (char c:lista) provisional += c;
        recompensas = provisional;
        IdlePrison.getPlugin().escribirRecompensas(jugador, recompensas);
    }

    public void setItemsVendidos(int itemsVendidos) { this.itemsVendidos = itemsVendidos; }
    public void setBloquesRotos(int bloquesRotos) { this.bloquesRotos = bloquesRotos; }

    public void diferenciaDinero(double money) {
        diferenciaDinero = money;
    }
    public double getDiferenciaDinero() {return diferenciaDinero;}
    public void reiniciarDiferenciaDinero() { diferenciaDinero = 0;}
}
