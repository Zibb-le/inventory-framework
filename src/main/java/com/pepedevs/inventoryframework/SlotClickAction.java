package com.pepedevs.inventoryframework;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.player.User;

@FunctionalInterface
public interface SlotClickAction {

    boolean onClick(int slot, ItemStack itemClicked, ItemStack itemOnCursor, User user, ClickType clickType);

}
