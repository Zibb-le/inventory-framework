package org.zibble.inventoryframework.menu.openinventory;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.wrapper.play.server.*;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.InventoryType;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.Menu;
import org.zibble.inventoryframework.menu.nameable.NamedMenu;
import org.zibble.inventoryframework.menu.property.PropertyPair;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@ApiStatus.Internal
public abstract class AbstractOpenInventory {

    private static byte containerId = 100;

    protected @NotNull ProtocolPlayer<?> user;
    protected @NotNull Menu menu;
    protected @Range(from = 101, to = 255) byte windowId;

    public AbstractOpenInventory(@NotNull ProtocolPlayer<?> user, @NotNull Menu menu) {
        this.user = user;
        this.menu = menu;
    }

    public void show() {
        Component title = this.menu instanceof NamedMenu ? ((NamedMenu) this.menu).title() : Component.empty();
        this.windowId = this.nextContainerId();
        WrapperPlayServerOpenWindow wrapper;
        if (InventoryFramework.framework().serverVersion().isNewerThan(ServerVersion.V_1_13_2)) {
            wrapper = new WrapperPlayServerOpenWindow(
                    this.windowId,
                    this.type().latestId(menu.columns() * menu.rows()),
                    title);
        } else {
            wrapper = new WrapperPlayServerOpenWindow(
                    this.windowId,
                    this.type().legacyId(),
                    title,
                    this.type().legacySlots(menu.columns() * menu.rows()),
                    0);
        }
        this.user.sendPacket(wrapper);
        this.listener().onOpen();
    }

    public void sendItems(@NotNull List<com.github.retrooper.packetevents.protocol.item.ItemStack> items) {
        WrapperPlayServerWindowItems wrapper = new WrapperPlayServerWindowItems(this.windowId, 0, items, null);
        this.user.sendPacket(wrapper);
    }

    public void sendItems(@NotNull MenuItem<ItemStack>[][] items) {
        List<com.github.retrooper.packetevents.protocol.item.ItemStack> itemStacks = new ArrayList<>(items.length * items[0].length);
        for (MenuItem<ItemStack>[] a : items) {
            for (MenuItem<ItemStack> item : a) {
                if (item == null || item.getContent() == null) itemStacks.add(null);
                else itemStacks.add(item.getContent().asProtocol());
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

}
