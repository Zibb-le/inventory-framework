package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.protocol.potion.PotionType;

public interface Effect {

    int getId();

    PotionType asProtocol();

}
