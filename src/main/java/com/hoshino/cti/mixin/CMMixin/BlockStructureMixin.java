package com.hoshino.cti.mixin.CMMixin;

import com.hoshino.cti.util.IMixinMachineTile;
import fr.frinn.custommachinery.api.machine.MachineTile;
import fr.frinn.custommachinery.common.util.BlockStructure;
import fr.frinn.custommachinery.common.util.PartialBlockState;
import fr.frinn.custommachinery.common.util.ingredient.IIngredient;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(value = BlockStructure.class,remap = false)
public abstract class BlockStructureMixin {
    @Shadow
    public abstract Map<BlockPos, IIngredient<PartialBlockState>> getBlocks(Direction direction);

    /**
     * @author EtSH_C2H6S
     * @reason 让多方快检测时将能量接口的坐标添加到机器里。
     */
    @Overwrite
    public boolean match(LevelReader world, BlockPos machinePos, Direction machineFacing) {
        Map<BlockPos, IIngredient<PartialBlockState>> blocks = getBlocks(machineFacing);
        BlockPos.MutableBlockPos worldPos = new BlockPos.MutableBlockPos();
        BlockEntity machine = world.getBlockEntity(machinePos);
        for(BlockPos pos : blocks.keySet()) {
            IIngredient<PartialBlockState> ingredient = blocks.get(pos);
            worldPos.set(pos.getX() + machinePos.getX(), pos.getY() + machinePos.getY(), pos.getZ() + machinePos.getZ());
            BlockInWorld info = new BlockInWorld(world, worldPos, false);
            if(ingredient.getAll().stream().noneMatch(state -> state.test(info))){
                if (machine instanceof MachineTile machineTile){
                    IMixinMachineTile tile = (IMixinMachineTile) machineTile;
                    tile.cti$clearCachedPorts();
                }
                return false;
            }
            BlockEntity port = world.getBlockEntity(worldPos);
            if (pos!=machinePos&&machine instanceof MachineTile machineTile&&port instanceof MachineTile portTile) {
                IMixinMachineTile tile = (IMixinMachineTile) machineTile;

                if (portTile.getMachine().getId().toString().contains("energy_input")) {
                    tile.cti$addEnergyInput(pos);
                }
                if (portTile.getMachine().getId().toString().contains("energy_output")) {
                    tile.cti$addEnergyOutput(pos);
                }


                if (portTile.getMachine().getId().toString().contains("item_input")) {
                    tile.cti$addItemInput(pos);
                }
                if (portTile.getMachine().getId().toString().contains("item_output")) {
                    tile.cti$addItemOutput(pos);
                }


                if (portTile.getMachine().getId().toString().contains("fluid_input")) {
                    tile.cti$addFluidInput(pos);
                }
                if (portTile.getMachine().getId().toString().contains("fluid_output")) {
                    tile.cti$addFluidOutput(pos);
                }


                if (portTile.getMachine().getId().toString().contains("chemical_input")) {
                    tile.cti$addChemicalInput(pos);
                }
                if (portTile.getMachine().getId().toString().contains("chemical_output")) {
                    tile.cti$addChemicalOutput(pos);
                }

            }
        }
        return true;
    }
}
