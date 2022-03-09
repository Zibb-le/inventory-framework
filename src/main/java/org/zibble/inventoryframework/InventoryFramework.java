package org.zibble.inventoryframework;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import com.github.retrooper.packetevents.manager.server.ServerVersion;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.menu.InventoryPacketListener;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * The main class of Inventory Framework.
 * This class is responsible for registering the packet listener and running the actions defined in click actions.
 * <p>
 * Initialize this class with {@link #init(ExecutorService)}
 * And when the server stops run {@link #terminate()}
 */
public class InventoryFramework {

    private static InventoryFramework instance;

    private final PacketListenerCommon listener;
    private final ServerVersion serverVersion;

    private final ExecutorService actionRunner;

    @Contract("_ -> new")
    @NotNull
    public static InventoryFramework init(@NotNull ExecutorService actionRunner) {
        return new InventoryFramework(actionRunner);
    }

    private InventoryFramework(@NotNull ExecutorService runner) {
        if (instance != null) {
            throw new IllegalStateException("InventoryFramework is already initialized");
        }

        instance = this;
        this.actionRunner = runner;
        this.listener = PacketEvents.getAPI().getEventManager().registerListener(new InventoryPacketListener());
        this.serverVersion = PacketEvents.getAPI().getServerManager().getVersion();
    }

    @ApiStatus.Internal
    public ServerVersion getServerVersion() {
        return this.serverVersion;
    }

    @ApiStatus.Internal
    public void run(Runnable runnable) {
        actionRunner.submit(runnable);
    }

    /**
     * Terminates listeners and shuts down the action runner
     */
    public void terminate() {
        PacketEvents.getAPI().getEventManager().unregisterListener(this.listener);
        actionRunner.shutdown();
        instance = null;
    }

    public static InventoryFramework framework() {
        return instance;
    }

}
