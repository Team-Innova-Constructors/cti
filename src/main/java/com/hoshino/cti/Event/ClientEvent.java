package com.hoshino.cti.Event;

import com.hoshino.cti.client.hud.EnvironmentalHud;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEvent {
    public static class ClientEventBus {
        @SubscribeEvent
        public static void registerGuiOverlay(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("ionize", EnvironmentalHud.ENVIRONMENT_OVERLAY);
        }
    }
}
