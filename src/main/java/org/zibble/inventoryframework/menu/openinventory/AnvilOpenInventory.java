package org.zibble.inventoryframework.menu.openinventory;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zibble.inventoryframework.ClickType;
import org.zibble.inventoryframework.InventoryFramework;
import org.zibble.inventoryframework.InventoryListener;
import org.zibble.inventoryframework.MenuItem;
import org.zibble.inventoryframework.menu.inventory.AnvilMenu;
import org.zibble.inventoryframework.protocol.ProtocolPlayer;
import org.zibble.inventoryframework.protocol.item.StackItem;

import java.util.function.Consumer;

@ApiStatus.Internal
public class AnvilOpenInventory extends AbstractOpenInventory {

    private final InventoryListener listener;

    public AnvilOpenInventory(@NotNull ProtocolPlayer<?> user, @NotNull AnvilMenu menu) {
        super(user, menu);
        this.listener = new InventoryListener() {
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
                if (slot < 0 || slot >= 3) return;
                if (slot == 2 && menu.getOutputClick() != null) {
                    InventoryFramework.framework().run(() -> menu.getOutputClick().accept(this.getDisplayName(clickItem)));
                    return;
                }
                MenuItem<StackItem> item = menu.asItemList().get(slot);
                if (item == null || item.getClickAction() == null) return;
                InventoryFramework.framework().run(() -> item.getClickAction().onClick(user, clickType));
            }

            private @Nullable String getDisplayName(com.github.retrooper.packetevents.protocol.item.@NotNull ItemStack item) {
                if (item.getNBT() == null) return null;
                return item.getNBT().getCompoundTagOrNull("display").getStringTagValueOrNull("Name");
            }
        };
    }

    @Override
    public @NotNull InventoryListener listener() {
        return this.listener;
    }

}
