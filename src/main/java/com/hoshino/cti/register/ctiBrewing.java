package com.hoshino.cti.register;

import com.marth7th.solidarytinker.register.solidarytinkerItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;

public class ctiBrewing {
    public static void init(){
        PotionBrewing.addMix(Potions.AWKWARD, solidarytinkerItem.violane.get(), ctiPotions.BLOODANGER.get());
        PotionBrewing.addMix(ctiPotions.BLOODANGER.get(), Items.REDSTONE, ctiPotions.LONG_BLOODANGER.get());
        PotionBrewing.addMix(ctiPotions.BLOODANGER.get(), Items.GLOWSTONE_DUST, ctiPotions.STRONG_BLOODANGER.get());
    }
}
