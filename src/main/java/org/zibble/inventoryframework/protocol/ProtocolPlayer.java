package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface ProtocolPlayer<T> {

    @NotNull UUID uuid();

    @NotNull String name();

    @NotNull T handle();

    @NotNull
    default User asProtocol() {
        return PacketEvents.getAPI().getPlayerManager().getUser(this.handle());
    }

    default void sendPacket(@NotNull PacketWrapper<?> wrapper) {
        this.asProtocol().sendPacket(wrapper);
    }

    void updatePlayerInventory();

}
