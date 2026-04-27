package net.kvrobi.chimod.client.renderer.race;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kvrobi.chimod.util.Race;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class RaceLayer extends GeoRenderLayer<RaceProxy> {
    private final RacePlayerModel<RaceProxy> raceModel = new RacePlayerModel<>();

    public RaceLayer(GeoRenderer<RaceProxy> entityRendererIn) {
        super(entityRendererIn);
    }

    public void renderRace(PoseStack poseStack, RaceProxy animatable, MultiBufferSource bufferSource,
                           float partialTick, int packedLight, int packedOverlay, net.minecraft.client.model.PlayerModel<net.minecraft.client.player.AbstractClientPlayer> vanillaModel) {

        var model = getRenderer().getGeoModel();

        AbstractClientPlayer player = animatable.getPlayer();
        Race race = player.getData(net.kvrobi.chimod.util.ModAttachments.RACE_DATA.get()).getRace();
        //boolean isFlying = (race == net.kvrobi.chimod.util.Race.EAGLE || race == net.kvrobi.chimod.util.Race.RAVEN) && player.getAbilities().flying;

        //var head = model.getAnimationProcessor().getBone("head");
        syncBone(model, "body", vanillaModel.body, 0.0f, 0.0f, 0.0f);
        syncBone(model, "head", vanillaModel.head, 0.0f, 0.0f, 0.0f);
        syncBone(model, "right_arm", vanillaModel.rightArm, -5.0f, 2.0f, 0.0f);
        syncBone(model, "left_arm", vanillaModel.leftArm, 5.0f, 2.0f, 0.0f);
        syncBone(model, "right-leg", vanillaModel.rightLeg, -1.9f, 12.0f, 0.0f);
        syncBone(model, "left_leg", vanillaModel.leftLeg, 1.9f, 12.0f, 0.0f);

        ResourceLocation modelLoc = model.getModelResource(animatable);
        BakedGeoModel bakedModel = model.getBakedModel(modelLoc);

        if (bakedModel != null) {
            poseStack.pushPose();

            poseStack.scale(-1.0f, -1.0f, 1.0f);
            poseStack.translate(-0.501, -2.001, -0.501);


            RenderType renderType = net.minecraft.client.renderer.RenderType.entityCutoutNoCull(model.getTextureResource(animatable));

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
                -1
        );
    }
    private void syncBone(GeoModel<?> model, String geckoBoneName, ModelPart vanillaPart, float defaultX, float defaultY, float defaultZ) {
        var bone = model.getAnimationProcessor().getBone(geckoBoneName);
        if (bone != null) {
            bone.setRotX(-vanillaPart.xRot);
            bone.setRotY(-vanillaPart.yRot);
            bone.setRotZ(vanillaPart.zRot);
            bone.setPosX(-(vanillaPart.x - defaultX));
            bone.setPosY(-(vanillaPart.y - defaultY));
            bone.setPosZ((vanillaPart.z - defaultZ));
        }
    }

}
