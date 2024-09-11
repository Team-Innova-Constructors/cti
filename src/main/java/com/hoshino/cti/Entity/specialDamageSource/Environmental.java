package com.hoshino.cti.Entity.specialDamageSource;

import net.minecraft.world.damagesource.DamageSource;

public class Environmental extends DamageSource {
    public Environmental(String p_19333_,float amount) {
        super(p_19333_);
        this.AMOUNT=amount;
    }
    public float AMOUNT ;

    public static Environmental ionizedSource(float amount){
        return new Environmental("cti.ionized",amount);
    }
    public static Environmental scorchSource(float amount){
        return new Environmental("cti.scorch",amount);
    }
    public static Environmental frozenSource(float amount){
        return new Environmental("cti.frozen",amount);
    }
}
