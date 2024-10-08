package com.hoshino.cti.mixin.AdAstraMixin;

import com.hoshino.cti.register.ctiModifiers;
import com.hoshino.cti.util.BiomeUtil;
import com.hoshino.cti.util.ctiTagkey;
import earth.terrarium.ad_astra.common.util.ModUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;

import static com.c2h6s.etshtinker.etshtinker.EtSHrnd;

@Mixin(value = ModUtils.class, remap = false)
public abstract class ModUtilMixin {
    @Inject(method = "getEntityGravity", at = @At("TAIL"), cancellable = true)
    private static void getEntityGravity(Entity entity, CallbackInfoReturnable<Float> callbackInfo)
    {
        float gravity = callbackInfo.getReturnValueF();

        if (gravity != 1.0F)
        {
            if (entity instanceof LivingEntity living){
                if (BiomeUtil.getBiomeKey(living.level.getBiome(living.blockPosition()))==BiomeUtil.DISORDERED_ZONE){
                    callbackInfo.setReturnValue(EtSHrnd().nextFloat()+0.5f);
                }
                for (ItemStack stack: List.of(living.getItemBySlot(EquipmentSlot.HEAD),living.getItemBySlot(EquipmentSlot.CHEST),living.getItemBySlot(EquipmentSlot.FEET),living.getItemBySlot(EquipmentSlot.LEGS))){
                    if (stack.getItem() instanceof IModifiable && ToolStack.from(stack).getModifierLevel(ctiModifiers.gravity_normalizer.get())>0){
                        callbackInfo.setReturnValue(1f);
                    }
                }
            }
        }

    }

    @Inject(method = "armourIsOxygenated", at = @At("TAIL"), cancellable = true)
    private static void armourIsOxygenated(LivingEntity entity, CallbackInfoReturnable<Boolean> callbackInfo)
    {
        boolean oxygenated = callbackInfo.getReturnValueZ();
        if (!oxygenated)
        {
            ItemStack stack =entity.getItemBySlot(EquipmentSlot.HEAD);
            if (stack.getItem() instanceof IModifiable iModifiable){
                ToolStack tool =ToolStack.from(stack);
                if (tool.getModifierLevel(ctiModifiers.space_suit.get())>0){
                    callbackInfo.setReturnValue(true);
                }
            }else if (stack.getTags().toList().contains(ctiTagkey.OXYGEN_REGEN)){
                callbackInfo.setReturnValue(true);
            }
        }

    }
}
