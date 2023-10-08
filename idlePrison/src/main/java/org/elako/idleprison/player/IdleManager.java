package org.elako.idleprison.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.comandos.Idle;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.items.MaterialesManager;

import java.util.LinkedList;
import java.util.Objects;

public class IdleManager {
    private final int RATE = 60;
    private int ticksLeft = RATE;

    private final DineroManager dineroManager;
    private final PlayerManager playerManager;


    public IdleManager(DineroManager dinero, PlayerManager player) {
        playerManager = player;
        dineroManager = dinero;
    }

    public static String tiempoToString(int segundos){
        String devolver = "";
        int min = segundos/60;
        if (min>0){
            devolver += min;
            if (min==1) devolver += " min ";
            else devolver += " mins ";
        }
        int seg = segundos%60;
        if (seg>0 && min >0) devolver +=  "y ";
        if (seg>0 || min == 0) {
            devolver += seg;
            devolver += " segs";
        }

        return devolver;
    }

    public LinkedList<String> loreToStrings(int idle , String p, int cantidad){
        LinkedList<String> devolver = new LinkedList<>();
        devolver.add(ChatColor.WHITE + "-" + getDineroCompraToString(idle, p,cantidad) + " E");

        LinkedList<ItemStack> materiales = materialesTotal(idle, p, cantidad);
        LinkedList<ItemStack> materiales2 = materialesTotal(idle, p, 1);
        for (ItemStack i : materiales2) {
            int n = needMaterial(idle, p, materiales2.indexOf(i));
            i.setAmount(0);
            for (ItemStack i2 : materiales) {
                if(Objects.equals(i.getItemMeta(), i2.getItemMeta())) {
                    i.setAmount(i.getAmount()+i2.getAmount());
                }
            }

            ItemMeta im = i.getItemMeta();
            if (im == null) return devolver;

            if (i.getAmount()<=n) devolver.add(ChatColor.WHITE + "-" + i.getAmount() + " " +  im.getDisplayName().substring(4));
            else devolver.add(ChatColor.WHITE + "-" + i.getAmount() + "/" + n + " " +  im.getDisplayName().substring(4) );
        }
        devolver.add("");

        return devolver;
    }

    public LinkedList<ItemStack> materiales1(int idle, int nivel){
        LinkedList<ItemStack> devolver = new LinkedList<>();
        switch (idle){
            case 1:
                if (nivel%10 == 0) {
                    devolver.add(MaterialesManager.getItem(IpMateriales.POLVO_HUESO,5*nivel));
                    devolver.add(MaterialesManager.getItem(IpMateriales.ALGA,10));
                } else {
                    devolver.add(MaterialesManager.getItem(IpMateriales.POLVO_HUESO,nivel));
                    devolver.add(MaterialesManager.getItem(IpMateriales.ALGA));
                }
                break;
            case 2:
                if (nivel%10 == 0) {
                    devolver.add(MaterialesManager.getItem(IpMateriales.PIEDRA_INFIERNO_VERDE,nivel / 10));
                } else {
                    devolver.add(MaterialesManager.getItem(IpMateriales.POLVO_HUESO,nivel));
                    devolver.add(MaterialesManager.getItem(IpMateriales.PIEDRA_INFIERNO_ROJA,nivel/10 + 1));
                }
                break;
            case 3:
                if (nivel%10 == 0) devolver.add(MaterialesManager.getItem(IpMateriales.PEZ_GLOBO));
                else {
                    devolver.add(MaterialesManager.getItem(IpMateriales.RESTOS_BASURA));
                    devolver.add(MaterialesManager.getItem(IpMateriales.CALCITA,8*nivel));
                }
                break;
            case 4:
                if(nivel%10 == 0){
                    devolver.add(MaterialesManager.getItem(IpMateriales.GRAN_GRANITO, 2*nivel));
                } else {
                    devolver.add(MaterialesManager.getItem(IpMateriales.PIEDRA,nivel));
                    devolver.add(MaterialesManager.getItem(IpMateriales.ROCA_ARENOSA,nivel));
                    devolver.add(MaterialesManager.getItem(IpMateriales.GRANITO, 8*nivel));
                }
                break;
            case 5:

                if(nivel%10 == 0) {
                    devolver.add(MaterialesManager.getItem(IpMateriales.ARENISCA, 4*nivel));
                    devolver.add(MaterialesManager.getItem(IpMateriales.ARENA, 64*(nivel/10)));
                }
                else {
                    devolver.add(MaterialesManager.getItem(IpMateriales.ARENISCA, 2*nivel));
                }
                break;
            case 6:
                if(nivel%10 == 0) {
                    devolver.add(MaterialesManager.getItem(IpMateriales.PIEDRA,64*(nivel/10)));
                } else {
                    devolver.add(MaterialesManager.getItem(IpMateriales.MINI_ROCA,nivel*16));
                    devolver.add(MaterialesManager.getItem(IpMateriales.CARBON,nivel));
                }
                break;
        }
        return devolver;
    }

