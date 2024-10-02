package com.hoshino.cti;

import com.hoshino.cti.Event.LivingEvents;
import com.hoshino.cti.Modifier.capability.*;
import com.hoshino.cti.Screen.AtmosphereCondensatorScreen;
import com.hoshino.cti.Screen.AtmosphereExtractorScreen;
import com.hoshino.cti.Screen.menu.ctiMenu;
import com.hoshino.cti.client.hud.EnvironmentalHud;
import com.hoshino.cti.netwrok.ctiPacketHandler;
import com.hoshino.cti.recipe.ctiRecipes;
import com.hoshino.cti.register.*;
import com.hoshino.cti.util.BiomeUtil;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.MinecraftForge;
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

@Mod(cti.MOD_ID)

public class cti {
    /**
     * 联动模组
     * mekanism
     * etshtinker的离子炮
     */
    public static boolean Mekenabled = ModList.get().isLoaded("mekanism");
    public static boolean ETSH = ModList.get().isLoaded("etshtinker");
    public static final String MOD_ID = "cti"; //*是你的模组名，需要英文
    public cti() {
        /**
         *几个注册表都在这边，有的联动所以需要前置
         */
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::registerGuiOverlay);
        ctiItem.ITEMS.register(eventBus);
        ctiModifiers.MODIFIERS.register(eventBus);
        ctiFluid.FLUIDS.register(eventBus);
        ctiBlock.BLOCK.register(eventBus);
        ctiEffects.EFFECT.register(eventBus);
        ctiEntity.ENTITIES.register(eventBus);
        ctiBlockEntityType.BLOCK_ENTITIES.register(eventBus);
        ctiItem.ASTRAITEM.init();
        ctiItem.VEHICLES.init();
        ctiEntity.ENTITY_TYPES.init();
        MinecraftForge.EVENT_BUS.register(new LivingEvents());
        ctiPacketHandler.init();
        ctiMenu.MENU_TYPE.register(eventBus);
        ctiRecipes.register(eventBus);
        if(Mekenabled){
            ctiGas.GAS.register(eventBus);
        }
    }
    public static ResourceLocation getResource(String id) {
        return new ResourceLocation("cti", id);
    }
    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event){
        MenuScreens.register(ctiMenu.ATMOSPHERE_EXT_MENU.get(), AtmosphereExtractorScreen::new);
        MenuScreens.register(ctiMenu.ATMOSPHERE_CON_MENU.get(), AtmosphereCondensatorScreen::new);
        event.enqueueWork(ctiEntity::registerEntityRenderers);

    }
    @SubscribeEvent
    public void registerGuiOverlay(RegisterGuiOverlaysEvent event){
        if (FMLEnvironment.dist == Dist.CLIENT) {
            event.registerAboveAll("ionize", EnvironmentalHud.ENVIRONMENT_OVERLAY);
        }
    }
    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event){
        //环境抗性cap，别忘了在这注册上
        ToolCapabilityProvider.register(ElectricShieldToolCap::new);
        ToolCapabilityProvider.register(ScorchShieldToolCap::new);
        ToolCapabilityProvider.register(FreezeShieldToolCap::new);

        event.enqueueWork(BiomeUtil::init);
    }
    public static <T> TinkerDataCapability.TinkerDataKey<T> createKey(String name) {
        return TinkerDataCapability.TinkerDataKey.of(getResource(name));
    }
}
