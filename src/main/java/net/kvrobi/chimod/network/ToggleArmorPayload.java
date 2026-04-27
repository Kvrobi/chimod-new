package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.item.armor.custom.ChiArmor;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ToggleArmorPayload() implements CustomPacketPayload {
    public static final Type<ToggleArmorPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "toggle_armor"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ToggleArmorPayload> CODEC = StreamCodec.unit(new ToggleArmorPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
    public static void handleData(final ToggleArmorPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);

            if (stack.getItem() instanceof ChiArmor chiArmor) {
                chiArmor.toggle(player, stack);
            }
        });
    }

}