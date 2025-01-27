package com.hoshino.cti.mixin.L2;

import dev.xkmc.l2hostility.content.traits.legendary.DementorTrait;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DementorTrait.class)
public class DementorMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraftforge/event/entity/living/LivingAttackEvent;setCanceled(Z)V"),method = "onAttackedByOthers",remap = false, cancellable = true)
    private void OnAttack(int level, LivingEntity entity, LivingAttackEvent event, CallbackInfo ci){
        if(!event.getSource().isBypassArmor()&&!event.getSource().isMagic()&&!event.getSource().isBypassInvul()&&event.getSource().getEntity()!=null){
            ci.cancel();
        }
    }
}
