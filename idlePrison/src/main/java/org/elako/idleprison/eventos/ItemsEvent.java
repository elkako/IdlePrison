package org.elako.idleprison.eventos;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.comandos.Idleprison;
import org.elako.idleprison.items.*;
import org.elako.idleprison.mina.*;
import org.elako.idleprison.player.TreeSkillManager;

import java.util.LinkedList;

public class ItemsEvent implements Listener {
    private final MinaManager minaManager;
    private final BloqueManager bloqueManager;

    public ItemsEvent(MinaManager mina, BloqueManager bloques) {
        minaManager = mina;
        bloqueManager = bloques;
    }

    public double media(int a, int b){ return (double)(a + b)/2; }

    public boolean isNotPrisonBlock(Block bloque){
        for (Block b: minaManager.getBloques())
            if (bloque.equals(b)) return false;
        return true;
    }

    @EventHandler
    public void onHeldItem(PlayerItemHeldEvent e){
        Player p = e.getPlayer();
        int menu = -1;
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            ItemStack item = p.getInventory().getItem(i);
            if(item != null) if(item.equals(PicosManager.getMenu())) menu = i;
        }
        if (menu == -1)  return;
        if ( e.getNewSlot() != menu) return;

        p.openInventory(Idleprison.crearInventario(p));
        e.setCancelled(true);
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (!e.getAction().equals(Action.LEFT_CLICK_AIR) && !e.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;
        if (!e.getMaterial().equals(Material.SPYGLASS)) return;

        int nivel = e.getItem().getEnchantmentLevel(Enchantment.CHANNELING);

        if (nivel == 0) return;

        Block block = p.getTargetBlock(null,8 * nivel);
        Material material = block.getType();

        if (isNotPrisonBlock(block)) return;

        //visor nivel 1 -> drops, nivel 2 -> prejuicios, nivel 3 -> dureza
        LinkedList<String> s = new LinkedList<>();

        IpBloque ipBloque = bloqueManager.getBloque(material);

        double rentabilidad = 0.0;

        if (nivel < 3) s.add( bloqueManager.getNombre(new ItemStack(material)) );
        else s.add( bloqueManager.getNombre(new ItemStack(material)) + "[Dureza " + material.getHardness() + "]");

        s.add("Drops:");
        for (DropProb drop : ipBloque.getDropsProb()) {
            IpMaterial ipMaterial = MaterialesManager.itemToMaterial(drop.getItem());
            double prob = drop.getProb() + drop.getProb() * (double) TreeSkillManager.getChanceDrop(p.getName()) /100;
            rentabilidad += prob * ipMaterial.getDinero();
            s.add("-" + prob*100 + "% " + ipMaterial.getNombre());
        }
        for (DropCuanti drop : ipBloque.getDropsCuanti()) {
            IpMaterial ipMaterial = MaterialesManager.itemToMaterial(drop.getItem());
            rentabilidad += media(drop.getMax(), drop.getMin()) * ipMaterial.getDinero();
            s.add("-" + ipMaterial.getNombre() + " x" + drop.getMin() + "-"
                    + drop.getMax() );
        }

        rentabilidad += rentabilidad * ((double) TreeSkillManager.getDineroVender(p.getName()) /100)  ;

        if (nivel > 1) s.add("Rentabilidad: " + rentabilidad + "E");

        for (String str: s) {
            p.sendMessage(str);
        }
        IdlePrison.scoreboardNotification(p, s,5);


    }
}
