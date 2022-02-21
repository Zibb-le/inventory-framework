package org.zibble.inventoryframework.menu.openinventory;

import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerCloseWindow;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSetSlot;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerWindowItems;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerWindowProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.property.PropertyPair;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOpenInventory {

    private static byte containerId = 100;

    protected @NotNull ProtocolPlayer<?> user;
    protected @NotNull Menu menu;
    protected @Range(from = 101, to = 255) byte windowId;

    public AbstractOpenInventory(@NotNull ProtocolPlayer<?> user, @NotNull Menu menu) {
        this.user = user;
        this.menu = menu;
    }

    public void sendItems(@NotNull List<com.github.retrooper.packetevents.protocol.item.ItemStack> items) {
        WrapperPlayServerWindowItems wrapper = new WrapperPlayServerWindowItems(this.windowId, 0, items, null);
        this.user.sendPacket(wrapper);
    }

    public void sendItems(@NotNull MenuItem<ItemStack>[][] items) {
        List<com.github.retrooper.packetevents.protocol.item.ItemStack> itemStacks = new ArrayList<>(items.length * items[0].length);
        for (MenuItem<ItemStack>[] a : items) {
            for (MenuItem<ItemStack> item : a) {
                if (item == null || item.content() == null) itemStacks.add(null);
                else itemStacks.add(item.content().asProtocol());
            }
        }
        this.sendItems(itemStacks);
    }

    public void setSlot(@Range(from = 0, to = Integer.MAX_VALUE) int slot, @Nullable ItemStack itemStack) {
        WrapperPlayServerSetSlot wrapper = new WrapperPlayServerSetSlot(this.windowId, 0, slot, itemStack == null ? null : itemStack.asProtocol());
        this.user.sendPacket(wrapper);
    }

    public void close() {
        WrapperPlayServerCloseWindow wrapper = new WrapperPlayServerCloseWindow(this.windowId);
        this.user.sendPacket(wrapper);
        this.listener().onClose();
    }

    @NotNull
    public ProtocolPlayer<?> player() {
        return user;
    }

    @NotNull
    public InventoryType type() {
        return this.menu.type();
    }

    @NotNull
    public Menu menu() {
        return this.menu;
    }

    public @Range(from = 101, to = 255) byte windowId() {
        return windowId;
    }

    @Range(from = 101, to = 255)
    protected byte nextContainerId() {
        containerId = containerId == Byte.MAX_VALUE ? 100 : ++containerId;
        return containerId;
    }

    public void updateWindowData(@Range(from = 0, to = Integer.MAX_VALUE) int ID, int value) {
        WrapperPlayServerWindowProperty wrapper = new WrapperPlayServerWindowProperty(this.windowId, ID, value);
        this.user.sendPacket(wrapper);
    }

    public void updateWindowData(@NotNull PropertyPair[] propertyPairs) {
        for (PropertyPair propertyPair : propertyPairs) {
            this.updateWindowData(propertyPair.id(), propertyPair.value());
        }
    }

    @NotNull
    public abstract InventoryListener listener();

    public abstract void show();

}
