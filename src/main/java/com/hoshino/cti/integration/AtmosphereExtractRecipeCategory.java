package com.hoshino.cti.integration;

import com.hoshino.cti.Plugin.JEIPlugin;
import com.hoshino.cti.cti;
import com.hoshino.cti.recipe.AtmosphereExtractorRecipe;
import com.hoshino.cti.register.ctiItem;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;

import static mezz.jei.library.ingredients.IngredientInfoRecipe.recipeWidth;

public class AtmosphereExtractRecipeCategory implements IRecipeCategory<AtmosphereExtractorRecipe> {
    // 区分合成分类的ID
    public static final ResourceLocation UID = new ResourceLocation(cti.MOD_ID,
            "atmosphere_extract");
    // png file texture
    public static final ResourceLocation TEXTURE = new ResourceLocation(cti.MOD_ID,
            "textures/gui/machine/atmosphere_extractor_bg.png");

    // 合成分类的背景图片
    private final IDrawable background;
    // 合成分类的图标
    private final IDrawable icon;

    // 构造方法
    public AtmosphereExtractRecipeCategory(IGuiHelper helper) {
        // 渲染背景图片。图片的开始位置和图片的结束的位置 u,v,width,height
        this.background = helper.createDrawable(TEXTURE, 42, 17, 100, 48);
        // 图标
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ctiItem.atmosphere_extractor.get()));

    }

    // 返回JEITutorialModPlugin定义的type
    @Override
    public RecipeType<AtmosphereExtractorRecipe> getRecipeType() {
        return JEIPlugin.ATMOSPHERE_EXTRACT;
    }

    // 合成界面的标题是什么
    @Override
    public Component getTitle() {
        return Component.literal("大气提取");
    }

    //
    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    // 添加合成表的输入slot和输出的slot
    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AtmosphereExtractorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.OUTPUT, 74, 18).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(AtmosphereExtractorRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        String biomes = recipe.getBiome();
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(biomes));
        Component biomeC = Component.literal("群系:").withStyle(ChatFormatting.WHITE).append(Component.translatable("biome." + key.location().toLanguageKey()).withStyle(ChatFormatting.LIGHT_PURPLE));
        Minecraft.getInstance().font.draw(stack, biomeC, recipeWidth / 2 - Minecraft.getInstance().font.width(biomeC), (Minecraft.getInstance().font.lineHeight + 2) * 2 - 20, 0);
        IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
    }
}
