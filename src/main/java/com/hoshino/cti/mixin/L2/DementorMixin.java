package com.hoshino.cti.mixin.L2;

import dev.xkmc.l2hostility.content.traits.legendary.DementorTrait;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DementorTrait.class)
public class DementorMixin {
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraftforge/event/entity/living/LivingAttackEvent;setCanceled(Z)V"),method = "onAttackedByOthers",remap = false)
    private void OnAttack(LivingAttackEvent event, boolean b){
        if(!event.getSource().isBypassArmor()&&!event.getSource().isMagic()&&!event.getSource().isBypassInvul()&&event.getSource().getEntity()!=null){
            event.setCanceled(true);
        }
    }
}
