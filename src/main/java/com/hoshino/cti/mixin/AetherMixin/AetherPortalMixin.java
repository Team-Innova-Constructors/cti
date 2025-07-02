package com.hoshino.cti.mixin.AetherMixin;

import com.aetherteam.aether.block.portal.AetherPortalBlock;
import com.hoshino.cti.util.AdvanceMentHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twilightforest.TwilightForestMod;

@Mixin(value = AetherPortalBlock.class)
public class AetherPortalMixin {
    @Inject(method = "handleTeleportation", at = @At("HEAD"), cancellable = true,remap = false)
    private void preventPlayerTeleport(Entity entity, CallbackInfo ci) {
        if (!(entity instanceof ServerPlayer serverPlayer)) return;
        if (!AdvanceMentHelper.hasCompletedAdvancement(serverPlayer, new ResourceLocation(TwilightForestMod.ID, "progress_naga"))) {
            serverPlayer.displayClientMessage(Component.literal("你现在还没完成暮色,天镜的怪对你来说太过于凶猛了,尝试提升一下自己吧"), true);
            ci.cancel();
        }
    }
}
