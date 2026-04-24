package net.kvrobi.chimod.util.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class FlightData {

    public static final Codec<FlightData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("isHovering").forGetter(d -> d.isHovering),
                    Codec.BOOL.fieldOf("isGliding").forGetter(d -> d.isGliding),
                    Codec.FLOAT.fieldOf("currentEnergy").forGetter(d -> d.currentEnergy),
                    Codec.FLOAT.fieldOf("maxEnergy").forGetter(d -> d.maxEnergy)
            ).apply(instance, FlightData::new)
    );
    public boolean isHovering = false;
    public boolean isGliding = false;
    public float maxEnergy = 500;
    public float currentEnergy = 500;
    ///I need to lock speed in place, for gliding
    public FlightData() {
        maxEnergy = 500;
        currentEnergy = 500;
    }
    public FlightData(boolean isH, boolean isG, float current, float max) {
        isHovering = isH;
        isGliding = isG;
        currentEnergy = current;
        maxEnergy = max;

    }
    public void setCurrentEnergy(float value) {
        currentEnergy = Math.clamp(value, 0, maxEnergy);
    }
    public void recharge(float value) {
        setCurrentEnergy(currentEnergy + value);
    }
    public void consume(float value) {
        setCurrentEnergy(currentEnergy - value);
    }
    public float getCurrentEnergy() {
        return currentEnergy;
    }
    public float getMaxEnergy() {
        return maxEnergy;
    }

}
