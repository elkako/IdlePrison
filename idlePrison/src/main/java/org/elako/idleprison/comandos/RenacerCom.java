package org.elako.idleprison.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elako.idleprison.player.rango.Rangos;
import org.elako.idleprison.player.rango.RangosManager;
import org.elako.idleprison.player.PlayerManager;
import org.elako.idleprison.player.TreeSkillManager;


public class RenacerCom implements CommandExecutor {
    TreeSkillManager treeSkillManager;
    PlayerManager playerManager;
    RangosManager rangosManager;

    public RenacerCom(TreeSkillManager treeSkillManager, PlayerManager playerManager, RangosManager rangosManager) {
        this.treeSkillManager = treeSkillManager;
        this.playerManager = playerManager;
        this.rangosManager = rangosManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;

        if (rangosManager.isPermitido(p.getName(), Rangos.CAMPESINO1)) treeSkillManager.renacer(p);
        else p.sendMessage("No tienes rango suficiente, sorry");

        return false;
    }
}
