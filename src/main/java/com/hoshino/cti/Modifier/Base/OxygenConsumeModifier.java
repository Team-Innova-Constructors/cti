package com.hoshino.cti.Modifier.Base;

import com.c2h6s.etshtinker.Modifiers.modifiers.etshmodifieriii;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class OxygenConsumeModifier extends etshmodifieriii {
    public boolean hasOxygen(IToolStackView tool, ModifierEntry modifier){
        return false;
    }
    public void consumeOxygen(IToolStackView tool, ModifierEntry modifier){

    }
}
