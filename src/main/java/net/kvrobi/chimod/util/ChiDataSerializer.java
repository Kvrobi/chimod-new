package net.kvrobi.chimod.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.Nullable;

public class ChiDataSerializer implements IAttachmentSerializer<CompoundTag, ChiData> {

    @Override
    public ChiData read(IAttachmentHolder holder, CompoundTag tag, HolderLookup.Provider registries) {
        ChiData data = new ChiData();
        data.deserializeNBT(registries, tag);
        return data;
    }

    @Override
    public CompoundTag write(ChiData data, HolderLookup.Provider registries) {
        return data.serializeNBT(registries);
    }
}