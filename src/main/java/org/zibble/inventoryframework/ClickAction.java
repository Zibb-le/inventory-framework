package org.zibble.inventoryframework;

import org.zibble.inventoryframework.protocol.ProtocolPlayer;

public interface ClickAction {

    void onClick(ProtocolPlayer<?> clicker, ClickType clickType);

}
