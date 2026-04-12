package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.nbt.Tag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ChiMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.SWORDS).add(ModItems.LION_VALIOUS_GRAY.get())
                .add(ModItems.LION_CLUBIUS_MAXIMUS.get())
                .add(ModItems.LION_DECALUS.get())
                .add(ModItems.LION_FANGIOUS.get());

        tag(ItemTags.HOES).add(ModItems.LION_FANGIOUS.get());

        tag(ItemTags.AXES).add(ModItems.LION_JAHAK.get())
                .add(ModItems.LION_DECALUS.get());

        tag(ItemTags.CHEST_ARMOR).add(ModItems.GOLDEN_SHOULDER_PADS.get());




        //tag(ModTags.)
    }
}
