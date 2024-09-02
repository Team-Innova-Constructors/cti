package com.hoshino.cti.register;

import com.hoshino.cti.Items.pncMinigunAmmo.ElectroniumAmmo;
import com.hoshino.cti.Items.pncMinigunAmmo.ProtoniumAmmo;
import com.hoshino.cti.Items.pncMinigunAmmo.UltraDenseAmmo;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ctiItem {
    public ctiItem(){}
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "cti");
    public static final RegistryObject<Item> test = ITEMS.register("test", ( ) -> new Item(new Item.Properties().tab(com.hoshino.cti.register.ctiTab.MATERIALS)));
    public static final RegistryObject<Item> ultradense_ammo = ITEMS.register("ultradense_ammo", UltraDenseAmmo::new);
    public static final RegistryObject<Item> protonium_ammo = ITEMS.register("protonium_ammo", ProtoniumAmmo::new);
    public static final RegistryObject<Item> electronium_ammo = ITEMS.register("electronium_ammo", ElectroniumAmmo::new);
}
