package net.kvrobi.chimod.world.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.kvrobi.chimod.world.registration.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public record ShapedLionCraftingRecipe(int width, int height, NonNullList<Ingredient> ingredients, ItemStack result) implements CraftingRecipe {

    @Override
    public boolean matches(CraftingInput input, Level level) {
        // Change the 3s to 5s because your table is 5x5!, later, for now I want it to stay 3x3
        for (int startX = 0; startX <= 3 - this.width; ++startX) {
            for (int startY = 0; startY <= 3 - this.height; ++startY) {
                if (this.matchesAt(input, startX, startY, false)) return true;
                if (this.matchesAt(input, startX, startY, true)) return true;
            }
        }
        return false;
    }

    private boolean matchesAt(CraftingInput input, int startX, int startY, boolean mirrored) {
        // Loop through your 5x5 grid
        for (int gridX = 0; gridX < 3; ++gridX) {
            for (int gridY = 0; gridY < 3; ++gridY) {
                int recipeX = gridX - startX;
                int recipeY = gridY - startY;
                Ingredient ingredient = Ingredient.EMPTY;

                if (recipeX >= 0 && recipeY >= 0 && recipeX < this.width && recipeY < this.height) {
                    // Calculate the index for the ingredient list
                    int listIndex;
                    if (mirrored) {
                        listIndex = this.width - recipeX - 1 + recipeY * this.width;
                    } else {
                        listIndex = recipeX + recipeY * this.width;
                    }

                    // --- THE FIX: Check if the index is within the list size ---
                    if (listIndex >= 0 && listIndex < this.ingredients.size()) {
                        ingredient = this.ingredients.get(listIndex);
                    }
                }

                if (!ingredient.test(input.getItem(gridX + gridY * 3))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= this.width && height >= this.height;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.LION_SHAPED_CRAFTING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.LION_CRAFTING_TYPE.get();
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    // --- CUSTOM 5x5 SERIALIZER ---
    public static class Serializer implements RecipeSerializer<ShapedLionCraftingRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        // Notice how we read the width, height, and list explicitly now
        public static final MapCodec<ShapedLionCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.INT.fieldOf("width").forGetter(ShapedLionCraftingRecipe::width),
                Codec.INT.fieldOf("height").forGetter(ShapedLionCraftingRecipe::height),
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").forGetter(ShapedLionCraftingRecipe::ingredients),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(ShapedLionCraftingRecipe::result)
        ).apply(inst, (width, height, ingredientsList, result) -> {
            NonNullList<Ingredient> nonNullIngredients = NonNullList.create();
            nonNullIngredients.addAll(ingredientsList);
            return new ShapedLionCraftingRecipe(width, height, nonNullIngredients, result);
        }));

        public static final StreamCodec<RegistryFriendlyByteBuf, ShapedLionCraftingRecipe> STREAM_CODEC = StreamCodec.of(
                Serializer::toNetwork, Serializer::fromNetwork
        );

        @Override
        public MapCodec<ShapedLionCraftingRecipe> codec() { return CODEC; }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ShapedLionCraftingRecipe> streamCodec() { return STREAM_CODEC; }

        private static ShapedLionCraftingRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            int width = buf.readVarInt();
            int height = buf.readVarInt();
            int size = buf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for(int i = 0; i < size; i++) {
                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            }
            return new ShapedLionCraftingRecipe(width, height, ingredients, ItemStack.STREAM_CODEC.decode(buf));
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, ShapedLionCraftingRecipe recipe) {
            buf.writeVarInt(recipe.width());
            buf.writeVarInt(recipe.height());
            buf.writeVarInt(recipe.ingredients().size());
            for (Ingredient ingredient : recipe.ingredients()) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
            }
            ItemStack.STREAM_CODEC.encode(buf, recipe.result());
        }
    }
}