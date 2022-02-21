package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.protocol.item.type.ItemType;

public class Material {

    private final ItemType itemType;

    public Material(ItemType itemType) {
        this.itemType = itemType;
    }

    public ItemType asProtocol() {
        return itemType;
    }

}
