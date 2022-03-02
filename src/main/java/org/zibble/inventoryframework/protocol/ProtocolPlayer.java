package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface ProtocolPlayer<T> {

    @NotNull UUID uuid();

    @NotNull String name();

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

    @ApiStatus.Internal
    void updatePlayerInventory();

}
