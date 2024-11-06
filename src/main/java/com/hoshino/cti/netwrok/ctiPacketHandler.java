package com.hoshino.cti.netwrok;

import com.hoshino.cti.cti;
import com.hoshino.cti.netwrok.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;


public class ctiPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel INSTANCE ;
    static int id = 0;

    public static void init() {
        INSTANCE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(cti.MOD_ID,"cti_message")).networkProtocolVersion(()->"1").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true).simpleChannel();
        INSTANCE.messageBuilder(PIonizeValueSync.class,id++, NetworkDirection.PLAY_TO_CLIENT).decoder(PIonizeValueSync::new).encoder(PIonizeValueSync::toByte).consumerMainThread(PIonizeValueSync::handle).add();
        INSTANCE.messageBuilder(PScorchValueSync.class,id++, NetworkDirection.PLAY_TO_CLIENT).decoder(PScorchValueSync::new).encoder(PScorchValueSync::toByte).consumerMainThread(PScorchValueSync::handle).add();
        INSTANCE.messageBuilder(PFrozenValueSync.class,id++, NetworkDirection.PLAY_TO_CLIENT).decoder(PFrozenValueSync::new).encoder(PFrozenValueSync::toByte).consumerMainThread(PFrozenValueSync::handle).add();
        INSTANCE.messageBuilder(PMachineEnergySync.class,id++, NetworkDirection.PLAY_TO_CLIENT).decoder(PMachineEnergySync::new).encoder(PMachineEnergySync::toByte).consumerMainThread(PMachineEnergySync::handle).add();
        INSTANCE.messageBuilder(PMachineFluidSync.class,id++, NetworkDirection.PLAY_TO_CLIENT).decoder(PMachineFluidSync::new).encoder(PMachineFluidSync::toByte).consumerMainThread(PMachineFluidSync::handle).add();
        INSTANCE.messageBuilder(PPressureValueSync.class,id++, NetworkDirection.PLAY_TO_CLIENT).decoder(PPressureValueSync::new).encoder(PPressureValueSync::toByte).consumerMainThread(PPressureValueSync::handle).add();
        INSTANCE.messageBuilder(PStellarBlade.class,id++, NetworkDirection.PLAY_TO_SERVER).decoder(PStellarBlade::new).encoder(PStellarBlade::toByte).consumerMainThread(PStellarBlade::handle).add();
        INSTANCE.messageBuilder(PRailgunItemS2C.class,id++, NetworkDirection.PLAY_TO_CLIENT).decoder(PRailgunItemS2C::new).encoder(PRailgunItemS2C::toByte).consumerMainThread(PRailgunItemS2C::handle).add();
        INSTANCE.messageBuilder(PRailgunC2S.class,id++, NetworkDirection.PLAY_TO_SERVER).decoder(PRailgunC2S::new).encoder(PRailgunC2S::toByte).consumerMainThread(PRailgunC2S::handle).add();
    }

    public static <MSG> void sendToServer(MSG msg){
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(MSG msg, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()->player),msg);
    }

    public static <MSG> void sendToClient(MSG msg){
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }


}
