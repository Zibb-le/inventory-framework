package org.zibble.inventoryframework.menu.openinventory;

import com.github.retrooper.packetevents.protocol.player.User;
import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.FixedButtonMenu;
import org.zibble.inventoryframework.protocol.item.ItemStack;

public class FixedButtonOpenInventory extends AbstractOpenInventory {

    private final InventoryListener listener;

    public FixedButtonOpenInventory(User user, FixedButtonMenu menu) {
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
            public void onClick(int slot, ClickType clickType) {
                if (slot < 0) return;
                MenuItem<ItemStack> item = menu.getAsList().get(slot);
                if (item == null || item.getClickAction() == null) return;
                item.getClickAction().onClick(user, clickType);
            }

            @Override
            public void onButtonClick(int buttonID) {
                if (buttonID < 0) return;
                menu.getButtonClickHandler().accept(buttonID);
            }
        };
    }

    @Override
    public InventoryListener getInventoryListener() {
        return this.listener;
    }

    @Override
    public void show() {

    }

}
