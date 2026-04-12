package net.kvrobi.chimod.client;

import net.kvrobi.chimod.ChiMod;
import net.kvrobi.chimod.network.OpenMenuPayload;
import net.kvrobi.chimod.network.ToggleArmorPayload;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class ClientInputHandler {
    private final Map<KeyMapping, Consumer<Minecraft>> keyActions = new HashMap<>();

    public ClientInputHandler() {
        register(ModKeyBindings.OPEN_CHI_MENU, this::handleOpenMenu);
        register(ModKeyBindings.ACTIVATE_ARMOR, this::handleActivateArmor);
    }

    private void register(KeyMapping key, Consumer<Minecraft> action) {
        keyActions.put(key, action);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.player == null) return;

        for(Map.Entry<KeyMapping, Consumer<Minecraft>> entry : keyActions.entrySet()) {
            while(entry.getKey().consumeClick()) {
                entry.getValue().accept(mc);
            }
        }
    }

    private void handleOpenMenu(Minecraft mc) {
        System.out.println("Dispatching Menu Packet!");
        PacketDistributor.sendToServer(new OpenMenuPayload());
    }

    private void handleActivateArmor(Minecraft mc) {
        System.out.println("Dispatching Armor Activation Packet!");
        PacketDistributor.sendToServer(new ToggleArmorPayload());
    }
}
