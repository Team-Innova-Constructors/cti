package com.hoshino.cti.Blocks;

import com.c2h6s.etshtinker.init.etshtinkerParticleType;
import com.hoshino.cti.register.ctiBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;


public class unipolarBudding extends AmethystBlock {
    private static final Direction[] DIRECTIONS = Direction.values();
    public unipolarBudding(Properties p_49795_) {
        super(p_49795_);
    }


    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        Direction direction = DIRECTIONS[pRandom.nextInt(DIRECTIONS.length)];
        BlockPos blockpos = pPos.relative(direction);
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (pLevel.getGameTime() % 24000 <= 12000 && pLevel.getGameTime() % 24000 >= 6000&&pLevel.getBiome(blockpos).toString().contains("ionized_mare")) {
            Block block = ctiBlock.unipolar_magnet.get();
            BlockState blockState = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, blockstate.getFluidState().getType() == Fluids.WATER);
            pLevel.setBlockAndUpdate(blockpos, blockState);
        }
    }
}
