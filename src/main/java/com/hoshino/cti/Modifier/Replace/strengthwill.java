package com.hoshino.cti.Modifier.Replace;

import com.hoshino.cti.Entity.Systems.EnvironmentSystem;
import com.hoshino.cti.util.BiomeUtil;
import com.xiaoyue.tinkers_ingenuity.TinkersIngenuity;
import com.xiaoyue.tinkers_ingenuity.generic.XIModifier;
import com.xiaoyue.tinkers_ingenuity.register.TIEffects;
import com.xiaoyue.tinkers_ingenuity.utils.entity.EntityUtils;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.biome.Biome;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

public class strengthwill extends XIModifier {
    public static final TinkerDataCapability.TinkerDataKey<Integer> KEY = TinkersIngenuity.createKey("strength_will");
    public strengthwill() {
    }
    public boolean isSingleLevel() {
        return true;
    }
    public TinkerDataCapability.TinkerDataKey<Integer> useKey() {
        return KEY;
    }
    public static void onLivDamage(LivingEntity entity) {
        entity.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            if ((Integer)holder.get(KEY, 0) > 0 && entity.getHealth() >= entity.getMaxHealth()&&EnvironmentSystem.allEnvironmentValue(entity)<0) {
                EntityUtils.addEffect(entity, (MobEffect) TIEffects.LAST_STAND.get(), 40);
                entity.getPersistentData().putInt("wait",1);
            }
        });
    }

}
