package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Representation of player for Inventory Framework.
 * @param <T> The server's implementation of Player
 */
public interface ProtocolPlayer<T> {

    @NotNull UUID getUniqueID();

    @NotNull String getName();

    /**
     * @return The server's implementation of Player
     */
    @NotNull T handle();

    @NotNull
    @ApiStatus.Internal
    default User asProtocol() {
        return PacketEvents.getAPI().getPlayerManager().getUser(this.handle());
    }

    @ApiStatus.Internal
    default void sendPacket(@NotNull PacketWrapper<?> wrapper) {
        this.asProtocol().sendPacket(wrapper);
    }

    /**
     * Implementations are advised to try to implement this method in any way possible.
     * Not doing so can lead to ghost items in the player's inventory & the GUI.
     */
    @ApiStatus.Internal
    void updatePlayerInventory();

}
