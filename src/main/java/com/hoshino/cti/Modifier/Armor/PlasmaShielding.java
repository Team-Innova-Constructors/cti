package com.hoshino.cti.Modifier.Armor;

import com.c2h6s.etshtinker.util.slotUtil;
import com.hoshino.cti.cti;
import com.hoshino.cti.register.ctiModifiers;
import com.hoshino.cti.register.ctiToolStats;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

public class PlasmaShielding extends Modifier implements DamageBlockModifierHook , InventoryTickModifierHook , ToolStatsModifierHook {
    public static final ResourceLocation SHIELD_LOCATION = cti.getResource("plasma_shield");
    public static final ResourceLocation CD_LOCATION = cti.getResource("plasma_cooldown");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS,ModifierHooks.INVENTORY_TICK,ModifierHooks.DAMAGE_BLOCK);
    }

    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifierEntry, EquipmentContext equipmentContext, EquipmentSlot equipmentSlot, DamageSource damageSource, float damage) {
        LivingEntity entity = equipmentContext.getEntity();
        if (damageSource.getEntity() instanceof Projectile projectile){
            damageSource.setProjectile();
            projectile.discard();
            return true;
        }
        if (damage<=0){
            return true;
        }
        if (tool.getPersistentData().getInt(SHIELD_LOCATION)>0&&tool.getPersistentData().getInt(CD_LOCATION)<=0&&equipmentContext.getEntity().invulnerableTime<=0&&!damageSource.isProjectile()&&!damageSource.isExplosion()&&!damageSource.isFire()){
            tool.getPersistentData().putInt(SHIELD_LOCATION,tool.getPersistentData().getInt(SHIELD_LOCATION)-1);
            equipmentContext.getEntity().invulnerableTime=Math.max(10,equipmentContext.getEntity().invulnerableTime);
            if (equipmentContext.getEntity().level.isClientSide){
                equipmentContext.getEntity().level.playLocalSound(entity.getX(),entity.getEyeY(),entity.getZ(), SoundEvents.METAL_HIT, SoundSource.PLAYERS,1,1.5f,true);
            }
            if (tool.getPersistentData().getInt(SHIELD_LOCATION)<=0){
                tool.getPersistentData().putInt(CD_LOCATION,600);
                if (equipmentContext.getEntity().level.isClientSide){
                    equipmentContext.getEntity().level.playLocalSound(entity.getX(),entity.getEyeY(),entity.getZ(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS,1,1.2f,true);
                }
            }
            return true;
        }
        return damageSource.isProjectile()||damageSource.isExplosion();
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (isCorrectSlot){
            if (tool.getPersistentData().getInt(SHIELD_LOCATION)<modifier.getLevel()+2&&tool.getPersistentData().getInt(CD_LOCATION)<=0){
                tool.getPersistentData().putInt(SHIELD_LOCATION,modifier.getLevel()+2);
                if (world.isClientSide) world.playLocalSound(holder.getX(),holder.getEyeY(),holder.getZ(),SoundEvents.AMETHYST_BLOCK_CHIME,SoundSource.PLAYERS,1,0.75f,true);
            }
        }
    }

    public static boolean isShieldActive(Player player){
        for (EquipmentSlot slot: slotUtil.ARMOR){
            if (player.getItemBySlot(slot).getItem() instanceof IModifiable){
                ToolStack toolStack = ToolStack.from(player.getItemBySlot(slot));
                if (toolStack.getModifierLevel(ctiModifiers.plasma_shielding.get())>0){
                    return toolStack.getPersistentData().getInt(SHIELD_LOCATION)>0&&toolStack.getPersistentData().getInt(CD_LOCATION)<=0;
                }
            }
        }
        return false;
    }

    @Override
    public void addToolStats(IToolContext iToolContext, ModifierEntry modifierEntry, ModifierStatsBuilder modifierStatsBuilder) {
        ctiToolStats.ELECTRIC_RESISTANCE.add(modifierStatsBuilder,1);
        ctiToolStats.FROZEN_RESISTANCE.add(modifierStatsBuilder,1);
        ctiToolStats.SCORCH_RESISTANCE.add(modifierStatsBuilder,1);
    }
}
