package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.fluid.ModFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FluidTagsProvider {
    public ModFluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, ChiMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(FluidTags.WATER)
                .add(ModFluids.CHI_WATER_SOURCE.get())
                .add(ModFluids.CHI_WATER_FLOWING.get());

        TagKey<Fluid> C_WATER = TagKey.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath("c", "water"));
        this.tag(C_WATER)
                .add(ModFluids.CHI_WATER_SOURCE.get())
                .add(ModFluids.CHI_WATER_FLOWING.get());
    }

}
