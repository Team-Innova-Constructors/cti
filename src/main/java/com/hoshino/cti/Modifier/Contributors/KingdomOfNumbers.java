package com.hoshino.cti.Modifier.Contributors;


import com.hoshino.cti.cti;
import com.hoshino.cti.register.ctiEffects;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import static com.hoshino.cti.register.ctiModifiers.kingdomofnumbers;

public class KingdomOfNumbers extends BattleModifier {
    public KingdomOfNumbers() {
        MinecraftForge.EVENT_BUS.addListener(this::livinghurtevent);
    }

    private static final ResourceLocation numericalperceptiontime = cti.getResource("numericalperceptiontime");

    public void livinghurtevent(LivingHurtEvent event) {
        Entity a = event.getSource().getEntity();
        if (a instanceof ServerPlayer player && event.getEntity() != null) {
            ToolStack tool = ToolStack.from(player.getMainHandItem());
            int c = ModifierUtil.getModifierLevel(player.getItemBySlot(EquipmentSlot.MAINHAND), kingdomofnumbers.getId());
            if (c > 0) {
                int b = (int) event.getAmount();
                event.setAmount(b);
                if (b % 2 != 0&&tool.getPersistentData().getInt(numericalperceptiontime)==0) {
                    tool.getPersistentData().putInt(numericalperceptiontime, 300 - c * 10);
                    player.addEffect(new MobEffectInstance(ctiEffects.numerical_perception.get(), 200, c));
                }
            }
        }
    }

    @Override
    public void onInventoryTick(IToolStackView iToolStackView, ModifierEntry modifierEntry, Level level, LivingEntity entity, int index, boolean b, boolean b1, ItemStack itemStack) {
        if (entity instanceof ServerPlayer player) {
            ToolStack tool = ToolStack.from(player.getMainHandItem());
            if (tool.getPersistentData().getInt(numericalperceptiontime)>0){
                tool.getPersistentData().putInt(numericalperceptiontime,tool.getPersistentData().getInt(numericalperceptiontime)-1);
            }
        }
    }
}