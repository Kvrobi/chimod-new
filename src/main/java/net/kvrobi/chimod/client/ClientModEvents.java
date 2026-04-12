package net.kvrobi.chimod.client;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.client.renderer.ChiArmorRenderer;
import net.kvrobi.chimod.client.renderer.ChiWeaponRenderer;
import net.kvrobi.chimod.client.screen.ChiMenuScreen;
import net.kvrobi.chimod.fluid.ModFluids;
import net.kvrobi.chimod.item.ModItems;
import net.kvrobi.chimod.item.armor.custom.ChiArmor;
import net.kvrobi.chimod.world.registration.ModMenuTypes;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class ClientModEvents {

    @SubscribeEvent
    public static void registerExtensions(RegisterClientExtensionsEvent event) {
        //List of  ChiWeapon items
        registerChiWeapon(event, ModItems.LION_VALIOUS_GRAY.get());
        registerChiWeapon(event, ModItems.LION_CLUBIUS_MAXIMUS.get());
        registerChiWeapon(event, ModItems.LION_JABAKA.get());
        registerChiWeapon(event, ModItems.LION_JAHAK.get());
        registerChiWeapon(event, ModItems.LION_CHI_JABAKA.get());
        registerChiWeapon(event, ModItems.LION_FANGIOUS.get());
        registerChiWeapon(event, ModItems.LION_DECALUS.get());

        registerChiArmor(event, ModItems.GOLDEN_SHOULDER_PADS.get());

        //registerChiWeapon(event, ModItems.GOLDEN_SHOULDER_PADS.get());
        //registerClientExtensions(event, ModItems.GOLDEN_SHOULDER_PADS.get());

        registerSimpleFluid(event, ModFluids.CHI_WATER_TYPE.get(), 0xFF00E5FF);

    }


    //@SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ModKeyBindings.OPEN_CHI_MENU);
        event.register(ModKeyBindings.ACTIVATE_ARMOR);
    }

    public static void registerScreens(RegisterMenuScreensEvent event) {
        // This tells the game: "When you see ChiMenu, use ChiMenuScreen to draw it"
        ChiMod.LOGGER.info("Registering Screen for: " + ModMenuTypes.CHI_MENU.getId());
        event.register(ModMenuTypes.CHI_MENU.get(), ChiMenuScreen::new);
    }

    private static void registerSimpleFluid(RegisterClientExtensionsEvent event, FluidType type, int tint) {
        // Get the name from the registry (e.g., "chi_type")
        ResourceLocation typeName = NeoForgeRegistries.FLUID_TYPES.getKey(type);

        // Remove "_type" if you want your textures named "chi_still.png" instead of "chi_type_still.png"
        String baseName = typeName.getPath().replace("_type", "");

        event.registerFluidType(new IClientFluidTypeExtensions() {
            private final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath(
                    ChiMod.MOD_ID, "block/" + baseName + "_still");
            private final ResourceLocation FLOW = ResourceLocation.fromNamespaceAndPath(
                    ChiMod.MOD_ID, "block/" + baseName + "_flow");

            @Override
            public ResourceLocation getStillTexture() { return STILL; }

            @Override
            public ResourceLocation getFlowingTexture() { return FLOW; }

            @Override
            public int getTintColor() { return tint; }
        }, type);
    }

    private static void registerChiArmor(RegisterClientExtensionsEvent event, Item item) {
        event.registerItem(new IClientItemExtensions() {
            private ChiArmorRenderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null) {
                    // Casting to ChiArmor to pull the GeoModelPath you defined in the item class
                    this.renderer = new ChiArmorRenderer(((ChiArmor) item).getGeoModelPath());
                }

                // Syncs the model bones to the player's current pose (walking, swinging, etc.)
                //this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original, null, 0, 0, 0, 0, 0);

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original, null, 0, 0, 0, 0, 0);

                return this.renderer;
            }
        }, item);
    }

    private static void registerChiWeapon(RegisterClientExtensionsEvent event, Item item) {
        event.registerItem(new IClientItemExtensions() {
            private ChiWeaponRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    // Get the registry name of the item (e.g., "kvrobichimod:lion_valius")
                    ResourceLocation name = BuiltInRegistries.ITEM.getKey(item);

                    // Pass the name to the renderer so it finds the right .geo.json
                    this.renderer = new ChiWeaponRenderer(name);
                }
                return this.renderer;
            }
        }, item);
    }
}