package com.hoshino.cti.Modifier.aetherCompact;

import com.aetherteam.aether.data.resources.AetherDamageTypes;
import com.c2h6s.etshtinker.Modifiers.modifiers.EtSTBaseModifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
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

public class ValkyrieBless extends EtSTBaseModifier {
    @Override
    public void postMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage) {
        if (context.isFullyCharged()&&context.getTarget() instanceof LivingEntity) {
            Entity entity = context.getTarget();
            entity.invulnerableTime = 0;
            entity.hurt(RANDOM.nextBoolean() ? AetherDamageTypes.cloudCrystal(context.getAttacker(), context.getAttacker()) : AetherDamageTypes.thunderCrystal(context.getAttacker(), context.getAttacker()), damage * 0.2f * modifier.getLevel());
            entity.invulnerableTime = 0;
            LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, entity.level);
            bolt.setPos(entity.position());
            bolt.setCause(context.getAttacker() instanceof ServerPlayer player ? player : null);
            bolt.setDamage(damage * 0.2f);
            entity.level.addFreshEntity(bolt);
        }
    }

    @Override
    public boolean modifierOnProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target!=null&&attacker!=null&&projectile instanceof AbstractArrow arrow) {
            target.invulnerableTime = 0;
            target.hurt(RANDOM.nextBoolean() ? AetherDamageTypes.cloudCrystal(attacker, attacker) : AetherDamageTypes.thunderCrystal(attacker, attacker), (float) (arrow.getBaseDamage()*arrow.getDeltaMovement().length() * 0.2f * modifier.getLevel()));
            target.invulnerableTime = 0;
            LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, target.level);
            bolt.setPos(target.position());
            bolt.setCause(attacker instanceof ServerPlayer player ? player : null);
            bolt.setDamage((float) (arrow.getBaseDamage()*arrow.getDeltaMovement().length() * 0.2f));
            target.level.addFreshEntity(bolt);
        }
        return false;
    }
}
