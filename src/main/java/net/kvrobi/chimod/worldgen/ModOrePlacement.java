package net.kvrobi.chimod.worldgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {
    public static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

    public static List<PlacementModifier> rareButHugePlacement(int chance, int count, PlacementModifier height) {
        return List.of(RarityFilter.onAverageOnceEvery(chance), CountPlacement.of(count), InSquarePlacement.spread(), height, BiomeFilter.biome()
        );
    }


    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier height) {
        return orePlacement(CountPlacement.of(count), height);
    }

    public static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier height) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), height);
    }

}
