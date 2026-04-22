package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.Race;
import net.minecraft.client.Minecraft;
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
                        net.minecraft.client.player.LocalPlayer localPlayer = Minecraft.getInstance().player;

                        // 2. If the packet is for US, update immediately (even if the world is still loading!)
                        if (localPlayer != null && localPlayer.getId() == payload.entityId()) {
                            localPlayer.getData(ModAttachments.RACE_DATA.get()).setRace(payload.race());
                        }
                        // 3. If the packet is for ANOTHER player, safely find them in the world
                        else if (Minecraft.getInstance().level != null) {
                            net.minecraft.world.entity.Entity entity = Minecraft.getInstance().level.getEntity(payload.entityId());
                            if (entity instanceof net.minecraft.world.entity.player.Player otherPlayer) {
                                otherPlayer.getData(ModAttachments.RACE_DATA.get()).setRace(payload.race());
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