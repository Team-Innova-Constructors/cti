package com.hoshino.cti.client;

import com.hoshino.cti.Cti;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CtiParticleType {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Cti.MOD_ID);
    public static final RegistryObject<SimpleParticleType> STAR_LINE = PARTICLES.register("star_line",()->new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> RED_SPARK = PARTICLES.register("spark_red",()->new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> FIERY_EXPLODE = PARTICLES.register("fiery_explode",()->new SimpleParticleType(false));

}
