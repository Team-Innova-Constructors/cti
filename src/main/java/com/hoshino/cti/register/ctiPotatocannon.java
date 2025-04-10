package com.hoshino.cti.register;


import com.marth7th.solidarytinker.register.solidarytinkerItem;
import com.simibubi.create.Create;
import com.simibubi.create.content.equipment.potatoCannon.BuiltinPotatoProjectileTypes;
import com.simibubi.create.content.equipment.potatoCannon.PotatoCannonProjectileType;
import mekanism.common.registries.MekanismItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.EntityHitResult;

import java.util.function.Predicate;

public class ctiPotatocannon extends BuiltinPotatoProjectileTypes {

    public static final PotatoCannonProjectileType
            TIER_5_ROCKET = create("rocket")
            .damage(1000)
            .reloadTicks(15)
            .knockback(0.1f)
            .velocity(1.1f)
            .renderTumbling()
            .onEntityHit(potion(MobEffects.LEVITATION, 0, 200, true))
            .onEntityHit(fireandexplode(20, 1, false))
            .sticky()
            .soundPitch(1.0f)
            .registerAndAssign(ctiItem.TIER_5_ROCKET.get());


    public static final PotatoCannonProjectileType VIOLANE = create("violane")
            .damage(555)
            .reloadTicks(15)
            .knockback(0.1f)
            .velocity(1.1f)
            .renderTumbling()
            .sticky()
            .soundPitch(1.0f)
            .drag(2)
            .registerAndAssign(solidarytinkerItem.violane.get());
    public static final PotatoCannonProjectileType ANTI = create("anti")
            .damage(9999999)
            .reloadTicks(15)
            .knockback(1f)
            .velocity(1f)
            .renderTumbling()
            .sticky()
            .onEntityHit(fireandexplode(10, 2, true))
            .soundPitch(1.0f)
            .drag(2)
            .registerAndAssign(MekanismItems.ANTIMATTER_PELLET.get());

    public static void register() {

    }

    private static Predicate<EntityHitResult> fireandexplode(int seconds, int explodelevel, boolean ifbroke) {
        return ray -> {
            ray.getEntity().setSecondsOnFire(seconds);
            ray.getEntity().level.explode(ray.getEntity(), ray.getEntity().getX(), ray.getEntity().getY(), ray.getEntity().getZ(), explodelevel, ifbroke, Explosion.BlockInteraction.NONE);
            return false;
        };
    }

    private static Predicate<EntityHitResult> potion(MobEffect effect, int level, int ticks, boolean recoverable) {
        return ray -> {
            Entity entity = ray.getEntity();
            if (entity.level.isClientSide)
                return true;
            if (entity instanceof LivingEntity)
                applyEffect((LivingEntity) entity, new MobEffectInstance(effect, ticks, level - 1));
            return !recoverable;
        };
    }

    private static void applyEffect(LivingEntity entity, MobEffectInstance effect) {
        if (effect.getEffect()
                .isInstantenous())
            effect.getEffect()
                    .applyInstantenousEffect(null, null, entity, effect.getDuration(), 1.0);
        else
            entity.addEffect(effect);
    }

    private static PotatoCannonProjectileType.Builder create(String name) {
        return new PotatoCannonProjectileType.Builder(Create.asResource(name));
    }
}
