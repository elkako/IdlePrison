package org.elako.idleprison.mina;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RompiendoBloque {
    Player player;
    long tiempo;
    boolean activo;
    Block block;

    //https://www.spigotmc.org/threads/1-8-1-13-custom-block-breaking-change-block-hardness.362586/
    public RompiendoBloque(Player player, Block block){
        tiempo = System.currentTimeMillis() / 1000L;
        this.player = player;
        activo = true;
        this.block = block;
    }

    public boolean isActivo(){
        return activo;
    }

    public void terminarRompiendoBloque(){
        activo = false;
        player.sendMessage("CANCEL");
    }

}
