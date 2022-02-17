package com.pepedevs.inventoryframework.openinventory;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerOpenWindow;
import com.pepedevs.inventoryframework.*;
import com.pepedevs.inventoryframework.protocol.PacketUtils;
import net.kyori.adventure.text.Component;

public class OpenInventory extends AbstractOpenInventory {

    private final InventoryListener listener;

    public OpenInventory(User user, Menu menu) {
        super(user, menu);
        this.listener = new InventoryListener() {
            @Override
            public void onOpen() {
                if (menu.getOnOpen() != null) {
                    menu.getOnOpen().accept(user);
                }
            }

            @Override
            public void onClose() {
                if (menu.getOnClose() != null) {
                    menu.getOnClose().accept(user);
                }
            }

            @Override
            public boolean onClick(int slot, ItemStack clicked, ItemStack onCursor, ClickType clickType) {
                if (slot < 0) return false;
                if (slot >= menu.getColumns() * menu.getRows()) {
                    SlotClickAction clickAction = menu.getPlayerInventoryComponent().getClickAction(slot - menu.getColumns() * menu.getRows());
                    if (clickAction == null) return true;
                    return clickAction.onClick(slot, clicked, onCursor, user, clickType);
                }
                MenuItem<ItemStack> item = menu.getItems()[slot / menu.getColumns()][slot % menu.getColumns()];
                if (item == null || item.getClickAction() == null) return true;
                return item.getClickAction().onClick(user, clickType);
            }
        };
    }

    @Override
    public InventoryListener getInventoryListener() {
        return this.listener;
    }

    @Override
    public void show() {
        Component title = this.menu instanceof NamedMenu ? ((NamedMenu) this.menu).getTitle() : Component.empty();
        byte id = this.nextContainerId();
        WrapperPlayServerOpenWindow wrapper = new WrapperPlayServerOpenWindow(
                id,
                this.getInventoryType().getLegacyId(),
                title,
                this.getInventoryType() == InventoryType.CHEST ? menu.getColumns() * menu.getRows() : 0,
                0);
        PacketUtils.sendPacket(this.user, wrapper);
        this.windowId = id;
        this.getInventoryListener().onOpen();
    }
}
