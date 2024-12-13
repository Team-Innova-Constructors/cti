package com.hoshino.cti.Modifier.Replace;

import com.hoshino.cti.Entity.Systems.EnvironmentSystem;
import com.xiaoyue.tinkers_ingenuity.TinkersIngenuity;
import com.xiaoyue.tinkers_ingenuity.generic.XIModifier;
import com.xiaoyue.tinkers_ingenuity.register.TIEffects;
import com.xiaoyue.tinkers_ingenuity.utils.EntityUtils;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

public class StrengthWill extends XIModifier {
    public static final TinkerDataCapability.TinkerDataKey<Integer> KEY = TinkersIngenuity.createKey("strength_will");
    public StrengthWill() {
    }
    public boolean isSingleLevel() {
        return true;
    }
    public TinkerDataCapability.TinkerDataKey<Integer> useKey() {
        return KEY;
    }
    public static void onLivDamage(LivingEntity entity) {
        entity.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            if (holder.get(KEY, 0) > 0 && entity.getHealth() >= entity.getMaxHealth()&&EnvironmentSystem.IsEnvironmentalSafe(entity)) {
                EntityUtils.addEffect(entity, TIEffects.LAST_STAND.get(), 40);
            }
        });
    }

}
