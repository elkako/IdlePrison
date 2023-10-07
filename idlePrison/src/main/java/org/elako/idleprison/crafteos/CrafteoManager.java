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

        ItemStack picoPiedra = PicosManager.getPicoPiedra();
        ItemStack roca = MaterialesManager.getItem(IpMateriales.ROCA);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                roca,   roca,       roca,
                roca,   picoMadera, roca,
                roca,   roca,       roca
        ) ), picoPiedra, Rangos.SINTECHO2 ) );

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

        ItemStack fragmentoRoja1 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_ROJO1);
        ItemStack esenciaRoja1 = MaterialesManager.getItem(IpMateriales.ESENCIA_ROJA1);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                fragmentoRoja1,   fragmentoRoja1,   fragmentoRoja1,
                fragmentoRoja1,   fragmentoRoja1,   fragmentoRoja1,
                fragmentoRoja1,   fragmentoRoja1,   fragmentoRoja1
        ) ), esenciaRoja1, Rangos.SINTECHO1 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaRoja1) ), picoPiedra,
                Rangos.SINTECHO1, Enchantment.LOOT_BONUS_BLOCKS, 1
        ) );


        ItemStack granGranito = MaterialesManager.getItem(IpMateriales.GRAN_GRANITO);
        ItemStack granito = MaterialesManager.getItem(IpMateriales.GRANITO);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                granito,   granito,   granito,
                granito,   granito,   granito,
                granito,   granito,   granito
        ) ), granGranito, Rangos.SINTECHO1 ) );

        ItemStack fragmentoVerde1 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_VERDE1);
        ItemStack esenciaVerde1 = MaterialesManager.getItem(IpMateriales.ESENCIA_VERDE1);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                fragmentoVerde1,   fragmentoVerde1,   fragmentoVerde1,
                fragmentoVerde1,   fragmentoVerde1,   fragmentoVerde1,
                fragmentoVerde1,   fragmentoVerde1,   fragmentoVerde1
        ) ), esenciaVerde1, Rangos.CAMPESINO3 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaVerde1) ), picoMadera,
                Rangos.CAMPESINO3, Enchantment.LOOT_BONUS_MOBS, 1
        ) );

        ItemStack fragmentoAzul2 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_AZUL2);
        ItemStack esenciaAzul2 = MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL2);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                fragmentoAzul2,   fragmentoAzul2,   fragmentoAzul2,
                fragmentoAzul2,   fragmentoAzul2,   fragmentoAzul2,
                fragmentoAzul2,   fragmentoAzul2,   fragmentoAzul2
        ) ), esenciaAzul2, Rangos.CAMPESINO3 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaAzul2) ), picoPiedra,
                Rangos.CAMPESINO3, Enchantment.DIG_SPEED, 2
        ) );

        ItemStack trigo = MaterialesManager.getItem(IpMateriales.TRIGO);
        ItemStack tablones = MaterialesManager.getItem(IpMateriales.TABLONES_ROBLE);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                nulo,   nulo,       nulo,
                trigo,  trigo,      trigo,
                nulo,   tablones,   nulo
        ) ), MaterialesManager.getItem(IpMateriales.PAN,2), Rangos.CAMPESINO2 ) );

        ItemStack catalejo = PicosManager.getCatalejo();
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                nulo,       nulo,       lente,
                tablones,   tablones,  lente,
                nulo,       nulo,       nulo
        ) ), catalejo, Rangos.CAMPESINO2 ) );

        ItemStack fragmentoAmarillo1 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_AMARILLO1);
        ItemStack esenciaAmarilla1 = MaterialesManager.getItem(IpMateriales.ESENCIA_AMARILLA1);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                fragmentoAmarillo1,   fragmentoAmarillo1,   fragmentoAmarillo1,
                fragmentoAmarillo1,   fragmentoAmarillo1,   fragmentoAmarillo1,
                fragmentoAmarillo1,   fragmentoAmarillo1,   fragmentoAmarillo1
        ) ), esenciaAmarilla1, Rangos.CAMPESINO1 ) );

        crafteos.add( new CrafteoShapeless( new LinkedList<>( List.of(
                esenciaAmarilla1, catalejo
        )), PicosManager.getDetectorBloques1() , Rangos.CAMPESINO1 ) );


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
