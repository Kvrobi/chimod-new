package net.kvrobi.chimod.util.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class FluidData {

    public static final Codec<FluidData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("wasUnderChiFluid").forGetter(d -> d.wasUnderChiFluid),
                    Codec.INT.fieldOf("lastTickInChiFluid").forGetter(d -> d.lastTickInChiFluid)
            ).apply(instance, FluidData::new)
    );

    public boolean wasUnderChiFluid = false;
    public int lastTickInChiFluid = -100;

    public FluidData() { }
    public FluidData(boolean wasUnder, int lastTick) {
        wasUnderChiFluid = wasUnder;
        lastTickInChiFluid = lastTick;
    }
}
