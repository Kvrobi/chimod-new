package net.kvrobi.chimod.block.entity;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.block.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ChiMod.MOD_ID);

   /* public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChiOrbBlockEntity>> CHI_ORB_BE =
            BLOCK_ENTITIES.register("chi_orb_be", () ->
                    BlockEntityType.Builder.of(ChiOrbBlockEntity::new,
                            ModBlocks.CHI_ORB_BLOCK.get()).build(null));*/

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}