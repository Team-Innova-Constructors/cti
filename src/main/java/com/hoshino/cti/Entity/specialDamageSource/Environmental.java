package com.hoshino.cti.Entity.specialDamageSource;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class Environmental extends DamageSource {
    public Environmental(String p_19333_,float amount) {
        super(p_19333_);
        this.AMOUNT=amount;
    }
    public float AMOUNT ;
    public float getAMOUNT(){
        return this.AMOUNT;
    }

    public static Environmental ionizedSource(float amount){
        return new Environmental("cti.ionized",amount);
    }
    public static Environmental scorchSource(float amount){
        return new Environmental("cti.scorch",amount);
    }
    public static Environmental frozenSource(float amount){
        return new Environmental("cti.frozen",amount);
    }
    public static Environmental pressureSource(float amount){
        return new Environmental("cti.pressure",amount);
    }

    public static PierceThrough playerIonizedSource(float amount, LivingEntity entity){
        return new PierceThrough("cti.ionized",entity,amount);
    }
    public static PierceThrough playerScorchSource(float amount, LivingEntity entity){
        return new PierceThrough("cti.scorch",entity,amount);
    }
    public static PierceThrough playerFrozenSource(float amount, LivingEntity entity){
        return new PierceThrough("cti.frozen",entity,amount);
    }
    public static PierceThrough playerPressureSource(float amount, LivingEntity entity){
        return new PierceThrough("cti.pressure",entity,amount);
    }
}
