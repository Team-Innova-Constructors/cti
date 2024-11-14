package com.hoshino.cti.Event;

import com.hoshino.cti.Entity.DisposibleFakePlayer;
import com.hoshino.cti.Entity.specialDamageSource.Environmental;
import com.hoshino.cti.Entity.specialDamageSource.PierceThrough;
import com.hoshino.cti.register.ctiEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;

public class LivingEvents {
    public LivingEvents(){
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST,this::onPierceDamage);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST,this::onPierceAttack);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST,this::onPierceHurt);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST,this::onFakePlayerHurt);
    }

    private void onFakePlayerHurt(LivingHurtEvent event) {
        if(event.getEntity().getMaxHealth()>10000&&event.getSource().getEntity() instanceof FakePlayer fakePlayer&&!(fakePlayer instanceof DisposibleFakePlayer)){
            event.setCanceled(true);
        }
    }

    private void onPierceHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player){
            MobEffectInstance instance = player.getEffect(ctiEffects.stress.get());
            if (instance!=null&&instance.getDuration()>0){
                int level = instance.getAmplifier()+1;
                float red = Math.max(0.9f,1-0.04f*level);
                event.setAmount(event.getAmount()*red);
            }
        }
        if (event.getSource() instanceof PierceThrough source){
            event.setAmount(source.getAMOUNT());
            event.setCanceled(false);
        }
        else if (event.getSource() instanceof Environmental source){
            event.setAmount(source.getAMOUNT());
            event.setCanceled(false);
        }
        if (event.getEntity() instanceof Warden warden&& event.getSource().getMsgId().equals("sonic_boom")){
            event.setAmount(warden.getMaxHealth()/4);
        }
    }

    private void onPierceAttack(LivingAttackEvent event) {
        if (event.getSource() instanceof PierceThrough source){
            event.setCanceled(false);
        }
        else if (event.getSource() instanceof Environmental source){
            event.setCanceled(false);
        }
    }

    public void onPierceDamage(LivingDamageEvent event) {
        if (event.getSource() instanceof PierceThrough source){
            event.setAmount(source.getAMOUNT());
            event.setCanceled(false);
        }
        else if (event.getSource() instanceof Environmental source){
            event.setAmount(source.getAMOUNT());
            event.setCanceled(false);
        }
    }
}
