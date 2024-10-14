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

    public static final GasRegistryObject<Gas> MANA_GAS  = GAS.register("mana_gas", 0x65d1ff);
    public static final GasRegistryObject<Gas> NEUTRON = GAS.register("neutron",0x404259);
    public static final GasRegistryObject<Gas> OVERCHARGED_NEUTRON = GAS.register("overcharged_neutron",0x6A00DD);

}
