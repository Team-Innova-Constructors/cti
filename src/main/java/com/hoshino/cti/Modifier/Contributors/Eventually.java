package com.hoshino.cti.Modifier.Contributors;

import com.hoshino.cti.cti;
import com.hoshino.cti.register.ctiEffects;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import com.marth7th.solidarytinker.util.method.ModifierLevel;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;

public class Eventually extends BattleModifier {

    {
        MinecraftForge.EVENT_BUS.addListener(this::LivingDeathEvent);
    }

    @Override
    public void LivingAttackEvent(LivingAttackEvent event) {
        if(event.getSource().getEntity() instanceof Player player){
            if(ModifierUtil.getModifierLevel(player.getMainHandItem(),this.getId())>0){
                event.getSource().bypassMagic();
            }
        }
    }

    private void LivingDeathEvent(LivingDeathEvent event) {
        if(event.getSource().getEntity() instanceof Player player){
            if(ModifierLevel.getMainhandModifierlevel(player,this.getId())>0&&player.hasEffect(ctiEffects.ev.get())){
                IToolStackView tool= ToolStack.from(player.getMainHandItem());
                ModDataNBT ToolData=tool.getPersistentData();
                ToolData.putInt(DEATH, ToolData.getInt(DEATH)+1);
            }
        }
    }
    private static final ResourceLocation DEATH = cti.getResource("death");
    private static final ResourceLocation HIT = cti.getResource("hit");
    @Override
    public @Nullable Component onRemoved(IToolStackView iToolStackView, Modifier modifier) {
        iToolStackView.getPersistentData().remove(DEATH);
        iToolStackView.getPersistentData().remove(HIT);
        return null;
    }

    @Override
    public int getPriority() {
        return 600;
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity entity, int index, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        ModDataNBT ToolData=tool.getPersistentData();
        if(entity.hasEffect(ctiEffects.ev.get())){
            if(ToolData.getInt(DEATH)>128/(2 * modifier.getLevel())&&!entity.getTags().contains("transmigration")){
                entity.addTag("transmigration");
            }
        }
        else ToolData.putInt(DEATH,0);
    }

    @Override
    public void LivingHurtEvent(LivingHurtEvent event) {
        if(event.getEntity() instanceof Player player){
            if(ModifierUtil.getModifierLevel(player.getMainHandItem(),this.getId())>0){
                IToolStackView tool=ToolStack.from(player.getMainHandItem());
                ModDataNBT ToolData=tool.getPersistentData();
                int ev=ToolData.getInt(HIT);
                int level=ModifierUtil.getModifierLevel(player.getMainHandItem(),this.getId());
                if(ev<128&&!player.hasEffect(ctiEffects.ev.get())){
                   ToolData.putInt(HIT,ev+1);
                }
                else {
                    player.addEffect(new MobEffectInstance(ctiEffects.ev.get(),2560 * level,level-1,true,true));
                    ToolData.putInt(HIT,0);
                }
            }
        }
    }

    @Override
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if(attacker instanceof Player player&&livingTarget instanceof Mob&&!attacker.hasEffect(ctiEffects.ev.get())){
            IToolStackView Tool=ToolStack.from(player.getMainHandItem());
            ModDataNBT ToolData=Tool.getPersistentData();
            int ev=ToolData.getInt(HIT);
                return damage+(float) (Math.pow(2,ev));
            }
        return damage;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if(context.getLivingTarget() instanceof Mob mob&&context.getAttacker() instanceof Player player){
            if(player.hasEffect(ctiEffects.ev.get())){
                mob.die(DamageSource.playerAttack(player));
                mob.remove(Entity.RemovalReason.KILLED);
            }
        }
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> list, TooltipKey key, TooltipFlag tooltipFlag) {
        if(player!=null){
            ModDataNBT ToolData=tool.getPersistentData();
            int amount=ToolData.getInt(DEATH);
            double hit= Math.pow(2,ToolData.getInt(HIT));
            if(player.hasEffect(ctiEffects.ev.get())){
                int EVlevel=player.getEffect(ctiEffects.ev.get()).getAmplifier()+1;
                int need=Math.max(128/(EVlevel * 2) - amount,0);
                list.add(Component.literal("已狩猎的数量："+amount).withStyle(ChatFormatting.LIGHT_PURPLE));
                list.add(Component.literal("你还需要狩猎"+need).withStyle(ChatFormatting.LIGHT_PURPLE));
            }
            else
                list.add(Component.literal("你已经叠加"+hit).withStyle(ChatFormatting.DARK_PURPLE));
        }
    }
}
