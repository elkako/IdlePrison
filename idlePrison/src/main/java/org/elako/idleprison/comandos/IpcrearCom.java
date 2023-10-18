package org.elako.idleprison.comandos;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.mina.MinaManager;
import org.elako.idleprison.player.PlayerManager;

import javax.annotation.Nonnull;

public class IpcrearCom implements CommandExecutor {
    private final MinaManager minaMg;
    private final PlayerManager playerManager;
    private Location previa1 = null;
    private Location previa2 = null;

    public IpcrearCom(MinaManager mina, PlayerManager player) {
        minaMg = mina;
        playerManager = player;
    }

    public boolean isPrevia1() {return previa1 != null;}
    public boolean isPrevia2(){return previa2 != null;}

    public void restartPrevia(){
        previa1 = null;
        previa2 = null;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command,
                             @Nonnull String s, @Nonnull String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        if ( strings.length != 1) {
            //no tiene un input el comando
            p.sendMessage("Falló");
            return false;
        }

        if(playerManager.isPermisoComandos(p.getName())) {
            switch (strings[0]) {
                case "1":  // /ipcrear 1
                    previa1 = p.getLocation();
                    p.sendMessage("uno");
                    return true;
                case "2":  // /ipcrear 2
                    p.sendMessage("dos");
                    previa2 = p.getLocation();
                    return true;

                // añadir mina

                case "help":
                    p.sendMessage("admin commands");
                    p.sendMessage("'/Ipcrear 1' seleccionar una esquina de la mina");
                    p.sendMessage("'/Ipcrear 2' seleccionar la otra esquina de la mina");
                    p.sendMessage("'/Ipcrear minaN' para crear una mina habiendo seleccionando los límites, siendo N el" +
                            "número de la mina");
                    return true;
                default:  // /ipcrear mina
                if (isPrevia1() && isPrevia2()) {
                    if (minaMg.setBloques(strings[0], previa1, previa2)){
                        IdlePrison.getPlugin().escribirMina(strings[0], previa1, previa2);
                        restartPrevia();
                        return true;
                    }
                    p.sendMessage("Esa mina no existe'");
                    return false;
                }
                p.sendMessage("Falta seleccionar primero los bloques de la mina con '/mina 1' y '/mina 2'");
                return false;
            }
        }
            p.sendMessage("Permiso denegado");
        return false;
    }

}
