package org.elako.idleprison.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.items.materiales.IpMaterial;
import org.elako.idleprison.items.materiales.MaterialesManager;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.PlayerManager;
import org.elako.idleprison.player.rango.RangosManager;
import org.elako.idleprison.player.TreeSkillManager;

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
        if(MaterialesManager.itemToMaterial(item) != null) IdlePrison.imprimirConsola("C " +
                MaterialesManager.itemToMaterial(item));
        IpMaterial mate = MaterialesManager.itemToMaterial(item);
        if (mate == null) return -1;

        IdlePrison.imprimirConsola("XXXX");
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

}
