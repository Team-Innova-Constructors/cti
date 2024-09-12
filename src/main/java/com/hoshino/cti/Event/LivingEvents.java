package com.hoshino.cti.Event;

import com.hoshino.cti.Entity.specialDamageSource.Environmental;
import com.hoshino.cti.Entity.specialDamageSource.PierceThrough;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;

public class LivingEvents {
    public LivingEvents(){
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST,this::onPierceDamage);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST,this::onPierceAttack);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST,this::onPierceHurt);
    }

    private void onPierceHurt(LivingHurtEvent event) {
        if (event.getSource() instanceof PierceThrough source){
            event.setAmount(source.getAMOUNT());
            event.setCanceled(false);
        }
        else if (event.getSource() instanceof Environmental source){
            event.setAmount(source.getAMOUNT());
            event.setCanceled(false);
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
