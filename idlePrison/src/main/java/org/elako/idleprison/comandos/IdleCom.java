package org.elako.idleprison.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.elako.idleprison.player.idle.IdleManager;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.rango.Rangos;
import org.elako.idleprison.player.rango.RangosManager;
import org.elako.idleprison.player.PlayerManager;

import javax.annotation.Nonnull;
import java.util.List;

public class IdleCom implements CommandExecutor {
    private static PlayerManager playerManager;
    private static RangosManager rangosManager;
    private static IdleManager idleManager;

    public IdleCom(PlayerManager playerManager, RangosManager rango, IdleManager idle) {
        idleManager = idle;
        rangosManager = rango;
        IdleCom.playerManager = playerManager;
    }

    public static Inventory crearInventario(Player p, int cantidad) {
        // tamaños inventarios: 9 18 27 36 45 54
        String inventarioTitulo = "";
        if (cantidad != 1) {
            inventarioTitulo = " x";
            inventarioTitulo += String.valueOf(cantidad);
        }

        Inventory inventario = Bukkit.createInventory(p, 54, ChatColor.BOLD + String.valueOf(ChatColor.GOLD) + "Idle" + inventarioTitulo);
        String player = p.getName();

        for (int i=0;i<54;i++) {
            switch (i){
                case 1:
                    inventario.setItem( i, idleManager.crearObjetoMina(1,player, 0) );
                    break;
                case 10:
                    inventario.setItem( i, idleManager.crearObjetoMina(1,player, 64) );
                    break;
                case 19:
                    inventario.setItem( i, idleManager.crearObjetoMina(1,player, 128) );
                    break;
                case 9:
                    inventario.setItem( i, idleManager.crearObjetoIdle(1,player,cantidad) );
                    break;
                case 4:
                    inventario.setItem( i, idleManager.crearObjetoMina(2,player, 0) );
                    break;
                case 13:
                    inventario.setItem( i, idleManager.crearObjetoMina(2,player, 64) );
                    break;
                case 22:
                    inventario.setItem( i, idleManager.crearObjetoMina(2,player, 128) );
                    break;
                case 12:
                    inventario.setItem( i, idleManager.crearObjetoIdle(2,player,cantidad) );
                    break;
                case 7:
                    inventario.setItem( i, idleManager.crearObjetoMina(3,player, 0) );
                    break;
                case 16:
                    inventario.setItem( i, idleManager.crearObjetoMina(3,player, 64) );
                    break;
                case 25:
                    inventario.setItem( i, idleManager.crearObjetoMina(3,player, 128) );
                    break;
                case 15:
                    inventario.setItem( i, idleManager.crearObjetoIdle(3,player,cantidad) );
                    break;
                case 8:
                    if (cantidad == 1) inventario.setItem(i, IdleprisonCom.crearObjeto(Material.GRAY_CONCRETE,ChatColor.WHITE + "compra x1",1));
                    else inventario.setItem(i, IdleprisonCom.crearObjeto(Material.LIGHT_GRAY_CONCRETE,ChatColor.WHITE + "compra x1",1));
                    break;
                case 17:
                    if (cantidad == 10) inventario.setItem(i, IdleprisonCom.crearObjeto(Material.GRAY_CONCRETE,ChatColor.WHITE + "compra x10",10));
                    else inventario.setItem(i, IdleprisonCom.crearObjeto(Material.LIGHT_GRAY_CONCRETE,ChatColor.WHITE + "compra x10",10));
                    break;
                case 26:
                    if (cantidad == 64) inventario.setItem(i, IdleprisonCom.crearObjeto(Material.GRAY_CONCRETE,ChatColor.WHITE + "compra x64",64));
                    else inventario.setItem(i, IdleprisonCom.crearObjeto(Material.LIGHT_GRAY_CONCRETE,ChatColor.WHITE + "compra x64",64));
                    break;
                case 27:
                    inventario.setItem(i, IdleprisonCom.crearObjetoLore(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR", List.of(
                            ChatColor.WHITE + "Botón para volver al menú"  ) ));
                    break;
                case 29:
                    inventario.setItem( i, idleManager.crearObjetoMina(4,player, 0) );
                    break;
                case 38:
                    inventario.setItem( i, idleManager.crearObjetoMina(4,player, 64) );
                    break;
                case 47:
                    inventario.setItem( i, idleManager.crearObjetoMina(4,player, 128) );
                    break;
                case 37:
                    inventario.setItem( i, idleManager.crearObjetoIdle(4,player,cantidad) );
                    break;
                case 32:
                    inventario.setItem( i, idleManager.crearObjetoMina(5,player, 0) );
                    break;
                case 41:
                    inventario.setItem( i, idleManager.crearObjetoMina(5,player, 64) );
                    break;
                case 50:
                    inventario.setItem( i, idleManager.crearObjetoMina(5,player, 128) );
                    break;
                case 40:
                    inventario.setItem( i, idleManager.crearObjetoIdle(5,player,cantidad) );
                    break;
                case 35:
                   inventario.setItem( i, idleManager.crearObjetoMina(6,player, 0) );
                    break;
                case 44:
                    inventario.setItem( i, idleManager.crearObjetoMina(6,player, 64) );
                    break;
                case 53:
                    inventario.setItem( i, idleManager.crearObjetoMina(6,player, 128) );
                    break;
                case 43:
                    inventario.setItem( i, idleManager.crearObjetoIdle(6,player,cantidad) );
                    break;
                case 45:
                    inventario.setItem(i, IdleprisonCom.crearObjetoLore(Material.CHEST,ChatColor.GREEN + "Recoger dinero", List.of(
                            ChatColor.WHITE + "Dinero acumulado: " + DineroManager.dineroToString(
                                    playerManager.getDineroAcum(player), true ),
                            ChatColor.WHITE + "Tiempo acumulado: " + IdleManager.tiempoToString(
                                    playerManager.getPlayer(p.getName()).getTimeTotal() ) ) ));
                    break;
                default:
                    if (i<27) inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.BLACK_STAINED_GLASS_PANE," "));
                    else inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
            }
        }
        return inventario;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command,
                             @Nonnull String s, @Nonnull String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;

        if(!rangosManager.isPermitido(p.getName(), Rangos.CONDENADO4)) return false;

        if(strings.length == 0){
            p.openInventory(crearInventario(p,1));
            return true;
        }

        switch (strings[0]) {
            case "recoger":
                idleManager.recogerDinero(p);
                return true;

            case "help":
                p.sendMessage("'/idle' para ver el menú general de idle");
                p.sendMessage("'/idle recoger' para recoger el dinero del idle");
                return true;

            case "skip":
                // todo programar
                return true;

        }

        return false;
    }
}
