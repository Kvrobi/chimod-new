package net.kvrobi.chimod.client.screen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.world.inventory.ChiMenu;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class ChiMenuScreen extends AbstractContainerScreen<ChiMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "textures/gui/chi_menu.png");

    public ChiMenuScreen(ChiMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = 8;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        int x = (width - imageWidth) / 2 + 1;
        int y = (height - imageHeight) / 2 + 30;

        guiGraphics.blit(TEXTURE, x, y, 0 ,0, imageWidth, imageHeight-23);

        renderEnergyBar(guiGraphics, x, y);
    }

    private void renderEnergyBar(GuiGraphics guiGraphics, int x, int y) {
        int destX = x + 40;
        int destY = y + 24;

        int barWidth = 111;
        int barHeight = 7;

        float energy = this.menu.getEnergyPercentage();
        int filledWidth = (int)(energy *barWidth);

        guiGraphics.blit(TEXTURE, destX, destY, 40, 24, barWidth, 7);

        int barX = 39;
        int barY = 144;
        //int filledBarSourveV = 200;

        guiGraphics.blit(TEXTURE, destX, destY, barX, barY , filledWidth, 7);

        int textX = x + 106;
        int textY = y + 6;


        String text = (int)(energy*100) + "%";
        guiGraphics.drawCenteredString(this.font, text, textX, textY, 0x00E5FF);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
