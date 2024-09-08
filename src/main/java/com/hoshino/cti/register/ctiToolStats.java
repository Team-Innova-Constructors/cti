package com.hoshino.cti.register;

import com.hoshino.cti.cti;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStatId;
import slimeknights.tconstruct.library.tools.stat.ToolStats;


public class ctiToolStats {
    public static final FloatToolStat ELECTRIC_RESISTANCE = (FloatToolStat) ToolStats.register(new FloatToolStat(name("electric_resistance"), -3135232, 0.0F, 0.0F, 3));

    private static ToolStatId name(String name) {
        return new ToolStatId(cti.MOD_ID, name);
    }
}
