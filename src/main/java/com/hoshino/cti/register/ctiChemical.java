package com.hoshino.cti.register;

import mekanism.api.chemical.gas.Gas;
import mekanism.common.registration.impl.GasDeferredRegister;
import mekanism.common.registration.impl.GasRegistryObject;

import static com.hoshino.cti.cti.MOD_ID;
public class ctiChemical {
    public static final GasDeferredRegister GAS = new GasDeferredRegister(MOD_ID);

    public static final GasRegistryObject<Gas> MANA_GAS  = GAS.register("mana_gas", 0x65d1ff);
    public static final GasRegistryObject<Gas> NEUTRON = GAS.register("neutron",0x404259);
    public static final GasRegistryObject<Gas> OVERCHARGED_NEUTRON = GAS.register("overcharged_neutron",0x6A00DD);
    public static final GasRegistryObject<Gas> COLD_SODIUM = GAS.register("cold_sodium",0x0000FF);

}
