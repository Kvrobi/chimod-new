package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ChiMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CHI_SHARD.get());
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.CHI_ORB.get());
        basicItem(ModItems.BANANA.get());
        basicItem(ModItems.BANANA_JUICE.get());
        basicItem(ModItems.BANANA_PEEL.get());
        basicItem(ModItems.CRUDE_OIL_DROP.get());
        basicItem(ModItems.OIL_DROP.get());
        basicItem(ModItems.CHI_WATER_BUCKET.getId());
        basicItem(ModItems.GOLDEN_SHOULDER_PADS.get());
        basicItem(ModItems.GOLDEN_SHOULDER_SPIKED.get());
        basicItem(ModItems.IRON_HAMMER.get());
        basicItem(ModItems.IRON_CHI_HOLDER.get());
        basicItem(ModItems.IRON_CROSS_GUARD.get());
        basicItem(ModItems.IRON_ROD.get());
        basicItem(ModItems.IRON_PLATE.get());
        basicItem(ModItems.GOLD_CHI_HOLDER.get());
        basicItem(ModItems.GOLD_CROSS_GUARD.get());
        basicItem(ModItems.GOLD_ROD.get());
        basicItem(ModItems.GOLD_PLATE.get());


        basicItem(ModBlocks.LION_ROCK_TILES_DOOR.asItem());

        buttonItem(ModBlocks.LION_ROCK_TILES_BUTTON, ModBlocks.LION_ROCK_TILES);
        fenceItem(ModBlocks.LION_ROCK_TILES_FENCE, ModBlocks.LION_ROCK_TILES);
        wallItem(ModBlocks.LION_ROCK_TILES_WALL, ModBlocks.LION_ROCK_TILES);
        buttonItem(ModBlocks.LION_TILES_BUTTON, ModBlocks.LION_TILES);
        fenceItem(ModBlocks.LION_TILES_FENCE, ModBlocks.LION_TILES);
        wallItem(ModBlocks.LION_TILES_WALL, ModBlocks.LION_TILES);
        buttonItem(ModBlocks.LION_BRICKS_BUTTON, ModBlocks.LION_BRICKS);
        fenceItem(ModBlocks.LION_BRICKS_FENCE, ModBlocks.LION_BRICKS);
        wallItem(ModBlocks.LION_BRICKS_WALL, ModBlocks.LION_BRICKS);
        buttonItem(ModBlocks.LION_STONE_BUTTON, ModBlocks.LION_STONE);
        fenceItem(ModBlocks.LION_STONE_FENCE, ModBlocks.LION_STONE);
        buttonItem(ModBlocks.SMOOTH_LION_STONE_BUTTON, ModBlocks.SMOOTH_LION_STONE);
        fenceItem(ModBlocks.SMOOTH_LION_STONE_FENCE, ModBlocks.SMOOTH_LION_STONE);
        wallItem(ModBlocks.SMOOTH_LION_STONE_WALL, ModBlocks.SMOOTH_LION_STONE);
        wallItem(ModBlocks.LION_STONE_WALL, ModBlocks.LION_STONE);
        buttonItem(ModBlocks.LION_COBBLESTONE_BUTTON, ModBlocks.LION_COBBLESTONE);
        fenceItem(ModBlocks.LION_COBBLESTONE_FENCE, ModBlocks.LION_COBBLESTONE);
        wallItem(ModBlocks.LION_COBBLESTONE_WALL, ModBlocks.LION_COBBLESTONE);
        buttonItem(ModBlocks.CROCODILE_TILES_BUTTON, ModBlocks.CROCODILE_TILES);
        fenceItem(ModBlocks.CROCODILE_TILES_FENCE, ModBlocks.CROCODILE_TILES);
        wallItem(ModBlocks.CROCODILE_TILES_WALL, ModBlocks.CROCODILE_TILES);
        buttonItem(ModBlocks.CROCODILE_BRICKS_BUTTON, ModBlocks.CROCODILE_BRICKS);
        fenceItem(ModBlocks.CROCODILE_BRICKS_FENCE, ModBlocks.CROCODILE_BRICKS);
        wallItem(ModBlocks.CROCODILE_BRICKS_WALL, ModBlocks.CROCODILE_BRICKS);
        buttonItem(ModBlocks.CROCODILE_STONE_BUTTON, ModBlocks.CROCODILE_STONE);
        fenceItem(ModBlocks.CROCODILE_STONE_FENCE, ModBlocks.CROCODILE_STONE);
        buttonItem(ModBlocks.SMOOTH_CROCODILE_STONE_BUTTON, ModBlocks.SMOOTH_CROCODILE_STONE);
        fenceItem(ModBlocks.SMOOTH_CROCODILE_STONE_FENCE, ModBlocks.SMOOTH_CROCODILE_STONE);
        wallItem(ModBlocks.SMOOTH_CROCODILE_STONE_WALL, ModBlocks.SMOOTH_CROCODILE_STONE);
        wallItem(ModBlocks.CROCODILE_STONE_WALL, ModBlocks.CROCODILE_STONE);
        buttonItem(ModBlocks.CROCODILE_COBBLESTONE_BUTTON, ModBlocks.CROCODILE_COBBLESTONE);
        fenceItem(ModBlocks.CROCODILE_COBBLESTONE_FENCE, ModBlocks.CROCODILE_COBBLESTONE);
        wallItem(ModBlocks.CROCODILE_COBBLESTONE_WALL, ModBlocks.CROCODILE_COBBLESTONE);

        chiLampItem(ModBlocks.BLUE_CHI_LAMP);


        geckoItem(ModItems.LION_VALIOUS_GRAY);
        geckoItem(ModItems.LION_CLUBIUS_MAXIMUS);
        geckoItem(ModItems.LION_JABAKA);
        geckoItem(ModItems.LION_JAHAK);
        geckoItem(ModItems.LION_CHI_JABAKA);
        geckoItem(ModItems.LION_FANGIOUS);
        geckoItem(ModItems.LION_DECALUS);


    }

    private void geckoItem(DeferredItem<Item> item) {
        withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
                .parent(new ModelFile.UncheckedModelFile("builtin/entity"));
    }

    public void chiLampItem(DeferredBlock<?> block) {
        this.withExistingParent(block.getId().getPath(), modLoc("block/" + block.getId().getPath() + "_inactive_unlit"));
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
