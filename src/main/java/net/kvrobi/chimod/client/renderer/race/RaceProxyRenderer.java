package net.kvrobi.chimod.client.renderer.race;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoObjectRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class RaceProxyRenderer extends GeoObjectRenderer<RaceProxy> {
    public RaceProxyRenderer() {
        super(new RacePlayerModel<>());
    }

    // These are required by the abstract class but usually
    // ignored when just using the renderer for a layer.
    @Override public ResourceLocation getTextureLocation(RaceProxy animatable) {
        return getGeoModel().getTextureResource(animatable);
    }
}