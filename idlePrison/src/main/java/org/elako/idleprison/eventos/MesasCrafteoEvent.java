package org.elako.idleprison.eventos;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.crafteos.Crafteo;
import org.elako.idleprison.crafteos.CrafteoEncantar;
import org.elako.idleprison.crafteos.CrafteoManager;
import org.elako.idleprison.player.RangosManager;


public class MesasCrafteoEvent implements Listener {
    RangosManager rangosManager;
    CrafteoManager crafteoManager;

    public MesasCrafteoEvent(RangosManager rangos, CrafteoManager crafteo) {
        rangosManager = rangos;
        crafteoManager = crafteo;
    }

    public ItemMeta encantarArma(Material material, ItemStack[] matrix, Enchantment enchant, int nivel, Player p){
        for (ItemStack i :matrix) {
            if (i != null) {
                if(i.getType().equals(material)){
                    ItemMeta im = i.getItemMeta();

                    assert im != null;
                    im.addEnchant(enchant,nivel,true);
                    p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 100, 2);
                    return(im);
                }
            }
        }
        return null;
    }

    @EventHandler
    public void uno(PrepareItemCraftEvent e){ //pones items formando una receta
        CraftingInventory ci = e.getInventory();
        if(ci.getResult() == null) return;

        Player p = (Player) e.getView().getPlayer();
        boolean cancelar = true;

        //tablones quemados puedes desde nivel 1

        Crafteo crafteo = crafteoManager.getCrafteo(ci.getResult());
        if (crafteo != null) {
            cancelar = !rangosManager.isPermitido(p.getName(), crafteo.getPermiso());
        }

        CrafteoEncantar crafteoEn = null;

        if (cancelar) crafteoEn = crafteoManager.getEncantar(ci.getMatrix());
        if (crafteoEn != null) {
            cancelar = !rangosManager.isPermitido(p.getName(), crafteoEn.getPermiso());
            for (ItemStack i:ci.getMatrix()) {
                if(i == null ) continue;
                if(i.getItemMeta() == null ) continue;
                if(i.getItemMeta().getDisplayName().contains("Pico")){
                    ci.setResult(crafteoEn.encantar(i.clone()));
                    p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 100, 2);
                }
            }
        }

        if (cancelar) ci.getResult().setAmount(0);
    }

}
