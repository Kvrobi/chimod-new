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
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import javax.annotation.Nullable;

public class ChiWallBlock extends WallBlock {
    public static final BooleanProperty CHI_WATERLOGGED = BooleanProperty.create("chi_waterlogged");

    public ChiWallBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CHI_WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CHI_WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state != null) {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            return state.setValue(CHI_WATERLOGGED, fluidstate.getType() == ModFluids.CHI_WATER_SOURCE.get());
        }
        return null;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.getValue(CHI_WATERLOGGED)) {
            return ModFluids.CHI_WATER_SOURCE.get().getSource(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(CHI_WATERLOGGED)) {
            level.scheduleTick(currentPos, ModFluids.CHI_WATER_SOURCE.get(), ModFluids.CHI_WATER_SOURCE.get().getTickDelay(level));
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

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

    @Override
    public ItemStack pickupBlock(@Nullable Player player, LevelAccessor level, BlockPos pos, BlockState state) {
        if (state.getValue(CHI_WATERLOGGED)) {
            level.setBlock(pos, state.setValue(CHI_WATERLOGGED, false), 3);
            if (!state.canSurvive(level, pos)) {
                level.destroyBlock(pos, true);
            }
            return new ItemStack(ModItems.CHI_WATER_BUCKET.get());
        }
        return super.pickupBlock(player, level, pos, state);
    }
}
