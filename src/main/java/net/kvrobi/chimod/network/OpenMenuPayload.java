package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.network.chat.Component;
import net.kvrobi.chimod.world.inventory.ChiMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record OpenMenuPayload() implements CustomPacketPayload {
    public static final Type<OpenMenuPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "open_menu"));

    // Codecs tell Minecraft how to send this over the internet (it's empty because we send no extra data)
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenMenuPayload> CODEC = StreamCodec.unit(new OpenMenuPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }

    // This runs on the SERVER when the packet arrives
    public static void handleData(final OpenMenuPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                // Using a MenuProvider that explicitly points to your CHI_MENU type
                player.openMenu(new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.translatable("menu.kvrobichimod.chi_title");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
                        return new ChiMenu(id, inv);
                    }
                });
            }
        });
    }



}