package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.protocol.item.enchantment.type.EnchantmentType;

public interface Enchant {

    int getId();

    EnchantmentType asProtocol();

}
