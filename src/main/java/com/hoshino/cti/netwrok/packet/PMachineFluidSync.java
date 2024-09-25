package com.hoshino.cti.netwrok.packet;

import com.hoshino.cti.Screen.menu.GeneralMachineMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PMachineFluidSync {
    private final FluidStack stack;
    private final BlockPos blockPos;

    public PMachineFluidSync(FluidStack stack,BlockPos blockPos){
        this.stack =stack;
        this.blockPos =blockPos;
    }

    public PMachineFluidSync(FriendlyByteBuf buf){
        this.stack =buf.readFluidStack();
        this.blockPos =buf.readBlockPos();
    }

    public void toByte(FriendlyByteBuf buf){
        buf.writeFluidStack(this.stack);
        buf.writeBlockPos(this.blockPos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context =supplier.get();
        context.enqueueWork(()->{
            if (Minecraft.getInstance().player != null) {
                if (Minecraft.getInstance().player.containerMenu instanceof GeneralMachineMenu menu){
                    menu.setFluidDis(stack);
                }
            }
        });
        return true;
    }
}
