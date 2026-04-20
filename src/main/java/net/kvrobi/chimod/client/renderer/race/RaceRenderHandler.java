
package net.kvrobi.chimod.client.renderer.race;

import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.Race;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RaceRenderHandler {
    private static final Map<UUID, RaceProxy> PROXY_CACHE = new HashMap<>();

    public static void onPlayerRender(RenderPlayerEvent.Pre event) {
        Player player = event.getEntity();
        Race race = player.getData(ModAttachments.RACE_DATA.get()).getRace();

        if (race != Race.HUMAN) {
            var model = event.getRenderer().getModel();

            model.head.visible = false;
            model.hat.visible = false;
            model.body.visible = false;
            model.rightArm.visible = false;
            model.leftArm.visible = false;
            model.rightLeg.visible = false;
            model.leftLeg.visible = false;
            model.jacket.visible = false;
            model.rightSleeve.visible = false;
            model.leftSleeve.visible = false;
            model.rightPants.visible = false;
            model.leftPants.visible = false;
        }
    }

    //for now!!! it's for the inventory
    /*public static void onPlayerRenderPost(RenderPlayerEvent.Post event) {
        // IMPORTANT: Reset visibility so the player still looks normal
        // in the Inventory GUI or when switching back to Human.
        var model = event.getRenderer().getModel();

        model.head.visible = true;
        model.hat.visible = true;
        model.body.visible = true;
        model.rightArm.visible = true;
        model.leftArm.visible = true;
        model.rightLeg.visible = true;
        model.leftLeg.visible = true;

        model.jacket.visible = true;
        model.rightSleeve.visible = true;
        model.leftSleeve.visible = true;
        model.rightPants.visible = true;
        model.leftPants.visible = true;
    }*/
}

