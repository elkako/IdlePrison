package org.elako.idleprison.crafteos;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.items.MaterialesManager;
import org.elako.idleprison.player.Rangos;

import java.util.LinkedList;
import java.util.List;

public class CrafteoManager {
    LinkedList<Crafteo> crafteos = new LinkedList<>();

    public CrafteoManager() {
        ItemStack nulo = new ItemStack(Material.BARRIER);

        crafteos.add( new CrafteoShaped( new LinkedList<>( List.of(
                MaterialesManager.getItem(IpMateriales.TRIGO), MaterialesManager.getItem(IpMateriales.PAN) , MaterialesManager.getItem(IpMateriales.TRIGO),
                nulo, MaterialesManager.getItem(IpMateriales.CARBON), nulo,
                nulo, MaterialesManager.getItem(IpMateriales.ALGA), MaterialesManager.getItem(IpMateriales.PAN)) ), new ItemStack(Material.CALCITE), Rangos.CONDENADO4 ) );

        crafteos.getFirst().getCrafteo();
    }
}
