package net.kvrobi.chimod.world.inventory;

import net.kvrobi.chimod.item.custom.ChiOrbItem;
import net.kvrobi.chimod.util.data.ChiData;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.world.registration.ModMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class ChiMenu extends AbstractContainerMenu {
    private final Player player;

    public ChiMenu(int id, Inventory inv) {
        super(ModMenuTypes.CHI_MENU.get(), id);
        ItemStackHandler pocket = inv.player.getData(ModAttachments.CHI_ENERGY).getInventory();
        this.addSlot(new SlotItemHandler(pocket, 0, 20, 50) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof net.kvrobi.chimod.item.custom.ChiOrbItem;
            }
        });
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.player = inv.player;
    }



    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 10 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 10 + col * 18, 142));
        }
    }

    public float getEnergyPercentage() {
        ChiData data = player.getData(ModAttachments.CHI_ENERGY);
        int energy = data.getEnergy();
        return (energy / (float) 450.0f);
    }


    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0) {
                if (!this.moveItemStackTo(itemstack1, 1, 37, true)) return ItemStack.EMPTY;
            } else {
                if (itemstack1.getItem() instanceof ChiOrbItem) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) return ItemStack.EMPTY;
                }
            }
            if (itemstack1.isEmpty()) slot.setByPlayer(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return itemstack;
    }



    @Override
    public boolean stillValid(Player player) { return true; }
}


