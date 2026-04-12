package net.kvrobi.chimod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundMoveVehiclePacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class ChiWaterBlock extends LiquidBlock {
    public ChiWaterBlock(java.util.function.Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid.get(), properties);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        /*if(entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect()
        }*/
        super.entityInside(state, level, pos, entity);
    }
}
