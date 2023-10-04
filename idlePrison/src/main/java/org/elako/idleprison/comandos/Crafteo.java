package org.elako.idleprison.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.items.MaterialesManager;
import org.elako.idleprison.items.PicosManager;
import org.elako.idleprison.player.Rangos;
import org.elako.idleprison.player.RangosManager;

import java.util.Arrays;
import java.util.LinkedList;

public class Crafteo implements CommandExecutor {
    private static MaterialesManager materialesManager;
    private static RangosManager rangosManager;

    public Crafteo(MaterialesManager materiales, RangosManager rango) {
        materialesManager = materiales;
        rangosManager = rango;
    }

    public static Inventory crearInventario(Player p) {
        // tamaños inventarios: 9 18 27 36 45 54

        Inventory inventario = Bukkit.createInventory(p, 27, ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "Craftear");
        inventario.setItem(11, Idleprison.crearObjeto(Material.CRAFTING_TABLE,ChatColor.WHITE +"Craftear"));
        inventario.setItem(15, Idleprison.crearObjeto(Material.BOOK,ChatColor.WHITE +"Craftguide"));

        inventario.setItem(26, Idleprison.crearObjetoLore(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR", Arrays.asList(
                ChatColor.WHITE + "Botón para volver al menú"  ) ));

        return inventario;
    }

    public static Inventory crearCraftGuide(Player p) {
        ItemStack picoAzul1 = Idleprison.crearObjeto(Material.WOODEN_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico destructivo 1");
        ItemStack picoRojo1 = Idleprison.crearObjeto(Material.STONE_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico vivo 1");
        ItemStack picoVerde1 = Idleprison.crearObjeto(Material.WOODEN_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico capitalista 1");
        ItemStack picoAzul2 = Idleprison.crearObjeto(Material.STONE_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico destructivo 2");


        ItemMeta picoMeta = picoAzul1.getItemMeta();
        picoMeta.addEnchant(Enchantment.DIG_SPEED,1,true);
        picoAzul1.setItemMeta(picoMeta);

        ItemMeta picoMeta2 = picoRojo1.getItemMeta();
        picoMeta2.addEnchant(Enchantment.LOOT_BONUS_BLOCKS,1,true);
        picoRojo1.setItemMeta(picoMeta2);

        ItemMeta picoMeta3 = picoVerde1.getItemMeta();
        picoMeta3.addEnchant(Enchantment.LOOT_BONUS_MOBS,1,true);
        picoVerde1.setItemMeta(picoMeta3);

        ItemMeta picoMeta4 = picoAzul2.getItemMeta();
        picoMeta4.addEnchant(Enchantment.DIG_SPEED,2,true);
        picoAzul2.setItemMeta(picoMeta4);

        Inventory inventario = Bukkit.createInventory(p, 27, ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "CraftGuide");
        inventario.setItem(0, materialesManager.getItem(IpMateriales.TABLONES_QUEMADOS));
        if (rangosManager.isPermitido(p.getName(), Rangos.CONDENADO3)) inventario.setItem(1, PicosManager.getPicoMadera());
        if (rangosManager.isPermitido(p.getName(), Rangos.CONDENADO2)) inventario.setItem(2, materialesManager.getItem(IpMateriales.PESCADO_BRASA));
        if (rangosManager.isPermitido(p.getName(), Rangos.CONDENADO2)) inventario.setItem(3, materialesManager.getItem(IpMateriales.ESENCIA_AZUL1));
        if (rangosManager.isPermitido(p.getName(), Rangos.CONDENADO2)) inventario.setItem(4, picoAzul1);
        if (rangosManager.isPermitido(p.getName(), Rangos.SINTECHO2)) inventario.setItem(5, materialesManager.getItem(IpMateriales.TABLONES_ROBLE));
        if (rangosManager.isPermitido(p.getName(), Rangos.SINTECHO2)) inventario.setItem(6, PicosManager.getPicoPiedra());
        if (rangosManager.isPermitido(p.getName(), Rangos.SINTECHO2)) inventario.setItem(7, materialesManager.getItem(IpMateriales.LENTE));
        if (rangosManager.isPermitido(p.getName(), Rangos.SINTECHO1)) inventario.setItem(8, materialesManager.getItem(IpMateriales.ESENCIA_ROJA1));
        if (rangosManager.isPermitido(p.getName(), Rangos.SINTECHO1)) inventario.setItem(9, picoRojo1);
        if (rangosManager.isPermitido(p.getName(), Rangos.SINTECHO1)) inventario.setItem(10, materialesManager.getItem(IpMateriales.GRAN_GRANITO));
        if (rangosManager.isPermitido(p.getName(), Rangos.CAMPESINO3)) inventario.setItem(11, materialesManager.getItem(IpMateriales.ESENCIA_VERDE1));
        if (rangosManager.isPermitido(p.getName(), Rangos.CAMPESINO3)) inventario.setItem(12, picoVerde1);
        if (rangosManager.isPermitido(p.getName(), Rangos.CAMPESINO3)) inventario.setItem(13, materialesManager.getItem(IpMateriales.ESENCIA_AZUL1));
        if (rangosManager.isPermitido(p.getName(), Rangos.CAMPESINO3)) inventario.setItem(14, picoAzul2);
        if (rangosManager.isPermitido(p.getName(), Rangos.CAMPESINO2)) inventario.setItem(15, materialesManager.getItem(IpMateriales.PAN));
        if (rangosManager.isPermitido(p.getName(), Rangos.CAMPESINO2)) inventario.setItem(16, PicosManager.getCatalejo());
        if (rangosManager.isPermitido(p.getName(), Rangos.CAMPESINO1)) inventario.setItem(17, materialesManager.getItem(IpMateriales.ESENCIA_AMARILLA1) );
        if (rangosManager.isPermitido(p.getName(), Rangos.CAMPESINO1)) inventario.setItem(18, PicosManager.getDetectorBloques1());

        inventario.setItem(26, Idleprison.crearObjetoLore(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR", Arrays.asList(
                ChatColor.WHITE + "Botón para volver al menú"  ) ));

        return inventario;
    }

    public static Inventory guiaCrafteoSimple(Player p, LinkedList<ItemStack> items){
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
                case 15:
                case 20:
                case 21:
                case 22:
                    inventario.setItem(i, items.pop());
                    break;
                case 26:
                    inventario.setItem(i, Idleprison.crearObjeto(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR"));
                    break;
                default:
                    inventario.setItem(i,  Idleprison.crearObjeto(Material.BLACK_STAINED_GLASS_PANE," "));
            }
        }
        return inventario;
    }

    public static Inventory guiaCrafteoDoble(Player p, LinkedList<ItemStack> items){
        if (items.size() != 20) p.sendMessage("ERROR");
        Inventory inventario = Bukkit.createInventory(p, 54, ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Crafteo");
        for (int i=0;i<54;i++) {
            switch (i){
                case 2:
                case 3:
                case 4:
                case 11:
                case 12:
                case 13:
                case 15:
                case 20:
                case 21:
                case 22:
                case 32:
                case 33:
                case 34:
                case 39:
                case 41:
                case 42:
                case 43:
                case 50:
                case 51:
                case 52:
                    inventario.setItem(i, items.pop());
                    break;
                case 45:
                    inventario.setItem(i, Idleprison.crearObjeto(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR"));
                    break;
                default:
                    if (i<27) inventario.setItem(i,  Idleprison.crearObjeto(Material.BLACK_STAINED_GLASS_PANE," "));
                    else inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
            }
        }
        return inventario;
    }

    public static Inventory guiaCrafteoPicoMadera(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack air = new ItemStack(Material.AIR);
        ItemStack tablones = materialesManager.getItem(IpMateriales.TABLONES_QUEMADOS);

        items.add(tablones);  // primera linea
        items.add(tablones);
        items.add(tablones);
        items.add(air);       // segunda linea
        items.add(tablones);
        items.add(air);
        items.add(PicosManager.getPicoMadera()); //resultado
        items.add(air);       // tercera linea
        items.add(tablones);
        items.add(air);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoTablonesInfierno(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack air = new ItemStack(Material.AIR);
        //primer crafteo
        items.add(air);  // primera linea
        items.add(air);
        items.add(air);
        items.add(air);       // segunda linea
        items.add(materialesManager.getItem(IpMateriales.MADERA_INFIERNO));
        items.add(air);
        items.add(materialesManager.getItem(IpMateriales.TABLONES_QUEMADOS,7)); //resultado
        items.add(air);       // tercera linea
        items.add(air);
        items.add(air);
        //segundo crafteo
        items.add(air);     // primera linea
        items.add(air);
        items.add(air);
        items.add(materialesManager.getItem(IpMateriales.TABLONES_QUEMADOS,15)); //resultado
        items.add(air);       // segunda linea
        items.add(materialesManager.getItem(IpMateriales.MADERA_INFIERNO_CALIDAD));
        items.add(air);
        items.add(air);       // tercera linea
        items.add(air);
        items.add(air);
        return guiaCrafteoDoble(p,items);
    }

    public static Inventory guiaCrafteoPescadoBrasa(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack air = new ItemStack(Material.AIR);

        items.add(air);  // primera linea
        items.add(air);
        items.add(air);
        items.add(air);       // segunda linea
        items.add(materialesManager.getItem(IpMateriales.PESCADO_CRUDO));
        items.add(air);
        items.add(materialesManager.getItem(IpMateriales.PESCADO_BRASA)); //resultado
        items.add(air);       // tercera linea
        items.add(materialesManager.getItem(IpMateriales.TABLONES_QUEMADOS));
        items.add(air);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoEsenciaAzul1(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack fragmento = materialesManager.getItem(IpMateriales.FRAGMENTO_AZUL1);
        items.add(fragmento); // primera linea
        items.add(fragmento);
        items.add(fragmento);
        items.add(fragmento); // segunda linea
        items.add(fragmento);
        items.add(fragmento);
        items.add(materialesManager.getItem(IpMateriales.ESENCIA_AZUL1)); //resultado
        items.add(fragmento); // tercera linea
        items.add(fragmento);
        items.add(fragmento);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoPicoAzul1(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack pico = Idleprison.crearObjeto(Material.WOODEN_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico Cualquiera");
        ItemStack pico2 = Idleprison.crearObjeto(Material.WOODEN_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico Cualquiera");
        ItemMeta picoMeta = pico2.getItemMeta();
        picoMeta.addEnchant(Enchantment.DIG_SPEED,1,true);
        pico2.setItemMeta(picoMeta);

        ItemStack air = new ItemStack(Material.AIR);
        items.add(air);   // primera linea
        items.add(pico);
        items.add(air);
        items.add(air); // segunda linea
        items.add(air);
        items.add(air);
        items.add(pico2); //resultado
        items.add(air); // tercera linea
        items.add(materialesManager.getItem(IpMateriales.ESENCIA_AZUL1));
        items.add(air);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoLentes(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack arena = materialesManager.getItem(IpMateriales.ARENA);
        //primer crafteo
        items.add(arena);  // primera linea
        items.add(arena);
        items.add(arena);
        items.add(materialesManager.getItem(IpMateriales.MADERA_INFIERNO_CALIDAD)); // segunda linea
        items.add(arena);
        items.add(materialesManager.getItem(IpMateriales.BLOQUE_MAGMATICO));
        items.add(materialesManager.getItem(IpMateriales.LENTE)); //resultado
        items.add(arena);       // tercera linea
        items.add(arena);
        items.add(arena);
        //segundo crafteo
        items.add(arena);  // primera linea
        items.add(arena);
        items.add(arena);
        items.add(materialesManager.getItem(IpMateriales.LENTE)); //resultado
        items.add(materialesManager.getItem(IpMateriales.MADERA_ROBLE)); // segunda linea
        items.add(arena);
        items.add(materialesManager.getItem(IpMateriales.BLOQUE_MAGMATICO));
        items.add(arena);       // tercera linea
        items.add(arena);
        items.add(arena);
        return guiaCrafteoDoble(p,items);
    }

    public static Inventory guiaCrafteoCatalejo(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack air = new ItemStack(Material.AIR);
        ItemStack madera = materialesManager.getItem(IpMateriales.TABLONES_ROBLE);
        ItemStack lente = materialesManager.getItem(IpMateriales.LENTE);

        items.add(air);   // primera linea
        items.add(air);
        items.add(lente);
        items.add(madera); // segunda linea
        items.add(madera);
        items.add(lente);
        items.add(PicosManager.getCatalejo()); //resultado
        items.add(air); // tercera linea
        items.add(air);
        items.add(air);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoPicoPiedra(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack air = new ItemStack(Material.AIR);
        ItemStack tablones = materialesManager.getItem(IpMateriales.TABLONES_ROBLE);
        ItemStack roca = materialesManager.getItem(IpMateriales.ROCA);

        items.add(roca);  // primera linea
        items.add(roca);
        items.add(roca);
        items.add(air);       // segunda linea
        items.add(tablones);
        items.add(air);
        items.add(PicosManager.getPicoPiedra()); //resultado
        items.add(air);       // tercera linea
        items.add(tablones);
        items.add(air);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoTablonesRoble(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack air = new ItemStack(Material.AIR);
        ItemStack madera = materialesManager.getItem(IpMateriales.MADERA_ROBLE);
        //primer crafteo
        items.add(air);  // primera linea
        items.add(air);
        items.add(air);
        items.add(air);       // segunda linea
        items.add(madera);
        items.add(air);
        items.add(materialesManager.getItem(IpMateriales.TABLONES_ROBLE,5)); //resultado
        items.add(air);       // tercera linea
        items.add(air);
        items.add(air);
        //segundo crafteo
        items.add(air);     // primera linea
        items.add(madera);
        items.add(air);
        items.add(materialesManager.getItem(IpMateriales.TABLONES_ROBLE,17)); //resultado
        items.add(air);       // segunda linea
        items.add(madera);
        items.add(air);
        items.add(air);       // tercera linea
        items.add(madera);
        items.add(air);
        return guiaCrafteoDoble(p,items);
    }

    public static Inventory guiaCrafteoEsenciaRoja1(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack fragmento = materialesManager.getItem(IpMateriales.FRAGMENTO_ROJO1);
        items.add(fragmento); // primera linea
        items.add(fragmento);
        items.add(fragmento);
        items.add(fragmento); // segunda linea
        items.add(fragmento);
        items.add(fragmento);
        items.add(materialesManager.getItem(IpMateriales.ESENCIA_ROJA1)); //resultado
        items.add(fragmento); // tercera linea
        items.add(fragmento);
        items.add(fragmento);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoPicoRojo1(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack pico = Idleprison.crearObjeto(Material.STONE_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico Cualquiera");
        ItemStack pico2 = Idleprison.crearObjeto(Material.STONE_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico Cualquiera");
        ItemMeta picoMeta = pico2.getItemMeta();
        picoMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS,1,true);
        pico2.setItemMeta(picoMeta);

        ItemStack air = new ItemStack(Material.AIR);
        items.add(air);   // primera linea
        items.add(pico);
        items.add(air);
        items.add(air); // segunda linea
        items.add(air);
        items.add(air);
        items.add(pico2); //resultado
        items.add(air); // tercera linea
        items.add(materialesManager.getItem(IpMateriales.ESENCIA_ROJA1));
        items.add(air);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoGranGranito(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack granito = materialesManager.getItem(IpMateriales.GRANITO);

        //primer crafteo
        items.add(granito);  // primera linea
        items.add(granito);
        items.add(granito);
        items.add(granito);       // segunda linea
        items.add(granito);
        items.add(granito);
        items.add(materialesManager.getItem(IpMateriales.GRAN_GRANITO)); //resultado
        items.add(granito);       // tercera linea
        items.add(granito);
        items.add(granito);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoEsenciaVerde1(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack fragmento = materialesManager.getItem(IpMateriales.FRAGMENTO_VERDE1);
        items.add(fragmento); // primera linea
        items.add(fragmento);
        items.add(fragmento);
        items.add(fragmento); // segunda linea
        items.add(fragmento);
        items.add(fragmento);
        items.add(materialesManager.getItem(IpMateriales.ESENCIA_VERDE1)); //resultado
        items.add(fragmento); // tercera linea
        items.add(fragmento);
        items.add(fragmento);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoPicoVerde1(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack pico = Idleprison.crearObjeto(Material.WOODEN_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico Cualquiera");
        ItemStack pico2 = Idleprison.crearObjeto(Material.WOODEN_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico Cualquiera");
        ItemMeta picoMeta = pico2.getItemMeta();
        picoMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS,1,true);
        pico2.setItemMeta(picoMeta);

        ItemStack air = new ItemStack(Material.AIR);
        items.add(air);   // primera linea
        items.add(pico);
        items.add(air);
        items.add(air); // segunda linea
        items.add(air);
        items.add(air);
        items.add(pico2); //resultado
        items.add(air); // tercera linea
        items.add(materialesManager.getItem(IpMateriales.ESENCIA_VERDE1));
        items.add(air);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoEsenciaAzul2(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack fragmento = materialesManager.getItem(IpMateriales.FRAGMENTO_AZUL2);
        items.add(fragmento); // primera linea
        items.add(fragmento);
        items.add(fragmento);
        items.add(fragmento); // segunda linea
        items.add(fragmento);
        items.add(fragmento);
        items.add(materialesManager.getItem(IpMateriales.ESENCIA_AZUL2)); //resultado
        items.add(fragmento); // tercera linea
        items.add(fragmento);
        items.add(fragmento);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoPicoAzul2(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack pico = Idleprison.crearObjeto(Material.STONE_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico Cualquiera");
        ItemStack pico2 = Idleprison.crearObjeto(Material.STONE_PICKAXE,ChatColor.WHITE + "" + ChatColor.BOLD + "Pico Cualquiera");
        ItemMeta picoMeta = pico2.getItemMeta();
        picoMeta.addEnchant(Enchantment.DIG_SPEED,2,true);
        pico2.setItemMeta(picoMeta);

        ItemStack air = new ItemStack(Material.AIR);
        items.add(air);   // primera linea
        items.add(pico);
        items.add(air);
        items.add(air); // segunda linea
        items.add(air);
        items.add(air);
        items.add(pico2); //resultado
        items.add(air); // tercera linea
        items.add(materialesManager.getItem(IpMateriales.ESENCIA_AZUL2));
        items.add(air);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoPan(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack air = new ItemStack(Material.AIR);
        ItemStack trigo = materialesManager.getItem(IpMateriales.TRIGO);

        items.add(air);  // primera linea
        items.add(air);
        items.add(air);
        items.add(trigo);       // segunda linea
        items.add(trigo);
        items.add(trigo);
        items.add(materialesManager.getItem(IpMateriales.PAN,2)); //resultado
        items.add(air);       // tercera linea
        items.add(materialesManager.getItem(IpMateriales.TABLONES_ROBLE));
        items.add(air);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoEsenciaAmarilla1(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();
        ItemStack fragmento = materialesManager.getItem(IpMateriales.FRAGMENTO_AMARILLO1);
        items.add(fragmento); // primera linea
        items.add(fragmento);
        items.add(fragmento);
        items.add(fragmento); // segunda linea
        items.add(fragmento);
        items.add(fragmento);
        items.add(materialesManager.getItem(IpMateriales.ESENCIA_AMARILLA1)); //resultado
        items.add(fragmento); // tercera linea
        items.add(fragmento);
        items.add(fragmento);
        return guiaCrafteoSimple(p,items);
    }

    public static Inventory guiaCrafteoCatalejo1(Player p){
        LinkedList<ItemStack> items = new LinkedList<>();

        ItemStack air = new ItemStack(Material.AIR);
        items.add(air);   // primera linea
        items.add(PicosManager.getCatalejo());
        items.add(air);
        items.add(air); // segunda linea
        items.add(air);
        items.add(air);
        items.add(PicosManager.getDetectorBloques1()); //resultado
        items.add(air); // tercera linea
        items.add(materialesManager.getItem(IpMateriales.ESENCIA_AMARILLA1));
        items.add(air);
        return guiaCrafteoSimple(p,items);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;

        // tamaños inventarios: 9 18 27 36 45 54

        if (strings.length == 0){
            p.openInventory(crearInventario(p));
        } else if (strings.length == 1){
            if (strings[0].equals("craft")){
                p.openWorkbench(null,true);
            } else if (strings[0].equals("craftguide")){
                p.openInventory(Crafteo.crearCraftGuide(p));
            } else if (strings[0].equals("help")){
                p.sendMessage("'/crafteo' para ver el menú general de crafteos");
                p.sendMessage("'/crafteo craft' para abrir la mesa de crafteo");
                p.sendMessage("'/crafteo craftguide' para abrir la guia de crafteos");

                return true;
            }
        }

        p.sendMessage("Error, pon '/crafteo help' para ver el uso correcto");
        return false;
    }
}
