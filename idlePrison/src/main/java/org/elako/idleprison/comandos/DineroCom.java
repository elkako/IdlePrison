package org.elako.idleprison.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.PlayerManager;

import javax.annotation.Nonnull;

public class DineroCom implements CommandExecutor {
    DineroManager dineroManager;
    PlayerManager playerManager;

    public DineroCom(DineroManager dinero, PlayerManager player) {
        dineroManager = dinero;
        playerManager = player;
    }

    private void mensajeError(Player p) { p.sendMessage("Error, pon '/dinero help' para ver el uso correcto"); }

    private void mensajeJugadorDesconocido(Player p, String s) {
        p.sendMessage("No se ha encontrado el jugador '" + s + "'");
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command,
                             @Nonnull String s, @Nonnull String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        boolean permiso = playerManager.isPermisoComandos(p.getName());

        if (strings.length > 3) {
            //no tiene un input el comando
            mensajeError(p);
            p.sendMessage("1");
            return false;
        } else if (strings.length == 0){
            if (dineroManager.getDinero(p) == -1) {
                mensajeJugadorDesconocido(p,p.getName());
                return false;
            }
            String money = DineroManager.dineroToString(dineroManager.getDinero(p), false);
            p.sendMessage("Tu dinero es de " + money + " ElaCoins");
            return true;
        }

        if (strings.length == 1){
            switch (strings[0]) {
                case "all":  // /dinero all
                    dineroManager.dineroTodos(p);
                    return true;
                case "set":
                case "remove":
                case "add":
                    if (permiso) {
                        // /dinero set, /dinero remove, /dinero add
                        // admin commands
                        p.sendMessage("Falta la cantidad y el jugador");
                        return true;
                    } else {
                        mensajeError(p);
                        return false;
                    }

                case "help":
                    p.sendMessage("'/dinero' para ver tu dinero");
                    p.sendMessage("'/dinero all' para el ranking de dinero");
                    p.sendMessage("'/dinero all' para el ranking de dinero");
                    if (permiso) {
                        p.sendMessage("admin commands");
                        p.sendMessage("'/dinero set jugador cantidad' para ponerle al jugador esa cantidad de dinero");
                        p.sendMessage("'/dinero setm jugador cantidad' para ponerle al jugador esos millones de dinero");
                        p.sendMessage("'/dinero add jugador cantidad' para añadirle al jugador esa cantidad de dinero");
                        p.sendMessage("'/dinero remove jugador cantidad' para quitarle al jugador esa cantidad de dinero");
                    }
                    return true;
            }
        }

        if (strings.length == 2){
            if (strings[0].equals("set") || strings[0].equals("remove") || (strings[0].equals("add"))){
                if(permiso){
                    // /dinero set param, /dinero remove param, /dinero add param
                // admin commands
                p.sendMessage("Falta la cantidad o el jugador");
                return true;
                } else {
                    mensajeError(p);
                    return false;
                }
            }
        }

        // admin command
        if (strings.length == 3){
            double money = Double.parseDouble(strings[2]);
            switch (strings[0]) {
                // /dinero set jugador cantidad, /dinero remove jugador cantidad, /dinero add jugador cantidad
                case "set":
                    if(permiso){
                        if (dineroManager.isSetMoney(strings[1], money)) {
                            p.sendMessage("Establecido el dinero de " + strings[1] + " a " + DineroManager.dineroToString(money,true) + " con éxito");
                            return true;
                        } else {
                            mensajeJugadorDesconocido(p, strings[1]);
                            return false;
                        }
                    } else {
                        mensajeError(p);
                        return false;
                    }
                case "setm":
                    if(permiso){
                        if (dineroManager.isSetMoney(strings[1], money*Math.pow(10,6))) {
                            p.sendMessage("Establecido el dinero de " + strings[1] + " a " + DineroManager.dineroToString(money*Math.pow(10,6),true) + " con éxito");
                            return true;
                        } else {
                            mensajeJugadorDesconocido(p, strings[1]);
                            return false;
                        }
                    } else {
                        mensajeError(p);
                        return false;
                    }
                case "add":
                    if(permiso){
                        if (dineroManager.addMoney(strings[1], money)) {
                            p.sendMessage("Áñadido " + DineroManager.dineroToString(money,true) + " a " + strings[1] + " con éxito");
                            return true;
                        } else {
                            mensajeJugadorDesconocido(p, strings[1]);
                            return false;
                        }
                    } else {
                        mensajeError(p);
                        return false;
                    }
                case "remove":
                    if(permiso){
                        if (dineroManager.removeMoney(strings[1], money)) {
                            p.sendMessage("Eliminado " + DineroManager.dineroToString(money,true) + " a " + strings[1] + " con éxito");
                            return true;
                        } else {
                            mensajeJugadorDesconocido(p, strings[1]);
                            return false;
                        }
                    } else {
                        mensajeError(p);
                        return false;
                    }
                default:
                    mensajeError(p);
                    return false;
            }

        }

        return false;
    }
}
