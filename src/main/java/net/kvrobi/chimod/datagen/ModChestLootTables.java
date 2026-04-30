package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.function.BiConsumer;

public class ModChestLootTables implements LootTableSubProvider {
    public ModChestLootTables(HolderLookup.Provider provider) { }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        ResourceKey<LootTable> lionFortRuinLivingrChKey = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "chests/lion_fort_ruin_livingroom_chest"));
        output.accept(lionFortRuinLivingrChKey, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(UniformGenerator.between( 2.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.ACACIA_SAPLING).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(9).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ModItems.LION_JAHAK).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_JABAKA).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_CLUBIUS_MAXIMUS).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.LION_DECALUS).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.LION_FANGIOUS).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.LION_CHI_JABAKA).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.LION_VALIOUS_GRAY).setWeight(1))
        ));

        ResourceKey<LootTable> lionFortRuinTreasureChKey = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "chests/lion_fort_ruin_treasure_chest"));
        output.accept(lionFortRuinTreasureChKey, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(UniformGenerator.between( 2.0F, 6.0F))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(25).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(17).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ModItems.LION_CLUBIUS_MAXIMUS).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_DECALUS).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_FANGIOUS).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_JAHAK).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_JABAKA).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_CHI_JABAKA).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_VALIOUS_GRAY).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.GOLDEN_SHOULDER_SPIKED).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.GOLDEN_SHOULDER_PADS).setWeight(2))
                        .add(LootItem.lootTableItem(ModBlocks.CHI_ORB_BLOCK).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ModBlocks.FIRE_CHI_ORB_BLOCK).setWeight(1))
        ));

        ResourceKey<LootTable> lionFortRuinCaveChKey = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "chests/lion_fort_ruin_cave_chest"));
        output.accept(lionFortRuinCaveChKey, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(UniformGenerator.between( 2.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(19).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.EMERALD).setWeight(14).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
                        .add(LootItem.lootTableItem(Items.COAL).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(ModBlocks.CHI_ORB_BLOCK).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ModBlocks.FIRE_CHI_ORB_BLOCK).setWeight(1))
        ));

        ResourceKey<LootTable> lionSmallHouse1KitchenChKey = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "chests/lion_small_house1_kitchen_chest"));
        output.accept(lionSmallHouse1KitchenChKey, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(UniformGenerator.between( 2.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.WHEAT_SEEDS).setWeight(17).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(16).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(Items.BEEF).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.MUTTON).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.CHICKEN).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.PORKCHOP).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.RABBIT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.COD).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.SALMON).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(ModItems.BANANA).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.COOKED_BEEF).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.COOKED_MUTTON).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.COOKED_CHICKEN).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.COOKED_PORKCHOP).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.COOKED_RABBIT).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.COOKED_COD).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.COOKED_SALMON).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(ModItems.BANANA_JUICE).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.RABBIT_STEW).setWeight(20))
        ));

        ResourceKey<LootTable> lionSmallHouse1BedroomChKey = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "chests/lion_small_house1_bedroom_chest"));
        output.accept(lionSmallHouse1BedroomChKey, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(UniformGenerator.between( 2.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.WHEAT_SEEDS).setWeight(17).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(16).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))))
                        .add(LootItem.lootTableItem(Items.ACACIA_SAPLING).setWeight(18).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.ACACIA_LOG).setWeight(13).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.EMERALD).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
        ));

        ResourceKey<LootTable> lionSmallHouse1SmallTreasureChKey = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "chests/lion_small_house1_small_treasure_chest"));
        output.accept(lionSmallHouse1SmallTreasureChKey, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(UniformGenerator.between( 2.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(13).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(14).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(18).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.ACACIA_LOG).setWeight(13).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(14).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.EMERALD).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(9).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(ModItems.LION_JABAKA).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_JAHAK).setWeight(3))
                        .add(LootItem.lootTableItem(ModBlocks.CHI_ORB_BLOCK).setWeight(1))
        ));

        ResourceKey<LootTable> lionSmallHouse1TreasureChKey = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "chests/lion_small_house1_treasure_chest"));
        output.accept(lionSmallHouse1TreasureChKey, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(UniformGenerator.between( 2.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(12).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(11).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(12).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(Items.EMERALD).setWeight(13).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(11).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                        .add(LootItem.lootTableItem(ModItems.LION_JABAKA).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_JAHAK).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_JABAKA).setWeight(3))
                        .add(LootItem.lootTableItem(ModItems.LION_CLUBIUS_MAXIMUS).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.LION_DECALUS).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.LION_FANGIOUS).setWeight(2))
                        .add(LootItem.lootTableItem(ModItems.LION_CHI_JABAKA).setWeight(2))
                        .add(LootItem.lootTableItem(ModBlocks.CHI_ORB_BLOCK).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ModItems.LION_VALIOUS_GRAY).setWeight(1))
        ));
    }
}
