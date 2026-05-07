package net.kvrobi.chimod.world.inventory;

import net.kvrobi.chimod.world.registration.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.CommonHooks;

public class LionResultSlot extends ResultSlot {
    private final CraftingContainer craftSlots;
    private final Player player;

    public LionResultSlot(Player player, CraftingContainer craftSlots, Container resultSlots, int slot, int x, int y) {
        super(player, craftSlots, resultSlots, slot, x, y);
        this.craftSlots = craftSlots;
        this.player = player;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        this.checkTakeAchievements(stack);
        CommonHooks.setCraftingPlayer(player);
        CraftingInput craftingInput = this.craftSlots.asCraftInput();
        NonNullList<ItemStack> nonNullList = player.level().getRecipeManager().getRemainingItemsFor((RecipeType<CraftingRecipe>) ModRecipes.LION_CRAFTING_TYPE.get(), craftingInput, player.level());
        CommonHooks.setCraftingPlayer(null);

        for (int i = 0; i < this.craftSlots.getContainerSize(); ++i) {
            ItemStack currentStack = this.craftSlots.getItem(i);

            if (!currentStack.isEmpty()) {
                ItemStack remainingStack = ItemStack.EMPTY;
                if (currentStack.getItem().hasCraftingRemainingItem(currentStack)) {
                    remainingStack = currentStack.getItem().getCraftingRemainingItem(currentStack);
                }

                this.craftSlots.removeItem(i, 1);
                currentStack = this.craftSlots.getItem(i);

                if (!remainingStack.isEmpty()) {
                    if (currentStack.isEmpty()) {
                        this.craftSlots.setItem(i, remainingStack);
                    } else if (ItemStack.isSameItemSameComponents(currentStack, remainingStack)) {
                        remainingStack.grow(currentStack.getCount());
                        this.craftSlots.setItem(i, remainingStack);
                    } else if (!this.player.getInventory().add(remainingStack)) {
                        this.player.drop(remainingStack, false);
                    }
                }
            }
        }

        CommonHooks.setCraftingPlayer(null);
    }

}