    public LinkedList<ItemStack> materialesTotal(int idle, String jugador, int cantidad){
        LinkedList<ItemStack> devolver = new LinkedList<>();
        for (int i = 1; i <= cantidad; i++) {
            devolver.addAll(materiales1( idle, playerManager.getIdle(idle,jugador)+i));
        }
        return devolver;
    }

    public int needMaterial(int idle, String jugador, int pos){
        LinkedList<ItemStack> devolver = materialesTotal(idle, jugador, 1);
        int n=0;

        ItemStack i = devolver.get(pos);

        for (ItemStack i2 : Objects.requireNonNull(IdlePrison.getPlugin().getServer().getPlayer(jugador)).getInventory().getContents()) {
            if(i2 != null) {
                if( i2.getType().equals(i.getType()) ){
                    n += i2.getAmount();
                }
            }
        }
        return n;
    }

    public void comprarMaterial(int idle, String jugador, int cantidad){
        LinkedList<ItemStack> devolver = materialesTotal(idle, jugador, cantidad);

        for (ItemStack i1 : devolver ){
            int n = i1.getAmount();
            for (ItemStack i2 : Objects.requireNonNull(IdlePrison.getPlugin().getServer().getPlayer(jugador)).getInventory().getContents()) {
                if(i2 != null &&  n != 0) {
                    if( i2.getType().equals(i1.getType()) ){
                        if(n >= i2.getAmount()){
                            n -= i2.getAmount();
                            i2.setAmount(0);
                        } else {
                            i2.setAmount( i2.getAmount()-n );
                            n = 0;
                        }

                    }
                }
            }
        }
    }

    public boolean isMateriales(int idle, String jugador, int cantidad){
        LinkedList<ItemStack> devolver = materialesTotal(idle, jugador, cantidad);

        for (ItemStack i1 : devolver ){
            int n = i1.getAmount();
            for (ItemStack i2 : Objects.requireNonNull(IdlePrison.getPlugin().getServer().getPlayer(jugador)).getInventory().getContents()) {
                if(i2 != null) {
                    if( i2.getType().equals(i1.getType()) ){
                        n = n - i2.getAmount();
                    }
                }
            }
            if(n > 0) return false;
        }
        return true;
    }

    public double formulaDinero(int idle,int nivel){
        switch (idle){  //return 10+5*Math.pow(2,playerManager.getIdle1(jugador));
            case 1:
                if (nivel == 1) return 10;
                return formulaDinero(1, nivel-1)+ nivel;
            case 2:
                if (nivel == 1) return 50;
                return formulaDinero(2, nivel-1)+nivel*3;
            case 3:
                if (nivel == 1) return 200;
                return formulaDinero(3, nivel-1)+nivel*5;
            case 4:
                if (nivel == 1) return 500;
                return formulaDinero(4, nivel-1)+nivel*8;
            case 5:
                if (nivel == 1) return 1000;
                return formulaDinero(5, nivel-1)+nivel*15;
            case 6:
                if (nivel == 1) return 1500;
                return formulaDinero(6, nivel-1)+nivel*30;
            default:
                return 0;
        }
    }

    public String getDineroCompraToString(int idle, String jugador,int cantidad){
        double rebaja = TreeSkillManager.getPrecioIdle(jugador);
        double dinero = 0;
        for (int i = 1; i <= cantidad; i++) {
            dinero += formulaDinero( idle, playerManager.getIdle(idle,jugador)+i );
        }
        dinero = Math.round( dinero-dinero*(rebaja/100) );
        double dineroJugador = playerManager.getDinero(jugador);
        if (dinero <= dineroJugador) return DineroManager.dineroToString(dinero);
        else return DineroManager.dineroToString(dinero) + "/" + DineroManager.dineroToString(dineroJugador);

    }


