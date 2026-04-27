package net.kvrobi.chimod.client;

import net.kvrobi.chimod.client.renderer.race.RaceLayerWrapper;
import net.kvrobi.chimod.client.renderer.race.RaceProxy;
import net.kvrobi.chimod.network.OpenMenuPayload;
import net.kvrobi.chimod.network.OpenRaceMenuPayload;
import net.kvrobi.chimod.network.ToggleArmorPayload;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.Race;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
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

        if (!mc.player.isCreative() && !mc.player.isSpectator()) {
            Race race = mc.player.getData(ModAttachments.RACE_DATA.get()).getRace();

            if (race == Race.EAGLE || race == Race.RAVEN) {
                var flightData = mc.player.getData(ModAttachments.FLIGHT_DATA.get());

                if ((!mc.player.getAbilities().flying /*mc.player.isUnderWater() || mc.player.isInWater() || mc.player.onGround()*/) && flightData.getCurrentEnergy() < flightData.getMaxEnergy()) {
                    flightData.recharge(0.75f);
                }

                if (mc.player.getAbilities().flying) {
                    flightData.consume(1);
                }
            }
        }


        for(Map.Entry<KeyMapping, Consumer<Minecraft>> entry : keyActions.entrySet()) {
            while(entry.getKey().consumeClick()) {
                entry.getValue().accept(mc);
            }
        }
    }

    private void handleOpenRaceMenu(Minecraft mc) {
        PacketDistributor.sendToServer(new OpenRaceMenuPayload());
    }

    @SubscribeEvent
    public void onPlayerSpawn(net.neoforged.neoforge.event.entity.EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide() && event.getEntity() instanceof net.minecraft.client.player.LocalPlayer) {

            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new net.kvrobi.chimod.network.RequestRaceSyncPayload());
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

    @SubscribeEvent
    public void onLogOut(ClientPlayerNetworkEvent.LoggingOut evemt) {
        RaceLayerWrapper.PROXY_CACHE.clear();
    }

    private static final net.kvrobi.chimod.client.renderer.race.RaceArmRenderer ARM_RENDERER =
            new net.kvrobi.chimod.client.renderer.race.RaceArmRenderer();

    public static void onRenderArm(net.neoforged.neoforge.client.event.RenderArmEvent event) {
        net.minecraft.client.player.AbstractClientPlayer player = event.getPlayer();
        net.kvrobi.chimod.util.Race race = player.getData(net.kvrobi.chimod.util.ModAttachments.RACE_DATA.get()).getRace();

        if (race == net.kvrobi.chimod.util.Race.HUMAN) return;

        event.setCanceled(true);

        net.kvrobi.chimod.client.renderer.race.RaceProxy proxy =
                net.kvrobi.chimod.client.renderer.race.RaceLayerWrapper.PROXY_CACHE.computeIfAbsent(
                        player.getUUID(), id -> new net.kvrobi.chimod.client.renderer.race.RaceProxy(player)
                );

        if(proxy.getPlayer() != player) {
            proxy = new RaceProxy(player);
            RaceLayerWrapper.PROXY_CACHE.put(player.getUUID(), proxy);
        }

        var model = ARM_RENDERER.getGeoModel();
        var bakedModel = model.getBakedModel(model.getModelResource(proxy));
        if (bakedModel == null) return;

        boolean isRight = event.getArm() == net.minecraft.world.entity.HumanoidArm.RIGHT;

        var rightArm = model.getAnimationProcessor().getBone("right_arm");
        var leftArm = model.getAnimationProcessor().getBone("left_arm");
        if (rightArm != null) rightArm.setHidden(!isRight);
        if (leftArm != null) leftArm.setHidden(isRight);

        com.mojang.blaze3d.vertex.PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();

        poseStack.scale(-0.9f, -0.9f, 0.9f);
        float shiftX = isRight ? -0.3f : -0.7f;
        float shiftY = isRight ? -0.475f : -0.475f;
        float shiftZ = isRight ? 0.45f : 0.45f;

        poseStack.translate(shiftX, shiftY, shiftZ);

        float pitch = -90.0f;
        float yaw = -0.0f;
        float roll = 0.0f;

        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(pitch));
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(yaw));
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(roll));


        net.minecraft.client.renderer.RenderType renderType =
                net.minecraft.client.renderer.RenderType.entityCutoutNoCull(model.getTextureResource(proxy));

        float partialTick = net.minecraft.client.Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(true);

        ARM_RENDERER.reRender(bakedModel, poseStack, event.getMultiBufferSource(), proxy,
                renderType, event.getMultiBufferSource().getBuffer(renderType),
                partialTick, event.getPackedLight(),
                net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY, -1);

        poseStack.popPose();
    }
}
