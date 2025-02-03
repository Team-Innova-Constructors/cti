package com.hoshino.cti.Modifier;

import com.c2h6s.etshtinker.init.etshtinkerToolStats;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.capability.fluid.ToolTankHelper;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class Refined extends Modifier implements VolatileDataModifierHook, ToolStatsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS,ModifierHooks.VOLATILE_DATA);
    }

    @Override
    public void addToolStats(IToolContext iToolContext, ModifierEntry modifier, ModifierStatsBuilder modifierStatsBuilder) {
        ToolStats.ARMOR.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        ToolStats.ARMOR_TOUGHNESS.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        ToolStats.ACCURACY.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        ToolStats.PROJECTILE_DAMAGE.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        ToolStats.MINING_SPEED.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        ToolStats.ATTACK_SPEED.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        ToolStats.ATTACK_DAMAGE.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        ToolStats.BLOCK_ANGLE.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        ToolStats.BLOCK_AMOUNT.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        ToolStats.DURABILITY.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        ToolStats.DRAW_SPEED.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());

        etshtinkerToolStats.PLASMARANGE.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        etshtinkerToolStats.DAMAGEMULTIPLIER.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
        etshtinkerToolStats.FLUID_EFFICIENCY.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());

        ToolTankHelper.CAPACITY_STAT.multiply(modifierStatsBuilder,1+0.3*modifier.getLevel());
    }

    @Override
    public void addVolatileData(IToolContext iToolContext, ModifierEntry modifierEntry, ModDataNBT modDataNBT) {
        modDataNBT.addSlots(SlotType.UPGRADE,modifierEntry.getLevel());
        modDataNBT.addSlots(SlotType.ABILITY,modifierEntry.getLevel());
    }
}
