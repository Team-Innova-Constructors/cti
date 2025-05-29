package com.hoshino.cti.Event;

import com.hoshino.cti.cti;
import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = cti.MOD_ID, bus = Bus.FORGE)
public class ExperienceBugFix {
    @SubscribeEvent
    public static void onChange(PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {

            player.connection.send(new ClientboundSetExperiencePacket(
                    player.experienceProgress,
                    player.totalExperience,
                    player.experienceLevel
            ));
        }
    }
}