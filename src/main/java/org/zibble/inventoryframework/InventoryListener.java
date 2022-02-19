package org.zibble.inventoryframework;

public abstract class InventoryListener {

    public void onOpen() {}

    public void onClose() {}

    public void onClick(int slot, ClickType clickType) {}

    public void onButtonClick(int buttonID) {}

}
