package com.pepedevs.inventoryframework;

import com.github.retrooper.packetevents.protocol.player.User;

public interface ClickAction {

    boolean onClick(User clicker, ClickType clickType);

}
