package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.fluid.ModFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FluidTagsProvider {
    public ModFluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, ChiMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Adding your fluids to the vanilla WATER tag triggers the screen overlay and fog
        this.tag(FluidTags.WATER)
                .add(ModFluids.CHI_WATER_SOURCE.get())
                .add(ModFluids.CHI_WATER_FLOWING.get());
    }

}
