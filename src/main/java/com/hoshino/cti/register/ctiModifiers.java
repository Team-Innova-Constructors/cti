package com.hoshino.cti.register;

import com.hoshino.cti.Modifier.*;
import com.hoshino.cti.Modifier.Armor.*;
import com.hoshino.cti.Modifier.Contributors.*;
import com.hoshino.cti.Modifier.Developer.*;
import com.hoshino.cti.Modifier.Replace.*;
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
    public static final StaticModifier<TimeToJudge> timetojudge= MODIFIERS.register("timetojudge", TimeToJudge::new);
    public static final StaticModifier<NetherFire> netherfire= MODIFIERS.register("netherfire", NetherFire::new);
    public static final StaticModifier<CelestialLight> celestiallight= MODIFIERS.register("celestiallight", CelestialLight::new);
    public static final StaticModifier<archangelwings> archangelwings= MODIFIERS.register("archangelwings", archangelwings::new);
    public static final StaticModifier<GoldSimulate> gold_simulate= MODIFIERS.register("gold_simulate", GoldSimulate::new);
    public static final StaticModifier<EndSlayer> end_slayer= MODIFIERS.register("end_slayer", EndSlayer::new);
    public static final StaticModifier<StellarBlade> stellar_blade= MODIFIERS.register("stellar_blade", StellarBlade::new);
    public static final StaticModifier<All> all= MODIFIERS.register("all", All::new);
    public static final StaticModifier<StressModifier> stress= MODIFIERS.register("stress", StressModifier::new);
    public static final StaticModifier<fieryCapable> fiery_capable= MODIFIERS.register("fiery_capable", fieryCapable::new);
    public static final StaticModifier<Improve> improve= MODIFIERS.register("improve", Improve::new);
    public static final StaticModifier<Hardcore> hardcore= MODIFIERS.register("hardcore", Hardcore::new);
    public static final StaticModifier<Mutation> mutation= MODIFIERS.register("mutation", Mutation::new);
    public static final StaticModifier<ScorchInduced> scorch_induced= MODIFIERS.register("scorch_induced", ScorchInduced::new);
    public static final StaticModifier<FrozenInduced> frozen_induced= MODIFIERS.register("frozen_induced", FrozenInduced::new);
    public static final StaticModifier<IonizeIndused> ionize_induced= MODIFIERS.register("ionize_induced", IonizeIndused::new);
    public static final StaticModifier<PressureIndused> pressure_induced= MODIFIERS.register("pressure_induced", PressureIndused::new);
    public static final StaticModifier<Disorder> disorder= MODIFIERS.register("disorder", Disorder::new);
    public static final StaticModifier<StellarBlessing> stellar_blessing= MODIFIERS.register("stellar_blessing", StellarBlessing::new);
    public static final StaticModifier<DragonsWifu> dragons_wifu= MODIFIERS.register("dragons_wifu", DragonsWifu::new);
    public static final StaticModifier<ArmorDragonsWifu> armor_dragons_wifu= MODIFIERS.register("armor_dragons_wifu", ArmorDragonsWifu::new);
    public static final StaticModifier<TrinityCurse> trinitycurse= MODIFIERS.register("trinitycurse", TrinityCurse::new);
    public static final StaticModifier<TrinityBlessing> trinityblessing= MODIFIERS.register("trinityblessing", TrinityBlessing::new);
    public static final StaticModifier<StrengthWill> strengthwill= MODIFIERS.register("strengthwill", StrengthWill::new);
    public static final StaticModifier<Breeding> breeding= MODIFIERS.register("breeding", Breeding::new);
    public static final StaticModifier<Eros> eros= MODIFIERS.register("eros", Eros::new);
    public static final StaticModifier<ararar> ararar= MODIFIERS.register("ararar", ararar::new);
    public static final StaticModifier<NerfedDash> nerfed_dash= MODIFIERS.register("nerfed_dash", NerfedDash::new);
    public static final StaticModifier<Emp> emp= MODIFIERS.register("emp", Emp::new);
    public static final StaticModifier<Transmigration> transmigration= MODIFIERS.register("transmigration", Transmigration::new);
    public static final StaticModifier<Eventually> eventually= MODIFIERS.register("eventually", Eventually::new);
    public static final StaticModifier<Trauma> trauma= MODIFIERS.register("trauma", Trauma::new);
    public static final StaticModifier<Arrogance> ARROGANCE_STATIC_MODIFIER= MODIFIERS.register("arrogance", Arrogance::new);
    public static final StaticModifier<NerfColossal> nerf_colossal= MODIFIERS.register("nerf_colossal", NerfColossal::new);
    public static final StaticModifier<SharpBlade> SHARP_BLADE_STATIC_MODIFIER= MODIFIERS.register("sharpblade", SharpBlade::new);
    public static final StaticModifier<Cosmopolitan> COSMOPOLITAN_STATIC_MODIFIER= MODIFIERS.register("cosmopolitan", Cosmopolitan::new);
    public static final StaticModifier<Infinity> INFINITY_STATIC_MODIFIER= MODIFIERS.register("infinity", Infinity::new);
    public static final StaticModifier<DeepAndDeep> DEEP_AND_DEEP_STATIC_MODIFIER= MODIFIERS.register("deepanddeep", DeepAndDeep::new);
    public static final StaticModifier<DeepOceanEcho> DeepOceanEcho= MODIFIERS.register("deepoceanecho", DeepOceanEcho::new);
    public static final StaticModifier<Decoying> DECOYING_STATIC_MODIFIER= MODIFIERS.register("decoying", Decoying::new);
    public static final StaticModifier<StrengthWill> STRENGTH_WILL= MODIFIERS.register("strengthWill", StrengthWill::new);
    public static final StaticModifier<DominateModifier> DOMINATE_MODIFIER_REPLACEMENT= MODIFIERS.register("dominate_replace", DominateModifier::new);
    public static final StaticModifier<LebegusProtection> LebegusProtection= MODIFIERS.register("lebegusprotection", LebegusProtection::new);
    public static final StaticModifier<RoxyLightning> RoxyLightning= MODIFIERS.register("roxylightning", RoxyLightning::new);
    public static final StaticModifier<Abelisures> Abelisures= MODIFIERS.register("abelisures", Abelisures::new);
    public static final StaticModifier<AlienMatter> AlienMatter= MODIFIERS.register("alienmatter", AlienMatter::new);
    public static final StaticModifier<OverwriteSoulDevouring> OVERWRITE_SOUL_DEVOURING_STATIC_MODIFIER= MODIFIERS.register("overwritesouldevouring", OverwriteSoulDevouring::new);
    public static final StaticModifier<PressureLoaded> pressure_loaded= MODIFIERS.register("pressure_loaded", PressureLoaded::new);

}
