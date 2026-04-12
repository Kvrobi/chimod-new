package net.kvrobi.chimod.item.custom;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.network.ChiSyncPayload;
import net.kvrobi.chimod.util.ChiData;
import net.kvrobi.chimod.util.ModAttachments;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.network.PacketDistributor;

public class ChiOrbItem extends BlockItem {

    public ChiOrbItem(Block block, Item.Properties properties) {
        super(block, properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if(!level.isClientSide()) {
            ChiData data = player.getData(ModAttachments.CHI_ENERGY);
            if(data.getEnergy() <= 300) {
                data.addEnergy(150);

                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20*120, 1 ));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20*90, 2));

                PacketDistributor.sendToPlayer((ServerPlayer) player, new ChiSyncPayload(data.getEnergy()));

                stack.shrink(1);
            }
        }

        return InteractionResultHolder. sidedSuccess(stack, level.isClientSide());
    }
}
