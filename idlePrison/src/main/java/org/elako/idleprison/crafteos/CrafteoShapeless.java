package org.elako.idleprison.crafteos;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.player.Rangos;

import java.util.LinkedList;
import java.util.List;

public class CrafteoShapeless extends Crafteo {

    public CrafteoShapeless(LinkedList<ItemStack> receta, ItemStack resultado, Rangos permiso) {
        super(receta, resultado, permiso);
    }

    @Override
    public ItemStack getIcono() { return getResultado(); }

    @Override
    public List<Recipe> getCrafteo() {
        ShapelessRecipe receta = new ShapelessRecipe(getResultado());
        String s= "";

        for (ItemStack item : getReceta()) {
          receta.addIngredient(item.getType());
          s += item.getType() + ", ";
        }
        s = s.substring(0,s.length()-2);
        IdlePrison.imprimirConsola("Crafteo creado:[" + s + "]");

        return List.of( receta );
    }

    @Override
    public Inventory getGuide(Player p) {
        LinkedList<ItemStack> lista = getReceta();
        LinkedList<ItemStack> guia = getReceta();
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
}
