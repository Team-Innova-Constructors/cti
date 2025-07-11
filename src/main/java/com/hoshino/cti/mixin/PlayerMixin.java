package com.hoshino.cti.mixin;

import com.c2h6s.etshtinker.init.EtshtinkerModifiers;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "getAttackStrengthScale", at = @At(value = "RETURN"), cancellable = true)
    public void changeScale(float p_36404_, CallbackInfoReturnable<Float> cir) {
        Player player = (Player) (Object) this;
        float delay = player.getCurrentItemAttackStrengthDelay();
        if (delay < 10) {
            cir.setReturnValue(Mth.clamp(((float) ((LivingEntityAccessor) player).getAttackStrengthTicker() + p_36404_) / (delay * 0.7f + 3f), 0.0F, 1.0F));
        }
    }
}
