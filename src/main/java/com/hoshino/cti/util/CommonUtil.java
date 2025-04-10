package com.hoshino.cti.util;

import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.WorldData;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.Iterator;

public class CommonUtil {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void Reload(MinecraftServer server) {
        LOGGER.info("Mod cti is now conducting a reload! It will take 114514 years.");
        PackRepository repository = server.getPackRepository();
        WorldData data = server.getWorldData();
        Collection<String> collection1 = repository.getSelectedIds();
        Collection<String> collection2 = discoverNewPacks(repository, data, collection1);
        reloadPacks(collection2, server);
    }

    public static void reloadPacks(Collection<String> p_138236_, MinecraftServer server) {
        server.reloadResources(p_138236_).exceptionally((p_138234_) -> {
            LOGGER.warn("Failed to execute reload", p_138234_);
            return null;
        });
    }

    public static Collection<String> discoverNewPacks(PackRepository repository, WorldData data, Collection<String> collection) {
        repository.reload();
        Collection<String> $$3 = Lists.newArrayList(collection);
        Collection<String> $$4 = data.getDataPackConfig().getDisabled();
        Iterator var5 = repository.getAvailableIds().iterator();

        while (var5.hasNext()) {
            String $$5 = (String) var5.next();
            if (!$$4.contains($$5) && !$$3.contains($$5)) {
                $$3.add($$5);
            }
        }

        return $$3;
    }
}
