package net.kvrobi.chimod.util;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
//import net.kvrobi.chimod.client.renderer.race.custom.HumanProxy;

import static net.kvrobi.chimod.util.Race.HUMAN;


public class RaceData {
    private Race race = HUMAN;

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
                    Codec.STRING.fieldOf("race").forGetter(d -> d.race.name())
            ).apply(instance, name -> {
                RaceData data = new RaceData();
                data.setRace(Race.fromString(name));
                return data;
            })
    );
}



