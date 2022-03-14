package org.zibble.inventoryframework;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import com.github.retrooper.packetevents.manager.server.ServerVersion;
import org.zibble.inventoryframework.menu.InventoryPacketListener;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * The main class of Inventory Framework.
 * This class is responsible for registering the packet listener and running the actions defined in click actions.
 * <p>
 * Initialize this class with {@link #init(ExecutorService)} or {@link #init(Consumer)}
 * And when the server stops, run {@link #terminate()}
 */
public class InventoryFramework {

    private static InventoryFramework instance;

    private final PacketListenerCommon listener;
    private final ServerVersion serverVersion;

    private final Consumer<Runnable> actionRunner;

    @Contract("_ -> new")
    @NotNull
    public static InventoryFramework init(@NotNull ExecutorService actionRunner) {
        return init(actionRunner::submit);
    }

    @Contract("_ -> new")
    @NotNull
    public static InventoryFramework init(@NotNull Consumer<Runnable> actionRunner) {
        return new InventoryFramework(actionRunner);
    }

    @Contract(" -> new")
    @NotNull
    public static InventoryFramework init() {
        return new InventoryFramework(null);
    }

    private InventoryFramework(@Nullable Consumer<Runnable> runner) {
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
        if (actionRunner != null) {
            actionRunner.accept(runnable);
        } else {
            runnable.run();
        }
    }

    /**
     * Terminates listeners and shuts down the action runner
     */
    public void terminate() {
        PacketEvents.getAPI().getEventManager().unregisterListener(this.listener);
        instance = null;
    }

    public static InventoryFramework framework() {
        return instance;
    }

}
