package com.hoshino.cti.Event;

import com.hoshino.cti.Entity.Projectiles.MeteorEntity;
import com.hoshino.cti.Event.ModEvents.MeteorSpawnEvent;
import com.hoshino.cti.register.ctiEffects;
import com.hoshino.cti.util.CommonUtil;
import com.hoshino.cti.util.DimensionConstants;
import com.xiaoyue.tinkers_ingenuity.content.basic.entity.projectile.ShurikenEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.WorldData;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;

import java.util.Collection;
import java.util.Random;

import static com.hoshino.cti.Items.RecipeTestItem.discoverNewPacks;
import static com.hoshino.cti.Items.RecipeTestItem.reloadPacks;

public class ServerEvent {
    public ServerEvent(){
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
        MinecraftForge.EVENT_BUS.addListener(this::onEntityTravelDimension);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST,this::onServerStart);
    }

    private void onServerStart(ServerStartedEvent event) {
        CommonUtil.Reload(event.getServer());
    }

    private void onEntityTravelDimension(EntityTravelToDimensionEvent event) {
        if (event.getEntity() instanceof ShurikenEntity entity){
            entity.discard();
            event.setCanceled(true);
        }
    }

    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        if(event.player.getLevel() instanceof ServerLevel level &&level.dimension().equals(DimensionConstants.MOON)&&level.getGameTime()%2000==0) {
            Player player = event.player;
            if (Math.abs(player.getX()) + Math.abs(player.getY()) > 750) {
                Random random = new Random();
                if (random.nextInt(10) == 0) {
                    Vec2 pos = new Vec2((float) (player.getX() + random.nextFloat() * 192), (float) (player.getZ() + random.nextFloat() * 192));
                    MeteorSpawnEvent event1 = new MeteorSpawnEvent(new Vec3(pos.x, 350, pos.y));
                    MinecraftForge.EVENT_BUS.post(event1);
                    if (!event1.isCanceled()) {
                        MeteorEntity entity = new MeteorEntity(level, pos.x, 350, pos.y, new Vec3(random.nextFloat() * 0.5, random.nextFloat() * 2.5 - 1.5, random.nextFloat() * 0.5));
                        entity.setExplosionPower((byte) (random.nextInt(125) + 25));
                        level.addFreshEntity(entity);
                    }
                }
            }
        }
    }

}
