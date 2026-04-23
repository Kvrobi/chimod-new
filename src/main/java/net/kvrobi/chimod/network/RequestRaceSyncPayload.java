package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record RequestRaceSyncPayload() implements CustomPacketPayload {
    public static final Type<RequestRaceSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "request_race_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, RequestRaceSyncPayload> CODEC = StreamCodec.of(
            (buf, payload) -> {},
            buf -> new RequestRaceSyncPayload()
    );

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}