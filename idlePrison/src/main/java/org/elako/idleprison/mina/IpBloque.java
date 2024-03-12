package org.elako.idleprison.mina;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class IpBloque {
    private final List<DropProb> dropsProb;
    private final List<DropCuanti> dropsCuanti;
    private final Material tipo;
    private final Morir morir;
    private final int vida;

    public interface Morir{
        void morir(Player p, Location pos);
    }

    public IpBloque(Material tipo, List<DropProb> dropsProb, List<DropCuanti> dropsCuanti, Morir morir) {
        this.tipo = tipo;
        this.dropsProb = dropsProb;
        this.dropsCuanti = dropsCuanti;
        this.morir = morir;
        this.vida = 0;
    }

    public IpBloque(Material tipo, List<DropProb> dropsProb, List<DropCuanti> dropsCuanti, Morir morir, int vida) {
        this.tipo = tipo;
        this.dropsProb = dropsProb;
        this.dropsCuanti = dropsCuanti;
        this.morir = morir;
        this.vida = vida;
    }

    public int getVida(){
        return vida;
    }

    public void roto(Player p, Location pos){
        morir.morir(p, pos);
    }

    public List<DropProb> getDropsProb() {
        return dropsProb;
    }

    public List<DropCuanti> getDropsCuanti() {
        return dropsCuanti;
    }

    public Material getTipo() {
        return tipo;
    }
}
