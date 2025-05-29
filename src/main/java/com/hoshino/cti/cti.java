package com.hoshino.cti;

import com.hoshino.cti.Event.LivingEvents;
import com.hoshino.cti.Event.MobEffect;
import com.hoshino.cti.Event.ServerEvent;
import com.hoshino.cti.Event.sleep;
import com.hoshino.cti.Modifier.capability.*;
import com.hoshino.cti.Screen.AtmosphereCondensatorScreen;
import com.hoshino.cti.Screen.AtmosphereExtractorScreen;
import com.hoshino.cti.Screen.ReactorNeutronCollectorScreen;
import com.hoshino.cti.Screen.menu.ctiMenu;
import com.hoshino.cti.client.hud.EnvironmentalHud;
import com.hoshino.cti.netwrok.ctiPacketHandler;
import com.hoshino.cti.register.*;
import com.hoshino.cti.util.BiomeUtil;
import com.hoshino.cti.util.tier.Roxy;
import com.hoshino.cti.world.feature.ctiConfiguredFeature;
import com.hoshino.cti.world.feature.ctiPlacedFeature;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.capability.ToolCapabilityProvider;

import java.util.List;

@Mod(cti.MOD_ID)

public class cti {
    public static boolean Mekenabled = ModList.get().isLoaded("mekanism");
    public static final String MOD_ID = "cti";

    public cti() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::registerGuiOverlay);
        CtiItem.ITEMS.register(eventBus);
        CtiModifiers.MODIFIERS.register(eventBus);
        ctiFluid.FLUIDS.register(eventBus);
        ctiBlock.BLOCK.register(eventBus);
        ctiEffects.EFFECT.register(eventBus);
        ctiEntity.ENTITIES.register(eventBus);
        ctiBlockEntityType.BLOCK_ENTITIES.register(eventBus);
        ctiInfusetype.INFUSE.register(eventBus);
        CtiItem.ASTRAITEM.init();
        CtiItem.VEHICLES.init();
        ctiHostilityTrait.register();
        ctiEntity.ENTITY_TYPES.init();
        MinecraftForge.EVENT_BUS.register(new LivingEvents());
        MinecraftForge.EVENT_BUS.register(new MobEffect());
        MinecraftForge.EVENT_BUS.register(new ServerEvent());
        ctiPacketHandler.init();
        ctiMenu.MENU_TYPE.register(eventBus);
        ctiPotions.POTIONS.register(eventBus);
        CtiItem.registerPartModels();
        //ctiRecipes.register(eventBus);
        ctiConfiguredFeature.CONFIGURED_FEATURES.register(eventBus);
        ctiPlacedFeature.PLACED_FEATURES.register(eventBus);
        if (Mekenabled) {
            ctiChemical.GAS.register(eventBus);
        }

    }

    public static sleep sleep = new sleep();

    public static ResourceLocation getResource(String id) {
        return new ResourceLocation("cti", id);
    }

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ctiMenu.ATMOSPHERE_EXT_MENU.get(), AtmosphereExtractorScreen::new);
        MenuScreens.register(ctiMenu.ATMOSPHERE_CON_MENU.get(), AtmosphereCondensatorScreen::new);
        MenuScreens.register(ctiMenu.NEUT_COL_MENU.get(), ReactorNeutronCollectorScreen::new);
        event.enqueueWork(ctiEntity::registerEntityRenderers);
    }

    @SubscribeEvent
    public void registerGuiOverlay(RegisterGuiOverlaysEvent event) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            event.registerAboveAll("ionize", EnvironmentalHud.ENVIRONMENT_OVERLAY);
        }
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        //环境抗性cap，别忘了在这注册上
        ToolCapabilityProvider.register(ElectricShieldToolCap::new);
        ToolCapabilityProvider.register(ScorchShieldToolCap::new);
        ToolCapabilityProvider.register(FreezeShieldToolCap::new);
        ToolCapabilityProvider.register(PressureShieldToolCap::new);
        ToolCapabilityProvider.register(PressurizableToolCap::new);
        event.enqueueWork(BiomeUtil::init);
        event.enqueueWork(ctiRailgunProjectile::register);
        event.enqueueWork(ctiRitual::init);
        //机械动力土豆加农炮
        CtiPotatocannon.register();
        //powah反应堆冷却剂
        PowahSolid.init();
        ctiSlots.init();
        //药水配方
        ctiBrewing.init();
        TierSortingRegistry.registerTier(Roxy.instance, new ResourceLocation("cti:roxy"), List.of(Tiers.NETHERITE), List.of());
    }

    public static <T> TinkerDataCapability.TinkerDataKey<T> createKey(String name) {
        return TinkerDataCapability.TinkerDataKey.of(getResource(name));
    }

    public static String makeDescriptionId(String type, String name) {
        return type + ".cti." + name;
    }
}
