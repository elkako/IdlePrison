package org.elako.idleprison.crafteos;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.elako.idleprison.player.rango.Rangos;

import java.util.LinkedList;

public class CrafteoShapeless extends Crafteo {

    public CrafteoShapeless(LinkedList<ItemStack> receta, ItemStack resultado, Rangos permiso) {
        super(receta, resultado, permiso);
    }

    @Override
    public ItemStack getIcono() { return getResultado(); }

    @Override
    public int isCrafteo(LinkedList<ItemStack> items) {
        int itemsMatch = 0;
        LinkedList<ItemStack> receta = new LinkedList<>(getReceta());

        for (ItemStack item : items) {
            if(item.getType().equals(Material.BARRIER)) continue;
            boolean existe = false;
            for (ItemStack rece : receta) {
                if(item.getType().equals(rece.getType())){
                    receta.remove(rece);
                    existe = true;
                    itemsMatch++;
                }
            }
            if(!existe) return 0;
        }
        if(itemsMatch >= getReceta().size()) return 1;
        return 0;
    }

    @Override
    public Inventory getGuide(Player p) {
        LinkedList<ItemStack> lista = getReceta();
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
        p.sendMessage("GUIASIZE" + lista.size());
        return guiaCrafteoSimple(p, new LinkedList<>(guia), new ItemStack(getResultado()));
    }
}
