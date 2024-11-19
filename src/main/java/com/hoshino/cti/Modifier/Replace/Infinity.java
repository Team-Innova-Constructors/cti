package com.hoshino.cti.Modifier.Replace;

import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

public class Infinity extends BattleModifier {
    @Override
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if(livingTarget instanceof Mob mob&&attacker instanceof Player player){
            if(level>3){
                livingTarget.die(DamageSource.playerAttack(player));
                livingTarget.kill();
                return damage + Integer.MAX_VALUE * level;
            }
            else {
                mob.setHealth((mob.getHealth() - mob.getMaxHealth() * 0.33f*level));
                return damage+131072*level;
            }
        }
        return damage;
    }

    @Override
    public void arrowhurt(ModifierNBT modifiers, NamespacedNBT persistentData, int level, Projectile projectile, EntityHitResult hit, AbstractArrow arrow, LivingEntity attacker, LivingEntity target) {
        if(target instanceof Mob mob&&attacker instanceof Player player){
            if(level<4){
                mob.setHealth((mob.getHealth() - mob.getMaxHealth() * 0.33f*level));
                arrow.setBaseDamage(arrow.getBaseDamage() + 131072 * level);
            }
            else {
                arrow.setBaseDamage(2147483647);
                mob.die(DamageSource.playerAttack(player));
                mob.kill();
            }
        }
    }
}
