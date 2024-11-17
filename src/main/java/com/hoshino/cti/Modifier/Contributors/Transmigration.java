package com.hoshino.cti.Modifier.Contributors;

import com.hoshino.cti.register.ctiEffects;
import com.hoshino.cti.register.ctiModifiers;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Transmigration extends BattleModifier {
    {
        MinecraftForge.EVENT_BUS.addListener(this::EffectClear);
        MinecraftForge.EVENT_BUS.addListener(this::EffectRemove);
    }
    private void EffectRemove(MobEffectEvent.Remove event) {
        if(event.getEntity() instanceof Player player&&player.hasEffect(ctiEffects.ev.get())){
            event.setCanceled(true);
        }
    }
    private void EffectClear(MobEffectEvent.Expired event) {
        if(event.getEffectInstance().getEffect()==ctiEffects.ev.get()){
            LivingEntity entity=event.getEntity();
            if(entity instanceof Player player){
                List<ItemStack>itemStacks=player.getInventory().armor;
                for(ItemStack itemStack:itemStacks){
                    if(player.getTags().contains("transmigration")|| ModifierUtil.getModifierLevel(itemStack, ctiModifiers.trauma.getId())>0){
                        player.heal(10);
                        TimerTask t = new TimerTask() {
                            @Override
                            public void run() {
                                player.getTags().remove("transmigration");
                            }
                        };
                        Timer timer = new Timer();
                        timer.schedule(t, 300);
                    }
                    else{
                        return;
                    }
                }
            }
        }
    }
}
