package com.hoshino.cti.Screen;

import com.hoshino.cti.Screen.menu.AtmosphereExtractorMenu;
import com.hoshino.cti.util.BiomeUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static com.hoshino.cti.util.BiomeUtil.getBiomeKey;

public class AtmosphereExtractorScreen extends AbstractContainerScreen<AtmosphereExtractorMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft:textures/gui/container/furnace.png");
    public static Font font = Minecraft.getInstance().font;
    public AtmosphereExtractorScreen(AtmosphereExtractorMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;

        this.blit(poseStack,x,y,0,0,imageWidth,imageHeight);

        drawProgress(poseStack,x,y);
    }

    protected void drawProgress(PoseStack poseStack, int x, int y) {
        if (menu.isCrafting()){
            int $$4 = this.leftPos;
            int $$5 = this.topPos;
            int $$7 = menu.getProgressScale();
            blit(poseStack, $$4 + 79, $$5 + 34, 176, 14, $$7 + 1, 16);
        }
    }

    @Override
    public void render(PoseStack poseStack, int mousex, int mousey, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mousex, mousey, delta);
        renderTooltip(poseStack,mousex,mousey);
        if (menu.entity.getLevel()!=null) {
            drawString(poseStack, font,Component.translatable( BiomeUtil.BiomekeyToString(getBiomeKey(menu.entity.getLevel().getBiome(menu.entity.getBlockPos())))), 24, mousey+8, 0x00B0FF);
            drawString(poseStack, font, String.valueOf(menu.entity.PROGRESS)+"%", mousex, mousey+16, 0x00B0FF);
        }
    }
}
