package com.hoshino.cti.Event;

import com.hoshino.cti.register.ctiEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.hoshino.cti.cti.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class CommonLivingHurt {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void LivingEntityEffectHurt(LivingHurtEvent event){
        if(event.getSource().getEntity() instanceof ServerPlayer player&&player.hasEffect(ctiEffects.numerical_perception.get())){
            event.setAmount(event.getAmount() * 2);
        }
    }
}
