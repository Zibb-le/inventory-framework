package org.zibble.inventoryframework.menu.openinventory;

import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.FixedButtonMenu;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.ItemStack;

public class FixedButtonOpenInventory extends AbstractOpenInventory {

    private final InventoryListener listener;

    public FixedButtonOpenInventory(ProtocolPlayer<?> user, FixedButtonMenu menu) {
        super(user, menu);
        this.listener = new InventoryListener() {
            @Override
            public void onOpen() {
                if (menu.onOpen() != null) {
                    menu.onOpen().accept(user);
                }
            }

            @Override
            public void onClose() {
                if (menu.onClose() != null) {
                    menu.onClose().accept(user);
                }
            }

            @Override
            public void onClick(int slot, ClickType clickType) {
                if (slot < 0) return;
                MenuItem<ItemStack> item = menu.asList().get(slot);
                if (item == null || item.clickAction() == null) return;
                item.clickAction().onClick(user, clickType);
            }

            @Override
            public void onButtonClick(int buttonID) {
                if (buttonID < 0) return;
                if (menu.buttonClickHandler() == null) return;
                menu.buttonClickHandler().accept(buttonID);
            }
        };
    }

    @Override
    public InventoryListener listener() {
        return this.listener;
    }

    @Override
    public void show() {

    }

}
