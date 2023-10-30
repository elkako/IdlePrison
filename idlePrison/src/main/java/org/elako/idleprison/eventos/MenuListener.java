package org.elako.idleprison.eventos;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.crafteos.CrafteoManager;
import org.elako.idleprison.items.materiales.IpMateriales;
import org.elako.idleprison.items.materiales.MaterialesManager;
import org.elako.idleprison.items.VenderManager;
import org.elako.idleprison.player.idle.IdleManager;
import org.elako.idleprison.comandos.*;
import org.elako.idleprison.player.PlayerManager;
import org.elako.idleprison.player.TreeSkillManager;
import org.elako.idleprison.mina.MinaManager;
import org.elako.idleprison.player.rango.RangosManager;

public class MenuListener implements Listener {
    private final TreeSkillManager treeSkillManager;
    private final MinaManager minaManager;
    private final RangosManager rangosManager;
    private final IdleManager idleManager;
    private final PlayerManager playerManager;
    private final CrafteoManager crafteoManager;
    private final VenderManager venderManager;

    public MenuListener(TreeSkillManager treeskill, MinaManager mina, RangosManager rango, IdleManager idle,
                        PlayerManager player, VenderManager vender, CrafteoManager crafteo) {
        playerManager = player;
        idleManager = idle;
        rangosManager = rango;
        treeSkillManager = treeskill;
        minaManager = mina;
        crafteoManager = crafteo;
        venderManager = vender;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getView().getTitle().equals(ChatColor.BOLD + String.valueOf(ChatColor.GREEN) + "Vender")) {
            Player p = (Player) e.getPlayer();
            for (ItemStack i : e.getView().getTopInventory()) {
                if (i == null) continue;
                if (i.getItemMeta() == null) continue;
               if (!i.getType().equals(Material.LIME_STAINED_GLASS_PANE) && !i.getType().equals(Material.GRAY_STAINED_GLASS_PANE)
                        && !i.getType().equals(Material.RED_STAINED_GLASS_PANE) && !i.getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
                    p.getInventory().addItem(i);
                }
            }

        }
    }

    private void manejadorMenu(InventoryClickEvent e, Player p) {
        e.setCancelled(true);         // No mover items

        if (e.getClickedInventory() == null) return;
        if (e.getClickedInventory().equals(e.getView().getBottomInventory())) return;
        if (e.getCurrentItem() == null) return;

        if (e.getCurrentItem().getType().equals(Material.IRON_PICKAXE)) {
            p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 100, 2);
            rangosManager.ascender(p);
            p.openInventory(IdleprisonCom.crearInventario(p));
        } else if (e.getCurrentItem().getType().equals(Material.CRAFTING_TABLE)) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 2);
            Inventory inventario = CrafteoCom.crearInventario(p);
            p.openInventory(inventario);
        } else if (e.getCurrentItem().getType().equals(Material.COAL_ORE)) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 2);
            Inventory inventario = MinaCom.crearInventario(p);
            p.openInventory(inventario);
        } else if (e.getCurrentItem().getType().equals(Material.OAK_SAPLING)) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 2);
            Inventory inventario = TreeSkillCom.crearInventario(p);
            p.openInventory(inventario);
        } else if (e.getCurrentItem().getType().equals(Material.CHEST)) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 2);
            Inventory inventario = IdleCom.crearInventario(p,1);
            p.openInventory(inventario);
        } else if (e.getCurrentItem().getType().equals(Material.GOLD_INGOT)){
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 2);
            Inventory inventario = VenderCom.crearInventario(p);
            p.openInventory(inventario);
        } else if (e.getCurrentItem().getType().equals(Material.NETHER_STAR)){
            treeSkillManager.renacer(p);
            p.closeInventory();
        }
    }

    private void manejadorMinas(InventoryClickEvent e, Player p) {
        e.setCancelled(true);         // No mover items
        if (e.getClickedInventory() == null) return;
        if (e.getClickedInventory().equals(e.getView().getBottomInventory())) return;

       minaManager.interactuar(e, p);
    }

    private void manejadorCraftMenu(InventoryClickEvent e, Player p) {
        e.setCancelled(true);         // No mover items
        if (e.getClickedInventory() == null) return;
        if (e.getClickedInventory().equals(e.getView().getBottomInventory())) return;
        if (e.getCurrentItem() == null) return;

        if (e.getCurrentItem().getType().equals(Material.CRAFTING_TABLE)) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 2);
            p.openWorkbench(null,true);
        } else if (e.getCurrentItem().getType().equals(Material.BOOK)) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 2);

            p.openInventory(CrafteoManager.CraftGuide(p,1));
        } else if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 100, 1.3F);
            p.openInventory(IdleprisonCom.crearInventario(p));
        }
    }

    private void manejadorCraftear(InventoryClickEvent e, Player p) {
        e.setCancelled(true);         // No mover items
        if (e.getClickedInventory() == null) return;
        if (e.getClickedInventory().equals(e.getView().getBottomInventory())) return;
        if (e.getCurrentItem() == null) return;

        if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)){
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 100, 1.3F);
            p.openInventory(CrafteoCom.crearInventario(p));
        } else crafteoManager.interactuarCrafteo(e.getView().getTopInventory(), p);
    }

    private void manejadorCraftguide(InventoryClickEvent e, Player p) {
        e.setCancelled(true);         // No mover items
        if (e.getClickedInventory() == null) return;
        if (e.getClickedInventory().equals(e.getView().getBottomInventory())) return;
        if (e.getCurrentItem() == null) return;

        if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)){
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 100, 1.3F);
            p.openInventory(CrafteoCom.crearInventario(p));
        } else crafteoManager.interactuar(e.getCurrentItem(), p);
    }

    private void manejadorCrafteo(InventoryClickEvent e, Player p) {
        e.setCancelled(true);         // No mover items
        if (e.getClickedInventory() == null) return;
        if (e.getClickedInventory().equals(e.getView().getBottomInventory())) return;
        if (e.getCurrentItem() == null) return;

        if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 100, 1.3F);
            p.openInventory(CrafteoManager.CraftGuide(p,1));
        } else crafteoManager.interactuar(e.getCurrentItem(), p);
    }

    private void manejadorIdle(InventoryClickEvent e, Player p, int cantidad) {
        e.setCancelled(true);         // No mover items
        if (e.getClickedInventory() == null) return;
        if (e.getClickedInventory().equals(e.getView().getBottomInventory())) return;

        idleManager.interactuar(e, p, cantidad);
    }

    private void manejadorVender(InventoryClickEvent e, Player p) {
        if (e.getClickedInventory() == null) return;
        if (e.getClickedInventory().equals(e.getView().getBottomInventory())) return;
        if (e.getCurrentItem() == null) return;

        if (e.getClickedInventory().equals(e.getView().getTopInventory())) {
            if (e.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE)) {
                p.playSound(p.getLocation(),Sound.BLOCK_WOODEN_BUTTON_CLICK_OFF,100,1);
                venderManager.vender(e.getView().getTopInventory().getContents(), p);
                e.setCancelled(true);         // No mover item
            } else if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 100, 1.3F);
                p.openInventory(IdleprisonCom.crearInventario(p));
                e.setCancelled(true);         // No mover item
            } else if (e.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE)) {
                e.setCancelled(true);         // No mover item
            } else if (e.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
                e.setCancelled(true);         // No mover item
            }
        }
    }

    private void manejadorTreeSkill(InventoryClickEvent e, Player p) {
        e.setCancelled(true);         // No mover items
        if (e.getClickedInventory() == null) return;
        if (e.getClickedInventory().equals(e.getView().getBottomInventory())) return;
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;

        if (e.getCurrentItem().getType().equals(Material.YELLOW_CONCRETE)) {
            if (treeSkillManager.getPuntosDisponibles(p.getName())>0){
                p.playSound(p.getLocation(),Sound.BLOCK_STONE_BUTTON_CLICK_ON,100,2);
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Trebol de 4 hojas (click para comprar)")) {
                    playerManager.setTreeSkill(1,p.getName(),1);
                    p.openInventory(TreeSkillCom.crearInventario(p));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Curso de marketing online (click para comprar)")) {
                    playerManager.setTreeSkill(2,p.getName(),1);
                    p.openInventory(TreeSkillCom.crearInventario(p));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Stonks (click para comprar)")) {
                    playerManager.setTreeSkill(3,p.getName(),1);
                    p.openInventory(TreeSkillCom.crearInventario(p));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "El ascendido (click para comprar)")) {
                    playerManager.setTreeSkill(1,p.getName(),2);
                    p.openInventory(TreeSkillCom.crearInventario(p));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Rebajas (click para comprar)")) {
                    playerManager.setTreeSkill(2,p.getName(),2);
                    p.openInventory(TreeSkillCom.crearInventario(p));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Ingresos pasivos (click para comprar)")) {
                    playerManager.setTreeSkill(3,p.getName(),2);
                    p.openInventory(TreeSkillCom.crearInventario(p));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Constitución ancha (click para comprar)")) {
                    playerManager.setTreeSkill(1,p.getName(),3);
                    p.setHealthScale(20+ TreeSkillManager.getHealthBoost(p.getName()));
                    p.openInventory(TreeSkillCom.crearInventario(p));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Minero adolescente (click para comprar)")) {
                    playerManager.setTreeSkill(2,p.getName(),3);
                    p.openInventory(TreeSkillCom.crearInventario(p));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Ahorrador (click para comprar)")) {
                    playerManager.setTreeSkill(3,p.getName(),3);
                    p.openInventory(TreeSkillCom.crearInventario(p));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Haste I (click para comprar)")) {
                    playerManager.setTreeSkill(1,p.getName(),4);
                    p.openInventory(TreeSkillCom.crearInventario(p));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Regeneration I (click para comprar)")) {
                    playerManager.setTreeSkill(2,p.getName(),4);
                    p.openInventory(TreeSkillCom.crearInventario(p));
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Speed I (click para comprar)")) {
                    playerManager.setTreeSkill(3,p.getName(),4);
                    p.openInventory(TreeSkillCom.crearInventario(p));
                }
            } else {
                p.playSound(p.getLocation(),Sound.BLOCK_WOODEN_BUTTON_CLICK_OFF,100,2);
                p.sendMessage("No te quedan puntos");
            }
        } else if (e.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
            p.playSound(p.getLocation(),Sound.BLOCK_WOODEN_BUTTON_CLICK_OFF,100,2);
            p.sendMessage("No te quedan puntos");
        } else if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 100, 1.3F);
            p.openInventory(IdleprisonCom.crearInventario(p));
        }
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getView().getPlayer();

        if(e.getClickedInventory()==null) return;

        if (isMenu(e.getCurrentItem())) {
            p.openInventory(IdleprisonCom.crearInventario(p));
            e.setCancelled(true);
        }

        if (e.getView().getTitle().equals(ChatColor.BOLD + String.valueOf(ChatColor.DARK_RED) + "Menú")) {
            manejadorMenu(e,p);

        } else if (e.getView().getTitle().equals(ChatColor.BOLD + String.valueOf(ChatColor.DARK_GREEN) + "Minas")) {
            manejadorMinas(e,p);

        } else if (e.getView().getTitle().equals(ChatColor.BOLD + String.valueOf(ChatColor.DARK_PURPLE) + "CraftMenu")) {
            manejadorCraftMenu(e,p);

        } else if (e.getView().getTitle().equals(ChatColor.BOLD + String.valueOf(ChatColor.DARK_PURPLE) + "CraftGuide")) {
            manejadorCraftguide(e, p);

        } else if (e.getView().getTitle().equals(ChatColor.BOLD + String.valueOf(ChatColor.RED) + "Craftear")) {
            manejadorCraftear(e, p);

        } else if (e.getView().getTitle().equals(ChatColor.BOLD + String.valueOf(ChatColor.LIGHT_PURPLE) + "Crafteo")) {
            manejadorCrafteo(e, p);

        } else if (e.getView().getTitle().contains(ChatColor.BOLD + String.valueOf(ChatColor.GOLD) + "Idle")) {
            if (e.getView().getTitle().contains("10")) manejadorIdle(e,p,10);
            else if (e.getView().getTitle().contains("64")) manejadorIdle(e,p,64);
            else manejadorIdle(e,p,1);

        } else if (e.getView().getTitle().equals(ChatColor.BOLD + String.valueOf(ChatColor.GREEN) + "Vender")) {
            manejadorVender(e,p);

        } else if (e.getView().getTitle().equals(ChatColor.BOLD + String.valueOf(ChatColor.DARK_AQUA) + "TreeSkill")) {
            manejadorTreeSkill(e,p);

        }

    }

    private boolean isMenu(ItemStack item) {
        if (item == null) return false;
        return item.equals( MaterialesManager.getItem(IpMateriales.MENU) );
    }
}
