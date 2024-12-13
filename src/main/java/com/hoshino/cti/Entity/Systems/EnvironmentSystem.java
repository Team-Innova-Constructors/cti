package com.hoshino.cti.Entity.Systems;

import cofh.core.init.CoreMobEffects;
import cofh.core.init.CoreParticles;
import com.c2h6s.etshtinker.init.etshtinkerEffects;
import com.c2h6s.etshtinker.init.etshtinkerParticleType;
import com.hoshino.cti.Capabilitiess.*;
import com.hoshino.cti.Entity.specialDamageSource.Environmental;
import com.hoshino.cti.netwrok.ctiPacketHandler;
import com.hoshino.cti.netwrok.packet.PFrozenValueSync;
import com.hoshino.cti.netwrok.packet.PIonizeValueSync;
import com.hoshino.cti.netwrok.packet.PPressureValueSync;
import com.hoshino.cti.netwrok.packet.PScorchValueSync;
import com.hoshino.cti.util.ctiTagkey;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
    //直接伤害的危害基础伤害
    public static final float baseDmg = 10;
    //百分比伤害的危害基础百分比
    public static final float Multiplier =0.1f;

    public static final String IONIZED_AMOUNT ="environmental.ionized";
    public static final String SCORCH_AMOUNT ="environmental.scorch";
    public static final String FROZEN_AMOUNT ="environmental.frozen";
    public static final String PRESSURE_AMOUNT ="environmental.pressure";

    //环境危害的计算函数，10ticks执行一次
    public static void EnvironmentTick(LivingEntity living, ServerLevel level) {
        Holder<Biome> biome = level.getBiome(living.blockPosition());
        //危害水平，等于群系水平-防护水平，后续可以补充
        float lvl_ionize = getBiomeIonizeLevel(biome) - getElectricResistance(living);
        float lvl_scorch = getBiomeScorchLevel(biome) -getScorchResistance(living) -getBiomeFreezeLevel(biome);
        float lvl_freeze = getBiomeFreezeLevel(biome) -getFreezeResistance(living) -getBiomeScorchLevel(biome);
        float lvl_pressure = getBiomePressureLevel(biome) - getPressureResistance(living);
        if (living.getMaxHealth()>5000&&!(living instanceof Player)){
            lvl_ionize=-0.5F;
            lvl_scorch=-0.5F;
            lvl_freeze=-0.5F;
            lvl_pressure=-0.5F;
        }
        if (lvl_pressure>1.5){
            living.invulnerableTime = 0;
            if (living instanceof Player player) {
                living.hurt(Environmental.playerPressureSource(Float.MAX_VALUE, player),Float.MAX_VALUE);
            }else {
                living.hurt(Environmental.pressureSource(Float.MAX_VALUE), Float.MAX_VALUE);
            }
        }
        CompoundTag nbt = living.getPersistentData();
        //积累危害值
        nbt.putFloat(IONIZED_AMOUNT, Mth.clamp(nbt.getFloat(IONIZED_AMOUNT)+lvl_ionize,-20*(1+getElectricResistance(living)),10000));
        nbt.putFloat(SCORCH_AMOUNT, Mth.clamp(nbt.getFloat(SCORCH_AMOUNT)+lvl_scorch,-40*(1+getScorchResistance(living)),10000));
        nbt.putFloat(FROZEN_AMOUNT, Mth.clamp(nbt.getFloat(FROZEN_AMOUNT)+lvl_freeze,-60*(1+getScorchResistance(living)),10000));
        nbt.putFloat(PRESSURE_AMOUNT, Mth.clamp(nbt.getFloat(PRESSURE_AMOUNT)+lvl_pressure,-100*(1+getPressureResistance(living)),10000));
        float ion_multiplier = (nbt.getFloat(IONIZED_AMOUNT)-50)/25;
        float sco_multiplier = (nbt.getFloat(SCORCH_AMOUNT)-50)/75-0.25f;
        float fro_multiplier = (nbt.getFloat(FROZEN_AMOUNT)-50)/100-0.25f;
        float pre_multiplier = (nbt.getFloat(PRESSURE_AMOUNT)-50)/50-0.25f;
        //危害值>0时的处理
        if (ion_multiplier > 0&&living.isAlive()) {
            living.invulnerableTime = 0;
            if (living instanceof Player player){
                living.hurt(Environmental.playerIonizedSource( baseDmg * ion_multiplier,player), Multiplier*0.25f*living.getMaxHealth() * ion_multiplier);
            }else {
                living.hurt(Environmental.ionizedSource(baseDmg * ion_multiplier), Multiplier * 0.25f * living.getMaxHealth() * ion_multiplier);
            }
            living.addEffect(new MobEffectInstance(etshtinkerEffects.ionized.get(), 50, (int) ion_multiplier));
            ((ServerLevel) living.level).sendParticles(etshtinkerParticleType.electric.get(), living.getX(), living.getY() + 0.5 * living.getBbHeight(), living.getZ(), (int) (baseDmg * fro_multiplier * 3), 0, 0, 0, 0.25);
            nbt.putFloat(IONIZED_AMOUNT,Math.max(0,nbt.getFloat(IONIZED_AMOUNT)*0.99f));
        }
        if (sco_multiplier > 0&&living.isAlive()) {
            living.invulnerableTime = 0;
            if (living instanceof Player player) {
                living.hurt(Environmental.playerScorchSource(baseDmg * sco_multiplier,player), baseDmg * sco_multiplier);
            }else {
                living.hurt(Environmental.scorchSource(baseDmg * sco_multiplier), baseDmg * sco_multiplier);
            }
            living.setSecondsOnFire(20);
            ((ServerLevel) living.level).sendParticles(ParticleTypes.FLAME, living.getX(), living.getY() + 0.5 * living.getBbHeight(), living.getZ(), (int) (baseDmg * sco_multiplier * 3), 0, 0, 0, 0.25);
            nbt.putFloat(SCORCH_AMOUNT,Math.max(0,nbt.getFloat(SCORCH_AMOUNT)*0.99f));
        }
        if (fro_multiplier > 0&&living.isAlive()) {
            living.invulnerableTime = 0;
            if (living instanceof Player player) {
                living.hurt(Environmental.playerFrozenSource(baseDmg * fro_multiplier,player), baseDmg * 0.5f * fro_multiplier);
            }else {
                living.hurt(Environmental.frozenSource(baseDmg * fro_multiplier), baseDmg * 0.5f * fro_multiplier);
            }
            living.addEffect(new MobEffectInstance(CoreMobEffects.CHILLED.get(), 50, (int) fro_multiplier));
            ((ServerLevel) living.level).sendParticles(CoreParticles.FROST.get(), living.getX(), living.getY() + 0.5 * living.getBbHeight(), living.getZ(), (int) (baseDmg * fro_multiplier * 3), 0, 0, 0, 0.25);
            nbt.putFloat(FROZEN_AMOUNT,Math.max(0,nbt.getFloat(FROZEN_AMOUNT)*0.99f));
        }
        if (pre_multiplier > 0&&living.isAlive()) {
            living.invulnerableTime = 0;
            if (living instanceof Player player) {
                living.hurt(Environmental.playerPressureSource(Multiplier * pre_multiplier * living.getMaxHealth(),player), Multiplier * pre_multiplier * living.getMaxHealth());
            }else {
                living.hurt(Environmental.pressureSource(Multiplier * pre_multiplier * living.getMaxHealth()), Multiplier * pre_multiplier * living.getMaxHealth());
            }
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 50, (int) pre_multiplier*2));
            ((ServerLevel) living.level).sendParticles(ParticleTypes.SMOKE, living.getX(), living.getY() + 0.5 * living.getBbHeight(), living.getZ(), (int) (baseDmg * pre_multiplier * 3), 0, 0, 0, 0.25);
            nbt.putFloat(PRESSURE_AMOUNT,Math.max(0,nbt.getFloat(PRESSURE_AMOUNT)*0.99f));
        }
        //发包
        if (living instanceof ServerPlayer player){
            ctiPacketHandler.sendToPlayer(new PIonizeValueSync(nbt.getFloat(IONIZED_AMOUNT),lvl_ionize),player);
            ctiPacketHandler.sendToPlayer(new PScorchValueSync(nbt.getFloat(SCORCH_AMOUNT),lvl_scorch),player);
            ctiPacketHandler.sendToPlayer(new PFrozenValueSync(nbt.getFloat(FROZEN_AMOUNT),lvl_freeze),player);
            ctiPacketHandler.sendToPlayer(new PPressureValueSync(nbt.getFloat(PRESSURE_AMOUNT),lvl_pressure),player);
        }
    }

    public static void addScorchValue(@NotNull LivingEntity living,float amount){
        CompoundTag nbt = living.getPersistentData();
        nbt.putFloat(SCORCH_AMOUNT,nbt.getFloat(SCORCH_AMOUNT)+amount);
    }
    public static void addFrozenValue(@NotNull LivingEntity living,float amount){
        CompoundTag nbt = living.getPersistentData();
        nbt.putFloat(FROZEN_AMOUNT,nbt.getFloat(FROZEN_AMOUNT)+amount);
    }
    public static void addIonizedValue(@NotNull LivingEntity living,float amount){
        CompoundTag nbt = living.getPersistentData();
        nbt.putFloat(IONIZED_AMOUNT,nbt.getFloat(IONIZED_AMOUNT)+amount);
    }
    public static void addPressureValue(@NotNull LivingEntity living,float amount){
        CompoundTag nbt = living.getPersistentData();
        nbt.putFloat(PRESSURE_AMOUNT,nbt.getFloat(PRESSURE_AMOUNT)+amount);
    }

    public static float getScorchValue(@NotNull LivingEntity living){
        return living.getPersistentData().getFloat(SCORCH_AMOUNT)-50;
    }
    public static float getFrozenValue(@NotNull LivingEntity living){
        return living.getPersistentData().getFloat(FROZEN_AMOUNT)-50;
    }
    public static float getIonizedValue(@NotNull LivingEntity living){
        return living.getPersistentData().getFloat(IONIZED_AMOUNT)-50;
    }
    public static float getPressureValue(@NotNull LivingEntity living){
        return living.getPersistentData().getFloat(PRESSURE_AMOUNT)-50;
    }
    public static float allEnvironmentValue(@NotNull LivingEntity living){
        return Math.max(getPressureValue(living),0)+Math.max(getFrozenValue(living),0)+Math.max(getIonizedValue(living),0)+Math.max(getScorchValue(living),0);
    }
    public static boolean IsEnvironmentalSafe(@NotNull LivingEntity living){
        Holder<Biome> biome =living.level.getBiome(living.blockPosition());
        float lvl_ionize = getBiomeIonizeLevel(biome) - getElectricResistance(living);
        float lvl_scorch = getBiomeScorchLevel(biome) -getScorchResistance(living) -getBiomeFreezeLevel(biome);
        float lvl_freeze = getBiomeFreezeLevel(biome) -getFreezeResistance(living) -getBiomeScorchLevel(biome);
        float lvl_pressure = getBiomePressureLevel(biome) - getPressureResistance(living);
        return lvl_freeze<=0&&lvl_pressure<=0&&lvl_scorch<=0&&lvl_ionize<=0;
    }

    public static float getElectricResistance(LivingEntity living){
        float resist =0;
        if (!(living instanceof Player)){
            resist+=1;
        }
        for (EquipmentSlot slot:ARMOR_SLOTS){
            ItemStack stack = living.getItemBySlot(slot);
            Optional<IElectricShielding> shielding = getCapability(stack, ctiCapabilities.ELECTRIC_SHIELDING,null).resolve();
            if (shielding.isPresent()){
                resist+=shielding.get().getElectricShieldinng();
            }
            else if (stack.getTags().toList().contains(ctiTagkey.ENVIRONMENT_ADV)){
                resist+=10f;
            }
        }
        return resist;
    }
    public static float getScorchResistance(LivingEntity living){
        float resist =0;
        if (!(living instanceof Player)){
            resist+=1;
        }
        for (EquipmentSlot slot:ARMOR_SLOTS){
            ItemStack stack = living.getItemBySlot(slot);
            Optional<IScorchShielding> shielding = getCapability(stack, ctiCapabilities.SCORCH_SHIELDING,null).resolve();
            if (shielding.isPresent()){
                resist+=shielding.get().getScorchShieldinng();
            }
            else if (stack.getTags().toList().contains(ctiTagkey.ENVIRONMENT_ADV)){
                resist+=10f;
            }
        }
        return resist;
    }
    public static float getFreezeResistance(LivingEntity living){
        float resist =0;
        if (!(living instanceof Player)){
            resist+=1;
        }
        for (EquipmentSlot slot:ARMOR_SLOTS){
            ItemStack stack = living.getItemBySlot(slot);
            Optional<IFreezeShielding> shielding = getCapability(stack, ctiCapabilities.FREEZE_SHIELDING,null).resolve();
            if (shielding.isPresent()){
                resist+=shielding.get().getFreezeShieldinng();
            }
            else if (stack.getTags().toList().contains(ctiTagkey.PRESSURE_MINOR)){
                resist+=0.5f;
            }
            else if (stack.getTags().toList().contains(ctiTagkey.ENVIRONMENT_ADV)){
                resist+=10f;
            }
        }
        return resist;
    }

    public static float getPressureResistance(LivingEntity living){
        float resist =0;
        if (!(living instanceof Player)){
            resist+=1;
        }
        for (EquipmentSlot slot:ARMOR_SLOTS){
            ItemStack stack = living.getItemBySlot(slot);
            Optional<IPressureShielding> shielding = getCapability(stack, ctiCapabilities.PRESSURE_SHIELDING,null).resolve();
            if (shielding.isPresent()){
                resist+=shielding.get().getPressureShielding();
            }
            else if (stack.getTags().toList().contains(ctiTagkey.PRESSURE_MINOR)){
                resist+=0.5f;
            }
            else if (stack.getTags().toList().contains(ctiTagkey.ENVIRONMENT_ADV)){
                resist+=10f;
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
