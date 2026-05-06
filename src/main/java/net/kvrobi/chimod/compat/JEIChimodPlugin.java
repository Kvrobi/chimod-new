package net.kvrobi.chimod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.world.recipe.ShapedLionCraftingRecipe;
import net.kvrobi.chimod.world.recipe.ShapelessLionCraftingRecipe;
import net.kvrobi.chimod.world.registration.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

@JeiPlugin
public class JEIChimodPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new LionCraftingRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        // Allow both Shaped AND Shapeless recipes into the list
        List<CraftingRecipe> lionRecipes = recipeManager
                .getAllRecipesFor((RecipeType<CraftingRecipe>) ModRecipes.LION_CRAFTING_TYPE.get()).stream()
                .map(RecipeHolder::value)
                .filter(recipe -> recipe instanceof ShapedLionCraftingRecipe || recipe instanceof ShapelessLionCraftingRecipe)
                .toList();

        registration.addRecipes(LionCraftingRecipeCategory.LION_CRAFTING_RECIPE_TYPE, lionRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.LION_CRAFTING_TABLE.get()),
                LionCraftingRecipeCategory.LION_CRAFTING_RECIPE_TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(net.kvrobi.chimod.client.screen.LionCraftingScreen.class,
                110, 52, 24, 18,
                LionCraftingRecipeCategory.LION_CRAFTING_RECIPE_TYPE);
    }
}