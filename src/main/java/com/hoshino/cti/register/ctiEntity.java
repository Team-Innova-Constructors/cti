package com.hoshino.cti.register;

import com.hoshino.cti.Entity.vehicles.rocketTier5;
import com.hoshino.cti.client.renderer.vehicle.rocketTier5.RocketRendererTier5;
import earth.terrarium.ad_astra.AdAstra;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import slimeknights.mantle.registration.deferred.EntityTypeDeferredRegister;
import earth.terrarium.botarium.client.ClientHooks;

public class ctiEntity {
    public static final EntityTypeDeferredRegister ENTITIES = new EntityTypeDeferredRegister("cti");
    public static final ResourcefulRegistry<EntityType<?>> ENTITY_TYPES = ResourcefulRegistries.create(Registry.ENTITY_TYPE, "cti");
    public static final RegistryEntry<EntityType<rocketTier5>> TIER_5_ROCKET = ENTITY_TYPES.register("tier_5_rocket", () -> EntityType.Builder.<rocketTier5>of(rocketTier5::new, MobCategory.MISC).sized(1.1f, 7.0f).fireImmune().build("cti"));
    public static void registerEntityRenderers() {
        ClientHooks.registerEntityRenderer(ctiEntity.TIER_5_ROCKET, RocketRendererTier5::new);
    }
}
