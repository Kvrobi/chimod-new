package net.kvrobi.chimod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.item.armor.custom.ChiArmor;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.function.Consumer;

public class ChiArmorRenderer extends GeoArmorRenderer<ChiArmor> {
    public ChiArmorRenderer(ResourceLocation modelPath) {
        super(new DefaultedItemGeoModel<>(modelPath));
    }
    @Override
    public ResourceLocation getTextureLocation(ChiArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "textures/geo/" + animatable.getGeoModelPath().getPath() + ".png");
    }
    /*@Override
    public void actuallyRender(PoseStack poseStack, ChiArmor animatable, GeoModel<ChiArmor> model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isRebind, float partialTick, int packedLight, int packedOverlay, int colour) {
        // If we aren't being worn by an entity, we reset the bones to the "Default" 3D pose
        if (this.getCurrentEntity() == null) {
            animatable.getAnimatableInstanceCache()
                    .getManagerForId(software.bernie.geckolib.animatable.GeoItem.getId(this.getCurrentItemStack()))
                    .getAnimationControllers()
                    .values()
                    .forEach(controller -> {
                        // This stops the animation and prevents the "invisible player" pose
                        controller.stop();
                    });
        }
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isRebind, partialTick, packedLight, packedOverlay, colour);
    }*/





}
