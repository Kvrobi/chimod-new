package net.kvrobi.chimod.item.custom;

import com.google.j2objc.annotations.Property;
import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.component.ModDataComponents;
import net.kvrobi.chimod.util.ChiData;
import net.kvrobi.chimod.util.ItemDisplaySettings;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.ModUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;


public class ChiWeapon extends Item implements GeoItem {
    private boolean isTool = false;
    private final ChatFormatting nameColor;
    private final ChatFormatting defNameColor;
    private final ItemDisplaySettings guiSettings;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean isChiAble = false;
    private boolean isSword = false;
    private boolean isAxe = false;
    private boolean isHoe = false;
    private boolean isTrident = false;
    private boolean isShovel = false;
    private boolean isPickaxe = false;
    double daminact = 1;
    double damact = 5;
    double reach = 0;

    public ChiWeapon(Properties properties, boolean isChiAble, boolean isTool, boolean isSword, boolean isAxe, boolean isHoe, boolean isTrident, boolean isShovel, boolean isPickaxe,
                     double daminact, double damact,double atsp, double reach, ChatFormatting defNameColor, ChatFormatting nameColor, ItemDisplaySettings guiSettings) {
        super(properties.attributes(createAttributes(daminact, atsp, reach)));
        this.isTool = isTool;
        this.isChiAble = isChiAble;
        this.isSword = isSword;
        this.isAxe = isAxe;
        this.isHoe = isHoe;
        this.isTrident = isTrident;
        this.isShovel = isShovel;
        this.isPickaxe = isPickaxe;
        this.nameColor = nameColor;
        this.guiSettings = guiSettings;
        this.daminact = daminact;
        this.damact = damact;
        this.defNameColor = defNameColor;
        this.reach = reach;
        SingletonGeoAnimatable.registerSyncedAnimatable(this);

    }

    public ItemDisplaySettings getGuiSettings() {
        return guiSettings;
    }

    public static ItemAttributeModifiers createAttributes(double v1, double v2, double reach) {
            return ItemAttributeModifiers.builder()
                    .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, v1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                    .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, v2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                    .add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "weapon_reach"),
                            reach, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

    private void swap(Player player, InteractionHand hand, ItemStack oldStack, Item newItem) {
        ItemStack newStack = new ItemStack(newItem);

        newStack.setDamageValue(oldStack.getDamageValue());
        newStack.applyComponents(oldStack.getComponentsPatch());

        player.setItemInHand(hand, newStack);
    }

