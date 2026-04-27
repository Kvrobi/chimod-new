package net.kvrobi.chimod.world;

import net.kvrobi.chimod.item.armor.custom.ChiArmor;
import net.kvrobi.chimod.item.custom.ChiOrbItem;
import net.kvrobi.chimod.item.custom.ChiWeapon;
import net.kvrobi.chimod.network.ChiSyncPayload;
import net.kvrobi.chimod.network.RaceSyncPayload;
import net.kvrobi.chimod.util.Race;
import net.kvrobi.chimod.util.data.ChiData;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.world.gui.RaceSelectionMenu;
import net.kvrobi.chimod.world.inventory.ChiMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber
public class ChiHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            CompoundTag persistentData = player.getPersistentData();
            CompoundTag modData = persistentData.getCompound(Player.PERSISTED_NBT_TAG);
            if (!modData.getBoolean("chimod_has_chosen_race")) {
                player.openMenu(new SimpleMenuProvider(
                        (id, inv, p) -> new RaceSelectionMenu(id, inv),
                        Component.literal("Select your Tribe")
                ));

                persistentData.put(Player.PERSISTED_NBT_TAG, modData);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;

        ChiData data = player.getData(ModAttachments.CHI_ENERGY);
        int oldEnergy = data.getEnergy();

        ItemStack chesplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack mainHand = player.getMainHandItem();
        ServerLevel level = (ServerLevel) player.level();
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

        handleMenuRefill(player, data);

        if (data.getEnergy() != oldEnergy) {
            PacketDistributor.sendToPlayer((ServerPlayer) player, new ChiSyncPayload(data.getEnergy()));
        }

        flightLogic(player);



    }

    private static void flightLogic(Player player) {
        if (player.isCreative() || player.isSpectator()) return;

        Race race = player.getData(ModAttachments.RACE_DATA.get()).getRace();
        var data = player.getData(ModAttachments.FLIGHT_DATA.get());

        if (race == Race.EAGLE || race == Race.RAVEN) {

            if (!player.getAbilities().flying /*player.isInWater() || player.isUnderWater() || player.onGround()*/ && data.getCurrentEnergy() < data.getMaxEnergy()) {
                data.recharge(0.75f);
            }


            if (data.getCurrentEnergy() > 0) {
                if (!player.getAbilities().mayfly) {
                    player.getAbilities().mayfly = true;
                    player.onUpdateAbilities();
                }

                if (player.getAbilities().flying) {
                    data.consume(1);
                }

            } else {
                if (player.getAbilities().mayfly || player.getAbilities().flying) {
                    player.getAbilities().mayfly = false;
                    player.getAbilities().flying = false;
                    player.onUpdateAbilities();
                }
            }

            if (player.level().getGameTime() % 200 == 0) {
                net.neoforged.neoforge.network.PacketDistributor.sendToPlayer((net.minecraft.server.level.ServerPlayer) player,
                        new net.kvrobi.chimod.network.FlightSyncPayload(data.getCurrentEnergy(), data.getMaxEnergy()));
            }
        } else {
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


}