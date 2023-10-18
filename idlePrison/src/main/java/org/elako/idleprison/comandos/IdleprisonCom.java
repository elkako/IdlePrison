package org.elako.idleprison.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elako.idleprison.items.*;
import org.elako.idleprison.items.notas.NotaManager;
import org.elako.idleprison.mina.MinaManager;
import org.elako.idleprison.player.IdleManager;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.rango.Rangos;
import org.elako.idleprison.player.rango.RangosManager;
import org.elako.idleprison.player.PlayerManager;
import org.elako.idleprison.player.TreeSkillManager;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class IdleprisonCom implements CommandExecutor {
    private static RangosManager rangoManager;
    private static DineroManager dineroManager;
    private static PlayerManager playerManager;
    private static TreeSkillManager treeSkillManager;
    private final MaterialesManager materialesManager;
    private final MinaManager minaManager;

    public IdleprisonCom(RangosManager rango, DineroManager dinero, PlayerManager player, TreeSkillManager treeSkill,
                         MinaManager mina, MaterialesManager materiales) {
        materialesManager = materiales;
        playerManager = player;
        dineroManager = dinero;
        rangoManager = rango;
        treeSkillManager = treeSkill;
        minaManager = mina;
    }

    public static ItemStack crearObjetoLore(Material material, String nombre, int amount, List<String> lore) {
        ItemStack item = new ItemStack(material,amount);
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) return item;
        itemMeta.setDisplayName(nombre);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack crearObjetoLore(Material material, String boton, List<String> lore) {
        return crearObjetoLore(material, boton, 1, lore);
    }

    public static ItemStack crearObjeto(Material material, String nombre, int amount) {
        ItemStack item = new ItemStack(material,amount);
        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) return item;
        itemMeta.setDisplayName(nombre);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack crearObjeto(Material material, String boton) {
        return crearObjeto(material, boton, 1);
    }

    public static Inventory crearInventario(Player p) {
        // tamaños inventarios: 9 18 27 36 45 54
        Inventory inventario = Bukkit.createInventory(p, 45, ChatColor.BOLD + String.valueOf(ChatColor.DARK_RED) + "Menú");

        Rangos rango = rangoManager.getPlayer(p);
        double dineroP = dineroManager.getDinero(p);
        double dineroAscender = rangoManager.getDineroAscender(rango, p.getName());

        inventario.setItem(10, crearObjetoLore(Material.PLAYER_HEAD, ChatColor.RED + "Éstadisticas", Arrays.asList(
                ChatColor.WHITE + "Nivel de renacer: " + treeSkillManager.getNivelRenacer(p.getName()),
                ChatColor.WHITE + "Rango: " + rango.toString().toLowerCase(),
                ChatColor.WHITE + "Dinero: " + DineroManager.dineroToString(dineroP)  +"laCoins" ) ));
        if (dineroAscender>dineroP)
            inventario.setItem(12, crearObjetoLore(Material.IRON_PICKAXE, ChatColor.RED + "Rankup",Arrays.asList(
                    ChatColor.WHITE + "Haz click aquí para subir de rango",
                    ChatColor.WHITE +  "Siguiente rango: " + rangoManager.siguienteRango(p.getName()).toLowerCase(),
                    ChatColor.WHITE + "Dinero necesario: " + DineroManager.dineroToString(dineroAscender)
                            + ", te falta: "  + DineroManager.dineroToString(dineroAscender-dineroP)) ));
        else
            inventario.setItem(12, crearObjetoLore(Material.IRON_PICKAXE, ChatColor.RED + "Rankup",Arrays.asList(
                    ChatColor.WHITE + "Haz click aquí para subir de rango",
                    ChatColor.WHITE +  "Siguiente rango: " + rangoManager.siguienteRango(p.getName()).toLowerCase(),
                    ChatColor.WHITE + "Dinero necesario: " + DineroManager.dineroToString(dineroAscender)
                            + ", te sobra: "  + DineroManager.dineroToString(dineroP-dineroAscender)) ));
        inventario.setItem(14, crearObjetoLore(Material.CHEST, ChatColor.RED + "Idle", Arrays.asList(
                ChatColor.WHITE + "Clickea para ir al menu idle y coneguir dinero ",
                ChatColor.WHITE + "Dinero acumulado: " + DineroManager.dineroToString(playerManager.getDineroAcum(p.getName())),
                ChatColor.WHITE + "Tiempo sin recoger: " + IdleManager.tiempoToString(playerManager.getPlayer(p.getName()).getTimeTotal())
                )  ));
        inventario.setItem(16, crearObjetoLore(Material.CRAFTING_TABLE, ChatColor.RED + "Crafteos", Arrays.asList(
                ChatColor.WHITE + "Clickea para ver una guía de todas ",
                ChatColor.WHITE + "las recetas que tienes disponibles" ) ));
        if (rangoManager.isPermitido(p.getName(),Rangos.CAMPESINO1) || treeSkillManager.getDineroRenacer(p.getName()) > 0 ){
            inventario.setItem(28, crearObjetoLore(Material.GOLD_INGOT, ChatColor.GOLD + "Vender", List.of(
                    ChatColor.WHITE + "Haz click aquí para vender objetos")));
            inventario.setItem(30, crearObjetoLore(Material.COAL_ORE, ChatColor.GOLD + "Minas", Arrays.asList(
                    ChatColor.WHITE + "Haz click aquí para ir al menú de",
                    ChatColor.WHITE + "teletransporte de las minas" )  ));
            inventario.setItem(32, crearObjetoLore(Material.OAK_SAPLING, ChatColor.GOLD + "TreeSkill", Arrays.asList(
                    ChatColor.WHITE + "Haz click para ver el árbol de habilidades",
                    ChatColor.WHITE + "Puntos disponibles: " )  ));
            inventario.setItem(34, crearObjetoLore(Material.NETHER_STAR, ChatColor.GOLD + "Despertar", Arrays.asList(
                    ChatColor.WHITE + "Clickea aquí para despertar y conseguir " ,
                    ChatColor.WHITE + "tantos puntos para treeskill como nivel tengas" ,
                    ChatColor.WHITE + "Nivel actual: "+ treeSkillManager.getNivelRenacer(p.getName()) ,
                    ChatColor.WHITE + "Te quedarás en nivel: "+ treeSkillManager.getNivelTotal(p.getName()),
                    ChatColor.WHITE + "Dinero para siguiente: " + DineroManager.dineroToString( treeSkillManager.dineroPaAscender( treeSkillManager.getDineroTotal(p.getName()) ) ) ,
                    ChatColor.WHITE + "Te queda: " + DineroManager.dineroToString( treeSkillManager.ascenderRestos( treeSkillManager.getDineroTotal(p.getName()) ) )   ,
                    ChatColor.WHITE + "Has conseguido: " + DineroManager.dineroToString( treeSkillManager.getDineroRun(p.getName()) ) ) ));
        } else {
            inventario.setItem(30, crearObjetoLore(Material.GOLD_INGOT, ChatColor.GOLD + "Vender", List.of(
                    ChatColor.WHITE + "Haz click aquí para vender objetos")));
            inventario.setItem(32, crearObjetoLore(Material.COAL_ORE, ChatColor.GOLD + "Minas", Arrays.asList(
                    ChatColor.WHITE + "Haz click aquí para ir al menú de",
                    ChatColor.WHITE + "teletransporte de las minas" )  ));
        }

        return inventario;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command,
                             @Nonnull String s, @Nonnull String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        boolean permiso = playerManager.isPermisoComandos(p.getName());

        if ( strings.length > 3) {
            //no tiene un input el comando
            p.sendMessage("Error, pon '/ip help' para ver el uso correcto");
            return false;
        }

        if (strings.length == 0) {
            if(!rangoManager.isPermitido(p.getName(), Rangos.CONDENADO4)) return false;
            p.openInventory(crearInventario(p));
            return true;
        } else if (strings.length == 1) {
            if (strings[0].equals("reiniciar")  && permiso) {
                playerManager.recogerDineroAcum(p.getName());
                playerManager.setDinero(p.getName(), 0.0);
                rangoManager.setPlayer(p.getName(), Rangos.CONDENADO4.toString());
                for (int i = 1; i <= 6; i++) {
                    playerManager.setIdle(i, p.getName(), 0);
                }
                minaManager.tpMina(p, "mina1");

                for (ItemStack i : p.getInventory().getContents()) {
                    if (i == null) continue;
                    i.setAmount(0);
                }
                playerManager.reloadTimeOffline(p.getName());
                playerManager.setDineroRenacer(p.getName(), 0);
                playerManager.setDineroRun(p.getName(), 0);
                p.getInventory().addItem(MaterialesManager.getItem(IpMateriales.PICO_MADERA));
                p.getInventory().setItem(8, MaterialesManager.getItem(IpMateriales.MENU));
                for (int i = 1; i <= 3; i++) {
                    playerManager.setTreeSkill(i, p.getName(), 0);
                }
                p.sendMessage("RELOADED");
                return true;
            } else if (strings[0].equals("help")){
                p.sendMessage("'/ip' o '/idleprison' para ver el menú general");
                if (permiso){
                    p.sendMessage("admin commands");
                    p.sendMessage("'/ip encantar color nivel' para encantar el objeto de tu mano, colores: azul, rojo," +
                            " verde, amarillo y negro ");
                    p.sendMessage("'/ip reiniciar' para reiniciar el idleprison");
                    p.sendMessage("'/ip materiales n' para ver el menú de items n");
                    p.sendMessage(" '/ip getnota nota' para recibir nota");
                }
                if (p.hasPermission("op")) p.sendMessage("'/ip setperm nm' para ver cambiar los permisos de un jugador" +
                        " el n es el permiso de constructor y el segundo de comandos ");
                return true;
            }
        }else if (strings.length == 2) {
            if (strings[0].equals("getnota") && permiso) {
                NotaManager.getNota( p, Integer.parseInt(strings[1]) );
                return true;
            } else if (strings[0].equals("materiales")  && permiso) {
                // tamaños inventarios: 9 18 27 36 45 54
                Inventory inventario = Bukkit.createInventory(p, 54, ChatColor.BOLD + String.valueOf(ChatColor.DARK_AQUA) + "-=(materiales)=-");
                if (strings[1].equals("1")){

                    int i = 0;
                    for (IpMaterial m : materialesManager.getMateriales()) {
                        inventario.setItem(i,m.getItem(1));
                        i++;
                        if (i == 54) break;
                    }
                } else {
                    int j = 0;
                    int i = 0;
                    for (IpMaterial m : materialesManager.getMateriales()) {
                        if (j<54) j++;
                        else {
                            inventario.setItem(i,m.getItem(1));
                            i++;
                            if (i == 54) break;
                        }
                    }
                }
                p.openInventory(inventario);
                return true;
            }
        } else {
            if (strings[0].equals("encantar") && permiso) {
                ItemMeta im = p.getInventory().getItemInMainHand().getItemMeta();

                assert im != null;
                switch (strings[1].toLowerCase()) {
                    case "azul":
                        im.addEnchant(Enchantment.DIG_SPEED, Integer.parseInt(strings[2]), true);
                        p.getInventory().getItemInMainHand().setItemMeta(im);
                        return true;
                    case "rojo":
                        im.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, Integer.parseInt(strings[2]), true);
                        p.getInventory().getItemInMainHand().setItemMeta(im);
                        return true;
                    case "verde":
                        im.addEnchant(Enchantment.LOOT_BONUS_MOBS, Integer.parseInt(strings[2]), true);
                        p.getInventory().getItemInMainHand().setItemMeta(im);
                        return true;
                    case "amarillo":
                        im.addEnchant(Enchantment.CHANNELING, Integer.parseInt(strings[2]), true);
                        p.getInventory().getItemInMainHand().setItemMeta(im);
                        return true;
                    case "negro":
                        im.addEnchant(Enchantment.MULTISHOT, Integer.parseInt(strings[2]), true);
                        p.getInventory().getItemInMainHand().setItemMeta(im);
                        return true;
                }
            } else if (p.hasPermission("op")){
                if (strings[0].equals("setperm")) {
                    switch (strings[2]) {
                        case "00":
                        case "01":
                        case "10":
                        case "11":
                            playerManager.setPermiso(strings[1],strings[2]);
                            break;
                        case "constructor":
                            if(playerManager.isPermisoComandos(strings[1])) playerManager.setPermiso(strings[1],"11");
                            else playerManager.setPermiso(strings[1],"10");
                            break;
                        case "comandos":
                            if(playerManager.isPermisoConstructor(p.getName())) playerManager.setPermiso(strings[1],"11");
                            else playerManager.setPermiso(strings[1],"01");
                            break;
                        default:
                            mensajeError(p);
                            return false;
                    }
                    String perm = "";

                    if(playerManager.isPermisoConstructor(p.getName())) perm += "1";
                    else perm += "0";

                    if(playerManager.isPermisoComandos(p.getName())) perm += "1";
                    else perm += "0";

                    p.sendMessage("Permiso puesto en " + perm + " a " + strings[1]);
                    return true;
                }
            }
        }
        mensajeError(p);
        return false;
    }

    private void mensajeError(Player p) {
        p.sendMessage("comando uso incorrecto");
    }

}
