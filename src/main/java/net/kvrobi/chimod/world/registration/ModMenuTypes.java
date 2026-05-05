package net.kvrobi.chimod.world.registration;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.world.gui.RaceSelectionMenu;
import net.kvrobi.chimod.world.inventory.ChiMenu;
import net.kvrobi.chimod.world.inventory.LionCraftingMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, ChiMod.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<ChiMenu>> CHI_MENU =
            MENUS.register("chi_menu", () -> IMenuTypeExtension.create((windowId, inv, data) ->
                    new ChiMenu(windowId, inv)));
    public static final DeferredHolder<MenuType<?>, MenuType<RaceSelectionMenu>> RACE_MENU =
            MENUS.register("race_menu", () -> IMenuTypeExtension.create((windowId, inv, data) ->
                    new RaceSelectionMenu(windowId, inv)));
    public static final DeferredHolder<MenuType<?>, MenuType<LionCraftingMenu>> LION_CRAFTING_MENU =
            MENUS.register("lion_crafting_menu", () -> IMenuTypeExtension.create(LionCraftingMenu::new));
}

