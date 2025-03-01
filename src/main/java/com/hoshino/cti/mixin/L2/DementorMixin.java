package com.hoshino.cti.mixin.L2;

import dev.xkmc.l2hostility.content.traits.legendary.DementorTrait;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DementorTrait.class,remap = false)
public class DementorMixin {
    /**
     * @author FireFly
     * @reason 摄魂判定问题
     */
    @Overwrite
    public void onAttackedByOthers(int level, LivingEntity entity, LivingAttackEvent event){
        if(!event.getSource().isMagic()|| !(event.getAmount() >=entity.getMaxHealth()* 0.2F)){
            event.setCanceled(true);
        }
    }
}
