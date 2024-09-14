package com.hoshino.cti.Screen.menu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class GeneralMachineMenu extends AbstractContainerMenu {
    public int EnergyStorage =0;
    public final List<String> Status = List.of(
            "cti.gui.cant_output",
            "cti.gui.missing_input",
            "cti.gui.normal"
    );
    public void setEnergyDis(int amount){
        this.EnergyStorage =amount;
    }
    protected GeneralMachineMenu(@Nullable MenuType<?> p_38851_, int p_38852_) {
        super(p_38851_, p_38852_);
    }
}
