package net.kvrobi.chimod.client.gui;

import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.Race;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

public class FlightEnergyOverlay {
    @SubscribeEvent
    public void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || player.isSpectator()) return;

        Race race = player.getData(ModAttachments.RACE_DATA.get()).getRace();
        if (race == Race.EAGLE || race == Race.RAVEN) {

            var flightData = player.getData(ModAttachments.FLIGHT_DATA.get());

            int screenWidth = mc.getWindow().getGuiScaledWidth();
            int screenHeight = mc.getWindow().getGuiScaledHeight();

            int barWidth = 80;
            int barHeight = 4;

            int x = (screenWidth / 2) + 10;
            int y = screenHeight - 49;

            float fillPercentage = (float) flightData.getCurrentEnergy() / flightData.getMaxEnergy();
            int filledWidth = (int) (barWidth * fillPercentage);

            GuiGraphics graphics = event.getGuiGraphics();

            // Colors use ARGB hex format: 0x [Alpha] [Red] [Green] [Blue]
            // Alpha is opacity. FF = 100% visible.

            // 1. Draw a 1-pixel Black Border
            graphics.fill(x - 1, y - 1, x + barWidth + 1, y + barHeight + 1, 0xFF000000);

            // 2. Draw the Background (Dark Gray)
            graphics.fill(x, y, x + barWidth, y + barHeight, 0xFF444444);

            // 3. Draw the Foreground Energy (Cool Cyan / Chi Blue)
            graphics.fill(x, y, x + filledWidth, y + barHeight, 0xFF00E5FF);
        }
    }
}
