package com.hoshino.cti.Items.pncMinigunAmmo;

import me.desht.pneumaticcraft.common.config.ConfigHelper;
import me.desht.pneumaticcraft.common.core.ModUpgrades;
import me.desht.pneumaticcraft.common.item.minigun.AbstractGunAmmoItem;
import me.desht.pneumaticcraft.common.minigun.Minigun;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import net.minecraft.client.gui.screens.Screen;

import javax.annotation.Nullable;
import java.util.List;

public class UltraDenseAmmo extends AbstractGunAmmoItem {
    @Override
    public int getAmmoColor(ItemStack itemStack) {
        return 0x00600075;
    }
    @Override
    public int getMaxDamage(ItemStack stack) {
        return 750;
    }
    @Override
    protected float getDamageMultiplier(Entity target, ItemStack ammoStack) {
        return 3f;
    }
    @Override
    public float getAirUsageMultiplier(Minigun minigun, ItemStack ammoStack) {
        return 10.0f;
    }
    public float getRangeMultiplier(ItemStack ammoStack) {
        return 0.25f;
    }
    @Override
    public int onTargetHit(Minigun minigun, ItemStack ammo, Entity target) {
        int times = 1;
        int nSpeed = minigun.getUpgrades(ModUpgrades.SPEED.get());
        for (int i = 0; i < nSpeed; i++) {
            if (minigun.getWorld().random.nextInt(100) < 20) times++;
        }
        double dmgMult = getDamageMultiplier(target, ammo);
        if (dmgMult > 0) {
            if (target instanceof LivingEntity || target instanceof EnderDragonPart || target instanceof EndCrystal) {
                target.invulnerableTime=0;
                target.hurt(getDamageSource(minigun), (float)(ConfigHelper.common().minigun.baseDamage.get() * dmgMult * times));
            } else if (target instanceof ShulkerBullet || target instanceof AbstractHurtingProjectile) {
                target.discard();
            }
        }
        return times;
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        if (Screen.hasShiftDown()){
            list.add(Component.translatable("cti.tooltip.item.ultradense_ammo").withStyle(ChatFormatting.AQUA));
        }else {
            list.add(Component.translatable("cti.tooltip.item.shift").withStyle(ChatFormatting.AQUA));
        }
    }
}
