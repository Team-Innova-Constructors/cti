package com.hoshino.cti.mixin;

import com.hoshino.cti.util.EntityUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.item.IModifiable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(at = @At(value = "HEAD"),method = "bobHurt",cancellable = true)
    public void cancleShaking(PoseStack p_109118_, float p_109119_, CallbackInfo ci){
        Entity entity = ((GameRenderer) (Object) this).getMinecraft().getCameraEntity();
        if (entity instanceof LivingEntity living&& EntityUtil.isAntiStun(living)){
            ci.cancel();
        }
    }
}
