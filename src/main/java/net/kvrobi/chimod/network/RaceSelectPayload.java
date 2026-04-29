package net.kvrobi.chimod.network;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.Race;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.data.RaceData;
import net.kvrobi.chimod.world.RaceExtraHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;

public record RaceSelectPayload(Race race) implements CustomPacketPayload {
    public static final Type<RaceSelectPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "race_select"));
    public static final StreamCodec<RegistryFriendlyByteBuf, RaceSelectPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.idMapper(i -> Race.values()[i], Race::ordinal), RaceSelectPayload::race,
            RaceSelectPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handleData(final RaceSelectPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                RaceData raceData = player.getData(ModAttachments.RACE_DATA);
                raceData.setRace(data.race());
                CompoundTag persistentData = player.getPersistentData();
                CompoundTag modData = persistentData.getCompound(Player.PERSISTED_NBT_TAG);
                if (!modData.getBoolean("chimod_has_chosen_race")) {
                    modData.putBoolean("chimod_has_chosen_race", true);
                }

                RaceExtraHandler.applyRaceAttributes(player);

                PacketDistributor.sendToPlayer(player, new RaceSyncPayload(data.race(), player.getId()));

                var flightData = player.getData(ModAttachments.FLIGHT_DATA.get());

                if (flightData != null) {
                    if (data.race() == Race.EAGLE) {
                        flightData.maxEnergy = 750;
                        flightData.currentEnergy = 750;
                    } else if (data.race() == Race.RAVEN) {
                        flightData.maxEnergy = 500;
                        flightData.currentEnergy = 500;
                    }

                    PacketDistributor.sendToPlayer(player, new FlightSyncPayload(flightData.currentEnergy, flightData.maxEnergy));

                }

            }
        });
    }
}

