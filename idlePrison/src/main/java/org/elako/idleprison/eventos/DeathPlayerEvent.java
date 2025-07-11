package org.elako.idleprison.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.player.VenderManager;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.TreeSkillManager;

public class DeathPlayerEvent implements Listener {
    private final VenderManager venderManager;
    private final DineroManager dineroManager;

    public int generarNumeros(int MAX, int MIN) {
        return (int) (Math.floor(Math.random() * (MAX - MIN + 1)) + MIN);
    }

    public DeathPlayerEvent(VenderManager venderManager, DineroManager dineroManager) {
        this.venderManager = venderManager;
        this.dineroManager = dineroManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        double dinero = 0;

        p.sendMessage("Has perdido al morir: ");

        for (ItemStack i : e.getPlayer().getInventory().getContents()) {
            if (i == null) continue;
            if (i.getItemMeta() == null) continue;
            double precio = venderManager.tasacion(p, i);
            precio += precio*((double) TreeSkillManager.getDineroVender(p.getName()) /100)  ;
            if (precio > 0) {
                int cantidad = generarNumeros(i.getAmount(), 0);
                p.sendMessage("-" + i.getItemMeta().getDisplayName()+ " " + cantidad );
                dinero += cantidad * precio;
                i.setAmount(i.getAmount()-cantidad);
            }
        }
        p.sendMessage("Hubieras ganado vendiendolo " + DineroManager.dineroToString(dinero, true) );


    }

}
