package com.hoshino.cti.register;

import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;

import static com.hoshino.cti.cti.MOD_ID;

public class ctiFluid {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MOD_ID);
    private static FluidObject<ForgeFlowingFluid> register(String name, int temp) {
        return FLUIDS.register(name).type(FluidType.Properties.create().density(2000).viscosity(10000).temperature(temp).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)).bucket().flowing();
    }
    public static final FluidObject<ForgeFlowingFluid> flux_fluid = register("flux_fluid",8192);
    public static final FluidObject<ForgeFlowingFluid> molten_uriel = register("molten_uriel",8192);
    public static final FluidObject<ForgeFlowingFluid> molten_stellar_manyullyn = register("molten_stellar_manyullyn",131072);
}
