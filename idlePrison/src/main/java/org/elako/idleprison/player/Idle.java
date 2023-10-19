package org.elako.idleprison.player;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.player.rango.Rangos;

import java.util.LinkedList;

public class Idle {
    private String nombre;
    private Material iconoIdle;
    private int precioTotal;
    private int precioPorNivel;
    private int dineroCantidad;
    private int dineroTiempo;
    private Rangos permiso;
    private Materiales materiales;

    public Idle(String nombre, Material iconoIdle, int precioTotal, int precioPorNivel, int dineroCantidad,
                int dineroTiempo, Rangos permiso, Materiales materiales) {
        this.nombre = nombre;
        this.iconoIdle = iconoIdle;
        this.precioTotal = precioTotal;
        this.precioPorNivel = precioPorNivel;
        this.dineroCantidad = dineroCantidad;
        this.dineroTiempo = dineroTiempo;
        this.permiso = permiso;
        this.materiales = materiales;
    }

    public interface Materiales{
        LinkedList<ItemStack> materiales10(int nivel);
        LinkedList<ItemStack> materiales1(int nivel);
    }

    public LinkedList<ItemStack> getMateriales(int nivel){
        if (nivel%10 == 0) return materiales.materiales10(nivel);
        else return materiales.materiales1(nivel);
    }
    
}
