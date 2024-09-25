package com.hoshino.cti.Blocks.Machine;

import com.hoshino.cti.Blocks.BlockEntity.AtmosphereCondensatorEntity;
import com.hoshino.cti.netwrok.ctiPacketHandler;
import com.hoshino.cti.netwrok.packet.PMachineEnergySync;
import com.hoshino.cti.netwrok.packet.PMachineFluidSync;
import com.hoshino.cti.register.ctiBlock;
import com.hoshino.cti.register.ctiBlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class AtmosphereCondensatorBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public AtmosphereCondensatorBlock(Properties p_49224_) {
        super(p_49224_);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new AtmosphereCondensatorEntity(blockPos,blockState);
    }
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING,context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.rotate(level,pos,direction);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (state.getBlock()!=newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof AtmosphereCondensatorEntity entity&&entity.getBlockState().is(ctiBlock.atmosphere_condensator.get())){
                entity.dropItem();
            }
            blockEntity.setRemoved();
        }
        super.onRemove(state, level, blockPos, newState, isMoving);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ctiBlockEntityType.Atmosphere_condensator.get(), AtmosphereCondensatorEntity::tick);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult result) {
        if (!level.isClientSide){
            BlockEntity entity =level.getBlockEntity(blockPos);
            if (entity instanceof AtmosphereCondensatorEntity condensator){
                if (player instanceof ServerPlayer) {
                    NetworkHooks.openScreen((ServerPlayer) player, (MenuProvider) entity, blockPos);
                    return InteractionResult.CONSUME;
                }
            }else {
                throw new IllegalStateException("Container provider MISSING");
            }
        }
        return InteractionResult.SUCCESS;
    }
}
