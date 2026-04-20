package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.Race;
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
                    context.enqueueWork(() -> {
                        if (context.player() != null) {
                            // Sync the local race data
                            context.player().getData(ModAttachments.RACE_DATA.get()).setRace(payload.race());

                            // FORCE FIGURA TO EQUIP THE AVATAR
                            if (payload.race() == Race.EAGLE) {
                                // This is where you call the Figura API or AvatarManager
                                // to set the avatar for the player's UUID.
                                // Note: You will need to convert your local resource to NBT
                                // or use LocalAvatarLoader.loadLocalAvatar(Path).
                            }
                        }
                    });
                }
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