    public boolean isActive(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.IS_ACTIVE.get(), false);
    }

    private boolean toggleState(Player player, InteractionHand hand, ItemStack stack) {
        // 1. Check the current state of our Data Component
        if (isChiAble) {
            boolean isActive = stack.getOrDefault(ModDataComponents.IS_ACTIVE.get(), false);
            ChiData data = player.getData(ModAttachments.CHI_ENERGY);

            if (!isActive) {
                if (!(data.getEnergy() > 0)) {
                    player.displayClientMessage(Component.literal("You need more Chi Energy to be able to activate this")
                            .withStyle(ChatFormatting.RED, ChatFormatting.BOLD), true);
                    return false;
                }
            }


            stack.set(ModDataComponents.IS_ACTIVE.get(), !isActive);

            return true;
        }
        return false;
    }

    public void deactivate(ItemStack stack, Player player, ServerLevel level) {
        if(isChiAble) {
            stack.set(ModDataComponents.IS_ACTIVE.get(), false);
            ItemAttributeModifiers newModifiers = ItemAttributeModifiers.builder()
                    .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                                    BASE_ATTACK_DAMAGE_ID, this.daminact, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND)
                    .add(Attributes.ATTACK_SPEED, new AttributeModifier(
                                    BASE_ATTACK_SPEED_ID, -2.4, AttributeModifier.Operation.ADD_VALUE),
                            EquipmentSlotGroup.MAINHAND)
                    .add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "weapon_reach"),
                            reach, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();

            // Overwrite the vanilla component for attributes
            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, newModifiers);

            // Sync visual animation
            triggerTransformAnimation(player, stack, level, false);

        }
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        if(ItemAbilities.DEFAULT_SWORD_ACTIONS.contains(itemAbility)) {
            return isSword;
        }
        if(ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility)) {
            return isAxe;
        }
        if(ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility)) {
            return isHoe;
        }
        if(ItemAbilities.DEFAULT_TRIDENT_ACTIONS.contains(itemAbility)) {
            return isTrident;
        }
        if(ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility)) {
            return isShovel;
        }
        if(ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(itemAbility)) {
            return isPickaxe;
        }





        return super.canPerformAction(stack, itemAbility);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        if(isTool) {
            if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0F) {
                // Damage the item by 2 (standard sword behavior)
                stack.hurtAndBreak(2, miningEntity, LivingEntity.getSlotForHand(miningEntity.getUsedItemHand()));
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, LivingEntity.getSlotForHand(attacker.getUsedItemHand()));
        return true;
    }

    /*private void playSwitchAnim(Level level, Player player, InteractionHand hand, ItemStack stack) {
        ResourceLocation currentId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        String path = currentId.getPath();
        if(level instanceof ServerLevel serverLevel) {
            if (!hasActivateAnim) {
                return;
            }
            if (path.endsWith("_active")) {
                triggerAnim(player, GeoItem.getOrAssignId(stack, serverLevel), "base_controller", "activate");
                return;
            } else if (path.endsWith("_inactive")) {
                triggerAnim(player, GeoItem.getOrAssignId(stack, serverLevel), "base_controller", "deactivate");
                return;
            }
        }
        return;
    }*/

    @Override
    public Component getName(ItemStack stack) {
        boolean isActive = stack.getOrDefault(ModDataComponents.IS_ACTIVE.get(), false);

        if (isActive) {
            return super.getName(stack).copy().withStyle(nameColor, ChatFormatting.BOLD);
        }

        return super.getName(stack).copy().withStyle(defNameColor);
    }




    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if(slotChanged) return true;

        boolean isSwap = oldStack.getItem() instanceof ChiWeapon && newStack.getItem() instanceof ChiWeapon;

        if(isSwap) return false;

        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
    }




    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (isChiAble) {


            if (level instanceof ServerLevel serverLevel) {
                boolean currentState = stack.getOrDefault(ModDataComponents.IS_ACTIVE.get(), false);
                ChiData data = player.getData(ModAttachments.CHI_ENERGY);
                if (!currentState && !(data.getEnergy() > 0)) {
                    player.displayClientMessage(Component.literal("You need more Chi Energy to be able to activate this!").withStyle(ChatFormatting.RED), true);
                    return InteractionResultHolder.fail(stack);
                }

                // Toggle state
                boolean newState = !currentState;
                stack.set(ModDataComponents.IS_ACTIVE.get(), newState);

                double damageValue = newState ? this.damact : this.daminact;

                ItemAttributeModifiers newModifiers = ItemAttributeModifiers.builder()
                        .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                                        BASE_ATTACK_DAMAGE_ID, damageValue, AttributeModifier.Operation.ADD_VALUE),
                                EquipmentSlotGroup.MAINHAND)
                        .add(Attributes.ATTACK_SPEED, new AttributeModifier(
                                        BASE_ATTACK_SPEED_ID, -2.4, AttributeModifier.Operation.ADD_VALUE),
                                EquipmentSlotGroup.MAINHAND)
                        .add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "weapon_reach"),
                                        reach, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();

                // Overwrite the vanilla component for attributes
                stack.set(DataComponents.ATTRIBUTE_MODIFIERS, newModifiers);

                // Sync visual animation
                triggerTransformAnimation(player, stack, serverLevel, newState);

                // SUCCESS on server forces the data to sync to the client
                return InteractionResultHolder.fail(stack);
            }

            // FAIL on client stops the hand-swing/punch animation
            return InteractionResultHolder.fail(stack);
        }
        return  InteractionResultHolder.pass(stack);
    }


    private void triggerTransformAnimation(Player player, ItemStack stack, ServerLevel level, boolean activating) {
        long id = GeoItem.getOrAssignId(stack, level);
        triggerAnim(player, id, "base_controller", activating ? "activate" : "deactivate");
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "base_controller", 0, state -> {
            ItemStack stack = state.getData(DataTickets.ITEMSTACK);
            if (stack == null) return PlayState.STOP;

            if (state.getController().getAnimationState() == AnimationController.State.RUNNING) {
                return PlayState.CONTINUE;
            }


            boolean isActive = stack.getOrDefault(ModDataComponents.IS_ACTIVE.get(), false);


            return state.setAndContinue(isActive ?
                    RawAnimation.begin().thenLoop("active") :
                    RawAnimation.begin().thenLoop("inactive"));
        })
                .triggerableAnim("activate", RawAnimation.begin().thenPlay("activating").thenLoop("active"))
                .triggerableAnim("deactivate", RawAnimation.begin().thenPlay("deactivating").thenLoop("inactive")));
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
