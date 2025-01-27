package com.hoshino.cti.Modifier;

import com.c2h6s.etshtinker.Modifiers.modifiers.etshmodifieriii;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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

public class IonizeIndused extends etshmodifieriii {
    @Override
    public int getPriority() {
        return 255;
    }

    @Override
    public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        Entity entity =context.getTarget();
        LivingEntity living =context.getAttacker();
        if (entity instanceof LivingEntity target &&living instanceof Player player&&!(target instanceof Player)){
            target.invulnerableTime=0;
            target.hurt(playerIonizedSource(damage/16,player),damage/16);
            if (getElectricResistance(target)<=1.5&&getIonizedValue(target)<1000){
                addIonizedValue(target,25*modifier.getLevel());
            }
            target.invulnerableTime=0;
        }
        return knockback;
    }
    @Override
    public boolean modifierOnProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target!=null&&projectile instanceof AbstractArrow arrow&&attacker instanceof Player player&&!(target instanceof Player)){
            target.invulnerableTime=0;
            target.hurt(playerIonizedSource((float) (arrow.getBaseDamage()*getMold(arrow.getDeltaMovement()))/16,player),(float) (arrow.getBaseDamage()*getMold(arrow.getDeltaMovement()))/16);
            if (getElectricResistance(target)<=1.5&&getIonizedValue(target)<1000){
                addIonizedValue(target,25*modifier.getLevel());
            }
            target.invulnerableTime=0;
        }
        return false;
    }
}
