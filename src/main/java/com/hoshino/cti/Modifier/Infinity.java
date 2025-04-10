package com.hoshino.cti.Modifier;

import com.hoshino.cti.register.ctiToolStats;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

public class Infinity extends BattleModifier implements ToolStatsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {
        super.registerHooks(builder);
        builder.addHook(this, ModifierHooks.TOOL_STATS);
    }

    @Override
    public void addToolStats(IToolContext iToolContext, ModifierEntry modifierEntry, ModifierStatsBuilder modifierStatsBuilder) {
        ctiToolStats.ELECTRIC_RESISTANCE.add(modifierStatsBuilder, 50);
        ctiToolStats.SCORCH_RESISTANCE.add(modifierStatsBuilder, 50);
        ctiToolStats.FROZEN_RESISTANCE.add(modifierStatsBuilder, 50);
        ctiToolStats.PRESSURE_RESISTANCE.add(modifierStatsBuilder, 50);
    }

    @Override
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if (livingTarget instanceof Mob mob && attacker instanceof Player player) {
            if (level > 3 || mob.getHealth() <= mob.getMaxHealth() * 0.33f * level) {
                livingTarget.die(DamageSource.playerAttack(player));

            } else if (mob.getHealth() > mob.getMaxHealth() * 0.33f * level) {
                return damage + 131072 * level;
            }
        }
        return damage;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (context.getLivingTarget() instanceof Mob mob) {
            if (modifier.getLevel() > 3 || mob.getHealth() <= mob.getMaxHealth() * 0.33f * modifier.getLevel()) {
                mob.kill();
            } else if (mob.getHealth() > mob.getMaxHealth() * 0.33 * modifier.getLevel()) {
                mob.setHealth(mob.getHealth() - mob.getMaxHealth() * 0.33f * modifier.getLevel());
            }
        }
    }

    @Override
    public void arrowhurt(ModifierNBT modifiers, NamespacedNBT persistentData, int level, Projectile projectile, EntityHitResult hit, AbstractArrow arrow, LivingEntity attacker, LivingEntity target) {
        if (target instanceof Mob mob && attacker instanceof Player player) {
            if (level < 4 || mob.getHealth() >= mob.getMaxHealth() * 0.33f * level) {
                mob.setHealth((mob.getHealth() - mob.getMaxHealth() * 0.33f * level));
                arrow.setBaseDamage(arrow.getBaseDamage() + 131072 * level);
            } else {
                arrow.setBaseDamage(2147483647);
                mob.die(DamageSource.playerAttack(player));
                mob.kill();
            }
        }
    }
}
