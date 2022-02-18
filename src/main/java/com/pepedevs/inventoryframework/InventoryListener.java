package com.pepedevs.inventoryframework;

import com.github.retrooper.packetevents.protocol.item.ItemStack;

public abstract class InventoryListener {

    public void onOpen() {}

    public void onClose() {}

    public void onClick(int slot, ItemStack clicked, ClickType clickType) {}

    public void onButtonClick(int buttonID) {}

}
