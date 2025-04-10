package com.hoshino.cti.register;

import com.hoshino.cti.L2.PurifyTrait;
import dev.xkmc.l2hostility.content.config.TraitConfig;
import dev.xkmc.l2hostility.init.L2Hostility;
import dev.xkmc.l2library.repack.registrate.util.entry.RegistryEntry;
import net.minecraft.ChatFormatting;

public class ctiHostilityTrait {
    public static final RegistryEntry<PurifyTrait> PURIFYTRAIT = L2Hostility.REGISTRATE.regTrait("purify", () -> new PurifyTrait(ChatFormatting.AQUA), (rl) -> new TraitConfig(rl, 100, 50, 1, 100)).desc("The Mob who have this trait will ignore Harmful effects and increase 10% damage each trait level").lang("Purify").register();

    public static void register() {
    }
}
