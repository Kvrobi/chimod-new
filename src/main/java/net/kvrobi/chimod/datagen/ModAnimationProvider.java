package net.kvrobi.chimod.datagen;

import com.google.gson.JsonObject;
import net.kvrobi.chimod.ChiMod;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModAnimationProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;
    private final Map<ResourceLocation, Integer> animations = new HashMap<>();

    public ModAnimationProvider(PackOutput output) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures/block");

        addAnimation("chi_water_flow", 2);
        addAnimation("chi_water_still", 4);
    }

    private void addAnimation(String name, int frameTime) {
        animations.put(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, name), frameTime);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        animations.forEach((location, frameTime) -> {
            JsonObject animation = new JsonObject();
            JsonObject inner = new JsonObject();
            inner.addProperty("frametime", frameTime);
            inner.addProperty("interpolate", true);
            animation.add("animation", inner);

            Path path = pathProvider.file(
                    ResourceLocation.fromNamespaceAndPath(location.getNamespace(), location.getPath() + ".png"),
                    "mcmeta"
            );

            futures.add(DataProvider.saveStable(cache, animation, path));
        });

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Mod Animation Metadata";
    }
}
