package com.hoshino.cti.mixin.AetherMixin;

import com.aetherteam.aether.entity.monster.dungeon.boss.SunSpirit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SunSpirit.class)
public abstract class SpiritMixin extends PathfinderMob{

    @Shadow(remap = false) public abstract void setFrozen(boolean frozen);

    protected SpiritMixin(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @Unique
    private int cti$freezeTick;
    @Inject(method = "isInvulnerableTo",at = @At("HEAD"), cancellable = true)
    private void isInvulnerableTo(DamageSource source, CallbackInfoReturnable<Boolean> cir){
        if(cti$freezeTick >0){
            cir.setReturnValue(false);
        }
    }
    @Inject(method = "tick",at = @At("HEAD"))
    private void tick(CallbackInfo ci){
        cti$freezeTick--;
        if(cti$freezeTick >0&&!isFreezing()){
            setFrozen(true);
        }
    }
    @Inject(method = "hurt",at = @At("HEAD"))
    private void hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if(source.getMsgId().equals("aether.ice_crystal")){
            cti$freezeTick =200;
        }
    }
}
