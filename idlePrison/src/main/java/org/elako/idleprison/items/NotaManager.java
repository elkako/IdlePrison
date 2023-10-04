package org.elako.idleprison.items;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.PlayerManager;

import java.util.LinkedList;
import java.util.List;

public class NotaManager {
    private static DineroManager dineroManager;
    private static PlayerManager playerManager;
    private static LinkedList<Nota> notas = new LinkedList<>();

    public NotaManager(DineroManager dineroManager, PlayerManager playerManager) {
        this.dineroManager = dineroManager;
        this.playerManager = playerManager;

        //infierno1
        notas.add(new Nota( notas.size()+1, "Mira la descripción de la nota", List.of( //1
                ChatColor.WHITE + "Las notas vendrán con " + ChatColor.GRAY + "un consejo " + ChatColor.WHITE +"y como",
                ChatColor.WHITE + " se obtuvieron, al pulsar 'Q' con ella en la",
                ChatColor.WHITE + " mano la reclamaras dándote una recompensa ",
                ChatColor.WHITE + " monetarea. "+ ChatColor.GRAY + "Tira esta nota " +  ChatColor.WHITE + "para seguir"
        ),List.of(
                ChatColor.WHITE + " Entra por primera vez a IdlePrison"
        ),1, TipoNota.OTRO));

        notas.add(new Nota( notas.size()+1, "Un nuevo comienzo", List.of( //2
                ChatColor.WHITE + "Con "+ ChatColor.GRAY +"/ip habres el menú," + ChatColor.WHITE + " en la sección",
                ChatColor.WHITE + " mina puedes empezar a minar para conseguir",
                ChatColor.WHITE + " dinero vendiendo y subir de rango"
        ),List.of(
                ChatColor.WHITE + " Reclama una nota"
        ),3, TipoNota.OTRO));

        notas.add(new Nota( notas.size()+1, "Ingresos pasivos", List.of( //3
                ChatColor.WHITE + "Idle es un buen recurso para conseguir",
                ChatColor.WHITE + " liquidez, pero tienes que estar atento",
                ChatColor.WHITE + " solo puedes estar " + ChatColor.GRAY + "10 minutos" + ChatColor.WHITE +
                        " sin recogerlo"
        ),List.of(
                ChatColor.WHITE + " Compra un idle por primera vez"
        ),5, TipoNota.IDLE));

        notas.add(new Nota( notas.size()+1, "Zona secreta 1", List.of( //4
                ChatColor.WHITE + "En cada mina hay un secreto que te da una nota"
        ),List.of(
                ChatColor.WHITE + " Encuentra el secreto de la zona infierno1"
        ),30, TipoNota.SECRETO));

        notas.add(new Nota( notas.size()+1, "Minero empedernido", List.of( //5
                ChatColor.WHITE + "Las minas se regeneran cada 5 minutos aproximádamente",
                ChatColor.WHITE + " puedes utilizar '/mina' si quieres subir encima de",
                ChatColor.WHITE + " una mina rápidamente"
        ),List.of(
                ChatColor.WHITE + " Rompe 30 bloques"
        ),20, TipoNota.MINERO));

        notas.add(new Nota( notas.size()+1, "Minero gay", List.of( //5
                ChatColor.WHITE + "Las minas se regeneran cada 5 minutos aproximádamente",
                ChatColor.WHITE + " puedes utilizar '/mina' si quieres subir encima de",
                ChatColor.WHITE + " una mina rápidamente"
        ),List.of(
                ChatColor.WHITE + " Rompe 30 bloques"
        ),20, TipoNota.MINERO));

        notas.add(new Nota( notas.size()+1, "Mendigo", List.of( //6
                ChatColor.WHITE + "Invierte ese dinero sabiamente,",
                ChatColor.WHITE + " no te lo gastes en drogas"
        ),List.of(
                ChatColor.WHITE + " Consigue 100 E"
        ),15, TipoNota.DINERO));

        notas.add(new Nota( notas.size()+1, "Empresario empedernido", List.of( //7
                ChatColor.WHITE + ""
        ),List.of(
                ChatColor.WHITE + " Consigue 10 idles"
        ),20,TipoNota.IDLE));

        notas.add(new Nota( notas.size()+1, "Vendedor empedernido", List.of( //8
                ChatColor.WHITE + "Ten cuidado de no vender objetos que necesites"
        ),List.of(
                ChatColor.WHITE + " Vende 70 items"
        ),25, TipoNota.VENDER));

        //infierno2
        notas.add(new Nota( notas.size()+1, "Elitista", List.of( //9
                ChatColor.WHITE + "Subir de rango te da acceso a nuevas minas,",
                ChatColor.WHITE + " nuevos crafteos e idles, cada vez costará más"
        ),List.of(
                ChatColor.WHITE + " Sube de rango por primera vez"
        ),40, TipoNota.DINERO));

        notas.add(new Nota( notas.size()+1, "Zona secreta 2", List.of( //todo 10
                ChatColor.WHITE + "En cada mina hay un secreto que te da una nota",
                ChatColor.WHITE + " mira cuidadosamente para encontrarlos"
        ),List.of(
                ChatColor.WHITE + " Encuentra el secreto de la zona infierno2"
        ),70, TipoNota.SECRETO));

        notas.add(new Nota( notas.size()+1, "Fondon", List.of( //todo 11
                ChatColor.WHITE + "La comida es útil para cuando pierdes vida"
        ),List.of(
                ChatColor.WHITE + " Comer 100 alimentos"
        ),70, TipoNota.COMER));

        //infierno3
        notas.add(new Nota( notas.size()+1, "Tecnología arcana", List.of( //todo 12
                ChatColor.WHITE + "Los fragmentos son usados para construir esencias",
                ChatColor.WHITE + " las cuales a su vez sirven para mejorar picos"
        ),List.of(
                ChatColor.WHITE + " Obtén tu primer fragmento de esencia"
        ),70, TipoNota.ESENCIA));

        notas.add(new Nota( notas.size()+1, "Destrucción masiva", List.of( //todo 13
                ChatColor.WHITE + "Aumenta la velocidad con la que mina el",
                ChatColor.WHITE + " pico que encantes"
        ),List.of(
                ChatColor.WHITE + " Craftea tu primera esencia de destrucción"
        ),70, TipoNota.ESENCIA));

        notas.add(new Nota( notas.size()+1, "Mileurista", List.of( //14
                ChatColor.WHITE + "Invierte ese dinero con sabiduría,",
                ChatColor.WHITE + " no te lo gastes en meretrices"
        ),List.of(
                ChatColor.WHITE + " Consigue 1.000 E"
        ),150, TipoNota.DINERO));

        notas.add(new Nota( notas.size()+1, "Zona secreta 3", List.of( //todo 15
                ChatColor.WHITE + "En cada mina hay un secreto que te da una nota",
                ChatColor.WHITE + " mira cuidadosamente para encontrarlos"
        ),List.of(
                ChatColor.WHITE + " Encuentra el secreto de la zona infierno3"
        ),200, TipoNota.SECRETO));

        //infierno4
        notas.add(new Nota( notas.size()+1, "Zona secreta 4", List.of( //16
                ChatColor.WHITE + "En cada mina hay un secreto que te da una nota",
                ChatColor.WHITE + " mira cuidadosamente para encontrarlos"
        ),List.of(
                ChatColor.WHITE + " Encuentra el secreto de la zona infierno2"
        ),500, TipoNota.SECRETO));

        notas.add(new Nota( notas.size()+1, "Mi primer luisbi", List.of( //todo 17
                ChatColor.WHITE + "En cada mina hay un secreto que te da una nota"
        ),List.of(
                ChatColor.WHITE + " Consigue 10.000 E"
        ),1500, TipoNota.DINERO));

        //afueras1

        //afueras2

        //extra1

        //muere por primera vez

        //afueras3



        //consigue primer esencia rojo, verde ,amarillonegro
        //consigue analizador de bloques

        //afueras4

        //renacer primera vez


    }

