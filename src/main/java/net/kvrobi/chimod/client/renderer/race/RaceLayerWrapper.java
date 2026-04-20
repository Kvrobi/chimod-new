package net.kvrobi.chimod.client.renderer.race;

import com.mojang.blaze3d.vertex.PoseStack;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.Race;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import software.bernie.geckolib.animation.AnimatableManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RaceLayerWrapper extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    // We cache proxies to avoid creating new objects every frame [cite: 185]
    private final RaceLayer internalGeoLayer = new RaceLayer(new RaceProxyRenderer());
    public static final Map<UUID, RaceProxy> PROXY_CACHE = new HashMap<>();

    public RaceLayerWrapper(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player,
                       float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks,
                       float netHeadYaw, float headPitch) {

        Race race = player.getData(ModAttachments.RACE_DATA.get()).getRace();

        if (race != Race.HUMAN) {
            RaceProxy proxy = PROXY_CACHE.computeIfAbsent(player.getUUID(), id -> new RaceProxy(player));

            // CRITICAL: Manually update the proxy's animation state
            // This is what makes state.isMoving() and timers actually work [cite: 181, 187]
            if (!Minecraft.getInstance().isPaused()) {
                // GeckoLib 4 uses the instance cache to handle the tick logic
                long animId = player.getId();
                var manager = proxy.getAnimatableInstanceCache().getManagerForId(animId);
                manager.updatedAt(proxy.getTick(proxy));
                boolean isMoving = player.walkDist > player.walkDistO || !player.onGround();
                software.bernie.geckolib.animation.AnimationState<RaceProxy> state =
                        new software.bernie.geckolib.animation.AnimationState<>(proxy, limbSwing, limbSwingAmount, partialTick, isMoving);

                this.internalGeoLayer.getRenderer().getGeoModel().handleAnimations(proxy, animId, state, partialTick);
            }

            this.internalGeoLayer.renderRace(poseStack, proxy, buffer, partialTick, packedLight, OverlayTexture.NO_OVERLAY, this.getParentModel());
        }
    }
}