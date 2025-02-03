package com.hoshino.cti.Modifier.Replace;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.definition.module.build.VolatileDataToolHook;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class imprison extends Modifier implements VolatileDataToolHook {
    @Override
    public void addVolatileData(IToolContext iToolContext, ModDataNBT modDataNBT) {
        modDataNBT.addSlots(SlotType.ABILITY,-10000);
        modDataNBT.addSlots(SlotType.DEFENSE,-10000);
        modDataNBT.addSlots(SlotType.UPGRADE,-10000);
    }
}
