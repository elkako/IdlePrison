package org.elako.idleprison.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.elako.idleprison.crafteos.CraftManager;

import javax.annotation.Nonnull;
import java.util.List;

public class CrafteoCom implements CommandExecutor {

    public static Inventory crearInventario(Player p) {
        // tamaños inventarios: 9 18 27 36 45 54

        Inventory inventario = Bukkit.createInventory(p, 27, ChatColor.BOLD + String.valueOf(ChatColor.DARK_PURPLE) + "CraftMenu");
        inventario.setItem(11, IdleprisonCom.crearObjeto(Material.CRAFTING_TABLE,ChatColor.WHITE +"Craftear"));
        inventario.setItem(15, IdleprisonCom.crearObjeto(Material.BOOK,ChatColor.WHITE +"Craftguide"));

        inventario.setItem(26, IdleprisonCom.crearObjetoLore(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR", List.of(
                ChatColor.WHITE + "Botón para volver al menú")));

        return inventario;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender,@Nonnull Command command,
                             @Nonnull String s,@Nonnull String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;

        // tamaños inventarios: 9 18 27 36 45 54

        if (strings.length == 0){
            p.openInventory(crearInventario(p));
            return true;
        } else if (strings.length == 1){
            switch (strings[0]) {
                case "craft":
                    //p.openWorkbench(null, true);
                    p.openInventory(CraftManager.CraftMenu(p));

                    return true;
                case "craftguide":
                    p.openInventory(CraftManager.CraftGuide(p,1));
                    return true;
                case "help":
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
