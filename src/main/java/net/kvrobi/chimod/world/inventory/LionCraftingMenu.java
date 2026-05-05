package net.kvrobi.chimod.world.inventory;

import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.world.registration.ModMenuTypes;
import net.kvrobi.chimod.world.registration.ModRecipes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeType;

public class LionCraftingMenu extends AbstractCraftingMenu{

    public LionCraftingMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    public LionCraftingMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(ModMenuTypes.LION_CRAFTING_MENU.get(), containerId, playerInventory, access, ModRecipes.LION_CRAFTING_TYPE.get());
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.LION_CRAFTING_TABLE.get());
    }
}