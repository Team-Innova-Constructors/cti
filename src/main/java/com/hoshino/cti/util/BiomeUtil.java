package com.hoshino.cti.util;

import com.hoshino.cti.cti;
import earth.terrarium.ad_astra.AdAstra;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiomeUtil {
    public static final ResourceKey<Biome> VENUS_BARRENS = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(AdAstra.MOD_ID,"infernal_venus_barrens"));
    public static final ResourceKey<Biome> VENUS_WASTELAND = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(AdAstra.MOD_ID,"venus_wastelands"));
    public static final ResourceKey<Biome> GLACIO_BARRENS = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(AdAstra.MOD_ID,"glacio_snowy_barrens"));
    public static final ResourceKey<Biome> GLACIO_ICE = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(AdAstra.MOD_ID,"glacio_ice_peaks"));

    public static final ResourceKey<Biome> IONIZED_MARE = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(cti.MOD_ID,"ionized_mare"));
    public static final ResourceKey<Biome> IONIZED_GLACIO = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(cti.MOD_ID,"ionized_glacio"));
    public static final ResourceKey<Biome> DISORDERED_ZONE = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(cti.MOD_ID,"disordered_zone"));
    public static final ResourceKey<Biome> INFERNAL = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(cti.MOD_ID,"infernal"));
    public static final ResourceKey<Biome> INFERNAL_MOLTEN = ResourceKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(cti.MOD_ID,"infernal_molten_surface"));

    //外星群系列表，注册完记得加上
    public static final List<ResourceKey<Biome>> PLANET_BIOMES = List.of(
            GLACIO_ICE,
            GLACIO_BARRENS,
            VENUS_BARRENS,
            VENUS_WASTELAND,
            IONIZED_MARE,
            IONIZED_GLACIO,
            DISORDERED_ZONE,
            INFERNAL,
            INFERNAL_MOLTEN
    );
    public static final Map<ResourceKey<Biome>,Integer> IONIZE_LEVEL =getBiomeIonizeLevelMap();
    public static final Map<ResourceKey<Biome>,Integer> SCORCH_LEVEL =getBiomeScorchLevelMap();
    public static final Map<ResourceKey<Biome>,Integer> FREEZE_LEVEL =getBiomeFreezeLevelMap();

    //群系电离等级字典，注册完记得加上
    public static final Map<ResourceKey<Biome>,Integer> getBiomeIonizeLevelMap(){
        Map<ResourceKey<Biome>,Integer> map =new HashMap<>();

        map.put(IONIZED_MARE,2);
        map.put(IONIZED_GLACIO,1);
        map.put(DISORDERED_ZONE,3);

        return map;
    }
    //群系灼热等级字典，注册完记得加上
    public static final Map<ResourceKey<Biome>,Integer> getBiomeScorchLevelMap(){
        Map<ResourceKey<Biome>,Integer> map =new HashMap<>();

        map.put(INFERNAL_MOLTEN,3);
        map.put(INFERNAL,2);

        return map;
    }
    //群系极寒等级字典，注册完记得加上
    public static final Map<ResourceKey<Biome>,Integer> getBiomeFreezeLevelMap(){
        Map<ResourceKey<Biome>,Integer> map =new HashMap<>();

        map.put(IONIZED_MARE,2);
        map.put(IONIZED_GLACIO,3);
        map.put(DISORDERED_ZONE,1);

        return map;
    }


    public static int getBiomeIonizeLevel(Holder<Biome> holder){
        ResourceKey<Biome> key =getBiomeKey(holder);
        return key!=null? IONIZE_LEVEL.getOrDefault(key, 0):0;
    }
    public static int getBiomeScorchLevel(Holder<Biome> holder){
        ResourceKey<Biome> key =getBiomeKey(holder);
        return key!=null? SCORCH_LEVEL.getOrDefault(key, 0):0;
    }
    public static int getBiomeFreezeLevel(Holder<Biome> holder){
        ResourceKey<Biome> key =getBiomeKey(holder);
        return key!=null? FREEZE_LEVEL.getOrDefault(key, 0):0;
    }


    public static ResourceKey<Biome> getBiomeKey(Holder<Biome> holder){
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
