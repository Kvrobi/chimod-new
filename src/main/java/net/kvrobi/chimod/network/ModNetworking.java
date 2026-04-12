package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.data.ClientRaceData;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModNetworking {

    // Keep it static, but remove @SubscribeEvent if you want to be fully manual

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
        registrar.playToClient(
                RaceSyncPayload.TYPE,
                RaceSyncPayload.STREAM_CODEC,
                (payload, context) -> {
                    // This runs on the CLIENT thread
                    context.enqueueWork(() -> {
                        // Update the client-side data
                        ClientRaceData.setLocalRace(payload.race());
                    });
                }
        );
    }
}