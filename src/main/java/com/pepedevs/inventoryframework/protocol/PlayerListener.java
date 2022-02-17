package com.pepedevs.inventoryframework.protocol;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSetSlot;

public class PlayerListener extends PacketListenerAbstract {

    public PlayerListener() {
        super(PacketListenerPriority.MONITOR);
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            WrapperPlayClientClickWindow wrapper = new WrapperPlayClientClickWindow(event);
            ProtocolPlayer.player(event.getUser()).setCarried(wrapper.getClickedItemStack());
        }
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.JOIN_GAME) {
            ProtocolPlayer.handle(event.getUser());
        } else if (event.getPacketType() == PacketType.Play.Server.DISCONNECT) {
            ProtocolPlayer.unhandle(event.getUser());
        } else if (event.getPacketType() == PacketType.Play.Server.CLOSE_WINDOW) {
            ProtocolPlayer.player(event.getUser()).setCarried(null);
        } else if (event.getPacketType() == PacketType.Play.Server.SET_SLOT) {
            WrapperPlayServerSetSlot wrapper = new WrapperPlayServerSetSlot(event);
            if (wrapper.getSlot() == -1) {
                ProtocolPlayer.player(event.getUser()).setCarried(wrapper.getItem());
            }
        }
    }

}
