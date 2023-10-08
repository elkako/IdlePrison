package org.elako.idleprison.mina;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.player.Rangos;

import java.util.HashMap;
import java.util.LinkedList;

public class IpMina {
    private final HashMap<IpMateriales,Integer> materiales;
    private final Rangos permiso;
    private final Material icono;
    private final int RATE ;
    private final LinkedList<Block> bloques = new LinkedList<>();
    private Location maxBloque;
    private Location minBloque;
    private int ticksLeft;
    private final Location spawn;
    private final String id;

    public int generarNumeros(int MAX, int MIN) {
        return (int) (Math.floor(Math.random() * (MAX - MIN + 1)) + MIN);
    }

    public IpMina(String id, Material icono, Rangos permiso, int rate, Location spawn, HashMap<IpMateriales,Integer> materiales) {
        this.id =id ;
        this.icono = icono;
        this.permiso = permiso;
        this.RATE = rate*5;
        ticksLeft = generarNumeros(100,1)*5;
        this.materiales = materiales;
        this.spawn = spawn;
    }

    public void setBloques(LinkedList<Block> bloques) {
        this.bloques.clear();
        this.bloques.addAll(bloques);
    }
    public void setBloquesLimite(Location b1, Location b2){
        maxBloque = b1;
        minBloque = b2;
    }

    public LinkedList<Block> getBloques() { return bloques; }


    public HashMap<IpMateriales, Integer> getMateriales() {return materiales;}

    public boolean tick(){
        ticksLeft--;  //reducir ticks
        if (ticksLeft <= 0){
            ticksLeft = RATE;
            IdlePrison.broadcast( "Reiniciando " + getName());
            return true;
        }
        return false;
    }

    public Location getSpawn() { return spawn; }
    public Rangos getPermiso() { return permiso; }
    public String getId() { return id; }
    public Material getIcono() { return icono; }

    public Location getMaxBloque() { return maxBloque; }

    public Location getMinBloque() { return minBloque; }

    public int getExtra(){
        IdlePrison.imprimirConsola(id + "[" + id.charAt(id.length()-1));
        if(id.contains("extra")) return Integer.parseInt(String.valueOf(id.charAt(id.length()-1)));
        return 0;
    }

    public String getName(){
         if (id.contains("extra")) return ChatColor.GRAY + id;
        if (id.contains("afueras")) return ChatColor.GOLD +id;
        return ChatColor.RED+ id ; // infierno
    }

}
