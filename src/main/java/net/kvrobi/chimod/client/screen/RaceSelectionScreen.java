package net.kvrobi.chimod.client.screen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.network.RaceSelectPayload;
import net.kvrobi.chimod.util.Race;
import net.kvrobi.chimod.world.gui.RaceSelectionMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class RaceSelectionScreen extends AbstractContainerScreen<RaceSelectionMenu> {
    private RaceList list;
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "textures/gui/race_selection_menu.png");

    public RaceSelectionScreen(RaceSelectionMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 256;
        this.imageHeight = 200;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.minecraft != null && this.minecraft.options.keyInventory.matches(keyCode, scanCode)) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    @Override
    protected void init() {
        super.init();

        int listTop = this.topPos + 30;
        int listBottom = this.topPos + 180;
        int listHeight = listBottom - listTop;
        int itemHeight = 24;

        this.list = new RaceList(this.minecraft, this.width, listHeight, listTop, itemHeight);
        this.addRenderableWidget(this.list);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0,
                this.imageWidth, this.imageHeight,
                194, 135,
                256, 256);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, this.topPos + 10, 0x00E5FF);
    }

    class RaceList extends ObjectSelectionList<RaceList.Entry> {
        public RaceList(net.minecraft.client.Minecraft mc, int width, int height, int top, int itemHeight) {
            super(mc, width, height, top, itemHeight);
            for (Race race : Race.values()) {
                this.addEntry(new Entry(race));
            }
        }

        class Entry extends ObjectSelectionList.Entry<Entry> {
            private final Race race;
            private final Button button;

            public Entry(Race race) {
                this.race = race;
                this.button = Button.builder(Component.literal(race.name()), (btn) -> {
                    PacketDistributor.sendToServer(new RaceSelectPayload(race));
                    RaceSelectionScreen.this.onClose();
                }).bounds(0, 0, 150, 20).build();
            }

            @Override
            public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean isHovered, float partialTick) {
                this.button.setX(left + (width / 2) - 75);
                this.button.setY(top + 2);
                this.button.render(guiGraphics, mouseX, mouseY, partialTick);
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                return this.button.mouseClicked(mouseX, mouseY, button);
            }

            @Override
            public Component getNarration() {
                return Component.literal(race.name());
            }
        }
    }
}