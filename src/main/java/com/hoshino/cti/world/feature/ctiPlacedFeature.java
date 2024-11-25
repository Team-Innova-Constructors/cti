package com.hoshino.cti.world.feature;

import com.hoshino.cti.cti;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ctiPlacedFeature {


    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, cti.MOD_ID);


    public static final RegistryObject<PlacedFeature> EXTRA_LAPIS_ORE_PLACED = PLACED_FEATURES.register("extra_lapis_ore_placed",
            () -> new PlacedFeature(ctiConfiguredFeature.EXTRA_LAPIS_ORE.getHolder().get(),
                    commonOrePlacement(2,
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20),VerticalAnchor.aboveBottom(56)))));
    public static final RegistryObject<PlacedFeature> EXTRA_REDSTONE_ORE_PLACED = PLACED_FEATURES.register("extra_redstone_ore_placed",
            () -> new PlacedFeature(ctiConfiguredFeature.EXTRA_REDSTONE_ORE.getHolder().get(),
                    commonOrePlacement(3,
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-20),VerticalAnchor.aboveBottom(56)))));
    public static final RegistryObject<PlacedFeature> EXTRA_IRON_ORE_PLACED = PLACED_FEATURES.register("extra_iron_ore_placed",
            () -> new PlacedFeature(ctiConfiguredFeature.EXTRA_IRON_ORE.getHolder().get(),
                    commonOrePlacement(3,
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-10),VerticalAnchor.aboveBottom(40)))));
    public static final RegistryObject<PlacedFeature> EXTRA_GOLD_ORE_PLACED = PLACED_FEATURES.register("extra_gold_ore_placed",
            () -> new PlacedFeature(ctiConfiguredFeature.EXTRA_GOLD_ORE.getHolder().get(),
                    commonOrePlacement(3,
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-10),VerticalAnchor.aboveBottom(40)))));

    public static final RegistryObject<PlacedFeature> HULTRADENSE_GEODE_PLACED = PLACED_FEATURES.register("hultra_dense_geode_placed",
            () -> new PlacedFeature(ctiConfiguredFeature.HULTRADENSE_GEODE.getHolder().get(), List.of(
                    RarityFilter.onAverageOnceEvery(50), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(27), VerticalAnchor.absolute(84)),
                    BiomeFilter.biome())));

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }
}
