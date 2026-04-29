package net.kvrobi.chimod.block;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.custom.ChiLampBlock;
import net.kvrobi.chimod.block.custom.ChiWaterBlock;
import net.kvrobi.chimod.block.custom.Orb;
import net.kvrobi.chimod.fluid.ModFluids;
import net.kvrobi.chimod.item.ModItems;
import net.kvrobi.chimod.item.custom.ChiOrbItem;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChiMod.MOD_ID);


    public static final DeferredBlock<Block> RAW_CHI_BLOCK = registerBlock("raw_chi_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CHI_ORE_BLOCK = registerBlock("chi_ore_block",
            () -> new DropExperienceBlock(UniformInt.of(1, 4), BlockBehaviour.Properties.of()
                    .strength(2.5f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DEEPSLATE_CHI_ORE_BLOCK = registerBlock("deepslate_chi_ore_block",
            () -> new DropExperienceBlock(UniformInt.of(1, 4), BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LION_ROCK_TILES = registerBlock("lion_rock_tiles",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LION_TILES = registerBlock("lion_tiles",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LION_STONE = registerBlock("lion_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LION_BRICKS = registerBlock("lion_bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> SMOOTH_LION_STONE = registerBlock("smooth_lion_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LION_COBBLESTONE = registerBlock("lion_cobblestone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    //non block blocks
    public static final DeferredBlock<StairBlock> LION_ROCK_TILES_STAIRS = registerBlock("lion_rock_tiles_stairs",
            () -> new StairBlock(ModBlocks.LION_ROCK_TILES.get().defaultBlockState(),BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<SlabBlock> LION_ROCK_TILES_SLAB = registerBlock("lion_rock_tiles_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<StairBlock> LION_BRICKS_STAIRS = registerBlock("lion_bricks_stairs",
            () -> new StairBlock(ModBlocks.LION_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<SlabBlock> LION_BRICKS_SLAB = registerBlock("lion_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<StairBlock> LION_STONE_STAIRS = registerBlock("lion_stone_stairs",
            () -> new StairBlock(ModBlocks.LION_STONE.get().defaultBlockState(),BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<SlabBlock> LION_STONE_SLAB = registerBlock("lion_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<StairBlock> SMOOTH_LION_STONE_STAIRS = registerBlock("smooth_lion_stone_stairs",
            () -> new StairBlock(ModBlocks.SMOOTH_LION_STONE.get().defaultBlockState(),BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<SlabBlock> SMOOTH_LION_STONE_SLAB = registerBlock("smooth_lion_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<StairBlock> LION_TILES_STAIRS = registerBlock("lion_tiles_stairs",
            () -> new StairBlock(ModBlocks.LION_TILES.get().defaultBlockState(),BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<SlabBlock> LION_TILES_SLAB = registerBlock("lion_tiles_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<StairBlock> LION_COBBLESTONE_STAIRS = registerBlock("lion_cobblestone_stairs",
            () -> new StairBlock(ModBlocks.LION_TILES.get().defaultBlockState(),BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<SlabBlock> LION_COBBLESTONE_SLAB = registerBlock("lion_cobblestone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));



    public static final DeferredBlock<PressurePlateBlock> LION_ROCK_TILES_PRESSURE_PLATE = registerBlock("lion_rock_tiles_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.STONE,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<ButtonBlock> LION_ROCK_TILES_BUTTON = registerBlock("lion_rock_tiles_button",
            () -> new ButtonBlock(BlockSetType.STONE, 20,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noCollission()));
    public static final DeferredBlock<PressurePlateBlock> LION_BRICKS_PRESSURE_PLATE = registerBlock("lion_bricks_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.STONE,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<ButtonBlock> LION_BRICKS_BUTTON = registerBlock("lion_bricks_button",
            () -> new ButtonBlock(BlockSetType.STONE, 20,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noCollission()));
    public static final DeferredBlock<PressurePlateBlock> LION_STONE_PRESSURE_PLATE = registerBlock("lion_stone_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.STONE,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<ButtonBlock> LION_STONE_BUTTON = registerBlock("lion_stone_button",
            () -> new ButtonBlock(BlockSetType.STONE, 20,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noCollission()));
    public static final DeferredBlock<PressurePlateBlock> SMOOTH_LION_STONE_PRESSURE_PLATE = registerBlock("smooth_lion_stone_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.STONE,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<ButtonBlock> SMOOTH_LION_STONE_BUTTON = registerBlock("smooth_lion_stone_button",
            () -> new ButtonBlock(BlockSetType.STONE, 20,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noCollission()));
    public static final DeferredBlock<PressurePlateBlock> LION_TILES_PRESSURE_PLATE = registerBlock("lion_tiles_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.STONE,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<ButtonBlock> LION_TILES_BUTTON = registerBlock("lion_tiles_button",
            () -> new ButtonBlock(BlockSetType.STONE, 20,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noCollission()));
    public static final DeferredBlock<PressurePlateBlock> LION_COBBLESTONE_PRESSURE_PLATE = registerBlock("lion_cobblestone_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.STONE,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<ButtonBlock> LION_COBBLESTONE_BUTTON = registerBlock("lion_cobblestone_button",
            () -> new ButtonBlock(BlockSetType.STONE, 20,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noCollission()));



    public static final DeferredBlock<FenceBlock> LION_ROCK_TILES_FENCE = registerBlock("lion_rock_tiles_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<FenceGateBlock> LION_ROCK_TILES_FENCE_GATE = registerBlock("lion_rock_tiles_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<WallBlock> LION_ROCK_TILES_WALL = registerBlock("lion_rock_tiles_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<FenceBlock> LION_BRICKS_FENCE = registerBlock("lion_bricks_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<FenceGateBlock> LION_BRICKS_FENCE_GATE = registerBlock("lion_bricks_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<WallBlock> LION_BRICKS_WALL = registerBlock("lion_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<FenceBlock> LION_STONE_FENCE = registerBlock("lion_stone_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<FenceGateBlock> LION_STONE_FENCE_GATE = registerBlock("lion_stone_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<WallBlock> LION_STONE_WALL = registerBlock("lion_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<FenceBlock> SMOOTH_LION_STONE_FENCE = registerBlock("smooth_lion_stone_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<FenceGateBlock> SMOOTH_LION_STONE_FENCE_GATE = registerBlock("smooth_lion_stone_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<WallBlock> SMOOTH_LION_STONE_WALL = registerBlock("smooth_lion_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<FenceBlock> LION_TILES_FENCE = registerBlock("lion_tiles_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<FenceGateBlock> LION_TILES_FENCE_GATE = registerBlock("lion_tiles_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<WallBlock> LION_TILES_WALL = registerBlock("lion_tiles_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<FenceBlock> LION_COBBLESTONE_FENCE = registerBlock("lion_cobblestone_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<FenceGateBlock> LION_COBBLESTONE_FENCE_GATE = registerBlock("lion_cobblestone_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<WallBlock> LION_COBBLESTONE_WALL = registerBlock("lion_cobblestone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));



    public static final DeferredBlock<DoorBlock> LION_ROCK_TILES_DOOR = registerBlock("lion_rock_tiles_door",
            () -> new DoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> LION_ROCK_TILES_TRAPDOOR = registerBlock("lion_rock_tiles_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
    /*public static final DeferredBlock<DoorBlock> LION_BRICKS_DOOR = registerBlock("lion_bricks_door",
            () -> new DoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> LION_BRICKS_TRAPDOOR = registerBlock("lion_bricks_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
    public static final DeferredBlock<DoorBlock> LION_STONE_DOOR = registerBlock("lion_stone_door",
            () -> new DoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> LION_STONE_TRAPDOOR = registerBlock("lion_stone_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
    public static final DeferredBlock<DoorBlock> SMOOTH_LION_STONE_DOOR = registerBlock("smooth_lion_stone_door",
            () -> new DoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> SMOOTH_LION_STONE_TRAPDOOR = registerBlock("smooth_lion_stone_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
    public static final DeferredBlock<DoorBlock> LION_TILES_DOOR = registerBlock("lion_tiles_door",
            () -> new DoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> LION_TILES_TRAPDOOR = registerBlock("lion_tiles_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));*/



    //fluids
    public static final DeferredHolder<Block, ChiWaterBlock> CHI_WATER_BLOCK = BLOCKS.register("chi_water_block",
            () -> new ChiWaterBlock(
                    ModFluids.CHI_WATER_SOURCE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
                            .noCollission()
                            .noLootTable()
                            .liquid()
                            .pushReaction(PushReaction.DESTROY)
            ));






    //custom blocks
    public static final DeferredBlock<Block> CHI_ORB_BLOCK = onlyRegisterBlock("chi_orb_block",
            () -> new Orb(UniformInt.of(1, 4), BlockBehaviour.Properties.of()
                    .strength(1f).instabreak().sound(SoundType.AMETHYST).noOcclusion()));

    public static final DeferredBlock<Block> FIRE_CHI_ORB_BLOCK = onlyRegisterBlock("fire_chi_orb_block",
            () -> new Orb(UniformInt.of(1, 4), BlockBehaviour.Properties.of()
                    .strength(1f).instabreak().sound(SoundType.AMETHYST).noOcclusion()));

    public static final DeferredBlock<Block> BLUE_CHI_LAMP = registerBlock("blue_chi_lamp", () -> new ChiLampBlock(BlockBehaviour.Properties.of()
            .strength(1.5f).sound(SoundType.GLASS).lightLevel(state -> state.getValue(ChiLampBlock.CLICKED) && state.getValue(ChiLampBlock.ACTIVE) ? 15 : 0)));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> onlyRegisterBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockOrb(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockOrb(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new ChiOrbItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
