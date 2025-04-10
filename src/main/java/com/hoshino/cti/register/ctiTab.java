package com.hoshino.cti.register;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ctiTab {
    public static final CreativeModeTab MATERIALS = new CreativeModeTab("cti.materials") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack((ItemLike) ctiItem.test.get());
        }
    };
    public static final CreativeModeTab MIXC = new CreativeModeTab("cti.mixc") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack((ItemLike) ctiItem.unipolar_magnet.get());
        }
    };
    public static final CreativeModeTab MACHINE = new CreativeModeTab("cti.machine") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ctiItem.atmosphere_condensator.get());
        }
    };
    public static final CreativeModeTab FOOD = new CreativeModeTab("cti.food") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ctiItem.etbeer.get());
        }
    };

    public ctiTab() {
    }
}
