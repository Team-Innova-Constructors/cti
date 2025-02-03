package com.hoshino.cti.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "getAttackStrengthScale",at = @At(value = "RETURN"), cancellable = true)
    public void changeScale(float p_36404_, CallbackInfoReturnable<Float> cir) {
        Player player =(Player) (Object) this;
        float delay = player.getCurrentItemAttackStrengthDelay();
        if (player.level.getDifficulty()== Difficulty.HARD) {
            delay*=1.25f;
        }
        if (delay<10){
            if (player.level.getDifficulty()== Difficulty.HARD) {
                cir.setReturnValue(Mth.clamp(((float) ((LivingEntityAccessor) player).getAttackStrengthTicker() + p_36404_) / (delay * 0.6f + 4f), 0.0F, 1.0F));
            }
            if (player.level.getDifficulty()== Difficulty.NORMAL) {
                cir.setReturnValue(Mth.clamp(((float) ((LivingEntityAccessor) player).getAttackStrengthTicker() + p_36404_) / (delay * 0.7f + 3f), 0.0F, 1.0F));
            }
            if (player.level.getDifficulty()== Difficulty.EASY) {
                cir.setReturnValue(Mth.clamp(((float) ((LivingEntityAccessor) player).getAttackStrengthTicker() + p_36404_) / (delay * 0.8f + 2f), 0.0F, 1.0F));
            }
        }
    }
}
