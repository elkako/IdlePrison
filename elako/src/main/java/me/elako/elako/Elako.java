package me.elako.elako;

import me.elako.elako.comandos.Generador;
import me.elako.elako.comandos.Inmolar;
import me.elako.elako.comandos.Ubicacion;
import me.elako.elako.eventos.Spawner;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class Elako extends JavaPlugin {
    private static Elako plugin;
    public static Spawner spawner = new Spawner();

    public static Elako getPlugin() {
        return plugin;
    }

    public void insertarConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        if(!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
    }

    public Location parsearUbicacion(int i){
        String mundo = getConfig().getString("Spawners.spawner " + i + ".mundo");
        double x = Double.parseDouble(Objects.requireNonNull(getConfig().getString("Spawners.spawner " + i + ".x")));
        double y = Double.parseDouble(Objects.requireNonNull(getConfig().getString("Spawners.spawner " + i + ".y")));
        double z = Double.parseDouble(Objects.requireNonNull(getConfig().getString("Spawners.spawner " + i + ".z")));
        World world = getServer().getWorld(Objects.requireNonNull(mundo));
        return new Location(world,x,y,z);
    }

    @Override
    public void onEnable() {
        plugin = this;

        //configs
       insertarConfig();

        //eventos
        getServer().getPluginManager().registerEvents(spawner, this);

        //comandos
        Objects.requireNonNull(getCommand("inmolar")).setExecutor(new Inmolar());
        Objects.requireNonNull(getCommand("ubicacion")).setExecutor(new Ubicacion());
        Objects.requireNonNull(getCommand("generador")).setExecutor(new Generador());

        //cargar spawners
        int n = getConfig().getInt("Spawners.numero");
        for (int i = 1; i<=n; i++) {
            Location coords = parsearUbicacion(i);
            spawner.generadores.add(coords);
        }
    }
}
