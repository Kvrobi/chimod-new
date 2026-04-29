package net.kvrobi.chimod.block.custom;

import net.kvrobi.chimod.fluid.ModFluids;
import net.kvrobi.chimod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Orb extends Block implements LiquidBlockContainer, BucketPickup {
    private static final Property<Boolean> WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final Property<Boolean> CHI_LOGGED = BooleanProperty.create("chi_logged");
    private final IntProvider xpRange;


    public Orb(IntProvider xpRange, Properties properties) {
        super(properties);
        this.xpRange = xpRange;
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(CHI_LOGGED, false));
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

        return this.defaultBlockState()
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
                .setValue(CHI_LOGGED, fluidState.getType() == ModFluids.CHI_WATER_SOURCE.get());
    }


    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if(state.getValue(CHI_LOGGED)) {
            level.scheduleTick(pos, ModFluids.CHI_WATER_SOURCE.get(), ModFluids.CHI_WATER_SOURCE.get().getTickDelay(level));
        } else if(state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, CHI_LOGGED);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        if(state.getValue(CHI_LOGGED)) {
            return ModFluids.CHI_WATER_SOURCE.get().getSource(false);
        } else if(state.getValue(WATERLOGGED)) {
            return Fluids.WATER.getSource(false);
        }
        return Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter blockGetter, BlockPos blockPos, BlockState state, Fluid fluid) {
        //boolean isEmpty = !state.getValue(WATERLOGGED) && !state.getValue(CHI_LOGGED);
        boolean isValid = fluid == Fluids.WATER || fluid == ModFluids.CHI_WATER_SOURCE.get();
        return isValid;
    }

    @Override
    public boolean placeLiquid(LevelAccessor levelAccessor, BlockPos blockPos, BlockState state, FluidState fluidState) {
        if(this.canPlaceLiquid(null, levelAccessor, blockPos, state, fluidState.getType())) {
            if(!levelAccessor.isClientSide()) {
                boolean isChi = fluidState.getType() == ModFluids.CHI_WATER_SOURCE.get();
                levelAccessor.setBlock(blockPos, state.setValue(CHI_LOGGED, isChi).setValue(WATERLOGGED, !isChi), 3);
                levelAccessor.scheduleTick(blockPos, fluidState.getType(), fluidState.getType().getTickDelay(levelAccessor));
            }
            return true;
        }
        return false;
    }


    @Override
    public ItemStack pickupBlock(@Nullable Player player, LevelAccessor levelAccessor, BlockPos blockPos, BlockState state) {
        if(state.getValue(CHI_LOGGED)) {
            levelAccessor.setBlock(blockPos, state.setValue(CHI_LOGGED, false), 3);
            return new ItemStack(ModItems.CHI_WATER_BUCKET.get());
        } else if(state.getValue(WATERLOGGED)) {
            levelAccessor.setBlock(blockPos, state.setValue(WATERLOGGED, false), 3);
            return new ItemStack(Items.WATER_BUCKET);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }
}
