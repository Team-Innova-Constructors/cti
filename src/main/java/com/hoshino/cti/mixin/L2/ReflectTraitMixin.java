package com.hoshino.cti.mixin.L2;

import com.hoshino.cti.util.method.GetModifierLevel;
import com.marth7th.solidarytinker.register.TinkerCuriosModifier;
import dev.xkmc.l2hostility.compat.curios.CurioCompat;
import dev.xkmc.l2hostility.content.traits.common.ReflectTrait;
import dev.xkmc.l2hostility.init.data.LHConfig;
import dev.xkmc.l2hostility.init.registrate.LHItems;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ReflectTrait.class, remap = false)
public class ReflectTraitMixin {
    /**
     * @author firefly
     * @reason 反射伤害过高,远高于怪物生命,现将其设置为不会超出怪物生命倍率
     */
    @Overwrite
    public void onHurtByOthers(int level, LivingEntity entity, LivingHurtEvent event) {
        if(event.getSource().getEntity() instanceof LivingEntity lv&&event.getSource() instanceof EntityDamageSource source&&!source.isThorns()){
            if (CurioCompat.hasItem(lv, LHItems.ABRAHADABRA.get())) {
                return;
            }
            if(lv instanceof Player player&& GetModifierLevel.CurioHasModifierlevel(player, TinkerCuriosModifier.BHA_STATIC_MODIFIER.getId())){
                return;
            }
            float Magnification = (float) (level * LHConfig.COMMON.reflectFactor.get());
            float reflectAmount =Math.min(event.getEntity().getHealth() * Magnification,event.getAmount() *Magnification);
            EntityDamageSource mobAttackReflect=new EntityDamageSource("mobattackreflect",event.getEntity());
            lv.hurt(mobAttackReflect,reflectAmount);
        }
    }
}
