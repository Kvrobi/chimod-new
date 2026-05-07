package net.kvrobi.chimod.worldgen;

import net.kvrobi.chimod.ChiMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> OVERWORLD_CHI_ORE_PLACED_KEY = registerKey("overworld_chi_ore_placed");
    public static final ResourceKey<PlacedFeature> LION_STONE_PLACED_KEY = registerKey("lion_stone_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, OVERWORLD_CHI_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_CHI_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, LION_STONE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LION_STONE_GEODE_KEY),
                ModOrePlacement.rareButHugePlacement(4, 3,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
