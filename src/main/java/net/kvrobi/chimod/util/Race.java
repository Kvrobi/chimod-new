package net.kvrobi.chimod.util;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum Race implements StringRepresentable{
    HUMAN, LION, EAGLE, CROCODILE, BEAR, WOLF, FOX, CROW;

    // The Codec still works exactly the same
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
        // This takes "HUMAN" and turns it into "human" automatically
        return this.name().toLowerCase(Locale.ROOT);
    }
}
