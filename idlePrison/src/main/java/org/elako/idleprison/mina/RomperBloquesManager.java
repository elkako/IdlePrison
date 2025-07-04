package org.elako.idleprison.mina;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.elako.idleprison.player.DineroManager;
import org.elako.idleprison.player.PlayerManager;

public class RomperBloquesManager {
    private final MinaManager bloquesPrison;
    private final PlayerManager playerManager;
    private final DineroManager dineroManager;
    private final BloqueManager bloqueManager;

    public RomperBloquesManager(MinaManager bloquesPrison, PlayerManager playerManager, DineroManager dineroManager, BloqueManager bloqueManager) {
        this.bloquesPrison = bloquesPrison;
        this.playerManager = playerManager;
        this.dineroManager = dineroManager;
        this.bloqueManager = bloqueManager;
    }

    public int generarNumeros(int MAX, int MIN) {
        return (int) (Math.floor(Math.random() * (MAX - MIN + 1)) + MIN);
    }
    public boolean probabilidad(double prob){  return (generarNumeros(100, 1)< prob*100); }


    public boolean romperBloque(ItemStack pico, Player p, Block bloque){
        if (isNotPrisonBlock(bloque)) {
            if (!playerManager.isPermisoConstructor(p.getName())){
                return false;
            }
            return true;
        }

        int rojo = pico.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
        int verde = pico.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);

        bloquePrisonRoto(bloque,p, rojo , verde,true); // romper bloque

        // romper en area
        int nivel = pico.getEnchantmentLevel(Enchantment.MULTISHOT);
        if (nivel < 10){ // nivel 1-9
            for (int i = 0; i < nivel*2; i++) {
                Block bloque2 = getBloquesCercanos(bloque);
                int n = 0;
                while (isNotPrisonBlock(bloque2)) { // para que no sea un bloque que no es de la mina
                    bloque2 = getBloquesCercanos(bloque);
                    n++;
                    if (n==20) break;
                }
                Location posicion = new Location(bloque.getLocation().getWorld(), bloque2.getLocation().getBlockX()+0.5,
                        bloque2.getLocation().getBlockY()+0.5, bloque2.getLocation().getBlockZ()+0.5);
                p.spawnParticle(Particle.SMOKE_NORMAL , posicion, 40, 0,0,0);
                bloquePrisonRoto(bloque2, p, rojo , verde, false);
                bloque2.setType(Material.AIR);
            }
        } else { // nivel 9-19
            for (int i = 0; i < nivel*2; i++) {
                Block bloque2 = getBloquesCercanos2(bloque);
                int n = 0;
                while (isNotPrisonBlock(bloque2)) { // para que no sea un bloque que no es de la mina
                    bloque2 = getBloquesCercanos2(bloque);
                    n++;
                    if (n==40) break;
                }
                Location posicion = new Location(bloque.getLocation().getWorld(), bloque2.getLocation().getBlockX()+0.5,
                        bloque2.getLocation().getBlockY()+0.5, bloque2.getLocation().getBlockZ()+0.5);
                p.spawnParticle(Particle.SMOKE_NORMAL , posicion, 40, 0,0,0);

                bloquePrisonRoto(bloque2, p, rojo , verde, false);
                bloque2.setType(Material.AIR);
            }
        }
        return true;
    }

    public boolean isNotPrisonBlock(Block bloque){
            return  !bloquesPrison.isPrison(bloque);
    }

    public void bloquePrisonDrop(Block bloque, Player p, int verde, Boolean damage){
        playerManager.setBloquesRotos(p.getName(), playerManager.getBloquesRotos(p.getName())+1);

        if(PlayerManager.inventarioLleno(p)) {
            p.sendMessage("Inventario lleno no puedes recibir item");
        }
        bloqueManager.drop(bloque,p,damage);

        dineroManager.addMoney(p.getName(),verde*10);
    }

    public void bloquePrisonRoto(Block bloque, Player p, int rojo, int verde, Boolean damage) {
        bloquePrisonDrop(bloque, p, verde, damage);
        switch (rojo) {
            case 1:
                if (probabilidad(0.6)) {
                    bloquePrisonDrop(bloque, p, verde, false);
                    if (damage) p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 100, 2);
                }
                break;
            case 2:
                if (probabilidad(0.5)) {
                    bloquePrisonDrop(bloque, p, verde, false);
                    bloquePrisonDrop(bloque, p, verde, false);
                    if (damage) p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 100, 2);
                } else if (probabilidad(0.7)) {
                    bloquePrisonDrop(bloque, p, verde, false);
                }
                break;
            case 3:
                if (probabilidad(0.45)) {
                    if (damage) p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 100, 2);
                    bloquePrisonDrop(bloque, p, verde, false);
                    bloquePrisonDrop(bloque, p, verde, false);
                    bloquePrisonDrop(bloque, p, verde, false);
                } else if (probabilidad(0.65)) {
                    bloquePrisonDrop(bloque, p, verde, false);
                    bloquePrisonDrop(bloque, p, verde, false);
                } else if (probabilidad(0.8)) {
                    bloquePrisonDrop(bloque, p, verde, false);
                }
                break;
        }
    }

    public Block getBloquesCercanos(Block bloque){
        Block devolver = null;
        int X = bloque.getX();
        int Y = bloque.getY();
        int Z = bloque.getZ();
        int n1 = generarNumeros(26,1);
        int n2 = 1;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if(n1==n2) devolver = bloque.getWorld().getBlockAt(X+i,Y+j,Z+k);
                    n2++;
                }
            }
        }
        return devolver;
    }

    public Block getBloquesCercanos2(Block bloque){
        Block devolver = null;
        int X = bloque.getX();
        int Y = bloque.getY();
        int Z = bloque.getZ();
        int n1 = generarNumeros(45+36,1);
        int n2 = 1;
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                for (int k = -2; k <= 2; k++) {
                    int n = 0;
                    if(Math.abs(i) == 2) n++;
                    if(Math.abs(j) == 2) n++;
                    if(Math.abs(k) == 2) n++;
                    if(n<2){
                        if(n1==n2) devolver = bloque.getWorld().getBlockAt(X+i,Y+j,Z+k);
                        n2++;
                    }

                }
            }
        }
        return devolver;
    }

}
