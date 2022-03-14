package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.protocol.item.ItemStack;

public interface Item {

    ItemStack asProtocol();

}
