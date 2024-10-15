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
    public static final RegistryObject<BlockEntityType<QuantumMinerEntity>> QUANTUM_MINER_ENTITY=BLOCK_ENTITIES.register("quantum_miner",()->BlockEntityType.Builder.of(QuantumMinerEntity::new, ctiBlock.quantum_miner.get()).build(null));
    public static final RegistryObject<BlockEntityType<QuantumMinerAdvancedEntity>> QUANTUM_MINER_ADVANCED_ENTITY=BLOCK_ENTITIES.register("quantum_miner_advanced",()->BlockEntityType.Builder.of(QuantumMinerAdvancedEntity::new, ctiBlock.quantum_miner_advanced.get()).build(null));
    public static final RegistryObject<BlockEntityType<ReactorNeutronCollectorEntity>> REACTOR_NEUTRON_COLLECTOR=BLOCK_ENTITIES.register("reactor_neutron_collector",()->BlockEntityType.Builder.of(ReactorNeutronCollectorEntity::new, ctiBlock.reactor_neutron_collector.get()).build(null));
    public static final RegistryObject<BlockEntityType<AlloyCentrifugeEntity>> ALLOY_CENTRIFUGE=BLOCK_ENTITIES.register("alloy_centrifuge",()->BlockEntityType.Builder.of(AlloyCentrifugeEntity::new, ctiBlock.alloy_centrifuge_block.get()).build(null));
    public static final RegistryObject<BlockEntityType<SodiumCoolerEntity>> SODIUM_COOLER=BLOCK_ENTITIES.register("sodium_cooler",()->BlockEntityType.Builder.of(SodiumCoolerEntity::new, ctiBlock.sodium_cooler_block.get()).build(null));
}
