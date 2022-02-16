package com.pepedevs.inventoryframework.openinventory;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerCloseWindow;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerOpenWindow;
import com.pepedevs.inventoryframework.InventoryListener;
import com.pepedevs.inventoryframework.InventoryType;
import com.pepedevs.inventoryframework.Menu;
import com.pepedevs.inventoryframework.NamedMenu;
import net.kyori.adventure.text.Component;

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
        Component title = this.menu instanceof NamedMenu ? ((NamedMenu) this.menu).getTitle() : Component.text("");
        WrapperPlayServerOpenWindow wrapper = new WrapperPlayServerOpenWindow(
                this.nextContainerId(),
                this.getInventoryType().getLegacyId(),
                title,
                this.getInventoryType() == InventoryType.CHEST ? menu.getColumns() * menu.getRows() : 0,
                0);
        PacketEvents.getAPI().getPlayerManager().sendPacket(this.user.getChannel(), wrapper);
    }

    protected void close() {
        WrapperPlayServerCloseWindow wrapper = new WrapperPlayServerCloseWindow(this.windowId);
        PacketEvents.getAPI().getPlayerManager().sendPacket(this.user.getChannel(), wrapper);
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
