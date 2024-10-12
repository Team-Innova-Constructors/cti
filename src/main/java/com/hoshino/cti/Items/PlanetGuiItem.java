package com.hoshino.cti.Items;

import earth.terrarium.ad_astra.common.screen.PlanetSelectionMenuProvider;
import earth.terrarium.botarium.api.menu.MenuHooks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static com.hoshino.cti.Entity.Systems.EnvironmentSystem.*;

import java.util.List;

public class PlanetGuiItem extends Item {
    public int lvl =1;
    public PlanetGuiItem(Properties p_41383_,int lvl) {
        super(p_41383_);
        this.lvl = lvl;
    }
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }
    public ItemStack finishUsingItem(ItemStack p_41409_, Level level, LivingEntity living) {
        if (living instanceof ServerPlayer player){
            if (!player.isShiftKeyDown()) {
                player.inventoryMenu.removed(player);
                MenuHooks.openMenu(player, new PlanetSelectionMenuProvider(this.lvl));
            }
            else{
                player.sendSystemMessage(Component.translatable("cti.message.environmental.freeze").append(" "+String.format("%.2f",getFreezeResistance(player))));
                player.sendSystemMessage(Component.translatable("cti.message.environmental.scorch").append(" "+String.format("%.2f",getScorchResistance(player))));
                player.sendSystemMessage(Component.translatable("cti.message.environmental.ionize").append(" "+String.format("%.2f",getElectricResistance(player))));
                player.sendSystemMessage(Component.translatable("cti.message.environmental.pressure").append(" "+String.format("%.2f",getPressureResistance(player))));
            }
        }
        return p_41409_;
    }
    public int getUseDuration(ItemStack p_41454_) {
        return 1;
    }
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BLOCK;
    }
    @Override
    public void appendHoverText(ItemStack p_40572_, @Nullable Level p_40573_, List<Component> list, TooltipFlag p_40575_) {
        list.add(Component.translatable("cti.tooltip.item.stellar_plate").withStyle(ChatFormatting.AQUA));
        super.appendHoverText(p_40572_, p_40573_, list, p_40575_);
    }
}
