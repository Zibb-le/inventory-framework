package org.zibble.inventoryframework;

import org.jetbrains.annotations.NotNull;

public abstract class InventoryListener {

    public void onOpen() {}

    public void onClose() {}

    public void onClick(int slot, @NotNull ClickType clickType) {}

    public void onButtonClick(int buttonID) {}

}
