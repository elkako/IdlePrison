package org.elako.idleprison.crafteos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.comandos.IdleprisonCom;
import org.elako.idleprison.items.materiales.IpMateriales;
import org.elako.idleprison.items.materiales.MaterialesManager;
import org.elako.idleprison.player.rango.Rangos;
import org.elako.idleprison.player.rango.RangosManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CraftManager {
    private static RangosManager rangosManager;
    private static final LinkedList<Crafteo> crafteos = new LinkedList<>();

    public CraftManager(RangosManager rangos) {
        rangosManager = rangos;
        ItemStack nulo = new ItemStack(Material.BARRIER);

        ItemStack maderaInferCali = MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO_CALIDAD);
        crafteos.add( new CrafteoShapelessDoble( new LinkedList<>( List.of(
                    MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO)
            )), MaterialesManager.getItem(IpMateriales.TABLONES_QUEMADOS,7), Rangos.CONDENADO4, new LinkedList<>( List.of(
                    maderaInferCali
            )), MaterialesManager.getItem(IpMateriales.TABLONES_QUEMADOS,15)
        ) );

        ItemStack tablonesQ = MaterialesManager.getItem(IpMateriales.TABLONES_QUEMADOS);
        ItemStack picoMadera = MaterialesManager.getItem(IpMateriales.PICO_MADERA);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                tablonesQ,   tablonesQ,   tablonesQ,
                nulo,       tablonesQ,   nulo,
                nulo,       tablonesQ,   nulo
        ) ), picoMadera, Rangos.CONDENADO3 ) );

        crafteos.add( new CrafteoShapeless( new LinkedList<>( List.of(
                MaterialesManager.getItem(IpMateriales.PESCADO_CRUDO),
                tablonesQ
        )), MaterialesManager.getItem(IpMateriales.PESCADO_BRASA) , Rangos.CONDENADO2 ) );

        ItemStack hielo = MaterialesManager.getItem(IpMateriales.HIELO);
        ItemStack bloqueMagma = MaterialesManager.getItem(IpMateriales.BLOQUE_MAGMATICO);
        ItemStack piedraElemental = MaterialesManager.getItem(IpMateriales.ELEMENTO);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                nulo,           nulo,           nulo,
                bloqueMagma,    bloqueMagma,    bloqueMagma,
                hielo,          hielo,          hielo
        ) ), piedraElemental, Rangos.CONDENADO2 ));

        crafteosArmadura( piedraElemental, nulo, List.of(
                MaterialesManager.getItem(IpMateriales.CASCO_ELEMENTAL),
                MaterialesManager.getItem(IpMateriales.PECHERA_ELEMENTAL),
                MaterialesManager.getItem(IpMateriales.PANTALONES_ELEMENTALES),
                MaterialesManager.getItem(IpMateriales.BOTAS_ELEMENTALES)
        ),Rangos.CONDENADO2);

        ItemStack fragmentoAzul1 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_AZUL1,2);
        ItemStack esenciaAzul1 = MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL1);
        crafteos.add( new CrafteoFragmentado( new LinkedList<>( List.of(
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
        ItemStack picoPiedra = MaterialesManager.getItem(IpMateriales.PICO_PIEDRA);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                roca,   roca,       roca,
                roca,   picoMadera, roca,
                roca,   roca,       roca
        ) ), picoPiedra, Rangos.SINTECHO2 ) );

        ItemStack arena = MaterialesManager.getItem(IpMateriales.ARENA);
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

        ItemStack rodajaSandia = MaterialesManager.getItem(IpMateriales.RODAJA_SANDIA);
        ItemStack sandia = MaterialesManager.getItem(IpMateriales.SANDIA);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                rodajaSandia,   rodajaSandia,   rodajaSandia,
                rodajaSandia,   rodajaSandia,   rodajaSandia,
                rodajaSandia,   rodajaSandia,   rodajaSandia
        ) ), sandia, Rangos.SINTECHO2 ) );

        crafteosArmadura( sandia, nulo, List.of(
                MaterialesManager.getItem(IpMateriales.CASCO_SANDIA),
                MaterialesManager.getItem(IpMateriales.PECHERA_SANDIA),
                MaterialesManager.getItem(IpMateriales.PANTALONES_SANDIA),
                MaterialesManager.getItem(IpMateriales.BOTAS_SANDIA)
        ),Rangos.SINTECHO2);

        ItemStack fragmentoRoja1 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_ROJO1);
        ItemStack esenciaRoja1 = MaterialesManager.getItem(IpMateriales.ESENCIA_ROJA1);
        crafteos.add( new CrafteoFragmentado( new LinkedList<>( List.of(
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

        ItemStack florDesierto = MaterialesManager.getItem(IpMateriales.FLOR_DESIERTO);
        ItemStack desiertoRojo = MaterialesManager.getItem(IpMateriales.DESIERTO_ROJO);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                nulo,           florDesierto,   nulo,
                granGranito,   granGranito,     granGranito,
                granGranito,   granGranito,     granGranito
        ) ), desiertoRojo, Rangos.SINTECHO1 ) );

        crafteosArmadura( desiertoRojo, nulo, List.of(
                MaterialesManager.getItem(IpMateriales.CASCO_DESERTICO),
                MaterialesManager.getItem(IpMateriales.PECHERA_DESERTICA),
                MaterialesManager.getItem(IpMateriales.PANTALONES_DESERTICOS),
                MaterialesManager.getItem(IpMateriales.BOTAS_DESERTICAS)
        ),Rangos.SINTECHO1);

        ItemStack fragmentoVerde1 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_VERDE1);
        ItemStack esenciaVerde1 = MaterialesManager.getItem(IpMateriales.ESENCIA_VERDE1);
        crafteos.add( new CrafteoFragmentado( new LinkedList<>( List.of(
                fragmentoVerde1,   fragmentoVerde1,   fragmentoVerde1,
                fragmentoVerde1,   fragmentoVerde1,   fragmentoVerde1,
                fragmentoVerde1,   fragmentoVerde1,   fragmentoVerde1
        ) ), esenciaVerde1, Rangos.CAMPESINO3 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaVerde1) ), picoMadera,
                Rangos.CAMPESINO3, Enchantment.LOOT_BONUS_MOBS, 1
        ) );

        ItemStack fragmentoAzul2 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_AZUL2);
        ItemStack esenciaAzul2 = MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL2);
        crafteos.add( new CrafteoFragmentado( new LinkedList<>( List.of(
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

        ItemStack catalejo = MaterialesManager.getItem(IpMateriales.CATALEJO);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                nulo,       nulo,       lente,
                tablones,   tablones,  lente,
                nulo,       nulo,       nulo
        ) ), catalejo, Rangos.CAMPESINO2 ) );

        ItemStack fragmentoAmarillo1 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_AMARILLO1);
        ItemStack esenciaAmarilla1 = MaterialesManager.getItem(IpMateriales.ESENCIA_AMARILLA1);
        crafteos.add( new CrafteoFragmentado( new LinkedList<>( List.of(
                fragmentoAmarillo1,   fragmentoAmarillo1,   fragmentoAmarillo1,
                fragmentoAmarillo1,   fragmentoAmarillo1,   fragmentoAmarillo1,
                fragmentoAmarillo1,   fragmentoAmarillo1,   fragmentoAmarillo1
        ) ), esenciaAmarilla1, Rangos.CAMPESINO1 ) );

        crafteos.add( new CrafteoShapeless( new LinkedList<>( List.of(
                esenciaAmarilla1, catalejo
        )), MaterialesManager.getItem(IpMateriales.DETECTOR1), Rangos.CAMPESINO1 ) );

        ItemStack carbon = MaterialesManager.getItem(IpMateriales.CARBON);
        crafteos.add( new CrafteoShapeless( new LinkedList<>( List.of(
                MaterialesManager.getItem(IpMateriales.SALMON_CRUDO),
                carbon
        )), MaterialesManager.getItem(IpMateriales.SALMON_AHUMADO) , Rangos.MINERO3 ) );

        ItemStack menaHierro = MaterialesManager.getItem(IpMateriales.MENA_HIERRO);
        ItemStack hierro = MaterialesManager.getItem(IpMateriales.LINGOTE_HIERRO);
        crafteos.add( new CrafteoShapeless( new LinkedList<>( List.of(
                menaHierro, carbon, menaHierro
        )), hierro , Rangos.MINERO3 ) );

        ItemStack picoHierro = MaterialesManager.getItem(IpMateriales.PICO_HIERRO);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                hierro,   hierro,       hierro,
                hierro,   picoPiedra,   hierro,
                hierro,   hierro,       hierro
        ) ), picoHierro, Rangos.MINERO3 ) );

        crafteosArmadura( hierro, nulo, List.of(
                MaterialesManager.getItem(IpMateriales.CASCO_HIERRO),
                MaterialesManager.getItem(IpMateriales.PECHERA_HIERRO),
                MaterialesManager.getItem(IpMateriales.PANTALONES_HIERRO),
                MaterialesManager.getItem(IpMateriales.BOTAS_HIERRO)
        ),Rangos.MINERO2);

        ItemStack fragmentoRojo2 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_ROJO2);
        ItemStack esenciaRoja2 = MaterialesManager.getItem(IpMateriales.ESENCIA_ROJA2);
        crafteos.add( new CrafteoFragmentado( new LinkedList<>( List.of(
                fragmentoRojo2,   fragmentoRojo2,   fragmentoRojo2,
                fragmentoRojo2,   fragmentoRojo2,   fragmentoRojo2,
                fragmentoRojo2,   fragmentoRojo2,   fragmentoRojo2
        ) ), esenciaRoja2, Rangos.MINERO1 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaRoja2) ), picoHierro,
                Rangos.MINERO1, Enchantment.LOOT_BONUS_BLOCKS, 2
        ) );

        ItemStack fragmentoVerde2 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_VERDE2);
        ItemStack esenciaVerde2 = MaterialesManager.getItem(IpMateriales.ESENCIA_VERDE2);
        crafteos.add( new CrafteoFragmentado( new LinkedList<>( List.of(
                fragmentoVerde2,   fragmentoVerde2,   fragmentoVerde2,
                fragmentoVerde2,   fragmentoVerde2,   fragmentoVerde2,
                fragmentoVerde2,   fragmentoVerde2,   fragmentoVerde2
        ) ), esenciaVerde2, Rangos.MINERO1 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaVerde2) ), picoMadera,
                Rangos.MINERO1, Enchantment.LOOT_BONUS_MOBS, 2
        ) );

        ItemStack fragmentoNegro1 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_NEGRO1);
        ItemStack esenciaNegra1 = MaterialesManager.getItem(IpMateriales.ESENCIA_NEGRA1);
        crafteos.add( new CrafteoFragmentado( new LinkedList<>( List.of(
                fragmentoNegro1,   fragmentoNegro1,   fragmentoNegro1,
                fragmentoNegro1,   fragmentoNegro1,   fragmentoNegro1,
                fragmentoNegro1,   fragmentoNegro1,   fragmentoNegro1
        ) ), esenciaNegra1, Rangos.MINERO1 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaNegra1) ), picoPiedra,
                Rangos.MINERO1, Enchantment.MULTISHOT, 1
        ) );

        for (Crafteo c : crafteos) {
            Rangos rango = c.getPermiso();

            rango.addDesbloqueo("[Minas: has desbloqueado " + Objects.requireNonNull(c.getIcono().getItemMeta()).getDisplayName() + "]");
        }

    }

    public static Inventory CraftMenu(Player p){
        // tamaños inventarios: 9 18 27 36 45 54

        Inventory inventario = Bukkit.createInventory(p, 54, ChatColor.BOLD + String.valueOf(ChatColor.RED) + "Craftear");

        for (int i=0;i<54;i++) {
            switch (i){
                case 10:
                case 11:
                case 12:
                case 19:
                case 20:
                case 21:
                case 25:
                case 28:
                case 29:
                case 30:
                    break;
                case 22:
                case 24:
                    inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.GLASS_PANE," "));
                    break;
                case 23:
                    inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.WHITE_STAINED_GLASS_PANE," "));
                    break;
                case 45:
                    inventario.setItem(i, IdleprisonCom.crearObjetoLore(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR", List.of(
                            ChatColor.WHITE + "Botón para volver al menú")));
                    break;
                case 50:
                    inventario.setItem(i, IdleprisonCom.crearObjeto(Material.CRAFTING_TABLE,ChatColor.WHITE + "Craftear x1"));
                    break;
                case 52:
                    inventario.setItem(i, IdleprisonCom.crearObjeto(Material.FLETCHING_TABLE,ChatColor.WHITE + "Craftear max") );
                    break;
                default:
                    if (i<27) inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.BLACK_STAINED_GLASS_PANE," "));
                    else inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
            }
        }

        return inventario;
    }

    public static Inventory CraftGuide(Player p, int pagina){
        // tamaños inventarios: 9 18 27 36 45 54

        Inventory inventario = Bukkit.createInventory(p, 54, ChatColor.BOLD + String.valueOf(ChatColor.DARK_PURPLE) + "CraftGuide");
        int i = 0;
        for (Crafteo c: crafteos) {
            if (rangosManager.isPermitido(p.getName(), c.getPermiso())) {
                ItemStack icono = c.getIcono();
                if(icono.getAmount()>1) icono.setAmount(1);
                inventario.setItem(i, icono);
            }
            else break;
            i++;
        }

        inventario.setItem(53, IdleprisonCom.crearObjetoLore(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR", List.of(
                ChatColor.WHITE + "Botón para volver al menú")));

        return inventario;
    }

    public void interactuar(ItemStack item, Player p){
        for ( Crafteo c : crafteos ) {
            if(MaterialesManager.comparar(item, c.getIcono())) p.openInventory(c.getGuide(p));
        }
    }

    private LinkedList<ItemStack> crafteoToLista(Inventory inventario) {
        LinkedList<ItemStack> items = new LinkedList<>();
        int n = 0;
        for (int i = 10; i <= 30; i++) {
            n++;
            if(inventario.getItem(i) != null) {
                items.add( inventario.getItem(i) );
            } else{
                items.add( IdleprisonCom.crearObjeto(Material.BARRIER, " ") );
            }
            if(n>=3){
                n = 0;
                i += 6;
            }
        }
        return items;
    }

    private void limpiarCrafteo(Inventory inventario) {
        int n = 0;
        for (int i = 10; i <= 30; i++) {
            n++;
            ItemStack item = inventario.getItem(i);
            if(item != null) {
                item.setAmount(item.getAmount()-1);
                inventario.setItem( i, item );
            }

            if(n>=3){
                n = 0;
                i += 6;
            }
        }
    }

    public void craftear(Inventory inventario, Player p) {
        LinkedList<ItemStack> items = new LinkedList<>(crafteoToLista(inventario));

        boolean conseguido = false;
        for (Crafteo crafteo:crafteos) {
            int ncraft = crafteo.isCrafteo(items);
            if(ncraft != 0) {
                conseguido = true;
                ItemStack resul = crafteo.getResultado(ncraft);
                if ( crafteo.getClass().equals(CrafteoEncantar.class) ){
                    resul = ((CrafteoEncantar) crafteo).encantar(items.get(ncraft));
                }
                if (inventario.getItem(25) != null) p.getInventory().addItem(inventario.getItem(25));
                inventario.setItem(25,resul);

                p.sendMessage( resul.getType().toString() );
                tickCrafteo(inventario);
                limpiarCrafteo(inventario);
            }
        }

        if(conseguido) {
            p.sendMessage("PIOLA");
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 100, 2F);
        } else {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 100, 1.3F);
            p.sendMessage("ANTIPIOLA");

        }
    }

    public void tickCrafteo(Inventory inventario) {
        LinkedList<ItemStack> items = new LinkedList<>(crafteoToLista(inventario));

        for (Crafteo crafteo:crafteos) {
            int ncraft = crafteo.isCrafteo(items);
            if (ncraft != 0) {
                IdlePrison.broadcast("MENÚ");
                inventario.setItem(23,  IdleprisonCom.crearObjeto(Material.LIME_STAINED_GLASS_PANE," "));
                return;
            }
        }
        inventario.setItem(23,  IdleprisonCom.crearObjeto(Material.WHITE_STAINED_GLASS_PANE," "));
    }


    private void crafteosArmadura(ItemStack material, ItemStack nulo, List<ItemStack> armaduras, Rangos rango){
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                nulo,       nulo,       nulo,
                material,   material,   material,
                material,   nulo,       material
        ) ), armaduras.get(0), rango ) );

        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                material,   nulo,       material,
                material,   material,   material,
                material,   material,   material
        ) ), armaduras.get(1), rango ) );

        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                material,   material,   material,
                material,   nulo,       material,
                material,   nulo,       material
        ) ), armaduras.get(2), rango ) );

        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                nulo,       nulo,   nulo,
                material,   nulo,   material,
                material,   nulo,   material
        ) ), armaduras.get(3), rango ) );
    }
}


