package com.hoshino.cti.netwrok.packet;

import com.hoshino.cti.Modifier.PlasmaWaveSlashPlus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.function.Supplier;

public class PPlasmaWaveSlashC2S {
    public PPlasmaWaveSlashC2S(){
    }

    public PPlasmaWaveSlashC2S(FriendlyByteBuf buf){
    }

    public void toByte(FriendlyByteBuf buf){
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context =supplier.get();
        context.enqueueWork(()->{
            ServerPlayer player = context.getSender();
            if (player!=null){
                PlasmaWaveSlashPlus.createSlash(context.getSender(), ToolStack.from( context.getSender().getMainHandItem()));
            }
        });
        return true;
    }
}
