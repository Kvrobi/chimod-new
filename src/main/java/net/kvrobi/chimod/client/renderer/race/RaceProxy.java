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
        controllers.add(new AnimationController<>(this, "base_controller", 2, state -> {
            Race race = player.getData(ModAttachments.RACE_DATA.get()).getRace();
            boolean isHorizontallyMoving = player.getDeltaMovement().horizontalDistanceSqr() > 0.001 /*|| player.swingTime > 0*/;
            boolean isPunching = player.swinging;
            boolean isShifting = player.isCrouching();
            boolean isSwimming = player.isSwimming();
            //System.out.println("hellobello kontrolalok dolgokat");
            String animName;
            if(isHorizontallyMoving) {
                animName = isPunching ? "punchwalk" : "walk";
            } else {
                animName = isPunching ? "punch" : "idle" ;
            }
            if(isHorizontallyMoving) {
                if (isHorizontallyMoving) {
                    animName = isPunching ? "punchshiftwalk" : "shiftwalk";
                } else {
                    animName = isPunching ? "punchshift" : "shift";
                }
            }
            if(isSwimming) {
                if(isHorizontallyMoving) {
                    animName = isPunching ? "punchswimming" : "swimming";
                }
            }

            // Race-specific overrides (Example: Eagle flying)
            if ((race == Race.EAGLE || race == Race.CROW) && player.getAbilities().flying) {
                if (isHorizontallyMoving) {
                    animName = isPunching ? "punchingmovingflight" : "movingflight" ;
                } else {
                    animName = isPunching ? "punchidleflight" : "idleflight";
                }
            }

            return state.setAndContinue(RawAnimation.begin().thenLoop(animName));
        }).triggerableAnim("activatechi", RawAnimation.begin().thenPlay("activatingchi"))
          .triggerableAnim("usespecialmove", RawAnimation.begin().thenPlay("specialmove")));
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