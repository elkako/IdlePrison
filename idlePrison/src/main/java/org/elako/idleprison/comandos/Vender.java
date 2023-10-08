package org.elako.idleprison.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.elako.idleprison.items.VenderManager;

import java.util.List;

public class Vender implements CommandExecutor {
    VenderManager venderManager;

    public Vender(VenderManager vender) {
        venderManager = vender;
    }

    public static Inventory crearInventario(Player p) {
        // tamaños inventarios: 9 18 27 36 45 54
        Inventory inventario = Bukkit.createInventory(p, 54, ChatColor.BOLD + String.valueOf(ChatColor.GREEN) + "Vender");
        for (int i = 7; i < 27 ;i=i+8) {         //7,8,16,17,25,26
            inventario.setItem(i, Idleprison.crearObjeto(Material.BLACK_STAINED_GLASS_PANE,ChatColor.WHITE + " " ));
            i++;
            inventario.setItem(i, Idleprison.crearObjeto(Material.BLACK_STAINED_GLASS_PANE,ChatColor.WHITE + " " ));
        }
        for (int i = 34; i < 53 ; i=i+8) {         //34,35,43,44,52,53
            inventario.setItem(i, Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE,ChatColor.WHITE + " " ));
            i++;
            if (i != 35) inventario.setItem(i, Idleprison.crearObjeto(Material.GRAY_STAINED_GLASS_PANE,ChatColor.WHITE + " " ));
        }

        inventario.setItem(35, Idleprison.crearObjetoLore(Material.LIME_STAINED_GLASS_PANE,ChatColor.WHITE + "Vender", List.of(
                ChatColor.WHITE + "Botón para vender objetos",
                ChatColor.WHITE + "Arrastra todo lo que quieras vender") ));
        inventario.setItem(53, Idleprison.crearObjetoLore(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR", List.of(
                ChatColor.WHITE + "Botón para volver al menú"  ) ));
        return inventario;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        if (strings.length > 1) {
            p.sendMessage("Falló");
            return false;
        }

        if (strings.length == 0){
            Inventory inventario = crearInventario(p);
            p.openInventory(inventario);
            return  true;
        }else {
            switch (strings[0]) {
                case "hand":   // /vender hand
                    venderManager.venderObjeto(p.getInventory().getItemInMainHand(), p.getInventory().getContents(), p);
                    break;
                case "all":
                    venderManager.vender(p.getInventory().getContents(), p);
                    break;
            }
        }

        return false;
    }
}
