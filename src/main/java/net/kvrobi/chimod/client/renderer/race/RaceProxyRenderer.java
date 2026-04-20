package net.kvrobi.chimod.client.renderer.race;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoObjectRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class RaceProxyRenderer extends GeoObjectRenderer<RaceProxy> {
    public RaceProxyRenderer() {
        super(new RacePlayerModel<>());
    }

    // These are required by the abstract class but usually
    // ignored when just using the renderer for a layer.
    @Override public ResourceLocation getTextureLocation(RaceProxy animatable) {
        return getGeoModel().getTextureResource(animatable);
    }

    public void renderSingleBone(com.mojang.blaze3d.vertex.PoseStack poseStack, RaceProxy animatable,
                                 software.bernie.geckolib.cache.object.GeoBone bone,
                                 net.minecraft.client.renderer.RenderType renderType,
                                 net.minecraft.client.renderer.MultiBufferSource bufferSource,
                                 com.mojang.blaze3d.vertex.VertexConsumer buffer,
                                 float partialTick, int packedLight, int packedOverlay, int colour) {
        // This bypasses the full model and draws ONLY this specific bone!
        this.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer,
                true, partialTick, packedLight, packedOverlay, colour);
    }
}