    public double getDineroCompra(int idle, String jugador,int cantidad){
        double rebaja = TreeSkillManager.getPrecioIdle(jugador);
        double dinero = 0;
        for (int i = 1; i <= cantidad; i++) {
            dinero += formulaDinero( idle, playerManager.getIdle(idle,jugador)+i );
        }
        return Math.round( dinero-dinero*(rebaja/100) );
    }

    public void recogerDinero(Player p){
        double acum = playerManager.recogerDineroAcum(p.getName());
        dineroManager.addMoney(p.getName(),acum);
        p.sendMessage("Has recogido " + DineroManager.dineroToString(acum) + " de idle");
        playerManager.reloadTimeOffline(p.getName());
    }

    public double minaDinero(String jugador, int idle){
        return minaDinero(jugador,idle,playerManager.getIdle(idle,jugador));
    }

    public boolean isTen(String jugador, int idle){
        return playerManager.getIdle(idle, jugador)%10 == 9;
    }

    public double minaDinero(String jugador, int idle,int n) {
        int doble = TreeSkillManager.getIdleDoble(jugador);
        double incremento = TreeSkillManager.getDineroIdle(jugador);
        double cantidad=0;
        switch (idle) {
            case 1:
                cantidad = n;
                break;
            case 2:
                cantidad = n*8;
                break;
            case 3:
                cantidad = n*20;
                break;
            case 4:
                cantidad = n*25;
                break;
            case 5:
                cantidad = n*70;
                break;
            case 6:
                cantidad = n*100;
                break;
        }
        cantidad += cantidad * 0.5 * Math.floor((double) playerManager.getIdle(idle, jugador) /10);
        cantidad = cantidad + cantidad * (incremento / 100) ;

        if (doble>=idle) cantidad = cantidad * 2;
        return cantidad;
    }

    public boolean isComprable(String jugador, int idle,int cantidad){
       return dineroManager.getDinero(jugador) >= getDineroCompra(idle, jugador,cantidad) &
               isMateriales(idle, jugador, cantidad);
    }

    public double comprar(Player p, int idle, int cantidad){
        String jugador = p.getName();

        double dinero = getDineroCompra(idle, jugador,cantidad);
        playerManager.reloadTimeOffline(jugador);
        playerManager.setIdle(idle, jugador, playerManager.getIdle(idle,jugador)+cantidad);
        dineroManager.removeMoney(jugador,dinero);
        return dinero;
    }

    public void tick(){
        ticksLeft--;  //reducir ticks
        if(ticksLeft%5==0) {
            for(String p : playerManager.getPlayers()){
                Player player = IdlePrison.getPlugin().getServer().getPlayer(p);
                if(playerManager.getTimeOffline(p) + TreeSkillManager.getMinsInactividad(p)*12<=0) continue; // lleva demasiado offline

                playerManager.reduceTimeOffline(p); //reducir time offline

                if(ticksLeft%10==0) playerManager.addDineroAcum(p, minaDinero(p,1), player != null);
                if(ticksLeft%20==0) playerManager.addDineroAcum(p, minaDinero(p,2), player != null);
                if(ticksLeft%25==0) playerManager.addDineroAcum(p, minaDinero(p,3), player != null);
                if(ticksLeft%15==0) playerManager.addDineroAcum(p, minaDinero(p,4), player != null);
                if(ticksLeft%30==0) playerManager.addDineroAcum(p, minaDinero(p,5), player != null);
                if(ticksLeft%35==0) playerManager.addDineroAcum(p, minaDinero(p,6), player != null);

                // actualizar menú idle
                if(player == null) continue;

                if (player.getOpenInventory().getTitle().equals(ChatColor.BOLD + String.valueOf(ChatColor.GOLD) + "Idle")){
                    player.openInventory(Idle.crearInventario(player,1));
                }

            }
        }

        if (ticksLeft <= 0){
            ticksLeft = RATE;
            for (Player p :IdlePrison.getPlugin().getServer().getOnlinePlayers()) {
                if (playerManager.getTimeOffline(p.getName())+ TreeSkillManager.getMinsInactividad(p.getName())*12==0) p.sendMessage("Idle ya no generará mas dinero hasta que lo recojas");
            }

        }
    }

}
