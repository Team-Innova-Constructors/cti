package com.hoshino.cti.Modifier;

import com.hoshino.cti.register.CtiToolStats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

public class SpaceSuitModifier extends Modifier implements ToolStatsModifierHook, InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {
        super.registerHooks(builder);
        builder.addHook(this, ModifierHooks.TOOL_STATS, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void addToolStats(IToolContext tool, ModifierEntry modifier, ModifierStatsBuilder builder) {
        CtiToolStats.SCORCH_RESISTANCE.add(builder, 0.25 * modifier.getLevel());
        CtiToolStats.FROZEN_RESISTANCE.add(builder, 0.25 * modifier.getLevel());
        CtiToolStats.ELECTRIC_RESISTANCE.add(builder, 0.1 * modifier.getLevel());
    }

    @Override
    public void onInventoryTick(IToolStackView iToolStackView, @NotNull ModifierEntry modifierEntry, Level level, LivingEntity livingEntity, int i, boolean b, boolean b1, ItemStack itemStack) {
        livingEntity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 0, false, false));
    }
}
