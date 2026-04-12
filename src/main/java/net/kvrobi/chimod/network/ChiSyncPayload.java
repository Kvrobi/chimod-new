package net.kvrobi.chimod.network;

import io.netty.buffer.ByteBuf;
import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.ChiData;
import net.kvrobi.chimod.util.ModAttachments;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ChiSyncPayload(int energy) implements CustomPacketPayload {
    public static final Type<ChiSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "chi_sync"));

    public static final StreamCodec<ByteBuf, ChiSyncPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ChiSyncPayload::energy,
            ChiSyncPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleData(final ChiSyncPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            // This runs on the CLIENT
            if (context.player() != null) {
                context.player().setData(ModAttachments.CHI_ENERGY, new ChiData(data.energy()));
            }
        });
    }
}