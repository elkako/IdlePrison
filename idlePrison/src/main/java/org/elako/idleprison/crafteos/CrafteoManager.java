package org.elako.idleprison.crafteos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.elako.idleprison.comandos.Idleprison;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.items.MaterialesManager;
import org.elako.idleprison.player.Rangos;
import org.elako.idleprison.player.RangosManager;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CrafteoManager {
    RangosManager rangosManager;
    LinkedList<Crafteo> crafteos = new LinkedList<>();

    public CrafteoManager(RangosManager rangos) {
        rangosManager = rangos;
        ItemStack nulo = new ItemStack(Material.BARRIER);

        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                MaterialesManager.getItem(IpMateriales.TRIGO), MaterialesManager.getItem(IpMateriales.PAN) , MaterialesManager.getItem(IpMateriales.TRIGO),
                nulo, MaterialesManager.getItem(IpMateriales.CARBON), nulo,
                nulo, MaterialesManager.getItem(IpMateriales.ALGA), MaterialesManager.getItem(IpMateriales.PAN)) ), new ItemStack(Material.CALCITE),
                Rangos.CONDENADO4 ) );

        crafteos.add( new CrafteoShapeless( new LinkedList<>( List.of(
                MaterialesManager.getItem(IpMateriales.TRIGO), MaterialesManager.getItem(IpMateriales.PAN) )
        ), new ItemStack(Material.MELON), Rangos.CONDENADO3 ) );

        crafteos.add( new CrafteoShapeless( new LinkedList<>( List.of(
                MaterialesManager.getItem(IpMateriales.TRIGO), MaterialesManager.getItem(IpMateriales.PAN)
                , MaterialesManager.getItem(IpMateriales.PAN) )
        ), new ItemStack(Material.RABBIT), Rangos.CONDENADO3 ) );

        crafteos.add( new CrafteoShapeless( new LinkedList<>( List.of(
                MaterialesManager.getItem(IpMateriales.TRIGO))
        ), new ItemStack(Material.PAPER), Rangos.CONDENADO3 ) );


    }

    public LinkedList<Recipe> getCrafteos() {
        LinkedList<Recipe> recetas = new LinkedList<>();
        for (Crafteo c : crafteos) {
            for (Recipe r :c.getCrafteo()) {
                recetas.add( r );
            }
        }
        return recetas;
    }

    public Inventory CraftGuide(Player p){
        Inventory inventario = Bukkit.createInventory(p, 27, ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "CraftGuide");
        int i = 0;
        for (Crafteo c: crafteos) {
            if (rangosManager.isPermitido(p.getName(), c.getPermiso())) inventario.setItem(i, c.getIcono());
            else break;
            i++;
        }

        inventario.setItem(26, Idleprison.crearObjetoLore(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR", Arrays.asList(
                ChatColor.WHITE + "Botón para volver al menú"  ) ));

        return inventario;
    }

    public void interactuar(ItemStack item, Player p){
        if (item.getType().equals(Material.RED_STAINED_GLASS_PANE))
            p.openInventory(org.elako.idleprison.comandos.Crafteo.crearInventario(p));
        for ( Crafteo c : crafteos ) {
            if (item.equals(c.getResultado())) p.openInventory(c.getGuide(p));
        }
    }

}
