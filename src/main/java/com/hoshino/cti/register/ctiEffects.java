package com.hoshino.cti.register;

import com.hoshino.cti.Effects.Stress;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.marth7th.solidarytinker.solidarytinker.MOD_ID;

public class ctiEffects {
    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MOD_ID);
    public static final RegistryObject<MobEffect> stress =EFFECT.register("stress", Stress::new);
}
