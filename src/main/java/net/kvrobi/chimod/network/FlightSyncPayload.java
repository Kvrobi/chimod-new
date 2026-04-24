package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.ModAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record FlightSyncPayload(float currentEn, float maxEn) implements CustomPacketPayload {

    public static final Type<FlightSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "flight_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, FlightSyncPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, FlightSyncPayload::currentEn,
            ByteBufCodecs.FLOAT, FlightSyncPayload::maxEn,
            FlightSyncPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleData(final FlightSyncPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.flow().isClientbound()) {
                var player = Minecraft.getInstance().player;
                if (player != null) {
                    // FORCE the client to update its math to match the Server!
                    var flightData = player.getData(ModAttachments.FLIGHT_DATA.get());
                    flightData.currentEnergy = data.currentEn();
                    flightData.maxEnergy = data.maxEn();
                }
            }
        });
    }
}
