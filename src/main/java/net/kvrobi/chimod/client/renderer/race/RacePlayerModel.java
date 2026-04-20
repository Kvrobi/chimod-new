
package net.kvrobi.chimod.client.renderer.race;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.RaceData;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;


public class RacePlayerModel<T extends RaceProxy> extends GeoModel<T> {

    @Override
    public ResourceLocation getModelResource(RaceProxy animatable) {
        RaceData data = animatable.getPlayer().getData(ModAttachments.RACE_DATA.get());
        return ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "geo/entity/" + data.getRace().toString().toLowerCase() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RaceProxy animatable) {
        RaceData data = animatable.getPlayer().getData(ModAttachments.RACE_DATA.get());
        return ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "textures/geo/entity/" + data.getRace().toString().toLowerCase() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(RaceProxy animatable) {
        RaceData data = animatable.getPlayer().getData(ModAttachments.RACE_DATA.get());
        return ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "animations/entity/" + data.getRace().toString().toLowerCase() +".animation.json");
    }

}

