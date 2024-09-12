package com.hoshino.cti.register;

import com.hoshino.cti.Blocks.Machine.PlasmaInfuserBlock;
import com.hoshino.cti.Blocks.OverdenseGlacioStone;
import com.hoshino.cti.Blocks.unipolarBudding;
import com.hoshino.cti.Blocks.BlockEntity.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.world.block.CrystalClusterBlock;


public class ctiBlock {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, "cti");
    public static final RegistryObject<Block> unipolar_magnet_budding = BLOCK.register("unipolar_magnet_budding", () -> new unipolarBudding(BlockBehaviour.Properties.of(Material.AMETHYST).lightLevel((BlockStateBase)-> 15).sound(SoundType.AMETHYST).randomTicks()));
    public static final RegistryObject<Block> unipolar_magnet = BLOCK.register("unipolar_magnet", () -> new CrystalClusterBlock(SoundEvents.AMETHYST_BLOCK_CHIME,7,3, BlockBehaviour.Properties.of(Material.AMETHYST).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(0.5F).lightLevel((BlockStateBase)-> 5)));
    public static final RegistryObject<Block> overdense_glacio_stone = BLOCK.register("overdense_glacio_stone", () -> new OverdenseGlacioStone(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.ANCIENT_DEBRIS).randomTicks()));
    public static final RegistryObject<BaseEntityBlock> plasma_Infuser =BLOCK.register("plasma_infuser",()->new PlasmaInfuserBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)));
}
