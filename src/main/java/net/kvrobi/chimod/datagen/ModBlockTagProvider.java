package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ChiMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.RAW_CHI_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_CHI_ORE_BLOCK.get())
                .add(ModBlocks.CHI_ORE_BLOCK.get())
                .add(ModBlocks.CHI_ORB_BLOCK.get())
                .add(ModBlocks.FIRE_CHI_ORB_BLOCK.get())
                .add(ModBlocks.LION_ROCK_TILES.get())
                .add(ModBlocks.LION_ROCK_TILES_SLAB.get())
                .add(ModBlocks.LION_ROCK_TILES_STAIRS.get())
                .add(ModBlocks.LION_ROCK_TILES_DOOR.get())
                .add(ModBlocks.LION_ROCK_TILES_TRAPDOOR.get())
                .add(ModBlocks.LION_ROCK_TILES_FENCE.get())
                .add(ModBlocks.LION_ROCK_TILES_FENCE_GATE.get())
                .add(ModBlocks.LION_ROCK_TILES_WALL.get())
                .add(ModBlocks.LION_ROCK_TILES_BUTTON.get())
                .add(ModBlocks.LION_ROCK_TILES_PRESSURE_PLATE.get())
                .add(ModBlocks.LION_TILES.get())
                .add(ModBlocks.LION_TILES_SLAB.get())
                .add(ModBlocks.LION_TILES_STAIRS.get())
                .add(ModBlocks.LION_TILES_FENCE.get())
                .add(ModBlocks.LION_TILES_FENCE_GATE.get())
                .add(ModBlocks.LION_TILES_WALL.get())
                .add(ModBlocks.LION_TILES_BUTTON.get())
                .add(ModBlocks.LION_TILES_PRESSURE_PLATE.get())
                .add(ModBlocks.LION_BRICKS.get())
                .add(ModBlocks.LION_BRICKS_SLAB.get())
                .add(ModBlocks.LION_BRICKS_STAIRS.get())
                .add(ModBlocks.LION_BRICKS_FENCE.get())
                .add(ModBlocks.LION_BRICKS_FENCE_GATE.get())
                .add(ModBlocks.LION_BRICKS_WALL.get())
                .add(ModBlocks.LION_BRICKS_BUTTON.get())
                .add(ModBlocks.LION_BRICKS_PRESSURE_PLATE.get())
                .add(ModBlocks.LION_STONE.get())
                .add(ModBlocks.LION_STONE_SLAB.get())
                .add(ModBlocks.LION_STONE_STAIRS.get())
                .add(ModBlocks.LION_STONE_FENCE.get())
                .add(ModBlocks.LION_STONE_FENCE_GATE.get())
                .add(ModBlocks.LION_STONE_WALL.get())
                .add(ModBlocks.LION_STONE_BUTTON.get())
                .add(ModBlocks.LION_STONE_PRESSURE_PLATE.get())
                .add(ModBlocks.SMOOTH_LION_STONE.get())
                .add(ModBlocks.SMOOTH_LION_STONE_SLAB.get())
                .add(ModBlocks.SMOOTH_LION_STONE_STAIRS.get())
                .add(ModBlocks.SMOOTH_LION_STONE_FENCE.get())
                .add(ModBlocks.SMOOTH_LION_STONE_FENCE_GATE.get())
                .add(ModBlocks.SMOOTH_LION_STONE_WALL.get())
                .add(ModBlocks.SMOOTH_LION_STONE_BUTTON.get())
                .add(ModBlocks.SMOOTH_LION_STONE_PRESSURE_PLATE.get())
                .add(ModBlocks.CROCODILE_TILES.get())
                .add(ModBlocks.CROCODILE_TILES_SLAB.get())
                .add(ModBlocks.CROCODILE_TILES_STAIRS.get())
                .add(ModBlocks.CROCODILE_TILES_FENCE.get())
                .add(ModBlocks.CROCODILE_TILES_FENCE_GATE.get())
                .add(ModBlocks.CROCODILE_TILES_WALL.get())
                .add(ModBlocks.CROCODILE_TILES_BUTTON.get())
                .add(ModBlocks.CROCODILE_TILES_PRESSURE_PLATE.get())
                .add(ModBlocks.CROCODILE_BRICKS.get())
                .add(ModBlocks.CROCODILE_BRICKS_SLAB.get())
                .add(ModBlocks.CROCODILE_BRICKS_STAIRS.get())
                .add(ModBlocks.CROCODILE_BRICKS_FENCE.get())
                .add(ModBlocks.CROCODILE_BRICKS_FENCE_GATE.get())
                .add(ModBlocks.CROCODILE_BRICKS_WALL.get())
                .add(ModBlocks.CROCODILE_BRICKS_BUTTON.get())
                .add(ModBlocks.CROCODILE_BRICKS_PRESSURE_PLATE.get())
                .add(ModBlocks.CROCODILE_STONE.get())
                .add(ModBlocks.CROCODILE_STONE_SLAB.get())
                .add(ModBlocks.CROCODILE_STONE_STAIRS.get())
                .add(ModBlocks.CROCODILE_STONE_FENCE.get())
                .add(ModBlocks.CROCODILE_STONE_FENCE_GATE.get())
                .add(ModBlocks.CROCODILE_STONE_WALL.get())
                .add(ModBlocks.CROCODILE_STONE_BUTTON.get())
                .add(ModBlocks.CROCODILE_STONE_PRESSURE_PLATE.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_SLAB.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_STAIRS.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_FENCE.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_FENCE_GATE.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_WALL.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_BUTTON.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_PRESSURE_PLATE.get())
                .add(ModBlocks.BLUE_CHI_LAMP.get());

        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.RAW_CHI_BLOCK.get()).add(ModBlocks.CHI_ORE_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.DEEPSLATE_CHI_ORE_BLOCK.get());

        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.LION_ROCK_TILES.get())
                .add(ModBlocks.LION_TILES.get())
                .add(ModBlocks.LION_BRICKS.get())
                .add(ModBlocks.LION_STONE.get())
                .add(ModBlocks.SMOOTH_LION_STONE.get())
                .add(ModBlocks.LION_COBBLESTONE.get())

                .add(ModBlocks.LION_ROCK_TILES_SLAB.get())
                .add(ModBlocks.LION_TILES_SLAB.get())
                .add(ModBlocks.LION_BRICKS_SLAB.get())
                .add(ModBlocks.LION_STONE_SLAB.get())
                .add(ModBlocks.SMOOTH_LION_STONE_SLAB.get())
                .add(ModBlocks.LION_COBBLESTONE_SLAB.get())

                .add(ModBlocks.LION_ROCK_TILES_STAIRS.get())
                .add(ModBlocks.LION_TILES_STAIRS.get())
                .add(ModBlocks.LION_BRICKS_STAIRS.get())
                .add(ModBlocks.LION_STONE_STAIRS.get())
                .add(ModBlocks.SMOOTH_LION_STONE_STAIRS.get())
                .add(ModBlocks.LION_COBBLESTONE_STAIRS.get())

                .add(ModBlocks.LION_ROCK_TILES_FENCE.get())
                .add(ModBlocks.LION_TILES_FENCE.get())
                .add(ModBlocks.LION_BRICKS_FENCE.get())
                .add(ModBlocks.LION_STONE_FENCE.get())
                .add(ModBlocks.SMOOTH_LION_STONE_FENCE.get())
                .add(ModBlocks.LION_COBBLESTONE_FENCE.get())

                .add(ModBlocks.LION_ROCK_TILES_FENCE_GATE.get())
                .add(ModBlocks.LION_TILES_FENCE_GATE.get())
                .add(ModBlocks.LION_BRICKS_FENCE_GATE.get())
                .add(ModBlocks.LION_STONE_FENCE_GATE.get())
                .add(ModBlocks.SMOOTH_LION_STONE_FENCE_GATE.get())
                .add(ModBlocks.LION_COBBLESTONE_FENCE_GATE.get())

                .add(ModBlocks.LION_ROCK_TILES_WALL.get())
                .add(ModBlocks.LION_TILES_WALL.get())
                .add(ModBlocks.LION_BRICKS_WALL.get())
                .add(ModBlocks.LION_STONE_WALL.get())
                .add(ModBlocks.SMOOTH_LION_STONE_WALL.get())
                .add(ModBlocks.LION_COBBLESTONE_WALL.get())

                .add(ModBlocks.LION_ROCK_TILES_BUTTON.get())
                .add(ModBlocks.LION_TILES_BUTTON.get())
                .add(ModBlocks.LION_BRICKS_BUTTON.get())
                .add(ModBlocks.LION_STONE_BUTTON.get())
                .add(ModBlocks.SMOOTH_LION_STONE_BUTTON.get())
                .add(ModBlocks.LION_COBBLESTONE_BUTTON.get())

                .add(ModBlocks.LION_ROCK_TILES_PRESSURE_PLATE.get())
                .add(ModBlocks.LION_TILES_PRESSURE_PLATE.get())
                .add(ModBlocks.LION_BRICKS_PRESSURE_PLATE.get())
                .add(ModBlocks.LION_STONE_PRESSURE_PLATE.get())
                .add(ModBlocks.SMOOTH_LION_STONE_PRESSURE_PLATE.get())
                .add(ModBlocks.LION_COBBLESTONE_PRESSURE_PLATE.get())

                .add(ModBlocks.CROCODILE_TILES.get())
                .add(ModBlocks.CROCODILE_BRICKS.get())
                .add(ModBlocks.CROCODILE_STONE.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE.get())
                .add(ModBlocks.CROCODILE_COBBLESTONE.get())

                .add(ModBlocks.CROCODILE_TILES_SLAB.get())
                .add(ModBlocks.CROCODILE_BRICKS_SLAB.get())
                .add(ModBlocks.CROCODILE_STONE_SLAB.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_SLAB.get())
                .add(ModBlocks.CROCODILE_COBBLESTONE_SLAB.get())

                .add(ModBlocks.CROCODILE_TILES_STAIRS.get())
                .add(ModBlocks.CROCODILE_BRICKS_STAIRS.get())
                .add(ModBlocks.CROCODILE_STONE_STAIRS.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_STAIRS.get())
                .add(ModBlocks.CROCODILE_COBBLESTONE_STAIRS.get())

                .add(ModBlocks.CROCODILE_TILES_FENCE.get())
                .add(ModBlocks.CROCODILE_BRICKS_FENCE.get())
                .add(ModBlocks.CROCODILE_STONE_FENCE.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_FENCE.get())
                .add(ModBlocks.CROCODILE_COBBLESTONE_FENCE.get())

                .add(ModBlocks.CROCODILE_TILES_FENCE_GATE.get())
                .add(ModBlocks.CROCODILE_BRICKS_FENCE_GATE.get())
                .add(ModBlocks.CROCODILE_STONE_FENCE_GATE.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_FENCE_GATE.get())
                .add(ModBlocks.CROCODILE_COBBLESTONE_FENCE_GATE.get())

                .add(ModBlocks.CROCODILE_TILES_WALL.get())
                .add(ModBlocks.CROCODILE_BRICKS_WALL.get())
                .add(ModBlocks.CROCODILE_STONE_WALL.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_WALL.get())
                .add(ModBlocks.CROCODILE_COBBLESTONE_WALL.get())

                .add(ModBlocks.CROCODILE_TILES_BUTTON.get())
                .add(ModBlocks.CROCODILE_BRICKS_BUTTON.get())
                .add(ModBlocks.CROCODILE_STONE_BUTTON.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_BUTTON.get())
                .add(ModBlocks.CROCODILE_COBBLESTONE_BUTTON.get())

                .add(ModBlocks.CROCODILE_TILES_PRESSURE_PLATE.get())
                .add(ModBlocks.CROCODILE_BRICKS_PRESSURE_PLATE.get())
                .add(ModBlocks.CROCODILE_STONE_PRESSURE_PLATE.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_PRESSURE_PLATE.get())
                .add(ModBlocks.CROCODILE_COBBLESTONE_PRESSURE_PLATE.get())

                .add(ModBlocks.LION_ROCK_TILES_DOOR.get())

                .add(ModBlocks.LION_ROCK_TILES_TRAPDOOR.get());

        tag(BlockTags.FENCES).add(ModBlocks.LION_ROCK_TILES_FENCE.get())
                .add(ModBlocks.LION_ROCK_TILES_FENCE.get())
                .add(ModBlocks.LION_TILES_FENCE.get())
                .add(ModBlocks.LION_BRICKS_FENCE.get())
                .add(ModBlocks.LION_STONE_FENCE.get())
                .add(ModBlocks.SMOOTH_LION_STONE_FENCE.get())
                .add(ModBlocks.CROCODILE_TILES_FENCE.get())
                .add(ModBlocks.CROCODILE_BRICKS_FENCE.get())
                .add(ModBlocks.CROCODILE_STONE_FENCE.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_FENCE.get())
                .add(ModBlocks.CROCODILE_COBBLESTONE_FENCE.get());

        tag(BlockTags.FENCE_GATES).add(ModBlocks.LION_ROCK_TILES_FENCE_GATE.get())
                .add(ModBlocks.LION_ROCK_TILES_FENCE_GATE.get())
                .add(ModBlocks.LION_TILES_FENCE_GATE.get())
                .add(ModBlocks.LION_BRICKS_FENCE_GATE.get())
                .add(ModBlocks.LION_STONE_FENCE_GATE.get())
                .add(ModBlocks.SMOOTH_LION_STONE_FENCE_GATE.get())
                .add(ModBlocks.CROCODILE_TILES_FENCE_GATE.get())
                .add(ModBlocks.CROCODILE_BRICKS_FENCE_GATE.get())
                .add(ModBlocks.CROCODILE_STONE_FENCE_GATE.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_FENCE_GATE.get())
                .add(ModBlocks.CROCODILE_COBBLESTONE_FENCE_GATE.get());

        tag(BlockTags.WALLS).add(ModBlocks.LION_ROCK_TILES_WALL.get())
                .add(ModBlocks.LION_ROCK_TILES_WALL.get())
                .add(ModBlocks.LION_TILES_WALL.get())
                .add(ModBlocks.LION_BRICKS_WALL.get())
                .add(ModBlocks.LION_STONE_WALL.get())
                .add(ModBlocks.SMOOTH_LION_STONE_WALL.get())
                .add(ModBlocks.CROCODILE_TILES_WALL.get())
                .add(ModBlocks.CROCODILE_BRICKS_WALL.get())
                .add(ModBlocks.CROCODILE_STONE_WALL.get())
                .add(ModBlocks.SMOOTH_CROCODILE_STONE_WALL.get())
                .add(ModBlocks.CROCODILE_COBBLESTONE_WALL.get());

    }
}
