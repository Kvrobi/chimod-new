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
        addBlock(ModBlocks.LION_ROCK_TILES_STAIRS, "Lion Rock Tiles stairs");
        addBlock(ModBlocks.LION_ROCK_TILES_SLAB, "Lion Rock Tiles slab");
        addBlock(ModBlocks.LION_ROCK_TILES_DOOR, "Lion Rock Tiles Door");
        addBlock(ModBlocks.LION_ROCK_TILES_TRAPDOOR, "Lion Rock Tiles Trapdoor");
        addBlock(ModBlocks.LION_ROCK_TILES_FENCE_GATE, "Lion Rock Tiles Fence Gate");
        addBlock(ModBlocks.LION_ROCK_TILES_WALL, "Lion Rock Tiles Wall");
        addBlock(ModBlocks.LION_ROCK_TILES_FENCE, "Lion Rock Tiles Fence");
        addBlock(ModBlocks.LION_ROCK_TILES_BUTTON, "Lion Rock Tiles Button");
        addBlock(ModBlocks.LION_ROCK_TILES_PRESSURE_PLATE, "Lion Rock Tiles Pressure Plate");
        addBlock(ModBlocks.CHI_WATER_BLOCK, "Chi Water");

        //items
        addItem(ModItems.CHISEL, "Chisel");
        addItem(ModItems.RAW_CHI, "Raw Chi");
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
