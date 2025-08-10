package com.hoshino.cti.Event;

import com.hoshino.cti.client.CtiKeyBinding;
import com.hoshino.cti.netwrok.CtiPacketHandler;
import com.hoshino.cti.netwrok.packet.StarHitPacket;
import com.hoshino.cti.util.Vec3Helper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.hoshino.cti.Cti.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
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
    @SubscribeEvent
    public static void onKeyPressed(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.level.isClientSide()) {
                if (CtiKeyBinding.STAR_HIT.consumeClick()) {
                    var mob= Vec3Helper.getPointedEntity(player,player.getLevel(),50, Mob.class, Mob::isAlive, Mob -> false);
                    if (mob != null) {
                        CtiPacketHandler.sendToServer(new StarHitPacket(mob.getUUID()));
                    }
                }
            }
        }
    }
}
