package com.hoshino.cti.Modifier.Developer;

import com.hoshino.cti.register.ctiEffects;
import com.hoshino.cti.register.ctiToolStats;
import com.marth7th.solidarytinker.extend.superclass.ArmorModifier;
import com.marth7th.solidarytinker.util.method.ModifierLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

public class Trauma extends ArmorModifier {
    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ctiToolStats.ELECTRIC_RESISTANCE.add(builder,14.5F);
        ctiToolStats.FROZEN_RESISTANCE.add(builder,14.5F);
        ctiToolStats.PRESSURE_RESISTANCE.add(builder,14.5F);
        ctiToolStats.SCORCH_RESISTANCE.add(builder,14.5F);
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity entity, int index, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if(entity instanceof Player player){
            if(player.hasEffect(ctiEffects.ev.get())){
                if(ModifierLevel.EquipHasModifierlevel(entity,this.getId())){
                    double x = player.getX();
                    double y = player.getY();
                    double z = player.getZ();
                    List<Mob> mobbbb = player.level.getEntitiesOfClass(Mob.class, new AABB(x + 10, y + 10, z + 10, x - 10, y - 10, z - 10));
                    for (Mob targets : mobbbb) {
                        if (targets != null&&!targets.getType().getCategory().isFriendly()) {
                            BlockPos posA = player.getOnPos();
                            targets.hurt(DamageSource.playerAttack(player).bypassMagic().bypassArmor().bypassInvul(),Float.MAX_VALUE);
                            targets.die(DamageSource.playerAttack(player));
                            player.level.playSound(null,posA, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.PLAYERS,1F,1F);
                            targets.remove(Entity.RemovalReason.KILLED);
                        }
                    }
                }
            }
        }
    }
}