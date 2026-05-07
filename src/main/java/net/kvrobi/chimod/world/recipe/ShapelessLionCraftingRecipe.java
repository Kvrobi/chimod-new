package net.kvrobi.chimod.world.recipe;

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

public record ShapelessLionCraftingRecipe(NonNullList<Ingredient> ingredients, ItemStack output) implements CraftingRecipe {

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        }
        else if (input.size() == 1 && this.ingredients.size() == 1) {
            return this.ingredients.getFirst().test(input.getItem(0));
        }
        else {
            return input.stackedContents().canCraft(this, null);
        }
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.ingredients.size();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.LION_SHAPELESS_CRAFTING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.LION_CRAFTING_TYPE.get();
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    public static class Serializer implements RecipeSerializer<ShapelessLionCraftingRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        public static final MapCodec<ShapelessLionCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").forGetter(ShapelessLionCraftingRecipe::ingredients),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(ShapelessLionCraftingRecipe::output)
        ).apply(inst, (ingredientsList, result) -> {
            NonNullList<Ingredient> nonNullIngredients = NonNullList.create();
            nonNullIngredients.addAll(ingredientsList);
            return new ShapelessLionCraftingRecipe(nonNullIngredients, result);
        }));

        public static final StreamCodec<RegistryFriendlyByteBuf, ShapelessLionCraftingRecipe> STREAM_CODEC = StreamCodec.of(
                Serializer::toNetwork, Serializer::fromNetwork
        );

        @Override
        public MapCodec<ShapelessLionCraftingRecipe> codec() { return CODEC; }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ShapelessLionCraftingRecipe> streamCodec() { return STREAM_CODEC; }

        private static ShapelessLionCraftingRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            int size = buf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for(int i = 0; i < size; i++) {
                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            }
            return new ShapelessLionCraftingRecipe(ingredients, ItemStack.STREAM_CODEC.decode(buf));
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, ShapelessLionCraftingRecipe recipe) {
            buf.writeVarInt(recipe.ingredients().size());
            for (Ingredient ingredient : recipe.ingredients()) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
            }
            ItemStack.STREAM_CODEC.encode(buf, recipe.output());
        }
    }
}