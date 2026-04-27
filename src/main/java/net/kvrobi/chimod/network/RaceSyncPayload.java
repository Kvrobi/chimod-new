package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.Race;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record RaceSyncPayload(Race race, int entityId) implements CustomPacketPayload {

    public static final Type<RaceSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "race_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, RaceSyncPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.idMapper(i -> Race.values()[i], Race::ordinal), RaceSyncPayload::race,
            ByteBufCodecs.INT, RaceSyncPayload::entityId,
            RaceSyncPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}