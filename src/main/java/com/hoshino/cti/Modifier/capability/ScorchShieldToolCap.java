package com.hoshino.cti.Modifier.capability;

import com.hoshino.cti.Capabilitiess.IScorchShielding;
import com.hoshino.cti.Capabilitiess.ctiCapabilities;
import com.hoshino.cti.register.ctiToolStats;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import slimeknights.tconstruct.library.tools.capability.ToolCapabilityProvider;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.function.Supplier;

public class ScorchShieldToolCap implements IScorchShielding, ToolCapabilityProvider.IToolCapabilityProvider {
    public final Supplier<? extends IToolStackView> tool;
    public final LazyOptional<IScorchShielding> capOptional;

    public ScorchShieldToolCap(ItemStack stack, Supplier<? extends IToolStackView> toolStack) {
        tool = toolStack;
        capOptional = LazyOptional.of(() -> this);
    }

    @Override
    public <T> LazyOptional<T> getCapability(IToolStackView iToolStackView, Capability<T> capability) {
        return iToolStackView.getStats().get(ctiToolStats.SCORCH_RESISTANCE) > 0 && capability == ctiCapabilities.SCORCH_SHIELDING ? ctiCapabilities.SCORCH_SHIELDING.orEmpty(capability, this.capOptional) : LazyOptional.empty();
    }

    @Override
    public float getScorchShieldinng() {
        return tool.get().getStats().get(ctiToolStats.SCORCH_RESISTANCE);
    }
}
