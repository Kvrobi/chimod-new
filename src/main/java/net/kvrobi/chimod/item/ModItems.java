package net.kvrobi.chimod.item;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.fluid.ModFluids;
import net.kvrobi.chimod.item.armor.ModArmorMaterials;
import net.kvrobi.chimod.item.armor.custom.ChiArmor;
import net.kvrobi.chimod.item.custom.*;
import net.kvrobi.chimod.util.ItemDisplaySettings;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ChiMod.MOD_ID);

    //simple items

    public static final DeferredItem<Item> RAW_CHI = ITEMS.register("rawchi",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHI_ORB = ITEMS.register("chiorb",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BANANA_PEEL = ITEMS.register("banana_peel",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CRUDE_OIL_DROP = ITEMS.register("crude_oil_drop",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, BucketItem> CHI_WATER_BUCKET = ITEMS.register("chi_water_bucket",
            () -> new BucketItem(ModFluids.CHI_WATER_SOURCE.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));





    //special itemek
    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));

    public static final DeferredItem<Item> BANANA = ITEMS.register("banana",
            () -> new ModFood(ModItems.BANANA_PEEL.get(), false, 4, 0.5f, new Item.Properties()));

    public static final DeferredItem<Item> BANANA_JUICE = ITEMS.register("banana_juice",
            () -> new ModFood(Items.GLASS_BOTTLE, true, 4, 0.5f, new Item.Properties()));

    public static final DeferredItem<Item> OIL_DROP = ITEMS.register("oil_drop",
            () -> new ModFuel( new Item.Properties(), 1600)); //1600 is the value for coal

    public static final DeferredItem<Item> LION_VALIOUS_GRAY = ITEMS.register("lion_valious_gray",
            () -> new ChiWeapon(new Item.Properties().durability(512),
                    true, false, true,false, false,false,false, false,
                    2, 6,-2.4, 0, ChatFormatting.GRAY, ChatFormatting.AQUA,
                    new ItemDisplaySettings(0.8f, 0f, 0f, 0f, 0f, 0f, 0f,
                            0.95f, -0.65f, 0.03f, 0f,45f, 90f, 0f)));

    public static final DeferredItem<Item> LION_CLUBIUS_MAXIMUS = ITEMS.register("lion_clubius_maximus",
            () -> new ChiWeapon(new Item.Properties().durability(256),
                    true, false, true, false, false,false,false, false,
                    1, 5,-2.4, 0, ChatFormatting.GRAY, ChatFormatting.AQUA,
                    new ItemDisplaySettings(0.8f, 0f, 0f, 0f, 0f, 0f, 0f,
                            1.1f, -0.60f, 0.03f, 0f,45f, 90f, 0f)));

    public static final DeferredItem<Item> LION_JABAKA = ITEMS.register("lion_jabaka",
            () -> new ChiWeapon(new Item.Properties().durability(256),
                    false, false, false,false, false,false,false, false,
                    4, 4,-3, 1.5, ChatFormatting.WHITE, ChatFormatting.WHITE,
                    new ItemDisplaySettings(1.f, 0f, -0.38f, 0f, -20f, 0f, 0f,
                            0.6f, -0.45f, 0.03f, 0f,0f, 0f, -45f)));

    public static final DeferredItem<Item> LION_JAHAK = ITEMS.register("lion_jahak",
            () -> new ChiWeapon(new Item.Properties().durability(200),
                    false, true, false,true, false,false,false, false,
                    5.5, 5.5,-3.5, 1.5, ChatFormatting.WHITE, ChatFormatting.WHITE,
                    new ItemDisplaySettings(1.f, 0f, -0.38f, 0f, -20f, 0f, 0f,
                            0.6f, -0.45f, 0.03f, 0f,0f, 0f, -45f)));

    public static final DeferredItem<Item> LION_CHI_JABAKA = ITEMS.register("lion_chi_jabaka",
            () -> new ChiWeapon(new Item.Properties().durability(256),
                    true, false, false,false, false,false,false, false,
                    3, 6,-3.2, 2.0, ChatFormatting.GRAY, ChatFormatting.AQUA,
                    new ItemDisplaySettings(1.f, 0f, -0.42f, 0f, -25f, 0f, 0f,
                            0.5f, -0.45f, 0.03f, 0f,0f, 0f, -45f)));

    public static final DeferredItem<Item> LION_FANGIOUS = ITEMS.register("lion_fangious",
            () -> new ChiWeapon(new Item.Properties().durability(256),
                    true, true, true,false, true,false,false, false,
                    3, 7,-3, 0, ChatFormatting.GRAY, ChatFormatting.AQUA,
                    new ItemDisplaySettings(1.f, 0f, -0.42f, 0f, -25f, 0f, 0f,
                            0.5f, -0.45f, 0.03f, 0f,0f, 0f, -45f)));

    public static final DeferredItem<Item> LION_DECALUS = ITEMS.register("lion_decalus",
            () -> new ChiWeapon(new Item.Properties().durability(256),
                    true, true, true, true, false, false, false, false,
                    3, 7, -2, 0, ChatFormatting.GRAY, ChatFormatting.AQUA,
                    new ItemDisplaySettings(1.f, 0f, -0.42f, 0f, -25f, 0f, 0f,
                            0.5f, -0.45f, 0.03f, 0f, 0f, 0f, -45f)));


    public static final DeferredItem<Item> GOLDEN_SHOULDER_PADS = ITEMS.register("golden_shoulder_pads", () -> new ChiArmor(ModArmorMaterials.CHI_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(256), 2, 6, 0, 1,
            new ItemDisplaySettings(0f, 0f, 1f, 0f, 0f, 0f, 0f,
                    1f, 0f, 0.3f, 0f, 0f, 0f, 0f)));
    public static final DeferredItem<Item> GOLDEN_SHOULDER_SPIKED = ITEMS.register("golden_shoulder_spiked", () -> new ChiArmor(ModArmorMaterials.CHI_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(256), 2, 6, 0, 1,
            new ItemDisplaySettings(0f, 0f, 1f, 0f, 0f, 0f, 0f,
                    1f, 0f, 0.3f, 0f, 0f, 0f, 0f)));


    /*public static final DeferredItem<Item> LION_VALIOUS_GRAY_INACTIVE = ITEMS.register("lion_valious_gray_inactive",
            () -> new ChiWeapon(new Item.Properties().durability(256), true, 0,1.6, ChatFormatting.GRAY,
                    new ItemDisplaySettings(0.8f ,0.95f, -0.65f, 0.03f, 0f, 45f, 90f, 0f)));
*/

    public static void register(IEventBus eventBus)  {
        ITEMS.register(eventBus);
    }


}

