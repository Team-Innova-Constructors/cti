package com.hoshino.cti.register;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ctiTab {
    public static final CreativeModeTab MATERIALS = new CreativeModeTab("materials") {
        @Override
        public  ItemStack makeIcon() {
            return new ItemStack((ItemLike) ctiItem.test.get());
        }
    };
    public ctiTab(){}
}
