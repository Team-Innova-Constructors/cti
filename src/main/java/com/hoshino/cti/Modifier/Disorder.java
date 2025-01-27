package com.hoshino.cti.Modifier;

import com.c2h6s.etshtinker.Entities.plasmaexplosionentity;
import com.c2h6s.etshtinker.Modifiers.modifiers.etshmodifieriii;
import com.c2h6s.etshtinker.init.etshtinkerEffects;
import com.c2h6s.etshtinker.init.etshtinkerEntity;
import com.c2h6s.etshtinker.init.etshtinkerParticleType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.Random;

import static com.c2h6s.etshtinker.etshtinker.EtSHrnd;
import static com.c2h6s.etshtinker.util.vecCalc.getScatteredVec3;

public class Disorder extends etshmodifieriii {
    @Override
    public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        if (!context.isExtraAttack()){
            Random random = new Random();
            Player player = context.getPlayerAttacker();
            Entity entity = context.getTarget();
            if (player!=null&&entity instanceof LivingEntity target) {
                int c = 0;
                while (c < modifier.getLevel()) {
                    if (random.nextBoolean()) break;
                    c++;
                    Level level = player.level;
                    Vec3 vec3 = getScatteredVec3(new Vec3(0, EtSHrnd().nextInt(2)==1?1:-1, 0), 80).normalize();
                    double d = EtSHrnd().nextDouble() * 4+2;
                    Vec3 direction = new Vec3(-(d) * vec3.x, -(d) * vec3.y, -(d) * vec3.z);
                    plasmaexplosionentity explosion = new plasmaexplosionentity(etshtinkerEntity.plasmaexplosionentity.get(),level);
                    explosion.setOwner(player);
                    explosion.damage=damage/4;
                    explosion.tool= (ToolStack) tool;
                    explosion.particle = etshtinkerParticleType.plasmaexplosionpurple.get();
                    explosion.setPos(new Vec3(target.getX(),target.getY()+target.getBbHeight()*0.5,target.getZ()).add(direction));
                    explosion.rayVec3 = vec3.scale(d*2);
                    level.addFreshEntity(explosion);
                }
            }
        }
        if (context.getTarget() instanceof LivingEntity living){
            living.forceAddEffect(new MobEffectInstance(etshtinkerEffects.hi_gravity.get(),200,1,false,false), context.getAttacker());
        }
        return knockback;
    }
}
