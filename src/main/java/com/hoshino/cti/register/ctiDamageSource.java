package com.hoshino.cti.register;

import net.minecraft.world.damagesource.DamageSource;

public class ctiDamageSource extends DamageSource {
    public ctiDamageSource(String p_19333_) {
        super(p_19333_);
    }
    public static final ctiDamageSource IONIZED = new ctiDamageSource("cti.ionized");
    public static final ctiDamageSource SCORCH = new ctiDamageSource("cti.scorch");
    public static final ctiDamageSource FROZEN = new ctiDamageSource("cti.frozen");
}
