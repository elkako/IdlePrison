package org.elako.idleprison.items;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.elako.idleprison.items.armaduras.IpArmadura;
import org.elako.idleprison.items.armaduras.IpArmaduraColor;
import org.elako.idleprison.items.armaduras.SetsArmadura;
import org.elako.idleprison.player.rango.Rangos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.bukkit.ChatColor.*;

public class MaterialesManager {
    private static final LinkedList<IpMaterial> materiales = new LinkedList<>();

    public MaterialesManager() {
        //infierno 1,2,3,4
        materiales.add(new IpHerramienta(Material.ENCHANTED_BOOK, "Menú de idlePrison", List.of( // madera infierno
                WHITE + "Pontelo en la mano o haz click para",
                WHITE + " abrir el menú de /idleprison"),
                Rangos.CONDENADO4, IpMateriales.MENU, itemMeta -> {} ));

        materiales.add(new IpMaterial(Material.STRIPPED_CRIMSON_STEM, "Madera del infierno", List.of( // madera infierno
                WHITE + "Tronco de madera procedente del infierno"),
                -1, Rangos.CONDENADO4,IpMateriales.MADERA_INFIERNO));

        materiales.add(new IpMaterial(Material.STRIPPED_WARPED_STEM, "Madera del infierno de calidad", List.of( // madera infierno calidad
                WHITE + "Tronco de madera procedente del infierno"),
                -1, Rangos.CONDENADO4,IpMateriales.MADERA_INFIERNO_CALIDAD));

        materiales.add(new IpMaterial(Material.CRIMSON_PLANKS, "Tablones quemados", List.of( // tablones quemados
                WHITE + "Tablones de madera procedentes del infierno"),
                0.75, Rangos.CONDENADO4,IpMateriales.TABLONES_QUEMADOS));

        //infierno 1
        materiales.add(new IpHerramienta(Material.WOODEN_PICKAXE, "Pico humilde", List.of( // madera infierno
                WHITE + "No es muy eficiente pero si duradero",
                WHITE + " está creado con tablones quemados"),
                Rangos.CONDENADO3, IpMateriales.PICO_MADERA, itemMeta -> itemMeta.setUnbreakable(true)));

        materiales.add(new IpMaterial(Material.CALCITE, "Calcita", Arrays.asList( // calcita
                WHITE + "Bloque de piedra formado por huesos de",
                WHITE + " humanos que se perdieron por el infierno"),
                1, Rangos.CONDENADO4, IpMateriales.CALCITA));

        materiales.add(new IpMaterial(Material.BONE_MEAL, "Polvo de hueso", List.of( // polvo de hueso
                WHITE + "Trozo desprendido de una calcita"),
                0.75, Rangos.CONDENADO4, IpMateriales.POLVO_HUESO));

        materiales.add(new IpComida(Material.DRIED_KELP, "Alga seca", List.of( // Alga
                WHITE + "No destaca por su sabor"),
                0.5, Rangos.CONDENADO4, IpMateriales.ALGA, 0.5, 0 ));

        materiales.add(new IpMaterial(Material.DRIED_KELP_BLOCK, "Bloque de algas secas", List.of(), // Alga
                0.5, Rangos.CONDENADO4, IpMateriales.ALGA_BLOQUE ));

        //infierno2
        materiales.add(new IpMaterial(Material.NETHERRACK, "Piedra infierno", Arrays.asList( // piedra infierno
                WHITE + "Piedra que huele a las cenizas",
                WHITE + " de un fuego recien apagado"),
                1.25, Rangos.CONDENADO3, IpMateriales.PIEDRA_INFIERNO));

        materiales.add(new IpMaterial(Material.CRIMSON_NYLIUM, "Piedra infierno roja", List.of( // piedra infierno roja
                WHITE + "Piedra infierno que tiene color rojo"),
                3, Rangos.CONDENADO3, IpMateriales.PIEDRA_INFIERNO_ROJA));

        materiales.add(new IpMaterial(Material.WARPED_NYLIUM, "Piedra infierno verde", List.of( // piedra infierno verde
                WHITE + "Piedra infierno que tiene color verde"),
                5, Rangos.CONDENADO3, IpMateriales.PIEDRA_INFIERNO_VERDE));

        //infierno3
        materiales.add(new IpMaterial(Material.MAGMA_BLOCK, "Bloque magmático", Arrays.asList( // bloque magmático
                WHITE + "Bloque que irradia un destello",
                WHITE + " similar al del magma"),
                6, Rangos.CONDENADO2, IpMateriales.BLOQUE_MAGMATICO));

        materiales.add(new IpComida(Material.GLOW_BERRIES, "Baya luminosa", List.of( // Baya luminosa
                WHITE + "Una fruta que desprende una luz constante"),
                6, Rangos.CONDENADO2, IpMateriales.BAYA_LUMINOSA, 0,10 ));

        materiales.add(new IpMaterial(Material.ICE, "Hielo cristalizado", List.of( // hielo cristalizado
                WHITE + "Está muy frío y puede contener peces"),
                4, Rangos.CONDENADO2, IpMateriales.HIELO));

        materiales.add(new IpFragmento(Material.BLUE_DYE, "Fragmento de esencia destructiva [1]", List.of( // fragmento azul 1
                WHITE + "Fragmento para crear una esencia destructiva"),
                5, Rangos.CONDENADO2, Enchantment.DIG_SPEED, 1, IpMateriales.FRAGMENTO_AZUL1));

        materiales.add(new IpFragmento(Material.LIGHT_BLUE_TERRACOTTA, "Esencia destructiva [1]", List.of( // esencia azul 1
                WHITE + "Esencia mágica utilizada para mejorar picos"),
               50, Rangos.CONDENADO2, Enchantment.DIG_SPEED, 1, IpMateriales.ESENCIA_AZUL1));

        materiales.add(new IpComida(Material.COD, "Pescado crudo", List.of( // Pescado crudo
                WHITE + "Se puede comer pero estaría mejor cocinado"),
                3, Rangos.CONDENADO2, IpMateriales.PESCADO_CRUDO, 1,0 ));

        materiales.add(new IpComida(Material.COOKED_COD, "Pescado a la brasa", List.of( // Pescado brasa
                WHITE + "Te recupera las fuerzas solo de olerlo"),
                6, Rangos.CONDENADO2, IpMateriales.PESCADO_BRASA, 4, 2 ));

        materiales.add(new IpComida(Material.PUFFERFISH, "Pez globo", List.of( // Pez globo
                WHITE + "Un pez mofletudo raro de encontrar"),
                14, Rangos.CONDENADO2, IpMateriales.PEZ_GLOBO, -1, 6));

        materiales.add(new IpMaterial(Material.BUBBLE_CORAL, "Elemento", List.of( // elemento
                WHITE + "Contiene el poder del fuego y hielo" ,
                WHITE + "se puede utilizar para crear armaduras"),
                31, Rangos.CONDENADO2, IpMateriales.ELEMENTO));

        //Infierno 4
        materiales.add(new IpMaterial(Material.TUFF, "Restos de basura", Arrays.asList( // restos de basura
                WHITE + "Trozo de los restos de desechos humanos,",
                WHITE + " la basura hace una curiosa forma cúbica"),
                20, Rangos.CONDENADO1, IpMateriales.RESTOS_BASURA));

        materiales.add(new IpMaterial(Material.BONE_BLOCK, "Calcita antigua", List.of(), // bloque de calcita
                0.5, Rangos.CONDENADO1, IpMateriales.CALCITA_ANTIGUA ));

        materiales.add(new IpMaterial(Material.BROWN_DYE, "Podredumbre", List.of( // podredumbre
                WHITE + "Tiene un olor... peculiar"),
                5, Rangos.CONDENADO1, IpMateriales.PODREDUMBRE));

        materiales.add(new IpComida(Material.CHICKEN, "Restos de comida", List.of( // restos comida1
                WHITE + "Lo que es basura para unos es oro para otros"),
                6, Rangos.CONDENADO1, IpMateriales.RESTOS_COMIDA1, 1.5, 0));

        materiales.add(new IpComida(Material.RABBIT, "Restos de comida", List.of( // restos comida2
                WHITE + "Lo que es basura para unos es oro para otros"),
                7, Rangos.CONDENADO1, IpMateriales.RESTOS_COMIDA2, 1.5, 0 ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_HELMET, "Casco elemental", List.of( // casco elemental
                WHITE + "Contiene el poder del hielo armadura básica",
                WHITE + "Set elemental" ),
                Rangos.CONDENADO2, IpMateriales.CASCO_ELEMENTAL,1, SetsArmadura.NULO,
                Color.fromRGB(159,251,255) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_CHESTPLATE, "Pechera elemental", List.of( // casco elemental
                WHITE + "Contiene el poder del fuego armadura básica",
                WHITE + "Set elemental" ),
                Rangos.CONDENADO2, IpMateriales.PECHERA_ELEMENTAL,2, SetsArmadura.NULO,
                Color.fromRGB(210,57,33) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_LEGGINGS, "Pantalones elementales", List.of( // casco elemental
                WHITE + "Contiene el poder del hielo armadura básica",
                WHITE + "Set elemental" ),
                Rangos.CONDENADO2, IpMateriales.PANTALONES_ELEMENTALES,2, SetsArmadura.NULO,
                Color.fromRGB(159,251,255) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_BOOTS, "Botas elementales", List.of( // casco elemental
                WHITE + "Contiene el poder del fuego armadura básica",
                WHITE + "Set elemental" ),
                Rangos.CONDENADO2, IpMateriales.BOTAS_ELEMENTALES,1, SetsArmadura.NULO,
                Color.fromRGB(210,57,33) ));

        //Afueras 1,2,3,4
        materiales.add(new IpMaterial(Material.STRIPPED_OAK_LOG, "Madera de roble", List.of( // Madera de roble
                WHITE + "Tronco de madera procedente de las afueras"),
                -1, Rangos.SINTECHO2, IpMateriales.MADERA_ROBLE));

        materiales.add(new IpMaterial(Material.OAK_PLANKS, "Tablones de roble", Arrays.asList( // Tablones de roble
                WHITE + "Tablones de madera procedentes de las afueras,",
                WHITE + " prenden que dan gusto"),
                4, Rangos.SINTECHO2, IpMateriales.TABLONES_ROBLE));

        //Afueras 1
        materiales.add(new IpHerramienta(Material.STONE_PICKAXE, "Pico resistente", List.of( // madera infierno
                WHITE + "Es algo más eficiente pero tampoco",
                WHITE + " exageradamente, al menos funciona"),
                Rangos.SINTECHO2, IpMateriales.PICO_PIEDRA, itemMeta -> itemMeta.setUnbreakable(true)));

        materiales.add(new IpMaterial(Material.STONE, "Piedra", Arrays.asList( // Piedra
                WHITE + "Trozo de piedra extraido con cuidado ",
                WHITE + " sorprende por un gran pulido"),
                30, Rangos.SINTECHO2, IpMateriales.PIEDRA));

        materiales.add(new IpMaterial(Material.COBBLESTONE, "Roca", Arrays.asList( // Roca
                WHITE + "Una gran roca que parece bastante",
                WHITE + " resistente para crear picos"),
                15, Rangos.SINTECHO2, IpMateriales.ROCA));

        materiales.add(new IpMaterial(Material.SANDSTONE, "Roca arenosa", List.of( // Roca arenosa
                WHITE + "Arena solidificada en forma de roca"),
                25, Rangos.SINTECHO2, IpMateriales.ROCA_ARENOSA));

        materiales.add(new IpMaterial(Material.SAND, "Arena", Arrays.asList( // Arena
                WHITE + "Se te mete hasta en los bolsillos y",
                WHITE + " puede llegar a resultar molesta"),
                14, Rangos.SINTECHO2, IpMateriales.ARENA));

        materiales.add(new IpMaterial(Material.GLASS_PANE, "Lente convergente", Arrays.asList( // Lente convergente
                WHITE + "Pequeño trozo de cristal útil para",
                WHITE + " construir herramientas"),
                -1, Rangos.SINTECHO2, IpMateriales.LENTE));

        materiales.add(new IpComida(Material.MELON_SLICE, "Rodaja de sandía", List.of( // Sandía rodaja
                WHITE + "Fruta que entra muy bien en verano"),
                7, Rangos.SINTECHO2, IpMateriales.RODAJA_SANDIA,1, 0 ));

        materiales.add(new IpMaterial(Material.MELON, "Sandía", List.of( // sandia
                 WHITE + "Conjunto de rodajas de sandia"),
                65, Rangos.SINTECHO2, IpMateriales.SANDIA ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_HELMET, "Casco sandia", List.of( // casco SANDIA
                WHITE + "Contiene el poder del hielo armadura básica",
                WHITE + "Set sandía entero: hambre" ),
                Rangos.SINTECHO2, IpMateriales.CASCO_SANDIA,2, SetsArmadura.SANDIA,
                Color.fromRGB(235,48,116) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_CHESTPLATE, "Pechera sandia", List.of( // casco SANDIA
                WHITE + "Contiene el poder del fuego armadura básica",
                WHITE + "Set sandía entero: hambre" ),
                Rangos.SINTECHO2, IpMateriales.PECHERA_SANDIA,2, SetsArmadura.SANDIA,
                Color.fromRGB(235,48,116) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_LEGGINGS, "Pantalones sandia", List.of( // casco SANDIA
                WHITE + "Contiene el poder del hielo armadura básica",
                WHITE + "Set sandía entero: hambre" ),
                Rangos.SINTECHO2, IpMateriales.PANTALONES_SANDIA,2, SetsArmadura.SANDIA,
                Color.fromRGB(235,48,116) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_BOOTS, "Botas sandia", List.of( // casco SANDIA
                WHITE + "Contiene el poder del fuego armadura básica",
                WHITE + "Set sandía entero: hambre" ),
                Rangos.SINTECHO2, IpMateriales.BOTAS_SANDIA,2, SetsArmadura.SANDIA,
                Color.fromRGB(235,48,116) ));

        //Afueras 2
        materiales.add(new IpFragmento(Material.RED_DYE, "Fragmento de esencia vida [1]", List.of( // fragmento rojo 1
                WHITE + "Fragmento para crear una esencia vida"),
                15, Rangos.SINTECHO1, Enchantment.LOOT_BONUS_BLOCKS, 1, IpMateriales.FRAGMENTO_ROJO1));

        materiales.add(new IpFragmento(Material.RED_TERRACOTTA, "Esencia vida [1]", List.of( // esencia rojo 1
                WHITE + "Esencia mágica utilizada para mejorar picos"),
                150, Rangos.SINTECHO1, Enchantment.LOOT_BONUS_BLOCKS, 1, IpMateriales.ESENCIA_ROJA1));

        materiales.add(new IpMaterial(Material.GRANITE_SLAB, "Granito", Arrays.asList( // Granito
                WHITE + "Roca de color rojo por recibir el intenso",
                WHITE + " sol del desierto constante"),
                6, Rangos.SINTECHO1, IpMateriales.GRANITO));

        materiales.add(new IpMaterial(Material.GRANITE, "Gran granito", Arrays.asList( // Gran granito
                WHITE + "Roca de color rojo de gran tamaño,",
                WHITE + " grangrangrangrangrangranito"),
                54, Rangos.SINTECHO1, IpMateriales.GRAN_GRANITO));

        materiales.add(new IpMaterial(Material.ALLIUM, "Flor del desierto", List.of( // DESIERTO ROJO
                WHITE + "Una pequeña flor que crece solo en oasis"),
                100, Rangos.SINTECHO1, IpMateriales.FLOR_DESIERTO));

        materiales.add(new IpMaterial(Material.FIRE_CORAL, "Desierto rojo", Arrays.asList( // DESIERTO ROJO
                WHITE + "Contiene el poder del desierto,",
                WHITE + " se puede utilizar para crear armaduras"),
                400, Rangos.SINTECHO1, IpMateriales.DESIERTO_ROJO));

        materiales.add(new IpArmaduraColor(Material.LEATHER_HELMET, "Casco desértico", List.of( // casco elemental
                WHITE + "Contiene el poder del desierto y del calor",
                WHITE + "Set desierto rojo entero: Inmunidad al fuego" ),
                Rangos.SINTECHO1, IpMateriales.CASCO_DESERTICO,3, SetsArmadura.DESIERTO_ROJO,
                Color.fromRGB(239,79,34) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_CHESTPLATE, "Pechera desértica", List.of( // casco elemental
                WHITE + "Contiene el poder del desierto y del calor",
                WHITE + "Set desierto rojo entero: Inmunidad al fuego" ),
                Rangos.SINTECHO1, IpMateriales.PECHERA_DESERTICA,4, SetsArmadura.DESIERTO_ROJO,
                Color.fromRGB(239,79,34) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_LEGGINGS, "Pantalones desertico", List.of( // casco elemental
                WHITE + "Contiene el poder del desierto y del calor",
                WHITE + "Set desierto rojo entero: Inmunidad al fuego" ),
                Rangos.SINTECHO1, IpMateriales.PANTALONES_DESERTICOS,3, SetsArmadura.DESIERTO_ROJO,
                Color.fromRGB(239,79,34) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_BOOTS, "Botas desertico", List.of( // casco elemental
                WHITE + "Contiene el poder del desierto y del calor",
                WHITE + "Set desierto rojo entero: Inmunidad al fuego" ),
                Rangos.SINTECHO1, IpMateriales.BOTAS_DESERTICAS,2, SetsArmadura.DESIERTO_ROJO,
                Color.fromRGB(239,79,34) ));

        //Extra 1
        materiales.add(new IpFragmento(Material.LIME_DYE, "Fragmento de esencia capitalista [1]", List.of( // fragmento verde 1
                WHITE + "Fragmento para crear una esencia de capitalismo"),
                17, Rangos.CAMPESINO3, Enchantment.LOOT_BONUS_MOBS, 1, IpMateriales.FRAGMENTO_VERDE1));

        materiales.add(new IpFragmento(Material.LIME_TERRACOTTA, "Esencia capitalista [1]", List.of( // esencia verde 1
                WHITE + "Esencia mágica utilizada para mejorar picos"),
                170, Rangos.CAMPESINO3, Enchantment.LOOT_BONUS_MOBS, 1, IpMateriales.ESENCIA_VERDE1));

        materiales.add(new IpFragmento(Material.CYAN_DYE, "Fragmento de esencia destructiva [2]", List.of( // fragmento azul 2
                WHITE + "Fragmento para crear una esencia de destrucción"),
                20, Rangos.CAMPESINO3, Enchantment.DIG_SPEED, 2, IpMateriales.FRAGMENTO_AZUL2));

        materiales.add(new IpFragmento(Material.LIGHT_BLUE_CONCRETE, "Esencia destructiva [2]", List.of( // esencia azul 2
                WHITE + "Esencia mágica utilizada para mejorar picos"),
                200, Rangos.CAMPESINO3, Enchantment.DIG_SPEED, 2, IpMateriales.ESENCIA_AZUL2));

        //Afueras3
        materiales.add(new IpHerramienta(Material.SPYGLASS, "Catalejo pirata", List.of( // madera infierno
                WHITE + "Utensilio que puede ser utilizado para",
                WHITE + " ver lejos y sobretodo crafteos"),
                Rangos.CAMPESINO2, IpMateriales.CATALEJO, itemMeta -> {} ));

        materiales.add(new IpMaterial(Material.SMOOTH_SANDSTONE, "Arenisca", Arrays.asList( // Arenisca
                WHITE + "Roca de color similar al de la arena",
                WHITE + " camuflándola entre ella"),
                20, Rangos.CAMPESINO2, IpMateriales.ARENISCA));

        materiales.add(new IpMaterial(Material.SMOOTH_QUARTZ_SLAB, "Trozo de marmol", Arrays.asList( // trozo marmol
                WHITE + "Mineral duro y blanco ",
                WHITE + " en zonas con mucha arena"),
                70, Rangos.CAMPESINO2, IpMateriales.TROZO_MARMOL));

        materiales.add(new IpMaterial(Material.SMOOTH_QUARTZ_SLAB, "Marmol", Arrays.asList( // marmol
                WHITE + "Piedra blanca brillante encontrada",
                WHITE + " en zonas con mucha arena"),
                45, Rangos.CAMPESINO2, IpMateriales.MARMOL));

        materiales.add(new IpMaterial(Material.SMOOTH_QUARTZ, "Cuarzo", Arrays.asList( // Cuarzo
                WHITE + "Piedra blanca impoluta encontrada",
                WHITE + " en zonas con mucha arena"),
                30, Rangos.CAMPESINO2, IpMateriales.CUARZO));

        materiales.add(new IpMaterial(Material.HAY_BLOCK, "Bala de trigo", List.of(), // bala trigo
                0.5, Rangos.CAMPESINO2, IpMateriales.BALA_TRIGO ));

        materiales.add(new IpMaterial(Material.WHEAT, "Trigo", List.of( // Trigo
                WHITE + "Todo un manjar (si fueras una vaca)"),
                15, Rangos.CAMPESINO2, IpMateriales.TRIGO));

        materiales.add(new IpComida(Material.BREAD, "Pan de chapata", List.of( // pan
                WHITE + "Recien horneado y calentito"),
                37, Rangos.CONDENADO2, IpMateriales.PAN,3,6  ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_HELMET, "Casco de cuarzo", List.of( // casco elemental
                WHITE + "Armadura utilizada por nobles, perfecta",
                WHITE + " para llamar la atención.",
                WHITE + "Set cuarzo: brillo" ),
                Rangos.SINTECHO1, IpMateriales.CASCO_CUARZO,3, SetsArmadura.CUARZO,
                Color.fromRGB(253,252,230) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_CHESTPLATE, "Pechera de cuarzo", List.of( // casco elemental
                WHITE + "Armadura utilizada por nobles, perfecta",
                WHITE + " para llamar la atención.",
                WHITE + "Set cuarzo: brillo" ),
                Rangos.SINTECHO1, IpMateriales.PECHERA_CUARZO,4, SetsArmadura.CUARZO,
                Color.fromRGB(253,252,230) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_LEGGINGS, "Pantalones de cuarzo", List.of( // casco elemental
                WHITE + "Armadura utilizada por nobles, perfecta",
                WHITE + " para llamar la atención.",
                WHITE + "Set cuarzo: brillo" ),
                Rangos.SINTECHO1, IpMateriales.PANTALONES_CUARZO,4, SetsArmadura.CUARZO,
                Color.fromRGB(253,252,230) ));

        materiales.add(new IpArmaduraColor(Material.LEATHER_BOOTS, "Botas de cuarzo", List.of( // casco elemental
                WHITE + "Armadura utilizada por nobles, perfecta",
                WHITE + "para llamar la atención.",
                WHITE + "Set cuarzo: brillo" ),
                Rangos.SINTECHO1, IpMateriales.BOTAS_CUARZO,3, SetsArmadura.CUARZO,
                Color.fromRGB(253,252,230) ));

        //Afueras4
        materiales.add(new IpMaterial(Material.COBBLESTONE_SLAB, "Mini roca", Arrays.asList( // Mini roca
                WHITE + "Roca de tamaño pequeño, tranquilo",
                WHITE + " el tamaño no importa"),
                7, Rangos.CAMPESINO1, IpMateriales.MINI_ROCA));

        materiales.add(new IpMaterial(Material.COAL_ORE, "Mena de carbón", List.of(), // Mena carbon
                0.5, Rangos.CAMPESINO1, IpMateriales.MENA_CARBON ));

        materiales.add(new IpMaterial(Material.COAL, "Carbón", List.of( // Carbón
                WHITE + "Mineral negro que arde con facilidad"),
                80, Rangos.CAMPESINO1, IpMateriales.CARBON));

        materiales.add(new IpFragmento(Material.YELLOW_DYE, "Fragmento de esencia de sabiduría [1]", List.of( // fragmento amarilla 1
                WHITE + "Fragmento para crear una esencia de sabiduría"),
                18, Rangos.CAMPESINO1, Enchantment.CHANNELING, 1, IpMateriales.FRAGMENTO_AMARILLO1));

        materiales.add(new IpFragmento(Material.YELLOW_TERRACOTTA, "Esencia de sabiduría [1]", List.of( // esencia amarilla 1
                WHITE + "Esencia mágica utilizada para mejorar picos"),
                180, Rangos.CAMPESINO1, Enchantment.CHANNELING, 1, IpMateriales.ESENCIA_AMARILLA1));

        materiales.add(new IpHerramienta(Material.SPYGLASS, "Detector de bloques [1]", List.of( // madera infierno
                WHITE + "Click derecho a un bloque para ver información",
                WHITE + " del mismo, no se pierde al renacer",
                WHITE + "Distancia máxima: 8"),
                Rangos.CAMPESINO2, IpMateriales.DETECTOR1, itemMeta -> {
                itemMeta.setDisplayName(BOLD + String.valueOf(YELLOW) + "Detector de bloques [1]");
                itemMeta.addEnchant(Enchantment.CHANNELING,1,true);
        } ));
        //Pueblo1
        materiales.add(new IpMaterial(Material.PACKED_ICE, "Hielo compactado", List.of( // hielo cristalizado
                WHITE + "Agua muy fría y puede contener peces"),
                50, Rangos.MINERO3, IpMateriales.HIELO_COMPACTO));

        materiales.add(new IpComida(Material.SALMON, "Salmon crudo", Arrays.asList( // Pescado crudo
                WHITE + "Se puede comer pero estaría mejor cocinado",
                WHITE + "Al comer: "+ RED +"+2"+ WHITE +" vida"),
                20, Rangos.MINERO3, IpMateriales.SALMON_CRUDO, 2,0 ));

        materiales.add(new IpComida(Material.COOKED_SALMON, "Salmon ahumado", List.of( // Pescado brasa
                WHITE + "Huele mal pero tiene nutrientes"),
                40, Rangos.MINERO3, IpMateriales.SALMON_AHUMADO, 4, 8));

        materiales.add(new IpMaterial(Material.IRON_ORE, "Mena de hierro", List.of(
                WHITE + "Se puede fundir para conseguir hierro",
                WHITE + " un mineral bastante resistente"
                ), // Mena hierro
                100, Rangos.MINERO3, IpMateriales.MENA_HIERRO ));

        materiales.add(new IpMaterial(Material.IRON_INGOT, "Lingote de hierro", List.of( // hierro
                WHITE + "Es duro y resistente, es bueno para crear",
                WHITE + " herramientas y armaduras."),
                180, Rangos.MINERO3, IpMateriales.HIERRO));

        materiales.add(new IpArmadura(Material.IRON_HELMET, "Casco metálico", List.of( // casco elemental
                WHITE + "Contiene el poder del desierto y del calor",
                WHITE + "Set hierro" ),
                Rangos.MINERO3, IpMateriales.CASCO_HIERRO,3, SetsArmadura.NULO));

        materiales.add(new IpArmadura(Material.IRON_CHESTPLATE, "Pechera metálico", List.of( // casco elemental
                WHITE + "Contiene el poder del desierto y del calor",
                WHITE + "Set hierro" ),
                Rangos.MINERO3, IpMateriales.PECHERA_HIERRO,4, SetsArmadura.NULO ));

        materiales.add(new IpArmadura(Material.IRON_LEGGINGS, "Pantalones metálicos", List.of( // casco elemental
                WHITE + "Contiene el poder del desierto y del calor",
                WHITE + "Set hierro" ),
                Rangos.MINERO3, IpMateriales.PANTALONES_HIERRO,4, SetsArmadura.NULO ));

        materiales.add(new IpArmadura(Material.IRON_BOOTS, "Botas metálicas", List.of( // casco elemental
                WHITE + "Contiene el poder del desierto y del calor",
                WHITE + "Set hierro" ),
                Rangos.MINERO3, IpMateriales.BOTAS_HIERRO,3, SetsArmadura.NULO ));


        materiales.add(new IpMaterial(Material.SPRUCE_LEAVES, "Arbusto", List.of(), // arbusto
                180, Rangos.MINERO3, IpMateriales.ARBUSTO));

        materiales.add(new IpMaterial(Material.LARGE_FERN, "Hojas", List.of( // hojas
                WHITE + "Mineral negro que arde con facilidad"),
                40, Rangos.MINERO3, IpMateriales.HOJAS));

        materiales.add(new IpComida(Material.APPLE, "Manzana", List.of( // manzana
                WHITE + "No te la comas si te lo pide una serpiente"),
                75, Rangos.MINERO3, IpMateriales.MANZANA, 4,0 ));

        materiales.add(new IpMaterial(Material.DIORITE_SLAB, "Diorita", Arrays.asList( // Granito
                WHITE + "Roca de color blanco por recibir el intenso",
                WHITE + " frio de la nieve constante"),
                60, Rangos.MINERO3, IpMateriales.DIORITA));

        materiales.add(new IpMaterial(Material.DIORITE, "Gran diorita", Arrays.asList( // Gran granito
                WHITE + "Roca de color rojo de gran tamaño,",
                WHITE + " grangrangrangrangrangranito"),
                540, Rangos.MINERO3, IpMateriales.GRAN_DIORITA));

        materiales.add(new IpMaterial(Material.SNOW_BLOCK, "Nieve", Arrays.asList( // nieve
                WHITE + "Nieve blanca como la nieve",
                WHITE + " que casualidad"),
                500, Rangos.MINERO3, IpMateriales.NIEVE));

        //Pueblo2
        materiales.add(new IpMaterial(Material.DEEPSLATE_COAL_ORE, "Mena de carbón oscura", List.of(), // Mena carbon oscura
                0.5, Rangos.MINERO2, IpMateriales.MENA_CARBON_OSCURA ));

        materiales.add(new IpMaterial(Material.AMETHYST_BLOCK, "Amatista", List.of(), // Mena carbon oscura
                0.5, Rangos.MINERO2, IpMateriales.AMATISTA ));

        materiales.add(new IpMaterial(Material.REDSTONE_ORE, "Mena de stylium", List.of( // Mena stylum
                WHITE + "Se puede fundir para conseguir stylum",
                WHITE + " un mineral magico usado en tecnología antigua"),
                140, Rangos.MINERO2, IpMateriales.MENA_STYLUM ));

        materiales.add(new IpMaterial(Material.REDSTONE, "Stylium", List.of( // stylum
                WHITE + "Mineral usado en tecnología antigua para ",
                WHITE + " transmitir energía a mecanismos extraños"),
                205, Rangos.MINERO2, IpMateriales.STYLUM));

        materiales.add(new IpMaterial(Material.END_STONE, "Piedra lunar", List.of( // Mena stylum
                WHITE + "Piedra amarillenta que brilla con ",
                WHITE + " la luz del sol"),
                500, Rangos.MINERO2, IpMateriales.PIEDRA_LUNAR ));

        materiales.add(new IpMaterial(Material.AMETHYST_CLUSTER, "Cristal de amatista", List.of( // Mena stylum
                WHITE + "Fragmento de un mineral cristalizado"),
                27, Rangos.MINERO2, IpMateriales.AMATISTA_CRISTAL ));

        //extra2
        materiales.add(new IpFragmento(Material.RED_DYE, "Fragmento de esencia vida [2]", List.of( // fragmento rojo 2
                WHITE + "Fragmento para crear una esencia vida"),
                100, Rangos.MINERO1, Enchantment.LOOT_BONUS_BLOCKS, 2, IpMateriales.FRAGMENTO_ROJO2));
        materiales.add(new IpFragmento(Material.RED_CONCRETE, "Esencia vida [2]", List.of( // esencia rojo 2
                WHITE + "Esencia mágica utilizada para mejorar picos"),
                1000, Rangos.MINERO1, Enchantment.LOOT_BONUS_BLOCKS, 2, IpMateriales.ESENCIA_ROJA2));

        materiales.add(new IpFragmento(Material.LIME_DYE, "Fragmento de esencia capitalista [2]", List.of( // fragmento verde 2
                WHITE + "Fragmento para crear una esencia de capitalismo"),
                130, Rangos.MINERO1, Enchantment.LOOT_BONUS_MOBS, 2, IpMateriales.FRAGMENTO_VERDE2));

        materiales.add(new IpFragmento(Material.LIME_CONCRETE, "Esencia capitalista [2]", List.of( // esencia verde 2
                WHITE + "Esencia mágica utilizada para mejorar picos"),
                1300, Rangos.MINERO1, Enchantment.LOOT_BONUS_MOBS, 2, IpMateriales.ESENCIA_VERDE2));

        materiales.add(new IpFragmento(Material.BLACK_DYE, "Fragmento de esencia oscura [1]", List.of( // fragmento oscuro 1
                WHITE + "Fragmento para crear una esencia de oscuridad"),
                150, Rangos.MINERO1, Enchantment.MULTISHOT, 1, IpMateriales.FRAGMENTO_OSCURO1));

        materiales.add(new IpFragmento(Material.BLACK_CONCRETE, "Esencia oscura [1]", List.of( // esencia oscura 1
                WHITE + "Esencia oscura utilizada para mejorar picos"),
                1500, Rangos.MINERO1, Enchantment.MULTISHOT, 1, IpMateriales.ESENCIA_OSCURA1));


    }

