package org.elako.idleprison.crafteos;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.comandos.IdleprisonCom;
import org.elako.idleprison.items.materiales.IpMateriales;
import org.elako.idleprison.items.materiales.MaterialesManager;
import org.elako.idleprison.player.rango.Rangos;

import java.util.LinkedList;
import java.util.Map;

import static org.bukkit.ChatColor.*;

public class CrafteoEncantar extends Crafteo {
    private final Enchantment encanti;
    private final int nivel;

    public CrafteoEncantar(LinkedList<ItemStack> receta, ItemStack resultado, Rangos permiso, Enchantment encanti, int nivel) {
        super(receta, resultado, permiso);
        this.encanti = encanti;
        this.nivel = nivel;
    }

    @Override
    public ItemStack getIcono() {
        ItemStack item = getResultado();
        ItemMeta meta = item.getItemMeta();

        assert meta != null;

        if(encanti.equals(Enchantment.DIG_SPEED)) meta.setDisplayName(WHITE + "Pico destructor ["+ nivel + "]");
        else if(encanti.equals(Enchantment.LOOT_BONUS_BLOCKS)) meta.setDisplayName(WHITE + "Pico vivo ["+ nivel + "]");
        else if(encanti.equals(Enchantment.LOOT_BONUS_MOBS)) meta.setDisplayName(WHITE + "Pico capitalista ["+ nivel + "]");
        else if(encanti.equals(Enchantment.MULTISHOT)) meta.setDisplayName(WHITE + "Pico oscuro ["+ nivel + "]");

        meta.addEnchant(encanti,nivel, true);

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public int isCrafteo(LinkedList<ItemStack> items){
        int devolver = 0;
        int index = 0;
        ItemStack esencia = getReceta().getFirst();

        for (ItemStack item : items) {
            if( item.getType().equals(Material.WOODEN_PICKAXE) || item.getType().equals(Material.STONE_PICKAXE)
            || item.getType().equals(Material.IRON_PICKAXE) || item.getType().equals(Material.DIAMOND_PICKAXE) ){
                devolver++;
                index = items.indexOf(item);
            }

            if( MaterialesManager.comparar(item, esencia) ) if(item.getAmount() > esencia.getAmount())
                devolver++;
        }

        if (devolver >= 2) return index;
        else return index;
    }

    @Override
    public Inventory getGuide(Player p) {
        LinkedList<ItemStack> lista = getReceta();
        ItemStack picoCualquiera = IdleprisonCom.crearObjeto(getResultado().getType(),
                DARK_AQUA + String.valueOf(BOLD) + "Pico cualquiera");
        lista.add(picoCualquiera);
        LinkedList<ItemStack> guia = new LinkedList<>();
        ItemStack vacio = new ItemStack(Material.BARRIER);
        switch (lista.size()){
            case 1:
                for (int i = 0; i < 4; i++) guia.add(vacio);
                guia.add(lista.pop());
                for (int i = 0; i < 4; i++) guia.add(vacio);
                break;
            case 2:
                guia.add(vacio);
                guia.add(lista.pop());
                for (int i = 0; i < 5; i++) guia.add(vacio);
                guia.add(lista.pop());
                guia.add(vacio);
                break;
            case 3:
                for (int i = 0; i < 3; i++) guia.add(vacio);
                for (int i = 0; i < 3; i++) guia.add(lista.pop());
                for (int i = 0; i < 3; i++) guia.add(vacio);
                break;
            case 9:
                for (int i = 0; i < 9; i++) guia.add(lista.pop());
                break;
        }
        return guiaCrafteoSimple(p, guia, getResultado());
    }

    public ItemStack encantar(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();

        if(itemMeta == null) return MaterialesManager.getItem(IpMateriales.PICO_MADERA);

        itemMeta.addEnchant(encanti,nivel,true);
        item.setItemMeta(itemMeta);
        return item;
    }

}
