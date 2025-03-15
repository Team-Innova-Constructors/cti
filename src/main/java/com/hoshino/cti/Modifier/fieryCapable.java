package com.hoshino.cti.Modifier;

import com.c2h6s.etshtinker.Modifiers.modifiers.etshmodifieriii;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static com.c2h6s.etshtinker.etshtinker.EtSHrnd;

public class fieryCapable extends etshmodifieriii {
    @Override
    public boolean isNoLevels() {
        return true;
    }

    @Override
    public void modifierOnInventoryTick(IToolStackView tool, ModifierEntry modifier, Level level, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack itemStack) {
        if (isCorrectSlot && holder != null) {
            holder.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10, 0, false, false));
            if (holder.isInLava() && tool.getDamage() > 0 && EtSHrnd().nextInt(3) == 1) {
                tool.setDamage(tool.getDamage() - 1);
            }
        }
    }
}
