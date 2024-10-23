package com.hoshino.cti.register;

import com.hoshino.cti.Entity.Projectiles.FallenStars;
import com.hoshino.cti.Entity.Projectiles.TinkerRailgunProjectile;
import com.hoshino.cti.Entity.vehicles.rocketTier5;
import com.hoshino.cti.client.renderer.projectile.LargeBrightItemProjectile;
import com.hoshino.cti.client.renderer.projectile.TinkerRaligunRenderer;
import com.hoshino.cti.client.renderer.vehicle.rocketTier5.RocketRendererTier5;
import com.xiaoyue.tinkers_ingenuity.register.TIItems;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.EntityTypeDeferredRegister;
import earth.terrarium.botarium.client.ClientHooks;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.TinkerTools;

public class ctiEntity {
    public static final EntityTypeDeferredRegister ENTITIES = new EntityTypeDeferredRegister("cti");
    public static final ResourcefulRegistry<EntityType<?>> ENTITY_TYPES = ResourcefulRegistries.create(Registry.ENTITY_TYPE, "cti");
    public static final RegistryEntry<EntityType<rocketTier5>> TIER_5_ROCKET = ENTITY_TYPES.register("tier_5_rocket", () -> EntityType.Builder.<rocketTier5>of(rocketTier5::new, MobCategory.MISC).sized(1.1f, 7.0f).fireImmune().build("cti"));
    public static final RegistryObject<EntityType<FallenStars>> star_blaze = ENTITIES.register("star_blaze", () -> EntityType.Builder.<FallenStars>of((entityType,level)->new FallenStars(entityType,level,ctiItem.star_blaze.get()), MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(1).setCustomClientFactory((spawnEntity, world) -> new FallenStars(ctiEntity.star_blaze.get(), world,ctiItem.star_blaze.get())).setShouldReceiveVelocityUpdates(true));
    public static final RegistryObject<EntityType<FallenStars>> star_ionize = ENTITIES.register("star_ionize", () -> EntityType.Builder.<FallenStars>of((entityType,level)->new FallenStars(entityType,level,ctiItem.star_ionize.get()), MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(1).setCustomClientFactory((spawnEntity, world) -> new FallenStars(ctiEntity.star_ionize.get(), world,ctiItem.star_ionize.get())).setShouldReceiveVelocityUpdates(true));
    public static final RegistryObject<EntityType<FallenStars>> star_frozen = ENTITIES.register("star_frozen", () -> EntityType.Builder.<FallenStars>of((entityType,level)->new FallenStars(entityType,level,ctiItem.star_frozen.get()), MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(1).setCustomClientFactory((spawnEntity, world) -> new FallenStars(ctiEntity.star_frozen.get(), world,ctiItem.star_frozen.get())).setShouldReceiveVelocityUpdates(true));
    public static final RegistryObject<EntityType<FallenStars>> star_pressure = ENTITIES.register("star_pressure", () -> EntityType.Builder.<FallenStars>of((entityType,level)->new FallenStars(entityType,level,ctiItem.star_pressure.get()), MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(1).setCustomClientFactory((spawnEntity, world) -> new FallenStars(ctiEntity.star_pressure.get(), world,ctiItem.star_pressure.get())).setShouldReceiveVelocityUpdates(true));
    public static final RegistryObject<EntityType<TinkerRailgunProjectile>> tinker_railgun = ENTITIES.register("tinker_railgun", () -> EntityType.Builder.<TinkerRailgunProjectile>of((entityType, level)->new TinkerRailgunProjectile(entityType,level,new ItemStack(TinkerToolParts.toolHandle), TinkerTools.cleaver.get()), MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(1).setCustomClientFactory((spawnEntity, world) -> new TinkerRailgunProjectile(ctiEntity.tinker_railgun.get(), world,new ItemStack(TinkerToolParts.toolHandle),TinkerTools.cleaver.get())).setShouldReceiveVelocityUpdates(true));
    public static final RegistryObject<EntityType<TinkerRailgunProjectile>> tinker_railgun_raiper = ENTITIES.register("tinker_railgun_raiper", () -> EntityType.Builder.<TinkerRailgunProjectile>of((entityType, level)->new TinkerRailgunProjectile(entityType,level,new ItemStack(TinkerToolParts.toughHandle), TIItems.RAPIER.get()), MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(1).setCustomClientFactory((spawnEntity, world) -> new TinkerRailgunProjectile(ctiEntity.tinker_railgun.get(), world,new ItemStack(TinkerToolParts.toughHandle),TIItems.RAPIER.get())).setShouldReceiveVelocityUpdates(true));
    public static void registerEntityRenderers() {
        ClientHooks.registerEntityRenderer(ctiEntity.TIER_5_ROCKET, RocketRendererTier5::new);
        ClientHooks.registerEntityRenderer(ctiEntity.star_blaze, LargeBrightItemProjectile::new);
        ClientHooks.registerEntityRenderer(ctiEntity.star_ionize, LargeBrightItemProjectile::new);
        ClientHooks.registerEntityRenderer(ctiEntity.star_frozen, LargeBrightItemProjectile::new);
        ClientHooks.registerEntityRenderer(ctiEntity.star_pressure, LargeBrightItemProjectile::new);
        ClientHooks.registerEntityRenderer(ctiEntity.tinker_railgun, TinkerRaligunRenderer::new);
        ClientHooks.registerEntityRenderer(ctiEntity.tinker_railgun_raiper, TinkerRaligunRenderer::new);
    }
}
