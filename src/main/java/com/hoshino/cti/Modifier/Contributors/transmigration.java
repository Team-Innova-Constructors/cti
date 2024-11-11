package com.hoshino.cti.Modifier.Contributors;

import com.hoshino.cti.register.ctiEffects;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import com.marth7th.solidarytinker.shelf.damagesource.tinkerdamage;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;

public class transmigration extends BattleModifier {
    {
        MinecraftForge.EVENT_BUS.addListener(this::LivingDeathEvent);
        MinecraftForge.EVENT_BUS.addListener(this::EffectClear);
    }

    private void EffectClear(MobEffectEvent.Remove event) {
        if(event.getEffect()==ctiEffects.ev.get()){
            if(event.getEntity().getPersistentData().getInt("transmigration")<8){
                event.getEntity().die(tinkerdamage.tinker(event.getEntity()));
                event.getEntity().setHealth(0);
            }
            else event.getEntity().getPersistentData().putInt("transmigration",0);
        }
    }

    private void LivingDeathEvent(LivingDeathEvent event) {
        if(event.getSource().getEntity() instanceof Player player){
            if(player.hasEffect(ctiEffects.ev.get())){
                int amount=player.getPersistentData().getInt("transmigration");
                player.getPersistentData().putInt("transmigration",amount+1);
            }
        }
    }
}
