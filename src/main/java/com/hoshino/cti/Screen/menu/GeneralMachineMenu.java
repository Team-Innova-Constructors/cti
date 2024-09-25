package com.hoshino.cti.Screen.menu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class GeneralMachineMenu extends AbstractContainerMenu {
    public int EnergyStorage =0;
    public FluidStack Fluidstack =FluidStack.EMPTY;
    public int FluidStorage=0;
    public final List<String> Status = List.of(
            "cti.gui.cant_output",
            "cti.gui.missing_input",
            "cti.gui.normal"
    );
    public void setEnergyDis(int amount){
        this.EnergyStorage =amount;
    }
    public void setFluidDis(FluidStack stack1){
        this.Fluidstack =stack1;
    }
    protected GeneralMachineMenu(@Nullable MenuType<?> p_38851_, int p_38852_) {
        super(p_38851_, p_38852_);
    }
}
