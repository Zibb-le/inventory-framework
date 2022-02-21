package org.zibble.inventoryframework.protocol;

import com.github.retrooper.packetevents.protocol.item.type.ItemType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class Material {

    private @NotNull final ItemType itemType;
    private @Range(from = 0, to = Byte.MAX_VALUE) final byte data;

    public Material(@NotNull ItemType itemType) {
        this((byte) 0, itemType);
    }

    public Material(@Range(from = 0, to = Byte.MAX_VALUE) byte data, @NotNull ItemType itemType) {
        this.itemType = itemType;
        this.data = data;
    }

    @NotNull
    public ItemType asProtocol() {
        return itemType;
    }

    public @Range(from = 0, to = Integer.MAX_VALUE) byte legacyData() {
        return data;
    }
}
