package net.kvrobi.chimod.client.renderer;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.item.armor.custom.ChiArmor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ChiArmorRenderer extends GeoArmorRenderer<ChiArmor> {
    public ChiArmorRenderer(ResourceLocation modelPath) {
        super(new DefaultedItemGeoModel<>(modelPath));
    }
    @Override
    public ResourceLocation getTextureLocation(ChiArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "textures/geo/" + animatable.getGeoModelPath().getPath() + ".png");
    }

}
