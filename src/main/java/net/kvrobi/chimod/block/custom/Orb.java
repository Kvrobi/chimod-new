package net.kvrobi.chimod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class Orb extends Block implements SimpleWaterloggedBlock {
    private static final Property<Boolean> WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final IntProvider xpRange;


    public Orb(IntProvider xpRange, Properties properties) {
        super(properties);
        this.xpRange = xpRange;
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }


    @Override
    public int getExpDrop(BlockState state, LevelAccessor level, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity breaker, ItemStack tool) {
        return this.xpRange.sample(level.getRandom());
    }


    private static final VoxelShape SHAPE = Block.box(5.0D,0.0D, 5.0D, 11D, 6D, 11D);

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }


    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }


    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if(state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }


    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
