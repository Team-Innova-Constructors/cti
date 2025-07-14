package com.hoshino.cti.register;

import com.hoshino.cti.L2.ExtremeDamageReduce;
import com.hoshino.cti.L2.OracleTrait;
import com.hoshino.cti.L2.PurifyTrait;
import dev.xkmc.l2hostility.content.config.TraitConfig;
import dev.xkmc.l2hostility.init.L2Hostility;
import dev.xkmc.l2library.repack.registrate.util.entry.RegistryEntry;
import net.minecraft.ChatFormatting;

public class CtiHostilityTrait {
    public static final RegistryEntry<PurifyTrait> PURIFYTRAIT = L2Hostility.REGISTRATE.regTrait("purify", () -> new PurifyTrait(ChatFormatting.AQUA), (rl) -> new TraitConfig(rl, 100, 30, 1, 400)).desc("The Mob who have this trait will ignore Harmful effects and increase 10% damage each trait level").lang("Purify").register();
    public static final RegistryEntry<OracleTrait> ORACLE_TRAIT = L2Hostility.REGISTRATE.regTrait("oracle", () -> new OracleTrait(ChatFormatting.GREEN), (rl) -> new TraitConfig(rl, 100, 30, 1, 1)).desc("The Mob who have this trait will clear positive effects of targets attacked.").lang("Oracle").register();
    public static final RegistryEntry<ExtremeDamageReduce> EXTREME_DAMAGE_REDUCE = L2Hostility.REGISTRATE.regTrait("extreme_damage_reduce", () -> new ExtremeDamageReduce(ChatFormatting.GREEN), (rl) -> new TraitConfig(rl, 100, 30, 1, 10000)).desc("Grant 60% damage reduction from Extreme Damage.").lang("Extreme DR").register();
    public static void register() {
    }
}
