package com.hoshino.cti.util;

import com.hoshino.cti.cti;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ctiTagkey {
    public static final TagKey<Item> OXYGEN_REGEN = TagKey.create(Registry.ITEM_REGISTRY, cti.getResource("oxygen_provide"));
    public static final TagKey<Item> PRESSURE_MINOR = TagKey.create(Registry.ITEM_REGISTRY, cti.getResource("pressure_protect_minor"));
}
