package org.elako.idleprison.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.elako.idleprison.player.IdleManager;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.Rangos;
import org.elako.idleprison.player.RangosManager;
import org.elako.idleprison.player.PlayerManager;

import java.util.Arrays;
import java.util.LinkedList;

public class Idle implements CommandExecutor {
    private static PlayerManager playerManager;
    private static RangosManager rangosManager;
    private static IdleManager idleManager;

    public Idle(PlayerManager playerManager, RangosManager rango, IdleManager idle) {
        idleManager = idle;
        rangosManager = rango;
        this.playerManager = playerManager;
    }

    public static Inventory crearInventario(Player p, int cantidad) {
        // tamaños inventarios: 9 18 27 36 45 54
        String inventarioTitulo = "";
        if (cantidad != 1) {
            inventarioTitulo = " x";
            inventarioTitulo += String.valueOf(cantidad);
        }

        Inventory inventario = Bukkit.createInventory(p, 54, ChatColor.BOLD + "" + ChatColor.GOLD + "Idle" + inventarioTitulo);
        String player = p.getName();

        for (int i=0;i<54;i++) {
            LinkedList<String> lore = new LinkedList<>();

            switch (i){
                case 1:
                    inventario.setItem(i, Idleprison.crearObjeto(Material.WOODEN_PICKAXE,
                            ChatColor.WHITE + "Niño minero", playerManager.getIdle(1,player) ));
                    break;
                case 10:
                    inventario.setItem(i, Idleprison.crearObjeto(Material.WOODEN_PICKAXE,
                            ChatColor.WHITE + "Niño minero", playerManager.getIdle(1,player)-64));
                    break;
                case 19:
                    inventario.setItem(i, Idleprison.crearObjeto(Material.WOODEN_PICKAXE,
                            ChatColor.WHITE + "Niño minero", playerManager.getIdle(1,player)-128));
                    break;
                case 9:
                    lore.add( ChatColor.WHITE + "Precio: " );
                    lore.addAll( idleManager.loreToStrings(1,player,cantidad) );

                    if (idleManager.isTen(player,1)) {
                        lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,1,cantidad)) +
                                "E cada 10 segs" + ChatColor.YELLOW + " x50%" );
                        lore.add( ChatColor.WHITE + " (" + DineroManager.dineroToString(idleManager.minaDinero(player, 1,cantidad)/10) +
                                " E/S)" + ChatColor.YELLOW + " x50%" );
                    }
                    else {
                        lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,1,cantidad)) +
                                "E cada 10 segs (" + DineroManager.dineroToString(idleManager.minaDinero(player, 1,cantidad)/10) + " E/S)");
                    }

                    lore.add( ChatColor.WHITE + "Total: " + DineroManager.dineroToString(idleManager.minaDinero(player,1)) + " E cada 10 segs" );
                    lore.add( ChatColor.WHITE + "(" + DineroManager.dineroToString(idleManager.minaDinero(player,1)/10) + " E/S)" );

                    if (idleManager.isComprable(player,1,cantidad)) inventario.setItem(i, Idleprison.crearObjetoLore(
                            Material.LIME_CONCRETE,ChatColor.GREEN + "Comprar x" + cantidad + " niño minero", lore ));
                    else inventario.setItem(i, Idleprison.crearObjetoLore(Material.YELLOW_CONCRETE,ChatColor.YELLOW + "Comprar x" + cantidad + " niño minero", lore ));
                    break;
                case 4:
                    if(!rangosManager.isPermitido(player, Rangos.CONDENADO3)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.STONE_PICKAXE,
                            ChatColor.WHITE + "Minero experimentado", playerManager.getIdle(2,player)));
                    break;
                case 13:
                    if(!rangosManager.isPermitido(player, Rangos.CONDENADO3)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.STONE_PICKAXE,
                            ChatColor.WHITE + "Minero experimentado", playerManager.getIdle(2,player)-64));
                    break;
                case 22:
                    if(!rangosManager.isPermitido(player, Rangos.CONDENADO3)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.STONE_PICKAXE,
                            ChatColor.WHITE + "Minero experimentado", playerManager.getIdle(2,player)-128));
                    break;
                case 12:
                    if(rangosManager.isPermitido(player, Rangos.CONDENADO3)) {
                        lore.add( ChatColor.WHITE + "Precio: " );
                        lore.addAll( idleManager.loreToStrings(2,player,cantidad) );
                        if (idleManager.isTen(player,2)) {
                            lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,2,cantidad)) +
                                    "E cada 20 segs" + ChatColor.YELLOW + " x50%" );
                            lore.add( ChatColor.WHITE + " (" + DineroManager.dineroToString(idleManager.minaDinero(player, 2,cantidad)/20) +
                                    " E/S)" + ChatColor.YELLOW + " x50%" );
                        }
                        else {
                            lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,2,cantidad)) +
                                    "E cada 20 segs (" + DineroManager.dineroToString(idleManager.minaDinero(player, 2,cantidad)/20) + " E/S)");
                        }
                        lore.add( ChatColor.WHITE + "Total: " + DineroManager.dineroToString(idleManager.minaDinero(player,2)) + " E cada 20 segs" );
                        lore.add( ChatColor.WHITE + "(" + DineroManager.dineroToString(idleManager.minaDinero(player,2)/20) + " E/S)" );

                        if (idleManager.isComprable(player,2,cantidad)) inventario.setItem(i, Idleprison.crearObjetoLore(
                                Material.LIME_CONCRETE,ChatColor.GREEN + "Comprar x" + cantidad + " minero experimentado", lore ));
                        else inventario.setItem(i, Idleprison.crearObjetoLore(Material.YELLOW_CONCRETE,ChatColor.YELLOW + "Comprar x" + cantidad + " minero experimentado", lore ));

                    }
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.BLACK_CONCRETE,ChatColor.RED + "Bloqueado"));
                    break;
                case 7:
                    if(!rangosManager.isPermitido(player, Rangos.CONDENADO1)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.CALCITE,
                            ChatColor.WHITE + "Yacimiento de calcita", playerManager.getIdle(3,player)));
                    break;
                case 16:
                    if(!rangosManager.isPermitido(player, Rangos.CONDENADO1)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.CALCITE,
                            ChatColor.WHITE + "Yacimiento de calcita", playerManager.getIdle(3,player)-64));
                    break;
                case 25:
                    if(!rangosManager.isPermitido(player, Rangos.CONDENADO1)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.CALCITE,
                            ChatColor.WHITE + "Yacimiento de calcita", playerManager.getIdle(3,player)-128));
                    break;
                case 15:
                    if(rangosManager.isPermitido(player, Rangos.CONDENADO1)){
                        lore.add( ChatColor.WHITE + "Precio: " );
                        lore.addAll( idleManager.loreToStrings(3,player,cantidad) );
                        if (idleManager.isTen(player,3)) {
                            lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,3,cantidad)) +
                                    "E cada 25 segs" + ChatColor.YELLOW + " x50%" );
                            lore.add( ChatColor.WHITE + " (" + DineroManager.dineroToString(idleManager.minaDinero(player, 3,cantidad)/25) +
                                    " E/S)" + ChatColor.YELLOW + " x50%" );
                        }
                        else {
                            lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,3,cantidad)) +
                                    "E cada 25 segs (" + DineroManager.dineroToString(idleManager.minaDinero(player, 3,cantidad)/25) + " E/S)");
                        }
                        lore.add( ChatColor.WHITE + "Total: " + DineroManager.dineroToString(idleManager.minaDinero(player,3)) + " E cada 25 segs" );
                        lore.add( ChatColor.WHITE + "(" + DineroManager.dineroToString(idleManager.minaDinero(player,3)/25) + " E/S)" );

                        if (idleManager.isComprable(player,3,cantidad)) inventario.setItem(i, Idleprison.crearObjetoLore(
                                Material.LIME_CONCRETE,ChatColor.GREEN + "Comprar x" + cantidad + " yacimiento de calcita", lore ));
                        else inventario.setItem(i, Idleprison.crearObjetoLore(Material.YELLOW_CONCRETE,ChatColor.YELLOW + "Comprar x" + cantidad + " yacimiento de calcita", lore ));

                    } else inventario.setItem(i, Idleprison.crearObjeto(Material.BLACK_CONCRETE,ChatColor.RED + "Bloqueado"));
                    break;
                case 8:
                    if (cantidad == 1) inventario.setItem(i, Idleprison.crearObjeto(Material.GRAY_CONCRETE,ChatColor.WHITE + "compra x1",1));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.LIGHT_GRAY_CONCRETE,ChatColor.WHITE + "compra x1",1));
                    break;
                case 17:
                    if (cantidad == 10) inventario.setItem(i, Idleprison.crearObjeto(Material.GRAY_CONCRETE,ChatColor.WHITE + "compra x10",10));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.LIGHT_GRAY_CONCRETE,ChatColor.WHITE + "compra x10",10));
                    break;
                case 26:
                    if (cantidad == 64) inventario.setItem(i, Idleprison.crearObjeto(Material.GRAY_CONCRETE,ChatColor.WHITE + "compra x64",64));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.LIGHT_GRAY_CONCRETE,ChatColor.WHITE + "compra x64",64));
                    break;
                case 27:
                    inventario.setItem(i, Idleprison.crearObjetoLore(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR", Arrays.asList(
                            ChatColor.WHITE + "Botón para volver al menú"  ) ));
                    break;
                case 29:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(!rangosManager.isPermitido(player, Rangos.SINTECHO1)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.GRANITE,
                            ChatColor.WHITE + "Cueva de granito", playerManager.getIdle(4,player)));
                    break;
                case 38:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(!rangosManager.isPermitido(player, Rangos.SINTECHO1)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.GRANITE,
                            ChatColor.WHITE + "Cueva de granito", playerManager.getIdle(4,player)-64));
                    break;
                case 47:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(!rangosManager.isPermitido(player, Rangos.SINTECHO1)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.GRANITE,
                            ChatColor.WHITE + "Cueva de granito", playerManager.getIdle(4,player)-128));
                    break;
                case 37:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(rangosManager.isPermitido(player, Rangos.SINTECHO1)){
                        lore.add( ChatColor.WHITE + "Precio: " );
                        lore.addAll( idleManager.loreToStrings(4,player,cantidad) );
                        if (idleManager.isTen(player,4)) {
                            lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,4,cantidad)) +
                                    "E cada 15 segs" + ChatColor.YELLOW + " x50%" );
                            lore.add( ChatColor.WHITE + " (" + DineroManager.dineroToString(idleManager.minaDinero(player, 4,cantidad)/15) +
                                    " E/S)" + ChatColor.YELLOW + " x50%" );
                        }
                        else {
                            lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,4,cantidad)) +
                                    "E cada 15 segs (" + DineroManager.dineroToString(idleManager.minaDinero(player, 4,cantidad)/15) + " E/S)");
                        }
                        lore.add( ChatColor.WHITE + "Total: " + DineroManager.dineroToString(idleManager.minaDinero(player,4)) + " E cada 15 segs" );
                        lore.add( ChatColor.WHITE + "(" + DineroManager.dineroToString(idleManager.minaDinero(player,4)/15) + " E/S)" );

                        if (idleManager.isComprable(player,4,cantidad)) inventario.setItem(i, Idleprison.crearObjetoLore(
                                Material.LIME_CONCRETE, ChatColor.GREEN + "Comprar x" + cantidad + " cueva de granito", lore ));
                        else inventario.setItem(i, Idleprison.crearObjetoLore(Material.YELLOW_CONCRETE,ChatColor.YELLOW + "Comprar x" + cantidad + " cueva de granito", lore ));

                    }
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.BLACK_CONCRETE,ChatColor.RED + "Bloqueado"));
                    break;
                case 32:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(!rangosManager.isPermitido(player, Rangos.CAMPESINO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.SMOOTH_SANDSTONE,
                                ChatColor.WHITE + "Piramide de arenisca", playerManager.getIdle(5,player)));
                    break;
                case 41:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(!rangosManager.isPermitido(player, Rangos.CAMPESINO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.SMOOTH_SANDSTONE,
                                ChatColor.WHITE + "Piramide de arenisca", playerManager.getIdle(5,player)-64));
                    break;
                case 50:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(!rangosManager.isPermitido(player, Rangos.CAMPESINO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.SMOOTH_SANDSTONE,
                                ChatColor.WHITE + "Piramide de arenisca", playerManager.getIdle(5,player)-128));
                    break;
                case 40:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(rangosManager.isPermitido(player, Rangos.CAMPESINO2)){
                        lore.add( ChatColor.WHITE + "Precio: " );
                        lore.addAll( idleManager.loreToStrings(5,player,cantidad) );
                        if (idleManager.isTen(player,5)) {
                            lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,5,cantidad)) +
                                    "E cada 30 segs" + ChatColor.YELLOW + " x50%" );
                            lore.add( ChatColor.WHITE + " (" + DineroManager.dineroToString(idleManager.minaDinero(player, 5,cantidad)/30) +
                                    " E/S)" + ChatColor.YELLOW + " x50%" );
                        }
                        else {
                            lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,5,cantidad)) +
                                    "E cada 30 segs (" + DineroManager.dineroToString(idleManager.minaDinero(player, 5,cantidad)/30) + " E/S)");
                        }
                        lore.add( ChatColor.WHITE + "Total: " + DineroManager.dineroToString(idleManager.minaDinero(player,5)) + " E cada 30 segs" );
                        lore.add( ChatColor.WHITE + "(" + DineroManager.dineroToString(idleManager.minaDinero(player,5)/30) + " E/S)" );

                        if (idleManager.isComprable(player,5,cantidad)) inventario.setItem(i, Idleprison.crearObjetoLore(
                                Material.LIME_CONCRETE,ChatColor.GREEN + "Comprar x" + cantidad + " piramide de arenisca", lore ));
                        else inventario.setItem(i, Idleprison.crearObjetoLore(Material.YELLOW_CONCRETE,ChatColor.YELLOW + "Comprar x" + cantidad + " piramide de arenisca", lore ));

                    }
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.BLACK_CONCRETE,ChatColor.RED + "Bloqueado"));
                    break;
                case 35:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(!rangosManager.isPermitido(player, Rangos.CAMPESINO1)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.COAL_ORE,
                                ChatColor.WHITE + "Mina de carbón", playerManager.getIdle(6,player)));
                    break;
                case 44:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(!rangosManager.isPermitido(player, Rangos.CAMPESINO1)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.COAL_ORE,
                                ChatColor.WHITE + "Mina de carbón", playerManager.getIdle(6,player)-64));
                    break;
                case 53:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(!rangosManager.isPermitido(player, Rangos.CAMPESINO1)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.COAL_ORE,
                                ChatColor.WHITE + "Mina de carbón", playerManager.getIdle(6,player)-128));
                    break;
                case 43:
                    if(!rangosManager.isPermitido(player, Rangos.SINTECHO2)) inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if(rangosManager.isPermitido(player, Rangos.CAMPESINO1)) {
                        lore.add( ChatColor.WHITE + "Precio: " );
                        lore.addAll( idleManager.loreToStrings(6,player,cantidad) );
                        if (idleManager.isTen(player,6)) {
                            lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,6,cantidad)) +
                                    "E cada 35 segs" + ChatColor.YELLOW + " x50%" );
                            lore.add( ChatColor.WHITE + " (" + DineroManager.dineroToString(idleManager.minaDinero(player, 6,cantidad)/35) +
                                    " E/S)" + ChatColor.YELLOW + " x50%" );
                        }
                        else {
                            lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(idleManager.minaDinero(player,6,cantidad)) +
                                    "E cada 35 segs (" + DineroManager.dineroToString(idleManager.minaDinero(player, 6,cantidad)/35) + " E/S)");
                        }
                        lore.add( ChatColor.WHITE + "Total: " + DineroManager.dineroToString(idleManager.minaDinero(player,6)) + " E cada 35 segs" );
                        lore.add( ChatColor.WHITE + "(" + DineroManager.dineroToString(idleManager.minaDinero(player,6)/35) + " E/S)" );

                        if (idleManager.isComprable(player,6,cantidad)) inventario.setItem(i, Idleprison.crearObjetoLore(
                                Material.LIME_CONCRETE,ChatColor.GREEN + "Comprar x" + cantidad + " mina de carbón", lore ));
                        else inventario.setItem(i, Idleprison.crearObjetoLore(Material.YELLOW_CONCRETE,ChatColor.YELLOW + "Comprar x" + cantidad + " mina de carbón", lore ));

                    }
                    else inventario.setItem(i, Idleprison.crearObjeto(Material.BLACK_CONCRETE,ChatColor.RED + "Bloqueado"));
                    break;
                case 45:
                    inventario.setItem(i, Idleprison.crearObjetoLore(Material.CHEST,ChatColor.GREEN + "Recoger dinero", Arrays.asList(
                            ChatColor.WHITE + "Dinero acumulado: " + DineroManager.dineroToString(playerManager.getDineroAcum(player)) ) ));
                    break;
                default:
                    if (i<27) inventario.setItem(i,  Idleprison.crearObjeto(Material.BLACK_STAINED_GLASS_PANE," "));
                    else inventario.setItem(i,  Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
            }
        }
        return inventario;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;

        if(!rangosManager.isPermitido(p.getName(), Rangos.CONDENADO4)) return false;

        if(strings.length == 0){
            p.openInventory(crearInventario(p,1));
            return true;
        }

       if (strings[0].equals("recoger")){
            idleManager.recogerDinero(p);
            return true;
        } else if (strings[0].equals("help")){
        p.sendMessage("'/idle' para ver el menú general de idle");
        p.sendMessage("'/idle recoger' para recoger el dinero del idle");

        return true;
       } else  if (strings[0].equals("skip")){
           // programar
           return true;
       }

        return false;
    }
}
