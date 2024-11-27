package com.hoshino.cti.Event;

import com.hoshino.cti.register.ctiEffects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.EventPriority;

public class MobEffect {
    public MobEffect(){
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH,this::HasResoluteEffect);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL,this::ResoluteEffectProtect);
    }
    private void HasResoluteEffect(MobEffectEvent.Remove event) {
        if(event.getEntity()!=null&&event.getEntity().hasEffect(ctiEffects.resolute.get())){
            if(event.getEffectInstance()!=null){
                if(event.getEffectInstance().getEffect().isBeneficial()){
                    event.setCanceled(true);
                }
            }
        }
    }
    private void ResoluteEffectProtect(MobEffectEvent.Applicable event) {
        if(event.getEntity() != null) {
            if(event.getEntity()!=null&&event.getEntity().hasEffect(ctiEffects.resolute.get())&&!event.getEffectInstance().getEffect().isBeneficial()){
                event.setCanceled(true);
            }
        }
    }

}
