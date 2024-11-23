package com.hoshino.cti.mixin;

import com.hoshino.cti.Entity.Systems.EnvironmentSystem;
import com.hoshino.cti.register.ctiEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

import static com.hoshino.cti.Entity.Systems.EnvironmentSystem.getFreezeResistance;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract boolean hasEffect(MobEffect p_21024_);

    @Shadow @Nullable public abstract LivingEntity getLastHurtByMob();

    @Inject(at = @At(value = "HEAD"), method = "aiStep",cancellable = true)
    public void StopAi(CallbackInfo ci){
        LivingEntity entity =(LivingEntity) (Object)this;
        if (entity.getPersistentData().getInt("emp")>0){
            entity.getPersistentData().putInt("emp",entity.getPersistentData().getInt("emp")-1);
            if (entity.getPersistentData().getInt("emp")<=0){
                entity.getPersistentData().remove("emp");
            }
            ci.cancel();
        }
    }


    @Inject(at = @At(value = "HEAD"), method = "tick")
    public void CtiTick(CallbackInfo callbackInfo){
        LivingEntity entity = ((LivingEntity) (Object) this);
        Level level = entity.level;
        if (entity instanceof Player player && (player.isCreative() || player.isSpectator())) {
            return;
        }
        if (!(entity instanceof Player)&&entity!=null&&entity.getPersistentData().getBoolean("vulnerable")){
            entity.invulnerableTime=0;
        }
        if (!level.isClientSide) {
            if (level.getGameTime()%10==0){
                EnvironmentSystem.EnvironmentTick(entity,(ServerLevel) level);
            }
        }
    }
    @Inject(at = @At(value = "TAIL"), method = "tick")
    public void FreezeTick(CallbackInfo callbackInfo){
        LivingEntity entity = ((LivingEntity) (Object) this);
        Level level = entity.level;
        if (!level.isClientSide) {
            if (getFreezeResistance(entity) > 0.5 && entity instanceof Player) {
                entity.setIsInPowderSnow(false);
                entity.setTicksFrozen(-10);
            }
        }
    }
    @Inject(at =@At(value = "HEAD"),method = "hurt", cancellable = true)
    public void Hurt(DamageSource p_21016_, float p_21017_, CallbackInfoReturnable<Boolean> cir){
        if(this.hasEffect(ctiEffects.ev.get())){
            cir.setReturnValue(false);
        }
    }
}
