package com.hoshino.cti.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistries;

public class StrictDamageProcess {
    public static float getStrictDamageForEntity(LivingEntity living,float damage){
        if (ForgeRegistries.ENTITY_TYPES.getKey(living.getType())!=null){
            if (ForgeRegistries.ENTITY_TYPES.getKey(living.getType()).getNamespace().equals("cataclysm")){
                return damage*0.25f;
            }
        }
        return damage;
    }
}
