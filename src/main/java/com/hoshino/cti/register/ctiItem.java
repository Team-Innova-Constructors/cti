package com.hoshino.cti.register;

import cofh.core.util.helpers.AugmentDataHelper;
import cofh.thermal.lib.common.ThermalFlags;
import cofh.thermal.lib.item.AugmentItem;
import com.hoshino.cti.Entity.vehicles.rocketTier5;
import com.hoshino.cti.Items.*;
import com.hoshino.cti.Items.MekUpgrades.AdvancedUpgrade;
import com.hoshino.cti.Items.Vehicle.RocketItemTier5;
import com.hoshino.cti.Items.Wine.Unknown_Wine;
import com.hoshino.cti.Items.ingots.uriel_ingot;
import com.hoshino.cti.Items.pncMinigunAmmo.ElectroniumAmmo;
import com.hoshino.cti.Items.pncMinigunAmmo.ProtoniumAmmo;
import com.hoshino.cti.Items.pncMinigunAmmo.UltraDenseAmmo;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import mekanism.api.Upgrade;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import umpaz.brewinandchewin.BrewinAndChewin;
import umpaz.brewinandchewin.common.item.BoozeItem;

import java.util.List;

import static cofh.core.init.CoreFlags.getFlag;
import static cofh.lib.util.constants.NBTTags.*;
import static cofh.thermal.lib.common.ThermalFlags.FLAG_UPGRADE_AUGMENTS;
import static earth.terrarium.ad_astra.common.registry.ModItems.ITEM_GROUP;
import static umpaz.brewinandchewin.common.registry.BCItems.TANKARD;

public class ctiItem {
    public static String FLAG_MACHINE_AUGMENTS = "machine_augments";
    public static String FLAG_DYNAMO_AUGMENTS = "dynamo_augments";
    public static String FLAG_AREA_AUGMENTS = "area_augments";
    public ctiItem(){}
    public static Item.Properties tankard_drinkItem() {
        return (new Item.Properties()).craftRemainder(TANKARD.get()).stacksTo(16).tab(ctiTab.FOOD);
    }
    public static Item.Properties bottle_drinkItem() {
        return (new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(ctiTab.FOOD);
    }
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "cti");
    public static final ResourcefulRegistry<Item> ASTRAITEM  = ResourcefulRegistries.create(Registry.ITEM, "cti");
    public static final ResourcefulRegistry<Item> VEHICLES = ResourcefulRegistries.create(ASTRAITEM);
    public static final RegistryObject<Item> test = ITEMS.register("test", ( ) -> new Item(new Item.Properties().tab(ctiTab.MATERIALS)));
    public static final RegistryObject<Item> ultradense_ammo = ITEMS.register("ultradense_ammo", UltraDenseAmmo::new);
    public static final RegistryObject<Item> protonium_ammo = ITEMS.register("protonium_ammo", ProtoniumAmmo::new);
    public static final RegistryObject<Item> magic_crystal = ITEMS.register("magic_crystal",()-> new Item(new Item.Properties().tab(ctiTab.MATERIALS)));
    public static final RegistryObject<Item> enriched_mana = ITEMS.register("enriched_mana",()-> new Item(new Item.Properties().tab(ctiTab.MATERIALS)));
    public static final RegistryObject<Item> uriel_ingot = ITEMS.register("uriel_ingot",()-> new uriel_ingot(new Item.Properties().tab(ctiTab.MATERIALS)));
    public static final RegistryObject<Item> stellar_manyullyn = ITEMS.register("stellar_manyullyn",()-> new TooltipedItem(new Item.Properties().tab(ctiTab.MATERIALS),List.of(Component.translatable("cti.tooltip.item.etsh").withStyle(ChatFormatting.LIGHT_PURPLE))));
    public static final RegistryObject<Item> invert_hoshino = ITEMS.register("invert_hoshino",()-> new TooltipedItem(new Item.Properties().tab(ctiTab.MATERIALS),List.of(Component.translatable("cti.tooltip.item.invert_hoshino").withStyle(ChatFormatting.DARK_PURPLE))));
    public static final RegistryObject<Item> roxy_ingot = ITEMS.register("roxy_ingot",()-> new TooltipedItem(new Item.Properties().tab(ctiTab.MATERIALS),List.of(Component.translatable("cti.tooltip.item.roxy_ingot").withStyle(ChatFormatting.BLUE))));
    public static final RegistryObject<Item> omniscient_gold_ingot = ITEMS.register("omniscient_gold_ingot",()-> new TooltipedItem(new Item.Properties().tab(ctiTab.MATERIALS),List.of(Component.translatable("cti.tooltip.item.omniscient_gold_ingot.desc1").withStyle(ChatFormatting.DARK_PURPLE),Component.translatable("cti.tooltip.item.omniscient_gold_ingot.desc2").withStyle(ChatFormatting.DARK_PURPLE))));
    public static final RegistryObject<Item> electronium_ammo = ITEMS.register("electronium_ammo", ElectroniumAmmo::new);
    public static final RegistryEntry<RocketItemTier5<rocketTier5>> TIER_5_ROCKET = VEHICLES.register("tier_5_rocket", () -> new RocketItemTier5<>(ctiEntity.TIER_5_ROCKET.get(), 5, new Item.Properties().tab(ITEM_GROUP).stacksTo(1).fireResistant().tab(ctiTab.MIXC)));
    public static final RegistryObject<Item> astra_tablet_5 = ITEMS.register("astra_tablet_5",()->new PlanetGuiItem(new Item.Properties().tab(ITEM_GROUP).stacksTo(1).fireResistant().tab(ctiTab.MIXC),5));
    public static final RegistryObject<Item> compressed_singularity = ITEMS.register("compressed_singularity",()->new compressedSingularityItem(new Item.Properties().tab(ITEM_GROUP).stacksTo(64).fireResistant().tab(ctiTab.MIXC)));
    public static final RegistryObject<Item> recipe_test_item = ITEMS.register("recipe_test_item", RecipeTestItem::new);
    public static final RegistryObject<Item> flat_tablet = ITEMS.register("flat_tablet", FlatWorldTabletItem::new);
    public static final RegistryObject<Item> flat_tablet_day = ITEMS.register("flat_tablet_day", FlatWorldDayTabletItem::new);

