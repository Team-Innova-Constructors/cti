package com.hoshino.cti.Modifier.Contributors;

import com.hoshino.cti.register.ctiEffects;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class Eventually extends BattleModifier {
    @Override
    public int getPriority() {
        return 600;
    }

    @Override
    public void LivingHurtEvent(LivingHurtEvent event) {
        if(event.getEntity() instanceof Player player){
            if(ModifierUtil.getModifierLevel(player.getMainHandItem(),this.getId())>0){
                int EY = player.getPersistentData().getInt("eventually");
                int level=ModifierUtil.getModifierLevel(player.getMainHandItem(),this.getId());
                if(EY<128&&!player.hasEffect(ctiEffects.ev.get())){
                    player.getPersistentData().putInt("eventually",EY+1);

                }
                else {
                    player.addEffect(new MobEffectInstance(ctiEffects.ev.get(),2560 * level,level-1,true,true));
                    player.getPersistentData().putInt("eventually",0);
                }
            }
        }
    }

    @Override
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if(attacker instanceof Player player&&livingTarget instanceof Mob&&!attacker.hasEffect(ctiEffects.ev.get())){
            int EY = player.getPersistentData().getInt("eventually");

                return damage+(float) (Math.pow(2,EY));
            }
        return damage;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if(context.getLivingTarget() instanceof Mob mob&&context.getAttacker() instanceof Player player){
            if(player.hasEffect(ctiEffects.ev.get())){
                int kill=player.getPersistentData().getInt("transmigration1");
                player.getPersistentData().putInt("transmigration1",kill+1);
                mob.die(DamageSource.playerAttack(player));
            }
        }
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> list, TooltipKey key, TooltipFlag tooltipFlag) {
        if(player!=null){
            int amount=player.getPersistentData().getInt("eventually");
            if(player.hasEffect(ctiEffects.ev.get())){
                int EVlevel=player.getEffect(ctiEffects.ev.get()).getAmplifier()+1;
                int need=Math.max(128/(EVlevel * 2) - amount,0);
                list.add(Component.literal("已狩猎的数量："+amount).withStyle(ChatFormatting.RED));
                list.add(Component.literal("你还需要狩猎"+need).withStyle(ChatFormatting.RED));
            }
        }
    }
}
