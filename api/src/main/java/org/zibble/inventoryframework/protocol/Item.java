package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface Item {

    @NotNull
    ItemStack asProtocol();

}
