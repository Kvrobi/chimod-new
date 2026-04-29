package net.kvrobi.chimod.fluid;

import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.fluids.FluidType;

import javax.annotation.Nullable;

public class DebugFluidType extends FluidType {

    public DebugFluidType(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable SoundEvent getSound(SoundAction action) {
        //System.out.println("[CHI MOD DEBUG] Fluid SoundAction requested: " + action.name());

        return super.getSound(action);
    }
}
