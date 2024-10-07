package com.hoshino.cti.recipe;

import com.hoshino.cti.cti;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeMap {
    //这个类用于真正添加配方
    //为了方便添加，物品和流体使用的是字符串

    //大气冷凝器
    //以第一个为例子  要填的值有4个
    //  "cryo_fuel1"                    -配方id
    //  "ad_astra:cryo_fuel"            -流体注册名
    //  25                              -流体数量
    //  "ad_astra:glacio_snowy_barrens" -群系
    public static List<AtmosphereCondensorRecipe> CondensorRecipeList = new ArrayList<>(List.of(

            new AtmosphereCondensorRecipe(cti.getResource("cryo_fuel1"),
                    new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("ad_astra:cryo_fuel")),25),
                    "ad_astra:glacio_snowy_barrens"),


            new AtmosphereCondensorRecipe(cti.getResource("cryo_fuel2"),
                    new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("ad_astra:cryo_fuel")),25),
                    "ad_astra:glacio_ice_peaks"),
            new AtmosphereCondensorRecipe(cti.getResource("overchargedneytronium"),
                    new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("etshtinker:overchargedneutronium")),1),
                    "cti:disordered_zone"),
            new AtmosphereCondensorRecipe(cti.getResource("sufuric_acid"),
                    new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("mekanism:sulfuric_acid")),1250),
                    "ad_astra:infernal_venus_barrens"),
            new AtmosphereCondensorRecipe(cti.getResource("sufuric_acid2"),
                    new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("mekanism:sulfuric_acid")),1250),
                    "ad_astra:venus_wastelands")
    ));

    //大气提取器
    //以第一个为例子  要填的值有4个
    //  "ice_shard"                     -配方id
    //  "ad_astra:ice_shard"            -物品注册名
    //  4                               -物品数量
    //  "ad_astra:glacio_snowy_barrens" -群系
    public static List<AtmosphereExtractorRecipe> ExtractorRecipeList = new ArrayList<>(List.of(

            new AtmosphereExtractorRecipe(cti.getResource("ice_shard"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ad_astra:ice_shard")),4),
                    "ad_astra:glacio_snowy_barrens"),


            new AtmosphereExtractorRecipe(cti.getResource("ice_shard2"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ad_astra:ice_shard")),4),
                    "ad_astra:glacio_ice_peaks")
    ));

    //量子采掘
    //以第一个为例子  要填的值有4个
    //  "copper_quantum"                -配方id
    //  "minecraft:copper_ore"          -物品注册名
    //  6                               -物品数量
    //  1.8f                            -产出概率（>1则增产）
    public static List<QuantumMinerRecipe> MinerRecipeList = new ArrayList<>(List.of(

            new QuantumMinerRecipe(cti.getResource("copper_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:copper_ore")),6),
                    1.8f),


            new QuantumMinerRecipe(cti.getResource("gold_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:gold_ore")),3),
                    1.1f),
            new QuantumMinerRecipe(cti.getResource("iron_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:iron_ore")),4),
                    1.25f)

    ));

    //中子素收集
    //以第一个为例子  要填的值有6个
    //  "activated_chroma"                  -配方id
    //  "avaritia:neutron_nugget"           -输出物品（通常来说不用改，就是中子素粒，当然也能改成别的）
    //  1                                   -输出数量
    //  1.965f                              -基础效率倍率（每个催化剂单元增加一次效率，加算）
    //  0.000075f                           -基础催化剂消耗概率（每个催化剂单元增加一次，加算，一次只消耗一个，每tick计算）
    //  "etshtinker:activated_chroma_plate" -催化剂种类
    //  3                               -每个催化剂单元的催化剂数量
    public static List<ReactorNeutronCollectorRecipe> NeutronRecipeList = new ArrayList<>(List.of(

            new ReactorNeutronCollectorRecipe(cti.getResource("activated_chroma"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("avaritia:neutron_nugget")),1),
                    1.965f,
                    0.000075f,
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("etshtinker:activated_chroma_plate")),3)),



            new ReactorNeutronCollectorRecipe(cti.getResource("chroma"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("avaritia:neutron_nugget")),1),
                    2.875f,
                    0.00775f,
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("etshtinker:chroma_plate")),5)),
            new ReactorNeutronCollectorRecipe(cti.getResource("ultra_dense"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("avaritia:neutron_nugget")),1),
                    12.675f,
                    0.00001f,
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("etshtinker:ultra_dense")),3)),
            new ReactorNeutronCollectorRecipe(cti.getResource("protonium"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("avaritia:neutron_nugget")),1),
                    3.398f,
                    0.000156f,
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("etshtinker:protonium")),3)),
            new ReactorNeutronCollectorRecipe(cti.getResource("electronium"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("avaritia:neutron_nugget")),1),
                    17.95f,
                    0.003673f,
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("etshtinker:electronium")),1))
    ));
}
