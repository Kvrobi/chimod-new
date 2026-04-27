package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.world.gui.RaceSelectionMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record OpenRaceMenuPayload() implements CustomPacketPayload {
    public static final Type<OpenRaceMenuPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "open_race_menu"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenRaceMenuPayload> CODEC = StreamCodec.unit(new OpenRaceMenuPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handleData(final OpenRaceMenuPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                player.openMenu(new SimpleMenuProvider(
                        (id, inv, p) -> new RaceSelectionMenu(id, inv),
                        Component.literal("Select Your Race")
                ));
            }
        });
    }
}
