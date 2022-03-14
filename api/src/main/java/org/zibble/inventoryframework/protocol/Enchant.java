package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.protocol.item.enchantment.type.EnchantmentType;
import org.jetbrains.annotations.NotNull;

public interface Enchant {

    int getId();

    @NotNull
    EnchantmentType asProtocol();

}
