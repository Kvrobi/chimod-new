package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.item.ModItems;
import net.kvrobi.chimod.util.ModTags;
import net.kvrobi.chimod.world.recipe.LionShapedRecipePattern;
import net.kvrobi.chimod.world.recipe.ShapedLionCraftingRecipe;
import net.kvrobi.chimod.world.recipe.ShapelessLionCraftingRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.references.Blocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> CHI_ORB_SMELTABLES = List.of(ModItems.CHI_SHARD,ModBlocks.CHI_ORE_BLOCK,ModBlocks.DEEPSLATE_CHI_ORE_BLOCK);
        List<ItemLike> L_STONE = List.of(ModBlocks.LION_COBBLESTONE);
        List<ItemLike> SMOOTH_LION_STONE_SMELTABLES = List.of(ModBlocks.LION_STONE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_CHI_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.CHI_SHARD.get())
                .unlockedBy("has_raw_chi", has(ModItems.CHI_SHARD)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_CHI_LAMP.get())
                .pattern("TGT")
                .pattern("G G")
                .pattern("TGT")
                .define('G', Items.GLASS)
                .define('T',ModBlocks.LION_ROCK_TILES.get())
                .unlockedBy("has_lion_rock_tiles", has(ModBlocks.LION_ROCK_TILES)).save(recipeOutput);


        /*ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.LION_BRICKS.get(),4)
                .requires(ModBlocks.LION_STONE, 4)
                .unlockedBy("has_lion_stone_block", has(ModBlocks.LION_STONE)).save(recipeOutput, "kvrobichimod:lion_bricks_from_four_lion_stone");
*/

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHI_SHARD.get(),9)
                .requires(ModBlocks.RAW_CHI_BLOCK)
                .unlockedBy("has_raw_chi_block", has(ModBlocks.RAW_CHI_BLOCK)).save(recipeOutput, "kvrobichimod:raw_chi_from_raw_chi_block");

       /* ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHI_ORB.get(),1)
                .requires(ModBlocks.CHI_ORB_BLOCK)
                .unlockedBy("has_chi_orb_block", has(ModBlocks.CHI_ORB_BLOCK)).save(recipeOutput, "kvrobichimod:chi_orb_from_chi_orb_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CHI_ORB_BLOCK.get(),1)
                .requires(ModItems.CHI_ORB)
                .unlockedBy("has_chi_orb", has(ModItems.CHI_ORB)).save(recipeOutput, "kvrobichimod:chi_orb_block_from_chi_orb");
*/
        /*ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHI_SHARD.get(),1)
                .requires(ModBlocks.CHI_ORE_BLOCK)
                .unlockedBy("has_raw_chi_block", has(ModBlocks.RAW_CHI_BLOCK)).save(recipeOutput, "kvrobichimod:raw_chi_2");*/

       /* oreSmelting(recipeOutput, CHI_ORB_SMELTABLES, RecipeCategory.MISC, ModItems.CHI_ORB.get(), 0.25f,200, "chi_orb");
        oreBlasting(recipeOutput, CHI_ORB_SMELTABLES, RecipeCategory.MISC, ModItems.CHI_ORB.get(), 0.25f,100, "chi_orb");
*/
        oreSmelting(recipeOutput, L_STONE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_STONE.get(), 0.1f,200, "lion_stone");
        //oreBlasting(recipeOutput, L_STONE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_STONE.get(), 0.25f,100, "lion_stone");
        oreSmelting(recipeOutput, SMOOTH_LION_STONE_SMELTABLES, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_LION_STONE.get(), 0.1f, 200, "smooth_lion_stone");


        stairBuilder(ModBlocks.LION_ROCK_TILES_STAIRS.get(), Ingredient.of(ModBlocks.LION_ROCK_TILES)).group("lion_rock_tiles")
                .unlockedBy("has_lion_rock_tiles", has(ModBlocks.LION_ROCK_TILES)).save(recipeOutput);
        //slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_ROCK_TILES_SLAB, ModBlocks.LION_ROCK_TILES);
        //slabben megcsinálja a builder-t rögtön.

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_ROCK_TILES_SLAB.get(), Ingredient.of(ModBlocks.LION_ROCK_TILES)).group("lion_rock_tiles")
                .unlockedBy("has_lion_rock_tiles", has(ModBlocks.LION_ROCK_TILES)).save(recipeOutput);

        doorBuilder(ModBlocks.LION_ROCK_TILES_DOOR.get(), Ingredient.of(ModBlocks.LION_ROCK_TILES)).group("lion_rock_tiles")
                .unlockedBy("has_lion_rock_tiles", has(ModBlocks.LION_ROCK_TILES)).save(recipeOutput);

        trapdoorBuilder(ModBlocks.LION_ROCK_TILES_TRAPDOOR.get(), Ingredient.of(ModBlocks.LION_ROCK_TILES)).group("lion_rock_tiles")
                .unlockedBy("has_lion_rock_tiles", has(ModBlocks.LION_ROCK_TILES)).save(recipeOutput);

        fenceBuilder(ModBlocks.LION_ROCK_TILES_FENCE.get(), Ingredient.of(ModBlocks.LION_ROCK_TILES)).group("lion_rock_tiles")
                .unlockedBy("has_lion_rock_tiles", has(ModBlocks.LION_ROCK_TILES)).save(recipeOutput);

        fenceGateBuilder(ModBlocks.LION_ROCK_TILES_FENCE_GATE.get(), Ingredient.of(ModBlocks.LION_ROCK_TILES)).group("lion_rock_tiles")
                .unlockedBy("has_lion_rock_tiles", has(ModBlocks.LION_ROCK_TILES)).save(recipeOutput);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_ROCK_TILES_WALL.get(), Ingredient.of(ModBlocks.LION_ROCK_TILES)).group("lion_rock_tiles")
                .unlockedBy("has_lion_rock_tiles", has(ModBlocks.LION_ROCK_TILES)).save(recipeOutput);

        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_ROCK_TILES_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.LION_ROCK_TILES)).group("lion_rock_tiles")
                .unlockedBy("has_lion_rock_tiles", has(ModBlocks.LION_ROCK_TILES)).save(recipeOutput);

        buttonBuilder(ModBlocks.LION_ROCK_TILES_BUTTON.get(), Ingredient.of(ModBlocks.LION_ROCK_TILES)).group("lion_rock_tiles")
                .unlockedBy("has_lion_rock_tiles", has(ModBlocks.LION_ROCK_TILES)).save(recipeOutput);



        stairBuilder(ModBlocks.LION_TILES_STAIRS.get(), Ingredient.of(ModBlocks.LION_TILES)).group("lion_tiles")
                .unlockedBy("has_lion_tiles", has(ModBlocks.LION_TILES)).save(recipeOutput);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_TILES_SLAB.get(), Ingredient.of(ModBlocks.LION_TILES)).group("lion_tiles")
                .unlockedBy("has_lion_tiles", has(ModBlocks.LION_TILES)).save(recipeOutput);

        fenceBuilder(ModBlocks.LION_TILES_FENCE.get(), Ingredient.of(ModBlocks.LION_TILES)).group("lion_tiles")
                .unlockedBy("has_lion_tiles", has(ModBlocks.LION_TILES)).save(recipeOutput);

        fenceGateBuilder(ModBlocks.LION_TILES_FENCE_GATE.get(), Ingredient.of(ModBlocks.LION_TILES)).group("lion_tiles")
                .unlockedBy("has_lion_tiles", has(ModBlocks.LION_TILES)).save(recipeOutput);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_TILES_WALL.get(), Ingredient.of(ModBlocks.LION_TILES)).group("lion_tiles")
                .unlockedBy("has_lion_tiles", has(ModBlocks.LION_TILES)).save(recipeOutput);

        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_TILES_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.LION_TILES)).group("lion_tiles")
                .unlockedBy("has_lion_tiles", has(ModBlocks.LION_TILES)).save(recipeOutput);

        buttonBuilder(ModBlocks.LION_TILES_BUTTON.get(), Ingredient.of(ModBlocks.LION_TILES)).group("lion_tiles")
                .unlockedBy("has_lion_tiles", has(ModBlocks.LION_TILES)).save(recipeOutput);


        stairBuilder(ModBlocks.LION_STONE_STAIRS.get(), Ingredient.of(ModBlocks.LION_STONE)).group("lion_stone")
                .unlockedBy("has_lion_stone", has(ModBlocks.LION_STONE)).save(recipeOutput);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_STONE_SLAB.get(), Ingredient.of(ModBlocks.LION_STONE)).group("lion_stone")
                .unlockedBy("has_lion_stone", has(ModBlocks.LION_STONE)).save(recipeOutput);

        fenceBuilder(ModBlocks.LION_STONE_FENCE.get(), Ingredient.of(ModBlocks.LION_STONE)).group("lion_stone")
                .unlockedBy("has_lion_stone", has(ModBlocks.LION_STONE)).save(recipeOutput);

        fenceGateBuilder(ModBlocks.LION_STONE_FENCE_GATE.get(), Ingredient.of(ModBlocks.LION_STONE)).group("lion_stone")
                .unlockedBy("has_lion_stone", has(ModBlocks.LION_STONE)).save(recipeOutput);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_STONE_WALL.get(), Ingredient.of(ModBlocks.LION_STONE)).group("lion_stone")
                .unlockedBy("has_lion_stone", has(ModBlocks.LION_STONE)).save(recipeOutput);

        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_STONE_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.LION_STONE)).group("lion_stone")
                .unlockedBy("has_lion_stone", has(ModBlocks.LION_STONE)).save(recipeOutput);

        buttonBuilder(ModBlocks.LION_STONE_BUTTON.get(), Ingredient.of(ModBlocks.LION_STONE)).group("lion_stone")
                .unlockedBy("has_lion_stone", has(ModBlocks.LION_STONE)).save(recipeOutput);


        stairBuilder(ModBlocks.LION_BRICKS_STAIRS.get(), Ingredient.of(ModBlocks.LION_BRICKS)).group("lion_bricks")
                .unlockedBy("has_lion_bricks", has(ModBlocks.LION_BRICKS)).save(recipeOutput);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_BRICKS_SLAB.get(), Ingredient.of(ModBlocks.LION_BRICKS)).group("lion_bricks")
                .unlockedBy("has_lion_bricks", has(ModBlocks.LION_BRICKS)).save(recipeOutput);

        fenceBuilder(ModBlocks.LION_BRICKS_FENCE.get(), Ingredient.of(ModBlocks.LION_BRICKS)).group("lion_bricks")
                .unlockedBy("has_lion_bricks", has(ModBlocks.LION_BRICKS)).save(recipeOutput);

        fenceGateBuilder(ModBlocks.LION_BRICKS_FENCE_GATE.get(), Ingredient.of(ModBlocks.LION_BRICKS)).group("lion_bricks")
                .unlockedBy("has_lion_bricks", has(ModBlocks.LION_BRICKS)).save(recipeOutput);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_BRICKS_WALL.get(), Ingredient.of(ModBlocks.LION_BRICKS)).group("lion_bricks")
                .unlockedBy("has_lion_bricks", has(ModBlocks.LION_BRICKS)).save(recipeOutput);

        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_BRICKS_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.LION_BRICKS)).group("lion_bricks")
                .unlockedBy("has_lion_bricks", has(ModBlocks.LION_BRICKS)).save(recipeOutput);

        buttonBuilder(ModBlocks.LION_BRICKS_BUTTON.get(), Ingredient.of(ModBlocks.LION_BRICKS)).group("lion_bricks")
                .unlockedBy("has_lion_bricks", has(ModBlocks.LION_BRICKS)).save(recipeOutput);



        stairBuilder(ModBlocks.SMOOTH_LION_STONE_STAIRS.get(), Ingredient.of(ModBlocks.SMOOTH_LION_STONE)).group("smooth_lion_stone")
                .unlockedBy("has_smooth_lion_stone", has(ModBlocks.SMOOTH_LION_STONE)).save(recipeOutput);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_LION_STONE_SLAB.get(), Ingredient.of(ModBlocks.SMOOTH_LION_STONE)).group("smooth_lion_stone")
                .unlockedBy("has_smooth_lion_stone", has(ModBlocks.SMOOTH_LION_STONE)).save(recipeOutput);

        fenceBuilder(ModBlocks.SMOOTH_LION_STONE_FENCE.get(), Ingredient.of(ModBlocks.SMOOTH_LION_STONE)).group("smooth_lion_stone")
                .unlockedBy("has_smooth_lion_stone", has(ModBlocks.SMOOTH_LION_STONE)).save(recipeOutput);

        fenceGateBuilder(ModBlocks.SMOOTH_LION_STONE_FENCE_GATE.get(), Ingredient.of(ModBlocks.SMOOTH_LION_STONE)).group("smooth_lion_stone")
                .unlockedBy("has_smooth_lion_stone", has(ModBlocks.SMOOTH_LION_STONE)).save(recipeOutput);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_LION_STONE_WALL.get(), Ingredient.of(ModBlocks.SMOOTH_LION_STONE)).group("smooth_lion_stone")
                .unlockedBy("has_smooth_lion_stone", has(ModBlocks.SMOOTH_LION_STONE)).save(recipeOutput);

        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_LION_STONE_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.SMOOTH_LION_STONE)).group("smooth_lion_stone")
                .unlockedBy("has_smooth_lion_stone", has(ModBlocks.SMOOTH_LION_STONE)).save(recipeOutput);

        buttonBuilder(ModBlocks.SMOOTH_LION_STONE_BUTTON.get(), Ingredient.of(ModBlocks.SMOOTH_LION_STONE)).group("smooth_lion_stone")
                .unlockedBy("has_smooth_lion_stone", has(ModBlocks.SMOOTH_LION_STONE)).save(recipeOutput);


        stairBuilder(ModBlocks.LION_COBBLESTONE_STAIRS.get(), Ingredient.of(ModBlocks.LION_COBBLESTONE)).group("lion_cobblestone")
                .unlockedBy("has_lion_cobblestone", has(ModBlocks.LION_COBBLESTONE)).save(recipeOutput);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_COBBLESTONE_SLAB.get(), Ingredient.of(ModBlocks.LION_COBBLESTONE)).group("lion_cobblestone")
                .unlockedBy("has_lion_cobblestone", has(ModBlocks.LION_COBBLESTONE)).save(recipeOutput);

        fenceBuilder(ModBlocks.LION_COBBLESTONE_FENCE.get(), Ingredient.of(ModBlocks.LION_COBBLESTONE)).group("lion_cobblestone")
                .unlockedBy("has_lion_cobblestone", has(ModBlocks.LION_COBBLESTONE)).save(recipeOutput);

        fenceGateBuilder(ModBlocks.LION_COBBLESTONE_FENCE_GATE.get(), Ingredient.of(ModBlocks.LION_COBBLESTONE)).group("lion_cobblestone")
                .unlockedBy("has_lion_cobblestone", has(ModBlocks.LION_COBBLESTONE)).save(recipeOutput);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_COBBLESTONE_WALL.get(), Ingredient.of(ModBlocks.LION_COBBLESTONE)).group("lion_cobblestone")
                .unlockedBy("has_lion_cobblestone", has(ModBlocks.LION_COBBLESTONE)).save(recipeOutput);

        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_COBBLESTONE_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.LION_COBBLESTONE)).group("lion_cobblestone")
                .unlockedBy("has_lion_cobblestone", has(ModBlocks.LION_COBBLESTONE)).save(recipeOutput);

        buttonBuilder(ModBlocks.LION_COBBLESTONE_BUTTON.get(), Ingredient.of(ModBlocks.LION_COBBLESTONE)).group("lion_cobblestone")
                .unlockedBy("has_lion_cobblestone", has(ModBlocks.LION_COBBLESTONE)).save(recipeOutput);



        stairBuilder(ModBlocks.CROCODILE_TILES_STAIRS.get(), Ingredient.of(ModBlocks.CROCODILE_TILES)).group("crocodile_tiles")
                .unlockedBy("has_crocodile_tiles", has(ModBlocks.CROCODILE_TILES)).save(recipeOutput);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_TILES_SLAB.get(), Ingredient.of(ModBlocks.CROCODILE_TILES)).group("crocodile_tiles")
                .unlockedBy("has_crocodile_tiles", has(ModBlocks.CROCODILE_TILES)).save(recipeOutput);

        fenceBuilder(ModBlocks.CROCODILE_TILES_FENCE.get(), Ingredient.of(ModBlocks.CROCODILE_TILES)).group("crocodile_tiles")
                .unlockedBy("has_crocodile_tiles", has(ModBlocks.CROCODILE_TILES)).save(recipeOutput);

        fenceGateBuilder(ModBlocks.CROCODILE_TILES_FENCE_GATE.get(), Ingredient.of(ModBlocks.CROCODILE_TILES)).group("crocodile_tiles")
                .unlockedBy("has_crocodile_tiles", has(ModBlocks.CROCODILE_TILES)).save(recipeOutput);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_TILES_WALL.get(), Ingredient.of(ModBlocks.CROCODILE_TILES)).group("crocodile_tiles")
                .unlockedBy("has_crocodile_tiles", has(ModBlocks.CROCODILE_TILES)).save(recipeOutput);

        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_TILES_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.CROCODILE_TILES)).group("crocodile_tiles")
                .unlockedBy("has_crocodile_tiles", has(ModBlocks.CROCODILE_TILES)).save(recipeOutput);

        buttonBuilder(ModBlocks.CROCODILE_TILES_BUTTON.get(), Ingredient.of(ModBlocks.CROCODILE_TILES)).group("crocodile_tiles")
                .unlockedBy("has_crocodile_tiles", has(ModBlocks.CROCODILE_TILES)).save(recipeOutput);



        stairBuilder(ModBlocks.CROCODILE_STONE_STAIRS.get(), Ingredient.of(ModBlocks.CROCODILE_STONE)).group("crocodile_stone")
                .unlockedBy("has_crocodile_stone", has(ModBlocks.CROCODILE_STONE)).save(recipeOutput);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_STONE_SLAB.get(), Ingredient.of(ModBlocks.CROCODILE_STONE)).group("crocodile_stone")
                .unlockedBy("has_crocodile_stone", has(ModBlocks.CROCODILE_STONE)).save(recipeOutput);

        fenceBuilder(ModBlocks.CROCODILE_STONE_FENCE.get(), Ingredient.of(ModBlocks.CROCODILE_STONE)).group("crocodile_stone")
                .unlockedBy("has_crocodile_stone", has(ModBlocks.CROCODILE_STONE)).save(recipeOutput);

        fenceGateBuilder(ModBlocks.CROCODILE_STONE_FENCE_GATE.get(), Ingredient.of(ModBlocks.CROCODILE_STONE)).group("crocodile_stone")
                .unlockedBy("has_crocodile_stone", has(ModBlocks.CROCODILE_STONE)).save(recipeOutput);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_STONE_WALL.get(), Ingredient.of(ModBlocks.CROCODILE_STONE)).group("crocodile_stone")
                .unlockedBy("has_crocodile_stone", has(ModBlocks.CROCODILE_STONE)).save(recipeOutput);

        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_STONE_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.CROCODILE_STONE)).group("crocodile_stone")
                .unlockedBy("has_crocodile_stone", has(ModBlocks.CROCODILE_STONE)).save(recipeOutput);

        buttonBuilder(ModBlocks.CROCODILE_STONE_BUTTON.get(), Ingredient.of(ModBlocks.CROCODILE_STONE)).group("crocodile_stone")
                .unlockedBy("has_crocodile_stone", has(ModBlocks.CROCODILE_STONE)).save(recipeOutput);


        stairBuilder(ModBlocks.CROCODILE_BRICKS_STAIRS.get(), Ingredient.of(ModBlocks.CROCODILE_BRICKS)).group("crocodile_bricks")
                .unlockedBy("has_crocodile_bricks", has(ModBlocks.CROCODILE_BRICKS)).save(recipeOutput);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_BRICKS_SLAB.get(), Ingredient.of(ModBlocks.CROCODILE_BRICKS)).group("crocodile_bricks")
                .unlockedBy("has_crocodile_bricks", has(ModBlocks.CROCODILE_BRICKS)).save(recipeOutput);

        fenceBuilder(ModBlocks.CROCODILE_BRICKS_FENCE.get(), Ingredient.of(ModBlocks.CROCODILE_BRICKS)).group("crocodile_bricks")
                .unlockedBy("has_crocodile_bricks", has(ModBlocks.CROCODILE_BRICKS)).save(recipeOutput);

        fenceGateBuilder(ModBlocks.CROCODILE_BRICKS_FENCE_GATE.get(), Ingredient.of(ModBlocks.CROCODILE_BRICKS)).group("crocodile_bricks")
                .unlockedBy("has_crocodile_bricks", has(ModBlocks.CROCODILE_BRICKS)).save(recipeOutput);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_BRICKS_WALL.get(), Ingredient.of(ModBlocks.CROCODILE_BRICKS)).group("crocodile_bricks")
                .unlockedBy("has_crocodile_bricks", has(ModBlocks.CROCODILE_BRICKS)).save(recipeOutput);

        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_BRICKS_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.CROCODILE_BRICKS)).group("crocodile_bricks")
                .unlockedBy("has_crocodile_bricks", has(ModBlocks.CROCODILE_BRICKS)).save(recipeOutput);

        buttonBuilder(ModBlocks.CROCODILE_BRICKS_BUTTON.get(), Ingredient.of(ModBlocks.CROCODILE_BRICKS)).group("crocodile_bricks")
                .unlockedBy("has_crocodile_bricks", has(ModBlocks.CROCODILE_BRICKS)).save(recipeOutput);



        stairBuilder(ModBlocks.SMOOTH_CROCODILE_STONE_STAIRS.get(), Ingredient.of(ModBlocks.SMOOTH_CROCODILE_STONE)).group("smooth_crocodile_stone")
                .unlockedBy("has_smooth_crocodile_stone", has(ModBlocks.SMOOTH_CROCODILE_STONE)).save(recipeOutput);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_CROCODILE_STONE_SLAB.get(), Ingredient.of(ModBlocks.SMOOTH_CROCODILE_STONE)).group("smooth_crocodile_stone")
                .unlockedBy("has_smooth_crocodile_stone", has(ModBlocks.SMOOTH_CROCODILE_STONE)).save(recipeOutput);

        fenceBuilder(ModBlocks.SMOOTH_CROCODILE_STONE_FENCE.get(), Ingredient.of(ModBlocks.SMOOTH_CROCODILE_STONE)).group("smooth_crocodile_stone")
                .unlockedBy("has_smooth_crocodile_stone", has(ModBlocks.SMOOTH_CROCODILE_STONE)).save(recipeOutput);

        fenceGateBuilder(ModBlocks.SMOOTH_CROCODILE_STONE_FENCE_GATE.get(), Ingredient.of(ModBlocks.SMOOTH_CROCODILE_STONE)).group("smooth_crocodile_stone")
                .unlockedBy("has_smooth_crocodile_stone", has(ModBlocks.SMOOTH_CROCODILE_STONE)).save(recipeOutput);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_CROCODILE_STONE_WALL.get(), Ingredient.of(ModBlocks.SMOOTH_CROCODILE_STONE)).group("smooth_crocodile_stone")
                .unlockedBy("has_smooth_crocodile_stone", has(ModBlocks.SMOOTH_CROCODILE_STONE)).save(recipeOutput);

        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_CROCODILE_STONE_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.SMOOTH_CROCODILE_STONE)).group("smooth_crocodile_stone")
                .unlockedBy("has_smooth_crocodile_stone", has(ModBlocks.SMOOTH_CROCODILE_STONE)).save(recipeOutput);

        buttonBuilder(ModBlocks.SMOOTH_CROCODILE_STONE_BUTTON.get(), Ingredient.of(ModBlocks.SMOOTH_CROCODILE_STONE)).group("smooth_crocodile_stone")
                .unlockedBy("has_smooth_crocodile_stone", has(ModBlocks.SMOOTH_CROCODILE_STONE)).save(recipeOutput);



        stairBuilder(ModBlocks.CROCODILE_COBBLESTONE_STAIRS.get(), Ingredient.of(ModBlocks.CROCODILE_COBBLESTONE)).group("crocodile_cobblestone")
                .unlockedBy("has_crocodile_cobblestone", has(ModBlocks.CROCODILE_COBBLESTONE)).save(recipeOutput);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_COBBLESTONE_SLAB.get(), Ingredient.of(ModBlocks.CROCODILE_COBBLESTONE)).group("crocodile_cobblestone")
                .unlockedBy("has_crocodile_cobblestone", has(ModBlocks.CROCODILE_COBBLESTONE)).save(recipeOutput);

        fenceBuilder(ModBlocks.CROCODILE_COBBLESTONE_FENCE.get(), Ingredient.of(ModBlocks.CROCODILE_COBBLESTONE)).group("crocodile_cobblestone")
                .unlockedBy("has_crocodile_cobblestone", has(ModBlocks.CROCODILE_COBBLESTONE)).save(recipeOutput);

        fenceGateBuilder(ModBlocks.CROCODILE_COBBLESTONE_FENCE_GATE.get(), Ingredient.of(ModBlocks.CROCODILE_COBBLESTONE)).group("crocodile_cobblestone")
                .unlockedBy("has_crocodile_cobblestone", has(ModBlocks.CROCODILE_COBBLESTONE)).save(recipeOutput);

        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_COBBLESTONE_WALL.get(), Ingredient.of(ModBlocks.CROCODILE_COBBLESTONE)).group("crocodile_cobblestone")
                .unlockedBy("has_crocodile_cobblestone", has(ModBlocks.CROCODILE_COBBLESTONE)).save(recipeOutput);

        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CROCODILE_COBBLESTONE_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.CROCODILE_COBBLESTONE)).group("crocodile_cobblestone")
                .unlockedBy("has_crocodile_cobblestone", has(ModBlocks.CROCODILE_COBBLESTONE)).save(recipeOutput);

        buttonBuilder(ModBlocks.CROCODILE_COBBLESTONE_BUTTON.get(), Ingredient.of(ModBlocks.CROCODILE_COBBLESTONE)).group("crocodile_cobblestone")
                .unlockedBy("has_crocodile_cobblestone", has(ModBlocks.CROCODILE_COBBLESTONE)).save(recipeOutput);






        ///recipes

        brickBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_BRICKS.get(), recipeOutput);
        brickBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_TILES.get(), recipeOutput);
        brickBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_ROCK_TILES.get(), recipeOutput);
        brickBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_BRICKS.get(), recipeOutput);
        brickBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.CROCODILE_TILES.get(), recipeOutput);


        stonecutterBuilder(ModBlocks.LION_ROCK_TILES.get(), ModBlocks.LION_ROCK_TILES_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_ROCK_TILES.get(), ModBlocks.LION_ROCK_TILES_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_ROCK_TILES.get(), ModBlocks.LION_ROCK_TILES_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_ROCK_TILES.get(), ModBlocks.LION_ROCK_TILES_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_ROCK_TILES.get(), ModBlocks.LION_ROCK_TILES_SLAB.get(), 2, recipeOutput);

        stonecutterBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_TILES_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_TILES_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_TILES_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_TILES_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_TILES_SLAB.get(), 2, recipeOutput);
        stonecutterBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_ROCK_TILES.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_ROCK_TILES_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_ROCK_TILES_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_ROCK_TILES_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_ROCK_TILES_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_TILES.get(), ModBlocks.LION_ROCK_TILES_SLAB.get(), 2, recipeOutput);

        stonecutterBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_STONE_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_STONE_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_STONE_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_STONE_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_STONE_SLAB.get(), 2, recipeOutput);
        stonecutterBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_BRICKS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_BRICKS_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_BRICKS_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_BRICKS_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_BRICKS_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_STONE.get(), ModBlocks.LION_BRICKS_SLAB.get(), 2, recipeOutput);

        stonecutterBuilder(ModBlocks.LION_BRICKS.get(), ModBlocks.LION_BRICKS_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_BRICKS.get(), ModBlocks.LION_BRICKS_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_BRICKS.get(), ModBlocks.LION_BRICKS_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_BRICKS.get(), ModBlocks.LION_BRICKS_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_BRICKS.get(), ModBlocks.LION_BRICKS_SLAB.get(), 2, recipeOutput);

        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.SMOOTH_LION_STONE_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.SMOOTH_LION_STONE_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.SMOOTH_LION_STONE_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.SMOOTH_LION_STONE_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.SMOOTH_LION_STONE_SLAB.get(), 2, recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_TILES.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_TILES_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_TILES_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_TILES_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_TILES_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_TILES_SLAB.get(), 2, recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_ROCK_TILES.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_ROCK_TILES_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_ROCK_TILES_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_ROCK_TILES_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_ROCK_TILES_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_LION_STONE.get(), ModBlocks.LION_ROCK_TILES_SLAB.get(), 2, recipeOutput);

        stonecutterBuilder(ModBlocks.LION_COBBLESTONE.get(), ModBlocks.LION_COBBLESTONE_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_COBBLESTONE.get(), ModBlocks.LION_COBBLESTONE_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_COBBLESTONE.get(), ModBlocks.LION_COBBLESTONE_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_COBBLESTONE.get(), ModBlocks.LION_COBBLESTONE_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.LION_COBBLESTONE.get(), ModBlocks.LION_COBBLESTONE_SLAB.get(), 2, recipeOutput);

        stonecutterBuilder(ModBlocks.CROCODILE_TILES.get(), ModBlocks.CROCODILE_TILES_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_TILES.get(), ModBlocks.CROCODILE_TILES_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_TILES.get(), ModBlocks.CROCODILE_TILES_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_TILES.get(), ModBlocks.CROCODILE_TILES_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_TILES.get(), ModBlocks.CROCODILE_TILES_SLAB.get(), 2, recipeOutput);

        stonecutterBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_STONE_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_STONE_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_STONE_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_STONE_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_STONE_SLAB.get(), 2, recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_BRICKS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_BRICKS_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_BRICKS_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_BRICKS_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_BRICKS_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_STONE.get(), ModBlocks.CROCODILE_BRICKS_SLAB.get(), 2, recipeOutput);

        stonecutterBuilder(ModBlocks.CROCODILE_BRICKS.get(), ModBlocks.CROCODILE_BRICKS_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_BRICKS.get(), ModBlocks.CROCODILE_BRICKS_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_BRICKS.get(), ModBlocks.CROCODILE_BRICKS_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_BRICKS.get(), ModBlocks.CROCODILE_BRICKS_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_BRICKS.get(), ModBlocks.CROCODILE_BRICKS_SLAB.get(), 2, recipeOutput);

        stonecutterBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.SMOOTH_CROCODILE_STONE_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.SMOOTH_CROCODILE_STONE_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.SMOOTH_CROCODILE_STONE_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.SMOOTH_CROCODILE_STONE_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.SMOOTH_CROCODILE_STONE_SLAB.get(), 2, recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.CROCODILE_TILES.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.CROCODILE_TILES_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.CROCODILE_TILES_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.CROCODILE_TILES_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.CROCODILE_TILES_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.SMOOTH_CROCODILE_STONE.get(), ModBlocks.CROCODILE_TILES_SLAB.get(), 2, recipeOutput);

        stonecutterBuilder(ModBlocks.CROCODILE_COBBLESTONE.get(), ModBlocks.CROCODILE_COBBLESTONE_FENCE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_COBBLESTONE.get(), ModBlocks.CROCODILE_COBBLESTONE_FENCE_GATE.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_COBBLESTONE.get(), ModBlocks.CROCODILE_COBBLESTONE_WALL.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_COBBLESTONE.get(), ModBlocks.CROCODILE_COBBLESTONE_STAIRS.get(), recipeOutput);
        stonecutterBuilder(ModBlocks.CROCODILE_COBBLESTONE.get(), ModBlocks.CROCODILE_COBBLESTONE_SLAB.get(), 2, recipeOutput);




        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_CRAFTING_TABLE.get())
                .pattern("WW")
                .pattern("SS")
                .define('W', Items.BLUE_WOOL)
                .define('S',ModBlocks.SMOOTH_LION_STONE.get())
                .unlockedBy("has_smooth_lion_stone", has(ModBlocks.SMOOTH_LION_STONE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.IRON_HAMMER.get())
                .pattern("NII")
                .pattern(" S ")
                .pattern(" S ")
                .define('N', Items.IRON_NUGGET)
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_iron", has(Items.IRON_INGOT)).save(recipeOutput);


        /// MY CUSTOM RECIPES

        /// THE SHAPELESS RECIPES FOR LION CRAFTING

        /*NonNullList<Ingredient> shapelessInputs = NonNullList.create();
        shapelessInputs.add(Ingredient.of(ModItems.BANANA.get()));
        shapelessInputs.add(Ingredient.of(ModBlocks.CHI_ORB_BLOCK.get()));

        ShapelessLionCraftingRecipe shapelessRecipe = new ShapelessLionCraftingRecipe(
                shapelessInputs,
                new ItemStack(ModItems.LION_CLUBIUS_MAXIMUS.get(), 1)
        );

        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_clubius_maximus_1"), shapelessRecipe, null);
*/

        /// THE SHAPED RECIPES FOR LION CRAFTING



        LionShapedRecipePattern ironLionChiHolderPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.IRON_INGOT),
                        'P', Ingredient.of(ModItems.IRON_PLATE.get()),
                        'T', Ingredient.of(ModItems.CHI_SHARD.get())
                ),
                List.of(
                        "PIP",
                        "ITI",
                        "PIP"
                )
        );
        ShapedLionCraftingRecipe ironLionChiHolderShapedRecipe = new ShapedLionCraftingRecipe(
                ironLionChiHolderPattern,
                new ItemStack(ModItems.IRON_CHI_HOLDER.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_chi_holder_rec"), ironLionChiHolderShapedRecipe, null);

        LionShapedRecipePattern ironPlatePattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.IRON_INGOT),
                        'H', Ingredient.of(ModItems.IRON_HAMMER.get())
                ),
                List.of(
                        " H ",
                        "III",
                        "III"
                )
        );
        ShapedLionCraftingRecipe ironPlateShapedRecipe = new ShapedLionCraftingRecipe(
                ironPlatePattern,
                new ItemStack(ModItems.IRON_PLATE.get(), 3)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_plate_rec"), ironPlateShapedRecipe, null);

        LionShapedRecipePattern ironLionCrossGuardPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.IRON_INGOT),
                        'H', Ingredient.of(ModItems.IRON_HAMMER.get())
                ),
                List.of(
                        " H ",
                        "III"
                )
        );
        ShapedLionCraftingRecipe ironLionCrossGuardShapedRecipe = new ShapedLionCraftingRecipe(
                ironLionCrossGuardPattern,
                new ItemStack(ModItems.IRON_CROSS_GUARD.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_cross_guard_rec"), ironLionCrossGuardShapedRecipe, null);

        LionShapedRecipePattern ironLionRodPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.IRON_INGOT)
                ),
                List.of(
                        "I",
                        "I",
                        "I"
                )
        );
        ShapedLionCraftingRecipe ironLionRodShapedRecipe = new ShapedLionCraftingRecipe(
                ironLionRodPattern,
                new ItemStack(ModItems.IRON_ROD.get(), 2)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_rod_rec"), ironLionRodShapedRecipe, null);

        LionShapedRecipePattern ironLionValiousPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.IRON_INGOT),
                        'C', Ingredient.of(ModItems.IRON_CHI_HOLDER.get()),
                        'G', Ingredient.of(ModItems.IRON_CROSS_GUARD.get()),
                        'P', Ingredient.of(ModItems.IRON_PLATE.get()),
                        'S', Ingredient.of(ModItems.IRON_ROD.get())
                ),
                List.of(
                        "I I",
                        "I I",
                        "ICI",
                        "GPG",
                        " S "
                )
        );
        ShapedLionCraftingRecipe ironLionValiousShapedRecipe = new ShapedLionCraftingRecipe(
                ironLionValiousPattern,
                new ItemStack(ModItems.LION_VALIOUS_GRAY.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_valious_rec"), ironLionValiousShapedRecipe, null);

        LionShapedRecipePattern ironLionClubiusPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.IRON_INGOT),
                        'P', Ingredient.of(ModItems.IRON_PLATE.get()),
                        'C', Ingredient.of(ModItems.IRON_CHI_HOLDER.get()),
                        'S', Ingredient.of(ModItems.IRON_ROD.get())
                ),
                List.of(
                        "III",
                        "III",
                        "PCP",
                        " S "
                )
        );
        ShapedLionCraftingRecipe ironLionClubiusShapedRecipe = new ShapedLionCraftingRecipe(
                ironLionClubiusPattern,
                new ItemStack(ModItems.LION_CLUBIUS_MAXIMUS.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_clubius_rec"), ironLionClubiusShapedRecipe, null);

        LionShapedRecipePattern ironLionJabakaPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.IRON_INGOT),
                        'P', Ingredient.of(ModItems.IRON_PLATE.get()),
                        'S', Ingredient.of(ModItems.IRON_ROD.get())
                ),
                List.of(
                        "   II",
                        "  PPI",
                        "  SP ",
                        " S   ",
                        "S    "
                )
        );
        ShapedLionCraftingRecipe ironLionJabakaShapedRecipe = new ShapedLionCraftingRecipe(
                ironLionJabakaPattern,
                new ItemStack(ModItems.LION_JABAKA.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_jabaka_rec"), ironLionJabakaShapedRecipe, null);

        LionShapedRecipePattern ironLionJahakPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.IRON_INGOT),
                        'P', Ingredient.of(ModItems.IRON_PLATE.get()),
                        'S', Ingredient.of(ModItems.IRON_ROD.get())
                ),
                List.of(
                        "   II",
                        "  PPI",
                        "  SP ",
                        " SII ",
                        "S I  "
                )
        );
        ShapedLionCraftingRecipe ironLionJahakShapedRecipe = new ShapedLionCraftingRecipe(
                ironLionJahakPattern,
                new ItemStack(ModItems.LION_JAHAK.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_jahak_rec"), ironLionJahakShapedRecipe, null);

        LionShapedRecipePattern ironLionFangiousPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.IRON_INGOT),
                        'P', Ingredient.of(ModItems.IRON_PLATE.get()),
                        'C', Ingredient.of(ModItems.IRON_CHI_HOLDER.get()),
                        'B', Ingredient.of(Items.BONE),
                        'S', Ingredient.of(ModItems.IRON_ROD.get())
                ),
                List.of(
                        "   I ",
                        " PIBI",
                        "PCPIB",
                        " S  I",
                        "S    "
                )
        );
        ShapedLionCraftingRecipe ironLionFangiousShapedRecipe = new ShapedLionCraftingRecipe(
                ironLionFangiousPattern,
                new ItemStack(ModItems.LION_FANGIOUS.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_fangious_rec"), ironLionFangiousShapedRecipe, null);

        LionShapedRecipePattern goldLionChiHolderPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.GOLD_INGOT),
                        'P', Ingredient.of(ModItems.GOLD_PLATE.get()),
                        'T', Ingredient.of(ModItems.CHI_SHARD.get())
                ),
                List.of(
                        "PIP",
                        "ITI",
                        "PIP"
                )
        );
        ShapedLionCraftingRecipe goldLionChiHolderShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionChiHolderPattern,
                new ItemStack(ModItems.GOLD_CHI_HOLDER.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_chi_holder_rec"), goldLionChiHolderShapedRecipe, null);

        LionShapedRecipePattern goldLionPlatePattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.GOLD_INGOT),
                        'H', Ingredient.of(ModItems.IRON_HAMMER.get())
                ),
                List.of(
                        " H ",
                        "III",
                        "III"
                )
        );
        ShapedLionCraftingRecipe goldLionPlateShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionPlatePattern,
                new ItemStack(ModItems.GOLD_PLATE.get(), 3)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_plate_rec"), goldLionPlateShapedRecipe, null);

        LionShapedRecipePattern goldLionCrossGuardPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.GOLD_INGOT),
                        'H', Ingredient.of(ModItems.IRON_HAMMER.get())
                ),
                List.of(
                        " H ",
                        "III"
                )
        );
        ShapedLionCraftingRecipe goldLionCrossGuardShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionCrossGuardPattern,
                new ItemStack(ModItems.GOLD_CROSS_GUARD.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_cross_guard_rec"), goldLionCrossGuardShapedRecipe, null);

        LionShapedRecipePattern goldLionRodPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.GOLD_INGOT)
                ),
                List.of(
                        "I",
                        "I",
                        "I"
                )
        );
        ShapedLionCraftingRecipe goldLionRodShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionRodPattern,
                new ItemStack(ModItems.GOLD_ROD.get(), 2)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_rod_rec"), goldLionRodShapedRecipe, null);

        LionShapedRecipePattern goldLionShouldPadsSpikedPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.GOLD_INGOT),
                        'P', Ingredient.of(ModItems.GOLD_PLATE.get()),
                        'C', Ingredient.of(ModItems.GOLD_CHI_HOLDER.get())
                ),
                List.of(
                        " I I ",
                        "PPPPP",
                        "PPCPP",
                        " PPP "
                )
        );
        ShapedLionCraftingRecipe goldLionShoulderPadsSpikedShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionShouldPadsSpikedPattern,
                new ItemStack(ModItems.GOLDEN_SHOULDER_SPIKED.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_golden_shoulder_pads_spiked_rec"), goldLionShoulderPadsSpikedShapedRecipe, null);

        LionShapedRecipePattern goldLionShouldPadsPattern = LionShapedRecipePattern.of(
                Map.of(
                        'I', Ingredient.of(Items.GOLD_INGOT),
                        'P', Ingredient.of(ModItems.GOLD_PLATE.get()),
                        'C', Ingredient.of(ModItems.GOLD_CHI_HOLDER.get())
                ),
                List.of(
                        "I   I",
                        "PPPPP",
                        "PPCPP",
                        " PPP "
                )
        );
        ShapedLionCraftingRecipe goldLionShoulderPadsShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionShouldPadsPattern,
                new ItemStack(ModItems.GOLDEN_SHOULDER_PADS.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_golden_shoulder_pads_rec"), goldLionShoulderPadsShapedRecipe, null);

        LionShapedRecipePattern goldLionJabakaPattern = LionShapedRecipePattern.of(
                Map.of(
                        'V', Ingredient.of(Items.IRON_INGOT),
                        'L', Ingredient.of(ModItems.IRON_PLATE.get()),
                        'P', Ingredient.of(ModItems.GOLD_PLATE.get()),
                        'C', Ingredient.of(ModItems.GOLD_CHI_HOLDER.get()),
                        'S', Ingredient.of(ModItems.GOLD_ROD.get())
                ),
                List.of(
                        "  LVV",
                        "  PLV",
                        " PCPL",
                        " SP  ",
                        "S    "
                )
        );
        ShapedLionCraftingRecipe goldLionJabakaShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionJabakaPattern,
                new ItemStack(ModItems.LION_CHI_JABAKA.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_jabaka_rec"), goldLionJabakaShapedRecipe, null);

        LionShapedRecipePattern goldLionDecalusPattern = LionShapedRecipePattern.of(
                Map.of(
                        'N', Ingredient.of(Items.GOLD_NUGGET),
                        'I', Ingredient.of(Items.GOLD_INGOT),
                        'P', Ingredient.of(ModItems.GOLD_PLATE.get()),
                        'C', Ingredient.of(ModItems.GOLD_CHI_HOLDER.get()),
                        'S', Ingredient.of(ModItems.GOLD_ROD.get())
                ),
                List.of(
                        "   NP",
                        "  NIN",
                        " NINI",
                        "PCN  ",
                        "SP   "
                )
        );
        ShapedLionCraftingRecipe goldLionDecalusShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionDecalusPattern,
                new ItemStack(ModItems.LION_DECALUS.get(), 1)
        );
        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_decalus_rec"), goldLionDecalusShapedRecipe, null);



    }

    private void brickBuilder(ItemLike input, ItemLike output, int c, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, c)
                .pattern("BB")
                .pattern("BB")
                .define('B', input)
                .unlockedBy(getHasName(input), has(input)).save(recipeOutput, ChiMod.MOD_ID + ":" + getItemName(output) + "_from_" + getItemName(input) + "_crafting_" + c + "_amount");
    }

    private void brickBuilder(ItemLike input, ItemLike output, RecipeOutput recipeOutput) {
        brickBuilder(input, output, 4, recipeOutput);
    }

    private void stonecutterBuilder(ItemLike input, ItemLike output, int c, RecipeOutput recipeOutput) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, output, c)
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput, ChiMod.MOD_ID + ":" + getItemName(output) + "_from_" + getItemName(input) + "_stonecutting_" + c + "_amount");
    }

    private void stonecutterBuilder(ItemLike input, ItemLike output, RecipeOutput recipeOutput) {
        this.stonecutterBuilder(input, output, 1, recipeOutput);
    }
}



