package net.kvrobi.chimod.util;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.kvrobi.chimod.network.ChiSyncPayload;
import net.kvrobi.chimod.network.RaceSyncPayload;
import net.kvrobi.chimod.util.data.ChiData;
import net.kvrobi.chimod.util.data.RaceData;
import net.kvrobi.chimod.world.gui.RaceSelectionMenu;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Arrays;
import java.util.Collection;

public class ChiCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        // Build the command: /chi set <value>
        dispatcher.register(Commands.literal("chi_energy")
                .then(Commands.literal("set")
                        .requires(source -> source.hasPermission(2)) // Require Operator level 2
                        .then(Commands.argument("amount", IntegerArgumentType.integer(0, 1500))
                                .executes(context -> {
                                    int amount = IntegerArgumentType.getInteger(context, "amount");
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    ChiData data = player.getData(ModAttachments.CHI_ENERGY);
                                    data.setEnergy(amount);
                                    PacketDistributor.sendToPlayer(player, new ChiSyncPayload(data.getEnergy()));

                                    context.getSource().sendSuccess(() ->
                                            Component.literal("Chi set to " + amount), true);
                                    return 1;
                                })
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            Collection<ServerPlayer> targets = EntityArgument.getPlayers(context, "targets");
                                            for (ServerPlayer player : targets) {
                                                ChiData data = player.getData(ModAttachments.CHI_ENERGY);
                                                data.setEnergy(amount);
                                                PacketDistributor.sendToPlayer(player, new ChiSyncPayload(data.getEnergy()));
                                            }
                                            context.getSource().sendSuccess(() ->
                                                    Component.literal("Chi set to " + amount), true);
                                            return 1;
                                        })
                                )
                        )
                )
                .then(Commands.literal("add")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.argument("amount", IntegerArgumentType.integer(0, 1500))
                                .executes(context -> {
                                    int amount = IntegerArgumentType.getInteger(context, "amount");
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    ChiData data = player.getData(ModAttachments.CHI_ENERGY);
                                    data.addEnergy(amount);
                                    PacketDistributor.sendToPlayer(player, new ChiSyncPayload(data.getEnergy()));
                                    context.getSource().sendSuccess(() -> Component.literal("added " + amount + "Chi"), true);
                                    return 1;
                                })
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            Collection<ServerPlayer> targets = EntityArgument.getPlayers(context, "targets");
                                            for (ServerPlayer player : targets) {
                                                ChiData data = player.getData(ModAttachments.CHI_ENERGY);
                                                data.addEnergy(amount);
                                                PacketDistributor.sendToPlayer(player, new ChiSyncPayload(data.getEnergy()));
                                            }
                                            context.getSource().sendSuccess(() -> Component.literal("Added" + amount + "Chi to every target"), true);
                                            return 1;
                                        })
                                )

                        )
                )
                .then(Commands.literal("query")
                        .requires(source -> source.hasPermission(0))
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            ChiData data = player.getData(ModAttachments.CHI_ENERGY);
                            context.getSource().sendSuccess(() ->
                                    Component.literal("Current Chi: " + data.getEnergy()), false);
                            return 1;
                        })
                )
        );
        dispatcher.register(Commands.literal("chi_race").then(Commands.literal("set")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("type", StringArgumentType.word())
                // Autocomplete for race names
                .suggests((ctx, builder) -> SharedSuggestionProvider.suggest(
                        Arrays.stream(Race.values()).map(Race::name).toList(), builder))
                .executes(context -> {
                    String raceName = StringArgumentType.getString(context, "type");
                    ServerPlayer player = context.getSource().getPlayerOrException();

                    // Set the data
                    RaceData data = player.getData(ModAttachments.RACE_DATA);
                    data.setRace(Race.fromString(raceName));

                    // Sync to client (Important for the future menu!)
                    PacketDistributor.sendToPlayer(player, new RaceSyncPayload(data.getRace(), player.getId()));

                    context.getSource().sendSuccess(() ->
                            Component.literal("Race set to: " + raceName), true);
                    return 1;
                })
        )).then(Commands.literal("query").requires(source -> source.hasPermission(0))
                            .executes(context -> {
                                ServerPlayer player = context.getSource().getPlayerOrException();
                                RaceData data = player.getData(ModAttachments.RACE_DATA);
                                context.getSource().sendSuccess(() ->
                                        Component.literal("Current Race: " + data.getRace()), false);
                                return 1;
                            })
                )// Inside ChiCommand.register
                .then(Commands.literal("open")
                        .requires(source -> source.hasPermission(0))
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            player.openMenu(new SimpleMenuProvider(
                                    (id, inv, p) -> new RaceSelectionMenu(id, inv),
                                    Component.literal("Select Your Race")
                            ));
                            return 1;
                        })
                )
        );
    }
}