    public static IpMaterial getIpMaterial(IpMateriales ipMateriales){
        if (ipMateriales == null) return null;
        for (IpMaterial m: materiales) {
            if (m.isNombre(ipMateriales)) return m;
        }
        return null;
    }

    public static ItemStack getItem(IpMateriales ipMateriales, int n){
        IpMaterial m = getIpMaterial(ipMateriales);

        return m.getItem(n);
    }

    public static ItemStack getItem(IpMateriales ipMateriales){
        if(ipMateriales.equals(IpMateriales.AIRE)) return new ItemStack(Material.BARRIER);
        return getItem(ipMateriales,1);
    }

    public static boolean comparar(ItemStack item1, ItemStack item2){ // true == son iguales
        if(item1 == null && item2 == null) return true;
        if(item1 == null || item2 == null) return false;
        if(item1.getItemMeta() == null || item2.getItemMeta() == null) return false;
        return item1.getItemMeta().equals(item2.getItemMeta());
    }

    public static IpMaterial itemToMaterial(ItemStack itemStack) {
        if (itemStack == null) return materiales.getFirst();
        for (IpMaterial m: materiales) {
            if(m.getClass().equals(IpFragmento.class)) {
                IpFragmento fragmento = (IpFragmento) m;
                if (fragmento.getNivel() != itemStack.getEnchantmentLevel(fragmento.getEnchant()))  continue;
            }
            if (Objects.requireNonNull(itemStack.getItemMeta()).equals(m.getItem(1).getItemMeta())) return m;
        }
        return materiales.getFirst();
    }

