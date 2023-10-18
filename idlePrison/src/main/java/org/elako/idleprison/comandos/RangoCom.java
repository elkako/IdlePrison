package org.elako.idleprison.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elako.idleprison.player.PlayerManager;
import org.elako.idleprison.player.rango.RangosManager;

import javax.annotation.Nonnull;

public class RangoCom implements CommandExecutor {
    private final RangosManager rangoManager;
    PlayerManager playerManager;

    public RangoCom(RangosManager rango, PlayerManager player) {
        rangoManager = rango;
        playerManager = player;
    }

    private void mensajeError(Player p) {p.sendMessage("Error, pon '/rango help' para ver el uso correcto");}

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command,
                             @Nonnull String s, @Nonnull String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        boolean permiso = playerManager.isPermisoComandos(p.getName());

        if ( strings.length > 2) {
            //no tiene uno o dos inputs el comando
            mensajeError(p);
            return false;
        }


        if(strings.length == 0){
            p.sendMessage(rangoManager.getPlayer(p).toString());
            return true;
        } else if(strings.length == 1){
            switch (strings[0]) {
                case "rankup": // /rango rankup
                    rangoManager.ascender(p);
                    return true;
                case "all":  // /rango all
                    rangoManager.mostrar(p);
                    return true;
                case "set": // /rango set
                    if(permiso){
                        p.sendMessage("Uso correcto /rango set rango");
                        return true;
                    } else {
                        mensajeError(p);
                        return false;
                    }
                case "list": // /rango list
                    rangoManager.listar(p);
                    return true;
                case "help":
                    p.sendMessage("'/rango' para ver tu rango");
                    p.sendMessage("'/rango rankup' para ascender de rango");
                    p.sendMessage("'/rango all' para mostrar los rangos");
                    if(permiso) {
                        p.sendMessage("admin commands");
                        p.sendMessage("'/rango set rango' para cambiar tu rango");
                    }
                    return true;
                default:
                    mensajeError(p);
                    return false;
            }
        } else {
            if(permiso){
                if (strings[0].equals("set")) { // /rango set cantidad
                    rangoManager.setPlayer(p.getName(), strings[1]);
                    p.sendMessage("Has sido establecido " + strings[1]);
                    return true;
                } else {
                    p.sendMessage("No se ha encontrado el rango '" + s + "'");
                    rangoManager.listar(p);
                    return false;
                }
            } else {
                mensajeError(p);
                return false;
            }
        }
    }
}
