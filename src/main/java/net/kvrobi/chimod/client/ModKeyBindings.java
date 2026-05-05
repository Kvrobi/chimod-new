package net.kvrobi.chimod.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    public static final String KEY_CATEGORY_CHI = "key.categories.kvrobichimod";

    public static final KeyMapping OPEN_CHI_MENU = new KeyMapping(
      "key.kvrobichimod.open_menu",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_I,
            KEY_CATEGORY_CHI
    );

    public static final KeyMapping ACTIVATE_ARMOR = new KeyMapping(
            "key.kvrobichimod.toggle_armor",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            KEY_CATEGORY_CHI
            );

}
