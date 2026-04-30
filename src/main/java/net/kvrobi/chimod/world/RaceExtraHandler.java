package net.kvrobi.chimod.world;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.util.Race;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.w3c.dom.Attr;

public class RaceExtraHandler {
    private static final ResourceLocation KVROBI_CHI_HEALTH_MODI = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "health_base_mod");
    private static final ResourceLocation KVROBI_CHI_SPEED_MODI = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "movespeed_base_mod");
    private static final ResourceLocation KVROBI_CHI_FLYSPEED_MODI = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "flyspeed_base_mod");
    private static final ResourceLocation KVROBI_CHI_WATER_EFF_MODI = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "water_efficiency_base_mod");
    private static final ResourceLocation KVROBI_CHI_ATK_DAM_MODI = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "attack_damage_base_mod");
    private static final ResourceLocation KVROBI_CHI_ATK_SPEED_MODI = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "attack_speed_base_mod");
    private static final ResourceLocation KVROBI_CHI_ATK_KNOCKBACK_MODI = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "attack_knockback_base_mod");
    private static final ResourceLocation KVROBI_CHI_BLOCK_BREAK_SPD_MODI = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "block_break_speed_base_mod");
    private static final ResourceLocation KVROBI_CHI_JUMP_STR_MODI = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "jump_strenght_base_mod");
    private static final ResourceLocation KVROBI_CHI_SAFE_FALL_DIST = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "safe_fall_dist_base_mod");
    private static final ResourceLocation KVROBI_CHI_OXY_BONUS_MODI = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "oxygen_bonus_base_mod");
    private static final ResourceLocation KVROBI_CHI_SWIM_SPD_MODI = ResourceLocation.fromNamespaceAndPath(ChiMod.MOD_ID, "swim_speed_base_mod");

    public static void applyRaceAttributes(Player player) {
        Race playerRace = player.getData(ModAttachments.RACE_DATA.get()).getRace();

        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        AttributeInstance moveSpd = player.getAttribute(Attributes.MOVEMENT_SPEED);
        AttributeInstance flySpd = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
        AttributeInstance waterMoveEff = player.getAttribute(Attributes.WATER_MOVEMENT_EFFICIENCY);
        AttributeInstance atkDmg = player.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance atkSpd = player.getAttribute(Attributes.ATTACK_SPEED);
        AttributeInstance atkKnockback = player.getAttribute(Attributes.ATTACK_KNOCKBACK);
        AttributeInstance blockBreakSpd = player.getAttribute(Attributes.BLOCK_BREAK_SPEED);
        AttributeInstance jumpStr = player.getAttribute(Attributes.JUMP_STRENGTH);
        AttributeInstance oxyBonus = player.getAttribute(Attributes.OXYGEN_BONUS);
        AttributeInstance swimSpeed = player.getAttribute(NeoForgeMod.SWIM_SPEED);


        safeRemove(maxHealth, KVROBI_CHI_HEALTH_MODI);
        safeRemove(moveSpd, KVROBI_CHI_SPEED_MODI);
        safeRemove(flySpd, KVROBI_CHI_FLYSPEED_MODI);
        safeRemove(waterMoveEff, KVROBI_CHI_WATER_EFF_MODI);
        safeRemove(atkDmg, KVROBI_CHI_ATK_DAM_MODI);
        safeRemove(atkSpd, KVROBI_CHI_ATK_SPEED_MODI);
        safeRemove(atkKnockback, KVROBI_CHI_ATK_KNOCKBACK_MODI);
        safeRemove(blockBreakSpd, KVROBI_CHI_BLOCK_BREAK_SPD_MODI);
        safeRemove(jumpStr, KVROBI_CHI_JUMP_STR_MODI);
        safeRemove(oxyBonus, KVROBI_CHI_OXY_BONUS_MODI);
        safeRemove(swimSpeed, KVROBI_CHI_SWIM_SPD_MODI);
        safeRemove(player.getAttribute(Attributes.SAFE_FALL_DISTANCE), KVROBI_CHI_SAFE_FALL_DIST);

        player.getAbilities().setFlyingSpeed(0.05f);


        switch (playerRace) {
            case EAGLE:
                applyEagleAttribute(player);
                break;
            case LION:
                applyLionAttribute(player);
                break;
            case CROCODILE:
                applyCrocodileAttribute(player);
                break;
            case BEAR:
                applyBearAttribute(player);
                break;
            case WOLF:
                applyWolfAttribute(player);
                break;
            case GORILLA:
                applyGorillaAttribute(player);
                break;
            case RAVEN:
                applyRavenAttribute(player);
                break;
            default:
                break;
        }
        player.onUpdateAbilities();
    }

    private static void safeRemove(AttributeInstance instance, ResourceLocation id) {
        if (instance != null) {
            instance.removeModifier(id);
        }
    }

    private static void applySafeModifier(AttributeInstance instance, ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        if (instance != null) {
            instance.addPermanentModifier(new AttributeModifier(id, amount, operation));
        }
    }

    private static void applyEagleAttribute(Player player) {
        player.getAbilities().setFlyingSpeed(0.055f);
        applySafeModifier(player.getAttribute(Attributes.MAX_HEALTH), KVROBI_CHI_HEALTH_MODI, -2, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.ATTACK_DAMAGE), KVROBI_CHI_ATK_DAM_MODI, -0.25, AttributeModifier.Operation.ADD_VALUE);
    }

    private static void applyLionAttribute(Player player) {
        applySafeModifier(player.getAttribute(Attributes.MOVEMENT_SPEED), KVROBI_CHI_SPEED_MODI, 0.015, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.MAX_HEALTH), KVROBI_CHI_HEALTH_MODI, 2, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.ATTACK_SPEED), KVROBI_CHI_ATK_SPEED_MODI, 0.1, AttributeModifier.Operation.ADD_VALUE);
    }

    private static void applyCrocodileAttribute(Player player) {
        applySafeModifier(player.getAttribute(Attributes.MOVEMENT_SPEED), KVROBI_CHI_SPEED_MODI, -0.01, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.MAX_HEALTH), KVROBI_CHI_HEALTH_MODI, 2, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.WATER_MOVEMENT_EFFICIENCY), KVROBI_CHI_WATER_EFF_MODI, 0.5, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(NeoForgeMod.SWIM_SPEED), KVROBI_CHI_SWIM_SPD_MODI, 0.5, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.OXYGEN_BONUS), KVROBI_CHI_OXY_BONUS_MODI, 6., AttributeModifier.Operation.ADD_VALUE);
    }

    private static void applyBearAttribute(Player player) {
        applySafeModifier(player.getAttribute(Attributes.MAX_HEALTH), KVROBI_CHI_HEALTH_MODI, 4, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.ATTACK_DAMAGE), KVROBI_CHI_ATK_DAM_MODI, 2, AttributeModifier.Operation.ADD_VALUE);
        //applySafeModifier(player.getAttribute(Attributes.ATTACK_KNOCKBACK), KVROBI_CHI_ATK_KNOCKBACK_MODI, 0.5, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.ATTACK_SPEED), KVROBI_CHI_ATK_SPEED_MODI, -0.1, AttributeModifier.Operation.ADD_VALUE);
    }

    private static void applyWolfAttribute(Player player) {
        applySafeModifier(player.getAttribute(Attributes.MOVEMENT_SPEED), KVROBI_CHI_SPEED_MODI, 0.1, AttributeModifier.Operation.ADD_VALUE);
        //applySafeModifier(player.getAttribute(Attributes.MAX_HEALTH), KVROBI_CHI_HEALTH_MODI, 2, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.ATTACK_SPEED), KVROBI_CHI_ATK_SPEED_MODI, 0.5, AttributeModifier.Operation.ADD_VALUE);
    }

    private static void applyGorillaAttribute(Player player) {
        applySafeModifier(player.getAttribute(Attributes.JUMP_STRENGTH), KVROBI_CHI_JUMP_STR_MODI, 0.305, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.SAFE_FALL_DISTANCE), KVROBI_CHI_SAFE_FALL_DIST, 3, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.MAX_HEALTH), KVROBI_CHI_HEALTH_MODI, 6, AttributeModifier.Operation.ADD_VALUE);
        //applySafeModifier(player.getAttribute(Attributes.ATTACK_DAMAGE), KVROBI_CHI_ATK_DAM_MODI, , AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.ATTACK_KNOCKBACK), KVROBI_CHI_ATK_KNOCKBACK_MODI, 0.5, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.BLOCK_BREAK_SPEED), KVROBI_CHI_BLOCK_BREAK_SPD_MODI, 0.5, AttributeModifier.Operation.ADD_VALUE);
        //applySafeModifier(player.getAttribute(Attributes.ATTACK_SPEED), KVROBI_CHI_ATK_SPEED_MODI, -0.1, AttributeModifier.Operation.ADD_VALUE);

    }

    private static void applyRavenAttribute(Player player) {
        player.getAbilities().setFlyingSpeed(0.075f);
        applySafeModifier(player.getAttribute(Attributes.MAX_HEALTH), KVROBI_CHI_HEALTH_MODI, -4, AttributeModifier.Operation.ADD_VALUE);
        applySafeModifier(player.getAttribute(Attributes.MOVEMENT_SPEED), KVROBI_CHI_SPEED_MODI, 0.02, AttributeModifier.Operation.ADD_VALUE);
    }

}
