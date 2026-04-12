package net.kvrobi.chimod.world;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.item.armor.custom.ChiArmor;
import net.kvrobi.chimod.item.custom.ChiOrbItem;
import net.kvrobi.chimod.item.custom.ChiWeapon;
import net.kvrobi.chimod.network.ChiSyncPayload;
import net.kvrobi.chimod.util.ChiData;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.world.inventory.ChiMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber
public class ChiHandler {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;

        ChiData data = player.getData(ModAttachments.CHI_ENERGY);
        int oldEnergy = data.getEnergy();

        ItemStack chesplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack mainHand = player.getMainHandItem();
        ServerLevel level = (ServerLevel) player.level();
        // Logic: Drain 1 energy per second if weapon is active
        if (player.level().getGameTime() % 20 == 0) {
            if (mainHand.getItem() instanceof ChiWeapon weapon && weapon.isActive(mainHand)) {
                data.consumeEnergy(1);
            }
            if(chesplate.getItem() instanceof ChiArmor armor && armor.isActive(chesplate) ) {
                data.consumeEnergy(1);
            }
        }
        if (data.getEnergy() <= 0) {
            if (mainHand.getItem() instanceof ChiWeapon weapon && weapon.isActive(mainHand)) {
                weapon.deactivate(mainHand, player, level);
                player.displayClientMessage(Component.literal("Your Chi has been depleted!").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD), true);
            }
            if(chesplate.getItem() instanceof ChiArmor armor && armor.isActive(chesplate) ) {
                armor.deactivate(chesplate, player, level);
                player.displayClientMessage(Component.literal("Your Chi has been depleted!").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD), true);
            }
        }

        // SYNC CHECK: If the energy value changed this tick, send a packet!
        handleMenuRefill(player, data);

        if (data.getEnergy() != oldEnergy) {
            PacketDistributor.sendToPlayer((ServerPlayer) player, new ChiSyncPayload(data.getEnergy()));
        }
    }

    private static void handleMenuRefill(Player player, ChiData data) {

        if (player.containerMenu instanceof ChiMenu chiMenu) {
            ItemStack orbStack = chiMenu.getSlot(0).getItem();
            if (orbStack.getItem() instanceof ChiOrbItem && data.getEnergy() <= 0) {
                data.addEnergy(300);
                orbStack.shrink(1);
            }
        }
    }
}