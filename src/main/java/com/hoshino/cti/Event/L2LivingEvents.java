package com.hoshino.cti.Event;

import com.hoshino.cti.Cti;
import com.hoshino.cti.register.CtiModifiers;
import com.hoshino.cti.util.method.GetModifierLevel;
import dev.xkmc.l2hostility.content.capability.mob.MobTraitCap;
import dev.xkmc.l2hostility.init.registrate.LHTraits;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = Cti.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class L2LivingEvents {
    /**
     *  @author <h3>ItoolstackView</h3>
     *  <h5>增强隐身词条,原本的隐身词条太过羸弱</h5>
     *  <br><h5>I.在怪物拥有隐身效果并且有隐身词条时候才会触发攻击落空</h5>
     *  <h5>II.落空概率会根据此次伤害/目标当前生命计算,从而在一个80%-0%的区间浮动,值得注意的是超出目标当前生命的伤害必定不会落空(第29行)</h5>
     *  <h5>III.有本影合金/其他手段破除目标隐身则可无视此效果</h5>
     *
     */
    @SubscribeEvent
    public static void invisible(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Mob mob) || event.getSource().getEntity() == null) return;
        float amount = event.getAmount();
        float health = mob.getHealth();
        if (amount > health) return;
        mob.getCapability(MobTraitCap.CAPABILITY)
                .filter(cap -> cap.hasTrait(LHTraits.INVISIBLE.get()) && mob.hasEffect(MobEffects.INVISIBILITY))
                .ifPresentOrElse(cap -> {
                            if (event.getSource().getEntity() instanceof Player player) {
                                if (GetModifierLevel.EachHandsHaveModifierlevel(player, CtiModifiers.end_slayer.getId())) {
                                    return;
                                }
                                var source = mob.level.getRandom();
                                int number = source.nextInt(100);
                                int o = (int) (amount / health * 100f)+20;
                                boolean c = o > number;
                                if (!c) {
                                    event.setCanceled(true);
                                }
                            }
                        }, () -> {}
                );
    }
}
