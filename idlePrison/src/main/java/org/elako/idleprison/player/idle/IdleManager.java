package org.elako.idleprison.player.idle;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.comandos.IdleCom;
import org.elako.idleprison.comandos.IdleprisonCom;
import org.elako.idleprison.items.materiales.IpMateriales;
import org.elako.idleprison.items.materiales.MaterialesManager;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.PlayerManager;
import org.elako.idleprison.player.TreeSkillManager;
import org.elako.idleprison.player.rango.Rangos;
import org.elako.idleprison.player.rango.RangosManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class IdleManager {
    private final int RATE = 60;
    private int ticksLeft = RATE;

    private final DineroManager dineroManager;
    private final PlayerManager playerManager;
    private final RangosManager rangosManager;
    private final LinkedList<Idle> idles = new LinkedList<>();

    public IdleManager(DineroManager dinero, PlayerManager player, RangosManager rangosManager) {
        playerManager = player;
        dineroManager = dinero;
        this.rangosManager = rangosManager;

        idles.add( new Idle("Niño Minero", Material.STONE_PICKAXE,10,1,1,
                10, Rangos.CONDENADO4, Rangos.CONDENADO4, true, nivel -> new LinkedList<>( List.of(
                    MaterialesManager.getItem(IpMateriales.POLVO_HUESO,nivel),
                    MaterialesManager.getItem(IpMateriales.ALGA)
                )), nivel -> new LinkedList<>(List.of(
                    MaterialesManager.getItem(IpMateriales.POLVO_HUESO,5*nivel),
                    MaterialesManager.getItem(IpMateriales.ALGA,10)
                )  )) );
        idles.add( new Idle("Minero Experimentado", Material.IRON_PICKAXE,50,3,8,
                20, Rangos.CONDENADO3, Rangos.CONDENADO4, true, nivel -> new LinkedList<>( List.of(
                    MaterialesManager.getItem(IpMateriales.POLVO_HUESO,nivel),
                    MaterialesManager.getItem(IpMateriales.PIEDRA_INFIERNO_ROJA,nivel/10 + 1)
                )), nivel -> new LinkedList<>( List.of(
                    MaterialesManager.getItem(IpMateriales.PIEDRA_INFIERNO_VERDE,nivel / 10)
                )  )) );
        idles.add( new Idle("Yacimiento de Calcita", Material.CALCITE,200,5,20,
                25, Rangos.CONDENADO1, Rangos.CONDENADO4, true, nivel -> new LinkedList<>( List.of(
                    MaterialesManager.getItem(IpMateriales.RESTOS_BASURA),
                    MaterialesManager.getItem(IpMateriales.CALCITA,8*nivel)
                )), nivel -> new LinkedList<>(List.of(
                    MaterialesManager.getItem(IpMateriales.PEZ_GLOBO)
                )  )) );

        idles.add( new Idle("Cueva de Granito", Material.GRANITE,500,8,25,
                15, Rangos.SINTECHO1, Rangos.SINTECHO2, false, nivel -> new LinkedList<>( List.of(
                    MaterialesManager.getItem(IpMateriales.PIEDRA,nivel),
                    MaterialesManager.getItem(IpMateriales.ROCA_ARENOSA,nivel),
                    MaterialesManager.getItem(IpMateriales.GRANITO, 8*nivel)
                )), nivel -> new LinkedList<>(List.of(
                    MaterialesManager.getItem(IpMateriales.GRAN_GRANITO, 2*nivel)
                )  )) );
        idles.add( new Idle("Piramide de Arenisca", Material.SMOOTH_SANDSTONE,1000,15, 70,
                30, Rangos.CAMPESINO2, Rangos.SINTECHO2, false, nivel -> new LinkedList<>( List.of(
                    MaterialesManager.getItem(IpMateriales.ARENISCA, 2*nivel)
                )), nivel -> new LinkedList<>(List.of(
                    MaterialesManager.getItem(IpMateriales.ARENISCA, 4*nivel),
                    MaterialesManager.getItem(IpMateriales.ARENA, 64*(nivel/10))
                )  )) );
        idles.add( new Idle("Mina de Carbón", Material.COAL_ORE,1500,30,100,
                35, Rangos.CAMPESINO1, Rangos.SINTECHO2, false, nivel -> new LinkedList<>( List.of(
                    MaterialesManager.getItem(IpMateriales.MINI_ROCA,nivel*16),
                    MaterialesManager.getItem(IpMateriales.CARBON,nivel)
                )), nivel -> new LinkedList<>(List.of(
                    MaterialesManager.getItem(IpMateriales.PIEDRA,64*(nivel/10))
                )  )) );
    }

    public ItemStack crearObjetoIdle(int nIdle, String player, int cantidad){
        LinkedList<String> lore = new LinkedList<>();
        Idle idle = getIdle(nIdle);
        if(!rangosManager.isPermitido(player, idle.getPermisoMostrar())) {
            if(idle.isNegro()) return IdleprisonCom.crearObjeto(Material.BLACK_STAINED_GLASS_PANE," ");
            else return IdleprisonCom.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," ");
        } else if(rangosManager.isPermitido(player, idle.getPermisoCompra())) {
            lore.add( ChatColor.WHITE + "Precio: " );
            lore.addAll( loreToStrings(nIdle,player,cantidad) );
            if (isTen(player,nIdle)) {
                lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(minaDinero(player,nIdle,cantidad), true) +
                        " cada "+ idle.getDineroTiempo() + " segs" + ChatColor.YELLOW + " x50%" );
                lore.add( ChatColor.WHITE + " (" + DineroManager.dineroToString(minaDinero(player, nIdle,cantidad)/idle.getDineroTiempo(), false) +
                        " E/S)" + ChatColor.YELLOW + " x50%" );
            }
            else {
                lore.add(  ChatColor.WHITE + "+" + DineroManager.dineroToString(minaDinero(player,nIdle,cantidad), true) +
                        " cada "+ idle.getDineroTiempo() + " segs (" + DineroManager.dineroToString(minaDinero(player, nIdle,cantidad)/idle.getDineroTiempo(), false) + " E/S)");
            }
            lore.add( ChatColor.WHITE + "Total: " + DineroManager.dineroToString(minaDinero(player,nIdle), true) + " cada " + idle.getDineroTiempo() + " segs" );
            lore.add( ChatColor.WHITE + "(" + DineroManager.dineroToString(minaDinero(player,nIdle)/idle.getDineroTiempo(), false) + " E/S)" );

            if (isComprable(player,nIdle,cantidad)) return IdleprisonCom.crearObjetoLore(
                    Material.LIME_CONCRETE,ChatColor.GREEN + "Comprar x" + cantidad + " " + idle.getNombre(), lore );
            else return IdleprisonCom.crearObjetoLore(Material.YELLOW_CONCRETE,ChatColor.YELLOW + "Comprar x" + cantidad + " " + idle.getNombre(), lore );
        }
        else return IdleprisonCom.crearObjeto(Material.BLACK_CONCRETE,ChatColor.RED + "Bloqueado");
    }

    public ItemStack crearObjetoMina(int nIdle, String player,int resta){
        Idle idle = getIdle(nIdle);

        if(!rangosManager.isPermitido(player, idle.getPermisoMostrar())) {
            if( ( (nIdle-1) %3) %2 == 0) return IdleprisonCom.crearObjeto(Material.BLACK_STAINED_GLASS_PANE," ");
            else return IdleprisonCom.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," ");
        }else if(!rangosManager.isPermitido(player, idle.getPermisoCompra()))
            return IdleprisonCom.crearObjeto(Material.GLASS_PANE, " ");
        else return IdleprisonCom.crearObjeto(idle.getIconoIdle(),
                ChatColor.WHITE + idle.getNombre(), playerManager.getIdle(nIdle,player)-resta);
    }

    public Idle getIdle(int i){ return idles.get(i-1); }

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

    public LinkedList<ItemStack> materialesx1(int idle, int nivel){
        return getIdle(idle).getMateriales(nivel);
    }

    public LinkedList<ItemStack> materialesTotal(int idle, String jugador, int cantidad){
        LinkedList<ItemStack> devolver = new LinkedList<>();
        for (int i = 1; i <= cantidad; i++) {
            devolver.addAll(materialesx1( idle, playerManager.getIdle(idle,jugador)+i));
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
        if (nivel == 1) return getIdle(idle).getPrecioTotal();
        return formulaDinero(idle, nivel-1)+ nivel * getIdle(idle).getPrecioPorNivel();
    }

    public String getDineroCompraToString(int idle, String jugador,int cantidad){
        double rebaja = TreeSkillManager.getPrecioIdle(jugador);
        double dinero = 0;
        for (int i = 1; i <= cantidad; i++) {
            dinero += formulaDinero( idle, playerManager.getIdle(idle,jugador)+i );
        }
        dinero = Math.round( dinero-dinero*(rebaja/100) );
        double dineroJugador = playerManager.getDinero(jugador);
        if (dinero <= dineroJugador) return DineroManager.dineroToString(dinero, false);
        else return DineroManager.dineroToString(dinero, false) + "/" + DineroManager.dineroToString(dineroJugador, false);
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
        p.sendMessage("Has recogido " + DineroManager.dineroToString(acum,true) + " de idle");
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

        double cantidad = n * getIdle(idle).getDineroCantidad();

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

                for (Idle idle :idles) {
                    if(ticksLeft%idle.getDineroTiempo()==0){
                        playerManager.addDineroAcum(p, minaDinero(p, idles.indexOf(idle)+1 ), player != null);
                    }
                }

                // actualizar menú idle
                if(player == null) continue;    // offline
                String titulo = player.getOpenInventory().getTitle();
                if(!titulo.contains("Idle")) continue;

                if (titulo.equals(ChatColor.BOLD + String.valueOf(ChatColor.GOLD) + "Idle")){
                    player.openInventory(IdleCom.crearInventario(player,1));
                } else if (titulo.equals(ChatColor.BOLD + String.valueOf(ChatColor.GOLD) + "Idle x10")){
                    player.openInventory(IdleCom.crearInventario(player,10));
                } else if (titulo.equals(ChatColor.BOLD + String.valueOf(ChatColor.GOLD) + "Idle x64")){
                    player.openInventory(IdleCom.crearInventario(player,64));
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

    public void interactuar(InventoryClickEvent e, Player p, int cantidad) {
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;

        if (e.getCurrentItem().getType().equals(Material.LIME_CONCRETE)) {
            p.playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, 100, 2);
            for (Idle idle : idles) {

                int nIdle = idles.indexOf(idle) + 1;
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains(idle.getNombre())) {
                    comprarMaterial(nIdle, p.getName(), cantidad);
                    double dinero = comprar(p, nIdle, cantidad);
                    if (dinero != 0) {
                        p.sendMessage("Comprado por: " + DineroManager.dineroToString(dinero,true) + ", 1 alga seca y " +
                                (playerManager.getIdle(nIdle, p.getName())) + " polvo de hueso");
                        p.openInventory(IdleCom.crearInventario(p, cantidad));
                    } else p.sendMessage("No suficiente dinero");
                }
            }
        }else if (e.getCurrentItem().getType().equals(Material.YELLOW_CONCRETE)) {
            p.sendMessage("No tienes suficiente dinero");
            p.playSound(p.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_OFF, 100, 2);
        } else if (e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_CONCRETE)) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("10")) {
                p.openInventory(IdleCom.crearInventario(p, 10));
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("64")) {
                p.openInventory(IdleCom.crearInventario(p, 64));
            } else p.openInventory(IdleCom.crearInventario(p, 1));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 2);
        } else if (e.getCurrentItem().getType().equals(Material.BLACK_CONCRETE)) {
            p.sendMessage("Bloqueado");
            p.playSound(p.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_OFF, 100, 2);
        } else if (e.getCurrentItem().getType().equals(Material.CHEST)) {
            p.playSound(p.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_OFF, 100, 1);
            recogerDinero(p);
            p.openInventory(IdleCom.crearInventario(p, cantidad));
        } else if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 100, 1.3F);
            p.openInventory(IdleprisonCom.crearInventario(p));
        }
    }
}
