package com.hoshino.cti.world.feature;

import com.google.common.base.Suppliers;
import com.hoshino.cti.cti;
import com.hoshino.cti.register.ctiBlock;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ctiConfiguredFeature {
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, cti.MOD_ID);
    /*
    创建一个Supplier对象，提供一个包含了两个OreConfiguration.TargetBlockState对象的列表
    OreConfiguration.TargetBlockState 描述矿物生成时候的目标方块和替代方块。
    其中第一个参数：RuleTest 表示了替代的规则。 第二个参数BlockState表示了替代的方块和方块的状态。
    OreFeatures.STONE_ORE_REPLACEABLES 表示替代的规则是替代：石头、花岗岩、安山岩
    OreFeatures.DEEPSLATE_ORE_REPLACEABLES 替代深渊的石头。
     */
    public static final Supplier<List<OreConfiguration.TargetBlockState>> EXTRA_REDSTONE_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Blocks.REDSTONE_ORE.defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,Blocks.DEEPSLATE_REDSTONE_ORE.defaultBlockState())
    ));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> EXTRA_LAPIS_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Blocks.LAPIS_ORE.defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,Blocks.DEEPSLATE_LAPIS_ORE.defaultBlockState())
    ));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> EXTRA_IRON_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Blocks.IRON_ORE.defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,Blocks.DEEPSLATE_IRON_ORE.defaultBlockState())
    ));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> EXTRA_GOLD_ORES = Suppliers.memoize(()-> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Blocks.GOLD_ORE.defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,Blocks.DEEPSLATE_GOLD_ORE.defaultBlockState())
    ));
    public static final RegistryObject<ConfiguredFeature<?,?>> EXTRA_LAPIS_ORE = CONFIGURED_FEATURES.register("extra_lapis",
            ()->new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(EXTRA_LAPIS_ORES.get(),20)));

    public static final RegistryObject<ConfiguredFeature<?,?>> EXTRA_REDSTONE_ORE = CONFIGURED_FEATURES.register("extra_redstone",
            ()->new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(EXTRA_REDSTONE_ORES.get(),20)));

    public static final RegistryObject<ConfiguredFeature<?,?>> EXTRA_IRON_ORE = CONFIGURED_FEATURES.register("extra_iron",
            ()->new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(EXTRA_IRON_ORES.get(),30)));

    public static final RegistryObject<ConfiguredFeature<?,?>> EXTRA_GOLD_ORE = CONFIGURED_FEATURES.register("extra_gold",
            ()->new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(EXTRA_GOLD_ORES.get(),15)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> HULTRADENSE_GEODE = CONFIGURED_FEATURES.register("hultra_dense_geode",
            () -> new ConfiguredFeature<>(Feature.GEODE,
                    new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                            BlockStateProvider.simple(Blocks.BUDDING_AMETHYST),
                            BlockStateProvider.simple(ctiBlock.ultra_dense_hydride_ore.get()),
                            BlockStateProvider.simple(Blocks.CALCITE),
                            BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                            List.of(ctiBlock.ultra_dense_hydride_ore.get().defaultBlockState()),
                            BlockTags.FEATURES_CANNOT_REPLACE , BlockTags.GEODE_INVALID_BLOCKS),
                            new GeodeLayerSettings(1.7, 2.2, 3.2, 5.2), new GeodeCrackSettings(0.95, 2.0, 2), 0.5D, 0.1D,
                            true, UniformInt.of(6, 9), UniformInt.of(3, 4), UniformInt.of(1, 2), 16, -32, 0.05D, 1)));
}
