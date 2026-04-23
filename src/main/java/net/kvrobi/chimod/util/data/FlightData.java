package net.kvrobi.chimod.util.data;

public class FlightData {
    public boolean isHovering = false;
    public boolean isGliding = false;
    public int maxEnergy = 500;
    public int currentEnergy = 500;
    ///I need to lock speed in place, for gliding
    public FlightData() {
        maxEnergy = 500;
        currentEnergy = 500;
    }
    public FlightData(int max) {
        maxEnergy = max;
        currentEnergy = max;
    }
    public void setCurrentEnergy(int value) {
        currentEnergy = Math.clamp(value, 0, maxEnergy);
    }
    public void recharge(int value) {
        setCurrentEnergy(currentEnergy + value);
    }
    public void consume(int value) {
        setCurrentEnergy(currentEnergy - value);
    }
    public int getCurrentEnergy() {
        return currentEnergy;
    }
    public int getMaxEnergy() {
        return maxEnergy;
    }

}
