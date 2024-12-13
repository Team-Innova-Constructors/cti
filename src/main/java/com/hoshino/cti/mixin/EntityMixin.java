package com.hoshino.cti.mixin;

import earth.terrarium.ad_astra.common.registry.ModDamageSource;
import me.desht.pneumaticcraft.common.entity.semiblock.AbstractSemiblockEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.hoshino.cti.Entity.Systems.EnvironmentSystem.getFreezeResistance;
import static com.hoshino.cti.Entity.Systems.EnvironmentSystem.getScorchResistance;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(at = @At(value = "HEAD"), method = "isInvulnerableTo", cancellable = true)
    private void setInvulnerableTo(DamageSource source, CallbackInfoReturnable<Boolean> cir){
        Entity entity =(Entity) (Object)this;
        if(entity instanceof Player living){
            if (getScorchResistance(living)>0.5&&source.isFire()){
                cir.setReturnValue(true);
            }
            if (getFreezeResistance(living)>0.5&&(source== ModDamageSource.CRYO_FUEL||source==DamageSource.FREEZE)){
                cir.setReturnValue(true);
            }
        }else if(entity!=null&&entity.getPersistentData().getBoolean("vulnerable")){
            cir.setReturnValue(false);
        }
        if(entity instanceof AbstractSemiblockEntity&&!source.isBypassInvul()){
            cir.setReturnValue(true);
        }

    }
    @Inject(at = @At(value = "HEAD"), method = "isInvulnerable", cancellable = true)
    private void setVulnerable(CallbackInfoReturnable<Boolean> cir){
        Entity entity =(Entity) (Object)this;
        if(entity!=null&&entity.getPersistentData().getBoolean("vulnerable")){
            cir.setReturnValue(false);
        }
    }
    @Inject(at = @At(value = "HEAD"), method = "fireImmune", cancellable = true)
    private void setFireImmune(CallbackInfoReturnable<Boolean> cir){
        Entity entity =(Entity) (Object)this;
        if(entity instanceof Player living){
            if (getScorchResistance(living)>0.5){
                cir.setReturnValue(true);
            }
        }
    }
    @Inject(at = @At(value = "HEAD"), method = "canFreeze", cancellable = true)
    private void setFreezeImmune(CallbackInfoReturnable<Boolean> cir){
        Entity entity =(Entity) (Object)this;
        if(entity instanceof Player living){
            if (getFreezeResistance(living)>0.5){
                cir.setReturnValue(false);
            }
        }
    }
}
