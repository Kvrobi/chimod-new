package net.kvrobi.chimod.world.gui;

import net.kvrobi.chimod.world.registration.ModMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class RaceSelectionMenu extends AbstractContainerMenu {
    public RaceSelectionMenu(int id, Inventory inv) {
        // Pass the MenuType and window ID to the super constructor
        super(ModMenuTypes.RACE_MENU.get(), id);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
