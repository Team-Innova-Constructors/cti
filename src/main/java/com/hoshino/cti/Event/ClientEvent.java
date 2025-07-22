package com.hoshino.cti.Event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import dev.xkmc.l2complements.init.registrate.LCEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.hoshino.cti.Cti.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvent {
//    @SubscribeEvent
//    public static void onRenderLivingPost(RenderLivingEvent.Post<LivingEntity, ? extends EntityModel<LivingEntity>> event) {
//        LivingEntity entity = event.getEntity();
//        PoseStack poseStack = event.getPoseStack();
//        MultiBufferSource bufferSource = event.getMultiBufferSource();
//        if (Minecraft.getInstance().player != null && entity instanceof Mob mob) {
//            poseStack.pushPose();
//            float heightOffset = entity.getBbHeight() + 3F;
//            poseStack.translate(0F, heightOffset, 0F);
//            poseStack.mulPose(Vector3f.YP.rotationDegrees(-entity.getYRot()));
//            poseStack.mulPose(Vector3f.XP.rotationDegrees(90F));
//            poseStack.mulPose(Vector3f.YP.rotationDegrees(225F));
//            poseStack.mulPose(Vector3f.XP.rotationDegrees(-90F));
//            poseStack.scale(2.5F, 2.5F, 2.5F);
//            ItemStack ironSword = new ItemStack(Items.IRON_SWORD);
//            Minecraft.getInstance().getItemRenderer().renderStatic(ironSword, ItemTransforms.TransformType.NONE, event.getPackedLight(), OverlayTexture.NO_OVERLAY, poseStack, bufferSource, entity.getId());
//            poseStack.popPose();
//        }
//    }
}
