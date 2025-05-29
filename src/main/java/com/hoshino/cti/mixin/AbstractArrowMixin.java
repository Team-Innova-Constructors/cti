package com.hoshino.cti.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin {
    @Unique private Vec3 cti$deltaMovement;
    @Inject(at = @At("HEAD"),method = "onHitEntity")
    private void cancelVelocity(EntityHitResult pResult, CallbackInfo ci){
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        this.cti$deltaMovement = arrow.getDeltaMovement();
        CompoundTag nbt = arrow.getPersistentData();
        if (nbt.getFloat("cti_basedamage")>0) arrow.setBaseDamage(nbt.getFloat("cti_basedamage"));
        else nbt.putFloat("cti_basedamage", (float) arrow.getBaseDamage());
        arrow.setDeltaMovement(arrow.getDeltaMovement().scale(1/arrow.getDeltaMovement().length()));
    }
    @Inject(at = @At("TAIL"),method = "onHitEntity")
    private void addBackVelocity(EntityHitResult pResult, CallbackInfo ci){
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        if (this.cti$deltaMovement!=null) arrow.setDeltaMovement(this.cti$deltaMovement);
    }
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V"),method = "onHitBlock")
    private void cancelDamage(BlockHitResult pResult, CallbackInfo ci){
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        CompoundTag nbt = arrow.getPersistentData();
        nbt.remove("cti_basedamage");
        arrow.setBaseDamage(0);
    }

}
