package net.kvrobi.chimod.item;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ChiMod.MOD_ID);

    public static final Supplier<CreativeModeTab> CHI_BLOCK_TAB = CREATIVE_MODE_TAB.register("chi_blocks_tab",
            () -> CreativeModeTab.builder().icon( () -> new ItemStack(ModBlocks.CHI_ORB_BLOCK.get())).title(Component.translatable("creativetab.kvrobichimod.chi_blocks"))
                    .displayItems((parameters, output) -> {
                output.accept(ModBlocks.CHI_ORB_BLOCK);
                output.accept(ModBlocks.RAW_CHI_BLOCK);
                output.accept(ModBlocks.CHI_ORE_BLOCK);
                output.accept(ModBlocks.FIRE_CHI_ORB_BLOCK);
                output.accept(ModBlocks.DEEPSLATE_CHI_ORE_BLOCK);

                output.accept(ModBlocks.LION_ROCK_TILES);
                output.accept(ModBlocks.LION_ROCK_TILES_STAIRS);
                output.accept(ModBlocks.LION_ROCK_TILES_SLAB);
                output.accept(ModBlocks.LION_ROCK_TILES_WALL);
                output.accept(ModBlocks.LION_ROCK_TILES_FENCE);
                output.accept(ModBlocks.LION_ROCK_TILES_FENCE_GATE);
                output.accept(ModBlocks.LION_ROCK_TILES_DOOR);
                output.accept(ModBlocks.LION_ROCK_TILES_TRAPDOOR);
                output.accept(ModBlocks.LION_ROCK_TILES_PRESSURE_PLATE);
                output.accept(ModBlocks.LION_ROCK_TILES_BUTTON);

                output.accept(ModBlocks.BLUE_CHI_LAMP);

            }).build());

    public static final Supplier<CreativeModeTab> CHI_ITEMS_TAB = CREATIVE_MODE_TAB.register("chi_items_tab",
            () -> CreativeModeTab.builder().icon( () -> new ItemStack(ModItems.CHI_ORB.get())).withTabsBefore(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID,
                    "chi_blocks_tab")).title(Component.translatable("creativetab.kvrobichimod.chi_items")).displayItems((parameters, output) -> {
                        output.accept(ModItems.CHI_ORB);
                        output.accept(ModItems.RAW_CHI);
                        output.accept(ModItems.BANANA_PEEL);
                        output.accept((ItemLike) ModItems.CHI_WATER_BUCKET);

            }).build());

    public static final Supplier<CreativeModeTab> CHI_WEAPONS_TAB = CREATIVE_MODE_TAB.register("chi_weapons_tab",
            () -> CreativeModeTab.builder().icon( () -> new ItemStack(ModItems.LION_VALIOUS_GRAY.get())).withTabsBefore(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID,
                    "chi_items_tab")).title(Component.translatable("creativetab.kvrobichimod.chi_weapons")).displayItems((parameters, output) -> {
                        output.accept(ModItems.LION_VALIOUS_GRAY);
                        output.accept(ModItems.LION_CLUBIUS_MAXIMUS);
                        output.accept(ModItems.LION_JABAKA);
                        output.accept(ModItems.LION_JAHAK);
                        output.accept(ModItems.LION_CHI_JABAKA);
                        output.accept(ModItems.LION_FANGIOUS);
                        output.accept(ModItems.LION_DECALUS);

            }).build());

    public static final Supplier<CreativeModeTab> CHI_ARMORS_TAB = CREATIVE_MODE_TAB.register("chi_armors_tab",
            () -> CreativeModeTab.builder().icon( () -> new ItemStack(ModItems.GOLDEN_SHOULDER_PADS.get())).withTabsBefore(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID,
                    "chi_weapons_tab")).title(Component.translatable("creativetab.kvrobichimod.chi_weapons")).displayItems((parameters, output) -> {
                output.accept(ModItems.GOLDEN_SHOULDER_PADS);
                output.accept(ModItems.GOLDEN_SHOULDER_SPIKED);

            }).build());

    /*public static final Supplier<CreativeModeTab> CHI_TOOLS_TAB = CREATIVE_MODE_TAB.register("chi_tools_tab",
            () -> CreativeModeTab.builder().icon( () -> new ItemStack(ModItems.LION_VALIOUS_GRAY.get())).withTabsBefore(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID,
                    "chi_armors_tab")).title(Component.translatable("creativetab.kvrobichimod.chi_tools")).displayItems((parameters, output) -> {
                output.accept(ModItems.LION_VALIOUS_GRAY);
                output.accept(ModItems.LION_CLUBIUS_MAXIMUS);
                output.accept(ModItems.LION_JABAKA);

            }).build());*/

    public static final Supplier<CreativeModeTab> CHI_FOODS_TAB = CREATIVE_MODE_TAB.register("chi_foods_tab",
            () -> CreativeModeTab.builder().icon( () -> new ItemStack(ModItems.BANANA_JUICE.get())).withTabsBefore(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID,
                    "chi_armors_tab")).title(Component.translatable("creativetab.kvrobichimod.chi_foods")).displayItems((parameters, output) -> {
                        output.accept(ModItems.BANANA);
                        output.accept(ModItems.BANANA_JUICE);
            }).build());

    public static final Supplier<CreativeModeTab> CHI_MISC = CREATIVE_MODE_TAB.register("chi_misc",
            () -> CreativeModeTab.builder().icon( () -> new ItemStack(ModItems.CHISEL.get())).withTabsBefore(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID,
                    "chi_foods_tab")).title(Component.translatable("creativetab.kvrobichimod.chi_misc")).displayItems((parameters, output) -> {
                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.OIL_DROP);
                        output.accept(ModItems.CRUDE_OIL_DROP);
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
