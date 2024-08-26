package com.hoshino.cti;

import com.hoshino.cti.register.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

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
        ctiItem.ITEMS.register(eventBus);
        ctiModifiers.MODIFIERS.register(eventBus);
        ctiFluid.FLUIDS.register(eventBus);
        ctiBlock.BLOCK.register(eventBus);
        ctiEffects.EFFECT.register(eventBus);
        if(Mekenabled){
            ctiGas.GAS.register(eventBus);
        }
    }
    public static ResourceLocation getResource(String id) {
        return new ResourceLocation("cti", id);
    }
    public static <T> TinkerDataCapability.TinkerDataKey<T> createKey(String name) {
        return TinkerDataCapability.TinkerDataKey.of(getResource(name));
    }
}
