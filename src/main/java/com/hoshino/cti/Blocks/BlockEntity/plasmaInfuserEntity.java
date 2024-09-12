package com.hoshino.cti.Blocks.BlockEntity;

import com.hoshino.cti.register.ctiBlockEntityType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class plasmaInfuserEntity extends GeneralMachineEntity{
    public plasmaInfuserEntity( BlockPos blockPos, BlockState blockState) {
        super(ctiBlockEntityType.plasma_infuser, blockPos, blockState, 40, Component.translatable("cti.machine.plasma_infuser").withStyle(ChatFormatting.DARK_PURPLE),3,1000000000,1000000000,1000000000);
    }


    public static void tick(Level level, BlockPos blockPos, BlockState state, plasmaInfuserEntity plasmaInfuserEntity) {
    }
}
