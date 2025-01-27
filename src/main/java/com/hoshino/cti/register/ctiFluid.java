package com.hoshino.cti.register;

import com.hoshino.cti.cti;
import com.marth7th.solidarytinker.solidarytinker;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.mantle.registration.object.FluidObject;

import static com.hoshino.cti.cti.MOD_ID;

public class ctiFluid {

    private static FluidType.Properties hot(String name) {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000).descriptionId(cti.makeDescriptionId("fluid", name)).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_FILL_LAVA);
    }

    private static FluidType.Properties cool(String name) {
        return cool().descriptionId(cti.makeDescriptionId("fluid", name)).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_FILL_LAVA);
    }

    private static FluidType.Properties cool() {
        return FluidType.Properties.create().sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_FILL_LAVA);
    }

    private static FlowingFluidObject<ForgeFlowingFluid> register(String name, int temp) {
        return FLUIDS.register(name).type(hot(name).temperature(temp).lightLevel(12)).block(Material.LAVA, 15).bucket().flowing();
    }
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MOD_ID);
    public static final FluidObject<ForgeFlowingFluid> flux_fluid = register("flux_fluid",8192);
    public static final FluidObject<ForgeFlowingFluid> molten_uriel = register("molten_uriel",8192);
    public static final FluidObject<ForgeFlowingFluid> molten_infinity = register("molten_infinity",8192);
    public static final FluidObject<ForgeFlowingFluid> molten_stellar_manyullyn = register("molten_stellar_manyullyn",131072);
    public static final FluidObject<ForgeFlowingFluid> molten_invert_hoshino = register("molten_invert_hoshino",350507);
    public static final FluidObject<ForgeFlowingFluid> molten_roxy = register("molten_roxy",225252);
    public static final FluidObject<ForgeFlowingFluid> molten_omniscient_gold = register("molten_omniscient_gold",225252);
    public static final FluidObject<ForgeFlowingFluid> molten_violium = register("molten_violium",6000);
    public static final FluidObject<ForgeFlowingFluid> molten_aetherium = register("molten_aetherium",7000);
    public static final FluidObject<ForgeFlowingFluid> extraterrestrial_essense = register("extraterrestrial_essense",0);
    public static final FluidObject<ForgeFlowingFluid> molten_meteorium = register("molten_meteorium",8900);
}
