package com.hoshino.cti.Event;

import com.hoshino.cti.client.CtiKeyBinding;
import com.hoshino.cti.client.CtiParticleType;
import com.hoshino.cti.client.hud.CurseInfoHud;
import com.hoshino.cti.client.particle.ParticleType.StarFallParticleProvider;
import com.hoshino.cti.client.renderer.projectile.StarDragonAmmoRenderer;
import com.hoshino.cti.register.CtiEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientBusEvent {
    @SubscribeEvent
    public static void registerGuiOverlay(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("curse", CurseInfoHud.CurseHUD);
    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CtiEntity.star_dragon_ammo.get(), StarDragonAmmoRenderer::new);
    }
    @SubscribeEvent
    public static void onParticleProviderRegister(RegisterParticleProvidersEvent event) {
        event.register(CtiParticleType.STARFALL.get(),StarFallParticleProvider::new);
    }
    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(CtiKeyBinding.STAR_HIT);
    }
}
