package com.hoshino.cti.Event.AetherEvent;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.marth7th.solidarytinker.register.solidarytinkerModifiers;
import com.marth7th.solidarytinker.util.method.ModifierLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.hoshino.cti.cti.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class Spirit {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void SunSpiritCool(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof Player player && ModifierLevel.EquipHasModifierlevel(player, solidarytinkerModifiers.EXTREMELYCOLD_STATIC_MODIFIER.getId())) {
            if (event.getEntity().getType() == AetherEntityTypes.SUN_SPIRIT.get()) {
                event.getSource().bypassArmor().bypassMagic().bypassInvul();
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onSunSpiritCool(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player && ModifierLevel.EquipHasModifierlevel(player, solidarytinkerModifiers.EXTREMELYCOLD_STATIC_MODIFIER.getId())) {
            if (event.getEntity().getType() == AetherEntityTypes.SUN_SPIRIT.get()) {
                event.setAmount(event.getEntity().getMaxHealth() * 0.8F + event.getAmount());
            }
        }
    }
}
