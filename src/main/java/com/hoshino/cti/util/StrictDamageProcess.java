package com.hoshino.cti.util;

import com.hoshino.cti.register.CtiHostilityTrait;
import dev.xkmc.l2hostility.content.capability.mob.MobTraitCap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistries;

public class StrictDamageProcess {
    public static float getStrictDamageForEntity(LivingEntity living,float damage){
        var cap = MobTraitCap.HOLDER.get(living);
        if (cap.hasTrait(CtiHostilityTrait.EXTREME_DAMAGE_REDUCE.get())) damage*=0.4f;
        return damage;
    }
}
