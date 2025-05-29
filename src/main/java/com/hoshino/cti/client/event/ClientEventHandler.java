package com.hoshino.cti.client.event;

import com.hoshino.cti.client.InitPartModel;
import com.hoshino.cti.cti;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = cti.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {
    @SubscribeEvent
    public static void registerLoader(ModelEvent.RegisterGeometryLoaders event){
        InitPartModel.init(event::register);
    }
}
