package com.hoshino.cti.recipe;

import com.hoshino.cti.cti;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ctiRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, cti.MOD_ID);

    public static final RegistryObject<RecipeSerializer<AtmosphereExtractorRecipe>> EXTRACTOR_SERIALIZER = SERIALIZERS.register("atmosphere_extract",() -> AtmosphereExtractorRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<AtmosphereCondensorRecipe>> CONDENSER_SERIALIZER = SERIALIZERS.register("atmosphere_condense",() -> AtmosphereCondensorRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<QuantumMinerRecipe>> QUANTUM_MINING = SERIALIZERS.register("quantum_mining",() -> QuantumMinerRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
