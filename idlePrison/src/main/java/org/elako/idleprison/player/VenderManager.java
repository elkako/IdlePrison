package org.elako.idleprison.player;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.comandos.IdleprisonCom;
import org.elako.idleprison.items.materiales.IpMaterial;
import org.elako.idleprison.items.materiales.MaterialesManager;
import org.elako.idleprison.player.rango.RangosManager;

import java.util.List;

public class VenderManager {
    DineroManager dineroManager;
    RangosManager rangosManager;
    PlayerManager playerManager;


    public VenderManager(DineroManager dineroManager, RangosManager rangosManager, PlayerManager playerManager) {
        this.dineroManager = dineroManager;
        this.rangosManager = rangosManager;
        this.playerManager = playerManager;
    }

    public double tasacion(Player player, ItemStack item){
        if (item == null) return -1;
        IpMaterial mate = MaterialesManager.itemToMaterial(item);
        if (mate == null) return -1;

        if ( rangosManager.isPermitido(player.getName(), mate.getPermiso()) ) return mate.getDinero();
        else return 0;
    }

    public void venderObjeto(ItemStack item, ItemStack[] lista, Player p) {
        double precio;
        int cantidad = 0;
        ItemMeta itemMeta = item.getItemMeta();
        precio = tasacion(p, item);
        precio += precio * ((double) TreeSkillManager.getDineroVender(p.getName()) / 100);
        if (precio == -1) p.sendMessage("Ese item no es vendible");
        else if (precio == 0) p.sendMessage("No tienes rango suficiente para venderlo");
        else {
            for (ItemStack i : lista) {
                if (i == null) continue;
                if (i.getItemMeta() == null) continue;

                assert itemMeta != null;
                if (itemMeta.getAsString().equals(i.getItemMeta().getAsString())) {
                    cantidad += i.getAmount();
                    i.setAmount(0);
                }
            }
            double dinero = cantidad * precio;
            playerManager.setItemsVendidos(p.getName(), playerManager.getItemsVendidos(p.getName())+cantidad);
            dineroManager.addMoney(p.getName(), dinero);
            p.sendMessage(cantidad + " items vendidos por " + DineroManager.dineroToString(dinero, true) );
        }
    }

    public void vender(ItemStack[] lista, Player p) {
        double precio;
        double dinero = 0;
        int cantidad = 0;
        for (ItemStack i : lista) {
            if (i == null) continue;
            if (i.getItemMeta() == null) continue;
            precio = tasacion(p, i);
            precio += precio*((double) TreeSkillManager.getDineroVender(p.getName()) /100);
            if (precio > 0) {
                dinero += i.getAmount() * precio;
                cantidad += i.getAmount();
                i.setAmount(0);
            }
        }
        playerManager.setItemsVendidos(p.getName(), playerManager.getItemsVendidos(p.getName())+cantidad);
        dineroManager.addMoney(p.getName(), dinero);
        p.sendMessage(cantidad + " items vendidos por " + DineroManager.dineroToString(dinero, true) );
    }

    public void tickVender(Inventory inventario, Player p){
        double ingresos = 0;

        for (ItemStack item : inventario.getContents()) {
            double tasa = tasacion(p,item);
            if (tasa > 0) ingresos += tasa * item.getAmount();
        }

        inventario.setItem(35, IdleprisonCom.crearObjetoLore(Material.LIME_STAINED_GLASS_PANE, ChatColor.WHITE + "Vender", List.of(
                ChatColor.WHITE + "Botón para vender objetos, arrastra",
                ChatColor.WHITE + " todo lo que quieras vender",
                ChatColor.WHITE + "Obtendrás: " + DineroManager.dineroToString(ingresos,true)) ));
    }

}
