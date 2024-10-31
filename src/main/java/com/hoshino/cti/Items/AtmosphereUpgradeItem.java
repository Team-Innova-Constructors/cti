package com.hoshino.cti.Items;

import com.hoshino.cti.register.ctiTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

import java.util.List;

public class AtmosphereUpgradeItem extends TooltipedItem {
    public final float SPEED_FACTOR;
    public final float POWER_FACTOR;
    public AtmosphereUpgradeItem(float speedFactor,float powerFactor) {
        super(new Item.Properties().tab(ctiTab.MIXC).stacksTo(1), List.of(Component.translatable("cti.tooltip.item.atmosphere_upgrade").withStyle(ChatFormatting.GREEN),Component.translatable("cti.tooltip.item.speed_factor").append(" + "+String.format("%.1f",speedFactor)+" x").withStyle(ChatFormatting.AQUA),Component.translatable("cti.tooltip.item.power_factor").append(" * "+String.format("%.1f",powerFactor)+" x").withStyle(ChatFormatting.RED)));
        this.SPEED_FACTOR =speedFactor;
        this.POWER_FACTOR =powerFactor;
    }
}

