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

public class RaceProxy implements GeoAnimatable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final AbstractClientPlayer player;

    public RaceProxy(AbstractClientPlayer player) {
        this.player = player;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "base_controller", 10, state -> {
            Race race = player.getData(ModAttachments.RACE_DATA.get()).getRace();
            boolean isHorizontallyMoving = player.getDeltaMovement().horizontalDistanceSqr() > 0.001 /*|| player.swingTime > 0*/;
            boolean isSwimming = player.isSwimming();
            String animName;
            /*boolean isPunching = player.swinging;
            boolean isSneaking = player.isCrouching();*/
            //String extraAnimName = null;
            animName = isHorizontallyMoving ? "sprint" : "idle" ;

            /*if(isSneaking) {
                if (isHorizontallyMoving) {
                    animName = isPunching ? "punchsneakwalk" : "sneakwalk";
                } else {
                    animName = isPunching ? "punchsneak" : "sneak";
                }
            }*/
            if(isSwimming) {
                animName = "swimming";
            }

            if ((race == Race.EAGLE || race == Race.RAVEN) && (player.getAbilities().flying || player.isFallFlying())) {
                animName = "idleflight";
                /*if (isHorizontallyMoving) {
                    extraAnimName = isPunching ? "punchstartmovingflight" : "startmovingflight";
                    animName = isPunching ? "punchmovingflight" : "movingflight" ;
                } else {
                    animName = isPunching ? "punchidleflight" : "idleflight";
                }*/
            }
            /*if(extraAnimName != null) {
                return state.setAndContinue(RawAnimation.begin().thenPlay(extraAnimName).thenLoop(animName));
            }*/
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