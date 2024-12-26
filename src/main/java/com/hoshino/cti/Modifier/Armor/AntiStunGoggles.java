package com.hoshino.cti.Modifier.Armor;

import com.hoshino.cti.util.EntityUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class AntiStunGoggles extends Modifier implements InventoryTickModifierHook {
    public AntiStunGoggles(){
        MinecraftForge.EVENT_BUS.addListener(this::OnEffectApply);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }

    private void OnEffectApply(MobEffectEvent.Applicable event) {
        if (event.getEntity()!=null&& EntityUtil.isAntiStun(event.getEntity())&&event.getEffectInstance().getEffect()== MobEffects.CONFUSION){
            event.setResult(Event.Result.DENY);
        }
    }


    @Override
    public void onInventoryTick(IToolStackView iToolStackView, ModifierEntry modifierEntry, Level level, LivingEntity livingEntity, int i, boolean b, boolean b1, ItemStack itemStack) {
        MobEffectInstance instance = livingEntity.getEffect(MobEffects.CONFUSION);
        if (instance!=null){
            livingEntity.removeEffect(MobEffects.CONFUSION);
        }
    }
}
