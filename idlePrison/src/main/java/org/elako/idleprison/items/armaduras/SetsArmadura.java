package org.elako.idleprison.items.armaduras;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum SetsArmadura {
   ELEMENTAL( null ) , SANDIA( new PotionEffect(PotionEffectType.HUNGER,100,0) ),
    DESIERTO_ROJO( new PotionEffect(PotionEffectType.FIRE_RESISTANCE,100,0) ),
   CUARZO( new PotionEffect(PotionEffectType.GLOWING,20,0) ),
   ;

   private final PotionEffect efecto;

   SetsArmadura( PotionEffect efecto ){ this.efecto = efecto; }

   public PotionEffect getEfecto() { return efecto; }

}
