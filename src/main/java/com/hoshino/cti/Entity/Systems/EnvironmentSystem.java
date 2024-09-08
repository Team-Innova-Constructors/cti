package com.hoshino.cti.Entity.Systems;

import cofh.core.init.CoreMobEffects;
import cofh.core.init.CoreParticles;
import com.c2h6s.etshtinker.init.etshtinkerEffects;
import com.c2h6s.etshtinker.init.etshtinkerParticleType;
import com.hoshino.cti.Capabilitiess.IElectricShielding;
import com.hoshino.cti.Capabilitiess.IFreezeShielding;
import com.hoshino.cti.Capabilitiess.IScorchShielding;
import com.hoshino.cti.Capabilitiess.ctiCapabilities;
import com.hoshino.cti.register.ctiDamageSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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
    //直接伤害的危害基础伤害
    public static final float baseDmg = 20;
    //百分比伤害的危害基础百分比
    public static final float Multiplier =0.1f;

    //环境危害的计算函数，10ticks执行一次
    public static void EnvironmentTick(LivingEntity living, ServerLevel level) {
        Holder<Biome> biome = level.getBiome(living.blockPosition());
        //危害水平，等于群系水平-防护水平，后续可以补充
        float lvl_ionize = getBiomeIonizeLevel(biome) - getElectricResistance(living);
        float lvl_scorch = getBiomeScorchLevel(biome) -getScorchResistance(living);
        float lvl_freeze = getBiomeFreezeLevel(biome) -getFreezeResistance(living);
        //危害水平>0时的处理
        //为了获得死亡信息，用自定义伤害类型处死
        if (lvl_ionize > 0) {
            if (living.getHealth()>baseDmg * lvl_ionize) {
                living.setHealth(living.getHealth() - baseDmg * lvl_ionize);
                living.addEffect(new MobEffectInstance(etshtinkerEffects.ionized.get(),50,(int) lvl_ionize));
            }
            else if (living.isAlive()){
                living.setHealth(0.001f);
                living.hurt(ctiDamageSource.IONIZED.bypassArmor(), Float.MAX_VALUE);
                if (living.isAlive()){
                    living.hurt(ctiDamageSource.IONIZED,2048);
                }
            }
            if (living.isAlive()) {
                ((ServerLevel) living.level).sendParticles(etshtinkerParticleType.electric.get(), living.getX(), living.getY() + 0.5 * living.getBbHeight(), living.getZ(), (int) (baseDmg * lvl_ionize * 5), 0, 0, 0, 0.25);
            }
        }
        if (lvl_freeze > 0) {
            if (living.getHealth()>baseDmg * lvl_freeze) {
                living.setHealth(living.getHealth() - baseDmg * lvl_freeze *0.25f);
                living.addEffect(new MobEffectInstance(CoreMobEffects.CHILLED.get(),50,(int) lvl_freeze));
            }
            else if (living.isAlive()){
                living.setHealth(0.001f);
                living.hurt(ctiDamageSource.FROZEN.bypassArmor(), Float.MAX_VALUE);
                if (living.isAlive()){
                    living.hurt(ctiDamageSource.FROZEN,2048);
                }
            }
            if (living.isAlive()) {
                ((ServerLevel) living.level).sendParticles(CoreParticles.FROST.get(), living.getX(), living.getY() + 0.5 * living.getBbHeight(), living.getZ(), (int) (baseDmg * lvl_ionize * 5), 0, 0, 0, 0.25);
            }
        }
        if (lvl_scorch > 0) {
            if (living.getHealth()>baseDmg * lvl_scorch) {
                living.setHealth(living.getHealth() - baseDmg * lvl_scorch *0.5f);
                living.setSecondsOnFire(200);
            }
            else if (living.isAlive()){
                living.setHealth(0.001f);
                living.hurt(ctiDamageSource.SCORCH.bypassArmor(), Float.MAX_VALUE);
                if (living.isAlive()){
                    living.hurt(ctiDamageSource.SCORCH,2048);
                }
            }
            if (living.isAlive()) {
                ((ServerLevel) living.level).sendParticles(ParticleTypes.FLAME, living.getX(), living.getY() + 0.5 * living.getBbHeight(), living.getZ(), (int) (baseDmg * lvl_ionize * 5), 0, 0, 0, 0.25);
            }
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
    public static float getScorchResistance(LivingEntity living){
        float resist =0;
        for (EquipmentSlot slot:ARMOR_SLOTS){
            ItemStack stack = living.getItemBySlot(slot);
            Optional<IScorchShielding> shielding = getCapability(stack, ctiCapabilities.SCORCH_SHIELDING,null).resolve();
            if (shielding.isPresent()){
                resist+=shielding.get().getScorchShieldinng();
            }
        }
        return resist;
    }
    public static float getFreezeResistance(LivingEntity living){
        float resist =0;
        for (EquipmentSlot slot:ARMOR_SLOTS){
            ItemStack stack = living.getItemBySlot(slot);
            Optional<IFreezeShielding> shielding = getCapability(stack, ctiCapabilities.FREEZE_SHIELDING,null).resolve();
            if (shielding.isPresent()){
                resist+=shielding.get().getFreezeShieldinng();
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
