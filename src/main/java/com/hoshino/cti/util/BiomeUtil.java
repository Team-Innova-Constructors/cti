package com.hoshino.cti.util;

import com.hoshino.cti.cti;
import earth.terrarium.ad_astra.AdAstra;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;

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


    public static void init(){

        IONIZE_LEVEL.put(IONIZED_MARE,2.1f);
        IONIZE_LEVEL.put(IONIZED_GLACIO,0.8f);
        IONIZE_LEVEL.put(DISORDERED_ZONE,3.5f);

        SCORCH_LEVEL.put(INFERNAL_MOLTEN,2.9f);
        SCORCH_LEVEL.put(INFERNAL,2.1f);

        FREEZE_LEVEL.put(IONIZED_MARE,1.5f);
        FREEZE_LEVEL.put(IONIZED_GLACIO,3.3f);
        FREEZE_LEVEL.put(DISORDERED_ZONE,1.1f);

    }

    public static String BiomekeyToString(ResourceKey<Biome> key){
        return key!=null?"biome."+key.location().toLanguageKey():"cti.gui.biome.null";
    }

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
    public static final Map<ResourceKey<Biome>,Float> IONIZE_LEVEL =new HashMap<>();
    public static final Map<ResourceKey<Biome>,Float> SCORCH_LEVEL =new HashMap<>();
    public static final Map<ResourceKey<Biome>,Float> FREEZE_LEVEL =new HashMap<>();



    public static float getBiomeIonizeLevel(Holder<Biome> holder){
        ResourceKey<Biome> key =getBiomeKey(holder);
        return key!=null? IONIZE_LEVEL.getOrDefault(key, 0f):0f;
    }
    public static float getBiomeScorchLevel(Holder<Biome> holder){
        ResourceKey<Biome> key =getBiomeKey(holder);
        return key!=null? SCORCH_LEVEL.getOrDefault(key, 0f):0f;
    }
    public static float getBiomeFreezeLevel(Holder<Biome> holder){
        ResourceKey<Biome> key =getBiomeKey(holder);
        return key!=null? FREEZE_LEVEL.getOrDefault(key, 0f):0f;
    }

    @Nullable
    public static ResourceKey<Biome> getBiomeKey(Holder<Biome> holder){
        ResourceKey<Biome> key =null;
        if (holder.unwrapKey().isPresent()){
            key=holder.unwrapKey().get();
        }
        return key;
    }

}
