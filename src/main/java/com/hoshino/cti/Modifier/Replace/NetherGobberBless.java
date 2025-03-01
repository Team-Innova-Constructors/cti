package com.hoshino.cti.Modifier.Replace;

import com.hoshino.cti.util.method.GetModifierLevel;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerTools;

public class NetherGobberBless extends BattleModifier {
    public boolean isCorrectDimension(LivingEntity livingEntity){
        return livingEntity.getLevel().dimension().equals(Level.NETHER);
    }

    @Override
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if(this.isCorrectDimension(attacker)){
            return damage * (1+level * 0.25f);
        }
        return damage;
    }

    @Override
    public void arrowhurt(ModifierNBT modifiers, NamespacedNBT persistentData, int level, Projectile projectile, EntityHitResult hit, AbstractArrow arrow, LivingEntity attacker, LivingEntity target) {
        if(this.isCorrectDimension(attacker)){
            arrow.setBaseDamage(arrow.getBaseDamage() * (1+level*0.25f));
        }
    }
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if(context.getAttacker() instanceof Player player&&context.getLivingTarget() instanceof WitherSkeleton WS){
            WS.die(DamageSource.playerAttack(player));
            WS.remove(Entity.RemovalReason.KILLED);
            ItemStack skull = new ItemStack(Items.WITHER_SKELETON_SKULL);
            int Modifierlevel= ModifierUtil.getModifierLevel(player.getMainHandItem(), TinkerModifiers.severing.getId())-4;
            if(tool.getItem()==TinkerTools.cleaver.get()){
                    context.getLivingTarget().spawnAtLocation(skull,9*Modifierlevel);
            }
            else context.getLivingTarget().spawnAtLocation(skull,1);
        }
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity entity, int index, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if(entity instanceof Player player){
            if(!player.hasEffect(MobEffects.FIRE_RESISTANCE)){
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,100,0,false,false));
            }
            for(ItemStack itemStack1: player.getAllSlots()){
                if(ModifierUtil.getModifierLevel(itemStack1,this.getId())>0&&player.tickCount%60==0){
                    float saturationLevel= player.getFoodData().getSaturationLevel();
                    int foodlevel=player.getFoodData().getFoodLevel();
                    player.getFoodData().setFoodLevel(Math.min(20,foodlevel+1));
                    player.getFoodData().setSaturation(Math.min(20,saturationLevel+1));
                }
            }
        }
    }

    @Override
    public void LivingDamageEvent(LivingDamageEvent event) {
        if(event.getEntity() instanceof Player player){
            if(this.isCorrectDimension(player)){
                int level= GetModifierLevel.getAllSlotModifierlevel(player,this.getId());
                if(level>0){
                    event.setAmount(event.getAmount() * (1-level * 0.08F));
                }
            }
        }
    }
}
