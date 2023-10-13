package org.elako.idleprison.items.armaduras;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.elako.idleprison.player.Rangos;

public enum SetsArmadura {
   ELEMENTAL( null ) , SANDIA( new PotionEffect(PotionEffectType.HUNGER,100,0) ) ;

   private PotionEffect efecto;

   SetsArmadura( PotionEffect efecto ){
      this.efecto = efecto;
   }

   public PotionEffect getEfecto() {
      return efecto;
   }
}
