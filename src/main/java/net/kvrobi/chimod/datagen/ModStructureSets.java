package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class ModStructureSets {
    public static final ResourceKey<StructureSet> LION_HOUSE_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_small_houses"));
    public static final ResourceKey<StructureSet> LION_FORT_RUIN_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_fort_ruins"));
    public static final ResourceKey<StructureSet> CROCODILE_FORT_RUIN_SET = ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "crocodile_fort_ruins"));

    public static void bootstrap(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> structureGetter = context.lookup(Registries.STRUCTURE);

        context.register(LION_HOUSE_SET, new StructureSet(
                structureGetter.getOrThrow(ModStructures.LION_HOUSE_STRUCTURE1),
                new RandomSpreadStructurePlacement(
                        32,
                        8,
                        RandomSpreadType.LINEAR,
                        194837245
                )
        ));

        context.register(LION_FORT_RUIN_SET, new StructureSet(
                structureGetter.getOrThrow(ModStructures.LION_FORT_RUIN_STRUCTURE),
                new RandomSpreadStructurePlacement(
                        64,
                        16,
                        RandomSpreadType.LINEAR,
                        194837242
                )
        ));

        context.register(CROCODILE_FORT_RUIN_SET, new StructureSet(
                structureGetter.getOrThrow(ModStructures.CROCODILE_FORT_RUIN_STRUCTURE),
                new RandomSpreadStructurePlacement(
                        64,
                        16,
                        RandomSpreadType.LINEAR,
                        194837642
                )
        ));
    }
}