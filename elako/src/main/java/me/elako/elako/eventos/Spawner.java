package me.elako.elako.eventos;

import me.elako.elako.Elako;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Spawner implements Listener {
    public LinkedList<Location> generadores = new LinkedList<>();
    public LinkedList<Chunk> chunquis = new LinkedList<>();

    public void generadorAdd(Location coords){
        FileConfiguration config = Elako.getPlugin().getConfig();
        int n = config.getInt("Spawners.numero");
        n++;
        config.set("Spawners.numero" , n);
        generadores.add(coords);
        String posicionConfig = "Spawners.spawner " + n;
        config.set(posicionConfig + ".mundo" , coords.getWorld().getName());
        config.set(posicionConfig + ".x" , coords.getBlockX());
        config.set(posicionConfig + ".y" , coords.getBlockY());
        config.set(posicionConfig + ".z" , coords.getBlockZ());
    }

    public boolean compararChunks(Chunk a, Chunk b){
        return (a.getX() == b.getX() && a.getZ() == b.getZ());
    }

    @EventHandler
    public void romper(ChunkLoadEvent event){
        for (Location gen:generadores) {
            Chunk chunk = gen.getChunk();
            Chunk chunk2 = event.getChunk();

            if(compararChunks(chunk,chunk2)) {
                for (Player p : event.getWorld().getPlayers()) {
                    p.sendMessage("DUBIDAAAAAA");
                }
                Zombie zombie = (Zombie) event.getChunk().getWorld().spawnEntity(gen, EntityType.ZOMBIE);
                zombie.setCustomName("Conchatumare1");
                zombie.setCustomNameVisible(true);
                //zombie.setVelocity(new Vector(2, 2, 0));
                zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                Entity zombie2 = event.getChunk().getWorld().spawnEntity(gen, EntityType.ZOMBIE);
                zombie2.setCustomName("Conchatumare2");
                zombie2.setCustomNameVisible(true);


            }
        }
    }

    @EventHandler
    public void caca(ChunkUnloadEvent event){
        for (Player p: event.getWorld().getPlayers()){
            //p.sendMessage("no cargado");
        }
    }

}
