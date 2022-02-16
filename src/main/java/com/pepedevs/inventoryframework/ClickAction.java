package com.pepedevs.inventoryframework;

import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;

public interface ClickAction {

    boolean onClick(User clicker, WrapperPlayClientClickWindow.WindowClickType clickType);

}
