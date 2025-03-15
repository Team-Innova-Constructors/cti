package com.hoshino.cti.Blocks.Machine;

import com.hoshino.cti.Blocks.BlockEntity.QuantumMinerEntity;
import com.hoshino.cti.register.ctiBlock;
import com.hoshino.cti.register.ctiBlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class QuantumMinerBlock extends BaseEntityBlock {
    public QuantumMinerBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new QuantumMinerEntity(blockPos, blockState);
    }

    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof QuantumMinerEntity entity && entity.getBlockState().is(ctiBlock.quantum_miner.get())) {
                entity.dropItem();
            }
            blockEntity.setRemoved();
        }
        super.onRemove(state, level, blockPos, newState, isMoving);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ctiBlockEntityType.QUANTUM_MINER_ENTITY.get(), QuantumMinerEntity::tick);
    }

}
