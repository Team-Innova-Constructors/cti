package com.hoshino.cti.util.Recipe;

import earth.terrarium.ad_astra.common.registry.ModFluids;
import earth.terrarium.ad_astra.common.registry.ModItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashMap;
import java.util.Map;

import static com.hoshino.cti.util.BiomeUtil.GLACIO_BARRENS;
import static com.hoshino.cti.util.BiomeUtil.GLACIO_ICE;

public class AtmosphereCondensator {
    public static class BiomeToFluid {
        public static final Map<ResourceKey<Biome>, FluidStack> MAP = new HashMap<>();
        public static void extendMap(){
            MAP.put(GLACIO_ICE,new FluidStack(ModFluids.CRYO_FUEL.get(),100));
            MAP.put(GLACIO_BARRENS,new FluidStack(ModFluids.CRYO_FUEL.get(),100));
        }
        public static FluidStack getOutput(ResourceKey<Biome> biome){
            return MAP.getOrDefault(biome,FluidStack.EMPTY).copy();
        }
    }
}
