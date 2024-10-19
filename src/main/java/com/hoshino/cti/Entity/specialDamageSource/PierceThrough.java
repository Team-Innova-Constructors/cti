package com.hoshino.cti.Entity.specialDamageSource;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class PierceThrough extends EntityDamageSource {
    public float AMOUNT ;
    public PierceThrough(Player player,Float amount) {
        super("player",player);
        this.AMOUNT=amount;
    }
    public PierceThrough(String name, LivingEntity entity, Float amount) {
        super(name,entity);
        this.AMOUNT=amount;
        this.bypassInvul();
    }
    public static DamageSource pierceDamage(Player player, Float amount){
        return new PierceThrough(player,amount);
    }
    public float getAMOUNT(){
        return this.AMOUNT;
    }
}
