package com.hoshino.cti.Items;

import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

public class TestTool extends Item {
    public TestTool(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 5;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if(entity instanceof Player player&&level.isClientSide()){
            var Item=player.getOffhandItem().getItem();
            if(Item instanceof BucketItem bucketItem){
                var fluid=bucketItem.getFluid();
                var tag=fluid.defaultFluidState().getTags();
                var list=tag.toList();
                player.sendSystemMessage(Component.literal("该桶内流体ID"));
                player.sendSystemMessage(runCopy(Component.literal(fluid.getFluidType().getDescriptionId())));
                player.sendSystemMessage(Component.literal("该桶内有以下流体tag"));
                for(TagKey<Fluid> fluidTagKey:list){
                    String string=fluidTagKey.location().toString();
                    var component=runCopy(Component.literal(string));
                    player.sendSystemMessage(component);
                }
            }
        }
        return stack;
    }
    private Component runCopy(Component component){
        return Component.literal("-")
                .withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD,"\""+component.getString()+"\"")))
                .withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,Component.literal("点了就能复制到粘贴板喵"))))
                .withStyle(style -> style.withColor(0xffc4ff))
                .append(component.getString());
    }
}
