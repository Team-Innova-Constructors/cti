package com.hoshino.cti.Event.ModEvents;

import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class MeteorSpawnEvent extends Event {
    public final Vec3 Pos;

    public MeteorSpawnEvent(Vec3 Pos) {
        this.Pos = Pos;
    }

    public Vec3 getPos() {
        return this.Pos;
    }
}
