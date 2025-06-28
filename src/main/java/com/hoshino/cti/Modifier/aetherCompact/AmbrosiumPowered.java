package com.hoshino.cti.Modifier.aetherCompact;

import com.aetherteam.aether.item.AetherItems;
import com.hoshino.cti.Cti;
import com.hoshino.cti.library.modifier.CtiModifierHook;
import com.hoshino.cti.library.modifier.hooks.SlotStackModifierHook;
import com.hoshino.cti.netwrok.CtiPacketHandler;
import com.hoshino.cti.netwrok.packet.PAmbrosiumChargeC2S;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.ItemStackedOnOtherEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.*;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

public class AmbrosiumPowered extends Modifier implements SlotStackModifierHook, ToolStatsModifierHook, TooltipModifierHook, ToolDamageModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS, CtiModifierHook.SLOT_STACK,ModifierHooks.TOOLTIP,ModifierHooks.TOOL_DAMAGE);
    }


    public static final ResourceLocation KEY_AMBROSIUM_POWER = Cti.getResource("ambrosium_power");

    @Override
    public boolean overrideOtherStackedOnMe(IToolStackView slotTool, ModifierEntry modifier, ItemStack held, Slot slot, Player player, SlotAccess access) {
        if (held.is(AetherItems.AMBROSIUM_SHARD.get())&&slot.allowModification(player)){
            if (!player.level.isClientSide&&slotTool.getPersistentData().getInt(KEY_AMBROSIUM_POWER)<64*modifier.getLevel()){
                held.shrink(1);
                chargeTool(slotTool);
            }
            return true;
        }
        return false;
    }

    public static void chargeTool(IToolStackView toolStackView){
        toolStackView.getPersistentData().putInt(KEY_AMBROSIUM_POWER,toolStackView.getPersistentData().getInt(KEY_AMBROSIUM_POWER)+4);
        ((ToolStack)toolStackView).rebuildStats();
    }

    @Override
    public void addToolStats(IToolContext iToolContext, ModifierEntry modifierEntry, ModifierStatsBuilder modifierStatsBuilder) {
        IModDataView nbt = iToolContext.getPersistentData();
        if (nbt.getInt(KEY_AMBROSIUM_POWER)>0){
            List.of(ToolStats.ATTACK_DAMAGE,ToolStats.PROJECTILE_DAMAGE,ToolStats.ATTACK_SPEED,ToolStats.DRAW_SPEED).forEach(stat->stat.percent(modifierStatsBuilder,0.25*modifierEntry.getLevel()));
        }
    }

    @Override
    public void addTooltip(IToolStackView iToolStackView, ModifierEntry modifierEntry, @Nullable Player player, List<Component> list, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        list.add(Component.literal("神能晶充能：").append(String.valueOf(iToolStackView.getPersistentData().getInt(KEY_AMBROSIUM_POWER))).withStyle(this.getDisplayName().getStyle()));
    }


    @Override
    public int onDamageTool(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i, @Nullable LivingEntity livingEntity) {
        if (iToolStackView.getPersistentData().getInt(KEY_AMBROSIUM_POWER)>0){
            iToolStackView.getPersistentData().putInt(KEY_AMBROSIUM_POWER,iToolStackView.getPersistentData().getInt(KEY_AMBROSIUM_POWER)-1);
            return 0;
        }
        return i;
    }
}
