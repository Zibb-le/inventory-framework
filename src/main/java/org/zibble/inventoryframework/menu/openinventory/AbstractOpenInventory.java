package org.zibble.inventoryframework.menu.openinventory;

import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerCloseWindow;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSetSlot;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerWindowItems;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.protocol.item.ItemStack;

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

    public void sendItems(List<com.github.retrooper.packetevents.protocol.item.ItemStack> items) {
        WrapperPlayServerWindowItems wrapper = new WrapperPlayServerWindowItems(this.windowId, 0, items, null);
        this.user.sendPacket(wrapper);
    }

    public void sendItems(MenuItem<ItemStack>[][] items) {
        List<com.github.retrooper.packetevents.protocol.item.ItemStack> itemStacks = new ArrayList<>(items.length * items[0].length);
        for (MenuItem<ItemStack>[] a : items) {
            for (MenuItem<ItemStack> item : a) {
                if (item == null) itemStacks.add(null);
                else itemStacks.add(item.getContent().asProtocol());
            }
        }
        this.sendItems(itemStacks);
    }

    public void setSlot(int slot, ItemStack itemStack) {
        WrapperPlayServerSetSlot wrapper = new WrapperPlayServerSetSlot(this.windowId, 0, slot, itemStack.asProtocol());
        this.user.sendPacket(wrapper);
    }

    protected void close() {
        WrapperPlayServerCloseWindow wrapper = new WrapperPlayServerCloseWindow(this.windowId);
        this.user.sendPacket(wrapper);
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
