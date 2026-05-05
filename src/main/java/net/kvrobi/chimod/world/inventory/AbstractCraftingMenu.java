package net.kvrobi.chimod.world.inventory;

import net.kvrobi.chimod.world.registration.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.commands.PublishCommand;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Optional;

public abstract class AbstractCraftingMenu extends AbstractContainerMenu {
    protected final CraftingContainer craftSlots = new TransientCraftingContainer(this, 3, 3);
    protected final ResultContainer resultSlots = new ResultContainer();
    protected final ContainerLevelAccess access;
    protected final Player player;
    protected final RecipeType<?> recipeType;

    public AbstractCraftingMenu(MenuType<?> menuType, int containerId, Inventory playerInv, ContainerLevelAccess access, RecipeType<?> recipeType) {
        super(menuType, containerId);
        this.access = access;
        this.player = playerInv.player;
        this.recipeType = recipeType;

        this.addSlot(new ResultSlot(playerInv.player, this.craftSlots, this.resultSlots, 0, 124, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInv, i, 8 + i * 18, 142));
        }
    }

    @Override
    public abstract boolean stillValid(Player player);

    @Override
    public void slotsChanged(Container container) {
        this.access.execute((level, pos) -> {
            if (!level.isClientSide) {
                CraftingInput input = this.craftSlots.asCraftInput();
                Optional<RecipeHolder<CraftingRecipe>> optional = level.getRecipeManager()
                                .getRecipeFor((RecipeType<CraftingRecipe>) this.recipeType, input, level);
                if(optional.isPresent()) {
                    ItemStack result = optional.get().value().assemble(input, level.registryAccess());
                    this.resultSlots.setItem(0, result);
                } else {
                    this.resultSlots.setItem(0, ItemStack.EMPTY);
                }

            }
        });
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.access.execute((level, pos) -> {
            this.clearContainer(player, this.craftSlots);
        });
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();
            if(index == 0) {
                this.access.execute((level, pos) -> {
                    itemStack1.getItem().onCraftedBy(itemStack1, level, player);
                });
                if(!this.moveItemStackTo(itemStack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemStack1, itemStack);
            } else if(index >= 10 && index < 46) {
                if(!this.moveItemStackTo(itemStack1, 1,10, false)) {
                    if(index < 37) {
                        if(!this.moveItemStackTo(itemStack1, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemStack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemStack1,10, 37, false)) {
                return ItemStack.EMPTY;
            }

            if(itemStack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if(itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            if(itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemStack1);
            if(index == 0) {
                player.drop(itemStack1, false);
            }
        }
        return itemStack;
    }

}
