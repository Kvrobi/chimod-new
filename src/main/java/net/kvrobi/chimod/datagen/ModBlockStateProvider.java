package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.block.custom.ChiLampBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ChiMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.CHI_ORE_BLOCK);
        blockWithItem(ModBlocks.RAW_CHI_BLOCK);
        blockWithItem(ModBlocks.DEEPSLATE_CHI_ORE_BLOCK);
        blockWithItem(ModBlocks.LION_ROCK_TILES);
        blockWithItem(ModBlocks.LION_TILES);
        blockWithItem(ModBlocks.LION_BRICKS);
        blockWithItem(ModBlocks.LION_STONE);
        blockWithItem(ModBlocks.SMOOTH_LION_STONE);
        blockWithItem(ModBlocks.LION_COBBLESTONE);


        stairsBlock(ModBlocks.LION_BRICKS_STAIRS.get(), blockTexture(ModBlocks.LION_BRICKS.get()));
        slabBlock(ModBlocks.LION_BRICKS_SLAB.get(), blockTexture(ModBlocks.LION_BRICKS.get()), blockTexture(ModBlocks.LION_BRICKS.get()));
        stairsBlock(ModBlocks.LION_STONE_STAIRS.get(), blockTexture(ModBlocks.LION_STONE.get()));
        slabBlock(ModBlocks.LION_STONE_SLAB.get(), blockTexture(ModBlocks.LION_STONE.get()), blockTexture(ModBlocks.LION_STONE.get()));
        stairsBlock(ModBlocks.SMOOTH_LION_STONE_STAIRS.get(), blockTexture(ModBlocks.SMOOTH_LION_STONE.get()));
        slabBlock(ModBlocks.SMOOTH_LION_STONE_SLAB.get(), blockTexture(ModBlocks.SMOOTH_LION_STONE.get()), blockTexture(ModBlocks.SMOOTH_LION_STONE.get()));
        stairsBlock(ModBlocks.LION_TILES_STAIRS.get(), blockTexture(ModBlocks.LION_TILES.get()));
        slabBlock(ModBlocks.LION_TILES_SLAB.get(), blockTexture(ModBlocks.LION_TILES.get()), blockTexture(ModBlocks.LION_TILES.get()));
        stairsBlock(ModBlocks.LION_ROCK_TILES_STAIRS.get(), blockTexture(ModBlocks.LION_ROCK_TILES.get()));
        slabBlock(ModBlocks.LION_ROCK_TILES_SLAB.get(), blockTexture(ModBlocks.LION_ROCK_TILES.get()), blockTexture(ModBlocks.LION_ROCK_TILES.get()));
        stairsBlock(ModBlocks.LION_COBBLESTONE_STAIRS.get(), blockTexture(ModBlocks.LION_COBBLESTONE.get()));
        slabBlock(ModBlocks.LION_COBBLESTONE_SLAB.get(), blockTexture(ModBlocks.LION_COBBLESTONE.get()), blockTexture(ModBlocks.LION_COBBLESTONE.get()));



        buttonBlock(ModBlocks.LION_ROCK_TILES_BUTTON.get(), blockTexture(ModBlocks.LION_ROCK_TILES.get()));
        pressurePlateBlock(ModBlocks.LION_ROCK_TILES_PRESSURE_PLATE.get(), blockTexture(ModBlocks.LION_ROCK_TILES.get()));
        buttonBlock(ModBlocks.LION_TILES_BUTTON.get(), blockTexture(ModBlocks.LION_TILES.get()));
        pressurePlateBlock(ModBlocks.LION_TILES_PRESSURE_PLATE.get(), blockTexture(ModBlocks.LION_TILES.get()));
        buttonBlock(ModBlocks.LION_BRICKS_BUTTON.get(), blockTexture(ModBlocks.LION_BRICKS.get()));
        pressurePlateBlock(ModBlocks.LION_BRICKS_PRESSURE_PLATE.get(), blockTexture(ModBlocks.LION_BRICKS.get()));
        buttonBlock(ModBlocks.LION_STONE_BUTTON.get(), blockTexture(ModBlocks.LION_STONE.get()));
        pressurePlateBlock(ModBlocks.LION_STONE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.LION_STONE.get()));
        buttonBlock(ModBlocks.SMOOTH_LION_STONE_BUTTON.get(), blockTexture(ModBlocks.SMOOTH_LION_STONE.get()));
        pressurePlateBlock(ModBlocks.SMOOTH_LION_STONE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.SMOOTH_LION_STONE.get()));
        buttonBlock(ModBlocks.LION_COBBLESTONE_BUTTON.get(), blockTexture(ModBlocks.LION_COBBLESTONE.get()));
        pressurePlateBlock(ModBlocks.LION_COBBLESTONE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.LION_COBBLESTONE.get()));



        fenceBlock(ModBlocks.LION_ROCK_TILES_FENCE.get(), blockTexture(ModBlocks.LION_ROCK_TILES.get()));
        fenceGateBlock(ModBlocks.LION_ROCK_TILES_FENCE_GATE.get(), blockTexture(ModBlocks.LION_ROCK_TILES.get()));
        wallBlock(ModBlocks.LION_ROCK_TILES_WALL.get(), blockTexture(ModBlocks.LION_ROCK_TILES.get()));
        fenceBlock(ModBlocks.LION_TILES_FENCE.get(), blockTexture(ModBlocks.LION_TILES.get()));
        fenceGateBlock(ModBlocks.LION_TILES_FENCE_GATE.get(), blockTexture(ModBlocks.LION_TILES.get()));
        wallBlock(ModBlocks.LION_TILES_WALL.get(), blockTexture(ModBlocks.LION_TILES.get()));
        fenceBlock(ModBlocks.LION_BRICKS_FENCE.get(), blockTexture(ModBlocks.LION_BRICKS.get()));
        fenceGateBlock(ModBlocks.LION_BRICKS_FENCE_GATE.get(), blockTexture(ModBlocks.LION_BRICKS.get()));
        wallBlock(ModBlocks.LION_BRICKS_WALL.get(), blockTexture(ModBlocks.LION_BRICKS.get()));
        fenceBlock(ModBlocks.LION_STONE_FENCE.get(), blockTexture(ModBlocks.LION_STONE.get()));
        fenceGateBlock(ModBlocks.LION_STONE_FENCE_GATE.get(), blockTexture(ModBlocks.LION_STONE.get()));
        fenceBlock(ModBlocks.SMOOTH_LION_STONE_FENCE.get(), blockTexture(ModBlocks.SMOOTH_LION_STONE.get()));
        fenceGateBlock(ModBlocks.SMOOTH_LION_STONE_FENCE_GATE.get(), blockTexture(ModBlocks.SMOOTH_LION_STONE.get()));
        wallBlock(ModBlocks.SMOOTH_LION_STONE_WALL.get(), blockTexture(ModBlocks.SMOOTH_LION_STONE.get()));
        wallBlock(ModBlocks.LION_STONE_WALL.get(), blockTexture(ModBlocks.LION_STONE.get()));
        fenceGateBlock(ModBlocks.LION_COBBLESTONE_FENCE_GATE.get(), blockTexture(ModBlocks.LION_COBBLESTONE.get()));
        wallBlock(ModBlocks.LION_COBBLESTONE_WALL.get(), blockTexture(ModBlocks.LION_COBBLESTONE.get()));



        trapdoorBlockWithRenderType(ModBlocks.LION_ROCK_TILES_TRAPDOOR.get(), modLoc("block/lion_rock_tiles_trapdoor"), true, "solid"); //ha lenne átlátszó pixel, akkor lenne "cutout" a "solid" helyett
        doorBlockWithRenderType(ModBlocks.LION_ROCK_TILES_DOOR.get(), modLoc("block/lion_rock_tiles_door_bottom"), modLoc("block/lion_rock_tiles_door_bottom"), "solid");



        blockItem(ModBlocks.LION_ROCK_TILES_PRESSURE_PLATE);
        blockItem(ModBlocks.LION_ROCK_TILES_STAIRS);
        blockItem(ModBlocks.LION_ROCK_TILES_SLAB);
        blockItem(ModBlocks.LION_ROCK_TILES_FENCE_GATE);
        blockItem(ModBlocks.LION_TILES_PRESSURE_PLATE);
        blockItem(ModBlocks.LION_TILES_STAIRS);
        blockItem(ModBlocks.LION_TILES_SLAB);
        blockItem(ModBlocks.LION_TILES_FENCE_GATE);
        blockItem(ModBlocks.LION_BRICKS_PRESSURE_PLATE);
        blockItem(ModBlocks.LION_BRICKS_STAIRS);
        blockItem(ModBlocks.LION_BRICKS_SLAB);
        blockItem(ModBlocks.LION_BRICKS_FENCE_GATE);
        blockItem(ModBlocks.LION_STONE_PRESSURE_PLATE);
        blockItem(ModBlocks.LION_STONE_STAIRS);
        blockItem(ModBlocks.LION_STONE_SLAB);
        blockItem(ModBlocks.LION_STONE_FENCE_GATE);
        blockItem(ModBlocks.SMOOTH_LION_STONE_PRESSURE_PLATE);
        blockItem(ModBlocks.SMOOTH_LION_STONE_STAIRS);
        blockItem(ModBlocks.SMOOTH_LION_STONE_SLAB);
        blockItem(ModBlocks.SMOOTH_LION_STONE_FENCE_GATE);
        blockItem(ModBlocks.LION_COBBLESTONE_PRESSURE_PLATE);
        blockItem(ModBlocks.LION_COBBLESTONE_STAIRS);
        blockItem(ModBlocks.LION_COBBLESTONE_SLAB);
        blockItem(ModBlocks.LION_COBBLESTONE_FENCE_GATE);


        blockItem(ModBlocks.LION_ROCK_TILES_TRAPDOOR, "_bottom");

        simpleBlock(ModBlocks.CHI_WATER_BLOCK.get(), models().getExistingFile(mcLoc("air")));


        lampBlock(ModBlocks.BLUE_CHI_LAMP, "blue_chi_lamp");


        BlockModelBuilder fire_chi_model = orbModel("fire_chi_orb_block", modLoc("block/fire_chi_particle"), modLoc("block/fire_chi_orb_block_top"), modLoc("block/fire_chi_orb_block_side"));
        BlockModelBuilder chi_model = orbModel("chi_orb_block", modLoc("block/chi_particle"), modLoc("block/chi_orb_block_top"), modLoc("block/chi_orb_block_side"));

        simpleWaterloggedBlock(ModBlocks.FIRE_CHI_ORB_BLOCK.get(), fire_chi_model);
        simpleWaterloggedBlock(ModBlocks.CHI_ORB_BLOCK.get(), chi_model);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("kvrobichimod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("kvrobichimod:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private BlockModelBuilder orbModel(String name, ResourceLocation particle, ResourceLocation top, ResourceLocation side) {
        return models().withExistingParent(name, "block")
                .renderType("cutout")
                .texture("particle", particle)
                .texture("top", top)
                .texture("side", side)
                .element()
                .from(5, 0, 5)
                .to(11, 6, 11)
                .face(Direction.DOWN).uvs(5, 5, 11, 11).texture("#top").cullface(Direction.DOWN).end()
                .face(Direction.UP).uvs(5, 5, 11, 11).texture("#top").end()
                .face(Direction.NORTH).uvs(5, 10, 11, 16).texture("#side").end()
                .face(Direction.SOUTH).uvs(5, 10, 11, 16).texture("#side").end()
                .face(Direction.WEST).uvs(5, 10, 11, 16).texture("#side").end()
                .face(Direction.EAST).uvs(5, 10, 11, 16).texture("#side").end()
                .end().transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(75, 45, 0)
                .translation(0, 2.5f, 0)
                .scale(0.375f, 0.375f, 0.375f)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0, 135, 0)
                .translation(2.0f, 4.0f, 0)
                .scale(0.40f, 0.40f, 0.40f)
                .end()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 225, 0)
                .translation(0, 3.5f, 0)
                .scale(1.0f, 1.0f, 1.0f)
                .end()
                .end();
    }


    private void lampBlock(DeferredBlock<?> block, String name) {

        getVariantBuilder(block.get()).forAllStates(state -> {

            boolean isActivated = state.getValue(ChiLampBlock.ACTIVE);
            boolean isClicked = state.getValue(ChiLampBlock.CLICKED);

            String suffix = isActivated ? "_active" : "_inactive";
            suffix += isClicked ? "_lit" : "_unlit";


            return ConfiguredModel.builder().modelFile(models()
                    .cubeAll(name + suffix, modLoc("block/" + name + suffix))).build();

        });
    }

    private void simpleWaterloggedBlock(Block block, ModelFile model) {
        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.WATERLOGGED, false)
                .modelForState().modelFile(model).addModel()
                .partialState().with(BlockStateProperties.WATERLOGGED, true)
                .modelForState().modelFile(model).addModel();
        simpleBlockItem(block, model);
    }
}
