package com.hoshino.cti.Modifier.Replace;

import com.marth7th.solidarytinker.extend.superclass.ArmorModifier;
import com.marth7th.solidarytinker.util.method.ModifierLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class Reflect extends ArmorModifier {
    @Override
    public void LivingHurt(LivingHurtEvent event, LivingEntity entity, Player player) {
        if(player!=null&&event.getSource().getEntity() instanceof LivingEntity enemy){
            if(ModifierLevel.EquipHasModifierlevel(player,this.getId())){
                int level=ModifierLevel.getTotalArmorModifierlevel(player,this.getId())+ModifierLevel.getEachHandsTotalModifierlevel(player,this.getId());
                enemy.hurt(DamageSource.thorns(player),event.getAmount() * 0.25F *level);
            }
        }
    }
}
