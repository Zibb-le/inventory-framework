package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.protocol.potion.PotionType;
import org.jetbrains.annotations.NotNull;

public interface Effect {

    int getId();

    @NotNull
    PotionType asProtocol();

}
