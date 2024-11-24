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
                    "ad_astra:venus_wastelands"),

            new AtmosphereCondensorRecipe(cti.getResource("tritium"),
                    new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("mekanismgenerators:tritium")),500),
                    "cti:jupiter"),
            new AtmosphereCondensorRecipe(cti.getResource("deuterium"),
                    new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("mekanismgenerators:deuterium")),500),
                    "cti:uranus"),

            new AtmosphereCondensorRecipe(cti.getResource("dense_lava"),
                    new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("kubejs:dense_lava")),250),
                    "cti:infernal_molten_surface"),
            new AtmosphereCondensorRecipe(cti.getResource("volatile_lava"),
                    new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("kubejs:volatile_lava")),250),
                    "cti:infernal"),
            new AtmosphereCondensorRecipe(cti.getResource("crude_oil"),
                    new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("immersivepetroleum:crudeoil")),1000),
                    "undergarden:smog_spires")

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
                    "ad_astra:glacio_ice_peaks"),
            new AtmosphereExtractorRecipe(cti.getResource("gas_hydrate"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("kubejs:gas_hydrate")),1),
                    "cti:uranus"),
            new AtmosphereExtractorRecipe(cti.getResource("diamond_jupiter"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:diamond")),4),
                    "cti:jupiter"),
            new AtmosphereExtractorRecipe(cti.getResource("enriched_slag"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("kubejs:enriched_slag")),4),
                    "cti:infernal_molten_surface"),
            new AtmosphereExtractorRecipe(cti.getResource("stable_slag"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("kubejs:stable_slag")),4),
                    "cti:infernal"),
            new AtmosphereExtractorRecipe(cti.getResource("dryice"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("powah:dry_ice")),64),
                    "ad_astra:martian_canyon_creek"),
            new AtmosphereExtractorRecipe(cti.getResource("dryice2"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("powah:dry_ice")),64),
                    "ad_astra:martian_polar_caps"),
            new AtmosphereExtractorRecipe(cti.getResource("dryice2"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("powah:dry_ice")),64),
                    "ad_astra:martian_wastelands")
    ));

    //量子采掘
    //以第一个为例子  要填的值有4个
    //  "copper_quantum"                -配方id
    //  "minecraft:copper_ore"          -物品注册名
    //  6                               -物品数量
    //  1.8f                            -产出概率（>1则增产）
    public static List<QuantumMinerRecipe> MinerRecipeList = new ArrayList<>(List.of(

            new QuantumMinerRecipe(cti.getResource("copper_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:copper_ore")),12),
                    1.8f),


            new QuantumMinerRecipe(cti.getResource("gold_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:gold_ore")),3),
                    1.1f),
            new QuantumMinerRecipe(cti.getResource("osmium_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("mekanism:osmium_ore")),4),
                    1.21f),
            new QuantumMinerRecipe(cti.getResource("iron_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:iron_ore")),4),
                    1.25f),
            new QuantumMinerRecipe(cti.getResource("coal_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:coal_ore")),9),
                    1.5f),
            new QuantumMinerRecipe(cti.getResource("diamond_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:diamond_ore")),2),
                    1.25f),
            new QuantumMinerRecipe(cti.getResource("glowstone_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:glowstone")),2),
                    1.6f),
            new QuantumMinerRecipe(cti.getResource("redstone_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:redstone_ore")),4),
                    1.15f),
            new QuantumMinerRecipe(cti.getResource("lapis_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:lapis_ore")),4),
                    1.15f),
            new QuantumMinerRecipe(cti.getResource("silver_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:silver_ore")),3),
                    1.45f),
            new QuantumMinerRecipe(cti.getResource("nickel_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:nickel_ore")),4),
                    1.05f),

            new QuantumMinerRecipe(cti.getResource("emerald_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:emerald_ore")),4),
                    1.15f),
            new QuantumMinerRecipe(cti.getResource("tin_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:tin_ore")),3),
                    1.45f),
            new QuantumMinerRecipe(cti.getResource("lead_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:lead_ore")),4),
                    1.05f),
            new QuantumMinerRecipe(cti.getResource("aluminium_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("immersiveengineering:ore_aluminum")),4),
                    1.65f),

            new QuantumMinerRecipe(cti.getResource("calorite_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ad_astra:venus_calorite_ore")),3),
                    0.55f),
            new QuantumMinerRecipe(cti.getResource("desh_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ad_astra:moon_desh_ore")),4),
                    0.75F),
            new QuantumMinerRecipe(cti.getResource("ostrum_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ad_astra:mars_ostrum_ore")),4),
                    0.75F),

            new QuantumMinerRecipe(cti.getResource("zirconium_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tinkers_reforged:titanium_ore")),2),
                    0.75f),
            new QuantumMinerRecipe(cti.getResource("ttan_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tinkerscalibration:titanium_ore")),3),
                    0.95F),


            new QuantumMinerRecipe(cti.getResource("neutronium_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("kubejs:overdense_neutronium_ore")),1),
                    0.1F),

            new QuantumMinerRecipe(cti.getResource("bismuthinite_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("etshtinker:bismuthinite_ore_deepslate")),2),
                    1.2f),
            new QuantumMinerRecipe(cti.getResource("cobalt_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tconstruct:cobalt_ore")),2),
                    1.35f),
            new QuantumMinerRecipe(cti.getResource("debris_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:ancient_debris")),2),
                    0.75f),
            new QuantumMinerRecipe(cti.getResource("quartz_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:nether_quartz_ore")),3),
                    1.3F),
            new QuantumMinerRecipe(cti.getResource("zinc_ore_quantum"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("create:zinc_ore")),4),
                    1.7F)

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
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("avaritia:neutron_nugget")),2),
                    12.675f,
                    0.00001f,
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("etshtinker:ultra_dense")),3)),
            new ReactorNeutronCollectorRecipe(cti.getResource("protonium"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("etshtinker:electronium")),1),
                    3.398f,
                    0.00956f,
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("etshtinker:protonium")),3)),
            new ReactorNeutronCollectorRecipe(cti.getResource("electronium"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("kubejs:protonium_nugget")),1),
                    17.95f,
                    0.000156f,
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("etshtinker:electronium")),1)),
            new ReactorNeutronCollectorRecipe(cti.getResource("violium_alloy"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("avaritia:neutron_nugget")),1),
                    12.198f,
                    0.00002f,
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("kubejs:violium_alloy")),2)),
            new ReactorNeutronCollectorRecipe(cti.getResource("atherium_alloy"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("avaritia:neutron_nugget")),1),
                    40.25f,
                    0.000393f,
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("kubejs:atherium_alloy")),1)),
            new ReactorNeutronCollectorRecipe(cti.getResource("anti_neutronium"),
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("avaritia:neutron_ingot")),2),
                    32f,
                    0.0000f,
                    new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("etshtinker:anti_neutronium")),2))

    ));
}
