package net.kvrobi.chimod.util;

import net.kvrobi.chimod.block.ModBlocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModUtil {

    public static boolean isChi(ItemStack stack) {
        boolean result = stack.is(ModBlocks.CHI_ORB_BLOCK.asItem()) || stack.is(ModBlocks.FIRE_CHI_ORB_BLOCK.asItem());
        return result;
    }
    public static boolean hasChi(Player player) {
        boolean result = player.getInventory().contains(new ItemStack(ModBlocks.CHI_ORB_BLOCK)) ||
        player.getInventory().contains(new ItemStack(ModBlocks.FIRE_CHI_ORB_BLOCK));
        return result;
    }
}
