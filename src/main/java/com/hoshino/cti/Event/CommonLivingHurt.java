package com.hoshino.cti.Event;

import com.hoshino.cti.register.ctiEffects;
import com.hoshino.cti.register.ctiHostilityTrait;
import com.hoshino.cti.register.ctiModifiers;
import com.hoshino.cti.util.method.GetModifierLevel;
import dev.xkmc.l2hostility.content.capability.mob.MobTraitCap;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import dev.xkmc.l2hostility.init.registrate.LHTraits;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;

import static com.hoshino.cti.cti.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class CommonLivingHurt {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void LivingEntityEffectHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer player && player.hasEffect(ctiEffects.numerical_perception.get())) {
            event.setAmount(event.getAmount() * 2);
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void PreventExplosionEvent(ExplosionEvent.Start event) {
        if (event.getExplosion().getSourceMob() instanceof Creeper creeper) {
            List<Player> playerlist = creeper.level.getEntitiesOfClass(Player.class, new AABB(creeper.getX() + 10, creeper.getY() + 10, creeper.getZ() + 10, creeper.getX() - 10, creeper.getY() - 10, creeper.getZ() - 10));
            for (Player player : playerlist) {
                if (GetModifierLevel.EquipHasModifierlevel(player, ctiModifiers.ExplosionPrevent.getId())) {
                    if (creeper.level instanceof ServerLevel level) {
                        level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1, 1);
                        level.sendParticles(ParticleTypes.EXPLOSION, creeper.getX(), creeper.getY() + 0.5 * creeper.getBbHeight(), creeper.getZ(), 1, 0, 0, 0, 0);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

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
            if (a > 0) {
                switch (a) {
                    case 1, 2, 3 -> event.setAmount(Math.max(0.1F, shouldHeal - a * 0.7F));
                    case 4, 5, 6 -> event.setAmount(Math.max(0.05F, shouldHeal - a * 0.6F));
                    case 7, 8, 9 -> event.setAmount(Math.max(0, shouldHeal - a * 0.5F));
                }
            }
        }
    }

    @SubscribeEvent
    public static void livingTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            int a = player.getPersistentData().getInt("pain");
            if (a > 0 && player.tickCount % 160 == 0) {
                player.getPersistentData().putInt("pain", a - 1);
            }
        }
    }
}
