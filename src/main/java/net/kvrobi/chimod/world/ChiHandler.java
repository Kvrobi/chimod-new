package net.kvrobi.chimod.world;

import net.kvrobi.chimod.item.armor.custom.ChiArmor;
import net.kvrobi.chimod.item.custom.ChiOrbItem;
import net.kvrobi.chimod.item.custom.ChiWeapon;
import net.kvrobi.chimod.network.ChiSyncPayload;
import net.kvrobi.chimod.network.OpenRaceMenuPayload;
import net.kvrobi.chimod.util.Race;
import net.kvrobi.chimod.util.data.ChiData;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.world.inventory.ChiMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
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

        flightLogic(player);
        //FLightData



    }

    private static void flightLogic(Player player) {
        // CRITICAL SAFETY: Ignore actual Creative or Spectator mode players
        // We don't want to drain their energy or randomly revoke their built-in flight!
        if (player.isCreative() || player.isSpectator()) return;

        Race race = player.getData(ModAttachments.RACE_DATA.get()).getRace();
        var data = player.getData(ModAttachments.FLIGHT_DATA.get());

        if (race == Race.EAGLE || race == Race.RAVEN) {

            // 1. Recharge while walking on the ground
            if (player.onGround() && data.getCurrentEnergy() < data.getMaxEnergy()) {
                data.recharge(0.1f);
            }

            // 2. Flight Logic
            if (data.getCurrentEnergy() > 0) {
                // If they have energy, give them permission to double-jump to fly!
                if (!player.getAbilities().mayfly) {
                    player.getAbilities().mayfly = true;
                    player.onUpdateAbilities(); // This magically syncs the permission to the Client!
                }

                // If Vanilla Minecraft says they are actively flying, drain the energy
                if (player.getAbilities().flying) {
                    data.consume(1); // Drains 20 energy per second
                }

            } else {
                // Out of energy! Revoke flight and force them to fall
                if (player.getAbilities().mayfly || player.getAbilities().flying) {
                    player.getAbilities().mayfly = false;
                    player.getAbilities().flying = false;
                    player.onUpdateAbilities(); // Syncs the fall to the Client!
                }
            }
        } else {
            // Failsafe: If a player switches from Eagle back to Human, revoke flight
            if (player.getAbilities().mayfly) {
                player.getAbilities().mayfly = false;
                player.getAbilities().flying = false;
                player.onUpdateAbilities();
            }
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

    private void handleOpenRaceMenu(Minecraft mc) {
        PacketDistributor.sendToServer(new OpenRaceMenuPayload());
    }
}