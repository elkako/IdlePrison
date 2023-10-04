package me.elako.elako.comandos;

import me.elako.elako.Elako;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public class Ubicacion implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //s = comando , strings = argumentos
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        if (strings.length > 1){
            //tiene mas de 2 inputs el comando
            enviarMensajeFallo(p);
            return false;
        }
        LinkedList<Location> generadores = new LinkedList<>(Elako.spawner.generadores);
        int nMax = generadores.size();

        if (strings.length == 0){
            //ubicacion no tiene inputs
            p.sendMessage("hay: " + nMax + " spawners");
            return true;
        }

        try {
            //ubicacion (numero) correcto
            int number = Integer.parseInt(strings[0]);
            if (nMax >= number){
                Location ubi = generadores.get(number);
                p.sendMessage(ubi.getWorld().getName());
                p.sendMessage(ubi.getBlockX() + " " + ubi.getBlockY() + " " + ubi.getBlockZ());
                return true;
            }
            else {
                //ubicacion (numero) se sale de rango
                enviarMensajeFallo(p);
                return false;
            }

        }
        catch (NumberFormatException ex){
            //ubicacion (no número)
            if(strings[0].equalsIgnoreCase("all") || strings[0].equalsIgnoreCase("todos")){
                for (int i = 1 ; i <= nMax;i++){
                    Location ubi = generadores.get(i);
                    p.sendMessage("Spawner " + i);
                    p.sendMessage(ubi.getWorld().getName() + " " +ubi.getBlockX() + " " + ubi.getBlockY() + " " + ubi.getBlockZ());
                }
                return true;
            } else if (strings[0].equalsIgnoreCase("chunk")) {
                for (Chunk c: Elako.spawner.chunquis) {
                    p.sendMessage(c.toString());
                }
                return true;
            }else {
                //ubicacion (no es número ni una cadena clave)
                enviarMensajeFallo(p);
                return false;
            }
        }
    }

    public void enviarMensajeFallo(Player p){
        p.sendMessage("Uso correcto del comando: /ubicacion, /ubicacion número o /ubicacion all");
    }

}
