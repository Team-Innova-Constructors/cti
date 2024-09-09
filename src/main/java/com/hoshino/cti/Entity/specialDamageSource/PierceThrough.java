package com.hoshino.cti.Entity.specialDamageSource;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.player.Player;

public class PierceThrough extends EntityDamageSource {
    public float AMOUNT ;
    public PierceThrough(Player player,Float amount) {
        super("player",player);
        this.AMOUNT=amount;
    }
    public static final DamageSource pierceDamage(Player player,Float amount){
        return new PierceThrough(player,amount);
    }
    public float getAMOUNT(){
        return this.AMOUNT;
    }
}
