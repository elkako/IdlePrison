package org.elako.idleprison.crafteos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.elako.idleprison.comandos.CrafteoCom;
import org.elako.idleprison.comandos.IdleprisonCom;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.items.MaterialesManager;
import org.elako.idleprison.player.Rangos;
import org.elako.idleprison.player.RangosManager;

import java.util.LinkedList;
import java.util.List;

public class CrafteoManager {
    private static RangosManager rangosManager;
    private static final LinkedList<Crafteo> crafteos = new LinkedList<>();

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

        ItemStack tablonesQ = MaterialesManager.getItem(IpMateriales.TABLONES_QUEMADOS);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                tablonesQ,   tablonesQ,   tablonesQ,
                nulo,       tablonesQ,   nulo,
                nulo,       tablonesQ,   nulo
        ) ), MaterialesManager.getItem(IpMateriales.PICO_MADERA), Rangos.CONDENADO3 ) );

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

        ItemStack fragmentoAzul1 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_AZUL1);
        ItemStack esenciaAzul1 = MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL1);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                fragmentoAzul1,   fragmentoAzul1,   fragmentoAzul1,
                fragmentoAzul1,   fragmentoAzul1,   fragmentoAzul1,
                fragmentoAzul1,   fragmentoAzul1,   fragmentoAzul1
        ) ), esenciaAzul1, Rangos.CONDENADO2 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaAzul1) ), MaterialesManager.getItem(IpMateriales.PICO_MADERA),
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
                roca,   MaterialesManager.getItem(IpMateriales.PICO_MADERA), roca,
                roca,   roca,       roca
        ) ), MaterialesManager.getItem(IpMateriales.PICO_PIEDRA), Rangos.SINTECHO2 ) );

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
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                fragmentoRoja1,   fragmentoRoja1,   fragmentoRoja1,
                fragmentoRoja1,   fragmentoRoja1,   fragmentoRoja1,
                fragmentoRoja1,   fragmentoRoja1,   fragmentoRoja1
        ) ), esenciaRoja1, Rangos.SINTECHO1 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaRoja1) ), MaterialesManager.getItem(IpMateriales.PICO_PIEDRA),
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
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                fragmentoVerde1,   fragmentoVerde1,   fragmentoVerde1,
                fragmentoVerde1,   fragmentoVerde1,   fragmentoVerde1,
                fragmentoVerde1,   fragmentoVerde1,   fragmentoVerde1
        ) ), esenciaVerde1, Rangos.CAMPESINO3 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaVerde1) ), MaterialesManager.getItem(IpMateriales.PICO_MADERA),
                Rangos.CAMPESINO3, Enchantment.LOOT_BONUS_MOBS, 1
        ) );

        ItemStack fragmentoAzul2 = MaterialesManager.getItem(IpMateriales.FRAGMENTO_AZUL2);
        ItemStack esenciaAzul2 = MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL2);
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                fragmentoAzul2,   fragmentoAzul2,   fragmentoAzul2,
                fragmentoAzul2,   fragmentoAzul2,   fragmentoAzul2,
                fragmentoAzul2,   fragmentoAzul2,   fragmentoAzul2
        ) ), esenciaAzul2, Rangos.CAMPESINO3 ) );

        crafteos.add( new CrafteoEncantar( new LinkedList<>( List.of(esenciaAzul2) ), MaterialesManager.getItem(IpMateriales.PICO_PIEDRA),
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
        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                fragmentoAmarillo1,   fragmentoAmarillo1,   fragmentoAmarillo1,
                fragmentoAmarillo1,   fragmentoAmarillo1,   fragmentoAmarillo1,
                fragmentoAmarillo1,   fragmentoAmarillo1,   fragmentoAmarillo1
        ) ), esenciaAmarilla1, Rangos.CAMPESINO1 ) );

        crafteos.add( new CrafteoShapeless( new LinkedList<>( List.of(
                esenciaAmarilla1, catalejo
        )), MaterialesManager.getItem(IpMateriales.DETECTOR1), Rangos.CAMPESINO1 ) );

    }

    public LinkedList<Recipe> getCrafteos() {
        LinkedList<Recipe> recetas = new LinkedList<>();
        for (Crafteo c : crafteos) {
            recetas.addAll(c.getCrafteo());
        }
        return recetas;
    }

    public static Inventory CraftGuide(Player p){
        // tamaños inventarios: 9 18 27 36 45 54
        Inventory inventario = Bukkit.createInventory(p, 36, ChatColor.BOLD + String.valueOf(ChatColor.DARK_PURPLE) + "CraftGuide");
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

        inventario.setItem(35, IdleprisonCom.crearObjetoLore(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR", List.of(
                ChatColor.WHITE + "Botón para volver al menú")));

        return inventario;
    }

    public void interactuar(ItemStack item, Player p){
        if (item.getType().equals(Material.RED_STAINED_GLASS_PANE))
            p.openInventory(CrafteoCom.crearInventario(p));
        for ( Crafteo c : crafteos ) {
            if(MaterialesManager.comparar(item, c.getIcono())) p.openInventory(c.getGuide(p));
        }
    }

    public Crafteo getCrafteo(ItemStack itemStack){
        for ( Crafteo c : crafteos ) {
            if(c.getClass() != CrafteoEncantar.class)
                    if(MaterialesManager.comparar(itemStack,c.getResultado())) return c;
        }

        return null;
    }

    public CrafteoEncantar getEncantar(ItemStack[] matrix) {
        CrafteoEncantar devolver = null;
        for (ItemStack i:matrix) {
            if(i == null ) continue;
            if(i.getItemMeta() == null ) continue;

            if(i.getItemMeta().getDisplayName().contains("Esencia")){
                for (Crafteo c:crafteos) {
                    if(c.getClass() == CrafteoEncantar.class){
                        CrafteoEncantar ce = (CrafteoEncantar) c;
                        if (ce.isCrafteoEcantado(i.getEnchantments()))
                            devolver = ce;
                    }
                }
            }
        }
        return devolver;
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