    public static IpMaterial blockToMaterial(Material material) {
        for (IpMaterial m: materiales) {
          if(material == m.getMaterial()) return m;
        }
        return materiales.getFirst();
    }

    public LinkedList<IpMaterial> getMateriales() {
        return materiales;
    }

    public int getVidaMax(ItemStack[] armor){
        int vida = 0;
        for (ItemStack i :armor) {
            IpMaterial ipMaterial = itemToMaterial(i);
            if(ipMaterial == null) continue;
            if(ipMaterial.getClass().equals(IpArmadura.class) || ipMaterial.getClass().equals(IpArmaduraColor.class)){
                IpArmadura armadura = (IpArmadura) ipMaterial;
                vida += armadura.getVida();
            }
        }
        return vida;
    }

    public PotionEffect getEfecto(ItemStack[] armor){
        SetsArmadura set = null;
        for (ItemStack i :armor) {
            if (i == null) return null;
            IpMaterial ipMaterial = itemToMaterial(i);
            if(ipMaterial == null) return null;
            if(ipMaterial.getClass().equals(IpArmadura.class) || ipMaterial.getClass().equals(IpArmaduraColor.class)){
                IpArmadura armadura = (IpArmadura) ipMaterial;
                if(armadura.getSetArmadura() == null) return null;
                if (set != armadura.getSetArmadura() && set != null) return null;
                set = armadura.getSetArmadura();
            }
        }
        if(set == null) return null;
        return set.getEfecto();
    }

}
