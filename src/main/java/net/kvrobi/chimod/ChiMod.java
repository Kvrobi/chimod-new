package net.kvrobi.chimod;

import com.mojang.logging.LogUtils;
import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.block.entity.ModBlockEntities;
import net.kvrobi.chimod.client.ClientInputHandler;
import net.kvrobi.chimod.client.ClientModEvents;
import net.kvrobi.chimod.client.renderer.race.RaceRenderHandler;
import net.kvrobi.chimod.component.ModDataComponents;
import net.kvrobi.chimod.datagen.DataGenerators;
import net.kvrobi.chimod.fluid.ModFluids;
import net.kvrobi.chimod.item.ModCreativeTabs;
import net.kvrobi.chimod.item.ModItems;
import net.kvrobi.chimod.item.armor.ModArmorMaterials;
import net.kvrobi.chimod.network.ModNetworking;
import net.kvrobi.chimod.network.RaceSyncEvents;
import net.kvrobi.chimod.util.ChiCommand;
import net.kvrobi.chimod.util.ModAttachments;
import net.kvrobi.chimod.world.registration.ModRecipes;
import net.kvrobi.chimod.worldgen.ModStructureTypes;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ChiMod.MOD_ID)
public class ChiMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "kvrobichimod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ChiMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener(DataGenerators::gatherData);
        NeoForge.EVENT_BUS.addListener(this::registerCommands);

        net.kvrobi.chimod.world.registration.ModMenuTypes.MENUS.register(modEventBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(ClientModEvents::registerExtensions);
            modEventBus.addListener(ClientModEvents::registerScreens);
            modEventBus.addListener(ClientModEvents::onRegisterKeyMappings);
            //modEventBus.addListener(ClientModEvents::onClientSetup);

            NeoForge.EVENT_BUS.addListener(RaceRenderHandler::onPlayerRender);
            NeoForge.EVENT_BUS.addListener(ClientInputHandler::onRenderArm);

            //NeoForge.EVENT_BUS.addListener();
            modEventBus.addListener(ClientModEvents::onAddLayers);
            ClientInputHandler inputHandler = new ClientInputHandler();
            NeoForge.EVENT_BUS.register(inputHandler);

            net.kvrobi.chimod.client.gui.FlightEnergyOverlay energyOverlay = new net.kvrobi.chimod.client.gui.FlightEnergyOverlay();
            NeoForge.EVENT_BUS.register(energyOverlay);
            NeoForge.EVENT_BUS.register(RaceSyncEvents.class);

        }





        modEventBus.addListener(ModNetworking::register);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        //NeoForge.EVENT_BUS.addListener(PlayerEvents::onPlayerTick);

        ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModAttachments.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModFluids.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModStructureTypes.register(modEventBus);
        ModRecipes.register(modEventBus);


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }



    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.CHI_SHARD);
            event.accept(ModItems.CHI_ORB);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.RAW_CHI_BLOCK);
            event.accept(ModBlocks.CHI_ORB_BLOCK);
            event.accept(ModBlocks.FIRE_CHI_ORB_BLOCK);
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.CHISEL);
        }
    }
    private void registerCommands(RegisterCommandsEvent event) {
        ChiCommand.register(event.getDispatcher());
    }



    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
