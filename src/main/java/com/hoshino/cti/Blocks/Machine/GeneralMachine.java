package com.hoshino.cti.Blocks.Machine;

import com.hoshino.cti.Blocks.BlockEntity.GeneralMachineEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GeneralMachine<T extends BlockEntity> extends BaseEntityBlock {
    protected GeneralMachine(Properties p_49224_,BlockEntity entity,BlockEntityType<T> type) {
        super(p_49224_);
        this.BLOCK_ENTITY =entity;
        this.BLOCK_ENTITY_TYPE =type;
    }
    private final BlockEntity BLOCK_ENTITY;
    private final BlockEntityType<T> BLOCK_ENTITY_TYPE;

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return this.BLOCK_ENTITY;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (state.getBlock()!=newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof GeneralMachineEntity entity&&entity.getBlockState().is(this.BLOCK_ENTITY.getBlockState().getBlock())){
                entity.dropItem();
            }
        }
        super.onRemove(state, level, blockPos, newState, isMoving);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return super.getTicker(level, blockState, (BlockEntityType<T>) this.BLOCK_ENTITY_TYPE);
    }
}
