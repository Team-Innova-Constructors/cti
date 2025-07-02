package com.hoshino.cti.client.event;

import com.hoshino.cti.client.CtiParticleType;
import com.hoshino.cti.client.InitPartModel;
import com.hoshino.cti.Cti;
import com.hoshino.cti.client.particle.FieryExplodeParticle;
import com.hoshino.cti.client.particle.RedSparkParticle;
import com.hoshino.cti.client.particle.StarLineParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cti.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {
    @SubscribeEvent
    public static void registerLoader(ModelEvent.RegisterGeometryLoaders event){
        InitPartModel.init(event::register);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.register(CtiParticleType.STAR_LINE.get(), StarLineParticle::provider);
        event.register(CtiParticleType.RED_SPARK.get(), RedSparkParticle::provider);
        event.register(CtiParticleType.FIERY_EXPLODE.get(), FieryExplodeParticle::provider);
    }

}
