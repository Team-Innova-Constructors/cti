package com.hoshino.cti.Modifier;

import com.hoshino.cti.util.method.GetModifierLevel;
import com.marth7th.solidarytinker.extend.superclass.ArmorModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.capability.fluid.ToolTankHelper;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class OxygeliumBudHelmet extends ArmorModifier {
    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity entity, int index, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if(!isCorrectSlot||!(entity instanceof Player player))return;
        var tank=ToolTankHelper.TANK_HELPER.getFluid(tool);
        int amount=tank.getAmount();
        if(tank.getFluid()== Fluids.WATER&&player.tickCount%10==0&&amount>10&&player.getAirSupply()<player.getMaxAirSupply()){
            tank.setAmount(tank.getAmount()-10);
            ToolTankHelper.TANK_HELPER.setFluid(tool,tank);
            player.setAirSupply(player.getMaxAirSupply());
        }
    }

    @Override
    public void LivingHurtEvent(LivingHurtEvent event) {
        var entity=event.getEntity();
        if(!(entity instanceof Player player))return;
        if(event.getSource().getEntity()!=null&& GetModifierLevel.EquipHasModifierlevel(player,this.getId())){
            event.setAmount(event.getAmount() * 0.72f);
        }
    }
}
