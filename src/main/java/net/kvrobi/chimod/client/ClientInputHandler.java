package net.kvrobi.chimod.client;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.network.OpenMenuPayload;
import net.kvrobi.chimod.network.ToggleArmorPayload;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class ClientInputHandler {
    private final Map<KeyMapping, Consumer<Minecraft>> keyActions = new HashMap<>();

    public ClientInputHandler() {
        register(ModKeyBindings.OPEN_CHI_MENU, this::handleOpenMenu);
        register(ModKeyBindings.ACTIVATE_ARMOR, this::handleActivateArmor);
    }

    private void register(KeyMapping key, Consumer<Minecraft> action) {
        keyActions.put(key, action);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.player == null) return;

        for(Map.Entry<KeyMapping, Consumer<Minecraft>> entry : keyActions.entrySet()) {
            while(entry.getKey().consumeClick()) {
                entry.getValue().accept(mc);
            }
        }
    }

    private void handleOpenMenu(Minecraft mc) {
        System.out.println("Dispatching Menu Packet!");
        PacketDistributor.sendToServer(new OpenMenuPayload());
    }

    private void handleActivateArmor(Minecraft mc) {
        System.out.println("Dispatching Armor Activation Packet!");
        PacketDistributor.sendToServer(new ToggleArmorPayload());
    }

    private static final net.kvrobi.chimod.client.renderer.race.RaceArmRenderer ARM_RENDERER =
            new net.kvrobi.chimod.client.renderer.race.RaceArmRenderer();

    public static void onRenderArm(net.neoforged.neoforge.client.event.RenderArmEvent event) {
        net.minecraft.client.player.AbstractClientPlayer player = event.getPlayer();
        net.kvrobi.chimod.util.Race race = player.getData(net.kvrobi.chimod.util.ModAttachments.RACE_DATA.get()).getRace();

        if (race == net.kvrobi.chimod.util.Race.HUMAN) return;

        // Cancel vanilla arm
        event.setCanceled(true);

        net.kvrobi.chimod.client.renderer.race.RaceProxy proxy =
                net.kvrobi.chimod.client.renderer.race.RaceLayerWrapper.PROXY_CACHE.computeIfAbsent(
                        player.getUUID(), id -> new net.kvrobi.chimod.client.renderer.race.RaceProxy(player)
                );

        var model = ARM_RENDERER.getGeoModel();
        var bakedModel = model.getBakedModel(model.getModelResource(proxy));
        if (bakedModel == null) return;

        boolean isRight = event.getArm() == net.minecraft.world.entity.HumanoidArm.RIGHT;

        // --- 1. ISOLATE THE CORRECT ARM ---
        var rightArm = model.getAnimationProcessor().getBone("right_arm");
        var leftArm = model.getAnimationProcessor().getBone("left_arm");
        if (rightArm != null) rightArm.setHidden(!isRight);
        if (leftArm != null) leftArm.setHidden(isRight);

        com.mojang.blaze3d.vertex.PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();

        // --- 2. CAMERA MATRIX MATH ---
        poseStack.scale(-1.0f, -1.0f, 1.0f);

        // This shifts your Blockbench shoulder pivot (X=5, Y=22) up to the camera origin (0,0,0).
        // If your arm is slightly off-center on screen, adjust these two numbers!
        float shiftX = isRight ? -5.0f / 16.0f : 5.0f / 16.0f;
        float shiftY = 22.0f / 16.0f;
        poseStack.translate(shiftX, shiftY, 0.0f);

        net.minecraft.client.renderer.RenderType renderType =
                net.minecraft.client.renderer.RenderType.entityCutoutNoCull(model.getTextureResource(proxy));

        float partialTick = net.minecraft.client.Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(true);

        // --- 3. RENDER ---
        ARM_RENDERER.reRender(bakedModel, poseStack, event.getMultiBufferSource(), proxy,
                renderType, event.getMultiBufferSource().getBuffer(renderType),
                partialTick, event.getPackedLight(),
                net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY, -1);

        poseStack.popPose();
    }
    private static void setBoneHidden(software.bernie.geckolib.cache.object.GeoBone bone, boolean hidden) {
        bone.setHidden(hidden);
        for (software.bernie.geckolib.cache.object.GeoBone child : bone.getChildBones()) {
            setBoneHidden(child, hidden);
        }
    }
}
