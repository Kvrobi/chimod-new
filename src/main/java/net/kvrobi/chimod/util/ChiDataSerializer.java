package net.kvrobi.chimod.util;

import net.kvrobi.chimod.util.data.ChiData;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.minecraft.core.HolderLookup;

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