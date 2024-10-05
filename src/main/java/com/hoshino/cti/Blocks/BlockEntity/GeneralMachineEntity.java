package com.hoshino.cti.Blocks.BlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

public class GeneralMachineEntity extends BlockEntity {

    public GeneralMachineEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }

    private int CurrentEnergy =0;
    private FluidStack Fluidstack =FluidStack.EMPTY;


    public void setEnergy(int Amount){
        this.CurrentEnergy = Amount;
    }
    public int getCurrentEnergy(){
        return this.CurrentEnergy;
    }

    public void setFluidDis(FluidStack stack1){
        this.Fluidstack =stack1;
    }
    public FluidStack getFluidDis(){
        return this.Fluidstack;
    }
}
