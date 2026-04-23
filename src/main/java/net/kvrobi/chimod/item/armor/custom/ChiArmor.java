package net.kvrobi.chimod.item.armor.custom;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.client.renderer.ChiArmorRenderer;
import net.kvrobi.chimod.client.renderer.ChiWeaponRenderer;
import net.kvrobi.chimod.component.ModDataComponents;
import net.kvrobi.chimod.util.data.ChiData;
import net.kvrobi.chimod.util.ItemDisplaySettings;
import net.kvrobi.chimod.util.ModAttachments;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;


public class ChiArmor extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    double baseProt;
    double activeProt;
    double baseToughness;
    double activeToughness;
    ItemDisplaySettings guiSettings;

    public ChiArmor(Holder<ArmorMaterial> material, ArmorItem.Type type, Properties properties, double baseProt, double activeProt, double baseToughness, double activeToughness, ItemDisplaySettings guiSettings) {
        super(material, type, properties.attributes(createAttributes(baseProt, baseToughness)));
        this.baseProt = baseProt;
        this.activeProt = activeProt;
        this.baseToughness = baseToughness;
        this.activeToughness = activeToughness;
        this.guiSettings = guiSettings;
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public static ItemAttributeModifiers createAttributes(double bP, double bT ) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ARMOR, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "armor_protection"), bP, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ARMOR)
                .add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "armor_toughness"), bT, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.ARMOR)
                .build();
    }

    public ItemDisplaySettings getGuiSettings() {
        return guiSettings;
    }

    public boolean isActive(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.IS_ACTIVE.get(), false);
    }


    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private ChiArmorRenderer armorRenderer; // For the body
            private ChiWeaponRenderer itemRenderer;  // For the GUI/Hand

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.itemRenderer == null) {
                    // Use your Weapon Renderer for the 3D item look
                    this.itemRenderer = new ChiWeaponRenderer(getGeoModelPath());
                }
                return this.itemRenderer;
            }

            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
                if (this.armorRenderer == null) {
                    this.armorRenderer = new ChiArmorRenderer(getGeoModelPath());
                }
                return this.armorRenderer;
            }
        });
    }

    public ResourceLocation getGeoModelPath() {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(this);
        return ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "armor/" + id.getPath());
    }

    public void deactivate(ItemStack stack, Player player, ServerLevel level) {

        stack.set(ModDataComponents.IS_ACTIVE.get(), false);
        //getDefaultAttributeModifiers();
        triggerTransformAnimation(player, stack, level, false);


    }
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide() && entity instanceof Player player) {
            // Only check if the armor is actually active
            if (isActive(stack)) {
                ChiData data = player.getData(ModAttachments.CHI_ENERGY.get());

                // If energy is empty, force deactivation
                if (data.getEnergy() <= 0) {
                    if (level instanceof ServerLevel serverLevel) {
                        this.deactivate(stack, player, serverLevel);
                    }
                }
            }
        }
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        boolean active = stack.getOrDefault(ModDataComponents.IS_ACTIVE.get(), false);
        double armorValue = active ? activeProt : baseProt;
        double toughnessValue = active ? activeToughness : baseToughness;

        builder.add(Attributes.ARMOR, new AttributeModifier(
                ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "armor_protection"),
                armorValue, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST);

        builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "armor_toughness"),
                toughnessValue, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST);

        return builder.build();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        boolean active = stack.getOrDefault(ModDataComponents.IS_ACTIVE.get(), false);

        // needs transalation
        tooltip.add(Component.translatable(active ? "tooltip.chimod.active" : "tooltip.chimod.inactive")
                .withStyle(active ? ChatFormatting.AQUA : ChatFormatting.GRAY));

        super.appendHoverText(stack, context, tooltip, flag);
    }

    public void toggle(Player player, ItemStack stack) {
        boolean newState = !stack.getOrDefault(ModDataComponents.IS_ACTIVE.get(), false);
        ChiData data = player.getData(ModAttachments.CHI_ENERGY);
        if (player.level() instanceof ServerLevel serverLevel) {
            if (data.getEnergy() >= 1) {
                player.displayClientMessage(Component.literal("Activating armor").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD), true);
                this.triggerTransformAnimation(player, stack, serverLevel, newState);
                serverLevel.playSound(null, player.blockPosition(), SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 0.4F, 1.8F);
                stack.set(ModDataComponents.IS_ACTIVE.get(), newState);
            } else {
                player.displayClientMessage(Component.literal("You need more energy to activate your armor").withStyle(ChatFormatting.RED), true);
            }
        }
    }

    private void triggerTransformAnimation(Player player, ItemStack stack, ServerLevel level, boolean activating) {
        long id = GeoItem.getOrAssignId(stack, level);
        ChiData data = player.getData(ModAttachments.CHI_ENERGY.get());
        //getDefaultAttributeModifiers();
        //System.out.println("Aktívál? " + activating);
        triggerAnim(player, id, "base_controller", data.getGolden() ? (activating ? "activate_golden" : "deactivate_golden") : (activating ? "activate_chi" : "deactivate_chi"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

        controllers.add(new AnimationController<>(this, "base_controller", 5, state -> {
            ItemStack stack = state.getData(DataTickets.ITEMSTACK);
            if (stack == null) return PlayState.STOP;

            var currentAnim = state.getController().getCurrentAnimation();
            if (currentAnim != null) {
                String name = currentAnim.animation().name();
                if (name.contains("activating") || name.contains("deactivating")) {
                    return PlayState.STOP;
                }

            }

            /*if (state.getController().getAnimationState() == AnimationController.State.RUNNING) {
                return PlayState.CONTINUE;
            }*/

            Entity entity = state.getData(DataTickets.ENTITY);
            boolean isGolden = false;
            if(entity instanceof Player player) {
                ChiData data = player.getData(ModAttachments.CHI_ENERGY.get());
                isGolden = data.getGolden();
            }

            boolean isActive = stack.getOrDefault(ModDataComponents.IS_ACTIVE.get(), false);
            //System.out.println("Arany? " + isGolden);


            return state.setAndContinue(isActive ?(isGolden ? RawAnimation.begin().thenLoop("active_golden")
                        : RawAnimation.begin().thenLoop("active_chi"))
                : RawAnimation.begin().thenLoop("inactive"));
        })
                .triggerableAnim("activate_chi", RawAnimation.begin().thenPlay("activating_chi"))
                .triggerableAnim("deactivate_chi", RawAnimation.begin().thenPlay("deactivating_chi"))
                .triggerableAnim("activate_golden", RawAnimation.begin().thenPlay("activating_golden"))
                .triggerableAnim("deactivate_golden", RawAnimation.begin().thenPlay("deactivating_golden")));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
