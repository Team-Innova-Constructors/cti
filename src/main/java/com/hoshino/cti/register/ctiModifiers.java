package com.hoshino.cti.register;

import com.hoshino.cti.Modifier.*;
import com.hoshino.cti.Modifier.Armor.*;
import com.hoshino.cti.Modifier.Contributors.*;
import com.hoshino.cti.Modifier.slot.*;
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
    public static final StaticModifier<timetojudge> timetojudge= MODIFIERS.register("timetojudge", timetojudge::new);
    public static final StaticModifier<netherfire> netherfire= MODIFIERS.register("netherfire", netherfire::new);
    public static final StaticModifier<celestiallight> celestiallight= MODIFIERS.register("celestiallight", celestiallight::new);
    public static final StaticModifier<archangelwings> archangelwings= MODIFIERS.register("archangelwings", archangelwings::new);
    public static final StaticModifier<GoldSimulate> gold_simulate= MODIFIERS.register("gold_simulate", GoldSimulate::new);
    public static final StaticModifier<EndSlayer> end_slayer= MODIFIERS.register("end_slayer", EndSlayer::new);
    public static final StaticModifier<StellarBlade> stellar_blade= MODIFIERS.register("stellar_blade", StellarBlade::new);
    public static final StaticModifier<All> all= MODIFIERS.register("all", All::new);
    public static final StaticModifier<StressModifier> stress= MODIFIERS.register("stress", StressModifier::new);
    public static final StaticModifier<fieryCapable> fiery_capable= MODIFIERS.register("fiery_capable", fieryCapable::new);
    public static final StaticModifier<improve> improve= MODIFIERS.register("improve", improve::new);
    public static final StaticModifier<hardcore> hardcore= MODIFIERS.register("hardcore", hardcore::new);
    public static final StaticModifier<mutation> mutation= MODIFIERS.register("mutation", mutation::new);
    public static final StaticModifier<ScorchInduced> scorch_induced= MODIFIERS.register("scorch_induced", ScorchInduced::new);
    public static final StaticModifier<FrozenInduced> frozen_induced= MODIFIERS.register("frozen_induced", FrozenInduced::new);
    public static final StaticModifier<IonizeIndused> ionize_induced= MODIFIERS.register("ionize_induced", IonizeIndused::new);
    public static final StaticModifier<PressureIndused> pressure_induced= MODIFIERS.register("pressure_induced", PressureIndused::new);
    public static final StaticModifier<Disorder> disorder= MODIFIERS.register("disorder", Disorder::new);
}
