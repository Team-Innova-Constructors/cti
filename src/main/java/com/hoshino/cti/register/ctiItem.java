package com.hoshino.cti.register;

import com.hoshino.cti.Entity.vehicles.rocketTier5;
import com.hoshino.cti.Items.PlanetGuiItem;
import com.hoshino.cti.Items.TooltipedBlockItem;
import com.hoshino.cti.Items.Vehicle.RocketItemTier5;
import com.hoshino.cti.Items.compressedSingularityItem;
import com.hoshino.cti.Items.pncMinigunAmmo.ElectroniumAmmo;
import com.hoshino.cti.Items.pncMinigunAmmo.ProtoniumAmmo;
import com.hoshino.cti.Items.pncMinigunAmmo.UltraDenseAmmo;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.ad_astra.AdAstra;
import earth.terrarium.ad_astra.common.item.vehicle.RocketItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static earth.terrarium.ad_astra.common.registry.ModItems.ITEM_GROUP;
import static earth.terrarium.ad_astra.common.registry.ModItems.VEHICLES;

public class ctiItem {
    public ctiItem(){}
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "cti");
    public static final ResourcefulRegistry<Item> ASTRAITEM  = ResourcefulRegistries.create(Registry.ITEM, "cti");
    public static final ResourcefulRegistry<Item> VEHICLES = ResourcefulRegistries.create(ASTRAITEM);
    public static final RegistryObject<Item> test = ITEMS.register("test", ( ) -> new Item(new Item.Properties().tab(com.hoshino.cti.register.ctiTab.MATERIALS)));
    public static final RegistryObject<Item> ultradense_ammo = ITEMS.register("ultradense_ammo", UltraDenseAmmo::new);
    public static final RegistryObject<Item> protonium_ammo = ITEMS.register("protonium_ammo", ProtoniumAmmo::new);
    public static final RegistryObject<Item> electronium_ammo = ITEMS.register("electronium_ammo", ElectroniumAmmo::new);
    public static final RegistryEntry<RocketItemTier5<rocketTier5>> TIER_5_ROCKET = VEHICLES.register("tier_5_rocket", () -> new RocketItemTier5<>(ctiEntity.TIER_5_ROCKET.get(), 5, new Item.Properties().tab(ITEM_GROUP).stacksTo(1).fireResistant().tab(ctiTab.MIXC)));
    public static final RegistryObject<Item> astra_tablet_1 = ITEMS.register("astra_tablet_1",()->new PlanetGuiItem(new Item.Properties().tab(ITEM_GROUP).stacksTo(1).fireResistant().tab(ctiTab.MIXC),1));
    public static final RegistryObject<Item> astra_tablet_2 = ITEMS.register("astra_tablet_2",()->new PlanetGuiItem(new Item.Properties().tab(ITEM_GROUP).stacksTo(1).fireResistant().tab(ctiTab.MIXC),2));
    public static final RegistryObject<Item> astra_tablet_3 = ITEMS.register("astra_tablet_3",()->new PlanetGuiItem(new Item.Properties().tab(ITEM_GROUP).stacksTo(1).fireResistant().tab(ctiTab.MIXC),3));
    public static final RegistryObject<Item> astra_tablet_4 = ITEMS.register("astra_tablet_4",()->new PlanetGuiItem(new Item.Properties().tab(ITEM_GROUP).stacksTo(1).fireResistant().tab(ctiTab.MIXC),4));
    public static final RegistryObject<Item> astra_tablet_5 = ITEMS.register("astra_tablet_5",()->new PlanetGuiItem(new Item.Properties().tab(ITEM_GROUP).stacksTo(1).fireResistant().tab(ctiTab.MIXC),5));
    public static final RegistryObject<Item> compressed_singularity = ITEMS.register("compressed_singularity",()->new compressedSingularityItem(new Item.Properties().tab(ITEM_GROUP).stacksTo(64).fireResistant().tab(ctiTab.MIXC)));



    public static final RegistryObject<BlockItem> unipolar_magnet = ITEMS.register("unipolar_magnet",()-> new BlockItem(ctiBlock.unipolar_magnet.get(), new Item.Properties().tab(ctiTab.MIXC)));
    public static final RegistryObject<BlockItem> unipolar_magnet_budding = ITEMS.register("unipolar_magnet_budding",()-> new BlockItem(ctiBlock.unipolar_magnet_budding.get(), new Item.Properties().tab(ctiTab.MIXC)));
    public static final RegistryObject<BlockItem> overdense_glacio_stone = ITEMS.register("overdense_glacio_stone",()-> new BlockItem(ctiBlock.overdense_glacio_stone.get(), new Item.Properties().tab(ctiTab.MIXC)));
    public static final RegistryObject<BlockItem> atmosphere_extractor = ITEMS.register("atmosphere_extractor",()-> new TooltipedBlockItem(ctiBlock.atmosphere_extractor.get(), new Item.Properties().tab(ctiTab.MACHINE), List.of(
            Component.translatable("cti.tooltip.item.atmosphere_extractor").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.fe_max").append(": 7.5 MFE").withStyle(ChatFormatting.RED),
            Component.translatable("cti.tooltip.item.fe_consumption").append(": 750 kFE/t").withStyle(ChatFormatting.RED)
    )));
    public static final RegistryObject<BlockItem> atmosphere_condensator = ITEMS.register("atmosphere_condensator",()-> new TooltipedBlockItem(ctiBlock.atmosphere_condensator.get(), new Item.Properties().tab(ctiTab.MACHINE), List.of(
            Component.translatable("cti.tooltip.item.atmosphere_condensator").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.fe_max").append(": 7.5 MFE").withStyle(ChatFormatting.RED),
            Component.translatable("cti.tooltip.item.fe_consumption").append(": 750 kFE/t").withStyle(ChatFormatting.RED)
    )));
    public static final RegistryObject<BlockItem> quantum_miner = ITEMS.register("quantum_miner",()-> new TooltipedBlockItem(ctiBlock.quantum_miner.get(), new Item.Properties().tab(ctiTab.MACHINE), List.of(
            Component.translatable("cti.tooltip.item.quantum_miner").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.fe_max").append(": 2.14 GFE").withStyle(ChatFormatting.RED),
            Component.translatable("cti.tooltip.item.fe_consumption").append(": 537 MFE/t").withStyle(ChatFormatting.RED)
    )));
    public static final RegistryObject<BlockItem> quantum_miner_advanced = ITEMS.register("quantum_miner_advanced",()-> new TooltipedBlockItem(ctiBlock.quantum_miner_advanced.get(), new Item.Properties().tab(ctiTab.MACHINE), List.of(
            Component.translatable("cti.tooltip.item.quantum_miner").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.quantum_miner_advanced").withStyle(ChatFormatting.LIGHT_PURPLE),
            Component.translatable("cti.tooltip.item.fe_max").append(": 2.14 GFE").withStyle(ChatFormatting.RED),
            Component.translatable("cti.tooltip.item.fe_consumption").append(": 1.61 GFE/t").withStyle(ChatFormatting.RED)

    )));
}
