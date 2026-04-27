package net.kvrobi.chimod.client.renderer;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.item.armor.custom.ChiArmor;
import net.kvrobi.chimod.item.custom.ChiWeapon;
import net.kvrobi.chimod.util.ItemDisplaySettings;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

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

}
