package net.kvrobi.chimod.network;

import net.kvrobi.chimod.util.Race;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record RaceSyncPayload(Race race) implements CustomPacketPayload {

    public static final Type<RaceSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("your_mod_id", "race_sync"));

    // StreamCodec defines how to write/read the data to the network buffer
    public static final StreamCodec<RegistryFriendlyByteBuf, RaceSyncPayload> STREAM_CODEC = StreamCodec.composite(
            // Use fromCodec for Enums to keep it consistent with your NBT saving
            ByteBufCodecs.fromCodec(Race.RACE_CODEC), RaceSyncPayload::race,
            RaceSyncPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}