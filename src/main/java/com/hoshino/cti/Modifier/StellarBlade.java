package com.hoshino.cti.Modifier;

import com.c2h6s.etshtinker.Modifiers.modifiers.etshmodifieriii;
import com.hoshino.cti.Entity.Projectiles.FallenStars;
import com.hoshino.cti.netwrok.ctiPacketHandler;
import com.hoshino.cti.netwrok.packet.PStellarBlade;
import com.hoshino.cti.register.ctiEntity;
import com.hoshino.cti.register.ctiItem;
import com.hoshino.cti.register.ctiModifiers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

import static com.c2h6s.etshtinker.etshtinker.EtSHrnd;
import static com.c2h6s.etshtinker.util.vecCalc.*;

public class StellarBlade extends etshmodifieriii {
    public StellarBlade(){
        MinecraftForge.EVENT_BUS.addListener(this::LeftClick);
    }

    private void LeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        ctiPacketHandler.sendToServer(new PStellarBlade());
    }

    @Override
    public boolean isNoLevels() {
        return true;
    }
    public static final List<Item> ls = List.of(ctiItem.star_blaze.get(),ctiItem.star_pressure.get(),ctiItem.star_ionize.get(),ctiItem.star_frozen.get());

    @Override
    public void modifierOnAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        super.modifierOnAttacked(tool, modifier, context, slotType, source, amount, isDirectDamage);
        LivingEntity attacker = context.getEntity();
        if (source.getEntity()!=null&&source==DamageSource.thorns(source.getEntity())){
            return;
        }
        if (source.getEntity() instanceof Player){
            return;
        }
        if (attacker instanceof Player player&&amount>5&&!player.getCooldowns().isOnCooldown(tool.getItem())&&modifier.getLevel()>0) {
            List<Mob> mobbbbbbb = context.getEntity().level.getEntitiesOfClass(Mob.class, new AABB(player.getX() - 16, player.getY() - 16, player.getZ() - 16, player.getX() + 16, player.getY() + 16, player.getZ() + 16));
            if (!mobbbbbbb.isEmpty()) {
                for (Mob target : mobbbbbbb) {
                    if (target != null) {
                        int c = 0;
                        while (c < EtSHrnd().nextInt(4) + 2) {
                            Level level = player.level;
                            Vec3 vec3 = getScatteredVec3(new Vec3(0, -0.25, 0), 0.57735);
                            double d = EtSHrnd().nextDouble() * 20;
                            Vec3 direction = new Vec3(-(30 + d) * vec3.x, -(30 + d) * vec3.y, -(30 + d) * vec3.z);
                            FallenStars fallenStars;
                            int rnd = EtSHrnd().nextInt(4);
                            if (rnd == 1) {
                                fallenStars = new FallenStars(ctiEntity.star_pressure.get(), level, ls.get(1));
                            } else if (rnd == 2) {
                                fallenStars = new FallenStars(ctiEntity.star_ionize.get(), level, ls.get(2));
                            } else if (rnd == 3) {
                                fallenStars = new FallenStars(ctiEntity.star_frozen.get(), level, ls.get(3));
                            } else {
                                fallenStars = new FallenStars(ctiEntity.star_blaze.get(), level, ls.get(0));
                            }
                            fallenStars.setOwner(player);
                            fallenStars.baseDamage = amount;
                            fallenStars.setDeltaMovement(vec3);
                            fallenStars.setPos(target.getX() + direction.x, target.getY() + target.getBbHeight() + direction.y, target.getZ() + direction.z);
                            player.level.addFreshEntity(fallenStars);
                            c++;
                        }
                    }
                }
                player.getCooldowns().addCooldown(tool.getItem(),10);
            }
        }
    }

    @Override
    public boolean modifierOnProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (attacker instanceof Player player&&target!=null&&projectile instanceof AbstractArrow arrow&&!(target instanceof Player)){
            int c =0;
            while (c<EtSHrnd().nextInt(4)+2) {
                Level level = player.level;
                Vec3 vec3 = getScatteredVec3(new Vec3(0, -0.25, 0), 0.57735);
                double d = EtSHrnd().nextDouble() * 20;
                Vec3 direction = new Vec3(-(30 + d) * vec3.x, -(30 + d) * vec3.y, -(30 + d) * vec3.z);
                FallenStars fallenStars;
                int rnd = EtSHrnd().nextInt(4);
                if (rnd == 1) {
                    fallenStars = new FallenStars(ctiEntity.star_pressure.get(), level, ls.get(1));
                } else if (rnd == 2) {
                    fallenStars = new FallenStars(ctiEntity.star_ionize.get(), level, ls.get(2));
                } else if (rnd == 3) {
                    fallenStars = new FallenStars(ctiEntity.star_frozen.get(), level, ls.get(3));
                } else {
                    fallenStars = new FallenStars(ctiEntity.star_blaze.get(), level, ls.get(0));
                }
                fallenStars.setOwner(player);
                fallenStars.baseDamage = (float) (arrow.getBaseDamage()*getMold(arrow.getDeltaMovement()));
                fallenStars.setDeltaMovement(vec3);
                fallenStars.setPos(target.getX() + direction.x, target.getY() + target.getBbHeight() + direction.y, target.getZ() + direction.z);
                player.level.addFreshEntity(fallenStars);
                c++;
            }
        }
        return false;
    }

    @Override
    public float modifierBeforeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        Player player = context.getPlayerAttacker();
        Entity entity = context.getTarget();
        if (player!=null&&entity instanceof LivingEntity target&&!context.isExtraAttack()&&!(target instanceof Player)){
            int c =0;
            while (c<EtSHrnd().nextInt(4)+2) {
                Level level = player.level;
                Vec3 vec3 = getScatteredVec3(new Vec3(0, -0.25, 0), 0.57735);
                double d = EtSHrnd().nextDouble() * 20;
                Vec3 direction = new Vec3(-(30 + d) * vec3.x, -(30 + d) * vec3.y, -(30 + d) * vec3.z);
                FallenStars fallenStars;
                int rnd = EtSHrnd().nextInt(4);
                if (rnd == 1) {
                    fallenStars = new FallenStars(ctiEntity.star_pressure.get(), level, ls.get(1));
                } else if (rnd == 2) {
                    fallenStars = new FallenStars(ctiEntity.star_ionize.get(), level, ls.get(2));
                } else if (rnd == 3) {
                    fallenStars = new FallenStars(ctiEntity.star_frozen.get(), level, ls.get(3));
                } else {
                    fallenStars = new FallenStars(ctiEntity.star_blaze.get(), level, ls.get(0));
                }
                fallenStars.setOwner(player);
                fallenStars.baseDamage = damage;
                fallenStars.setDeltaMovement(vec3);
                fallenStars.setPos(target.getX() + direction.x, target.getY() + target.getBbHeight() + direction.y, target.getZ() + direction.z);
                player.level.addFreshEntity(fallenStars);
                c++;
            }
        }
        return knockback;
    }
    public static void summonStars(@NotNull Player player){
        Entity entity =getNearestLiEnt(16f,player,player.level);
        ToolStack tool = ToolStack.from(player.getItemInHand(player.getUsedItemHand()));
        if (entity instanceof LivingEntity target&&tool.getModifierLevel(ctiModifiers.stellar_blade.get())>0){
            int c =0;
            while (c<EtSHrnd().nextInt(4)+2) {
                Level level = player.level;
                Vec3 vec3 = getScatteredVec3(new Vec3(0, -0.25, 0), 0.57735);
                double d = EtSHrnd().nextDouble() * 20;
                Vec3 direction = new Vec3(-(30 + d) * vec3.x, -(30 + d) * vec3.y, -(30 + d) * vec3.z);
                FallenStars fallenStars;
                int rnd = EtSHrnd().nextInt(4);
                if (rnd == 1) {
                    fallenStars = new FallenStars(ctiEntity.star_pressure.get(), level, ls.get(1));
                } else if (rnd == 2) {
                    fallenStars = new FallenStars(ctiEntity.star_ionize.get(), level, ls.get(2));
                } else if (rnd == 3) {
                    fallenStars = new FallenStars(ctiEntity.star_frozen.get(), level, ls.get(3));
                } else {
                    fallenStars = new FallenStars(ctiEntity.star_blaze.get(), level, ls.get(0));
                }
                fallenStars.setOwner(player);
                float damage = tool.getStats().get(ToolStats.ATTACK_DAMAGE);
                for (ModifierEntry modifiers:tool.getModifierList()){
                    damage = modifiers.getHook(ModifierHooks.MELEE_DAMAGE).getMeleeDamage(tool,modifiers,new ToolAttackContext(player,player,player.getUsedItemHand(),target,target,false,0,true),tool.getStats().get(ToolStats.ATTACK_DAMAGE),damage);
                }
                fallenStars.baseDamage = damage;
                fallenStars.setDeltaMovement(vec3);
                fallenStars.setPos(target.getX() + direction.x, target.getY() + target.getBbHeight() + direction.y, target.getZ() + direction.z);
                player.level.addFreshEntity(fallenStars);
                c++;
            }
        }
    }
}
