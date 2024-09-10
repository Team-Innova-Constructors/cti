package com.hoshino.cti.Blocks.BlockEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class plasmaInfuserEntity extends GeneralMachineEntity{
    public plasmaInfuserEntity(BlockEntityType<?> entityType, BlockPos blockPos, BlockState blockState, int maxProgress, Component displayName) {
        super(entityType, blockPos, blockState, maxProgress, Component.translatable("cti.machine.plasma_infuser").withStyle(ChatFormatting.DARK_PURPLE),1000000000,1000000000,1000000000);
    }

}
