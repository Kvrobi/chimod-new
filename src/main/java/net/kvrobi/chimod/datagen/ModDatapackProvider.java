package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.item.armor.ModArmorMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder();
    // If you have other custom data (Datapack-only registries), add them here.

    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        // Set.of(ChiMod.MOD_ID) ensures it only generates files for your mod
        super(output, registries, BUILDER, Set.of(ChiMod.MOD_ID));
    }



}
