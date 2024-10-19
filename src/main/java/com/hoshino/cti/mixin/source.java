package com.hoshino.cti.mixin;

import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Event.class)
public abstract class source {
    @Inject(at = @At(value = "HEAD"),method = "setCanceled")
    private void setCanceled(boolean cancel, CallbackInfo ci){
    }
}
