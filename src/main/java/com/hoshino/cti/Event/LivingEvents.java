package com.hoshino.cti.Event;

import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import com.aizistral.enigmaticlegacy.registries.EnigmaticItems;
import com.hoshino.cti.Entity.DisposibleFakePlayer;
import com.hoshino.cti.Entity.specialDamageSource.Environmental;
import com.hoshino.cti.Entity.specialDamageSource.PierceThrough;
import com.hoshino.cti.register.ctiEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.Collection;

public class LivingEvents {
    public LivingEvents() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onPierceDamage);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onPierceAttack);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onPierceHurt);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onFakePlayerHurt);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onPlayerHurt);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGHEST, this::onEffectApply);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onEffectApply);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, this::onGobberKillEnderDragon);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGHEST, this::onPlayerTick);
    }

    private void onGobberKillEnderDragon(LivingDeathEvent event) {
        if (event.getEntity() instanceof EnderDragon enderDragon && event.getSource().getEntity() instanceof Player player) {
            ItemStack skull = new ItemStack(Items.DRAGON_HEAD);
            if (player.getMainHandItem().getItem() == TinkerTools.cleaver.get()) {
                skull.setCount(9);
                enderDragon.spawnAtLocation(skull);
            }
        }
    }

    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof ServerPlayer player && SuperpositionHandler.hasCurio(player, EnigmaticItems.THE_CUBE)) {
            Collection<MobEffectInstance> harmeffect = player.getActiveEffects();
            for (int i = 0; i < harmeffect.size(); i++) {
                MobEffectInstance effect = harmeffect.stream().toList().get(i);
                MobEffect harm = effect.getEffect();
                if (harm.getCategory() == MobEffectCategory.HARMFUL) {
                    player.removeEffect(harm);
                    player.kill();
                }
            }
        }
    }

    private void onEffectApply(MobEffectEvent.Applicable event) {
        if (event.getEntity() instanceof Player player && event.getEffectInstance().getEffect().getCategory() == MobEffectCategory.HARMFUL && SuperpositionHandler.hasCurio(player, EnigmaticItems.THE_CUBE)) {
            event.setResult(Event.Result.DENY);
        }
    }

    private void onPlayerHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player1 && event.getEntity() instanceof Player player2) {
            if (!(player1 == player2)) {
                event.setCanceled(true);
            }
        }
    }

    private void onFakePlayerHurt(LivingHurtEvent event) {
        if (event.getEntity().getMaxHealth() > 10000 && event.getSource().getEntity() instanceof FakePlayer fakePlayer && !(fakePlayer instanceof DisposibleFakePlayer) && event.getAmount() < 2147483647) {
            event.setCanceled(true);
        }
    }

    private void onPierceHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            MobEffectInstance instance = player.getEffect(ctiEffects.stress.get());
            if (instance != null && instance.getDuration() > 0) {
                int level = instance.getAmplifier() + 1;
                float red = Math.max(0.9f, 1 - 0.04f * level);
                event.setAmount(event.getAmount() * red);
            }
        }
        if (event.getSource() instanceof PierceThrough source) {
            event.setAmount(source.getAMOUNT());
            event.setCanceled(false);
        } else if (event.getSource() instanceof Environmental source) {
            event.setAmount(source.getAMOUNT());
            event.setCanceled(false);
        }
        if (event.getEntity() instanceof Warden warden && event.getSource().getMsgId().equals("sonic_boom")) {
            event.setAmount(warden.getMaxHealth() / 4);
        }
    }

    private void onPierceAttack(LivingAttackEvent event) {
        if (event.getSource() instanceof PierceThrough source) {
            event.setCanceled(false);
        } else if (event.getSource() instanceof Environmental source) {
            event.setCanceled(false);
        }
    }

    public void onPierceDamage(LivingDamageEvent event) {
        if (event.getSource() instanceof PierceThrough source) {
            event.setAmount(source.getAMOUNT());
            event.setCanceled(false);
        } else if (event.getSource() instanceof Environmental source) {
            event.setAmount(source.getAMOUNT());
            event.setCanceled(false);
        }
    }
}
