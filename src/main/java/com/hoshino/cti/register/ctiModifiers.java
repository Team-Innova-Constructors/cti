package com.hoshino.cti.register;

import com.hoshino.cti.Modifier.*;
import com.hoshino.cti.cti;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class ctiModifiers {
    /**
     * 词条的注册部分
     * 图省事所有直接把等号外部分写在一起了（
     */
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(cti.MOD_ID);
    public static final StaticModifier<ElectricProtect> electric_protect= MODIFIERS.register("electric_protect", ElectricProtect::new);
    public static final StaticModifier<ScorchProtect> scorch_protect= MODIFIERS.register("scorch_protect", ScorchProtect::new);
    public static final StaticModifier<FreezeProtect> freeze_protect= MODIFIERS.register("freeze_protect", FreezeProtect::new);
    public static final StaticModifier<PressureProtect> pressure_protect= MODIFIERS.register("pressure_protect", PressureProtect::new);
    public static final StaticModifier<SpaceSuitModifier> space_suit= MODIFIERS.register("space_suit", SpaceSuitModifier::new);
    public static final StaticModifier<GravityNormalize> gravity_normalizer= MODIFIERS.register("gravity_normalizer", GravityNormalize::new);
    public static final StaticModifier<test> test= MODIFIERS.register("test", test::new);
}
