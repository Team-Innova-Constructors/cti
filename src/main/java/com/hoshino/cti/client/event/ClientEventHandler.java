package com.hoshino.cti.client.event;

import com.hoshino.cti.Screen.AtmosphereCondensatorScreen;
import com.hoshino.cti.Screen.AtmosphereExtractorScreen;
import com.hoshino.cti.Screen.ReactorNeutronCollectorScreen;
import com.hoshino.cti.Screen.menu.ctiMenu;
import com.hoshino.cti.client.CtiParticleType;
import com.hoshino.cti.client.InitPartModel;
import com.hoshino.cti.Cti;
import com.hoshino.cti.client.hud.EnvironmentalHud;
import com.hoshino.cti.client.particle.*;
import com.hoshino.cti.register.CtiEntity;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;

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
        event.register(CtiParticleType.FIERY_LINE.get(), FieryJavelinLineParticle::provider);
        event.register(CtiParticleType.ION.get(), IonParticle::provider);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ctiMenu.ATMOSPHERE_EXT_MENU.get(), AtmosphereExtractorScreen::new);
        MenuScreens.register(ctiMenu.ATMOSPHERE_CON_MENU.get(), AtmosphereCondensatorScreen::new);
        MenuScreens.register(ctiMenu.NEUT_COL_MENU.get(), ReactorNeutronCollectorScreen::new);
        event.enqueueWork(CtiEntity::registerEntityRenderers);
    }

    @SubscribeEvent
    public static void registerGuiOverlay(RegisterGuiOverlaysEvent event) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            event.registerAboveAll("ionize", EnvironmentalHud.ENVIRONMENT_OVERLAY);
        }
    }

}
