package org.elako.idleprison.crafteos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.elako.idleprison.comandos.Idleprison;
import org.elako.idleprison.player.Rangos;

import java.util.LinkedList;

public abstract class Crafteo {
    private LinkedList<ItemStack> receta;
    private ItemStack resultado;
    private Rangos permiso;
    private boolean doble;

    public Crafteo(LinkedList<ItemStack> receta, ItemStack resultado, Rangos permiso, boolean doble) {
        this.receta = receta;
        this.resultado = resultado;
        this.permiso = permiso;
        this.doble = doble;
    }

    public abstract ItemStack getIcono();
    public abstract Recipe getCrafteo();

    public abstract Inventory getGuide(Player p);


    public LinkedList<ItemStack> getReceta() { return receta; }
    public void setReceta(LinkedList<ItemStack> receta) { this.receta = receta; }

    public ItemStack getResultado() { return resultado; }
    public void setResultado(ItemStack resultado) { this.resultado = resultado; }

    public Rangos getPermiso() { return permiso; }
    public void setPermiso(Rangos permiso) { this.permiso = permiso; }

    public boolean isDoble(){ return doble; }

    public static Inventory guiaCrafteoSimple(Player p, LinkedList<ItemStack> items, ItemStack resul){
        Inventory inventario = Bukkit.createInventory(p, 27, ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Crafteo");
        if (items.size() != 10) p.sendMessage("ERROR");
        for (int i=0;i<27;i++) {
            switch (i){
                case 2:
                case 3:
                case 4:
                case 11:
                case 12:
                case 13:
                case 20:
                case 21:
                case 22:
                    ItemStack item = items.pop();
                    if(!item.getType().equals(Material.BARRIER))inventario.setItem(i, item);
                    break;
                case 15:
                    inventario.setItem(i, resul);
                case 26:
                    inventario.setItem(i, Idleprison.crearObjeto(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR"));
                    break;
                default:
                    inventario.setItem(i,  Idleprison.crearObjeto(Material.BLACK_STAINED_GLASS_PANE," "));
            }
        }
        return inventario;
    }

}
