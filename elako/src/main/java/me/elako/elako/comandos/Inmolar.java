package me.elako.elako.comandos;

import me.elako.elako.Elako;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inmolar implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //s = comando , strings = argumentos
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        if (strings.length != 1){
            p.sendMessage("Uso correcto del comando: /inmolar (numero)");
            return false;
        }
        String mensaje = Elako.getPlugin().getConfig().getString("Inmolate string");
        p.sendMessage( ChatColor.DARK_RED + mensaje);
        Location posicion = p.getLocation();
        p.getWorld().createExplosion(posicion, Float.parseFloat(strings[0]));
        return true;
    }
}