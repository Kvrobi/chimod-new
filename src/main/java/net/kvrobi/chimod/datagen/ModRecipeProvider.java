package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.item.ModItems;
import net.kvrobi.chimod.world.recipe.ShapedLionCraftingRecipe;
import net.kvrobi.chimod.world.recipe.ShapelessLionCraftingRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
        List<ItemLike> CHI_ORB_SMELTABLES = List.of(ModItems.RAW_CHI,ModBlocks.CHI_ORE_BLOCK,ModBlocks.DEEPSLATE_CHI_ORE_BLOCK);
        List<ItemLike> L_STONE = List.of(ModBlocks.LION_COBBLESTONE);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_CHI_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_CHI.get())
                .unlockedBy("has_raw_chi", has(ModItems.RAW_CHI)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_CHI_LAMP.get())
                .pattern("TGT")
                .pattern("G G")
                .pattern("TGT")
                .define('G', Items.GLASS)
                .define('T',ModBlocks.LION_ROCK_TILES.get())
                .unlockedBy("has_lion_rock_tiles", has(ModBlocks.LION_ROCK_TILES)).save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.LION_BRICKS.get(),4)
                .requires(ModBlocks.LION_STONE)
                .unlockedBy("has_lion_stone_block", has(ModBlocks.LION_STONE)).save(recipeOutput, "kvrobichimod:lion_bricks_from_four_lion_stone");


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_CHI.get(),9)
                .requires(ModBlocks.RAW_CHI_BLOCK)
                .unlockedBy("has_raw_chi_block", has(ModBlocks.RAW_CHI_BLOCK)).save(recipeOutput, "kvrobichimod:raw_chi_from_raw_chi_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHI_ORB.get(),1)
                .requires(ModBlocks.CHI_ORB_BLOCK)
                .unlockedBy("has_chi_orb_block", has(ModBlocks.CHI_ORB_BLOCK)).save(recipeOutput, "kvrobichimod:chi_orb_from_chi_orb_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CHI_ORB_BLOCK.get(),1)
                .requires(ModItems.CHI_ORB)
                .unlockedBy("has_chi_orb", has(ModItems.CHI_ORB)).save(recipeOutput, "kvrobichimod:chi_orb_block_from_chi_orb");

        /*ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_CHI.get(),1)
                .requires(ModBlocks.CHI_ORE_BLOCK)
                .unlockedBy("has_raw_chi_block", has(ModBlocks.RAW_CHI_BLOCK)).save(recipeOutput, "kvrobichimod:raw_chi_2");*/

        oreSmelting(recipeOutput, CHI_ORB_SMELTABLES, RecipeCategory.MISC, ModItems.CHI_ORB.get(), 0.25f,200, "chi_orb");
        oreBlasting(recipeOutput, CHI_ORB_SMELTABLES, RecipeCategory.MISC, ModItems.CHI_ORB.get(), 0.25f,100, "chi_orb");

        oreSmelting(recipeOutput, L_STONE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_STONE.get(), 0.25f,100, "lion_stone");
        oreBlasting(recipeOutput, L_STONE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LION_STONE.get(), 0.25f,100, "lion_stone");

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





        /// MY CUSTOM RECIPES

        /// THE SHAPELESS RECIPES FOR LION CRAFTING

        NonNullList<Ingredient> shapelessInputs = NonNullList.create();
        shapelessInputs.add(Ingredient.of(ModItems.BANANA.get()));
        shapelessInputs.add(Ingredient.of(ModBlocks.CHI_ORB_BLOCK.get()));

        ShapelessLionCraftingRecipe shapelessRecipe = new ShapelessLionCraftingRecipe(
                shapelessInputs,
                new ItemStack(ModItems.LION_CLUBIUS_MAXIMUS.get(), 1)
        );

        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_clubius_maximus_1"), shapelessRecipe, null);


        /// THE SHAPED RECIPES FOR LION CRAFTING


        Map<Character, Ingredient> key = Map.of(
                'F', Ingredient.of(ModBlocks.CHI_ORB_BLOCK.get()),
                'S', Ingredient.of(Items.STICK)
        );

        ShapedRecipePattern pattern = ShapedRecipePattern.of(key, List.of(
                " F ",
                " F ",
                " S "
        ));

        ShapedLionCraftingRecipe shapedRecipe = new ShapedLionCraftingRecipe(
                pattern.width(),
                pattern.height(),
                pattern.ingredients(),
                new ItemStack(ModItems.LION_VALIOUS_GRAY.get(), 1)
        );

        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_sword"), shapedRecipe, null);
    }
}
