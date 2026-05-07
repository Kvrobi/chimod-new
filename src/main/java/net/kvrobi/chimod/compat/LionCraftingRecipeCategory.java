package net.kvrobi.chimod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.world.recipe.ShapedLionCraftingRecipe;
import net.kvrobi.chimod.world.recipe.ShapelessLionCraftingRecipe;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import org.jetbrains.annotations.Nullable;

public class LionCraftingRecipeCategory implements IRecipeCategory<CraftingRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "lion_crafting");

    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID,
            "textures/gui/lion_crafting_menu.png");

    public static final RecipeType<CraftingRecipe> LION_CRAFTING_RECIPE_TYPE =
            new RecipeType<>(UID, CraftingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public LionCraftingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 112);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.LION_CRAFTING_TABLE.get()));
    }

    @Override
    public RecipeType<CraftingRecipe> getRecipeType() {
        return LION_CRAFTING_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.chimod.lion_crafting_table");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CraftingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.OUTPUT, 142, 53)
                .addItemStack(recipe.getResultItem(RegistryAccess.EMPTY));

        if (recipe instanceof ShapedLionCraftingRecipe shapedRecipe) {

            int width = shapedRecipe.pattern().width();
            int height = shapedRecipe.pattern().height();

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int index = j + (i * width);
                    int xPos = 12 + (j * 18);
                    int yPos = 17 + (i * 18);
                    builder.addSlot(RecipeIngredientRole.INPUT, xPos, yPos)
                            .addIngredients(shapedRecipe.getIngredients().get(index));
                }
            }

        } else if (recipe instanceof ShapelessLionCraftingRecipe shapelessRecipe) {

            int size = shapelessRecipe.getIngredients().size();

            for (int i = 0; i < size; i++) {
                int row = i / 5;
                int col = i % 5;

                int xPos = 12 + (col * 18);
                int yPos = 17 + (row * 18);

                builder.addSlot(RecipeIngredientRole.INPUT, xPos, yPos)
                        .addIngredients(shapelessRecipe.getIngredients().get(i));
            }
        }
    }
}