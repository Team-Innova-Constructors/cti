package com.hoshino.cti.util;

import com.hoshino.cti.cti;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiomeUtil {
    public static final ResourceKey<Biome> IONIZED_MARE = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(cti.MOD_ID,"ionized_mare"));
    public static final ResourceKey<Biome> IONIZED_GLACIO = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(cti.MOD_ID,"ionized_glacio"));
    public static final ResourceKey<Biome> DISORDERED_ZONE = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(cti.MOD_ID,"disordered_zone"));
    public static final ResourceKey<Biome> INFERNAL = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(cti.MOD_ID,"infernal"));
    public static final ResourceKey<Biome> INFERNAL_MOLTEN = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(cti.MOD_ID,"infernal_molten_surface"));

    public static final List<ResourceKey<Biome>> PLANET_BIOMES = List.of(
            IONIZED_MARE,
            IONIZED_GLACIO,
            DISORDERED_ZONE,
            INFERNAL,
            INFERNAL_MOLTEN
    );

    public static final Map<ResourceKey<Biome>,Integer> getBiomeIonizeLevelMap(){
        Map<ResourceKey<Biome>,Integer> map =new HashMap<>();

        map.put(IONIZED_MARE,3);
        map.put(IONIZED_GLACIO,1);
        map.put(DISORDERED_ZONE,1);

        return map;
    }
    public static final Map<ResourceKey<Biome>,Integer> IONIZE_LEVEL =BiomeUtil.getBiomeIonizeLevelMap();

    public ResourceKey<Biome> getBiomeKey(Holder<Biome> holder){
        ResourceKey<Biome> key =null;
        for (ResourceKey<Biome> biomeResourceKey :PLANET_BIOMES){
            if (holder.is(biomeResourceKey)){
                key = biomeResourceKey;
                break;
            }
        }
        return key;
    }

}
