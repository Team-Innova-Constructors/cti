package com.hoshino.cti.Entity.Systems;

import com.c2h6s.etshtinker.init.etshtinkerParticleType;
import com.hoshino.cti.Capabilitiess.IElectricShielding;
import com.hoshino.cti.Capabilitiess.ctiCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;

import static com.hoshino.cti.util.BiomeUtil.*;

public class EnvironmentSystem {
    public static final EquipmentSlot[] ARMOR_SLOTS = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    public static final float baseDmg = 20;
    public static final float Multiplier =0.1f;
    public static void EnvironmentTick(LivingEntity living, ServerLevel level) {
        Holder<Biome> biome = level.getBiome(living.blockPosition());
        float lvl_ionize = getBiomeIonizeLevel(biome) - getElectricResistance(living);
        int lvl_scorch = getBiomeScorchLevel(biome);
        int lvl_freeze = getBiomeFreezeLevel(biome);
        if (lvl_ionize > 0) {
            living.invulnerableTime = 0;
            living.hurt(DamageSource.DRAGON_BREATH, 0.01f);
            living.setHealth(living.getHealth() - baseDmg * lvl_ionize);
            ((ServerLevel) living.level).sendParticles(etshtinkerParticleType.electric.get(), living.getX(), living.getY(), living.getZ(), (int) (baseDmg * lvl_ionize), 0, 0, 0, 0.25);
        }
    }
    public static float getElectricResistance(LivingEntity living){
        float resist =0;
        for (EquipmentSlot slot:ARMOR_SLOTS){
            ItemStack stack = living.getItemBySlot(slot);
            Optional<IElectricShielding> shielding = getCapability(stack, ctiCapabilities.ELECTRIC_SHIELDING,null).resolve();
            if (shielding.isPresent()){
                resist+=shielding.get().getElectricShieldinng();
            }
        }
        return resist;
    }

    @NotNull
    public static <T> LazyOptional<T> getCapability(@Nullable ICapabilityProvider provider, @Nullable Capability<T> cap, @Nullable Direction side) {
        if (provider == null || cap == null || !cap.isRegistered()) {
            return LazyOptional.empty();
        }
        return provider.getCapability(cap, side);
    }
}
