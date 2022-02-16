package com.pepedevs.inventoryframework.inventory;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import com.pepedevs.inventoryframework.*;
import com.pepedevs.inventoryframework.openinventory.AbstractOpenInventory;
import net.kyori.adventure.text.Component;

public class ChestMenu extends NamedMenu {

    public ChestMenu(int rows, Component title) {
        super(rows, 9, title);
    }

    @Override
    public InventoryType getInventoryType() {
        return InventoryType.CHEST;
    }

    public static class Open extends AbstractOpenInventory {

        private final InventoryListener listener;

        public Open(User user, Menu menu) {
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
                public boolean onClick(int slot, WrapperPlayClientClickWindow.WindowClickType clickType) {
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

    }

}
