package com.hoshino.cti.Modifier;

import com.c2h6s.etshtinker.Modifiers.modifiers.etshmodifieriii;
import com.hoshino.cti.content.environmentSystem.EDamageSource;
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

import static com.hoshino.cti.content.environmentSystem.EnvironmentalHandler.*;

public class PressureIndused extends etshmodifieriii {

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        Entity entity = context.getTarget();
        LivingEntity living = context.getAttacker();
        if (entity instanceof LivingEntity target && living instanceof Player player && !(entity instanceof Player)&&context.isFullyCharged()) {
            target.invulnerableTime = 0;
            target.hurt(EDamageSource.indirectPressure(false, player,modifier.getLevel()), damageDealt / 6);
            if (getPressureResistance(target) <= 1.5 && getPressureValue(target) < 20) {
                addPressureValue(target, modifier.getLevel()*3);
            }
            target.invulnerableTime = 0;
        }
    }
}
