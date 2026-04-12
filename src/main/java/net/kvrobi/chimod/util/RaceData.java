package net.kvrobi.chimod.util;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class RaceData {
    private Race race = Race.HUMAN;

    public Race getRace() {return race;}
    public void setRace(Race race) {this.race = race;}

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



