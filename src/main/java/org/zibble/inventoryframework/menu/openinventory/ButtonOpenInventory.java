package org.zibble.inventoryframework.menu.openinventory;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.ButtonMenu;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.StackItem;

import java.util.function.Consumer;

@ApiStatus.Internal
public class ButtonOpenInventory extends AbstractOpenInventory {

    private @NotNull final InventoryListener inventoryListener;

    public ButtonOpenInventory(@NotNull ProtocolPlayer<?> user, @NotNull ButtonMenu<?> menu) {
        super(user, menu);
        this.inventoryListener = new InventoryListener() {
            @Override
            public void onOpen() {
                Consumer<ProtocolPlayer<?>> open = menu.onOpen();
                if (open != null) InventoryFramework.framework().run(() -> open.accept(user));
            }

            @Override
            public void onClose() {
                Consumer<ProtocolPlayer<?>> close = menu.onClose();
                if (close != null) InventoryFramework.framework().run(() -> close.accept(user));
            }

            @Override
            public void onClick(int slot, com.github.retrooper.packetevents.protocol.item.ItemStack clickItem, @NotNull ClickType clickType) {
                if (slot < 0) return;
                MenuItem<StackItem> item = menu.asItemList().get(slot);
                if (item == null || item.getClickAction() == null) return;
                InventoryFramework.framework().run(() -> item.getClickAction().onClick(user, clickType));
            }

            @Override
            public void onButtonClick(int buttonID) {
                if (buttonID < 0) return;
                MenuItem<?> item = menu.getButtonsAsList().get(buttonID);
                if (item == null || item.getClickAction() == null) return;
                InventoryFramework.framework().run(() -> item.getClickAction().onClick(user, ClickType.SWAP));
            }
        };
    }

    @Override
    @NotNull
    public InventoryListener listener() {
        return this.inventoryListener;
    }

}
