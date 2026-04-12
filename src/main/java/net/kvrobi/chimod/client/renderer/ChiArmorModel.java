package net.kvrobi.chimod.client.renderer;

import net.kvrobi.chimod.item.armor.custom.ChiArmor;
import net.kvrobi.chimod.item.custom.ChiWeapon;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChiArmorModel extends GeoModel<ChiArmor> {
    @Override
    public ResourceLocation getModelResource(ChiArmor animatable) {

        ResourceLocation id = BuiltInRegistries.ITEM.getKey(animatable);

        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "geo/armor/" + id.getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChiArmor animatable) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(animatable);

        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "textures/geo/armor/" + id.getPath() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChiArmor animatable) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(animatable);

        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "animations/armor/" + id.getPath() + ".animation.json");
    }
}
