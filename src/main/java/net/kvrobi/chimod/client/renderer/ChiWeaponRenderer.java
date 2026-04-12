package net.kvrobi.chimod.client.renderer;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.component.ModDataComponents;
import net.kvrobi.chimod.item.armor.custom.ChiArmor;
import net.kvrobi.chimod.item.custom.ChiWeapon;
import net.kvrobi.chimod.util.ItemDisplaySettings;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ChiWeaponRenderer extends GeoItemRenderer<ChiWeapon> {
    public ChiWeaponRenderer(ResourceLocation modelPath) {
        super(new DefaultedItemGeoModel<>(modelPath));
    }@Override
    public ResourceLocation getTextureLocation(ChiWeapon animatable) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(animatable);
        return ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID,
                "textures/geo/item/" + id.getPath() + ".png");
    }




    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (transformType == ItemDisplayContext.GUI && stack.getItem() instanceof ChiWeapon weapon) {
            ItemDisplaySettings s = weapon.getGuiSettings();

            poseStack.pushPose();
            poseStack.translate(s.transX(), s.transY(), s.transZ());
            poseStack.scale(s.scale(), s.scale(), s.scale());
            poseStack.mulPose(Axis.YP.rotationDegrees(s.rotationY()));
            poseStack.mulPose(Axis.XP.rotationDegrees(s.rotationX()));
            poseStack.mulPose(Axis.ZP.rotationDegrees(s.rotationZ()));


            super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
            poseStack.popPose();
        } else if (transformType.firstPerson() && stack.getItem() instanceof ChiWeapon weapon) {
            ItemDisplaySettings s = weapon.getGuiSettings();

            // Scale it down (e.g., 0.8f for 80% size)
            // You could also add a 'firstPersonScale' to your ItemDisplaySettings record!
            poseStack.pushPose();
            poseStack.scale(s.fpScale(), s.fpScale(), s.fpScale());
            poseStack.translate(s.fpTransX(), s.fpTransY(), s.fpTransZ());
            poseStack.mulPose(Axis.YP.rotationDegrees(s.fprotationY()));
            poseStack.mulPose(Axis.XP.rotationDegrees(s.fprotationX()));
            poseStack.mulPose(Axis.ZP.rotationDegrees(s.fprotationZ()));

            super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
            poseStack.popPose();
        } else if(transformType == ItemDisplayContext.GUI && stack.getItem() instanceof ChiArmor armor) {

            ItemDisplaySettings s = armor.getGuiSettings();

            poseStack.pushPose();
            poseStack.translate(s.transX(), s.transY(), s.transZ());
            poseStack.scale(s.scale(), s.scale(), s.scale());
            /*poseStack.mulPose(Axis.YP.rotationDegrees(s.rotationY()));
            poseStack.mulPose(Axis.XP.rotationDegrees(s.rotationX()));
            poseStack.mulPose(Axis.ZP.rotationDegrees(s.rotationZ()));*/

            super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
            poseStack.popPose();


        } else {
            super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
        }
    }

    /*@Override
    public void renderRecursively(PoseStack poseStack, ChiWeapon animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isRebind, float partialTick, int packedLight, int packedOverlay, int colour) {
        ItemStack stack = this.getCurrentItemStack();

        if (stack != null && bone.getName().equals("the_part_that_shrinks")) {
            boolean isActive = stack.getOrDefault(ModDataComponents.IS_ACTIVE.get(), false);

            var manager = animatable.getAnimatableInstanceCache().getManagerForId(GeoItem.getId(stack));
            var controller = manager.getAnimationControllers().get("base_controller");

            boolean isTransitioning = false;
            if (controller != null && controller.getCurrentAnimation() != null) {
                // Correct GeckoLib 4 way to get the animation name
                String animName = controller.getCurrentAnimation().animation().name();
                isTransitioning = animName.contains("activating") || animName.contains("deactivating");
            }

            // Only force the scale if we aren't currently transitioning
            if (!isTransitioning) {
                float scale = isActive ? 1f : 0f;
                bone.setScaleX(scale);
                bone.setScaleY(scale);
                bone.setScaleZ(scale);
            }
        }

        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isRebind, partialTick, packedLight, packedOverlay, colour);
    }*/
}
