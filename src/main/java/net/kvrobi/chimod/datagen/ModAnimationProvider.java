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
    // A map to store: ResourceLocation -> FrameTime
    private final Map<ResourceLocation, Integer> animations = new HashMap<>();

    public ModAnimationProvider(PackOutput output) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "textures/block");

        // --- ADD YOUR FLUIDS HERE ---
        addAnimation("chi_water_flow", 2);
        addAnimation("chi_water_still", 4);
        //addAnimation("oil_flow", 10); // Much slower
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

            // location.getPath() is "chi_water_flow"
            // We want the file to be "chi_water_flow.png.mcmeta"
            // The pathProvider already handles the "assets/<modid>/textures/block/" part

            Path path = pathProvider.file(
                    ResourceLocation.fromNamespaceAndPath(location.getNamespace(), location.getPath() + ".png"),
                    "mcmeta"
            );

            //System.out.println("DEBUG: Attempting to save to: " + path.toAbsolutePath());
            futures.add(DataProvider.saveStable(cache, animation, path));
        });

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Mod Animation Metadata";
    }
}
