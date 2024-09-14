package com.hoshino.cti.Blocks.BlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class GeneralMachineEntity extends BlockEntity {

    public GeneralMachineEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }
    public void setEnergy(int Amount){
        this.CurrentEnergy = Amount;
    }
    public int CurrentEnergy =0;
}
