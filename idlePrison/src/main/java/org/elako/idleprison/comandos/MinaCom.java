package org.elako.idleprison.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.mina.IpMina;
import org.elako.idleprison.mina.MinaManager;
import org.elako.idleprison.player.rango.Rangos;
import org.elako.idleprison.player.rango.RangosManager;

import javax.annotation.Nonnull;
import java.util.List;

public class MinaCom implements CommandExecutor {
    private static MinaManager minaManager;
    private static RangosManager rangosManager;

    public MinaCom(MinaManager mina, RangosManager rango) {
        rangosManager = rango;
        minaManager = mina;
    }

    public static Inventory crearInventario(Player p) {
        // tamaños inventarios: 9 18 27 36 45 54
        int size = 9;
        if (rangosManager.isPermitido(p.getName(), Rangos.CAMPESINO3)) size = 18;
        if (rangosManager.isPermitido(p.getName(), Rangos.MINERO3)) size = 27;


        Inventory inventario = Bukkit.createInventory(p, size, ChatColor.BOLD + String.valueOf(ChatColor.DARK_GREEN) + "Minas");
        int contador = 0;

        for (IpMina mina : minaManager.getMinas()) {
            if (contador == 4) contador++;
            IdlePrison.imprimirConsola(mina.getName() + " c" + contador + " e" + mina.getExtra());
            if(mina.getExtra() != 0) contador = (size-10)+ mina.getExtra();
            if (rangosManager.isPermitido(p.getName(), mina.getPermiso())) inventario.setItem(contador, minaManager.crearObjeto(mina));
            contador++;
        }

        inventario.setItem(size-1, IdleprisonCom.crearObjetoLore(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR", List.of(
                ChatColor.WHITE + "Botón para volver al menú")));

        return inventario;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command,
                             @Nonnull String s, @Nonnull String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        if (strings.length > 2) {
            //no tiene un input el comando
            p.sendMessage("Error");
            return false;
        }

        if(!rangosManager.isPermitido(p.getName(), Rangos.CONDENADO4)) return false;


        if(strings.length == 0) {
            Inventory inventario = crearInventario(p);
            p.openInventory(inventario);
            return true;
        } else if (strings.length == 1) {
           if (strings[0].equals("all")) {
                minaManager.minaAll(p);
            } else {
                minaManager.tpMina(p, strings[0]);
                return true;
            }
        } else {
            if(strings[0].equals("nombre")){
                p.setDisplayName(strings[1]);
                return true;
            }
        }
        return false;
    }
}
