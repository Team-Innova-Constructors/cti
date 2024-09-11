package com.hoshino.cti.client.hud;

import com.hoshino.cti.cti;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.List;

public class EnvironmentalHud {
    public static Font font = Minecraft.getInstance().font;
    public static final List<ChatFormatting> COLOR = List.of(
            ChatFormatting.GREEN,
            ChatFormatting.GOLD,
            ChatFormatting.GOLD,
            ChatFormatting.RED,
            ChatFormatting.DARK_RED,
            ChatFormatting.DARK_RED
    );
    public static final String Str = "cti.gui.environmental.";
    public static final ResourceLocation LV_0 = new ResourceLocation(cti.MOD_ID,"/textures/gui/environmental/lv0.png");
    public static final ResourceLocation LV_1 = new ResourceLocation(cti.MOD_ID,"/textures/gui/environmental/lv1.png");
    public static final ResourceLocation LV_2 = new ResourceLocation(cti.MOD_ID,"/textures/gui/environmental/lv2.png");
    public static final ResourceLocation LV_3 = new ResourceLocation(cti.MOD_ID,"/textures/gui/environmental/lv3.png");
    public static final ResourceLocation LV_4 = new ResourceLocation(cti.MOD_ID,"/textures/gui/environmental/lv4.png");
    public static final ResourceLocation LV_5 = new ResourceLocation(cti.MOD_ID,"/textures/gui/environmental/lv5.png");
    public static final List<ResourceLocation> LVL =List.of(LV_0, LV_1, LV_2, LV_3, LV_4,LV_5);

    public static final ResourceLocation IONIZE = new ResourceLocation(cti.MOD_ID,"/textures/gui/environmental/gui_ionize.png");
    public static final ResourceLocation SCORCH = new ResourceLocation(cti.MOD_ID,"/textures/gui/environmental/gui_scorch.png");
    public static final ResourceLocation FROZEN = new ResourceLocation(cti.MOD_ID,"/textures/gui/environmental/gui_frozen.png");


    public static final IGuiOverlay ENVIRONMENT_OVERLAY = ((gui, poseStack, partialTick, width, height) -> {
        int x =width/2;


        float ion_val =EnvironmentalPlayerData.getIonizeValue();
        double ion_build =EnvironmentalPlayerData.getIonizeBuild();
        int ion_lvl =(int) Mth.clamp(ion_build,0.0d,5.0d) ;
        int player_ion_lvl =ion_val<=0? 0:(int) Mth.clamp(ion_val/50,0,4)+1;

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0,IONIZE);
        GuiComponent.blit(poseStack,x+164, height -64,0,0,16,16,16,16);
        RenderSystem.setShaderTexture(0,LVL.get(ion_build<0?0:ion_lvl+1));
        GuiComponent.blit(poseStack,x+164, height -64,0,0,16,16,16,16);
        GuiComponent.drawString(poseStack, font,
                Component.translatable(Str+"ionize_value").append(": ")
                .append(String.format("%.01f",ion_val))
                .append("%").withStyle(COLOR.get(player_ion_lvl))
                ,x+180,height -64,255);
        GuiComponent.drawString(poseStack, font,
                Component.translatable(Str+"ionize_build").append(": ")
                        .append(String.format("%.01f",ion_build*2))
                        .append("/s").withStyle(COLOR.get(ion_build<=0?0:ion_lvl+1))
                ,x+180,height -56,255);


        float sco_val =EnvironmentalPlayerData.getScorchValue();
        double sco_build =EnvironmentalPlayerData.getScorchBuild();
        int sco_lvl =(int) Mth.clamp(sco_build,0.0d,5.0d) ;
        int player_sco_lvl =sco_val<=0? 0:(int) Mth.clamp(sco_val/50,0,4)+1;

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0,SCORCH);
        GuiComponent.blit(poseStack,x+164, height -46,0,0,16,16,16,16);
        RenderSystem.setShaderTexture(0,LVL.get(sco_build<=0?0:sco_lvl +1));
        GuiComponent.blit(poseStack,x+164, height -46,0,0,16,16,16,16);
        GuiComponent.drawString(poseStack, font,
                Component.translatable(Str+"scorch_value").append(": ")
                        .append(String.format("%.01f",sco_val))
                        .append("%").withStyle(COLOR.get(player_sco_lvl))
                ,x+180,height -46,255);
        GuiComponent.drawString(poseStack, font,
                Component.translatable(Str+"scorch_build").append(": ")
                        .append(String.format("%.01f", sco_build *2))
                        .append("/s").withStyle(COLOR.get(sco_build<=0?0:sco_lvl +1))
                ,x+180,height -38,255);


        float fzn_val =EnvironmentalPlayerData.getFrozenValue();
        double fzn_build =EnvironmentalPlayerData.getFrozenBuild();
        int fzn_lvl =(int) Mth.clamp(fzn_build,0.0d,5.0d) ;
        int player_fzn_lvl =fzn_val<=0? 0:(int) Mth.clamp(fzn_val /50,0,4)+1;

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0,FROZEN);
        GuiComponent.blit(poseStack,x+164, height -28,0,0,16,16,16,16);
        RenderSystem.setShaderTexture(0,LVL.get(fzn_build<=0?0:fzn_lvl+1));
        GuiComponent.blit(poseStack,x+164, height -28,0,0,16,16,16,16);
        GuiComponent.drawString(poseStack, font,
                Component.translatable(Str+"frozen_value").append(": ")
                        .append(String.format("%.01f", fzn_val))
                        .append("%").withStyle(COLOR.get(player_fzn_lvl))
                ,x+180,height -28,255);
        GuiComponent.drawString(poseStack, font,
                Component.translatable(Str+"frozen_build").append(": ")
                        .append(String.format("%.01f", fzn_build *2))
                        .append("/s").withStyle(COLOR.get(fzn_build<=0?0:fzn_lvl+1))
                ,x+180,height -20,255);

    });

}
