package com.hoshino.cti.register;

import com.hoshino.cti.Blocks.unipolarBudding;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ctiBlock {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, "cti");
    public static final RegistryObject<Block> unipolar_magnet_budding = BLOCK.register("unipolar_magnet_budding", () -> new unipolarBudding(BlockBehaviour.Properties.of(Material.AMETHYST).lightLevel((BlockStateBase)-> 15).sound(SoundType.AMETHYST).randomTicks()));
    public static final RegistryObject<Block> unipolar_magnet = BLOCK.register("unipolar_magnet", () -> new AmethystClusterBlock(7,3, BlockBehaviour.Properties.of(Material.AMETHYST).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((BlockStateBase)-> 5)));

}
