package com.hoshino.cti.Modifier.Contributors;


import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import tcintegrations.util.BotaniaHelper;
import vazkii.botania.api.mana.ManaItemHandler;

public class ArcaneTough extends BattleModifier {
    @Override
    public boolean havenolevel() {
        return true;
    }
    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity entity, int index, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if(entity instanceof Player player){
            if(tool.getDamage()>0){
                ManaItemHandler.instance().requestManaForTool(tool.getItem().getDefaultInstance(),player, BotaniaHelper.getManaPerDamageBonus(player, 60),true);
                tool.setDamage(tool.getDamage()-10);
                player.giveExperiencePoints(1000);
            }
             player.giveExperiencePoints(5);
            if(player.tickCount%100==0){
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,120,4,true,true));
            }
        }
    }

    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity entity) {
        return 0;
    }
}
