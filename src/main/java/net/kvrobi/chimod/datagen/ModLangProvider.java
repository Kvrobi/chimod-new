package net.kvrobi.chimod.datagen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output, String locale) {
        super(output, ChiMod.MOD_ID, locale);
    }

    public ModLangProvider(PackOutput output) {
        super(output, ChiMod.MOD_ID, "en_us");
    }


    @Override
    protected void addTranslations() {
        //blocks
        addBlock(ModBlocks.RAW_CHI_BLOCK, "Raw Chi Block");
        addBlock(ModBlocks.CHI_ORE_BLOCK, "Chi Ore");
        addBlock(ModBlocks.DEEPSLATE_CHI_ORE_BLOCK, "Deepslate Chi Ore");
        addBlock(ModBlocks.BLUE_CHI_LAMP, "Blue Chi Lamp");
        addBlock(ModBlocks.CHI_ORB_BLOCK, "Chi Orb");
        addBlock(ModBlocks.FIRE_CHI_ORB_BLOCK   , "Fire Chi Orb");
        addBlock(ModBlocks.LION_ROCK_TILES, "Lion Rock Tiles");
        addBlock(ModBlocks.LION_ROCK_TILES_STAIRS, "Lion Rock Tiles Stairs");
        addBlock(ModBlocks.LION_ROCK_TILES_SLAB, "Lion Rock Tiles Slab");
        addBlock(ModBlocks.LION_ROCK_TILES_DOOR, "Lion Rock Tiles Door");
        addBlock(ModBlocks.LION_ROCK_TILES_TRAPDOOR, "Lion Rock Tiles Trapdoor");
        addBlock(ModBlocks.LION_ROCK_TILES_FENCE_GATE, "Lion Rock Tiles Fence Gate");
        addBlock(ModBlocks.LION_ROCK_TILES_WALL, "Lion Rock Tiles Wall");
        addBlock(ModBlocks.LION_ROCK_TILES_FENCE, "Lion Rock Tiles Fence");
        addBlock(ModBlocks.LION_ROCK_TILES_BUTTON, "Lion Rock Tiles Button");
        addBlock(ModBlocks.LION_ROCK_TILES_PRESSURE_PLATE, "Lion Rock Tiles Pressure Plate");
        addBlock(ModBlocks.LION_TILES, "Lion Tiles");
        addBlock(ModBlocks.LION_TILES_STAIRS, "Lion Tiles Stairs");
        addBlock(ModBlocks.LION_TILES_SLAB, "Lion Tiles Slab");
        addBlock(ModBlocks.LION_TILES_FENCE_GATE, "Lion Tiles Fence Gate");
        addBlock(ModBlocks.LION_TILES_WALL, "Lion Tiles Wall");
        addBlock(ModBlocks.LION_TILES_FENCE, "Lion Tiles Fence");
        addBlock(ModBlocks.LION_TILES_BUTTON, "Lion Tiles Button");
        addBlock(ModBlocks.LION_TILES_PRESSURE_PLATE, "Lion Tiles Pressure Plate");
        addBlock(ModBlocks.LION_BRICKS, "Lion Bricks");
        addBlock(ModBlocks.LION_BRICKS_STAIRS, "Lion Bricks Stairs");
        addBlock(ModBlocks.LION_BRICKS_SLAB, "Lion Bricks Slab");
        addBlock(ModBlocks.LION_BRICKS_FENCE_GATE, "Lion Bricks Fence Gate");
        addBlock(ModBlocks.LION_BRICKS_WALL, "Lion Bricks Wall");
        addBlock(ModBlocks.LION_BRICKS_FENCE, "Lion Bricks Fence");
        addBlock(ModBlocks.LION_BRICKS_BUTTON, "Lion Bricks Button");
        addBlock(ModBlocks.LION_BRICKS_PRESSURE_PLATE, "Lion Bricks Pressure Plate");
        addBlock(ModBlocks.LION_STONE, "Lion Stone");
        addBlock(ModBlocks.LION_STONE_STAIRS, "Lion Stone Stairs");
        addBlock(ModBlocks.LION_STONE_SLAB, "Lion Stone Slab");
        addBlock(ModBlocks.LION_STONE_FENCE_GATE, "Lion Stone Fence Gate");
        addBlock(ModBlocks.LION_STONE_WALL, "Lion Stone Wall");
        addBlock(ModBlocks.LION_STONE_FENCE, "Lion Stone Fence");
        addBlock(ModBlocks.LION_STONE_BUTTON, "Lion Stone Button");
        addBlock(ModBlocks.SMOOTH_LION_STONE, "Smooth Lion Stone");
        addBlock(ModBlocks.SMOOTH_LION_STONE_STAIRS, "Smooth Lion Stone Stairs");
        addBlock(ModBlocks.SMOOTH_LION_STONE_SLAB, "Smooth Lion Stone Slab");
        addBlock(ModBlocks.SMOOTH_LION_STONE_FENCE_GATE, "Smooth Lion Stone Fence Gate");
        addBlock(ModBlocks.SMOOTH_LION_STONE_WALL, "Smooth Lion Stone Wall");
        addBlock(ModBlocks.SMOOTH_LION_STONE_FENCE, "Smooth Lion Stone Fence");
        addBlock(ModBlocks.SMOOTH_LION_STONE_BUTTON, "Smooth Lion Stone Button");
        addBlock(ModBlocks.SMOOTH_LION_STONE_PRESSURE_PLATE, "Smooth Lion Stone Pressure Plate");
        addBlock(ModBlocks.LION_STONE_PRESSURE_PLATE, "Lion Stone Pressure Plate");
        addBlock(ModBlocks.LION_COBBLESTONE, "Lion Cobblestone");
        addBlock(ModBlocks.LION_COBBLESTONE_STAIRS, "Lion Cobblestone Stairs");
        addBlock(ModBlocks.LION_COBBLESTONE_SLAB, "Lion Cobblestone Slab");
        addBlock(ModBlocks.LION_COBBLESTONE_FENCE_GATE, "Lion Cobblestone Fence Gate");
        addBlock(ModBlocks.LION_COBBLESTONE_WALL, "Lion Cobblestone Wall");
        addBlock(ModBlocks.LION_COBBLESTONE_FENCE, "Lion Cobblestone Fence");
        addBlock(ModBlocks.LION_COBBLESTONE_BUTTON, "Lion Cobblestone Button");
        addBlock(ModBlocks.LION_COBBLESTONE_PRESSURE_PLATE, "Lion Cobblestone Pressure Plate");
        addBlock(ModBlocks.CROCODILE_TILES, "Crocodile Tiles");
        addBlock(ModBlocks.CROCODILE_TILES_STAIRS, "Crocodile Tiles Stairs");
        addBlock(ModBlocks.CROCODILE_TILES_SLAB, "Crocodile Tiles Slab");
        addBlock(ModBlocks.CROCODILE_TILES_FENCE_GATE, "Crocodile Tiles Fence Gate");
        addBlock(ModBlocks.CROCODILE_TILES_WALL, "Crocodile Tiles Wall");
        addBlock(ModBlocks.CROCODILE_TILES_FENCE, "Crocodile Tiles Fence");
        addBlock(ModBlocks.CROCODILE_TILES_BUTTON, "Crocodile Tiles Button");
        addBlock(ModBlocks.CROCODILE_TILES_PRESSURE_PLATE, "Crocodile Tiles Pressure Plate");
        addBlock(ModBlocks.CROCODILE_BRICKS, "Crocodile Bricks");
        addBlock(ModBlocks.CROCODILE_BRICKS_STAIRS, "Crocodile Bricks Stairs");
        addBlock(ModBlocks.CROCODILE_BRICKS_SLAB, "Crocodile Bricks Slab");
        addBlock(ModBlocks.CROCODILE_BRICKS_FENCE_GATE, "Crocodile Bricks Fence Gate");
        addBlock(ModBlocks.CROCODILE_BRICKS_WALL, "Crocodile Bricks Wall");
        addBlock(ModBlocks.CROCODILE_BRICKS_FENCE, "Crocodile Bricks Fence");
        addBlock(ModBlocks.CROCODILE_BRICKS_BUTTON, "Crocodile Bricks Button");
        addBlock(ModBlocks.CROCODILE_BRICKS_PRESSURE_PLATE, "Crocodile Bricks Pressure Plate");
        addBlock(ModBlocks.CROCODILE_STONE, "Crocodile Stone");
        addBlock(ModBlocks.CROCODILE_STONE_STAIRS, "Crocodile Stone Stairs");
        addBlock(ModBlocks.CROCODILE_STONE_SLAB, "Crocodile Stone Slab");
        addBlock(ModBlocks.CROCODILE_STONE_FENCE_GATE, "Crocodile Stone Fence Gate");
        addBlock(ModBlocks.CROCODILE_STONE_WALL, "Crocodile Stone Wall");
        addBlock(ModBlocks.CROCODILE_STONE_FENCE, "Crocodile Stone Fence");
        addBlock(ModBlocks.CROCODILE_STONE_BUTTON, "Crocodile Stone Button");
        addBlock(ModBlocks.SMOOTH_CROCODILE_STONE, "Smooth Crocodile Stone");
        addBlock(ModBlocks.SMOOTH_CROCODILE_STONE_STAIRS, "Smooth Crocodile Stone Stairs");
        addBlock(ModBlocks.SMOOTH_CROCODILE_STONE_SLAB, "Smooth Crocodile Stone Slab");
        addBlock(ModBlocks.SMOOTH_CROCODILE_STONE_FENCE_GATE, "Smooth Crocodile Stone Fence Gate");
        addBlock(ModBlocks.SMOOTH_CROCODILE_STONE_WALL, "Smooth Crocodile Stone Wall");
        addBlock(ModBlocks.SMOOTH_CROCODILE_STONE_FENCE, "Smooth Crocodile Stone Fence");
        addBlock(ModBlocks.SMOOTH_CROCODILE_STONE_BUTTON, "Smooth Crocodile Stone Button");
        addBlock(ModBlocks.SMOOTH_CROCODILE_STONE_PRESSURE_PLATE, "Smooth Crocodile Stone Pressure Plate");
        addBlock(ModBlocks.CROCODILE_STONE_PRESSURE_PLATE, "Crocodile Stone Pressure Plate");
        addBlock(ModBlocks.CROCODILE_COBBLESTONE, "Crocodile Cobblestone");
        addBlock(ModBlocks.CROCODILE_COBBLESTONE_STAIRS, "Crocodile Cobblestone Stairs");
        addBlock(ModBlocks.CROCODILE_COBBLESTONE_SLAB, "Crocodile Cobblestone Slab");
        addBlock(ModBlocks.CROCODILE_COBBLESTONE_FENCE_GATE, "Crocodile Cobblestone Fence Gate");
        addBlock(ModBlocks.CROCODILE_COBBLESTONE_WALL, "Crocodile Cobblestone Wall");
        addBlock(ModBlocks.CROCODILE_COBBLESTONE_FENCE, "Crocodile Cobblestone Fence");
        addBlock(ModBlocks.CROCODILE_COBBLESTONE_BUTTON, "Crocodile Cobblestone Button");
        addBlock(ModBlocks.CROCODILE_COBBLESTONE_PRESSURE_PLATE, "Crocodile Cobblestone Pressure Plate");
        addBlock(ModBlocks.CHI_WATER_BLOCK, "Chi Water");

        //items
        addItem(ModItems.CHISEL, "Chisel");
        addItem(ModItems.CHI_SHARD, "Raw Chi");
        addItem(ModItems.CHI_ORB, "Chi Orb");
        addItem(ModItems.CRUDE_OIL_DROP, "Crude Oil Drop");
        addItem(ModItems.OIL_DROP, "Oil Drop");
        addItem(ModItems.BANANA, "Banana");
        addItem(ModItems.BANANA_PEEL, "Banana Peel");
        addItem(ModItems.BANANA_JUICE, "Banana Juice");
        addItem(ModItems.LION_VALIOUS_GRAY, "Gray Valious");
        addItem(ModItems.LION_CHI_JABAKA, "Jabaka");
        addItem(ModItems.LION_CLUBIUS_MAXIMUS, "Clubius Maximus");
        addItem(ModItems.LION_DECALUS, "Decalus");
        addItem(ModItems.LION_FANGIOUS, "Fangious");
        addItem(ModItems.LION_JABAKA, "Jabaka");
        addItem(ModItems.LION_JAHAK, "Jahak");
        addItem(ModItems.GOLDEN_SHOULDER_PADS, "Golden Shoulder Pads");
        addItem(ModItems.GOLDEN_SHOULDER_SPIKED, "Golden Shoulder Spiked");


        add("menu.kvrobichimod.race_title", "Select Your Tribe");

        //creativeTabs
        add("creativetab.kvrobichimod.chi_blocks", "Kvrobi's Chi Mod Blocks");
        add("creativetab.kvrobichimod.chi_items", "Kvrobi's Chi Mod Items");
        add("creativetab.kvrobichimod.chi_weapons", "Kvrobi's Chi Mod Weapons");
        add("creativetab.kvrobichimod.chi_foods", "Kvrobi's Chi Mod Food");
        add("creativetab.kvrobichimod.chi_misc", "Kvrobi's Chi Mod Miscellanous");

        //keysbinds
        add("key.categories.kvrobichimod", "Kvrobi's Chi Mod Keybinds");
        add("key.kvrobichimod.open_menu", "Open Chi Energy Menu");
        add("key.kvrobichimod.toggle_armor", "Toggle Chi Armor");


        //fluids
        add("fluid.kvrobichimod.chi", "Chi Water");


    }

}
