package net.kvrobi.chimod.util.data;

import net.kvrobi.chimod.util.Race;

public class ClientRaceData {
    private static Race localRace = Race.HUMAN;

    public static void setLocalRace(Race race) {
        localRace = race;
    }

    public static Race getLocalRace() {
        return localRace;
    }
}