/*

Map<Character, Ingredient> ironLionKey = Map.of(
        'I', Ingredient.of(Items.IRON_INGOT),
        'C', Ingredient.of(ModItems.IRON_CHI_HOLDER.get()),
        'G', Ingredient.of(ModItems.IRON_CROSS_GUARD.get()),
        'P', Ingredient.of(ModItems.IRON_PLATE.get()),
        'S', Ingredient.of(ModItems.IRON_ROD.get()),
        'T', Ingredient.of(ModItems.CHI_SHARD.get()),
        'H', Ingredient.of(ModItems.IRON_HAMMER.get()),
        'B', Ingredient.of(Items.BONE) //Temporary, until I add fangs
);


ShapedRecipePattern ironLionChiHolderPattern = ShapedRecipePattern.of(ironLionKey, List.of(
        "PIP",
        "ITI",
        "PIP"
));ShapedLionCraftingRecipe ironLionChiHolderShapedRecipe = new ShapedLionCraftingRecipe(
        ironLionChiHolderPattern,
        new ItemStack(ModItems.IRON_CHI_HOLDER.get(), 1)
);recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_chi_holder_rec"), ironLionChiHolderShapedRecipe, null);

ShapedRecipePattern ironPlatePattern = ShapedRecipePattern.of(ironLionKey, List.of(
        " H ",
        "III",
        "III"
));ShapedLionCraftingRecipe ironPlateShapedRecipe = new ShapedLionCraftingRecipe(
        ironPlatePattern,
        new ItemStack(ModItems.IRON_PLATE.get(), 3)
);recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_plate_rec"), ironPlateShapedRecipe, null);

ShapedRecipePattern ironLionCrossGuardPattern = ShapedRecipePattern.of(ironLionKey, List.of(
        " H ",
        "III"
));ShapedLionCraftingRecipe ironLionCrossGuardShapedRecipe = new ShapedLionCraftingRecipe(
        ironLionCrossGuardPattern,
        new ItemStack(ModItems.IRON_CROSS_GUARD.get(), 1)
);recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_cross_guard_rec"), ironLionCrossGuardShapedRecipe, null);

ShapedRecipePattern ironLionRodPattern = ShapedRecipePattern.of(ironLionKey, List.of(
        "I",
        "I",
        "I"
));ShapedLionCraftingRecipe ironLionRodShapedRecipe = new ShapedLionCraftingRecipe(
        ironLionRodPattern,
        new ItemStack(ModItems.IRON_ROD.get(), 2)
);recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_rod_rec"), ironLionRodShapedRecipe, null);

ShapedRecipePattern ironLionValiousPattern = ShapedRecipePattern.of(ironLionKey, List.of(
        "I I",
        "I I",
        "ICI",
        "GPG",
        " S "
));ShapedLionCraftingRecipe ironLionValiousShapedRecipe = new ShapedLionCraftingRecipe(
        ironLionValiousPattern,
        new ItemStack(ModItems.LION_VALIOUS_GRAY.get(), 1)
);recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_valious_rec"), ironLionValiousShapedRecipe, null);

ShapedRecipePattern ironLionClubiusPattern = ShapedRecipePattern.of(ironLionKey, List.of(
        "III",
        "III",
        "PCP",
        " S "
));ShapedLionCraftingRecipe ironLionClubiusShapedRecipe = new ShapedLionCraftingRecipe(
        ironLionClubiusPattern,
        new ItemStack(ModItems.LION_CLUBIUS_MAXIMUS.get(), 1)
);recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_clubius_rec"), ironLionClubiusShapedRecipe, null);

ShapedRecipePattern ironLionJabakaPattern = ShapedRecipePattern.of(ironLionKey, List.of(
        "   II",
        "  PPI",
        "  SP ",
        " S   ",
        "S    "
));ShapedLionCraftingRecipe ironLionJabakaShapedRecipe = new ShapedLionCraftingRecipe(
        ironLionJabakaPattern,
        new ItemStack(ModItems.LION_JABAKA.get(), 1)
);recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_jabaka_rec"), ironLionJabakaShapedRecipe, null);

ShapedRecipePattern ironLionJahakPattern = ShapedRecipePattern.of(ironLionKey, List.of(
        "   II",
        "  PPI",
        "  SP ",
        " SII ",
        "S I  "
));ShapedLionCraftingRecipe ironLionJahakShapedRecipe = new ShapedLionCraftingRecipe(
        ironLionJahakPattern,
        new ItemStack(ModItems.LION_JAHAK.get(), 1)
);recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_jahak_rec"), ironLionJahakShapedRecipe, null);

ShapedRecipePattern ironLionFangiousPattern = ShapedRecipePattern.of(ironLionKey, List.of(
        "   I ",
        " PIBI",
        "PCPIB",
        " S  I",
        "S    "
));ShapedLionCraftingRecipe ironLionFangiousShapedRecipe = new ShapedLionCraftingRecipe(
        ironLionFangiousPattern,
        new ItemStack(ModItems.LION_FANGIOUS.get(), 1)
);recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_iron_fangious_rec"), ironLionFangiousShapedRecipe, null);
        */




        /*Map<Character, Ingredient> goldLionKey = Map.of(
                'I', Ingredient.of(Items.GOLD_INGOT),
                'C', Ingredient.of(ModItems.GOLD_CHI_HOLDER.get()),
                'G', Ingredient.of(ModItems.GOLD_CROSS_GUARD.get()),
                'P', Ingredient.of(ModItems.GOLD_PLATE.get()),
                'S', Ingredient.of(ModItems.GOLD_ROD.get()),
                'T', Ingredient.of(ModItems.CHI_SHARD.get()),
                'H', Ingredient.of(ModItems.IRON_HAMMER.get()),
                'V', Ingredient.of(ModItems.IRON_PLATE.get()),
                'L', Ingredient.of(Items.IRON_INGOT),
                'N', Ingredient.of(Items.GOLD_NUGGET)
        );*/
