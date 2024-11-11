package com.hoshino.cti.Modifier.Contributors;

import com.hoshino.cti.register.ctiEffects;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import com.marth7th.solidarytinker.shelf.damagesource.tinkerdamage;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;

public class Transmigration extends BattleModifier {
    {
        MinecraftForge.EVENT_BUS.addListener(this::EffectClear);
    }

    private void EffectClear(MobEffectEvent.Remove event) {
        if(event.getEffect()==ctiEffects.ev.get()){
            int EVlevel=event.getEffectInstance().getAmplifier()+1;
            if(event.getEntity().getPersistentData().getInt("transmigration1" ) < 128 / (2 * EVlevel)){
                event.getEntity().die(tinkerdamage.tinker(event.getEntity()));
                event.getEntity().setHealth(0);
            }
        }
    }
}
