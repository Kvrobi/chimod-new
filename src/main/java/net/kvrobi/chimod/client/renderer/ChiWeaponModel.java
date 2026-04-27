package net.kvrobi.chimod.client.renderer;

import net.kvrobi.chimod.item.custom.ChiWeapon;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChiWeaponModel extends GeoModel<ChiWeapon> {
    @Override
    public ResourceLocation getModelResource(ChiWeapon animatable) {

        ResourceLocation id = BuiltInRegistries.ITEM.getKey(animatable);

        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "geo/weapon/" + id.getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChiWeapon animatable) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(animatable);

        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "textures/geo/item/" + id.getPath() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChiWeapon animatable) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(animatable);

        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "animations/" + id.getPath() + ".animation.json");
    }
}

