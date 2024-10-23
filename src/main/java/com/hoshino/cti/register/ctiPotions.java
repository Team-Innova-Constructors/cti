package com.hoshino.cti.register;

import com.hoshino.cti.cti;
import com.marth7th.solidarytinker.register.solidarytinkerEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ctiPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, cti.MOD_ID);
    public static final RegistryObject<Potion> BLOODANGER = POTIONS.register("bloodanger", () -> new Potion("bloodanger", new MobEffectInstance(solidarytinkerEffects.bloodanger.get(), 4800)));
    public static final RegistryObject<Potion> LONG_BLOODANGER = POTIONS.register("long_bloodanger", () -> new Potion("bloodanger", new MobEffectInstance(solidarytinkerEffects.bloodanger.get(), 9600)));
    public static final RegistryObject<Potion> STRONG_BLOODANGER = POTIONS.register("strong_bloodanger", () -> new Potion("bloodanger", new MobEffectInstance(solidarytinkerEffects.bloodanger.get(), 2400,1)));

}
