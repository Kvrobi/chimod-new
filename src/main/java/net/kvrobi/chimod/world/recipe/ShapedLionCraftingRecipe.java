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

public record ShapedLionCraftingRecipe(LionShapedRecipePattern pattern, ItemStack result) implements CraftingRecipe {

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return this.pattern.matches(input);
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        return result.copy();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return result.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.pattern.ingredients();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= this.pattern.width() && height >= this.pattern.height();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.LION_SHAPED_CRAFTING_SERIALIZER.get(); // Make sure this matches your registry!
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.LION_CRAFTING_TYPE.get();
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    public static class Serializer implements RecipeSerializer<ShapedLionCraftingRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        public static final MapCodec<ShapedLionCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                LionShapedRecipePattern.MAP_CODEC.forGetter(ShapedLionCraftingRecipe::pattern),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(ShapedLionCraftingRecipe::result)
        ).apply(inst, ShapedLionCraftingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ShapedLionCraftingRecipe> STREAM_CODEC = StreamCodec.of(
                Serializer::toNetwork, Serializer::fromNetwork
        );

        @Override
        public MapCodec<ShapedLionCraftingRecipe> codec() { return CODEC; }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ShapedLionCraftingRecipe> streamCodec() { return STREAM_CODEC; }

        private static ShapedLionCraftingRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            LionShapedRecipePattern pattern = LionShapedRecipePattern.STREAM_CODEC.decode(buf);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
            return new ShapedLionCraftingRecipe(pattern, result);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, ShapedLionCraftingRecipe recipe) {
            LionShapedRecipePattern.STREAM_CODEC.encode(buf, recipe.pattern());
            ItemStack.STREAM_CODEC.encode(buf, recipe.result());
        }
    }
}