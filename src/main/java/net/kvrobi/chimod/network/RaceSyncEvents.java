package net.kvrobi.chimod.network;

import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.Race;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.repository.Pack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class RaceSyncEvents {
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if(event.getEntity() instanceof ServerPlayer serverPlayer) {
            Race race = serverPlayer.getData(ModAttachments.RACE_DATA.get()).getRace();
            PacketDistributor.sendToPlayer(serverPlayer, new RaceSyncPayload(race, serverPlayer.getId()));
        }
    }
    @SubscribeEvent
    public static void onPlayerR(PlayerEvent.PlayerRespawnEvent event) {
        if(event.getEntity() instanceof ServerPlayer serverPlayer) {
            Race race = serverPlayer.getData(ModAttachments.RACE_DATA.get()).getRace();
            PacketDistributor.sendToPlayer(serverPlayer, new RaceSyncPayload(race, serverPlayer.getId()));
        }
    }
    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if(event.getEntity() instanceof ServerPlayer serverPlayer) {
            Race race = serverPlayer.getData(ModAttachments.RACE_DATA.get()).getRace();
            PacketDistributor.sendToPlayer(serverPlayer, new RaceSyncPayload(race, serverPlayer.getId()));
        }
    }
    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        if(event.getTarget() instanceof ServerPlayer serverTarget && event.getEntity() instanceof ServerPlayer serverPlayer) {
            Race race = serverTarget.getData(ModAttachments.RACE_DATA.get()).getRace();
            PacketDistributor.sendToPlayer(serverPlayer, new RaceSyncPayload(race, serverTarget.getId()));
        }
    }
}
