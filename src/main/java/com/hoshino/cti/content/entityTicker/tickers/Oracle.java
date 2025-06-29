package com.hoshino.cti.content.entityTicker.tickers;

import com.hoshino.cti.content.entityTicker.EntityTicker;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class Oracle extends EntityTicker {
    @Override
    public boolean tick(int duration, int level, Entity entity) {
        if (entity instanceof LivingEntity living){
            living.activeEffects.clear();
        }
        return true;
    }
}
