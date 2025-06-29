package com.hoshino.cti.Blocks.BlockEntity.Ports;

import com.hoshino.cti.util.ctiEnergyStore;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

public class EnergyOutputPortBE extends BasicPort{
    public final ctiEnergyStore energyStorage;

    public EnergyOutputPortBE(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int energyStorage) {
        super(pType, pPos, pBlockState);
        this.energyStorage = new ctiEnergyStore(energyStorage,energyStorage) {
            @Override
            public void onEnergyChange() {
                setChanged();
            }

            @Override
            public boolean canReceive() {
                return false;
            }
        };
    }
    private LazyOptional<IEnergyStorage> energyOptional = LazyOptional.empty();
    @Override
    public void onLoad() {
        super.onLoad();
        this.energyOptional = LazyOptional.of(()->energyStorage);
    }
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.energyOptional.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        this.energyStorage.writeToNbt(pTag);
    }
    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.energyStorage.readFromNbt(pTag);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap==ForgeCapabilities.ENERGY) return this.energyOptional.cast();
        return super.getCapability(cap);
    }


}
