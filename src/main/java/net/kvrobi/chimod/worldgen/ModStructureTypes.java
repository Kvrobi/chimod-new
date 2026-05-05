package net.kvrobi.chimod.worldgen;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.worldgen.structure.WaterJigsawStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModStructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, ChiMod.MOD_ID);

    public static final Supplier<StructureType<WaterJigsawStructure>> WATER_STRUCTURE =
            STRUCTURE_TYPES.register("water_structure", () -> () -> WaterJigsawStructure.CODEC);

    public static void register(IEventBus eventBus) {
        STRUCTURE_TYPES.register(eventBus);
    }
}