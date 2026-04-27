package net.kvrobi.chimod.util;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum Race implements StringRepresentable{
    HUMAN, LION, EAGLE, CROCODILE, BEAR, WOLF, RAVEN, GORILLA, RHINO;

    public static final Codec<Race> RACE_CODEC = StringRepresentable.fromEnum(Race::values);

    public static Race fromString(String name) {
        try {
            return Race.valueOf(name.toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            return HUMAN;
        }
    }

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
