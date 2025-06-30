package com.hoshino.cti.mixin.AetherMixin;

import com.aetherteam.aether.item.EquipmentUtil;
import com.hoshino.cti.register.CtiModifiers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(value = EquipmentUtil.class,remap = false)
public class EquipmentUtilMixin {
    @Inject(method = "hasFullPhoenixSet",at = @At("HEAD"),cancellable = true)
    private static void tinkerPhoenixSupport(LivingEntity entity, CallbackInfoReturnable<Boolean> cir){
        if (entity instanceof Player player) {
            for (ItemStack stack : player.getInventory().armor){
                if (stack.getItem() instanceof IModifiable){
                    ToolStack toolStack = ToolStack.from(stack);
                    if (toolStack.getModifierLevel(CtiModifiers.PHOENIX.get())>0) cir.setReturnValue(true);
                    return;
                }
            }
        }
    }
}
