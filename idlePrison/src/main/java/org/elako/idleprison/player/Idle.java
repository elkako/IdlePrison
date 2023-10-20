package org.elako.idleprison.player;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.player.rango.Rangos;

import java.util.LinkedList;

public class Idle {
    private final String nombre;
    private final Material iconoIdle;
    private final int precioTotal;
    private final int precioPorNivel;
    private final int dineroCantidad;
    private final int dineroTiempo;
    private final Rangos permiso;
    private final Materiales1 materiales1;
    private final Materiales10 materiales10;

    public Idle(String nombre, Material iconoIdle, int precioTotal, int precioPorNivel, int dineroCantidad,
                int dineroTiempo, Rangos permiso, Materiales1 materiales1, Materiales10 materiales10) {
        this.nombre = nombre;
        this.iconoIdle = iconoIdle;
        this.precioTotal = precioTotal;
        this.precioPorNivel = precioPorNivel;
        this.dineroCantidad = dineroCantidad;
        this.dineroTiempo = dineroTiempo;
        this.permiso = permiso;
        this.materiales1 = materiales1;
        this.materiales10 = materiales10;
    }

    public interface Materiales1{
        LinkedList<ItemStack> materiales1(int nivel);
    }

    public interface Materiales10{
        LinkedList<ItemStack> materiales10(int nivel);
    }


    public LinkedList<ItemStack> getMateriales(int nivel){
        if (nivel%10 == 0) return materiales10.materiales10(nivel);
        else return materiales1.materiales1(nivel);
    }

    public String getNombre() {
        return nombre;
    }

    public Material getIconoIdle() {
        return iconoIdle;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public int getPrecioPorNivel() {
        return precioPorNivel;
    }

    public int getDineroCantidad() {
        return dineroCantidad;
    }

    public int getDineroTiempo() {
        return dineroTiempo;
    }

    public Rangos getPermiso() {
        return permiso;
    }
}
