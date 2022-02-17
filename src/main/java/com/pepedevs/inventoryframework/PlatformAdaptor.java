package com.pepedevs.inventoryframework;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.player.User;

public interface PlatformAdaptor {

    ItemStack getItemOnCursor(User player);

}
