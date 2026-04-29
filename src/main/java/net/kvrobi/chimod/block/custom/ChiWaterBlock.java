package net.kvrobi.chimod.block.custom;

import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.data.FluidData;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class ChiWaterBlock extends LiquidBlock {
    public ChiWaterBlock(java.util.function.Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid.get(), properties);
    }


    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
            if (random.nextInt(64) == 0) {
                level.playLocalSound(
                        (double) pos.getX() + 0.5D,
                        (double) pos.getY() + 0.5D,
                        (double) pos.getZ() + 0.5D,
                        state.getFluidState().isSource() ? SoundEvents.EMPTY : SoundEvents.WATER_AMBIENT,
                        SoundSource.BLOCKS,
                        random.nextFloat() * 0.25F + 0.75F,
                        random.nextFloat() + 0.5F,
                        false
                );
            }
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);
        Player player = entity instanceof Player p ? p : null;
        FluidData fluidData = entity.getData(ModAttachments.FLUID_DATA.get());
        int currentTick = entity.tickCount;
        if (entity.tickCount - fluidData.lastTickInChiFluid > 3) {
            float pitch =  (entity.fallDistance > 10) ? 0.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.4F : 1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.4F;
            float volume = (entity.fallDistance > 10) ? 0.5F : 0.1F;

            level.playSound(player, entity.getX(), entity.getY(), entity.getZ(),
                    entity instanceof Player ? SoundEvents.PLAYER_SPLASH : SoundEvents.GENERIC_SPLASH,
                    entity.getSoundSource(), volume, pitch);

            entity.resetFallDistance();
        }
        if (entity.getDeltaMovement().horizontalDistanceSqr() > 0.005D) {
            if (level.random.nextInt(15) == 0) {
                float pitch = 1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.4F;

                level.playSound(player, entity.getX(), entity.getY(), entity.getZ(),
                        entity instanceof Player ? SoundEvents.PLAYER_SWIM : SoundEvents.GENERIC_SWIM,
                        entity.getSoundSource(), 0.05F, pitch);
            }
        }
        fluidData.lastTickInChiFluid = currentTick;
        entity.setData(ModAttachments.FLUID_DATA, fluidData);
    }
}
