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
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000).descriptionId(cti.makeDescriptionId("fluid", name)).sound(SoundActions.BUCKET_FILL, SoundEvents.ENDER_DRAGON_HURT).sound(SoundActions.BUCKET_EMPTY, SoundEvents.ENDER_DRAGON_DEATH);
    }

    private static FluidType.Properties cool(String name) {
        return cool().descriptionId(cti.makeDescriptionId("fluid", name)).sound(SoundActions.BUCKET_FILL, SoundEvents.ENDER_DRAGON_HURT).sound(SoundActions.BUCKET_EMPTY, SoundEvents.ENDER_DRAGON_HURT);
    }

    private static FluidType.Properties cool() {
        return FluidType.Properties.create().sound(SoundActions.BUCKET_FILL, SoundEvents.ENDER_DRAGON_HURT).sound(SoundActions.BUCKET_EMPTY, SoundEvents.ENDER_DRAGON_HURT);
    }

    private static FlowingFluidObject<ForgeFlowingFluid> register(String name, int temp) {
        return FLUIDS.register(name).type(hot(name).temperature(temp).lightLevel(12)).block(Material.LAVA, 15).bucket().flowing();
    }
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MOD_ID);
    public static final FluidObject<ForgeFlowingFluid> flux_fluid = register("flux_fluid",8192);
    public static final FluidObject<ForgeFlowingFluid> molten_uriel = register("molten_uriel",8192);
    public static final FluidObject<ForgeFlowingFluid> molten_infinity = register("molten_infinity",8192);
    public static final FluidObject<ForgeFlowingFluid> molten_stellar_manyullyn = register("molten_stellar_manyullyn",131072);
}
