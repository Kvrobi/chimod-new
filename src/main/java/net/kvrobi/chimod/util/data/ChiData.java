package net.kvrobi.chimod.util.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.items.ItemStackHandler;

public class ChiData {
    private int energy = 0;
    private static final int MAX_ENERGY = 1500;
    private boolean isGolden;


    public ChiData() {
        this.energy = 0;
        this.isGolden = false;
    }
    private final ItemStackHandler inventory = new ItemStackHandler(1);

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public boolean getGolden() {
        return isGolden;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int value) {
        this.energy = Math.clamp(value, 0, MAX_ENERGY);
        this.isGolden = this.energy >= 700;
    }

    public void addEnergy(int value) {
        setEnergy(this.energy + value);
    }
    public void consumeEnergy(int value) {
        setEnergy(this.energy - value);
    }

    public static final Codec<ChiData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("energy").forGetter(ChiData::getEnergy),
                    Codec.BOOL.fieldOf("isGolden").forGetter(ChiData::getGolden)
            ).apply(instance, ChiData::new));

    public ChiData(int energy, boolean isGolden) {
        this.energy = energy;
        this.isGolden = isGolden;
    }
    public ChiData(int energy){
        this.energy = 0;
        setEnergy(energy);
    }

    public CompoundTag serializeNBT(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("energy", this.energy);
        tag.put("inventory", this.inventory.serializeNBT(registries));
        return tag;
    }

    public void deserializeNBT(HolderLookup.Provider registries, CompoundTag tag) {
        this.energy = tag.getInt("energy");
        if (tag.contains("inventory")) {
            this.inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        }
    }


}
