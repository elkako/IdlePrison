package me.elako.elako.comandos;

import me.elako.elako.Elako;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Generador implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //s = comando , strings = argumentos
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        if (strings.length != 0){
            p.sendMessage("Uso correcto del comando: /ubicacion");
            return false;
        }
        Location ubicacion = p.getLocation();

        p.sendMessage(ubicacion.toString());
        Elako.getPlugin().saveConfig();

        //guardar en lista
        Elako.spawner.generadorAdd(ubicacion);
        Elako.spawner.chunquis.add(ubicacion.getChunk());

        return true;
    }
}
