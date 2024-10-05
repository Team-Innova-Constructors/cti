package com.hoshino.cti.recipe;

import com.google.gson.JsonObject;
import com.hoshino.cti.cti;
import committee.nova.mods.avaritia.init.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ReactorNeutronCollectorRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final ItemStack catalyst;
    private final float consumption_rate;
    private final float efficiency;

    public ReactorNeutronCollectorRecipe(ResourceLocation id, ItemStack output, float efficiency,float consumption,ItemStack catalyst){
        this.id = id;
        this.output = output;
        this.efficiency =efficiency;
        this.consumption_rate=consumption;
        this.catalyst =catalyst;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return !pLevel.isClientSide();
    }

    public float getEfficiency(){
        return efficiency;
    }
    public float getConsumptionRate(){
        return consumption_rate;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    public ItemStack getCatalyst() {
        return catalyst.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ReactorNeutronCollectorRecipe>{
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = "reactor_neutron_collect";
    }


    public static class Serializer implements RecipeSerializer<ReactorNeutronCollectorRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final  ResourceLocation ID =
                new ResourceLocation(cti.MOD_ID,"reactor_neutron_collect");
        @Override
        public ReactorNeutronCollectorRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = new ItemStack(ModItems.neutron_pile.get());
            float efficiency = GsonHelper.getAsFloat(pSerializedRecipe,"efficiency");
            float consumption = GsonHelper.getAsFloat(pSerializedRecipe,"consumption_rate");
            ItemStack catalyst = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe,"catalyst"));
            return new ReactorNeutronCollectorRecipe(pRecipeId,output,efficiency,consumption,catalyst);
        }
        @Override
        public @Nullable ReactorNeutronCollectorRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            ItemStack output = new ItemStack(ModItems.neutron_pile.get());
            CompoundTag tag = pBuffer.readNbt();
            float efficiency =0;
            float consumption_rate =0;
            if (tag!=null){
                efficiency =tag.getFloat("efficiency");
                consumption_rate =tag.getFloat("consumption_rate");
            }
            ItemStack catalyst = pBuffer.readItem();
            return new ReactorNeutronCollectorRecipe(pRecipeId,output,efficiency,consumption_rate,catalyst);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, ReactorNeutronCollectorRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());
            CompoundTag tag =new CompoundTag();
            tag.putFloat("efficiency",pRecipe.efficiency);
            tag.putFloat("consumption_rate",pRecipe.consumption_rate);
            pBuffer.writeNbt(tag);
            pBuffer.writeItemStack(pRecipe.getCatalyst(),false);
        }
    }
}
