package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.Race;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.RaceData;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;

public record RaceSelectPayload(Race race) implements CustomPacketPayload {
    public static final Type<RaceSelectPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "race_select"));
    public static final StreamCodec<RegistryFriendlyByteBuf, RaceSelectPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(Race.RACE_CODEC), RaceSelectPayload::race,
    RaceSelectPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handleData(final RaceSelectPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                // 1. Update your mod's internal race data
                RaceData raceData = player.getData(ModAttachments.RACE_DATA);
                raceData.setRace(data.race());

                // 2. Sync race to client for logic
                PacketDistributor.sendToPlayer(player, new RaceSyncPayload(data.race(), player.getId()));

                // 3. SEAMLESS TRANSITION (The "Magic" part)
                // If the race is EAGLE, we tell the client to force-load the avatar
                if (data.race() == Race.EAGLE) {
                    // Since setAvatar is a CLIENT-side method in AvatarManager,
                    // you actually need to call this in your RaceSyncPayload handler
                    // on the Client, not here on the Server.
                }
            }
        });
    }
}

