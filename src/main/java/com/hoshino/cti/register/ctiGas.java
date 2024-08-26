package com.hoshino.cti.register;

import mekanism.api.chemical.gas.Gas;
import mekanism.common.registration.impl.GasDeferredRegister;
import mekanism.common.registration.impl.GasRegistryObject;

import static com.marth7th.solidarytinker.solidarytinker.MOD_ID;

public class ctiGas {
    public static final GasDeferredRegister GAS = new GasDeferredRegister(MOD_ID);
    public static final GasRegistryObject<Gas> FLUORINE  = GAS.register("fluorine", 0xadc739);

}
