package net.kvrobi.chimod.world.registration;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.world.recipe.ShapedLionCraftingRecipe;
import net.kvrobi.chimod.world.recipe.ShapelessLionCraftingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
    public  static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, ChiMod.MOD_ID);

    public  static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, ChiMod.MOD_ID);

    //LION
    public static final Supplier<RecipeType<?>> LION_CRAFTING_TYPE =
            RECIPE_TYPES.register("lion_crafting", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "lion_crafting";
                }
            });

    public static final Supplier<RecipeSerializer<ShapelessLionCraftingRecipe>> LION_SHAPELESS_CRAFTING_SERIALIZER =
            RECIPE_SERIALIZERS.register("lion_shapeless_crafting", () -> ShapelessLionCraftingRecipe.Serializer.INSTANCE);

    public static final Supplier<RecipeSerializer<ShapedLionCraftingRecipe>> LION_SHAPED_CRAFTING_SERIALIZER =
            RECIPE_SERIALIZERS.register("lion_shaped_crafting", () -> ShapedLionCraftingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
