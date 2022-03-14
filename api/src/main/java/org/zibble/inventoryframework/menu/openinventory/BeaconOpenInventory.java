package org.zibble.inventoryframework.menu.openinventory;

import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.menu.inventory.buttonmenus.BeaconMenu;
import org.zibble.inventoryframework.protocol.Item;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;

import java.util.function.Consumer;

@ApiStatus.Internal
public class BeaconOpenInventory extends AbstractOpenInventory {

    private @NotNull final InventoryListener inventoryListener;

    public BeaconOpenInventory(@NotNull ProtocolPlayer<?> user, @NotNull BeaconMenu menu) {
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
                MenuItem<? extends Item> item = menu.asItemList().get(slot);
                if (item == null || item.getClickAction() == null) return;
                InventoryFramework.framework().run(() -> item.getClickAction().onClick(user, clickType));
            }

            @Override
            public void onButtonClick(int buttonID) {
                if (buttonID < 0) return;
                Consumer<Integer> clickHandler = menu.getButtonClickHandler();
                if (clickHandler != null) InventoryFramework.framework().run(() -> clickHandler.accept(buttonID));
            }
        };
    }

    @Override
    public @NotNull InventoryListener listener() {
        return this.inventoryListener;
    }
}
