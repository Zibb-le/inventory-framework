package org.zibble.inventoryframework;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import com.github.retrooper.packetevents.manager.server.ServerVersion;
import org.zibble.inventoryframework.menu.InventoryPacketListener;

import java.util.function.Consumer;

public class InventoryFramework {

    private static InventoryFramework instance;

    private final PacketListenerCommon listener;
    private final ServerVersion serverVersion;
    private Consumer<Runnable> runAction;

    public static InventoryFramework init() {
        return init(null);
    }

    public static InventoryFramework init(Consumer<Runnable> runAction) {
        return new InventoryFramework(runAction);
    }

    private InventoryFramework(Consumer<Runnable> runAction) {
        if (instance != null) {
            throw new IllegalStateException("InventoryFramework is already initialized");
        }

        instance = this;
        this.runAction = runAction;
        this.listener = PacketEvents.getAPI().getEventManager().registerListener(new InventoryPacketListener());
        this.serverVersion = PacketEvents.getAPI().getServerManager().getVersion();
    }

    public ServerVersion serverVersion() {
        return this.serverVersion;
    }

    public void run(Runnable runnable) {
        if (runAction != null) {
            runAction.accept(runnable);
        } else {
            runnable.run();
        }
    }

    public void terminate() {
        PacketEvents.getAPI().getEventManager().unregisterListener(this.listener);
        instance = null;
    }

    public static InventoryFramework framework() {
        return instance;
    }

}
