package org.elako.idleprison.crafteos;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.player.rango.Rangos;

import java.util.LinkedList;
import java.util.List;

public class CrafteoShapelessDoble extends Crafteo {
    private final LinkedList<ItemStack> receta2;
    private final ItemStack resultado2;

    public CrafteoShapelessDoble(LinkedList<ItemStack> receta, ItemStack resultado, Rangos permiso,
                                 LinkedList<ItemStack> receta2, ItemStack resultado2) {
        super(receta, resultado, permiso);
        this.receta2 = receta2;
        this.resultado2 = resultado2;
    }

    @Override
    public ItemStack getIcono() { return getResultado(); }

    public LinkedList<ItemStack> getReceta2() { return new LinkedList<>(receta2); }

    @Override
    public List<Recipe> getCrafteo() {
        ShapelessRecipe r = new ShapelessRecipe(IdlePrison.getCrafteoskey(), getResultado());
        ShapelessRecipe r2 = new ShapelessRecipe(IdlePrison.getCrafteoskey(), resultado2);
        StringBuilder s= new StringBuilder();

        for (ItemStack item : getReceta()) {
          r.addIngredient(item.getType());
          s.append(item.getType()).append(", ");
        }
        s = new StringBuilder(s.substring(0, s.length() - 2));
        s.append("],[");

        for (ItemStack item : receta2) {
            r2.addIngredient(item.getType());
            s.append(item.getType()).append(", ");
        }
        s = new StringBuilder(s.substring(0, s.length() - 2));

        IdlePrison.imprimirConsola("Crafteo creado:[" + s + "]");
        return List.of(r, r2);
    }

    @Override
    public int isCrafteo(LinkedList<ItemStack> items) {
        boolean devolver = true;
        int itemsMatch = 0;
        LinkedList<ItemStack> receta = new LinkedList<>(getReceta());
        LinkedList<ItemStack> receta2 = new LinkedList<>(getReceta2());

        for (ItemStack item : items) {
            if(item.getType().equals(Material.BARRIER)) continue;
            boolean existe = false;
            for (ItemStack rece : receta) {
                if(item.getType().equals(rece.getType())) {
                    receta.remove(rece);
                    existe = true;
                    itemsMatch++;
                }
            }
            if(!existe) {
                devolver = false;
                break;
            }
        }
        if(devolver && itemsMatch >= getReceta().size()) return 1;
        itemsMatch = 0;

        for (ItemStack item : items) {
            if(item.getType().equals(Material.BARRIER)) continue;
            boolean existe = false;
            for (ItemStack rece : receta2) {
                if(item.getType().equals(rece.getType())) {
                    receta2.remove(rece);
                    existe = true;
                    itemsMatch++;
                }
            }
            if(!existe) return 0;
        }
        if (itemsMatch >= getReceta2().size()) return 2;
        return 0;
    }

    @Override
    public ItemStack getResultado(int n){
        if(n == 1) return super.getResultado(n);
        else return resultado2.clone();
    }

    @Override
    public Inventory getGuide(Player p) {
        LinkedList<ItemStack> lista = getReceta();
        LinkedList<ItemStack> guia = new LinkedList<>();
        LinkedList<ItemStack> lista2 = getReceta2();
        LinkedList<ItemStack> guia2 = new LinkedList<>();
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
                guia2.add(vacio);
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

        switch (lista2.size()){
            case 1:
                for (int i = 0; i < 4; i++) guia2.add(vacio);
                guia2.add(lista2.pop());
                for (int i = 0; i < 4; i++) guia2.add(vacio);
                break;
            case 2:
                guia2.add(vacio);
                guia2.add(lista2.pop());
                for (int i = 0; i < 5; i++) guia2.add(vacio);
                guia2.add(lista2.pop());
                guia2.add(vacio);
                break;
            case 3:
                for (int i = 0; i < 3; i++) guia2.add(vacio);
                for (int i = 0; i < 3; i++) guia2.add(lista2.pop());
                for (int i = 0; i < 3; i++) guia2.add(vacio);
                break;
            case 9:
                for (int i = 0; i < 9; i++) guia2.add(lista2.pop());
                break;
        }

        p.sendMessage(guia.size() + " y " + guia2.size());
        return guiaCrafteoDoble(p, guia, getResultado(), guia2, resultado2);
    }
}
