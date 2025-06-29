package com.hoshino.cti.Modifier.Base;

import com.c2h6s.etshtinker.Modifiers.modifiers.EtSTBaseModifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class OxygenConsumeModifier extends EtSTBaseModifier {
    public boolean hasOxygen(IToolStackView tool, ModifierEntry modifier){
        return false;
    }
    public void consumeOxygen(IToolStackView tool, ModifierEntry modifier){

    }
}
