package org.zibble.inventoryframework;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public abstract class InventoryListener {

    public void onOpen() {}

    public void onClose() {}

    public void onClick(int slot, ItemStack clickItem, @NotNull ClickType clickType) {}

    public void onButtonClick(int buttonID) {}

}
