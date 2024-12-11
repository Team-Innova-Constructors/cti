package com.hoshino.cti.Items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TooltipedItem extends Item{
    public final List<Component> tooltip;
    public TooltipedItem(Item.Properties properties, @NotNull List<Component> tooltip) {
        super(properties);
        this.tooltip =tooltip;
    }

    @Override
    public void appendHoverText(ItemStack p_40572_, @Nullable Level p_40573_, List<net.minecraft.network.chat.Component> list, TooltipFlag p_40575_) {
        list.addAll(this.tooltip);
        super.appendHoverText(p_40572_, p_40573_, list, p_40575_);
    }
}
