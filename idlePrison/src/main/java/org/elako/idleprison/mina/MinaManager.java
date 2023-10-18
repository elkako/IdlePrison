package org.elako.idleprison.mina;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.comandos.IdleprisonCom;
import org.elako.idleprison.items.materiales.IpMaterial;
import org.elako.idleprison.items.materiales.IpMateriales;
import org.elako.idleprison.items.materiales.MaterialesManager;
import org.elako.idleprison.player.rango.Rangos;
import org.elako.idleprison.player.rango.RangosManager;

import java.util.*;

public class MinaManager {
    private static RangosManager rangosManager;
    private static final LinkedList<IpMina> ipMinas = new LinkedList<>();

    // añadir mina

    public MinaManager(RangosManager rangos) {
        rangosManager = rangos;
        Vector norte = new Vector(0,0,-1);
        Vector noroeste = new Vector(-1,0,-1);

        Location ubi1 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),81.5,-2,-86.5);
        ubi1.setDirection(norte);
        ipMinas.add( new IpMina("infierno1", Material.CALCITE, Rangos.CONDENADO4, 60, ubi1, new HashMap<>(Map.of(
                IpMateriales.CALCITA, 90, IpMateriales.ALGA_BLOQUE, 10 ))) );

        Location ubi2 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),30,-2,-86);
        ubi2.setDirection(norte);
        ipMinas.add( new IpMina("infierno2", Material.WARPED_NYLIUM, Rangos.CONDENADO3, 59, ubi2, new HashMap<>(Map.of(
                IpMateriales.CALCITA, 30,IpMateriales.PIEDRA_INFIERNO, 55, IpMateriales.ALGA_BLOQUE, 15 )
                )) );

        Location ubi3 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),-15.5,-2,-87.5);
        ubi3.setDirection(norte);
        ipMinas.add( new IpMina("infierno3", Material.MAGMA_BLOCK, Rangos.CONDENADO2,58, ubi3, new HashMap<>(Map.of(
                IpMateriales.PIEDRA_INFIERNO, 65, IpMateriales.BLOQUE_MAGMATICO, 15, IpMateriales.HIELO,15,
                IpMateriales.ESENCIA_AZUL1, 5 ))) );

        Location ubi4 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),-60.5,-2,-84.5);
        ubi4.setDirection(norte);
        ipMinas.add( new IpMina("infierno4", Material.TUFF, Rangos.CONDENADO1,57, ubi4, new HashMap<>(Map.of(
                IpMateriales.CALCITA, 10, IpMateriales.BLOQUE_MAGMATICO, 20, IpMateriales.CALCITA_ANTIGUA,15,
                IpMateriales.RESTOS_BASURA, 25 ))) );

        Location ubi5 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),81.5,-1,-26.5);
        ubi5.setDirection(norte);
        ipMinas.add( new IpMina("afueras1", Material.STONE, Rangos.SINTECHO2,64, ubi5, new HashMap<>(Map.of(
                IpMateriales.ROCA_ARENOSA, 25, IpMateriales.PIEDRA, 65, IpMateriales.SANDIA,10 ))) );

        Location ubi6 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),26.5,-2,-25.5);
        ubi6.setDirection(norte);
        ipMinas.add( new IpMina("afueras2", Material.RED_SAND, Rangos.SINTECHO1,63, ubi6, new HashMap<>(Map.of(
                IpMateriales.ROCA_ARENOSA, 60, IpMateriales.GRAN_GRANITO, 35, IpMateriales.ESENCIA_ROJA1,5 ))) );

        Location ubi7 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),-25.5,2,-80.5);
        ipMinas.add( new IpMina("afueras3", Material.SMOOTH_SANDSTONE, Rangos.CAMPESINO2,62, ubi7, new HashMap<>(Map.of(
                IpMateriales.ROCA_ARENOSA, 20, IpMateriales.ARENISCA, 37, IpMateriales.CUARZO,30,
                IpMateriales.BALA_TRIGO, 7, IpMateriales.ESENCIA_VERDE1,6 ))) );

        Location ubi8 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),-78.5,-2,-30.5);
        ubi8.setDirection(norte);
        ipMinas.add( new IpMina("afueras4", Material.COAL_ORE, Rangos.CAMPESINO1,61, ubi8, new HashMap<>(Map.of(
                IpMateriales.PIEDRA, 30, IpMateriales.GRAN_GRANITO, 15, IpMateriales.MENA_CARBON,10,
                IpMateriales.ESENCIA_AMARILLA1, 5, IpMateriales.ROCA,40 ))) );

        Location ubi9 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),80.5,-7,-204.5);
        ubi9.setDirection(norte);
        ipMinas.add( new IpMina("poblado1", Material.SNOW_BLOCK, Rangos.MINERO3,68, ubi9, new HashMap<>(Map.of(
                IpMateriales.MENA_CARBON, 35, IpMateriales.GRAN_DIORITA, 20, IpMateriales.HIELO_COMPACTO,10,
                IpMateriales.MENA_HIERRO, 15, IpMateriales.ARBUSTO,20 ))) );

        Location ubi10 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),-15.5,-5,-244.5);
        ubi10.setDirection(noroeste);
        ipMinas.add( new IpMina("poblado2", Material.AMETHYST_BLOCK, Rangos.MINERO2,67, ubi10, new HashMap<>(Map.of(
                IpMateriales.MENA_STYLUM, 25, IpMateriales.MENA_CARBON_OSCURA, 10, IpMateriales.AMATISTA,20,
                IpMateriales.PIEDRA_LUNAR, 55 ))) );

        Location ubiE1 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),81.5,-7,-139.5);
        ubiE1.setDirection(norte);
        ipMinas.add( new IpMina("extra1", Material.LIGHT_BLUE_CONCRETE, Rangos.CAMPESINO3,70, ubiE1, new HashMap<>(Map.of(
                IpMateriales.AIRE, 75, IpMateriales.ESENCIA_AZUL1, 10, IpMateriales.ESENCIA_ROJA1,9,
                IpMateriales.ESENCIA_VERDE1, 4, IpMateriales.ESENCIA_AZUL2,2 ))) );

        Location ubiE2 = new Location(IdlePrison.getPlugin().getServer().getWorld("world2"),81.5,-7,-139.5);
        ubiE2.setDirection(norte);
        ipMinas.add( new IpMina("extra2", Material.BLACK_CONCRETE, Rangos.MINERO1,71, ubiE2, new HashMap<>(Map.of(
                IpMateriales.AIRE, 30, IpMateriales.ESENCIA_AZUL1, 5, IpMateriales.ESENCIA_ROJA1,5,
                IpMateriales.ESENCIA_VERDE1, 5, IpMateriales.ESENCIA_AZUL2,5 , IpMateriales.ESENCIA_VERDE2, 15,
                IpMateriales.ESENCIA_ROJA2,15, IpMateriales.ESENCIA_OSCURA1, 20 ))) );

        // añadir mina

    }

    public LinkedList<IpMina> getMinas(){
        return ipMinas;
    }

    public IpMina getMina(String nombre){
        for (IpMina m : ipMinas) {
            if(m.getId().equals(nombre)) return m;
        }
        return null;
    }

    public void minaAll(Player p){
        for (IpMina m : ipMinas) {
            p.sendMessage(m.getId());
        }
    }

    public Location maxLocation(Location uno, Location dos){
        int maxX = Math.max(uno.getBlockX(),dos.getBlockX());
        int maxY = Math.max(uno.getBlockY(),dos.getBlockY());
        int maxZ = Math.max(uno.getBlockZ(),dos.getBlockZ());
        return new Location(uno.getWorld(), maxX, maxY, maxZ);
    }

    public Location minLocation(Location uno, Location dos){
        int minX = Math.min(uno.getBlockX(),dos.getBlockX());
        int minY = Math.min(uno.getBlockY(),dos.getBlockY());
        int minZ = Math.min(uno.getBlockZ(),dos.getBlockZ());
        return new Location(uno.getWorld(), minX, minY, minZ);
    }

    public List<Block> area(Location max, Location min){
        LinkedList<Block> devolver = new LinkedList<>();
        for(int i= min.getBlockX(); i <= max.getBlockX();i++){
            for(int j = min.getBlockY(); j <= max.getBlockY();j++) {
                for(int k = min.getBlockZ(); k <= max.getBlockZ();k++) {
                    devolver.add(Objects.requireNonNull(max.getWorld()).getBlockAt(i,j,k));
                }
            }
        }
        return devolver;
    }


    // para crear mina
    public boolean setBloques(String mina, Location uno, Location dos) {
        Location max = maxLocation(uno,dos);
        Location min = minLocation(uno,dos);
        LinkedList<Block> bloques = new LinkedList<>(area(max,min));
        IpMina ipmina= getMina(mina);
        if (ipmina == null) return false;
        ipmina.setBloquesLimite(max,min);
        ipmina.setBloques(bloques);
        refrescar(ipmina);
        return true;
    }

    // para saber si un item es de mina
    public LinkedList<Block> getBloques() {
        LinkedList<Block> bloques = new LinkedList<>();

        for (IpMina m : ipMinas) {
            if(m.getBloques() != null) bloques.addAll(m.getBloques());
        }
        return bloques;
    }

    public void tpMina(Player jugador, String minas){
        IpMina ipMina = getMina(minas);

        if (rangosManager.isPermitido(jugador.getName(), ipMina.getPermiso())) {
            jugador.teleport( ipMina.getSpawn() );
            jugador.sendMessage("Has sido correctamente teletransportado a " + ipMina.getName());
        } else jugador.sendMessage("Necesitas el rango " +  ipMina.getPermiso().toString().toLowerCase() +
                " para teletransportarte a esa mina");

    }

    //reiniciar minas
    private boolean entreUbicaciones(Location a, Location b, Location c) {
        if (a == null ) return false;
        if(a.getBlockX() < c.getBlockX()) return false;
        if(b.getBlockX() > c.getBlockX()) return false;
        if(a.getBlockY() < c.getBlockY()) return false;
        if(b.getBlockY() > c.getBlockY()) return false;
        if(a.getBlockZ() < c.getBlockZ()) return false;
        return b.getBlockZ() <= c.getBlockZ();
    }

    public int generarNumeros(int MAX, int MIN) {
        return (int) (Math.floor(Math.random() * (MAX - MIN + 1)) + MIN);
    }


    public void refrescar(IpMina mina){
        for (Player p : IdlePrison.getPlugin().getServer().getOnlinePlayers()){
            if (entreUbicaciones(mina.getMaxBloque(), mina.getMinBloque() ,p.getLocation())){
                p.sendMessage("REINISIO");
                tpMina(p,mina.getId());
            }
        }

        LinkedList<Material> materials = new LinkedList<>();
        LinkedList<Integer> probs = new LinkedList<>();

        HashMap<IpMateriales, Integer> materiales = mina.getMateriales();
        for (IpMateriales m : materiales.keySet()) {
            ItemStack item = MaterialesManager.getItem(m);
            if (item.getType() != Material.BARRIER) materials.add(item.getType());
            else materials.add(Material.AIR);
            probs.add(materiales.get(m));
        }

        if (mina.getBloques() == null) return;
        for (Block b: mina.getBloques()) {
            int n = generarNumeros(100, 1);
            int contador = 0;

            for (int i = 0; i < materials.size(); i++) {
                if (contador <= n && n <= contador+probs.get(i)) {
                    b.setType(materials.get(i));
                }
                contador += probs.get(i);
            }

        }
    }

    public void tick(){
        for (IpMina ipMina : ipMinas) {
            if (ipMina.tick()) refrescar(ipMina);
        }
    }

    // crear icono
    public ItemStack crearObjeto(IpMina mina){
        LinkedList<String> lore = new LinkedList<>();
        StringBuilder linea = new StringBuilder("Bloques: ");
        String nombre;
        int i=0;
        String id = mina.getId();

        HashMap<IpMateriales, Integer> materiales = mina.getMateriales();
        for (IpMateriales m :materiales.keySet()) {
            IpMaterial ipMaterial = MaterialesManager.getIpMaterial(m);
            if (ipMaterial == null) continue;
            if (ipMaterial.getNombre().contains("Esencia")) nombre = ChatColor.MAGIC + "esencia" +
                    ChatColor.WHITE;
            else nombre = ipMaterial.getNombre().toLowerCase();

            if (i==3) {
                lore.add(ChatColor.WHITE + linea.toString());
                linea = new StringBuilder();
            }
            i++;
            linea.append(nombre).append(", ");
        }
        linea = new StringBuilder(linea.substring(0, linea.length() - 2));
        lore.add(ChatColor.WHITE + linea.toString());

        return IdleprisonCom.crearObjetoLore(mina.getIcono(), mina.getName() , Integer.parseInt(String.valueOf(id.charAt(id.length()-1))), lore );
    }


}
