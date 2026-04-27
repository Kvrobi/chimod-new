package net.kvrobi.chimod.item.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ModFood extends Item {
    private final Item turnsInto;
    private final boolean isDrink;

    public ModFood(Item turnsInto, boolean isDrink, int nutrition, float saturation, Properties properties) {
        super(properties.food(new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).build()));
        this.turnsInto = turnsInto;
        this.isDrink = isDrink;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);

        if(livingEntity instanceof Player player){

            if(this.turnsInto != null) {
                if(result.isEmpty()) {
                    return new ItemStack(this.turnsInto);
                }
                ItemStack container = new ItemStack(this.turnsInto);
                if(!player.getInventory().add(container)) {
                    player.drop(container, false);
                }
            }
        }
        return result;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if(this.isDrink) {
            return UseAnim.DRINK;
        }
        return UseAnim.EAT;
    }

    @Override
    public SoundEvent getEatingSound() {
        if(this.isDrink) {
            return SoundEvents.GENERIC_DRINK;
        }
        return SoundEvents.GENERIC_EAT;
    }
}
