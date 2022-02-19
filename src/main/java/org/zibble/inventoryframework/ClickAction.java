package org.zibble.inventoryframework;

import com.github.retrooper.packetevents.protocol.player.User;

public interface ClickAction {

    void onClick(User clicker, ClickType clickType);

}
