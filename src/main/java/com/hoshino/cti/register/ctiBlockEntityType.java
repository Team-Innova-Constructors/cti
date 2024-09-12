package com.hoshino.cti.register;

import com.hoshino.cti.Blocks.BlockEntity.plasmaInfuserEntity;
import com.hoshino.cti.cti;
import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ctiBlockEntityType {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, cti.MOD_ID);
    public static final BlockEntityType<plasmaInfuserEntity> plasma_infuser;

    private static <T extends BlockEntity> BlockEntityType<T> register(String p_58957_, BlockEntityType.Builder<T> p_58958_) {
        Type<?> $$2 = Util.fetchChoiceType(References.BLOCK_ENTITY, p_58957_);
        return (BlockEntityType) Registry.register(Registry.BLOCK_ENTITY_TYPE, p_58957_, p_58958_.build($$2));
    }

    static {
        plasma_infuser =register("plasma_infuser", BlockEntityType.Builder.of(plasmaInfuserEntity::new, ctiBlock.plasma_Infuser.get()));
    }
}
