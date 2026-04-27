package net.kvrobi.chimod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class ChiWaterBlock extends LiquidBlock {
    public ChiWaterBlock(java.util.function.Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid.get(), properties);
    }



   /* @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (!level.isClientSide()) {
            if (random.nextInt(64) == 0) {
                level.playLocalSound(
                        (double) pos.getX() + 0.5D,
                        (double) pos.getY() + 0.5D,
                        (double) pos.getZ() + 0.5D,
                        SoundEvents.WATER_AMBIENT,
                        SoundSource.BLOCKS,
                        random.nextFloat() * 0.25F + 0.75F, // Volume
                        random.nextFloat() + 0.5F,          // Pitch
                        false                                // Distance delay
                );
            }
        }
    }*/
}
