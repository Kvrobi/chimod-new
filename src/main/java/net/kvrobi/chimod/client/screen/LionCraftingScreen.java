package net.kvrobi.chimod.client.screen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.world.inventory.LionCraftingMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class LionCraftingScreen extends AbstractContainerScreen<LionCraftingMenu> {

    // 1. Tell the game exactly where your texture is saved!
    // Make sure your file is at: src/main/resources/assets/chimod/textures/gui/chi_menu.png
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "textures/gui/lion_crafting_menu.png");

    public LionCraftingScreen(LionCraftingMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        // Default vanilla UI size. If your chi_menu.png is a different size, change these!
        this.imageWidth = 176;
        this.imageHeight = 202;
    }

    @Override
    protected void init() {
        super.init();
        // This adjusts the text labels ("Lion Crafting" and "Inventory") so they don't overlap slots
        this.titleLabelX = 8;
        this.titleLabelY = 6;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Darkens the game world behind the GUI
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        // Renders the background texture, the text, and the item slots
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // Renders the little tooltip box when you hover your mouse over an item
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        // Calculates the exact center of your monitor
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // Pastes your chi_menu.png directly into the center of the screen!
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }
}