package org.elako.idleprison.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Nota {
    private final String nombre;
    private final List<String> consejo;
    private final List<String> conseguido;
    private final double recompensa;
    private final int numero;
    private final TipoNota tipo;

    public Nota(int numero, String nombre, List<String> consejo, List<String> conseguido, double recompensa,
                TipoNota tipo) {
        this.nombre = nombre;
        this.consejo = consejo;
        this.conseguido = conseguido;
        this.recompensa = recompensa;
        this.numero = numero;
        this.tipo = tipo;
    }

    public ItemStack crearObjeto(){
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = item.getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + "Nota " + numero +
                ": " + nombre);

        LinkedList<String> lore = new LinkedList<>(consejo);
        lore.add(ChatColor.GRAY + "Se consigue: ");
        lore.addAll(conseguido);
        lore.add(ChatColor.YELLOW + String.valueOf(ChatColor.ITALIC) + "[Dropea la nota para reclamar]");


        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        return item;
    }

    public double getRecompensa() { return recompensa; }
    public int getNumero() { return numero; }
    public TipoNota getTipo() { return tipo; }
}
