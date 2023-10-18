package org.elako.idleprison.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.player.rango.RangosManager;
import org.elako.idleprison.player.PlayerManager;
import org.elako.idleprison.player.TreeSkillManager;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class TreeSkillCom implements CommandExecutor {
    static TreeSkillManager treeSkillManager;
    RangosManager rangosManager;
    static PlayerManager playerManager;

    public TreeSkillCom(TreeSkillManager treeskill, RangosManager rango, PlayerManager player) {
        treeSkillManager = treeskill;
        rangosManager = rango;
        playerManager = player;
    }
    public static boolean quedanPuntos(String p){
        return treeSkillManager.getPuntosDisponibles(p) > 0;
    }

    private static ItemStack botonTreeSkill(String p, int lvlActual, int lvlNecesario, String nombre, LinkedList<String> lore) {
        if (lvlActual>lvlNecesario) return IdleprisonCom.crearObjetoLore(Material.GREEN_CONCRETE, ChatColor.GREEN + nombre + " (comprado)" , lore);
        else if (lvlActual>lvlNecesario-1) {
            if (quedanPuntos(p)) {
                lore.add(ChatColor.WHITE + "Te quedan " + treeSkillManager.getPuntosDisponibles(p) + " puntos de treeSkill" );
                return IdleprisonCom.crearObjetoLore(Material.YELLOW_CONCRETE, ChatColor.YELLOW + nombre + " (click para comprar)", lore );
            }
            else return IdleprisonCom.crearObjetoLore(Material.RED_CONCRETE, ChatColor.RED + nombre + " (no te quedan puntos)",lore );
        }
        else return IdleprisonCom.crearObjetoLore(Material.GRAY_CONCRETE, ChatColor.GRAY + nombre + " (no alcanzable)",lore );

    }

    public static Inventory crearInventario(Player p) {
        Inventory inventario = Bukkit.createInventory(p, 54, ChatColor.BOLD + String.valueOf(ChatColor.DARK_AQUA) + "TreeSkill");
        // tamaños inventarios: 9 18 27 36 45 54
        int lvlTrab = playerManager.getTreeSkill(1,p.getName());
        int lvlEario = playerManager.getTreeSkill(2,p.getName());
        int lvlEdor = playerManager.getTreeSkill(3,p.getName());

        for (int i=0;i<54;i++) {
            switch (i){
                case 0:
                    inventario.setItem(i, IdleprisonCom.crearObjetoLore(Material.GOLDEN_PICKAXE,ChatColor.WHITE + "Trabajador", List.of(
                            ChatColor.WHITE + String.valueOf(lvlTrab) + " puntos invertidos en esta rama"
                    )));
                    break;
                case 18:
                    inventario.setItem(i, IdleprisonCom.crearObjetoLore(Material.RAW_GOLD,ChatColor.WHITE + "Empresario", List.of(
                            ChatColor.WHITE + String.valueOf(lvlEario) + " puntos invertidos en esta rama"
                    )));
                    break;
                case 36:
                    inventario.setItem(i, IdleprisonCom.crearObjetoLore(Material.GOLDEN_BOOTS,ChatColor.WHITE + "Emprendedor", List.of(
                            ChatColor.WHITE + String.valueOf(lvlEdor) + " puntos invertidos en esta rama"
                    )));
                    break;
                case 2:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlTrab, 0, "Trebol de 4 hojas", new LinkedList<>(
                            List.of(ChatColor.WHITE + "Al romper un bloque +50% chance cada drop")) ));
                    break;
                case 20:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlEario, 0, "Curso de marketing online", new LinkedList<>(
                            List.of( ChatColor.WHITE + "+25% de dinero al vender items" )) ));
                    break;
                case 38:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlEdor, 0, "Stonks", new LinkedList<>(
                            List.of( ChatColor.WHITE + "+25% a cualquier ingreso de idle" )) ));
                    break;
                case 4:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlTrab, 1, "El ascendido", new LinkedList<>(
                            List.of( ChatColor.WHITE + "-20% de dinero para ascender de rango" )) ));
                    break;
                case 22:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlEario, 1, "Rebajas", new LinkedList<>(
                            List.of( ChatColor.WHITE + "-20% precio al comprar en idle" )) ));
                    break;
                case 40:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlEdor, 1, "Ingresos pasivos", new LinkedList<>(
                            List.of( ChatColor.WHITE + "+5 minutos de inactividad en idle" )) ));
                    break;
                case 6:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlTrab, 2, "Constitución ancha", new LinkedList<>(
                            List.of( ChatColor.WHITE + "Te añade 5 corazones más de vida" )) ));
                    break;
                case 24:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlEario, 2, "Minero adolescente", new LinkedList<>(
                            List.of( ChatColor.WHITE + "Los niños mineros dan x2 de ingresos" )) ));
                    break;
                case 42:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlEdor, 2, "Ahorrador", new LinkedList<>(
                            List.of( ChatColor.WHITE + "Al renacer recibes 100 E" )) ));
                    break;
                case 8:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlTrab, 3, "Haste I", new LinkedList<>(
                            List.of( ChatColor.WHITE + "Te da el efecto de Prisa 1 continuamente" )) ));
                    break;
                case 26:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlEario, 3, "Regeneration I", new LinkedList<>(
                            List.of( ChatColor.WHITE + "Te da el efecto de Regeneración 1 continuamente" )) ));
                    break;
                case 44:
                    inventario.setItem(i, botonTreeSkill(p.getName(), lvlEdor, 3, "Speed I", new LinkedList<>(
                            List.of( ChatColor.WHITE + "Te da el efecto de Velocidad 1 continuamente" )) ));
                    break;
                case 45:
                    inventario.setItem(i, IdleprisonCom.crearObjeto(Material.RED_STAINED_GLASS_PANE,ChatColor.WHITE + "SALIR"));
                    break;
                case 53:
                    inventario.setItem(i, IdleprisonCom.crearObjeto(Material.GREEN_STAINED_GLASS_PANE,ChatColor.WHITE + "SIGUIENTE"));
                default:
                    if (i<=8 && i!=1) inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.GLASS_PANE," "));
                    else if (i<=17) inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.BLACK_STAINED_GLASS_PANE," "));
                    else if (i<=26 && i!=19) inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.GLASS_PANE," "));
                    else if (i<=35) inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
                    else if (i<=44 && i!=37) inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.GLASS_PANE," "));
                    else inventario.setItem(i,  IdleprisonCom.crearObjeto(Material.GRAY_STAINED_GLASS_PANE," "));
            }
        }

        return inventario;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command,
                             @Nonnull String s, @Nonnull String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;

        // tamaños inventarios: 9 18 27 36 45 54

        p.openInventory(crearInventario(p));

        return false;
    }
}
