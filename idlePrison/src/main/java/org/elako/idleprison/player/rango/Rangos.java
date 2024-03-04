package org.elako.idleprison.player.rango;

import java.util.LinkedList;

public enum Rangos{
    NUEVO(9999999999.0), CONDENADO4(100.0), CONDENADO3(400.0),
    CONDENADO2(1000.0), CONDENADO1(5000.0), SINTECHO2(10000.0),
    SINTECHO1(15000.0), CAMPESINO3(17000.0), CAMPESINO2(30000.0),
    CAMPESINO1(50000.0), MINERO3(70000.0), MINERO2(100000.0),
    MINERO1(9999999999.0);

    private double dineroSiguiente;
    private LinkedList<String> desbloquearExtra= new LinkedList<>();

    Rangos(double dineroSiguiente) {
        this.dineroSiguiente = dineroSiguiente;
    }
    public double getDineroSiguiente() { return dineroSiguiente; }

    public LinkedList<String> getDesbloquearExtra() { return new LinkedList<>(desbloquearExtra); }

    public void addDesbloqueo(String cadena){ desbloquearExtra.add(cadena); }

    public static boolean contieneNotRango(String rango){
        for (Rangos r: Rangos.values()) {
            if(r.toString().equals(rango.toUpperCase())) return false;
        }
        return true;
    }
}
