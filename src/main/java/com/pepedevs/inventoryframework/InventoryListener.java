package com.pepedevs.inventoryframework;

import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;

public abstract class InventoryListener {

    public void onOpen() {}

    public void onClose() {}

    public boolean onClick(int slot, WrapperPlayClientClickWindow.WindowClickType clickType) {
        return true;
    }

    public void onButtonClick(int buttonID) {}

}
