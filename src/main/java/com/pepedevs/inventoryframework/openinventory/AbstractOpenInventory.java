package com.pepedevs.inventoryframework.openinventory;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerCloseWindow;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerOpenWindow;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerWindowItems;
import com.pepedevs.inventoryframework.*;
import com.pepedevs.inventoryframework.protocol.PacketUtils;
import net.kyori.adventure.text.Component;

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

    protected void show() {
        Component title = this.menu instanceof NamedMenu ? ((NamedMenu) this.menu).getTitle() : Component.empty();
        WrapperPlayServerOpenWindow wrapper = new WrapperPlayServerOpenWindow(
                this.nextContainerId(),
                this.getInventoryType().getLegacyId(),
                title,
                this.getInventoryType() == InventoryType.CHEST ? menu.getColumns() * menu.getRows() : 0,
                0);
        PacketUtils.sendPacket(this.user, wrapper);
        this.getInventoryListener().onOpen();
    }

    public void sendItems(List<ItemStack> items) {
        WrapperPlayServerWindowItems wrapper = new WrapperPlayServerWindowItems(this.windowId, 0, items, InventoryFramework.framework().platformAdaptor().getItemOnCursor(this.user));
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

    public abstract InventoryListener getInventoryListener();

}