    //todo añadir get nota por tiponota

    public static int getNum(TipoNota tipo, int n) {
        int contador=0;
        for (Nota nota :notas) {
            if(nota.getTipo().equals(tipo)) contador++;
            if(contador >= n) return nota.getNumero();
        }
        return 0;
    }

    public static void getNota(Player p, int n) {
        if(playerManager.isNotaRecibidas(p.getName(),n)) return;
        p.getInventory().addItem(notas.get(n-1).crearObjeto());
        playerManager.setNotasRecibidas(p.getName(),n);
    }

    public static void getNotaRecompensa(Player p, int n) {
        int i = 0;
        for (Nota nota:notas) {
            if(nota.getNombre().contains("Zona secreta")) i++;
            if(i==n) {
                p.getInventory().addItem(nota.crearObjeto());
                return;
            }
        }
    }

    public static int numNotas(String player){ return playerManager.getNotas(player); }

    public static void sendMensajeRecompensa(Player p, int n) {
        double dinero = notas.get(n-1).getRecompensa();
        dineroManager.addMoney(p.getName(),dinero);
        p.sendMessage("Has conseguido " + DineroManager.dineroToString(dinero)
                + "E por reclamar la nota");

        playerManager.setNotas(p.getName(), n);
        p.sendMessage( numNotas(p.getName()) + "/" + notas.size() + " notas reclamadas");
    }
}
