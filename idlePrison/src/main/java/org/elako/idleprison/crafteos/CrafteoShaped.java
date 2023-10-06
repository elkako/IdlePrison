package org.elako.idleprison.crafteos;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.player.Rangos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CrafteoShaped extends Crafteo {

    public CrafteoShaped(LinkedList<ItemStack> receta, ItemStack resultado, Rangos permiso) {
        super(receta, resultado, permiso);
    }

    @Override
    public ItemStack getIcono() { return getResultado(); }

    @Override
    public List<Recipe> getCrafteo() {
        ShapedRecipe receta = new ShapedRecipe(getResultado());

        List<ItemStack> array = getReceta().stream().distinct().collect(Collectors.toList());

        HashMap<ItemStack, Character> map = new HashMap<>();
        char a = 'a';
        for (ItemStack item: array) {
            if (item.getType().equals(Material.BARRIER)) {
                map.put(item,' ');
            } else {
                receta.setIngredient(a,item.getType());
                map.put(item,a);
                a++;
            }
        }

        String s1 = ""; String s2 = ""; String s3 = "";
        int n = 0;
        for (ItemStack item :getReceta()) {
            n++;
            if(n<=3) s1 += map.get(item);
            else if(n<=6) s2 += map.get(item);
            else s3 += map.get(item);
        }

        receta.shape(s1, s2,s3);
        IdlePrison.imprimirConsola("Crafteo creado:[" + s1 + "], [" + s2 + "], [" + s3 + "]");

        return List.of(receta);
    }

    @Override
    public Inventory getGuide(Player p) {
        return guiaCrafteoSimple(p, getReceta(), getResultado());
    }
}
