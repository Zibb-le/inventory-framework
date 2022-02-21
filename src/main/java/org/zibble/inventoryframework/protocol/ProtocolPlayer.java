package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;

import java.util.UUID;

public interface ProtocolPlayer<T> {

    UUID uuid();

    String name();

    T handle();

    default User asProtocol() {
        return PacketEvents.getAPI().getPlayerManager().getUser(this.handle());
    }

    default void sendPacket(PacketWrapper<?> wrapper) {
        this.asProtocol().sendPacket(wrapper);
    }

}
