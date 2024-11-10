package com.hoshino.cti.Modifier.Contributors;

import com.hoshino.cti.register.ctiEffects;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class eventually extends BattleModifier {
    @Override
    public void LivingHurtEvent(LivingHurtEvent event) {
        if(event.getEntity() instanceof Player player){
            if(ModifierUtil.getModifierLevel(player.getMainHandItem(),this.getId())>0){
                int EY = player.getPersistentData().getInt("eventually");
                if(EY<128&&!player.hasEffect(ctiEffects.ev.get())){
                    player.getPersistentData().putInt("eventually",EY+1);
                }
                else {
                    player.addEffect(new MobEffectInstance(ctiEffects.ev.get(),2560,0,true,true));
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
                mob.die(DamageSource.playerAttack(player));
                mob.remove(Entity.RemovalReason.KILLED);
            }
        }
    }
}
