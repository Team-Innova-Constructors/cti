package com.hoshino.cti.register;

import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.infuse.InfuseType;
import mekanism.common.registration.impl.GasDeferredRegister;
import mekanism.common.registration.impl.GasRegistryObject;
import mekanism.common.registration.impl.InfuseTypeDeferredRegister;
import mekanism.common.registration.impl.InfuseTypeRegistryObject;

import static com.hoshino.cti.cti.MOD_ID;

public class ctiChemical {
    public static final GasDeferredRegister GAS = new GasDeferredRegister(MOD_ID);
    public static final InfuseTypeDeferredRegister INFUSE =new InfuseTypeDeferredRegister(MOD_ID);
    public static final GasRegistryObject<Gas> FLUORINE  = GAS.register("fluorine", 0xadc739);
    public static final GasRegistryObject<Gas> NEUTRON = GAS.register("neutron",0x404259);

}
