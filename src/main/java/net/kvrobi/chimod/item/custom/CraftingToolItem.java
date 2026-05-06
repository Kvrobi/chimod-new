package net.kvrobi.chimod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;

public class CraftingToolItem extends Item {
    public CraftingToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack toolCopy = itemStack.copy();
        toolCopy.setCount(1);
        int newDamage = itemStack.getDamageValue() + 1;

        if(newDamage >= toolCopy.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        toolCopy.setDamageValue(newDamage);
        return toolCopy;
    }
}
