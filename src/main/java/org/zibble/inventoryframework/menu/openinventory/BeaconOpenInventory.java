package org.zibble.inventoryframework.menu.openinventory;

import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.inventory.buttonmenus.BeaconMenu;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.ItemStack;

import java.util.function.Consumer;

public class BeaconOpenInventory extends AbstractOpenInventory {

    private @NotNull final InventoryListener inventoryListener;

    public BeaconOpenInventory(@NotNull ProtocolPlayer<?> user, @NotNull BeaconMenu menu) {
        super(user, menu);
        this.inventoryListener = new InventoryListener() {
            @Override
            public void onOpen() {
                Consumer<ProtocolPlayer<?>> open = menu.onOpen();
                if (open != null) open.accept(user);
            }

            @Override
            public void onClose() {
                Consumer<ProtocolPlayer<?>> close = menu.onClose();
                if (close != null) close.accept(user);
            }

            @Override
            public void onClick(int slot, @NotNull ClickType clickType) {
                if (slot < 0) return;
                MenuItem<ItemStack> item = menu.asList().get(slot);
                if (item == null || item.clickAction() == null) return;
                item.clickAction().onClick(user, clickType);
            }

            @Override
            public void onButtonClick(int buttonID) {
                if (buttonID < 0) return;
                Consumer<Integer> clickHandler = menu.buttonClickHandler();
                if (clickHandler != null) clickHandler.accept(buttonID);

            }
        };
    }

    @Override
    public @NotNull InventoryListener listener() {
        return this.inventoryListener;
    }
}
