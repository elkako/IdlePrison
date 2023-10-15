package org.elako.idleprison.mina;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.elako.idleprison.IdlePrison;
import org.elako.idleprison.items.IpMateriales;
import org.elako.idleprison.items.MaterialesManager;
import org.elako.idleprison.player.TreeSkillManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BloqueManager {
    LinkedList<IpBloque> ipBloques = new LinkedList<>();

    public int generarNumeros(int MAX, int MIN) {
        return (int) (Math.floor(Math.random() * (MAX - MIN + 1)) + MIN);
    }

    public boolean probabilidad(double prob){  return (generarNumeros(100, 1)< prob*100); }


    public boolean dropProbabilidad(Player p, double prob, ItemStack is){
        prob += prob * (double) TreeSkillManager.getChanceDrop(p.getName()) /100;
        int n = (int) Math.floor(prob);
        prob = prob-n;
        for (int i = 0; i < n; i++) {
            p.getInventory().addItem(is);
        }
        if(probabilidad(prob)) {
            p.getInventory().addItem(is);
            return true;
        }
        return n>0;
    }

    public void coloresEsencias(Player p, Location pos, Color color1, Color color2){
        Location posicion = new Location(pos.getWorld(),pos.getBlockX()+0.5,pos.getBlockY()+0.5,pos.getBlockZ()+0.5);

        p.spawnParticle(Particle.DUST_COLOR_TRANSITION , posicion, 40, 0,1,0, new Particle.DustTransition(color1,color2,2));
        p.spawnParticle(Particle.DUST_COLOR_TRANSITION, posicion, 40, 1,0,0, new Particle.DustTransition(color1,color2,2));
        p.spawnParticle(Particle.DUST_COLOR_TRANSITION, posicion, 40, 0,0,1, new Particle.DustTransition(color1,color2,2));
        p.spawnParticle(Particle.DUST_COLOR_TRANSITION, posicion, 50, 1,1,1, new Particle.DustTransition(color1,color2,1));
    }

    public IpBloque getBloque(Material material){
        for (IpBloque b: ipBloques) {
            if(material.equals(b.getTipo()))
                return b;
        }
        return null;
    }

    public String getNombre(ItemStack item){
        return MaterialesManager.blockToMaterial(item.getType()).getNombre();
    }

    public void drop(Block block, Player p, Boolean damage){
        IpBloque b = getBloque(block.getType());
        boolean hasDropped = false;

        if(b == null) IdlePrison.broadcast("ERROR: " + block.getType());

        for (DropProb d : b.getDropsProb()) {
            if(dropProbabilidad(p, d.getProb(), d.getItem())) hasDropped = true;
        }

        for (DropCuanti d : b.getDropsCuanti()) {
            hasDropped = true;
            for (int i = 0; i < generarNumeros(d.getMax(),d.getMin()); i++) {
                p.getInventory().addItem(d.getItem());
            }
            dropProbabilidad(p, 0, d.getItem());
        }

        if(damage && hasDropped) b.roto(p,block.getLocation());

    }

    public BloqueManager() {
        ipBloques.add(new IpBloque(Material.DRIED_KELP_BLOCK, new ArrayList<>(), List.of(
                new DropCuanti(MaterialesManager.getItem(IpMateriales.ALGA), 2, 4)
        ), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.CALCITE, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.CALCITA), 1 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.POLVO_HUESO), 0.6 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO), 0.1 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO_CALIDAD), 0.025 )
        ), new ArrayList<>(), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.NETHERRACK, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.PIEDRA_INFIERNO), 0.7 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.PIEDRA_INFIERNO_ROJA), 0.2 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.PIEDRA_INFIERNO_VERDE), 0.1 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO), 0.1 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO_CALIDAD), 0.025 )
        ), new ArrayList<>(), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.MAGMA_BLOCK, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.BLOQUE_MAGMATICO), 1 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.BAYA_LUMINOSA), 0.025 )
        ), new ArrayList<>(), (p, pos) -> p.setFireTicks(60)));

        ipBloques.add(new IpBloque(Material.ICE, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.HIELO), 1 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.PESCADO_CRUDO), 0.4 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.PEZ_GLOBO), 0.05 )
        ), new ArrayList<>(), (p, pos) -> pos.getBlock().setType(Material.AIR)));

        ipBloques.add(new IpBloque(Material.LIGHT_BLUE_TERRACOTTA, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.FRAGMENTO_AZUL1), 0.9)
        ), new ArrayList<>(), (p, pos) -> {
            coloresEsencias(p,pos,Color.BLUE,Color.AQUA);
            p.damage(6);
        } ));

        ipBloques.add(new IpBloque(Material.BONE_BLOCK, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO), 0.1 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.MADERA_INFIERNO_CALIDAD), 0.025 )
        ), Arrays.asList(
                new DropCuanti( MaterialesManager.getItem(IpMateriales.CALCITA), 4, 6 ),
                new DropCuanti( MaterialesManager.getItem(IpMateriales.POLVO_HUESO), 2, 4 )
        ), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.TUFF, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.RESTOS_BASURA), 0.5 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.RESTOS_COMIDA1), 0.3 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.RESTOS_COMIDA2), 0.1 )
        ), List.of(
                new DropCuanti(MaterialesManager.getItem(IpMateriales.PODREDUMBRE), 1, 3)
        ), (p, pos) -> {
            if (probabilidad(0.1)) {
                p.sendMessage(ChatColor.DARK_GREEN + "Te ha dado angustia");
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,60*2,0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON,60*2,2));
            }
        } ));

        ipBloques.add(new IpBloque(Material.STONE, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.PIEDRA), 0.3 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.ROCA), 0.7),
                new DropProb( MaterialesManager.getItem(IpMateriales.MADERA_ROBLE), 0.1)
        ), new ArrayList<>(), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.MELON, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.BAYA_LUMINOSA), 0.1)
        ), List.of(
                new DropCuanti(MaterialesManager.getItem(IpMateriales.SANDIA), 1, 7)
        ), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.SANDSTONE, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.ARENA), 1.2 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.ROCA_ARENOSA), 0.4),
                new DropProb( MaterialesManager.getItem(IpMateriales.MADERA_ROBLE), 0.1),
                new DropProb( MaterialesManager.getItem(IpMateriales.FLOR_DESIERTO), 0.007 )
        ), new ArrayList<>(), (p, pos) -> {
            if (probabilidad(0.02)) {
                p.sendMessage(ChatColor.DARK_GREEN + "Te ha dado calor");
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,60*2,0));
                p.setFireTicks(90);
                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,60*4,2));
                p.damage(4);
            }
        } ));

        ipBloques.add(new IpBloque(Material.RED_TERRACOTTA, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.FRAGMENTO_ROJO1), 0.9)
        ), new ArrayList<>(), (p, pos) -> {
            coloresEsencias(p,pos,Color.RED,Color.FUCHSIA);
            p.setFireTicks(180);
        } ));

        ipBloques.add(new IpBloque(Material.GRANITE, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.MADERA_ROBLE), 0.1),
                new DropProb( MaterialesManager.getItem(IpMateriales.FLOR_DESIERTO), 0.015 )
        ), List.of(
                new DropCuanti(MaterialesManager.getItem(IpMateriales.GRANITO), 3, 6)
        ), (p, pos) -> {
            if (probabilidad(0.05)) {
                p.sendMessage(ChatColor.DARK_GREEN + "Te ha dado calor");
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,60*2,0));
                p.setFireTicks(90);
                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,60*4,2));
                p.damage(4);
            }
        } ));

        ipBloques.add(new IpBloque(Material.LIME_TERRACOTTA, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.FRAGMENTO_VERDE1), 0.5)
        ), new ArrayList<>(), (p, pos) -> {
            coloresEsencias(p,pos,Color.GREEN,Color.LIME);
            p.damage(3);
            p.addPotionEffect(new PotionEffect(PotionEffectType.POISON,60*2,1));
        } ));

        ipBloques.add(new IpBloque(Material.LIGHT_BLUE_CONCRETE, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.FRAGMENTO_AZUL2), 0.4)
        ), new ArrayList<>(), (p, pos) -> {
            coloresEsencias(p,pos,Color.AQUA,Color.WHITE);
            p.damage(10);
        } ));

        ipBloques.add(new IpBloque(Material.SMOOTH_SANDSTONE, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.ARENISCA), 0.7),
                new DropProb( MaterialesManager.getItem(IpMateriales.FLOR_DESIERTO), 0.03 )
        ), List.of(
                new DropCuanti(MaterialesManager.getItem(IpMateriales.ARENA), 1, 3)
        ), (p, pos) -> {
            if (probabilidad(0.05)) {
                p.sendMessage(ChatColor.DARK_GREEN + "Te ha dado calor");
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,60*2,0));
                p.setFireTicks(90);
                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,60*4,2));
                p.damage(4);
            }
        } ));

        ipBloques.add(new IpBloque(Material.SMOOTH_QUARTZ, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.CUARZO), 0.9),
                new DropProb( MaterialesManager.getItem(IpMateriales.FLOR_DESIERTO), 0.015 )
        ), new ArrayList<>(), (p, pos) -> {
            if (probabilidad(0.05)) {
                p.sendMessage(ChatColor.DARK_GREEN + "Te ha dado calor");
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,60*2,0));
                p.setFireTicks(90);
                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,60*4,2));
                p.damage(4);
            }
        } ));

        ipBloques.add(new IpBloque(Material.HAY_BLOCK, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.BAYA_LUMINOSA), 0.1)
        ), List.of(
                new DropCuanti(MaterialesManager.getItem(IpMateriales.TRIGO), 1, 3)
        ), (p, pos) -> {
            if (probabilidad(0.07)) {
                p.sendMessage(ChatColor.DARK_GREEN + "Te ha dado calor");
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,60*2,0));
                p.setFireTicks(90);
                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,60*4,2));
                p.damage(4);
            }
        } ));

        ipBloques.add(new IpBloque(Material.COBBLESTONE, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.PIEDRA), 1)
        ), List.of(
                new DropCuanti(MaterialesManager.getItem(IpMateriales.MINI_ROCA), 2, 7)
        ), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.COAL_ORE, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.PIEDRA), 1 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.CARBON), 0.5)
        ), new ArrayList<>(), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.YELLOW_TERRACOTTA, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.FRAGMENTO_AMARILLO1), 0.6)
        ), new ArrayList<>(), (p, pos) -> {
            coloresEsencias(p,pos,Color.YELLOW,Color.ORANGE);
            p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,60,0));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,60*2,0));
        } ));

        ipBloques.add(new IpBloque(Material.DIORITE, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.NIEVE), 0.3 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.PIEDRA), 1 )
        ), List.of(
                new DropCuanti(MaterialesManager.getItem(IpMateriales.DIORITA), 1, 4)
        ), (p, pos) -> {
            if (probabilidad(0.05)) {
                p.damage(2);
                p.sendMessage(ChatColor.AQUA + "Te ha dado frío");
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,60*2,0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60*2,0));
            }
        } ));

        ipBloques.add(new IpBloque(Material.PACKED_ICE, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.HIELO_COMPACTO), 1 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.SALMON_CRUDO), 0.4 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.NIEVE), 0.3 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.PEZ_GLOBO), 0.2 )
        ), new ArrayList<>(), (p, pos) -> {
            pos.getBlock().setType(Material.AIR);
            if (probabilidad(0.1)) {
                p.damage(2);
                p.sendMessage(ChatColor.AQUA + "Te ha dado frío");
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,60*2,0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60*2,0));
            }
        }));

        ipBloques.add(new IpBloque(Material.IRON_ORE, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.PIEDRA), 3 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.MENA_HIERRO), 0.5)
        ), new ArrayList<>(), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.SPRUCE_LEAVES, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.MANZANA), 1 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.MADERA_ROBLE), 2),
                new DropProb( MaterialesManager.getItem(IpMateriales.HOJAS), 0.5)
        ), new ArrayList<>(), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.REDSTONE_ORE, Arrays.asList(
                new DropProb( MaterialesManager.getItem(IpMateriales.PIEDRA), 4 ),
                new DropProb( MaterialesManager.getItem(IpMateriales.MENA_STYLUM), 0.6 )
        ), List.of(), (p, pos) -> {
            if (probabilidad(0.4)) p.damage(5);
             } ));

        ipBloques.add(new IpBloque(Material.AMETHYST_BLOCK, List.of(
                new DropProb( MaterialesManager.getItem(IpMateriales.PIEDRA_LUNAR), 0.1 )
        ), List.of(
                new DropCuanti(MaterialesManager.getItem(IpMateriales.AMATISTA_CRISTAL), 2, 30)
        ), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.END_STONE, List.of(
                new DropProb( MaterialesManager.getItem(IpMateriales.PIEDRA_LUNAR), 1.3 )
        ), List.of(), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.DEEPSLATE_COAL_ORE, List.of(
                new DropProb( MaterialesManager.getItem(IpMateriales.PIEDRA), 5.7 )
        ), List.of(
                new DropCuanti(MaterialesManager.getItem(IpMateriales.CARBON), 0, 3)
        ), (p, pos) -> {} ));

        ipBloques.add(new IpBloque(Material.LIME_CONCRETE, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.FRAGMENTO_VERDE2), 0.25),
                new DropProb(MaterialesManager.getItem(IpMateriales.FRAGMENTO_VERDE2), 0.1)
        ), new ArrayList<>(), (p, pos) -> {
                coloresEsencias(p,pos,Color.LIME,Color.WHITE);
                p.damage(4);
                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON,60*2,2));
        } ));

        ipBloques.add(new IpBloque(Material.RED_CONCRETE, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.FRAGMENTO_ROJO2), 0.25),
                new DropProb(MaterialesManager.getItem(IpMateriales.FRAGMENTO_ROJO2), 0.1)
        ), new ArrayList<>(), (p, pos) -> {
                coloresEsencias(p,pos,Color.RED,Color.BLACK);
                p.setFireTicks(180);
        } ));

        ipBloques.add(new IpBloque(Material.BLACK_CONCRETE, List.of(
                new DropProb(MaterialesManager.getItem(IpMateriales.FRAGMENTO_OSCURO1), 0.3),
                new DropProb(MaterialesManager.getItem(IpMateriales.FRAGMENTO_OSCURO1), 0.2)
        ), new ArrayList<>(), (p, pos) -> {
                coloresEsencias(p,pos,Color.BLACK,Color.PURPLE);
                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,60*4,1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,60*2,-4));
        } ));

    }
}
