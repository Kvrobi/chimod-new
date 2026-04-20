package net.kvrobi.chimod.client.renderer.race;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.GeckoLibCache;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class RaceLayer extends GeoRenderLayer<RaceProxy> {
    private final RacePlayerModel<RaceProxy> raceModel = new RacePlayerModel<>();

    public RaceLayer(GeoRenderer<RaceProxy> entityRendererIn) {
        super(entityRendererIn);
    }

    public void renderRace(PoseStack poseStack, RaceProxy animatable, MultiBufferSource bufferSource,
                           float partialTick, int packedLight, int packedOverlay) {

        ResourceLocation modelLoc = this.raceModel.getModelResource(animatable);
        BakedGeoModel bakedModel = GeckoLibCache.getBakedModels().get(modelLoc);

        if (bakedModel != null) {
            poseStack.pushPose();

            // Flip the model right-side up
            poseStack.mulPose(Axis.XP.rotationDegrees(180f));
            poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(180f));
            // You may also need to translate it back into position depending on your .geo.json
            poseStack.translate(0.501, -2.001, 0.501);

            RenderType renderType = RenderType.entityCutoutNoCull(this.raceModel.getTextureResource(animatable));

            getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable,
                    renderType, bufferSource.getBuffer(renderType),
                    partialTick, packedLight, packedOverlay, -1);

            poseStack.popPose();
        }
    }

    @Override
    public void render(PoseStack poseStack, RaceProxy animatable, BakedGeoModel bakedModel, RenderType renderType,
                       MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
                       int packedLight, int packedOverlay) {

        ResourceLocation modelResource = this.raceModel.getModelResource(animatable);

        BakedGeoModel raceBakedModel = getGeoModel().getBakedModel(modelResource);

        RenderType raceRenderType = RenderType.entityCutoutNoCull(this.raceModel.getTextureResource(animatable));

        getRenderer().reRender(
                raceBakedModel,
                poseStack,
                bufferSource,
                animatable,
                raceRenderType,
                bufferSource.getBuffer(raceRenderType),
                partialTick,
                packedLight,
                packedOverlay,
                -1 // Color (White/Default)
        );
    }
}
