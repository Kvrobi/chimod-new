package net.kvrobi.chimod.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModStructures {
    public static final ResourceKey<Structure> LION_HOUSE_STRUCTURE1 = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath("kvrobichimod", "lion_small_house1"));
    public static final ResourceKey<Structure> LION_FORT_RUIN_STRUCTURE = ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath("kvrobichimod", "lion_fort_ruin"));

    public static void bootstrap(BootstrapContext<Structure> context) {
        HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> poolGetter = context.lookup(Registries.TEMPLATE_POOL);

        context.register(LION_HOUSE_STRUCTURE1, new JigsawStructure(
                new Structure.StructureSettings(
                        biomeGetter.getOrThrow(BiomeTags.IS_SAVANNA),
                        Map.of(),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        TerrainAdjustment.NONE
                ),
                poolGetter.getOrThrow(ModTemplatePools.LION_HOUSE_POOL),
                Optional.empty(),
                1,
                ConstantHeight.of(VerticalAnchor.absolute(-2)),
                false,
                Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                80,
                List.of(),
                DimensionPadding.ZERO,
                LiquidSettings.IGNORE_WATERLOGGING
        ));

        context.register(LION_FORT_RUIN_STRUCTURE, new JigsawStructure(
                new Structure.StructureSettings(
                        biomeGetter.getOrThrow(BiomeTags.IS_SAVANNA),
                        Map.of(),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        TerrainAdjustment.NONE
                ),
                poolGetter.getOrThrow(ModTemplatePools.LION_FORT_RUIN_POOL),
                Optional.empty(),
                1,
                ConstantHeight.of(VerticalAnchor.absolute(-2)),
                false,
                Optional.of(Heightmap.Types.WORLD_SURFACE_WG),
                80,
                List.of(),
                DimensionPadding.ZERO,
                LiquidSettings.IGNORE_WATERLOGGING
        ));
    }
}