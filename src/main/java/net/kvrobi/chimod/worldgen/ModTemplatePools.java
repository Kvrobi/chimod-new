package net.kvrobi.chimod.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class ModTemplatePools {

    public static final ResourceKey<StructureTemplatePool> LION_HOUSE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath("kvrobichimod", "lion_small_house1"));
    public static final ResourceKey<StructureTemplatePool> LION_FORT_RUIN_POOL = ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.fromNamespaceAndPath("kvrobichimod", "lion_fort_ruin"));

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> poolGetter = context.lookup(Registries.TEMPLATE_POOL);

        context.register(LION_HOUSE_POOL, new StructureTemplatePool(
                poolGetter.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("kvrobichimod:lion_small_house1"), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));

        context.register(LION_FORT_RUIN_POOL, new StructureTemplatePool(
                poolGetter.getOrThrow(Pools.EMPTY),
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("kvrobichimod:lion_fort_ruin"), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));
    }
}
