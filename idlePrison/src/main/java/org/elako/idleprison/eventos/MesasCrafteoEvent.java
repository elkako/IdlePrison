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
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.crafteos.Crafteo;
import org.elako.idleprison.crafteos.CrafteoManager;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.items.MaterialesManager;
import org.elako.idleprison.player.Rangos;
import org.elako.idleprison.player.RangosManager;

import java.util.LinkedList;


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

        LinkedList<Material> picos = new LinkedList<>();
        picos.add(Material.WOODEN_PICKAXE);
        picos.add(Material.STONE_PICKAXE);
        picos.add(Material.IRON_PICKAXE);
        picos.add(Material.DIAMOND_PICKAXE);

        for (Material material : picos) {
            if (ci.contains(material) && ci.contains(MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL1).getType())) { // Azul 1
                ci.getResult().setItemMeta(encantarArma(material,ci.getMatrix(),Enchantment.DIG_SPEED,1, p));
                cancelar = false;
            } else if (ci.contains(material) && ci.contains(MaterialesManager.getItem(IpMateriales.ESENCIA_ROJA1).getType())) { // Rojo 1
                ci.getResult().setItemMeta(encantarArma(material,ci.getMatrix(),Enchantment.LOOT_BONUS_BLOCKS,1, p));
                cancelar = false;
            } else if (ci.contains(material) && ci.contains(MaterialesManager.getItem(IpMateriales.ESENCIA_VERDE1).getType())) { // Verde 1
                ci.getResult().setItemMeta(encantarArma(material,ci.getMatrix(),Enchantment.LOOT_BONUS_MOBS,1, p));
                cancelar = false;
            } else if(ci.contains(material) && ci.contains(MaterialesManager.getItem(IpMateriales.ESENCIA_AZUL2).getType())){ // Azul 2
                ci.getResult().setItemMeta(encantarArma(material,ci.getMatrix(),Enchantment.DIG_SPEED,2, p));
                cancelar = false;
            }
        }

        if (cancelar) ci.getResult().setAmount(0);
    }

}
