package com.pepedevs.inventoryframework.protocol;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;

public class PacketUtils {

    public static void sendPacket(User user, PacketWrapper<?> packet) {
        PacketEvents.getAPI().getPlayerManager().sendPacket(user.getChannel(), packet);
    }

}
