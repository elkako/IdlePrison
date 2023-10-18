package org.elako.idleprison.player;


import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.items.MaterialesManager;
import org.elako.idleprison.mina.MinaManager;
import org.elako.idleprison.player.rango.Rangos;
import org.elako.idleprison.player.rango.RangosManager;

import java.util.LinkedList;

public class TreeSkillManager {
    private static PlayerManager playerManager;
    private final RangosManager rangosManager;
    private final MinaManager minaManager;

    public TreeSkillManager(PlayerManager playerManager, RangosManager rangosManager, MinaManager minaManager) {
        TreeSkillManager.playerManager = playerManager;
        this.rangosManager = rangosManager;
        this.minaManager = minaManager;
    }

    public void renacer(Player p){
        String ps = p.getName();
        for (ItemStack i : p.getInventory().getContents()) {
            if (i == null) continue;
            i.setAmount(0);
        }
        int nivel = getNivelRenacer(ps);
        playerManager.recogerDineroAcum(p.getName());
        playerManager.setDineroRenacer( ps, getDineroRun(ps) + getDineroRenacer(ps) );
        playerManager.setDineroRun(ps, getAhorros(p.getName()));
        playerManager.setDinero(ps, getAhorros(p.getName()));
        rangosManager.setPlayer(ps, Rangos.CONDENADO4.toString());
        for (int i = 1; i <= 4; i++) {
            playerManager.setIdle(i,ps,0);
        }
        minaManager.tpMina(p, "mina1");
        playerManager.reloadTimeOffline(p.getName());
        p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 70, 2);
        p.spawnParticle(Particle.DUST_COLOR_TRANSITION, p.getLocation(), 40, 1, 1, 1, new Particle.DustTransition(Color.BLUE, Color.RED, 3));
        p.sendMessage("Ahora eres nivel de renacer " + getNivelRenacer(ps) + " y has conseguido " + (getNivelRenacer(ps)-nivel) + " puntos de treeSkill" );
        p.getInventory().addItem(MaterialesManager.getItem(IpMateriales.PICO_MADERA));
        p.getInventory().setItem(8,MaterialesManager.getItem(IpMateriales.MENU));

        LinkedList<String> scoreboard = new LinkedList<>();
        scoreboard.add(ChatColor.WHITE + "Ahora eres nivel de renacer " + getNivelRenacer(ps));
        scoreboard.add(ChatColor.WHITE + "Y has conseguido " + (getNivelRenacer(ps)-nivel) + " puntos de treeSkill");

        IdlePrison.scoreboardNotification(p,scoreboard,10);
    }

    public int dineroAsNivel(double dinero){
        int i = 1;
        while ( dinero >= (9000 + (100* Math.round( 10*Math.pow(i,1.5) ) )) ){
            dinero = dinero-(9000+(100* Math.round( 10*Math.pow(i,1.5))));
            i++;
        }
        return i-1;
    }

    public double ascenderRestos(double dinero){
        int i = 1;
        while ( dinero >= (9000 + (100* Math.round( 10*Math.pow(i,1.5) ))) ){
            dinero = dinero-(9000+(100* Math.round( 10*Math.pow(i,1.5))));
            i++;
        }
        return 9000+(100* Math.round( 10*Math.pow(i,1.5)))-dinero;
    }

    public double dineroPaAscender(double dinero){
        return 9000+(100* Math.round( 10*Math.pow( dineroAsNivel(dinero)+1,1.5 ) ) );
    }


    public double getDineroRenacer(String player){ return playerManager.getDineroRenacer(player); }
    public int getNivelRenacer(String player) { return dineroAsNivel(getDineroRenacer(player)); }

    public double getDineroRun(String player){ return playerManager.getDineroRun(player); }
    public double getDineroTotal(String player) { return getDineroRun(player) + getDineroRenacer(player); }
    public int getNivelTotal(String player) { return dineroAsNivel(getDineroTotal(player) ); }

    public int getPuntosGastados(String player) {
        return  playerManager.getTreeSkill(1,player) + playerManager.getTreeSkill(2,player)
                + playerManager.getTreeSkill(3,player);
    }
   public int getPuntosDisponibles(String player) { return getNivelRenacer(player)-getPuntosGastados(player); }

    public static int getHaste(String player){
        int i = playerManager.getTreeSkill(1,player);
          if (i>3){
              return 1;
          } else return 0;
    }

    public static int getRegeneration(String player){
        int i = playerManager.getTreeSkill(2,player);
        if (i>3){
            return 1;
        } else return 0;
    }

    public static int getSpeed(String player){
        int i = playerManager.getTreeSkill(3,player);
        if (i>3){
            return 1;
        } else return 0;
    }

    public static int getChanceDrop(String player){
        int i = playerManager.getTreeSkill(1,player);
        if (i>0){
            return 50;
        } else return 0;
    }

    public static int getDineroVender(String player){
        int i = playerManager.getTreeSkill(2,player);
        if (i>0){
            return 25;
        } else return 0;
    }

    public static int getDineroIdle(String player){
        int i = playerManager.getTreeSkill(3,player);
        if (i>0){
            return 25;
        } else return 0;
    }

    public static int getPrecioAscender(String player){
        int i = playerManager.getTreeSkill(1,player);
        if (i>1){
            return 20;
        } else return 0;
    }

    public static int getPrecioIdle(String player){
        int i = playerManager.getTreeSkill(2,player);
        if (i>1){
            return 20;
        } else return 0;
    }

    public static int getMinsInactividad(String player){
        int i = playerManager.getTreeSkill(3,player);
        if (i>1){
            return 5;
        } else return 0;
    }

    public static int getHealthBoost(String player){
        int i = playerManager.getTreeSkill(1,player);
        if (i>2){
            return 10;
        } else return 0;
    }

    public static int getIdleDoble(String player){ // da desde ese número hasta abajo
        int i = playerManager.getTreeSkill(2,player);
        if (i>2){
            return 1;
        } else return 0;
    }

    public static int getAhorros(String player){
        int i = playerManager.getTreeSkill(3,player);
        if (i>2){
            return 100;
        } else return 0;
    }

}
