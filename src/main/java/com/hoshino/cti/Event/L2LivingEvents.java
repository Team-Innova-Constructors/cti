package com.hoshino.cti.Event;

import com.hoshino.cti.Cti;
import com.hoshino.cti.Modifier.Armor.AntiCurse;
import com.hoshino.cti.register.CtiModifiers;
import com.hoshino.cti.util.CommonUtil;
import com.hoshino.cti.util.method.GetModifierLevel;
import dev.xkmc.l2hostility.content.capability.mob.MobTraitCap;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import dev.xkmc.l2hostility.init.registrate.LHTraits;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

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
    /**
     *  @author <h3>ItoolstackView</h3>
     *  <h5>增强诅咒词条，使其在玩家免疫效果时也能生效</h5>
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void whenCurseMobAttackPlayer(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Mob mob) {
            LazyOptional<MobTraitCap> optional = mob.getCapability(MobTraitCap.CAPABILITY);
            if (optional.resolve().isPresent()) {
                MobTraitCap cap = optional.resolve().get();
                Set<MobTrait> set = cap.traits.keySet();
                for (int i = 0; i < set.stream().toList().size(); i++) {
                    MobTrait trait = LHTraits.CURSED.get();
                    if (cap.hasTrait(trait) && event.getEntity() instanceof Player player) {
                        int a = player.getPersistentData().getInt("pain");
                        player.getPersistentData().putInt("pain", Math.min(a + 1, 12));
                    }
                }
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void curseCostPlayerHeal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            int a = player.getPersistentData().getInt("pain");
            float shouldHeal = event.getAmount();
            float multiplier = 1f/Math.max (1,(CommonUtil.getArmorLevelingValue(AntiCurse.KEY_ANTI_CURSE,player)+1));
            if (a > 0) {
                switch (a) {
                    case 1, 2, 3 -> event.setAmount(Math.max(0.1F, shouldHeal - a * 0.7F * multiplier));
                    case 4, 5, 6 -> event.setAmount(Math.max(0.05F, shouldHeal - a * 0.6F * multiplier));
                    case 7, 8, 9 -> event.setAmount(Math.max(0, shouldHeal - a * 0.5F * multiplier));
                }
            }
        }
    }
    @SubscribeEvent
    public static void livingTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            int a = player.getPersistentData().getInt("pain");
            int reduct =1+ CommonUtil.getArmorLevelingValue(AntiCurse.KEY_ANTI_CURSE,player);
            if (a > 0 && player.tickCount % 160 == 0) {
                player.getPersistentData().putInt("pain", a - reduct);
            }
        }
    }
}
