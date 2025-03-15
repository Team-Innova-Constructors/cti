package com.hoshino.cti.Event;

import com.hoshino.cti.register.ctiEffects;
import com.hoshino.cti.register.ctiModifiers;
import com.hoshino.cti.util.method.GetModifierLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

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
}
