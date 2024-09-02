package com.hoshino.cti.register;

import com.hoshino.cti.Items.pncMinigunAmmo.UltraDenseAmmo;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ctiItem {
    public ctiItem(){}
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "solidarytinker");
    public static final RegistryObject<Item> test = ITEMS.register("test", ( ) -> new Item(new Item.Properties().tab(com.hoshino.cti.register.ctiTab.MATERIALS)));
    public static final RegistryObject<Item> ultradense_ammo = ITEMS.register("ultradense_ammo", UltraDenseAmmo::new);

}
