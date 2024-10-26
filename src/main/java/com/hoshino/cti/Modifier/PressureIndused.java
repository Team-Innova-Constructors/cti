package com.hoshino.cti.Modifier;

import com.c2h6s.etshtinker.Modifiers.modifiers.etshmodifieriii;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import static com.c2h6s.etshtinker.util.vecCalc.getMold;
import static com.hoshino.cti.Entity.Systems.EnvironmentSystem.*;
import static com.hoshino.cti.Entity.specialDamageSource.Environmental.playerIonizedSource;
import static com.hoshino.cti.Entity.specialDamageSource.Environmental.playerPressureSource;

public class PressureIndused extends etshmodifieriii {
    @Override
    public void modifierAfterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        Entity entity =context.getTarget();
        if (entity instanceof LivingEntity target ){
            target.hurt(playerPressureSource(damageDealt/2,target),damageDealt/2);
            if (getPressureResistance(target)<=1.5&&getPressureValue(target)<25){
                addPressureValue(target,5*modifier.getLevel());
            }
        }
    }
    @Override
    public boolean modifierOnProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target!=null&&projectile instanceof AbstractArrow arrow){
            target.invulnerableTime=0;
            target.hurt(playerPressureSource((float) (arrow.getBaseDamage()*getMold(arrow.getDeltaMovement())/2),target),(float) (arrow.getBaseDamage()*getMold(arrow.getDeltaMovement())/2));
            if (getPressureResistance(target)<=1.5){
                addPressureValue(target,50*modifier.getLevel());
            }
            target.invulnerableTime=0;
        }
        return false;
    }
}
