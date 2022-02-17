package com.pepedevs.inventoryframework.openinventory;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerCloseWindow;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSetSlot;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerWindowItems;
import com.pepedevs.inventoryframework.*;
import com.pepedevs.inventoryframework.protocol.PacketUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOpenInventory {

    private static byte containerId = 100;

    protected User user;
    protected Menu menu;
    protected byte windowId;

    public AbstractOpenInventory(User user, Menu menu) {
        this.user = user;
        this.menu = menu;
    }

    public void sendItems(List<ItemStack> items) {
        WrapperPlayServerWindowItems wrapper = new WrapperPlayServerWindowItems(this.windowId, 0, items, InventoryFramework.framework().platformAdaptor().getItemOnCursor(this.user));
        PacketUtils.sendPacket(this.user, wrapper);
    }

    public void sendItems(MenuItem<ItemStack>[][] items) {
        List<ItemStack> itemStacks = new ArrayList<>(items.length * items[0].length);
        for (MenuItem<ItemStack>[] a : items) {
            for (MenuItem<ItemStack> item : a) {
                if (item == null) itemStacks.add(null);
                else itemStacks.add(item.getContent());
            }
        }
        this.sendItems(itemStacks);
    }

    public void setSlot(int slot, ItemStack itemStack) {
        WrapperPlayServerSetSlot wrapper = new WrapperPlayServerSetSlot(this.windowId, 0, slot, itemStack);
        PacketUtils.sendPacket(this.user, wrapper);
    }

    protected void close() {
        WrapperPlayServerCloseWindow wrapper = new WrapperPlayServerCloseWindow(this.windowId);
        PacketUtils.sendPacket(this.user, wrapper);
        this.getInventoryListener().onClose();
    }

    public User getUser() {
        return user;
    }

    public InventoryType getInventoryType() {
        return this.menu.getInventoryType();
    }

    public byte getWindowId() {
        return windowId;
    }

    protected byte nextContainerId() {
        containerId = containerId == Byte.MAX_VALUE ? 100 : ++containerId;
        return containerId;
    }

    public abstract void show();

    public abstract InventoryListener getInventoryListener();

}
