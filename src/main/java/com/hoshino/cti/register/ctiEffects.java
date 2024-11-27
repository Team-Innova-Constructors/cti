package com.hoshino.cti.register;

import com.hoshino.cti.Effects.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hoshino.cti.cti.MOD_ID;

public class ctiEffects {
    public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MOD_ID);
    public static final RegistryObject<MobEffect> stress =EFFECT.register("stress", Stress::new);
    public static final RegistryObject<MobEffect> ev =EFFECT.register("ev", Ev::new);
    public static final RegistryObject<MobEffect> Abel =EFFECT.register("abel", Abel::new);
    public static final RegistryObject<MobEffect> resolute =EFFECT.register("resolute", resolute::new);
    public static final RegistryObject<MobEffect> AncientDragonFlame =EFFECT.register("ancientdragonflame", AncientDragonFlame::new);
}
