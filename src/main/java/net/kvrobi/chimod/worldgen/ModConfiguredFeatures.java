package net.kvrobi.chimod.worldgen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_CHI_ORE_KEY = registerKey("overworld_chi_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LION_STONE_GEODE_KEY = registerKey("lion_stone_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LION_CHI_POOL_KEY = registerKey("lion_chi_pool");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldChiTargets = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.CHI_ORE_BLOCK.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_CHI_ORE_BLOCK.get().defaultBlockState())
        );
        register(context, OVERWORLD_CHI_ORE_KEY, Feature.ORE, new OreConfiguration(overworldChiTargets, 9 ));

        List<OreConfiguration.TargetBlockState> lionStoneTargets = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.LION_STONE.get().defaultBlockState())
        );
        register(context, LION_STONE_GEODE_KEY, Feature.ORE, new OreConfiguration(lionStoneTargets, 64));


    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
