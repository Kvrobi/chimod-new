package net.kvrobi.chimod.block.custom;

import net.kvrobi.chimod.fluid.ModFluids;
import net.kvrobi.chimod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import javax.annotation.Nullable;

public class ChiSlabBlock extends SlabBlock {
    public static final BooleanProperty CHI_WATERLOGGED = BooleanProperty.create("chi_waterlogged");

    public ChiSlabBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CHI_WATERLOGGED, false));
    }

    // 2. Add the custom property to the block state definition
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CHI_WATERLOGGED);
    }

    // 3. Handle what happens when placed inside a fluid
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Let super handle normal waterlogging and stair facing/half
        BlockState state = super.getStateForPlacement(context);
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

        // If it's placed in Chi Water, set our property to true
        return state.setValue(CHI_WATERLOGGED, fluidstate.getType() == ModFluids.CHI_WATER_SOURCE.get()); // Assuming CHI_WATER is your source fluid
    }

    // 4. Render the correct fluid inside the block
    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.getValue(CHI_WATERLOGGED)) {
            return ModFluids.CHI_WATER_SOURCE.get().getSource(false);
        }
        return super.getFluidState(state); // Handles normal water falling back
    }

    // 5. Allow Chi Water to flow outward from the block
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(CHI_WATERLOGGED)) {
            level.scheduleTick(currentPos, ModFluids.CHI_WATER_SOURCE.get(), ModFluids.CHI_WATER_SOURCE.get().getTickDelay(level));
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    // 6. Handle the player right-clicking with a bucket TO PLACE fluid
    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        if (fluid == ModFluids.CHI_WATER_SOURCE.get()) {
            return !state.getValue(WATERLOGGED) && !state.getValue(CHI_WATERLOGGED);
        }
        return super.canPlaceLiquid(player, level, pos, state, fluid);
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        if (fluidState.getType() == ModFluids.CHI_WATER_SOURCE.get()) {
            if (!state.getValue(WATERLOGGED) && !state.getValue(CHI_WATERLOGGED)) {
                if (!level.isClientSide()) {
                    level.setBlock(pos, state.setValue(CHI_WATERLOGGED, true), 3);
                    level.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(level));
                }
                return true;
            }
            return false;
        }
        return super.placeLiquid(level, pos, state, fluidState);
    }

    // 7. Handle the player right-clicking with an empty bucket TO PICK UP fluid
    @Override
    public ItemStack pickupBlock(@Nullable Player player, LevelAccessor level, BlockPos pos, BlockState state) {
        if (state.getValue(CHI_WATERLOGGED)) {
            level.setBlock(pos, state.setValue(CHI_WATERLOGGED, false), 3);
            if (!state.canSurvive(level, pos)) {
                level.destroyBlock(pos, true);
            }
            // Return your custom Chi Water bucket
            return new ItemStack(ModItems.CHI_WATER_BUCKET.get());
        }
        return super.pickupBlock(player, level, pos, state);
    }
}

