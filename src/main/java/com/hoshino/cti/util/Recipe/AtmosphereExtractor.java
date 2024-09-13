package com.hoshino.cti.util.Recipe;

import earth.terrarium.ad_astra.common.registry.ModItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;

import java.util.HashMap;
import java.util.Map;

import static com.hoshino.cti.util.BiomeUtil.*;

public class AtmosphereExtractor {
    public static class BiomeToItem {
        public static final Map<ResourceKey<Biome>, ItemStack> MAP = new HashMap<>();
        public static void extendMap(){
            MAP.put(GLACIO_ICE,new ItemStack(ModItems.ICE_SHARD.get(),4));
            MAP.put(GLACIO_BARRENS,new ItemStack(ModItems.ICE_SHARD.get(),4));
        }
        public static ItemStack getOutput(ResourceKey<Biome> biome){
            return MAP.getOrDefault(biome,ItemStack.EMPTY);
        }
    }
}
