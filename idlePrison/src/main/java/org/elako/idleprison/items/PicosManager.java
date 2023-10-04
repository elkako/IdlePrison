package org.elako.idleprison.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Objects;

public class PicosManager {
    public static ItemStack getMenu(){
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta itemMeta = item.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName( ChatColor.RED + "" + ChatColor.BOLD + "Menú");
        itemMeta.setLore(Arrays.asList(ChatColor.WHITE + "Pontelo en la mano o haz click para",
                ChatColor.WHITE + " abrir el menú de /idleprison"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getCatalejo(){
        ItemStack item = new ItemStack(Material.SPYGLASS);
        ItemMeta itemMeta = item.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName( ChatColor.WHITE + "" + ChatColor.BOLD + "Catalejo");
        itemMeta.setLore(Arrays.asList(ChatColor.WHITE + "Utensilio que puede ser utilizado para",
                ChatColor.WHITE + " crafteos, no se pierde al renacer"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getDetectorBloques1(){
        ItemStack item = new ItemStack(Material.SPYGLASS);
        ItemMeta itemMeta = item.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName( ChatColor.WHITE + "" + ChatColor.BOLD + "Detector de bloques [1]");
        itemMeta.setLore(Arrays.asList(ChatColor.WHITE + "Click derecho a un bloque para ver información",
                                        ChatColor.WHITE + " del mismo, no se pierde al renacer",
                ChatColor.WHITE + "Distancia máxima: 8"  ));
        itemMeta.addEnchant(Enchantment.CHANNELING,1,true);
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getPicoMadera(){
        ItemStack item = new ItemStack(Material.WOODEN_PICKAXE);
        ItemMeta itemMeta = item.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName( ChatColor.WHITE + "" + ChatColor.BOLD + "Pico humilde");
        itemMeta.setLore(Arrays.asList(ChatColor.WHITE + "No es muy eficiente pero si duradero",
                ChatColor.WHITE + " está creado con tablones quemados"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getPicoPiedra(){
        ItemStack item = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta itemMeta = item.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName( ChatColor.WHITE + "" + ChatColor.BOLD + "Pico resistente");
        itemMeta.setLore(Arrays.asList(ChatColor.WHITE + "Es algo más eficiente pero tampoco",
                ChatColor.WHITE + " exageradamente, al menos funciona"));
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return item;
    }
}
