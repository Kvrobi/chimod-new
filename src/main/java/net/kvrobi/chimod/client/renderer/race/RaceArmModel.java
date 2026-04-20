package net.kvrobi.chimod.client.renderer.race;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RaceArmModel<T extends RaceProxy> extends GeoModel<T> {

    @Override
    public ResourceLocation getModelResource(RaceProxy proxy) {
        // Automatically grabs "eagle_arm.geo.json", "crow_arm.geo.json", etc.
        String race = proxy.getPlayer().getData(net.kvrobi.chimod.util.ModAttachments.RACE_DATA.get()).getRace().toString().toLowerCase();
        return ResourceLocation.fromNamespaceAndPath("kvrobichimod", "geo/entity/" + race + "_arm.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RaceProxy proxy) {
        String race = proxy.getPlayer().getData(net.kvrobi.chimod.util.ModAttachments.RACE_DATA.get()).getRace().toString().toLowerCase();
        return ResourceLocation.fromNamespaceAndPath("kvrobichimod", "textures/geo/entity/" + race + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(RaceProxy proxy) {
        String race = proxy.getPlayer().getData(net.kvrobi.chimod.util.ModAttachments.RACE_DATA.get()).getRace().toString().toLowerCase();
        return ResourceLocation.fromNamespaceAndPath("kvrobichimod", "animations/entity/" + race + ".animation.json");
    }
}
