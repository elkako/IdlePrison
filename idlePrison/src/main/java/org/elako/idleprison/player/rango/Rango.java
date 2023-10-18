package org.elako.idleprison.player.rango;

import java.util.List;

public class Rango {
    private Rangos rango;
    private Rangos Siguiente;
    private double dineroSiguiente;
    private List<String> desbloquearExtra;

    public Rango(Rangos rango, Rangos siguiente, double dineroSiguiente, List<String> desbloquearExtra) {
        this.rango = rango;
        Siguiente = siguiente;
        this.dineroSiguiente = dineroSiguiente;
        this.desbloquearExtra = desbloquearExtra;
    }
}
