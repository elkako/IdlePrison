package org.elako.idleprison.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.items.materiales.IpMaterial;
import org.elako.idleprison.items.materiales.IpMateriales;
import org.elako.idleprison.player.rango.Rangos;

import java.util.List;

import static org.bukkit.ChatColor.*;
import static org.bukkit.ChatColor.WHITE;

public class IpComida extends IpMaterial {
    private final double vida;
    private final int vidaAmarilla;


    public IpComida(Material material, String nombre, List<String> lore, double precio, Rangos permiso,
                    IpMateriales ipMateriales, double vida, int vidaAmarilla) {
        super(material, nombre, lore, precio, permiso, ipMateriales);
        this.vida = vida;
        if (vidaAmarilla%2!= 0) IdlePrison.imprimirConsola(nombre + " vida amarila: " + vidaAmarilla+ " debe ser múltiplo de 2");
        this.vidaAmarilla = vidaAmarilla;
    }

    private void curar(Player p) {
        if(vida > 0){
            int vidaRoja = (int) Math.floor(vida*2);
            if(p.getHealth()+vidaRoja<p.getHealthScale()) p.setHealth(p.getHealth()+vidaRoja);
            else if(p.getHealth()<p.getHealthScale()) p.setHealth(p.getHealthScale());//cantidad = medio corazon
        }
    }

    private void curarAmarillo(Player p){
        if(vidaAmarilla > 0) p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,900,(vidaAmarilla/2)-1 )); // 0 = 2 corazones amarillos
    }

    public void comido(Player p){
        curarAmarillo(p);
        curar(p);
    }

    @Override
    public ItemStack getItem(int cantidad){
        ItemStack item = super.getItem(cantidad);
        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + getNombre());


        List<String> lore = itemMeta.getLore() ;
        assert lore != null;

        if (vida == -1){
            lore.add(lore.size()-1, ChatColor.WHITE + "Al comer: sienta "+ ChatColor.DARK_GREEN +"mal");
        } else {
            if(vida>0 & vidaAmarilla>0)lore.add(lore.size()-1, WHITE + "Al comer: "+ RED + "+" + vida +
                    WHITE +" vida y"+ YELLOW +" +" + vidaAmarilla + WHITE +" vida amarilla");
            else if(vida > 0) lore.add(lore.size()-1, WHITE + "Al comer: "+ RED + "+" + vida + WHITE +" vida");
            else if(vidaAmarilla > 0) lore.add(lore.size()-1,
                    WHITE + "Al comer: "+ RED + "+" + vidaAmarilla + WHITE +" vida amarilla");
        }

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
}
