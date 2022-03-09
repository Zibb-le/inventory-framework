package org.zibble.inventoryframework;

import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

/**
 * A functional interface used to define tasks to be executed when a player clicks on an inventory item.
 * All click actions are handled on the thread/consumer provided during initialization of Inventory Framework.
 */
@FunctionalInterface
public interface ClickAction {

    void onClick(@NotNull ProtocolPlayer<?> clicker, @NotNull ClickType clickType);

}
