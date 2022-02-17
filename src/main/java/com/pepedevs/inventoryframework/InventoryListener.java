package com.pepedevs.inventoryframework;

import com.github.retrooper.packetevents.protocol.item.ItemStack;

public abstract class InventoryListener {

    public void onOpen() {}

    public void onClose() {}

    public boolean onClick(int slot, ItemStack clicked, ItemStack onCursor, ClickType clickType) {
        return true;
    }

    public void onButtonClick(int buttonID) {}

}
