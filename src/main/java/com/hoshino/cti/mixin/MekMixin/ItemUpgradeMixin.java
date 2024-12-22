package com.hoshino.cti.mixin.MekMixin;

import mekanism.common.item.ItemUpgrade;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(remap = false,value = ItemUpgrade.class)
public class ItemUpgradeMixin {
    @ModifyArg(method = "m_6225_",at = @At(value = "INVOKE", target = "Lmekanism/common/tile/component/TileComponentUpgrade;addUpgrades(Lmekanism/api/Upgrade;I)I"),index = 1)
    protected int limitCount(int maxAvailable){
        return Math.min(maxAvailable, 8);
    }
}
