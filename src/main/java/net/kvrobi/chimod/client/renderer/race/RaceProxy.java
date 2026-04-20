package net.kvrobi.chimod.client.renderer.race;

import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.Race;
import net.minecraft.client.player.AbstractClientPlayer;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import static software.bernie.geckolib.util.RenderUtil.getCurrentTick;

public class RaceProxy implements GeoAnimatable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final AbstractClientPlayer player;

    public RaceProxy(AbstractClientPlayer player) {
        this.player = player;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "base_controller", 0, state -> {
            Race race = player.getData(ModAttachments.RACE_DATA.get()).getRace();
            boolean isMoving = player.walkDist > player.walkDistO || player.swingTime > 0;
            System.out.println("hellobello kontrolalok dolgokat");
            String animName;
            animName = isMoving ? "walk" : "idle";

            // Race-specific overrides (Example: Eagle flying)
            if (race == Race.EAGLE && !player.onGround()) {
                animName = "idleflight";
            }

            return state.setAndContinue(RawAnimation.begin().thenLoop(animName));
        }));
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public AbstractClientPlayer getPlayer() {
        return player;
    }

    @Override
    public double getTick(Object object) {
        return net.minecraft.client.Minecraft.getInstance().level.getGameTime()
                + net.minecraft.client.Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(true);
    }
}