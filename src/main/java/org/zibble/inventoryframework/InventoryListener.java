package org.zibble.inventoryframework;

import org.zibble.inventoryframework.protocol.item.ItemStack;

public abstract class InventoryListener {

    public void onOpen() {}

    public void onClose() {}

    public void onClick(int slot, ItemStack clicked, ClickType clickType) {}

    public void onButtonClick(int buttonID) {}

}
