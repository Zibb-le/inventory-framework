package org.zibble.inventoryframework;

import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

public interface ClickAction {

    void onClick(@NotNull ProtocolPlayer<?> clicker, @NotNull ClickType clickType);

}
