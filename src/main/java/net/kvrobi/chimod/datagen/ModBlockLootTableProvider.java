package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.CHI_ORB_BLOCK.get());
        dropSelf(ModBlocks.FIRE_CHI_ORB_BLOCK.get());
        dropSelf(ModBlocks.RAW_CHI_BLOCK.get());
        dropSelf(ModBlocks.SMOOTH_LION_STONE.get());
        dropSelf(ModBlocks.LION_BRICKS.get());
        dropSelf(ModBlocks.LION_ROCK_TILES.get());
        dropSelf(ModBlocks.LION_TILES.get());
        dropSelf(ModBlocks.LION_COBBLESTONE.get());

        add(ModBlocks.LION_STONE.get(), block -> createSingleItemTableWithSilkTouch(block, ModBlocks.LION_COBBLESTONE.get()));


        dropSelf(ModBlocks.LION_ROCK_TILES_STAIRS.get());
        add(ModBlocks.LION_ROCK_TILES_SLAB.get(), block -> createSlabItemTable(ModBlocks.LION_ROCK_TILES_SLAB.get()));
        dropSelf(ModBlocks.LION_TILES_STAIRS.get());
        add(ModBlocks.LION_TILES_SLAB.get(), block -> createSlabItemTable(ModBlocks.LION_TILES_SLAB.get()));
        dropSelf(ModBlocks.LION_BRICKS_STAIRS.get());
        add(ModBlocks.LION_BRICKS_SLAB.get(), block -> createSlabItemTable(ModBlocks.LION_BRICKS_SLAB.get()));
        dropSelf(ModBlocks.LION_STONE_STAIRS.get());
        dropSelf(ModBlocks.SMOOTH_LION_STONE_STAIRS.get());
        add(ModBlocks.SMOOTH_LION_STONE_SLAB.get(), block -> createSlabItemTable(ModBlocks.SMOOTH_LION_STONE_SLAB.get()));
        add(ModBlocks.LION_STONE_SLAB.get(), block -> createSlabItemTable(ModBlocks.LION_STONE_SLAB.get()));
        dropSelf(ModBlocks.LION_COBBLESTONE_STAIRS.get());
        add(ModBlocks.LION_COBBLESTONE_SLAB.get(), block -> createSlabItemTable(ModBlocks.LION_COBBLESTONE_SLAB.get()));



        dropSelf(ModBlocks.LION_ROCK_TILES_WALL.get());
        dropSelf(ModBlocks.LION_ROCK_TILES_FENCE.get());
        dropSelf(ModBlocks.LION_ROCK_TILES_FENCE_GATE.get());
        dropSelf(ModBlocks.LION_BRICKS_WALL.get());
        dropSelf(ModBlocks.LION_BRICKS_FENCE.get());
        dropSelf(ModBlocks.LION_BRICKS_FENCE_GATE.get());
        dropSelf(ModBlocks.LION_TILES_WALL.get());
        dropSelf(ModBlocks.LION_TILES_FENCE.get());
        dropSelf(ModBlocks.LION_TILES_FENCE_GATE.get());
        dropSelf(ModBlocks.LION_STONE_WALL.get());
        dropSelf(ModBlocks.LION_STONE_FENCE.get());
        dropSelf(ModBlocks.SMOOTH_LION_STONE_WALL.get());
        dropSelf(ModBlocks.SMOOTH_LION_STONE_FENCE.get());
        dropSelf(ModBlocks.SMOOTH_LION_STONE_FENCE_GATE.get());
        dropSelf(ModBlocks.LION_STONE_FENCE_GATE.get());
        dropSelf(ModBlocks.LION_COBBLESTONE_WALL.get());
        dropSelf(ModBlocks.LION_COBBLESTONE_FENCE.get());
        dropSelf(ModBlocks.LION_COBBLESTONE_FENCE_GATE.get());



        add(ModBlocks.LION_ROCK_TILES_DOOR.get(), block -> createDoorTable(ModBlocks.LION_ROCK_TILES_DOOR.get()));
        dropSelf(ModBlocks.LION_ROCK_TILES_TRAPDOOR.get());



        dropSelf(ModBlocks.LION_ROCK_TILES_BUTTON.get());
        dropSelf(ModBlocks.LION_ROCK_TILES_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.LION_BRICKS_BUTTON.get());
        dropSelf(ModBlocks.LION_BRICKS_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.LION_TILES_BUTTON.get());
        dropSelf(ModBlocks.LION_TILES_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.LION_STONE_BUTTON.get());
        dropSelf(ModBlocks.SMOOTH_LION_STONE_BUTTON.get());
        dropSelf(ModBlocks.SMOOTH_LION_STONE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.LION_STONE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.LION_COBBLESTONE_BUTTON.get());
        dropSelf(ModBlocks.LION_COBBLESTONE_PRESSURE_PLATE.get());



        dropSelf(ModBlocks.BLUE_CHI_LAMP.get());

        add(ModBlocks.CHI_ORE_BLOCK.get(),
                block -> createOreDrop(ModBlocks.CHI_ORE_BLOCK.get(), ModItems.RAW_CHI.get()));
        add(ModBlocks.DEEPSLATE_CHI_ORE_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_CHI_ORE_BLOCK.get(), ModItems.RAW_CHI.get(), 1, 2));
    }


    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionCondition(pBlock, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops))).apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
