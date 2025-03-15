package com.hoshino.cti.Modifier.Replace;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

import javax.annotation.Nullable;
import java.util.Collection;

public class FixedOracular extends Modifier implements MeleeHitModifierHook, ProjectileHitModifierHook {
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget(), holder = context.getAttacker();
        if (target == null) {
            return;
        }
        Collection<MobEffectInstance> harmeffect;
        harmeffect = target.getActiveEffects();
        for (int i = 0; i < harmeffect.size(); i++) {
            MobEffectInstance effect = harmeffect.stream().toList().get(i);
            MobEffect bene = effect.getEffect();
            if (bene.getCategory() == MobEffectCategory.BENEFICIAL) {
                target.removeEffect(bene);
                target.level.addParticle(
                        ParticleTypes.HAPPY_VILLAGER,
                        target.getX() + RANDOM.nextDouble() - 0.5,
                        target.getY() + 1,
                        target.getZ() + RANDOM.nextDouble() - 0.5,
                        0, 0, 0);
            }
        }
        harmeffect = holder.getActiveEffects();
        for (int i = 0; i < harmeffect.size(); i++) {
            MobEffectInstance effect = harmeffect.stream().toList().get(i);
            MobEffect harm = effect.getEffect();
            if (harm.getCategory() == MobEffectCategory.HARMFUL) {
                holder.removeEffect(harm);
                holder.level.addParticle(
                        ParticleTypes.HAPPY_VILLAGER,
                        holder.getX() + RANDOM.nextDouble() - 0.5,
                        holder.getY() + 1,
                        holder.getZ() + RANDOM.nextDouble() - 0.5,
                        0, 0, 0);
            }
        }
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_HIT);
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target != null && attacker != null) {
            Collection<MobEffectInstance> harmeffect;
            harmeffect = target.getActiveEffects();
            for (int i = 0; i < harmeffect.size(); i++) {
                MobEffectInstance effect = harmeffect.stream().toList().get(i);
                MobEffect harm = effect.getEffect();
                if (harm.getCategory() == MobEffectCategory.BENEFICIAL) {
                    target.removeEffect(harm);
                    target.level.addParticle(
                            ParticleTypes.HAPPY_VILLAGER,
                            target.getX() + RANDOM.nextDouble() - 0.5,
                            target.getY() + 1,
                            target.getZ() + RANDOM.nextDouble() - 0.5,
                            0, 0, 0);
                }
            }
            harmeffect = attacker.getActiveEffects();
            for (int i = 0; i < harmeffect.size(); i++) {
                MobEffectInstance effect = harmeffect.stream().toList().get(i);
                MobEffect harm = effect.getEffect();
                if (harm.getCategory() == MobEffectCategory.HARMFUL) {
                    attacker.removeEffect(harm);
                    attacker.level.addParticle(
                            ParticleTypes.HAPPY_VILLAGER,
                            attacker.getX() + RANDOM.nextDouble() - 0.5,
                            attacker.getY() + 1,
                            attacker.getZ() + RANDOM.nextDouble() - 0.5,
                            0, 0, 0);
                }
            }
        }
        return false;
    }
}