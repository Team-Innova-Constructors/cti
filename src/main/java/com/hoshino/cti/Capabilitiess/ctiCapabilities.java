package com.hoshino.cti.Capabilitiess;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ctiCapabilities {
    //抗性（后续可补充）
    public static final Capability<IElectricShielding> ELECTRIC_SHIELDING = CapabilityManager.get(new CapabilityToken<>() {});
}
