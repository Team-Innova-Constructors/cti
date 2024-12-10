package com.hoshino.cti.mixin.L2;

import dev.xkmc.l2hostility.content.traits.legendary.UndyingTrait;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = UndyingTrait.class, remap = false)
public abstract class UndyingTraitMixin {
    @Inject(at = {@At("HEAD")}, method = {"onDeath"}, cancellable = true)
    private void onDeath(int level, LivingEntity entity, LivingDeathEvent event, CallbackInfo ci){
        if(event.getSource().isBypassMagic()){
            ci.cancel();
        }
    }
}
