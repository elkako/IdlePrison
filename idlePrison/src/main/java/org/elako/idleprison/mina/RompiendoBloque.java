package org.elako.idleprison.mina;

import org.bukkit.entity.Player;

public class RompiendoBloque {
    Player player;
    long tiempo;
    boolean activo;


    //https://www.spigotmc.org/threads/1-8-1-13-custom-block-breaking-change-block-hardness.362586/
    public RompiendoBloque(Player player){
        tiempo = System.currentTimeMillis() / 1000L;
        this.player = player;
        activo = true;
        estoyRompiendoBloque(1);
    }

    public void estoyRompiendoBloque(int i){
        /*while (activo) {
            long tiempoActual = System.currentTimeMillis() / 1000L;
            if (tiempo+i == tiempoActual) {
                player.sendMessage("HOLA ta:" + tiempoActual + " t:" + tiempo + " lleva:" +  i);
            }
        }

        estoyRompiendoBloque(i+1);*/
    }

    public void terminarRompiendoBloque(){
        activo = false;
        player.sendMessage("CANCEL");
    }

}
