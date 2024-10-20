package com.hoshino.cti.mixin.TIMixin;

import com.c2h6s.etshtinker.init.etshtinkerParticleType;
import com.xiaoyue.tinkers_ingenuity.content.tools.item.tinker.LaserGun;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.compress.utils.Lists;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.Iterator;
import java.util.List;

@Mixin(value = LaserGun.class,remap = false)
public class LaserGunMixin {
    @Redirect(method = "m_7203_", remap = false, at = @At(value = "INVOKE", target = "Lcom/xiaoyue/tinkers_ingenuity/content/tools/item/tinker/LaserGun;emitLaser(Lslimeknights/tconstruct/library/tools/nbt/ToolStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/Vec3;F)V"))
    void emitLaser(LaserGun instance, ToolStack toolStack, Player attacker, InteractionHand hand, Level world, BlockPos blockPos, Vec3 angle, float range) {
        Vec3 start = new Vec3(attacker.getX(),attacker.getEyeY(),attacker.getZ());
        Vec3 direction = angle.normalize();

        for(double d = 0.0; d < (double)range*2; d += 0.25) {
            Vec3 particlePos = start.add(direction.scale(d));
            ServerLevel serverWorld = (ServerLevel)world;
            serverWorld.sendParticles(etshtinkerParticleType.laserparticle.get(), particlePos.get(Direction.Axis.X), particlePos.get(Direction.Axis.Y), particlePos.get(Direction.Axis.Z), 1, 0.0, 0.0, 0.0, 0.0);
        }

        List<Entity> entities = Lists.newArrayList();

        for(double d = 0.0; d < (double)range; d += 0.5) {
            Vec3 currentPos = start.add(direction.scale(d));
            AABB smallAabb = new AABB(currentPos.x - 0.5, currentPos.y - 0.5, currentPos.z - 0.5, currentPos.x + 0.5, currentPos.y + 0.5, currentPos.z + 0.5);
            Iterator var15 = world.getEntities((Entity)null, smallAabb).iterator();

            while(var15.hasNext()) {
                Entity entity = (Entity)var15.next();
                if (entity instanceof LivingEntity && entity.isAlive() && !entities.contains(entity) && entity != attacker) {
                    ToolAttackUtil.attackEntity(toolStack, attacker, hand, entity, ToolAttackUtil.getCooldownFunction(attacker, hand), false);
                    entities.add(entity);
                }
            }
        }
    }
}
