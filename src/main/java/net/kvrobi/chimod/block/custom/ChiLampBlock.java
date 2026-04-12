package net.kvrobi.chimod.block.custom;

import com.google.j2objc.annotations.Property;
import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.datagen.ModBlockLootTableProvider;
import net.kvrobi.chimod.util.ModUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ChiLampBlock extends Block {
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");


    public ChiLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CLICKED, false));
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, false));
    }

    //my functions



    //overrides

    /*@Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        boolean isActive = state.getValue(ACTIVE);

        if(!level.isClientSide() && isActive) {
            boolean currentState = state.getValue(CLICKED);
            level.setBlockAndUpdate(pos, state.setValue(CLICKED, !currentState));
        }else if(!level.isClientSide()){
            player.displayClientMessage(Component.literal("You need to activate it with a Chi Orb (block) first")
                    .withStyle(ChatFormatting.RED, ChatFormatting.BOLD), true);
        }

        return InteractionResult.SUCCESS;
    }*/

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        boolean currentState = state.getValue(ACTIVE);
        boolean clickedState = state.getValue(CLICKED);
        if(!level.isClientSide()) {
            if (ModUtil.isChi(stack) && !currentState){
                level.setBlock(pos, state.setValue(ACTIVE, true) , 3);
                level.playSound(null, pos, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 1.0f, 1.0f);
            } else if (ModUtil.isChi(stack) && currentState) {
                player.displayClientMessage(Component.literal("This has already been activated").withStyle(ChatFormatting.AQUA), true);
            } else if(currentState) {
                level.setBlock(pos, state.setValue(CLICKED, !clickedState), 3);
                level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3f, 0.6f);
            }else {
                player.displayClientMessage(Component.literal("You need a Chi Orb to activate this").withStyle(ChatFormatting.RED, ChatFormatting.BOLD), true);
            }
        }

        return ItemInteractionResult.SUCCESS;
    }

    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        // This tells the game the block is "hollow" like air or glass,
        // allowing the light to actually escape the block's center.
        return 0;
    }




    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLICKED).add(ACTIVE);
    }

}
