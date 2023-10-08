package org.elako.idleprison.crafteos;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.player.Rangos;

import java.util.LinkedList;
import java.util.List;

public class CrafteoEncantar extends Crafteo {
    private Enchantment encanti;
    private int nivel;

    public CrafteoEncantar(LinkedList<ItemStack> receta, ItemStack resultado, Rangos permiso, Enchantment encanti, int nivel) {
        super(receta, resultado, permiso);
        this.encanti = encanti;
        this.nivel = nivel;
    }

    @Override
    public ItemStack getIcono() {
        ItemStack item = getResultado();
        ItemMeta meta = item.getItemMeta();

        if(encanti.equals(Enchantment.DIG_SPEED)) meta.setDisplayName(ChatColor.WHITE + "Pico destructor ["+ nivel + "]");
        else if(encanti.equals(Enchantment.LOOT_BONUS_BLOCKS)) meta.setDisplayName(ChatColor.WHITE + "Pico vivo ["+ nivel + "]");
        else if(encanti.equals(Enchantment.LOOT_BONUS_MOBS)) meta.setDisplayName(ChatColor.WHITE + "Pico capitalista ["+ nivel + "]");
        else if(encanti.equals(Enchantment.MULTISHOT)) meta.setDisplayName(ChatColor.WHITE + "Pico oscuro ["+ nivel + "]");

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public List<Recipe> getCrafteo() {
        ShapelessRecipe recetaW = new ShapelessRecipe(new ItemStack(Material.WOODEN_PICKAXE));
        ShapelessRecipe recetaS = new ShapelessRecipe(new ItemStack(Material.STONE_PICKAXE));
        ShapelessRecipe recetaI = new ShapelessRecipe(new ItemStack(Material.IRON_PICKAXE));
        ShapelessRecipe recetaD = new ShapelessRecipe(new ItemStack(Material.DIAMOND_PICKAXE));

        for (ItemStack esencia: getReceta()) {
            recetaW.addIngredient(new RecipeChoice.ExactChoice(esencia));
            recetaS.addIngredient(new RecipeChoice.ExactChoice(esencia));
            recetaI.addIngredient(new RecipeChoice.ExactChoice(esencia));
            recetaD.addIngredient(new RecipeChoice.ExactChoice(esencia));
        }

        recetaW.addIngredient(Material.WOODEN_PICKAXE);
        recetaS.addIngredient(Material.STONE_PICKAXE);
        recetaI.addIngredient(Material.IRON_PICKAXE);
        recetaD.addIngredient(Material.DIAMOND_PICKAXE);

        IdlePrison.imprimirConsola("Crafteo creado:[Encantar: " + nivel + " " + encanti + "]");

        return List.of( recetaW, recetaS, recetaI, recetaD );
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
        return guiaCrafteoSimple(p, guia, getResultado());
    }

}