/*

        ShapedRecipePattern goldLionChiHolderPattern = ShapedRecipePattern.of(goldLionKey, List.of(
                "PIP",
                "ITI",
                "PIP"
        ));ShapedLionCraftingRecipe goldLionChiHolderShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionChiHolderPattern,
                new ItemStack(ModItems.GOLD_CHI_HOLDER.get(), 1)
        );recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_chi_holder_rec"), goldLionChiHolderShapedRecipe, null);

        ShapedRecipePattern goldLionPlatePattern = ShapedRecipePattern.of(goldLionKey, List.of(
                " H ",
                "III",
                "III"
        ));ShapedLionCraftingRecipe goldLionPlateShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionPlatePattern,
                new ItemStack(ModItems.GOLD_PLATE.get(), 3)
        );recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_plate_rec"), goldLionPlateShapedRecipe, null);

        ShapedRecipePattern goldLionCrossGuardPattern = ShapedRecipePattern.of(goldLionKey, List.of(
                " H ",
                "III"
        ));ShapedLionCraftingRecipe goldLionCrossGuardShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionCrossGuardPattern,
                new ItemStack(ModItems.GOLD_CROSS_GUARD.get(), 1)
        );recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_cross_guard_rec"), goldLionCrossGuardShapedRecipe, null);

        ShapedRecipePattern goldLionRodPattern = ShapedRecipePattern.of(goldLionKey, List.of(
                "I",
                "I",
                "I"
        ));ShapedLionCraftingRecipe goldLionRodShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionRodPattern,
                new ItemStack(ModItems.GOLD_ROD.get(), 2)
        );recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_rod_rec"), goldLionRodShapedRecipe, null);

        ShapedRecipePattern goldLionShouldPadsSpikedPattern = ShapedRecipePattern.of(goldLionKey, List.of(
                " I I ",
                "PPPPP",
                "PPCPP",
                " PPP "
        ));ShapedLionCraftingRecipe goldLionShoulderPadsSpikedShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionShouldPadsSpikedPattern,
                new ItemStack(ModItems.GOLDEN_SHOULDER_SPIKED.get(), 1)
        );recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_golden_shoulder_pads_spiked_rec"), goldLionShoulderPadsSpikedShapedRecipe, null);

        ShapedRecipePattern goldLionShouldPadsPattern = ShapedRecipePattern.of(goldLionKey, List.of(
                "I   I",
                "PPPPP",
                "PPCPP",
                " PPP "
        ));ShapedLionCraftingRecipe goldLionShoulderPadsShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionShouldPadsPattern,
                new ItemStack(ModItems.GOLDEN_SHOULDER_PADS.get(), 1)
        );recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_golden_shoulder_pads_rec"), goldLionShoulderPadsShapedRecipe, null);

        ShapedRecipePattern goldLionJabakaPattern = ShapedRecipePattern.of(goldLionKey, List.of(
                "  LVV",
                "  PLV",
                " PCPL",
                " SP  ",
                "S    "
        ));ShapedLionCraftingRecipe goldLionJabakaShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionJabakaPattern,
                new ItemStack(ModItems.LION_CHI_JABAKA.get(), 1)
        );recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_jabaka_rec"), goldLionJabakaShapedRecipe, null);

        ShapedRecipePattern goldLionDecalusPattern = ShapedRecipePattern.of(goldLionKey, List.of(
                "   NP",
                "  NIN",
                " NINI",
                "PCN  ",
                "SP   "
        ));ShapedLionCraftingRecipe goldLionDecalusShapedRecipe = new ShapedLionCraftingRecipe(
                goldLionDecalusPattern,
                new ItemStack(ModItems.LION_DECALUS.get(), 1)
        );recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_gold_decalus_rec"), goldLionDecalusShapedRecipe, null);
*/
