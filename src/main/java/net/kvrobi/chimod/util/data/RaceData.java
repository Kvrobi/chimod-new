package net.kvrobi.chimod.util.data;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.kvrobi.chimod.util.Race;
//import net.kvrobi.chimod.client.renderer.race.custom.HumanProxy;

import static net.kvrobi.chimod.util.Race.HUMAN;


public class RaceData {
    private Race race;

    public RaceData() { this.race = HUMAN; }
    public RaceData(Race race) {
        this.race = race;
    }
    public Race getRace() {return race;}
    public String getRaceString() {return raceToString();}
    public void setRace(Race race) {this.race = race;}

    private String raceToString() {
        switch (race) {
            case HUMAN:
                return "human";
            case EAGLE:
                return "eagle";
            case LION:
                return "lion";
            case CROCODILE:
                return "crocodile";
            case BEAR:
                return "bear";
            case WOLF:
                return "wolf";
            case FOX:
                return "fox";
            case RAVEN:
                return "crow";
            default:
                return "human";

        }
    }

    public static final Codec<RaceData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.xmap(
                            name -> {
                                try { return Race.valueOf(name.toUpperCase()); }
                                catch (IllegalArgumentException e) { return Race.HUMAN; }
                            },

                            Race::name
                    ).fieldOf("race").forGetter(RaceData::getRace)
            ).apply(instance, RaceData::new)
    );
}



