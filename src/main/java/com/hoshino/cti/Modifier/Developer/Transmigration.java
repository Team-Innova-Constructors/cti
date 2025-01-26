package com.hoshino.cti.Modifier.Developer;

import com.hoshino.cti.register.ctiEffects;
import com.hoshino.cti.register.ctiModifiers;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Transmigration extends BattleModifier {
    {
        MinecraftForge.EVENT_BUS.addListener(this::EffectRemove);
    }
    private void EffectRemove(MobEffectEvent.Remove event) {
        if(event.getEntity() instanceof Player player&&player.hasEffect(ctiEffects.ev.get())){
            event.setCanceled(true);
        }
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity entity, int index, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        entity.addEffect(new MobEffectInstance(ctiEffects.ev.get(),200,0,true,true));
    }
}
