package com.hoshino.cti.Blocks.BlockEntity.Ports;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BasicPort extends BlockEntity {
    public BasicPort(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }
    public BlockPos controllerPos;


    public static BlockEntityTicker<BasicPort> getTicker(){
        return (pLevel, pPos, pState, pBlockEntity) -> tick();
    }

    public static void tick(){

    }
}
