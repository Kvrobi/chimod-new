package net.kvrobi.chimod.client.renderer.race;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoObjectRenderer;
import software.bernie.geckolib.model.GeoModel;

public class RaceArmRenderer extends GeoObjectRenderer<RaceProxy> {
    public RaceArmRenderer() {
        super(new RaceArmModel<>());
    }

    @Override
    public ResourceLocation getTextureLocation(RaceProxy animatable) {
        return getGeoModel().getTextureResource(animatable);
    }
}