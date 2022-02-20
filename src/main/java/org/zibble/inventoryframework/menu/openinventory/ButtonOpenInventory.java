package org.zibble.inventoryframework.menu.openinventory;

import com.github.retrooper.packetevents.protocol.player.User;
import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.ButtonMenu;
import org.zibble.inventoryframework.protocol.item.ItemStack;

public class ButtonOpenInventory extends AbstractOpenInventory {

    private final InventoryListener inventoryListener;

    public ButtonOpenInventory(User user, ButtonMenu<?> menu) {
        super(user, menu);
        this.inventoryListener = new InventoryListener() {
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
                MenuItem<?> item = menu.getButtonsAsList().get(buttonID);
                if (item == null || item.getClickAction() == null) return;
                item.getClickAction().onClick(user, ClickType.SWAP);
            }
        };
    }

    @Override
    public InventoryListener getInventoryListener() {
        return this.inventoryListener;
    }

    @Override
    public void show() {

    }
}
