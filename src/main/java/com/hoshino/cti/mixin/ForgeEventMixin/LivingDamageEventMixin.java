package com.hoshino.cti.mixin.ForgeEventMixin;

import com.hoshino.cti.Entity.specialDamageSource.Environmental;
import com.hoshino.cti.Entity.specialDamageSource.PierceThrough;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingDamageEvent.class, remap = false)
public class LivingDamageEventMixin {
    @Inject(at = @At("HEAD"), method = "setAmount", cancellable = true)
    private void changeAmount(float amount, CallbackInfo ci) {
        LivingDamageEvent event = (LivingDamageEvent) (Object) this;
        if (event.getSource() instanceof Environmental source) {
            if (amount < source.AMOUNT) {
                ci.cancel();
            }
        } else if (event.getSource() instanceof PierceThrough source) {
            if (amount < source.AMOUNT) {
                ci.cancel();
            }
        }
    }
}
