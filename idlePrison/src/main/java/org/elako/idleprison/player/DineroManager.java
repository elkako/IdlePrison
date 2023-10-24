package org.elako.idleprison.player;

import org.bukkit.entity.Player;

import java.util.LinkedList;

import static java.lang.String.*;

public class DineroManager {
    PlayerManager playerManager;

    public DineroManager(PlayerManager player) {
        playerManager = player;
    }

    public static String dineroToString(double dinero, boolean isE){
        String money = Double.toString(dinero);
        char[] moneyArr = money.toCharArray();
        StringBuilder moneyFinal = new StringBuilder();
        int cifras = Integer.parseInt(valueOf(moneyArr[moneyArr.length-1]));

        if (10000000>dinero && dinero>=1000000){ // 1M
            for (int i=0; i<4;i++) {
                if (i == money.indexOf(".")-6) moneyFinal.append(",");
                moneyFinal.append(moneyArr[i]);
            }
            moneyFinal.append("M");
        } else if (1000000>dinero && dinero >= 1000) { // 100.000, 10.000, 1.000
            for (int i=0; i<money.indexOf(".");i++) {
                if (i == money.indexOf(".")-3) moneyFinal.append(".");
                moneyFinal.append(moneyArr[i]);
            }
        }
        else if (dinero<1000) {   // 100, 10,1
            for (int i=0; i<6;i++) {
                if (money.length()<=i) break;

                if(moneyArr[i] == '.') moneyFinal.append(",");
                else moneyFinal.append(moneyArr[i]);
            }

            for (int i=moneyFinal.length()-1; i>0;i--) {
                if (moneyFinal.charAt(i) == '0') moneyFinal.deleteCharAt(i);
                else if (moneyFinal.charAt(i) == ',') {
                    moneyFinal.deleteCharAt(i);
                    break;
                } else break;
            }

        } else if (cifras==7 || cifras==8 || cifras==9){ //100M 10M
            boolean e = false;
            for (int i=0; i<6;i++) {
                if (i==cifras-7 && cifras == 9) moneyFinal.append(".");
                if (i==cifras-4) moneyFinal.append(",");
                if(e) moneyFinal.append(0);
                else if(moneyArr[i] == 'E'){
                    moneyFinal.append(0);
                    e = true;
                }else if(moneyArr[i] != '.')
                    moneyFinal.append(moneyArr[i]);
            }
            moneyFinal.append("M");
        } else{
            int cifras2 = Integer.parseInt(moneyArr[moneyArr.length - 2] + valueOf(moneyArr[moneyArr.length-1]));
            if (cifras2==10 || cifras2==11){
                boolean e = false;
                for (int i=0; i<cifras2-4;i++) {
                    if (i==cifras2-7) moneyFinal.append(".");
                    if(e) moneyFinal.append(0);
                    else if(moneyArr[i] == 'E'){
                        moneyFinal.append(0);
                        e = true;
                    }else if(moneyArr[i] != '.')
                        moneyFinal.append(moneyArr[i]);
                }
                moneyFinal.append("M");
            }
        }
        if(isE) moneyFinal.append("E");

        return valueOf(moneyFinal);
    }

    public void setMoney(String jugador, double money){
        playerManager.diferenciaDinero(jugador,money - playerManager.getDinero(jugador));
        playerManager.setDinero(jugador, money);
    }

    public boolean isSetMoney(String jugador, double money){
        if (playerManager.getPlayer(jugador)!=null) {
            setMoney(jugador,money);
            return true;
        }
        return false;
    }

    public double getDinero(Player p){
        if (playerManager.getPlayer(p.getName())==null) return -1;
        return playerManager.getPlayer(p.getName()).getDinero();
    }

    public double getDinero(String p){
        return playerManager.getPlayer(p).getDinero();
    }

    public boolean addMoney(String p, double money){
        if (playerManager.getPlayer(p)==null) return false;
        playerManager.setDineroRun(p,money + playerManager.getDineroRun(p));
        setMoney(p, money+playerManager.getDinero(p));
        return true;
    }

    public boolean removeMoney(String p, double money){
        if (playerManager.getPlayer(p)==null) return false;
        setMoney(p, playerManager.getDinero(p)-money);
        return true;
    }

    public void dineroTodos(Player p){ // FUNCIONA MAL
        LinkedList<String> jugadores = new LinkedList<>(playerManager.getPlayers());
        p.sendMessage("Ranking de dinero:");
        int i = 1;
        StringBuilder message = new StringBuilder();
        while (jugadores.size()>0){
            double n=0;
            String max = null;
            for (String player: jugadores) {
                if (playerManager.getDinero(player)>n) {
                    n = playerManager.getDinero(player);
                    max = player;
                }
            }
            message.append(i).append(": ").append(max).append("-").append(DineroManager.dineroToString(n,false));
            jugadores.remove(max);
            if (i%3==0) {
                message = new StringBuilder(message.substring(0, message.length() - 2));
                p.sendMessage(message.toString());
                message = new StringBuilder();
            }
            i++;
        }
        if(!message.toString().equals("")) {
            message = new StringBuilder(message.substring(0, message.length() - 2));
            p.sendMessage(message.toString());
        }
    }

}