package org.elako.idleprison.crafteos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.elako.idleprison.comandos.Idleprison;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.items.MaterialesManager;
import org.elako.idleprison.items.PicosManager;
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

        ItemStack maderaInferCali = MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO_CALIDAD);
        crafteos.add( new CrafteoShapelessDoble( new LinkedList<>( List.of(
                    MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO)
            )), MaterialesManager.getItem(IpMateriales.TABLONES_QUEMADOS,7), Rangos.CONDENADO4, new LinkedList<>( List.of(
                    maderaInferCali
            )), MaterialesManager.getItem(IpMateriales.TABLONES_QUEMADOS,15)
        ) );

        ItemStack picoMadera = PicosManager.getPicoMadera();
        ItemStack tablonesQ = MaterialesManager.getItem(IpMateriales.TABLONES_QUEMADOS);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                tablonesQ,   tablonesQ,   tablonesQ,
                nulo,       tablonesQ,   nulo,
                nulo,       tablonesQ,   nulo
        ) ), picoMadera, Rangos.CONDENADO3 ) );

        crafteos.add( new CrafteoShapeless( new LinkedList<>( List.of(
                MaterialesManager.getItem(IpMateriales.PESCADO_CRUDO),
                tablonesQ
        )), MaterialesManager.getItem(IpMateriales.PESCADO_BRASA) , Rangos.CONDENADO2 ) );

        ItemStack fragmentoAzul1 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_AZUL1);
        ItemStack esenciaAzul1 = MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL1);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                fragmentoAzul1,   fragmentoAzul1,   fragmentoAzul1,
                fragmentoAzul1,   fragmentoAzul1,   fragmentoAzul1,
                fragmentoAzul1,   fragmentoAzul1,   fragmentoAzul1
        ) ), esenciaAzul1, Rangos.CONDENADO2 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaAzul1) ), picoMadera,
                Rangos.CONDENADO2, Enchantment.DIG_SPEED, 1
        ) );

        ItemStack maderaRoble = MaterialesManager.getItem(IpMateriales.MADERA_ROBLE);
        crafteos.add( new CrafteoShapelessDoble( new LinkedList<>( List.of(
                    maderaRoble
            )), MaterialesManager.getItem(IpMateriales.TABLONES_ROBLE, 5), Rangos.SINTECHO2, new LinkedList<>( List.of(
                    maderaRoble, maderaRoble , maderaRoble
            )), MaterialesManager.getItem(IpMateriales.TABLONES_ROBLE,17)
        ) );

        ItemStack roca = MaterialesManager.getItem(IpMateriales.ROCA);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                roca,   roca,       roca,
                roca,   picoMadera, roca,
                roca,   roca,       roca
        ) ), PicosManager.getPicoPiedra(), Rangos.SINTECHO2 ) );

        ItemStack arena = MaterialesManager.getItem(IpMateriales.ARENA);
        ItemStack bloqueMagma = MaterialesManager.getItem(IpMateriales.BLOQUE_MAGMATICO);
        ItemStack lente = MaterialesManager.getItem(IpMateriales.LENTE);
        crafteos.add( new CrafteoShapelessDoble( new LinkedList<>( List.of(
                arena,              arena, arena ,
                maderaInferCali ,   arena, bloqueMagma,
                arena,              arena, arena
            )), lente, Rangos.SINTECHO2, new LinkedList<>( List.of(
                arena,          arena, arena ,
                maderaRoble ,   arena, bloqueMagma,
                arena,          arena, arena
            )), lente
        ) );


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
