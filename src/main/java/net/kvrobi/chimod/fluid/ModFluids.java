package net.kvrobi.chimod.fluid;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;
import net.kvrobi.chimod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModFluids {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, ChiMod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, ChiMod.MOD_ID);

    //fluid types defined:
    public static final DeferredHolder<FluidType, FluidType> CHI_WATER_TYPE = FLUID_TYPES.register("chi_water_type",
            () -> new FluidType(FluidType.Properties.create()
                    .descriptionId("fluid.kvrobichimod.chi")
                    .canPushEntity(true)
                    .canSwim(true)
                    .density(1000)
                    .viscosity(1000)
                    .canConvertToSource(true)
                    .canDrown(true)
                    .fallDistanceModifier(0)
                    .supportsBoating(true)
                    .temperature(200)));


    //fluids defined:
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> CHI_WATER_SOURCE = FLUIDS.register("chi_water_source",
            () -> new BaseFlowingFluid.Source(ModFluids.CHI_WATER_PROPERTIES));

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> CHI_WATER_FLOWING = FLUIDS.register("chi_water_flowing",
            () -> new BaseFlowingFluid.Flowing(ModFluids.CHI_WATER_PROPERTIES));



    //fluid Properties defines:
    public static final BaseFlowingFluid.Properties CHI_WATER_PROPERTIES = new BaseFlowingFluid.Properties(CHI_WATER_TYPE, CHI_WATER_SOURCE, CHI_WATER_FLOWING)
            .slopeFindDistance(4)
            .levelDecreasePerBlock(1)
            .block(ModBlocks.CHI_WATER_BLOCK)
            .bucket(ModItems.CHI_WATER_BUCKET);





    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
    }
}
