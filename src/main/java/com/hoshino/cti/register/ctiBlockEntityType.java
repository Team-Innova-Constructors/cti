package com.hoshino.cti.register;

import com.hoshino.cti.Blocks.BlockEntity.*;
import com.hoshino.cti.cti;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ctiBlockEntityType {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,cti.MOD_ID);

    public static final RegistryObject<BlockEntityType<AtmosphereExtractorEntity>> Atmosphere_extractor=BLOCK_ENTITIES.register("atmosphere_extractor",()->BlockEntityType.Builder.of(AtmosphereExtractorEntity::new, ctiBlock.atmosphere_extractor.get()).build(null));
    public static final RegistryObject<BlockEntityType<AtmosphereCondensatorEntity>> Atmosphere_condensator=BLOCK_ENTITIES.register("atmosphere_condensator",()->BlockEntityType.Builder.of(AtmosphereCondensatorEntity::new, ctiBlock.atmosphere_condensator.get()).build(null));
}
