package org.zibble.inventoryframework;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import com.github.retrooper.packetevents.manager.server.ServerVersion;
import org.zibble.inventoryframework.protocol.InventoryPacketListener;

public class InventoryFramework {

    private static InventoryFramework instance;

    private final PacketListenerCommon listener;
    private final ServerVersion serverVersion;

    public static InventoryFramework init() {
        return new InventoryFramework();
    }

    private InventoryFramework() {
        if (instance != null) {
            throw new IllegalStateException("InventoryFramework is already initialized");
        }

        instance = this;
        this.listener = PacketEvents.getAPI().getEventManager().registerListener(new InventoryPacketListener());
        this.serverVersion = PacketEvents.getAPI().getServerManager().getVersion();
    }

    public ServerVersion serverVersion() {
        return this.serverVersion;
    }

    public void terminate() {
        PacketEvents.getAPI().getEventManager().unregisterListener(this.listener);
        instance = null;
    }

    public static InventoryFramework framework() {
        return instance;
    }

}