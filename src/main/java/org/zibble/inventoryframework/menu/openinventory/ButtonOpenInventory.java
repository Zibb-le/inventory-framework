package org.zibble.inventoryframework.menu.openinventory;

import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.ButtonMenu;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.ItemStack;

public class ButtonOpenInventory extends AbstractOpenInventory {

    private final InventoryListener inventoryListener;

    public ButtonOpenInventory(ProtocolPlayer<?> user, ButtonMenu<?> menu) {
        super(user, menu);
        this.inventoryListener = new InventoryListener() {
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
                MenuItem<?> item = menu.buttonsAsList().get(buttonID);
                if (item == null || item.clickAction() == null) return;
                item.clickAction().onClick(user, ClickType.SWAP);
            }
        };
    }

    @Override
    public InventoryListener listener() {
        return this.inventoryListener;
    }

    @Override
    public void show() {

    }
}