    public static final RegistryObject<BlockItem> unipolar_magnet = ITEMS.register("unipolar_magnet",()-> new BlockItem(ctiBlock.unipolar_magnet.get(), new Item.Properties().tab(ctiTab.MIXC)));
    public static final RegistryObject<BlockItem> unipolar_magnet_budding = ITEMS.register("unipolar_magnet_budding",()-> new BlockItem(ctiBlock.unipolar_magnet_budding.get(), new Item.Properties().tab(ctiTab.MIXC)));
    public static final RegistryObject<BlockItem> overdense_glacio_stone = ITEMS.register("overdense_glacio_stone",()-> new BlockItem(ctiBlock.overdense_glacio_stone.get(), new Item.Properties().tab(ctiTab.MIXC)));
    public static final RegistryObject<BlockItem> ultra_dense_hydride_ore = ITEMS.register("ultra_dense_hydride_ore",()-> new BlockItem(ctiBlock.ultra_dense_hydride_ore.get(), new Item.Properties().tab(ctiTab.MIXC)));
    public static final RegistryObject<BlockItem> alloy_centrifuge = ITEMS.register("alloy_centrifuge",()-> new TooltipedBlockItem(ctiBlock.alloy_centrifuge_block.get(), new Item.Properties().tab(ctiTab.MACHINE),List.of(
            Component.translatable("cti.tooltip.item.alloy_centrifuge").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.alloy_centrifuge2").withStyle(ChatFormatting.GOLD),
            Component.translatable("cti.tooltip.item.alloy_centrifuge3").withStyle(ChatFormatting.RED)
    )));
    public static final RegistryObject<BlockItem> atmosphere_extractor = ITEMS.register("atmosphere_extractor",()-> new TooltipedBlockItem(ctiBlock.atmosphere_extractor.get(), new Item.Properties().tab(ctiTab.MACHINE), List.of(
            Component.translatable("cti.tooltip.item.atmosphere_extractor").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.fe_max").append(": 75 MFE").withStyle(ChatFormatting.RED),
            Component.translatable("cti.tooltip.item.fe_consumption").append(": 750 kFE/t").withStyle(ChatFormatting.RED)
    )));
    public static final RegistryObject<BlockItem> atmosphere_condensator = ITEMS.register("atmosphere_condensator",()-> new TooltipedBlockItem(ctiBlock.atmosphere_condensator.get(), new Item.Properties().tab(ctiTab.MACHINE), List.of(
            Component.translatable("cti.tooltip.item.atmosphere_condensator").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.fe_max").append(": 75 MFE").withStyle(ChatFormatting.RED),
            Component.translatable("cti.tooltip.item.fe_consumption").append(": 750 kFE/t").withStyle(ChatFormatting.RED)
    )));
    public static final RegistryObject<BlockItem> quantum_miner = ITEMS.register("quantum_miner",()-> new TooltipedBlockItem(ctiBlock.quantum_miner.get(), new Item.Properties().tab(ctiTab.MACHINE), List.of(
            Component.translatable("cti.tooltip.item.quantum_miner").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.fe_max").append(": 2.14 GFE").withStyle(ChatFormatting.RED),
            Component.translatable("cti.tooltip.item.fe_consumption").append(": 134 MFE/t").withStyle(ChatFormatting.RED)
    )));
    public static final RegistryObject<BlockItem> quantum_miner_advanced = ITEMS.register("quantum_miner_advanced",()-> new TooltipedBlockItem(ctiBlock.quantum_miner_advanced.get(), new Item.Properties().tab(ctiTab.MACHINE), List.of(
            Component.translatable("cti.tooltip.item.quantum_miner").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.quantum_miner_advanced").withStyle(ChatFormatting.LIGHT_PURPLE),
            Component.translatable("cti.tooltip.item.fe_max").append(": 2.14 GFE").withStyle(ChatFormatting.RED),
            Component.translatable("cti.tooltip.item.fe_consumption").append(": 536 MFE/t").withStyle(ChatFormatting.RED)
    )));
    public static final RegistryObject<BlockItem> reactor_neutron_collector = ITEMS.register("reactor_neutron_collector",()-> new TooltipedBlockItem(ctiBlock.reactor_neutron_collector.get(), new Item.Properties().tab(ctiTab.MACHINE), List.of(
            Component.translatable("cti.tooltip.item.reactor_neutron_collector").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.reactor_neutron_collector2").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.reactor_neutron_collector3").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.reactor_neutron_collector4").withStyle(ChatFormatting.YELLOW),
            Component.translatable("cti.tooltip.item.fe_max").append(": 500 MFE").withStyle(ChatFormatting.RED),
            Component.translatable("cti.tooltip.item.fe_generate").append(": 100 kFE/t").withStyle(ChatFormatting.RED)
    )));
    public static final RegistryObject<BlockItem> sodium_cooler = ITEMS.register("sodium_cooler",()-> new TooltipedBlockItem(ctiBlock.sodium_cooler_block.get(), new Item.Properties().tab(ctiTab.MACHINE), List.of(
            Component.translatable("cti.tooltip.item.sodium_cooler").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.sodium_cooler2").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.sodium_cooler3").withStyle(ChatFormatting.AQUA),
            Component.translatable("cti.tooltip.item.fe_max").append(": 2 GFE").withStyle(ChatFormatting.RED),
            Component.translatable("cti.tooltip.item.fe_generate").append(": 50 MFE/t").withStyle(ChatFormatting.RED)
    )));



    public static final RegistryObject<Item> advanced_speed_augment = ITEMS.register("advanced_speed_augment",()->new ThermalAugment(new Item.Properties().tab(ctiTab.MIXC), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_MACHINE)
            .mod(TAG_AUGMENT_MACHINE_POWER, 25F)
            .mod(TAG_AUGMENT_MACHINE_ENERGY, 1.2F)
            .mod(TAG_AUGMENT_RF_STORAGE, 5.0F)
            .mod(TAG_AUGMENT_RF_XFER, 20.0F)
            .build()).setShowInGroups(getFlag(FLAG_MACHINE_AUGMENTS)));
    public static final RegistryObject<Item> advanced_catalyst_augment = ITEMS.register("advanced_catalyst_augment",()->new ThermalAugment(new Item.Properties().tab(ctiTab.MIXC), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_MACHINE)
            .mod(TAG_AUGMENT_MACHINE_CATALYST, 0.25F)
            .mod(TAG_AUGMENT_MACHINE_ENERGY, 2F)
            .build()).setShowInGroups(getFlag(FLAG_MACHINE_AUGMENTS)));

    public static final RegistryObject<Item> advanced_dyano_augment = ITEMS.register("advanced_dyano_augment",()->new ThermalAugment(new Item.Properties().tab(ctiTab.MIXC), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_DYNAMO)
            .mod(TAG_AUGMENT_FLUID_STORAGE,4)
            .mod(TAG_AUGMENT_DYNAMO_THROTTLE,1)
            .mod(TAG_AUGMENT_DYNAMO_POWER, 60.0F)
            .mod(TAG_AUGMENT_DYNAMO_ENERGY, 2.5F)
            .build()).setShowInGroups(getFlag(FLAG_DYNAMO_AUGMENTS)));
    public static final RegistryObject<Item> Secondary_dyano_augment = ITEMS.register("secondary_dyano_augment",()->new ThermalAugment(new Item.Properties().tab(ctiTab.MIXC), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_DYNAMO)
            .mod(TAG_AUGMENT_FLUID_STORAGE,2)
            .mod(TAG_AUGMENT_DYNAMO_THROTTLE,1)
            .mod(TAG_AUGMENT_DYNAMO_POWER, 30.0F)
            .mod(TAG_AUGMENT_DYNAMO_ENERGY, 1.6F)
            .build()).setShowInGroups(getFlag(FLAG_DYNAMO_AUGMENTS)));

    public static final RegistryObject<Item> advanced_range_augment = ITEMS.register("advanced_range_augment",()->new ThermalAugment(new Item.Properties().tab(ctiTab.MIXC), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_AREA_EFFECT)
            .mod(TAG_AUGMENT_RADIUS, 3.0F)
            .build()).setShowInGroups(getFlag(FLAG_AREA_AUGMENTS)));

    public static final RegistryObject<Item> advanced_output_augment = ITEMS.register("advanced_output_augment",()->new ThermalAugment(new Item.Properties().tab(ctiTab.MIXC), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_MACHINE)
            .mod(TAG_AUGMENT_MACHINE_PRIMARY, 0.8F)
            .mod(TAG_AUGMENT_MACHINE_SECONDARY, 0.8F)
            .mod(TAG_AUGMENT_MACHINE_ENERGY, 1.75F)
            .build()).setShowInGroups(getFlag(FLAG_MACHINE_AUGMENTS)));
    public static final RegistryObject<Item> advanced_fluid_tank_augment = ITEMS.register("advanced_fluid_tank_augment",()->(new AugmentItem((new Item.Properties()).tab(ctiTab.MIXC), AugmentDataHelper.builder()
            .type(TAG_FLUID)
            .mod(TAG_AUGMENT_FLUID_STORAGE, 16.0F)
            .build())).setShowInGroups(ThermalFlags.getFlag(ThermalFlags.FLAG_STORAGE_AUGMENTS)));
    public static final RegistryObject<Item> UPGRADE_AUGMENTS_4 = ITEMS.register("upgrade_augment_4",()->new ThermalAugment(new Item.Properties().tab(ctiTab.MIXC), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_UPGRADE)
            .mod(TAG_AUGMENT_BASE_MOD,5)
            .build()).setShowInGroups(getFlag(FLAG_UPGRADE_AUGMENTS)));

    public static final RegistryObject<Item> UPGRADE_AUGMENTS_5 = ITEMS.register("upgrade_augment_5",()->new ThermalAugment(new Item.Properties().tab(ctiTab.MIXC), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_UPGRADE)
            .mod(TAG_AUGMENT_BASE_MOD,7)
            .build()).setShowInGroups(getFlag(FLAG_UPGRADE_AUGMENTS)));
    public static final RegistryObject<Item> UPGRADE_AUGMENTS_6 = ITEMS.register("upgrade_augment_6",()->new ThermalAugment(new Item.Properties().tab(ctiTab.MIXC), AugmentDataHelper.builder()
            .type(TAG_AUGMENT_TYPE_UPGRADE)
            .mod(TAG_AUGMENT_BASE_MOD,10)
            .build()).setShowInGroups(getFlag(FLAG_UPGRADE_AUGMENTS)));


    public static final RegistryObject<Item> upgrade_electronium  = ITEMS.register("upgrade_electronium",()->new AtmosphereUpgradeItem(3f,1.75f));
    public static final RegistryObject<Item> upgrade_violium  = ITEMS.register("upgrade_violium",()->new AtmosphereUpgradeItem(4.5f,0.5f));
    public static final RegistryObject<Item> upgrade_aetherium  = ITEMS.register("upgrade_aetherium",()->new AtmosphereUpgradeItem(10.5f,2f));


    //材料
    public static final RegistryObject<Item> nitro_supersteel = ITEMS.register("nitro_supersteel", ()->new Item(new Item.Properties().tab(ctiTab.MATERIALS)));
    public static final RegistryObject<Item> hot_supersteel = ITEMS.register("hot_supersteel", ()->new Item(new Item.Properties().tab(ctiTab.MIXC)));

    //弹射物物品
    public static final RegistryObject<Item> star_blaze = ITEMS.register("star_blaze", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> star_frozen = ITEMS.register("star_frozen", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> star_ionize = ITEMS.register("star_ionize", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> star_pressure = ITEMS.register("star_pressure", ()->new Item(new Item.Properties()));
    //吃的喝的
    public static final RegistryObject<Item> COLD_GOBBERWINE = ITEMS.register("cold_gobberwine", () -> new BoozeItem(1, 5,tankard_drinkItem().food(ctiWine.COLD_GOBBERWINE)));
    public static final RegistryObject<Item> ETHANOL_ABSOLUTE = ITEMS.register("ethanol_absolute", () -> new BoozeItem(3, 20,tankard_drinkItem().food(ctiWine.ETHANOL_ABSOLUTE)));
    public static final RegistryObject<Item> cornflower_beer = ITEMS.register("cornflower_beer", () -> new BoozeItem(1, 5,tankard_drinkItem().food(ctiWine.cornflower_beer)));
    public static final RegistryObject<Item> etbeer = ITEMS.register("etbeer", () -> new BoozeItem(3, 10,tankard_drinkItem().food(ctiWine.etbeer)));
    public static final RegistryObject<Item> xuerou_wine = ITEMS.register("xuerou_wine", () -> new BoozeItem(3, 5,tankard_drinkItem().food(ctiWine.xuerou_wine)));
    public static final RegistryObject<Item> mahoushaojiu_wine = ITEMS.register("mahoushaojiu_wine", () -> new BoozeItem(1, 10,tankard_drinkItem().food(ctiWine.mahoushaojiu_wine)));
    public static final RegistryObject<Item> UNKNOWN_WINE = ITEMS.register("unknown_wine", () -> new Unknown_Wine(tankard_drinkItem().food(ctiWine.UNKNOWN_WINE).craftRemainder(TANKARD.get())));
    public static final RegistryObject<Item> qdbeer = ITEMS.register("qdbeer", () -> new BoozeItem(1, 1,bottle_drinkItem().food(ctiWine.qdbeer)));
    public static final RegistryObject<Item> boomwine = ITEMS.register("boomwine", () -> new BoozeItem(1, 5,bottle_drinkItem().food(ctiWine.boomwine)));
    public static final RegistryObject<Item> fishbone_wine = ITEMS.register("fishbone_wine", () -> new BoozeItem(1, 1,tankard_drinkItem().food(ctiWine.fishbone_wine).craftRemainder(Items.ENCHANTED_GOLDEN_APPLE)));


    //mek高级升级
    public static final RegistryObject<Item> chroma_upgrade_speed_kit = ITEMS.register("chroma_upgrade_speed_kit", () -> new AdvancedUpgrade(12,true,List.of(Upgrade.SPEED)));
    public static final RegistryObject<Item> aetherium_upgrade_speed_kit = ITEMS.register("aetherium_upgrade_speed_kit", () -> new AdvancedUpgrade(16,true,List.of(Upgrade.SPEED)));
    public static final RegistryObject<Item> anti_upgrade_speed_kit = ITEMS.register("anti_upgrade_speed_kit", () -> new AdvancedUpgrade(20,true,List.of(Upgrade.SPEED)));
    public static final RegistryObject<Item> upgrade_double_kit = ITEMS.register("upgrade_double_kit", () -> new AdvancedUpgrade(8,false,List.of(Upgrade.SPEED,Upgrade.ENERGY)));
    public static final RegistryObject<Item> upgrade_energy_kit = ITEMS.register("upgrade_energy_kit", () -> new AdvancedUpgrade(8,false,List.of(Upgrade.ENERGY)));
    public static final RegistryObject<Item> upgrade_gas_kit = ITEMS.register("upgrade_gas_kit", () -> new AdvancedUpgrade(8,false,List.of(Upgrade.GAS)));
    public static final RegistryObject<Item> upgrade_speed_kit = ITEMS.register("upgrade_speed_kit", () -> new AdvancedUpgrade(8,false,List.of(Upgrade.SPEED)));
    public static final RegistryObject<Item> upgrade_energy_kit_2 = ITEMS.register("upgrade_energy_kit_2", () -> new AdvancedUpgrade(14,false,List.of(Upgrade.ENERGY)));
    public static final RegistryObject<Item> upgrade_energy_kit_3 = ITEMS.register("upgrade_energy_kit_3", () -> new AdvancedUpgrade(20,false,List.of(Upgrade.ENERGY)));

}
