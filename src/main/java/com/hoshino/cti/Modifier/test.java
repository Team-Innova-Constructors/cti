package com.hoshino.cti.Modifier;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.biome.Biome;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static com.hoshino.cti.Entity.Systems.EnvironmentSystem.getElectricResistance;
import static com.hoshino.cti.util.BiomeUtil.getBiomeIonizeLevel;


public class test extends Modifier implements GeneralInteractionModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {
        super.registerHooks(builder);
        builder.addHook(this, ModifierHooks.GENERAL_INTERACT);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand interactionHand, InteractionSource interactionSource) {
        GeneralInteractionModifierHook.startUsing(tool, modifier.getId(), player, interactionHand);
        return InteractionResult.CONSUME;
    }


    @Override
    public void onFinishUsing(IToolStackView tool, ModifierEntry modifier, LivingEntity entity) {
        Holder<Biome> biome = entity.level.getBiome(entity.blockPosition());
        float lvl_ionize = getBiomeIonizeLevel(biome);
        float iondef = getElectricResistance(entity);
        entity.sendSystemMessage(Component.literal(String.valueOf(lvl_ionize)));
        entity.sendSystemMessage(Component.literal(String.valueOf(iondef)));
    }

    @Override
    public int getUseDuration(IToolStackView tool, ModifierEntry modifier) {
        return 1;
    }

    @Override
    public UseAnim getUseAction(IToolStackView tool, ModifierEntry modifier) {
        return UseAnim.BLOCK;
    }
}
