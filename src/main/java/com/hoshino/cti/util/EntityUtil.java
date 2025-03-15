package com.hoshino.cti.util;

import com.hoshino.cti.register.ctiModifiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class EntityUtil {
    public static boolean isAntiStun(LivingEntity living) {
        ItemStack stack = living.getItemBySlot(EquipmentSlot.HEAD);
        if (stack.getItem() instanceof IModifiable) {
            return ToolStack.from(stack).getModifierLevel(ctiModifiers.anti_stun_goggles.get()) > 0;
        }
        return false;
    }
}
