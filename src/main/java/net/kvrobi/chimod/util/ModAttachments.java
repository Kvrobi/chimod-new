package net.kvrobi.chimod.util;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.data.ChiData;
import net.kvrobi.chimod.util.data.FlightData;
import net.kvrobi.chimod.util.data.FluidData;
import net.kvrobi.chimod.util.data.RaceData;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, ChiMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<ChiData>> CHI_ENERGY =
            ATTACHMENT_TYPES.register("chi_energy", () -> AttachmentType.builder(() -> new ChiData())
                    .serialize(new ChiDataSerializer())
                    .copyOnDeath()
                    .build());

    public static final Supplier<AttachmentType<RaceData>> RACE_DATA = ATTACHMENT_TYPES.register(
            "race_data",
            () -> AttachmentType.builder(() -> new RaceData())
                    .serialize(RaceData.CODEC)
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<net.neoforged.neoforge.attachment.AttachmentType<FlightData>> FLIGHT_DATA = ATTACHMENT_TYPES.register(
            "flight_data",
            () -> net.neoforged.neoforge.attachment.AttachmentType.builder(() -> new FlightData())
                    .serialize(FlightData.CODEC)
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<net.neoforged.neoforge.attachment.AttachmentType<FluidData>> FLUID_DATA = ATTACHMENT_TYPES.register(
            "fluid_data",
            () -> net.neoforged.neoforge.attachment.AttachmentType.builder(() -> new FluidData())
                    .serialize(FluidData.CODEC)
                    .build()
    );





    private static CompoundTag save(ChiData data, HolderLookup.Provider registries) {
        return data.serializeNBT(registries);
    }

    private static ChiData load(CompoundTag tag,net.neoforged.neoforge.attachment.IAttachmentHolder holder, HolderLookup.Provider registries) {
        ChiData data = new ChiData();
        data.deserializeNBT(registries, tag);
        return data;
    }

}