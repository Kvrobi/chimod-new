package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record UpdateFlightPayload(boolean isHovering, boolean isGliding) implements CustomPacketPayload {
    public static final Type<UpdateFlightPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "update_flight"));

    public static final StreamCodec<RegistryFriendlyByteBuf, UpdateFlightPayload> CODEC = StreamCodec.of(
            (buf, payload) -> {
                buf.writeBoolean(payload.isHovering());
                buf.writeBoolean(payload.isGliding());
            },
            buf -> new UpdateFlightPayload(buf.readBoolean(), buf.readBoolean())
    );

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
