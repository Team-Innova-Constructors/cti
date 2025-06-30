package com.hoshino.cti.mixin.Eni;

import com.aizistral.enigmaticlegacy.api.capabilities.IPlaytimeCounter;
import com.aizistral.enigmaticlegacy.items.CursedRing;
import com.hoshino.cti.register.CtiModifiers;
import com.hoshino.cti.util.method.GetModifierLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.SlotContext;

@Mixin(value = CursedRing.class,remap = false)
public class CurseRingMixin {
    /**
     * @reason <h5>前期压力大并且激怒后还会有残留效果,现在在白天和携带七咒的游戏日前4天不会再激怒末影人</h5>
     * @author firefly
     */
    @Inject(method = "curioTick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/EnderMan;m_6710_(Lnet/minecraft/world/entity/LivingEntity;)V"),cancellable = true)
    private void angryEnderMan(SlotContext context, ItemStack stack, CallbackInfo ci){
        if(!(context.entity() instanceof Player player))return;
        boolean shouldNotBeAngry=player.getLevel().isDay()|| GetModifierLevel.EquipHasModifierlevel(player, CtiModifiers.end_slayer.getId())||this.cti$isInfancy(player);
        if(shouldNotBeAngry){
            ci.cancel();
        }
    }
    @Inject(method = "curioTick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/NeutralMob;m_6710_(Lnet/minecraft/world/entity/LivingEntity;)V"),cancellable = true)
    private void angryOtherMob(SlotContext context, ItemStack stack, CallbackInfo ci){
        if(!(context.entity() instanceof Player player))return;
        boolean shouldNotBeAngry=player.level.isDay()||this.cti$isInfancy(player);
        if(shouldNotBeAngry) {
            ci.cancel();
        }
    }
    @Unique
    private boolean cti$isInfancy(Player player){
        IPlaytimeCounter counter = IPlaytimeCounter.get(player);
        return counter.getTimeWithCurses()<96000;
    }
}
