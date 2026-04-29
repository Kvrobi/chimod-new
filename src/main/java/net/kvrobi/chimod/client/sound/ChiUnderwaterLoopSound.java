package net.kvrobi.chimod.client.sound;

import net.kvrobi.chimod.fluid.ModFluids;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

public class ChiUnderwaterLoopSound extends AbstractTickableSoundInstance {
    private final LocalPlayer player;

    public ChiUnderwaterLoopSound(LocalPlayer player) {
        super(SoundEvents.AMBIENT_UNDERWATER_LOOP, SoundSource.AMBIENT, RandomSource.create());
        this.player = player;

        this.looping = true;
        this.delay = 0;
        this.volume = 1.0F;
        this.pitch = 1.0F;

        this.x = player.getX();
        this.y = player.getEyeY();
        this.z = player.getZ();
    }

    @Override
    public void tick() {
        if (this.player.isRemoved() || !this.player.isEyeInFluidType(ModFluids.CHI_WATER_TYPE.get())) {
            this.stop();
            return;
        }
        this.x = this.player.getX();
        this.y = this.player.getEyeY();
        this.z = this.player.getZ();
    }
}