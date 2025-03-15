package com.hoshino.cti.L2;

import dev.xkmc.l2hostility.content.capability.mob.MobTraitCap;
import dev.xkmc.l2hostility.content.traits.base.MobTrait;
import dev.xkmc.l2hostility.init.data.LHConfig;
import dev.xkmc.l2hostility.init.registrate.LHTraits;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class PurifyTrait extends MobTrait {
    public PurifyTrait(ChatFormatting format) {
        super(format);
        MinecraftForge.EVENT_BUS.addListener(this::MobEffectEvent);
    }

    @Override
    public void postInit(@NotNull LivingEntity mob, int lv) {
        var cap = MobTraitCap.HOLDER.get(mob);
        var manager = LHTraits.TRAITS.get().tags();
        if (manager == null) return;
        for (int i = 0; i < 4; i++) {
            var opt = manager.getTag(LHTraits.POTION).getRandomElement(mob.getRandom());
            if (opt.isEmpty()) continue;
            var trait = opt.get();
            if (trait.allow(mob) && !cap.hasTrait(trait)) {
                cap.setTrait(trait, lv);
                return;
            }
        }
    }

    private void MobEffectEvent(MobEffectEvent.Applicable event) {
        if (event.getEntity() instanceof Mob mob) {
            LazyOptional<MobTraitCap> optional = mob.getCapability(MobTraitCap.CAPABILITY);
            if (optional.resolve().isPresent()) {
                MobTraitCap cap = optional.resolve().get();
                Set<MobTrait> set = cap.traits.keySet();
                for (int i = 0; i < set.stream().toList().size(); i++) {
                    MobTrait trait = set.stream().toList().get(i);
                    if (cap.hasTrait(trait) && !event.getEffectInstance().getEffect().isBeneficial()) {
                        event.setResult(Event.Result.DENY);
                    }
                }
            }
        }
    }

    @Override
    public void addDetail(List<Component> list) {
        list.add(Component.translatable(getDescriptionId() + ".desc",
                mapLevel(i -> Component.literal(i + "")
                        .withStyle(ChatFormatting.AQUA)),
                mapLevel(i -> Component.literal(Math.round(i * LHConfig.COMMON.drainDamage.get() * 100) + "%")
                        .withStyle(ChatFormatting.AQUA)),
                mapLevel(i -> Component.literal(Math.round(i * LHConfig.COMMON.drainDuration.get() * 100) + "%")
                        .withStyle(ChatFormatting.AQUA)),
                mapLevel(i -> Component.literal(Math.round(i * LHConfig.COMMON.drainDurationMax.get() / 20f) + "")
                        .withStyle(ChatFormatting.AQUA))
        ).withStyle(ChatFormatting.GRAY));
    }
}
