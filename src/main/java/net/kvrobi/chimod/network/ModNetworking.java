package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.Race;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.HashMap;
import java.util.Map;

public class ModNetworking {

    public static final Map<Integer, Race> PENDING_RACES = new HashMap<>();

    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(ChiMod.MOD_ID);
        registrar.playToClient(ChiSyncPayload.TYPE, ChiSyncPayload.STREAM_CODEC, ChiSyncPayload::handleData);

        registrar.playToServer(
                OpenMenuPayload.TYPE,
                OpenMenuPayload.CODEC,
                OpenMenuPayload::handleData
        );

        registrar.playToServer(
                ToggleArmorPayload.TYPE,
                ToggleArmorPayload.CODEC,
                ToggleArmorPayload::handleData
        );

        registrar.playToServer(
                RequestRaceSyncPayload.TYPE,
                RequestRaceSyncPayload.CODEC,
                (payload, context) -> {
                    context.enqueueWork(() -> {
                        if (context.player() instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                            net.kvrobi.chimod.util.Race race = serverPlayer.getData(net.kvrobi.chimod.util.ModAttachments.RACE_DATA.get()).getRace();
                            net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer, new RaceSyncPayload(race, serverPlayer.getId()));
                        }
                    });
                }
        );

        registrar.playToServer(
                UpdateFlightPayload.TYPE,
                UpdateFlightPayload.CODEC,
                (payload, context) -> {
                    context.enqueueWork(() -> {
                        if (context.player() instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                            var data = serverPlayer.getData(net.kvrobi.chimod.util.ModAttachments.FLIGHT_DATA.get());
                            data.isHovering = payload.isHovering();
                            data.isGliding = payload.isGliding();
                        }
                    });
                }
        );

        registrar.playToClient(
                RaceSyncPayload.TYPE,
                RaceSyncPayload.STREAM_CODEC,
                (payload, context) -> {
                    context.enqueueWork(() -> {
                        net.minecraft.client.player.LocalPlayer localPlayer = net.minecraft.client.Minecraft.getInstance().player;
                        net.minecraft.client.multiplayer.ClientLevel level = net.minecraft.client.Minecraft.getInstance().level;

                        if (localPlayer != null && localPlayer.getId() == payload.entityId()) {
                            localPlayer.getData(ModAttachments.RACE_DATA.get()).setRace(payload.race());
                        } else if (level != null && level.getEntity(payload.entityId()) instanceof net.minecraft.world.entity.player.Player otherPlayer) {
                            otherPlayer.getData(ModAttachments.RACE_DATA.get()).setRace(payload.race());
                        }
                    });
                }
        );

        registrar.playToClient(
                FlightSyncPayload.TYPE,
                FlightSyncPayload.CODEC,
                FlightSyncPayload::handleData
        );

        registrar.playToServer(
                OpenRaceMenuPayload.TYPE,
                OpenRaceMenuPayload.CODEC,
                OpenRaceMenuPayload::handleData
        );
        registrar.playToServer(
                RaceSelectPayload.TYPE,
                RaceSelectPayload.CODEC,
                RaceSelectPayload::handleData
        );
    }
}