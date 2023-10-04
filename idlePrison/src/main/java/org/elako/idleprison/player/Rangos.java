package org.elako.idleprison.player;

public enum Rangos{
    NUEVO, CONDENADO4, CONDENADO3, CONDENADO2, CONDENADO1, SINTECHO2, SINTECHO1, CAMPESINO3, CAMPESINO2, CAMPESINO1,
    MINERO3, MINERO2, MINERO1;


    public static boolean contieneRango(String rango){
        for (Rangos r: Rangos.values()) {
            if(r.toString().equals(rango.toUpperCase())) return true;
        }
        return false;
    }
}
