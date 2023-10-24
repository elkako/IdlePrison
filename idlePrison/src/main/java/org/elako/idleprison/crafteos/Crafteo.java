package org.elako.idleprison.crafteos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.elako.idleprison.comandos.IdleprisonCom;
import org.elako.idleprison.player.rango.Rangos;

import java.util.LinkedList;
import java.util.List;

public abstract class Crafteo {
    private final LinkedList<ItemStack> receta;
    private final ItemStack resultado;
    private final Rangos permiso;

    public Crafteo(LinkedList<ItemStack> receta, ItemStack resultado, Rangos permiso) {
        this.receta = receta;
        this.resultado = resultado.clone();
        this.permiso = permiso;
    }

    public abstract List<Recipe> getCrafteo();

    public abstract Inventory getGuide(Player p);

    public abstract ItemStack getIcono();


    public LinkedList<ItemStack> getReceta() { return new LinkedList<>(receta); }

    public ItemStack getResultado() { return resultado; }

    public Rangos getPermiso() { return permiso; }

    public static Inventory guiaCrafteoSimple(Player p, LinkedList<ItemStack> items, ItemStack resul){
        Inventory inventario = Bukkit.createInventory(p, 27, ChatColor.BOLD + String.valueOf(ChatColor.LIGHT_PURPLE) + "Crafteo");
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
                    if(!item.getType().equals(Material.BARRIER)) inventario.setItem(i, item);
                    break;
                case 15:
                    inventario.setItem(i, resul);
                    break;
                case 26:
                    inventario.setItem(i, IdleprisonCom.crearObjeto(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR"));
                    break;
                default:
                    inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.BLACK_STAINED_GLASS_PANE," "));
            }
        }
        return inventario;
    }

    public static Inventory guiaCrafteoDoble(Player p, LinkedList<ItemStack> items, ItemStack resul,
                                             LinkedList<ItemStack> items2, ItemStack resul2){
        Inventory inventario = Bukkit.createInventory(p, 54, ChatColor.BOLD + String.valueOf(ChatColor.LIGHT_PURPLE) + "Crafteo");
        for (int i=0;i<54;i++) {
            p.sendMessage("i: " + i);
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
                case 32:
                case 33:
                case 34:
                case 41:
                case 42:
                case 43:
                case 50:
                case 51:
                case 52:
                    ItemStack item;
                    if (i<27) item = items.pop();
                    else  item = items2.pop();
                    if(!item.getType().equals(Material.BARRIER)) inventario.setItem(i, item);
                    break;
                case 15:
                    inventario.setItem(i, resul);
                    break;
                case 39:
                    inventario.setItem(i, resul2);
                    break;
                case 45:
                    inventario.setItem(i, IdleprisonCom.crearObjeto(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR"));
                    break;
                default:
                    if (i<27) inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.BLACK_STAINED_GLASS_PANE," "));
                    else inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
            }
        }
        return inventario;
    }

}
