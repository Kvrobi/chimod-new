package net.kvrobi.chimod.item.armor;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.world.inventory.ChiMenu;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, ChiMod.MOD_ID);

    // Create a Key so the bootstrap and the code both know we're talking about "chi"
    public static final ResourceKey<ArmorMaterial> CHI_ARMOR_MATERIAL_KEY =
            ResourceKey.create(Registries.ARMOR_MATERIAL, ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "chi"));

    // Your actual Holder used in Item registration
    public static final Holder<ArmorMaterial> CHI_ARMOR_MATERIAL = ARMOR_MATERIALS.register("chi", () -> new ArmorMaterial(
            new EnumMap<>(ArmorItem.Type.class), 0, SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.EMPTY, List.of(), 0, 0)); // Dummy values, bootstrap overrides these

    public static void bootstrap(BootstrapContext<ArmorMaterial> context) {
        context.register(CHI_ARMOR_MATERIAL_KEY, new ArmorMaterial(
                Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                    map.put(ArmorItem.Type.BOOTS, 3);
                    map.put(ArmorItem.Type.LEGGINGS, 6);
                    map.put(ArmorItem.Type.CHESTPLATE, 8);
                    map.put(ArmorItem.Type.HELMET, 3);
                }),
                15,
                SoundEvents.ARMOR_EQUIP_DIAMOND,
                () -> Ingredient.of(Items.DIAMOND),
                // "chi" means vanilla looks for textures/models/armor/chi_layer_1.png
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "chi"))),
                2.0F,
                0.0F
        ));
    }
}
