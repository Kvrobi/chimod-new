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
        Race race = player.getData(ModAttachments.RACE_DATA.get()).getRace();
        if(race == Race.EAGLE || race == Race.RAVEN) {
            var data = player.getData(ModAttachments.FLIGHT_DATA.get());
            if (player.onGround()) {
                if (data.isGliding || data.isHovering) {
                    data.isGliding = false;
                    data.isHovering = false;
                    if (player.level().isClientSide()) {
                        net.neoforged.neoforge.network.PacketDistributor.sendToServer(new net.kvrobi.chimod.network.UpdateFlightPayload(false, false));
                    }
                }

                // Recharge energy quickly while walking!
                if (data.getCurrentEnergy() < data.getMaxEnergy()) {
                    data.recharge(5);
                }
                return; // Skip the rest of the flight physics
            }

            // --- 2. HOVER MECHANICS ---
            if (data.isHovering) {
                if (data.getCurrentEnergy() > 0) {
                    // Lock Y to exactly 0.
                    player.setDeltaMovement(player.getDeltaMovement().x, 0.0, player.getDeltaMovement().z);
                    player.fallDistance = 0;

                    // Hovering takes a lot of energy!
                    data.consume(4);
                } else {
                    // Out of energy! Cancel flight so they fall!
                    data.isHovering = false;
                }
            }

            // --- 3. GLIDE SPEED LOCK ---
            else if (data.isGliding) {
                if (data.getCurrentEnergy() > 0) {

                    // Get the direction the camera is facing
                    net.minecraft.world.phys.Vec3 look = player.getLookAngle();

                    // The locked horizontal speed multiplier
                    double forwardSpeed = 0.6;
                    // The locked downward fall speed
                    double lockedY = -0.05;

                    // LOCK THE SPEED: Force X and Z to move constantly forward based on camera angle
                    player.setDeltaMovement(look.x * forwardSpeed, lockedY, look.z * forwardSpeed);
                    player.fallDistance = 0;

                    // Gliding is efficient, consumes less energy
                    data.consume(1);
                } else {
                    data.isGliding = false;
                }
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